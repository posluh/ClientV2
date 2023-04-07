package ru.posluh.clientserver.clientv2.utils;


import ru.posluh.clientserver.clientv2.entity.AuthorEntity;

public class ValidationAuthor {

    //Отправляет true или false в зависимости от ошибки
    public static boolean validateAuthor(AuthorEntity author) {
        StringBuilder errorMessage = new StringBuilder();
        String name = author.getName();
        String lastname = author.getLastname();
        String surname = author.getSurname();
                /*Данный код создает объект StringBuilder с именем errorMessage для хранения сообщений об ошибках,
        которые могут быть обнаружены в процессе валидации данных.*/

        if (name.isBlank() || !name.matches("[\\sA-ZА-Яa-zа-я0-9]{2,40}")) {
            errorMessage.append("Ошибка в имени автора! Минимальное количество символов - 2, максимальное - 40\n\n");
        }
        if (lastname.isBlank() || !lastname.matches("[\\sA-ZА-Яa-zа-я0-9]{2,60}")) {
            errorMessage.append("Ошибка в фамилии автора! Минимальное количество символов - 2, максимальное - 60\n\n");
        }
        if (surname.isBlank() || !surname.matches("[\\sA-ZА-Яa-zа-я0-9]{2,50}")) {
            errorMessage.append("Ошбика отвечстве автора! Минимальное количество символов - 2, максимальное - 50\n\n");
        }
        return errorMessage.length() == 0;
    }

    //Возвращает сообщение об ошибке
    public static String getErrorMessageFromBookFields(String name, String lastname, String surname) {
        StringBuilder errorMessage = new StringBuilder();
        if (name.isBlank() || !name.matches("[\\sA-ZА-Яa-zа-я0-9]{2,50}")) {
            errorMessage.append("Ошибка в имени автора! Минимальное количество символов - 2, максимальное - 40\n\n");
        }
        if (lastname.isBlank() || !lastname.matches("[\\sA-ZА-Яa-zа-я0-9]{2,50}")) {
            errorMessage.append("Ошибка в фамилии автора! Минимальное количество символов - 2, максимальное - 60\n\n");
        }
        if (surname.isBlank() || !surname.matches("[\\sA-ZА-Яa-zа-я0-9]{2,50}")) {
            errorMessage.append("Ошбика отвечстве автора! Минимальное количество символов - 2, максимальное - 50\n\n");
        }
        return errorMessage.toString();
    }
}