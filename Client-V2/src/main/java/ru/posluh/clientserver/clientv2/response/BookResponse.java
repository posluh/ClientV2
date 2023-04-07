package ru.posluh.clientserver.clientv2.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.posluh.clientserver.clientv2.entity.BookEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookResponse extends BaseResponse {
    private BookEntity data;

    public BookResponse(boolean success, String message, BookEntity data) {
        super(success, message);
        this.data = data;
    }
}
