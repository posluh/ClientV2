package ru.posluh.clientserver.clientv2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;
import ru.posluh.clientserver.clientv2.entity.BookEntity;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;
import ru.posluh.clientserver.clientv2.utils.AlertUtils;
import ru.posluh.clientserver.clientv2.utils.HTTPUtils;
import ru.posluh.clientserver.clientv2.utils.ValidationBook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.posluh.clientserver.clientv2.controller.ApplicationController.authorData;
import static ru.posluh.clientserver.clientv2.controller.ApplicationController.publishingData;

public class DialgBookController {
    @FXML
    private ComboBox<AuthorEntity> ComboboxAuthor;
    @FXML
    private ComboBox<PublishingEntity> ComboboxPublishing;

    public List<AuthorEntity> authorEntity = new ArrayList<>();
    public List<PublishingEntity> publishingEntity = new ArrayList<>();

    @FXML
    private TextField title;

    @FXML
    private TextField typeBook;

    @FXML
    private TextField year;

    private Stage editDialogStage;

    private BookEntity book;

    private boolean okClicked = false;

    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }

    public void setLabels(BookEntity book) {
        this.book = book;
        title.setText(book.getTitle());
        typeBook.setText(book.getTypeBook());
        year.setText(String.valueOf(book.getYear()));
        ComboboxAuthor.setItems(authorData);
        ComboboxPublishing.setItems(publishingData);
    }

    @FXML
    void saveBook() throws IOException {
        if (isInputValid()) {
            book.setTitle(title.getText());
            book.setTypeBook(typeBook.getText());
            book.setYear(year.getText());
            book.setAuthor(ComboboxAuthor.getValue());
            book.setPublishing(ComboboxPublishing.getValue());
            book.setId(HTTPUtils.sendBookAndGetData(book).getId());

            okClicked = true;
            editDialogStage.close();
        }
    }

    @FXML
    void cancelBook() {
        editDialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = ValidationBook.getErrorMessageFromBookFields(
                title.getText(),
                typeBook.getText(),
                year.getText(),
                ComboboxAuthor.getValue(),
                ComboboxPublishing.getValue()
        );
        if (errorMessage.length() == 0)
            return true;
        else {
            AlertUtils.showWarning(errorMessage);
            return false;
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
