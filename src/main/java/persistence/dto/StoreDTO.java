package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class StoreDTO {
    private int storeId;
    private String name;
    private String content;
    private String address;
    private String phoneNum;
    private int reviewCnt;
}
