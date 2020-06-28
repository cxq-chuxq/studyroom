package club.banyuan.studyroom.mapper;

import club.banyuan.studyroom.model.Order;
import club.banyuan.studyroom.model.Seat;
import club.banyuan.studyroom.model.SeatExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SeatMapper {
    List<Seat> listSeat();

    Seat querySeatById(@Param("seatId") int seatId);

    void addSeat(@Param("seat") Seat seat);

    void updateSeat(@Param("seat") Seat seat);

    void deleteSeat(@Param("seatId")int seatId);
}