package ru.posluh.clientserver.clientv2.controller;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import ru.posluh.clientserver.clientv2.Application;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;
import ru.posluh.clientserver.clientv2.entity.BookEntity;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;
import ru.posluh.clientserver.clientv2.utils.*;

import java.io.IOException;

public class ApplicationController {

    public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
    public static ObservableList<AuthorEntity> authorData = FXCollections.observableArrayList();
    public static ObservableList<PublishingEntity> publishingData = FXCollections.observableArrayList();

    /*FXCollections.observableArrayList() создает пустой список, который может быть использован для хранения объектов в
    JavaFX приложении. Этот список также имеет возможность уведомлять своих наблюдателей (observers) об изменениях в списке,
    что делает его особенно удобным для использования в сочетании с JavaFX элементами управления, такими как TableView или ListView.*/

    public final static String apiBook = "http://localhost:3234/api/v1/book/";
    public final static String apiAuthor = "http://localhost:3234/api/v1/author/";
    public final static String apiPublishing = "http://localhost:3234/api/v1/publishing/";

    AlertUtils alerts = new AlertUtils();
    static Gson gson = new Gson();

    @Setter
    private Stage stage;

    @FXML
    private void initialize() {
        try {
            getDataBook();
            updateTableBook();

            getDataAuthor();
            updateTableAuthor();

            getDataPublishing();
            updateTablePublishing();
        } catch (IOException e) {
            alerts.serverNotRun(stage);
            System.exit(0);
        }
    }

    // Книги

    @FXML
    private void buttonAddBook() throws IOException {
        BookEntity book = BookEntity.getNullObject();
        Application.showDialogBook(book);
        if (ValidationBook.validateBook(book)) {
            addBook(book);
        }
    }

    /*Этот код отвечает за обработку нажатия на кнопку "Добавить книгу" на главном экране приложения. При нажатии на
    эту кнопку открывается диалоговое окно для добавления новой книги. Сначала создается новый экземпляр класса BookEntity
   с помощью метода getNullObject(), затем открывается диалоговое окно с этим объектом. Если пользователь заполнил все поля
   книги корректно и нажал кнопку "ОК" в диалоговом окне, то вызывается метод addBook(), который отправляет запрос на сервер
   с данными новой книги и добавляет ее в список booksData. Если же данные введены некорректно, то книга не добавляется в список.*/

