package ru.posluh.clientserver.clientv2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

/*Аннотация @RequiredArgsConstructor - это Lombok аннотация, которая генерирует конструктор класса,
инициализирующий все поля, помеченные как final, а также поля, которые объявлены @NonNull.

Когда аннотация @RequiredArgsConstructor применяется к классу, Lombok генерирует конструктор,
принимающий все поля, помеченные как final или @NonNull, и использует их для инициализации этих полей.
Это уменьшает количество бойлерплейт-кода, который требуется для написания конструктора с аргументами.*/

@AllArgsConstructor
public class BaseResponse {
    public boolean success;
    public String message;
}