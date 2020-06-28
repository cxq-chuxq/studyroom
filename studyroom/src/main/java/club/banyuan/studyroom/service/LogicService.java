package club.banyuan.studyroom.service;

import club.banyuan.studyroom.common.CommonResult;
import club.banyuan.studyroom.model.Order;
import club.banyuan.studyroom.model.Seat;
import club.banyuan.studyroom.model.User;

import javax.servlet.http.HttpSession;

public interface LogicService {
    CommonResult userRegister(User userInfo);

    CommonResult userLogin(User userInfo, HttpSession session);

    CommonResult checkUserLogin(User user);

    CommonResult getSeat();

    CommonResult getSeatById(int seatId);

    CommonResult addSeat(Seat seat);

    CommonResult updateSeat(Seat seat);

    CommonResult deleteSeat(int seatId);

    CommonResult getOrder();

    CommonResult getOrderBySeatId(int seatId);

    CommonResult addOrder(Order order);

//    CommonResult updateOrder(Order order);

    CommonResult deleteOrder(int orderId);

    CommonResult getUser();

    CommonResult addUser(User user);

    CommonResult updateUser(User user);

    CommonResult deleteUser(int userId);
}