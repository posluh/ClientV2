package ru.posluh.clientserver.clientv2.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublishingEntity {
    private Long id;
    private String name;
    private String city;

    @Override
    public String toString() {
        return name;
    }
    public static PublishingEntity getNullObject() {
        return PublishingEntity.builder()
                .id(null)
                .name("")
                .city("")
                .build();
    }
}
/*
    Данный код представляет собой определение класса PublishingEntity, который содержит поля id, name и city.
    Также в классе определены методы toString() и getNullObject().

        Метод toString() переопределен из класса Object и возвращает строковое представление объекта класса PublishingEntity.
        В данном случае, метод просто возвращает значение поля name.

        Метод getNullObject() возвращает объект класса PublishingEntity, у которого все поля установлены в значения по умолчанию.
        Это может быть полезно для обработки исключений и избежания NullPointerException, когда необходимо работать с объектом,
        который может быть не инициализирован.
        */
