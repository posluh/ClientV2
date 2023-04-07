package ru.posluh.clientserver.clientv2.response;

import lombok.Data;
import ru.posluh.clientserver.clientv2.entity.PublishingEntity;

import java.util.List;

@Data
public class PublishingListResponse extends BaseResponse {
    public PublishingListResponse(List<PublishingEntity> data) {
        super(true, "Издательства: ");
        this.data = data;
    }

    private List<PublishingEntity> data;
}