    @FXML
    private void buttonDeleteBook() throws IOException {
        BookEntity selectedBook = tableBook.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            HTTPUtils.deleteBook(selectedBook);
            booksData.remove(selectedBook);
        } else {
            alerts.showNotSelected();
        }
    }

    /*Этот метод обработчика нажатия кнопки удаления книги из таблицы. Сначала он получает выделенную строку в
    таблице с помощью метода getSelectedItem() класса TableView, затем проверяет, что строка не равна null. Если
    строка не равна null, он отправляет запрос на удаление книги с помощью метода HTTPUtils.deleteBook(), передавая ему
    выделенную книгу в качестве аргумента, и удаляет ее из ObservableList с помощью метода remove() класса ObservableList.
    Если строка равна null, он вызывает метод alerts.showNotSelected(), который выводит сообщение об ошибке, что строка не выбрана.*/


    @FXML
    private void buttonEditBook() throws IOException {
        BookEntity selectedBook = tableBook.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Application.showDialogBook(selectedBook);
            updateBook(selectedBook);
        } else {
            alerts.showNotSelected();
        }
    }

    /*Данный код обрабатывает нажатие на кнопку "Изменить" в окне со списком книг. Он получает выбранную пользователем книгу
    из таблицы, передает ее в диалоговое окно для редактирования и затем обновляет ее данные в списке книг, вызывая метод
    updateBook(selectedBook). Если ни одна книга не выбрана, появляется предупреждение для пользователя.*/

    public static void getDataBook() throws IOException {
        String response = HTTPUtils.getBooks();
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = base.getAsJsonArray("data");
        for (JsonElement element : jsonArray) {
            BookEntity newBook = gson.fromJson(element.toString(), BookEntity.class);
            booksData.add(newBook);
        }
    }

    /*Данный метод получает данные о книгах из внешнего источника с помощью HTTP-запроса, преобразует полученный
    JSON-ответ в объекты типа BookEntity с помощью библиотеки Gson, и добавляет полученные объекты в список booksData, который
    используется для отображения данных в таблице.*/

    private void updateTableBook() {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        AuthorInBook.setCellValueFactory(new PropertyValueFactory<>("author"));
        PublishingInBook.setCellValueFactory(new PropertyValueFactory<>("publishing"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        typeBook.setCellValueFactory(new PropertyValueFactory<>("typeBook"));
        tableBook.setItems(booksData);
    }

    /*Метод updateTableBook() обновляет таблицу книг в пользовательском интерфейсе. Он устанавливает, какие данные
    будут отображаться в каждом столбце таблицы, используя фабрику свойств PropertyValueFactory и метод setCellValueFactory().
    Затем он устанавливает источник данных для таблицы, используя метод setItems(), который устанавливает список книг booksData в
    качестве источника данных для таблицы.*/

    public void addBook(BookEntity book) throws IOException {
        Long id = HTTPUtils.sendBookAndGetData(book).getId();
        book.setId(id);
        booksData.add(book);
        System.out.println(book);
    }

    /*Этот метод добавляет книгу в таблицу книг booksData. Для этого метод отправляет запрос на сервер, чтобы добавить книгу
    и получить её идентификатор (ID), затем присваивает полученный ID книге и добавляет книгу в booksData. Наконец, метод
    выводит информацию о добавленной книге в консоль с помощью метода System.out.println().*/

    public static void updateBook(BookEntity book) throws IOException {
        HTTPUtils.updateBook(book);
        int bookIndex = booksData.indexOf(book);
        booksData.set(bookIndex, book);
    }

    /*Данный метод обновляет информацию о книге, которая хранится в локальном списке booksData, отправляет запрос на сервер
    для обновления информации о книге в базе данных, и затем обновляет информацию о книге в booksData с помощью метода set().
    На вход метод получает объект BookEntity, который содержит обновленную информацию о книге.*/

    // Авторы

    public void buttonAddAuthor() throws IOException {
        AuthorEntity author = AuthorEntity.getNullObject();
        Application.showDialogAuthor(author);
        if (ValidationAuthor.validateAuthor(author)) {
            addAuthor(author);
        }
    }

    public void buttonEditAuthor() throws IOException {
        AuthorEntity author = tableAuthor.getSelectionModel().getSelectedItem();
        if (author != null) {
            Application.showDialogAuthor(author);
            updateAuthor(author);
        } else {
            alerts.showNotSelected();
        }
    }

    public void buttonDeleteAuthor() throws IOException {
        AuthorEntity author = tableAuthor.getSelectionModel().getSelectedItem();
        if (author != null) {
            HTTPUtils.deleteAuthor(author);
            authorData.remove(author);
        } else {
            alerts.showNotSelected();
        }
    }
    public static void getDataAuthor() throws IOException {
        String response = HTTPUtils.getAuthor();
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = base.getAsJsonArray("data");
        for (JsonElement element : jsonArray) {
            AuthorEntity newAuthor = gson.fromJson(element.toString(), AuthorEntity.class);
            authorData.add(newAuthor);
        }
    }

    private void updateTableAuthor() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tableAuthor.setItems(authorData);
    }


    public static void addAuthor(AuthorEntity author) throws IOException {
        Long id = HTTPUtils.sendAuthorAndGetData(author).getId();
        author.setId(id);
        authorData.add(author);
        System.out.println(author);
    }

    public static void updateAuthor(AuthorEntity author) throws IOException {
        HTTPUtils.updateAuthor(author);
        int authorIndex = authorData.indexOf(author);
        authorData.set(authorIndex, author);
    }

// Издательства
    public void buttonAddPublishing() throws IOException {
        PublishingEntity publishing = PublishingEntity.getNullObject();
        Application.showPublishing(publishing);
        if (ValidationPublishing.validatePublishing(publishing)) {
            addPublishing(publishing);
        }
    }

    public void buttonEditPublishing() throws IOException {
        PublishingEntity publishing = tablePublishing.getSelectionModel().getSelectedItem();
        if (publishing != null) {
            Application.showPublishing(publishing);
            updatePublishing(publishing);
        } else {
            alerts.showNotSelected();
        }
    }

    public void buttonDeletePublishing() throws IOException {
        PublishingEntity publishing = tablePublishing.getSelectionModel().getSelectedItem();
        if (publishing != null) {
            HTTPUtils.deletePublishing(publishing);
            publishingData.remove(publishing);
        } else {
            alerts.showNotSelected();
        }
    }
    public static void getDataPublishing() throws IOException {
        String response = HTTPUtils.getPublishing();
        JsonObject base = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = base.getAsJsonArray("data");
        for (JsonElement element : jsonArray) {
            PublishingEntity publishing = gson.fromJson(element.toString(), PublishingEntity.class);
            publishingData.add(publishing);
        }
    }

    private void updateTablePublishing() {
        namePublishing.setCellValueFactory(new PropertyValueFactory<>("name"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        tablePublishing.setItems(publishingData);
    }

    public static void addPublishing(PublishingEntity publishing) throws IOException {
        Long id = HTTPUtils.sendPublishingAndGetData(publishing).getId();
        publishing.setId(id);
        publishingData.add(publishing);
        System.out.println(publishing);
    }

    public static void updatePublishing(PublishingEntity publishing) throws IOException {
        HTTPUtils.updatePublishing(publishing);
        int publishingIndex = publishingData.indexOf(publishing);
        publishingData.set(publishingIndex, publishing);
    }


    @FXML
    private TableView<BookEntity> tableBook;
    @FXML
    private TableColumn<BookEntity, Long> bookId;
    @FXML
    private TableColumn<BookEntity, String> title;
    @FXML
    private TableColumn<BookEntity, String> AuthorInBook;
    @FXML
    private TableColumn<BookEntity, String> PublishingInBook;
    @FXML
    private TableColumn<BookEntity, String> year;
    @FXML
    private TableColumn<BookEntity, String> typeBook;

    @FXML
    private TableView<AuthorEntity> tableAuthor;
    @FXML
    private TableColumn<AuthorEntity, Long> idAuthor;
    @FXML
    private TableColumn<AuthorEntity, String> name;
    @FXML
    private TableColumn<AuthorEntity, String> surname;
    @FXML
    private TableColumn<AuthorEntity, String> lastname;

    @FXML
    private TableView<PublishingEntity> tablePublishing;
    @FXML
    private TableColumn<PublishingEntity, Long> idPublishing;
    @FXML
    private TableColumn<PublishingEntity, String> namePublishing;
    @FXML
    private TableColumn<PublishingEntity, String> city;
}