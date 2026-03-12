package com.example.csc325_firebase_webview_auth.view;

import com.example.csc325_firebase_webview_auth.model.Person;
import com.example.csc325_firebase_webview_auth.viewmodel.AccessDataViewModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javafx.stage.FileChooser;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

public class AccessFBView {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> majorColumn;

    @FXML
    private TableColumn<Person, Integer> ageColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField majorField;

    @FXML
    private TextField ageField;

    @FXML
    private Button writeButton;

    @FXML
    private Button readButton;

    private ObservableList<Person> listOfUsers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();

        nameField.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
        majorField.textProperty().bindBidirectional(accessDataViewModel.userMajorProperty());

        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());

        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        majorColumn.setCellValueFactory(data -> data.getValue().majorProperty());
        ageColumn.setCellValueFactory(data -> data.getValue().ageProperty().asObject());

        tableView.setItems(listOfUsers);
    }

    @FXML
    private void addRecord(ActionEvent event) {
        addData();
    }

    @FXML
    private void readRecord(ActionEvent event) {
        readFirebase();
    }

    @FXML
    private void regRecord(ActionEvent event) {
        registerUser();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/files/WebContainer.fxml");
    }

    public void addData() {

        DocumentReference docRef =
                App.fstore.collection("References").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameField.getText());
        data.put("Major", majorField.getText());
        data.put("Age", Integer.parseInt(ageField.getText()));

        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public boolean readFirebase() {

        listOfUsers.clear();

        ApiFuture<QuerySnapshot> future =
                App.fstore.collection("References").get();

        try {

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                Person person = new Person(
                        String.valueOf(document.getData().get("Name")),
                        document.getData().get("Major").toString(),
                        Integer.parseInt(document.getData().get("Age").toString())
                );

                listOfUsers.add(person);
            }

            return true;

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registerUser() {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setDisplayName("John Doe")
                .setDisabled(false);

        try {

            UserRecord userRecord = App.fauth.createUser(request);
            System.out.println("Created user: " + userRecord.getUid());
            return true;

        } catch (FirebaseAuthException ex) {
            return false;
        }
    }

    @FXML
    private void openLogin() {
        try {
            App.setRoot("/files/Login.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openRegister() {
        try {
            App.setRoot("/files/Register.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        System.out.println("User logged out");
    }

    // Requirement 6: Upload profile picture
    @FXML
    private void uploadPicture() {

        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        if (file != null) {
            System.out.println("Selected file: " + file.getAbsolutePath());
        }
    }
}