package ru.posluh.clientserver.clientv2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.posluh.clientserver.clientv2.controller.ApplicationController;
import ru.posluh.clientserver.clientv2.controller.DialgBookController;
import ru.posluh.clientserver.clientv2.controller.DialogAuthorController;
import ru.posluh.clientserver.clientv2.controller.DialogPublishingController;
import ru.posluh.clientserver.clientv2.entity.BookEntity;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/ApplicationView.fxml"));
        AnchorPane rootLayout = fxmlLoader.load();

        Scene scene = new Scene(rootLayout);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/ico.png")));
        stage.setTitle("Библиотека");
        stage.setScene(scene);
        stage.setResizable(false);

        ApplicationController controller = fxmlLoader.getController();
        stage.show();
    }

    /*Этот метод запускает главное окно приложения. Он создает экземпляр класса FXMLLoader, который загружает FXML-файл, содержащий интерфейс приложения, и создает соответствующий объект визуального интерфейса. Затем он создает новую сцену на основе этого объекта и добавляет иконку приложения и название. Затем он создает экземпляр ApplicationController и отображает сцену в новом окне.*/

    public static void showDialogBook(BookEntity book) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/DialgBook.fxml"));
            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("Информация о книге");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            DialgBookController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setLabels(book);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Этот метод отвечает за отображение диалогового окна с информацией о книге.

    Внутри метода происходят следующие действия:

    Создается объект FXMLLoader, который используется для загрузки разметки FXML для диалогового окна.

    Устанавливается местоположение файла FXML с помощью метода setLocation().

    Загружается разметка FXML с помощью метода load().

    Создается новое окно с помощью конструктора Stage().

    Устанавливается заголовок для окна с помощью метода setTitle().

    Устанавливается модальность окна с помощью метода initModality().

    Создается новый объект сцены (Scene) и устанавливается в окне с помощью метода setScene().

    Получается ссылка на контроллер (DialgBookController) для диалогового окна с помощью метода getController().

    Устанавливаются необходимые параметры для контроллера, в том числе ссылка на окно диалога и информация о книге.

    Отображается диалоговое окно с помощью метода showAndWait().

    После закрытия диалогового окна пользователем, выполнение метода showAndWait() прекращается, и выполнение программы продолжается
    сразу после вызова этого метода.*/

    public static void showDialogAuthor(AuthorEntity author) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/DialogAuthor.fxml"));
            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("Информация об авторе");
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            /*Получение доступа к контроллеру используется для связывания визуальных элементов на FXML с логикой приложения,
            реализованной в Java.

            В данном случае, после загрузки FXML файла, мы получаем доступ к контроллеру, который содержит методы и свойства,
            которые мы можем использовать для обработки пользовательского ввода и изменения визуальных элементов на экране.*/

            DialogAuthorController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setLabels(author);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**/

    public static void showPublishing(PublishingEntity publishing) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/DialogPublishing.fxml"));
            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setTitle("Информация об издательстве");
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            DialogPublishingController controller = loader.getController();
            controller.setEditDialogStage(dialogStage);
            controller.setLabels(publishing);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**/

    public static void main(String[] args) {
        launch();
    }
}