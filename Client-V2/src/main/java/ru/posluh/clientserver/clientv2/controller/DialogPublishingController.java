package ru.posluh.clientserver.clientv2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;
import ru.posluh.clientserver.clientv2.utils.AlertUtils;
import ru.posluh.clientserver.clientv2.utils.HTTPUtils;
import ru.posluh.clientserver.clientv2.utils.ValidationPublishing;

import java.io.IOException;

public class DialogPublishingController {

    private Stage editDialogStage;
    private PublishingEntity publishing;
    private boolean okClicked = false;

    public void setEditDialogStage(Stage editDialogStage) {
        this.editDialogStage = editDialogStage;
    }

    /*Этот метод устанавливает ссылку на окно диалога редактирования (Stage), которое используется в других методах
    для закрытия окна или изменения его свойств.*/

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setLabels(PublishingEntity publishing) {
        this.publishing = publishing;
        namePublishing.setText(publishing.getName());
        city.setText(publishing.getCity());
    }

    /*Данный метод устанавливает значения для двух текстовых меток (labels) на пользовательском интерфейсе.
    Переданные значения для меток берутся из объекта типа PublishingEntity. Метод также сохраняет ссылку на этот объект, чтобы
    позже использовать его при сохранении изменений, которые может внести пользователь.*/

    private boolean isInputValid() {
        String errorMessage = ValidationPublishing.getErrorMessageFromPublishingFields(
                namePublishing.getText(),
                city.getText()
        );
        if (errorMessage.length() == 0)
            return true;
        else {
            AlertUtils.showWarning(errorMessage);
            return false;
        }
    }

    @FXML
    void savePublishing() throws IOException {
        if (isInputValid()) {
            publishing.setName(namePublishing.getText());
            publishing.setCity(city.getText());
            publishing.setId(HTTPUtils.sendPublishingAndGetData(publishing).getId());
            okClicked = true;
            editDialogStage.close();
        }
    }

    /*Данный код отвечает за сохранение данных из формы редактирования/создания издательства в базу данных.

    Метод isInputValid() проверяет валидность введенных данных в форму и возвращает true, если все поля заполнены
    корректно, иначе выводит сообщение об ошибке через AlertUtils.showWarning() и возвращает false.

    Метод savePublishing() вызывается при нажатии на кнопку сохранения в форме редактирования/создания издательства.
    Если данные введены корректно, то они записываются в объект publishing, отправляются на сервер через
    HTTPUtils.sendPublishingAndGetData(), полученный ответ обновляет объект publishing, который затем сохраняется в базу данных.
    Если операция прошла успешно, устанавливается флаг okClicked в true и закрывается окно редактирования/создания издательства.
    Если данные не прошли валидацию, то ничего не происходит и пользователю выводится сообщение об ошибке.*/

    @FXML
    void cancelPublishing() {
        editDialogStage.close();
    }

    @FXML
    private TextField city;
    @FXML
    private TextField namePublishing;
    @FXML
    private Button buttonSavePublishing;
    @FXML
    private Button buttonCancelPublishing;
}
