package ru.posluh.clientserver.clientv2.response;

import lombok.Data;
import ru.posluh.clientserver.clientv2.entity.AuthorEntity;

import java.util.List;

@Data
public class AuthorListResponse extends BaseResponse {
    //поменять местми лист ии то что ниже тест моенять на попроще
    private List<AuthorEntity> data;

    public AuthorListResponse(List<AuthorEntity> data) {
        super(true, "Список Авторов");
        this.data = data;
    }
}