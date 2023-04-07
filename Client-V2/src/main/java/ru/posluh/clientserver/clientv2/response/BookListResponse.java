package ru.posluh.clientserver.clientv2.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.posluh.clientserver.clientv2.entity.BookEntity;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BookListResponse extends BaseResponse {
    public List<BookEntity> data;

    public BookListResponse(List<BookEntity> data) {
        super(true, "Список книг");
        this.data = data;
    }
}