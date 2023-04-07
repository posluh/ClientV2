package ru.posluh.clientserver.clientv2.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorEntity {
    private Long id;
    private String name;
    private String lastname;
    private String surname;

    @Override
    public String toString() {
        return surname + " " + name.charAt(0) + ". " + lastname.charAt(0) + ".";
    }

    public static AuthorEntity getNullObject() {
        return AuthorEntity.builder()
                .id(null)
                .name("")
                .surname("")
                .lastname("")
                .build();
    }

    /*Код, который вы привели, относится к классу AuthorEntity. В данном классе определен метод getNullObject(),
    который создает объект класса AuthorEntity с пустыми полями, т.е. используется для создания объекта, который может
    рассматриваться как отсутствующий объект или объект с нулевыми значениями полей. Данный метод создает объект используя
    билдер-шаблон, заполняя все поля объекта пустыми значениями.*/

}