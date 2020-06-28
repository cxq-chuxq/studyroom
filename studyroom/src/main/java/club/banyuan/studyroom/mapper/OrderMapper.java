package club.banyuan.studyroom.mapper;

import club.banyuan.studyroom.model.Order;
import club.banyuan.studyroom.model.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    List<Order> listOrder();

    List<Order> queryOrderBySeatId(@Param("seatId") int seatId);

    void addOrder(@Param("order") Order order);

    void updateOrder(@Param("order") Order order);

    Order queryOrderById(@Param("orderId") int orderId);

    void deleteOrder(@Param("orderId") int orderId);

    Order queryReservedOrder(@Param("order")Order order);
}