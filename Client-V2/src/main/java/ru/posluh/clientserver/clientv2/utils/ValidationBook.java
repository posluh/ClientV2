package ru.posluh.clientserver.clientv2.utils;


import ru.posluh.clientserver.clientv2.entity.AuthorEntity;
import ru.posluh.clientserver.clientv2.entity.BookEntity;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;

public class ValidationBook {

    //Возвращает true или false в зависимость от ошибки
    public static boolean validateBook(BookEntity book) {
        StringBuilder errorMessage = new StringBuilder();
        String title = book.getTitle();
        String typeBook = book.getTypeBook();
        String year = String.valueOf(book.getYear());
        AuthorEntity author = book.getAuthor();
        PublishingEntity publishing = book.getPublishing();
                /*Данный код создает объект StringBuilder с именем errorMessage для хранения сообщений об ошибках,
        которые могут быть обнаружены в процессе валидации данных.*/

        if (title.isBlank() || !title.matches("[\\sA-ZА-Яa-zа-я0-9]{2,120}")) {
            errorMessage.append("Ошбика в названии книги! Минимальное количество символов - 2, максимальное - 120\n\n");
        }
        if (author == null) {
            errorMessage.append("Не выбран автор из списка! Пожалуйста, выберите автора\n\n");
        }
        if (publishing == null) {
            errorMessage.append("Не выбрано издательство из списка! Пожалуйста, выберите издательство\n\n");
        }
        if (typeBook.isBlank() || !typeBook.matches("[\\sA-ZА-Яa-zа-я0-9]{1,40}")) {
            errorMessage.append("Ошибка в жанре книги! Минимальное количество символов - 1, максимальное - 40\n\n");
        }
        if (year.isBlank() || !year.matches("[1-2][0-9][0-9][0-9]")) {
            errorMessage.append("Неправильно введена дата издания книги! Минимум 1000 год.\n");
        } else {
            try {
                Integer.parseInt(year);
            } catch (NumberFormatException e) {
                errorMessage.append("Некорректный ввод значения года издания книги (вводите только цыферки)\n");
            }
        }
        return errorMessage.length() == 0;
    }

    // Возвращает сообщение текстом об ошибке
    public static String getErrorMessageFromBookFields(String title, String typeBook, String year, AuthorEntity author, PublishingEntity publishing) {

        StringBuilder errorMessage = new StringBuilder();
        if (title.isBlank() || !title.matches("[\\sA-ZА-Яa-zа-я0-9]{2,120}")) {
            errorMessage.append("Ошбика в названии книги! Минимальное количество символов - 2, максимальное - 120\n\n");
        }
        if (author == null) {
            errorMessage.append("Не выбран автор из списка! Пожалуйста, выберите автора\n\n");
        }
        if (publishing == null) {
            errorMessage.append("Не выбрано издательство из списка! Пожалуйста, выберите издательство\n\n");
        }
        if (typeBook.isBlank() || !typeBook.matches("[\\sA-ZА-Яa-zа-я0-9]{1,40}")) {
            errorMessage.append("Ошибка в жанре книги! Минимальное количество символов - 1, максимальное - 40\n\n");
        }
        if (year.isBlank() || !year.matches("[1-2][0-9][0-9][0-9]")) {
            errorMessage.append("Неправильно введена дата издания книги! Минимум 1000 год.\n");
        } else {
            try {
                Integer.parseInt(year);
            } catch (NumberFormatException e) {
                errorMessage.append("Неправильно введена дата издания книги! Минимум 1000 год.\n");
            }
        }
        return errorMessage.toString();
    }
}
