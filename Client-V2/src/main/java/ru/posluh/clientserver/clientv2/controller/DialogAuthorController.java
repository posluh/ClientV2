package ru.posluh.clientserver.clientv2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;
import ru.posluh.clientserver.clientv2.utils.AlertUtils;
import ru.posluh.clientserver.clientv2.utils.HTTPUtils;
import ru.posluh.clientserver.clientv2.utils.ValidationAuthor;

import java.io.IOException;

public class DialogAuthorController {

    @FXML
    private Button buttonSaveAuthor;

    @FXML
    private Button buttonsCancelAuthor;

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    private Stage editDialogStage;

    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }

    private AuthorEntity author;

    public void setLabels(AuthorEntity author) {
        this.author = author;
        name.setText(author.getName());
        surname.setText(author.getSurname());
        lastname.setText(author.getLastname());
    }

    private boolean okClicked = false;

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void cancelAuthor(ActionEvent event) {
        editDialogStage.close();
    }

    @FXML
    void saveAuthor() throws IOException {
        if (isInputValid()) {
            author.setName(name.getText());
            author.setLastname(lastname.getText());
            author.setSurname(surname.getText());
            author.setId(HTTPUtils.sendAuthorAndGetData(author).getId());

            okClicked = true;
            editDialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = ValidationAuthor.getErrorMessageFromBookFields(
                name.getText(),
                surname.getText(),
                lastname.getText()
        );
        if (errorMessage.length() == 0)
            return true;
        else {
            AlertUtils.showWarning(errorMessage);
            return false;
        }
    }
}
