package ru.posluh.clientserver.clientv2.utils;

import ru.posluh.clientserver.clientv2.entity.PublishingEntity;

public class ValidationPublishing {
    public static boolean validatePublishing(PublishingEntity publishing) {
        StringBuilder errorMessage = new StringBuilder();
        String name = publishing.getName();
        String address = publishing.getCity();
        /*Данный код создает объект StringBuilder с именем errorMessage для хранения сообщений об ошибках,
        которые могут быть обнаружены в процессе валидации данных.*/

        if (name == null || name.isBlank() || !name.matches("[\\sA-ZА-Яa-zа-я0-9]{3,120}")) {
            errorMessage.append("Ошибка в названии издательства! Минимальное количество символов - 3, максимальное - 120\n\n");
        }
        if (address == null || address.isBlank() || !address.matches("[\\sA-ZА-Яa-zа-я0-9]{3,200}")) {
            errorMessage.append("Ошибка в адресе издательства! Минимальное количество символов - 3, максимальное - 200\n\n");
        }
        return errorMessage.length() == 0;
    }

    public static String getErrorMessageFromPublishingFields(String name, String address) {
        StringBuilder errorMessage = new StringBuilder();
        if (name.isBlank() || !name.matches("[\\sA-ZА-Яa-zа-я0-9]{3,120}")) {
            errorMessage.append("Ошибка в названии издательства! Минимальное количество символов - 3, максимальное - 120\n\n");
        }
        if (address.isBlank() || !address.matches("[\\sA-ZА-Яa-zа-я0-9]{2,120}")) {
            errorMessage.append("Ошибка в адресе издательства! Минимальное количество символов - 3, максимальное - 200\n\n");
        }
        return errorMessage.toString();
    }
    /*Метод validatePublishing выполняет валидацию объекта PublishingEntity и возвращает true, если данные проходят проверку,
    и false, если не проходят.

    Метод getErrorMessageFromPublishingFields также выполняет валидацию названия и адреса издательства, но возвращает строку,
    содержащую сообщения об ошибках, обнаруженных в этих полях, вместо простого булевого значения. Этот метод, вероятно,
    используется для получения сообщений об ошибках для вывода на экран в случае, если данные не прошли проверку.*/
}
