package ru.posluh.clientserver.clientv2.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponse extends BaseResponse {
    private AuthorEntity author;

    public AuthorResponse(boolean success, String message, AuthorEntity author) {
        super(success, message);
        this.author = author;
    }

    public AuthorResponse(AuthorEntity author) {
        super(true, "Author data:");
    }
}