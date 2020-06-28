package club.banyuan.studyroom.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Seat {
    private Integer id;

    private Byte about;

    private Byte repair;
//    //预约时间段
//    private String bookPeriod;

    private Date createdAt;

    private Date updatedAt;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", about=").append(about);
        sb.append(", repair=").append(repair);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}