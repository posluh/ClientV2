package ru.posluh.clientserver.clientv2.entity;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BookEntity {
    private Long id;
    private String title;
    private AuthorEntity author;
    private PublishingEntity publishing;
    private String typeBook;
    private String year;

    @Override
    public String toString() {
        return "author=" + author +
                ", publishing=" + publishing +
                ", title='" + title + '\'' +
                ", typeBook='" + typeBook + '\'' +
                ", year='" + year + '\'' +
                ", id='" + id;
    }
    public static BookEntity getNullObject() {
        return BookEntity.builder()
                .id(null)
                .typeBook("")
                .author(AuthorEntity.getNullObject())
                .publishing(PublishingEntity.getNullObject())
                .year("")
                .title("")
                .build();
    }

    /*Этот метод getNullObject() создает новый объект класса BookEntity с определенными свойствами. Он используется
    для создания "пустого" объекта, который может использоваться в случае, если объект еще не был заполнен нужными данными.
    В данном случае, свойство id равно null, свойство typeBook равно пустой строке "", а свойства author, publishing, year,
    и title устанавливаются с помощью методов getNullObject() классов AuthorEntity и PublishingEntity и соответственно равны
    "пустым" объектам этих классов. Кроме того, для создания объекта используется паттерн проектирования "Builder" с использованием
    метода builder().*/

}