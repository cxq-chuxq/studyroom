package club.banyuan.studyroom.service.impl;

import club.banyuan.studyroom.common.CommonResult;
import club.banyuan.studyroom.mapper.OrderMapper;
import club.banyuan.studyroom.mapper.SeatMapper;
import club.banyuan.studyroom.mapper.UserMapper;
import club.banyuan.studyroom.model.Order;
import club.banyuan.studyroom.model.Seat;
import club.banyuan.studyroom.model.User;
import club.banyuan.studyroom.service.LogicService;
import club.banyuan.studyroom.service.impl.event.SeatOfOrder;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONObject;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class LogicServiceImpl implements LogicService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public CommonResult userRegister(User userInfo) {
        //查询该用户名是否已经注册
        User user = userMapper.queryUserByUserName(userInfo.getUsername());
        if (user != null) {
            return CommonResult.failed("注册失败,用户名已经存在!");
        }
        // 密码加密
        String passwordHash = BCrypt.hashpw(userInfo.getPassword());
        userInfo.setPassword(passwordHash);
        try {
            userMapper.addUser(userInfo);
        } catch (Exception e) {
            return CommonResult.failed("注册失败!异常");
        }
        return CommonResult.success("注册成功");
    }

    @Override
    public CommonResult userLogin(User userInfo, HttpSession session) {
        User user = userMapper.queryUserByUserName(userInfo.getUsername());
        if (user == null) {
            return CommonResult.failed("登录失败,用户不存在！");
        }
        // 判断密码对不对
        try {
            if (!BCrypt.checkpw(userInfo.getPassword(), user.getPassword())) {
                return CommonResult.failed("登录失败,密码错误！[99901]");
            }
        } catch (Exception e) {
            return CommonResult.failed("登录失败,密码错误！[99902]");
        }

        session.setAttribute("userInfo", user);
        return CommonResult.success("登录成功");
    }

    @Override
    public CommonResult checkUserLogin(User user) {
        User userInfo = userMapper.queryUserById(user.getId());
        if (Objects.equals(userInfo, user)) {
            return CommonResult.success(user, "登录");
        } else {
            return CommonResult.failed("未登录");
        }
    }

    @Override
    public CommonResult getSeat() {
        List<Seat> seats = seatMapper.listSeat();
        return CommonResult.success(seats, "ok");
    }

    @Override
    public CommonResult getSeatById(int seatId) {
        JSONObject json = new JSONObject();
        Seat seat = seatMapper.querySeatById(seatId);
        //根据座位号去查预订信息
        List<Order> orderList = orderMapper.queryOrderBySeatId(seatId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<SeatOfOrder> seatOfOrderList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 48; i++) {
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            SeatOfOrder seatOfOrder = new SeatOfOrder();
            seatOfOrder.setOrderDate(currentDate);
            seatOfOrder.setOrderHour(currentHour);
            for (Order order : orderList) {
                String orderDate = order.getOrderDate();
                if (Objects.equals(orderDate, currentDate) && currentHour == order.getOrderHour().intValue() && order.getState() == 1) {
                    seatOfOrder.setOrderStatus(true);
                }
            }
            seatOfOrderList.add(seatOfOrder);
        }
        json.putIfAbsent("seat", seat);
        json.putIfAbsent("seatOfOrderList", seatOfOrderList);
        return CommonResult.success(json, "ok");
    }

    @Override
    public CommonResult addSeat(Seat seat) {
        try {
            seatMapper.addSeat(seat);
        } catch (Exception e) {
            return CommonResult.failed("addSeat failed");
        }
        return CommonResult.success("addSeat ok");
    }

    @Override
    public CommonResult updateSeat(Seat seat) {
        try {
            seatMapper.updateSeat(seat);
        } catch (Exception e) {
            return CommonResult.failed("updateSeat failed");
        }
        return CommonResult.success("updateSeat ok");
    }

    @Override
    public CommonResult deleteSeat(int seatId) {
        Seat seat = seatMapper.querySeatById(seatId);
        if (seat == null) {
            return CommonResult.failed("座位不存在");
        }
        seatMapper.deleteSeat(seatId);
        return CommonResult.success("deleteSeat ok");
    }

    @Override
    public CommonResult getOrder() {
        List<Order> orders = orderMapper.listOrder();
        return CommonResult.success(orders, "ok");
    }

    @Override
    public CommonResult getOrderBySeatId(int seatId) {
        List<Order> order = orderMapper.queryOrderBySeatId(seatId);
        return CommonResult.success(order, "ok");
    }

    @Override
    public CommonResult addOrder(Order order) {
        Order reservedOrder = orderMapper.queryReservedOrder(order);
        if (reservedOrder != null) {
            return CommonResult.failed("addOrder failed,已经预约");
        }
        try {
            orderMapper.addOrder(order);
        } catch (Exception e) {
            return CommonResult.failed("addOrder failed");
        }
        return CommonResult.success("addOrder ok");
    }

//    @Override
//    public CommonResult updateOrder(Order order) {
//        try {
//            orderMapper.updateOrder(order);
//        } catch (Exception e) {
//            return CommonResult.failed("updateOrder failed");
//        }
//        return CommonResult.success("updateOrder ok");
//    }

    @Override
    public CommonResult deleteOrder(int orderId) {
        Order order = orderMapper.queryOrderById(orderId);
        if (order == null) {
            return CommonResult.failed("预约不存在");
        }
        Date t = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(t);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String orderDate = order.getOrderDate();
        int orderHour = order.getOrderHour();
        boolean flag1 = orderHour - hour > 6 && Objects.equals(orderDate, date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String addOneDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        boolean flag2 = orderHour + 24 - hour > 6 && Objects.equals(orderDate, addOneDate);
        if (!flag1 && !flag2) {
            return CommonResult.failed("距离预约时间不足六小时，不能取消预约");
        }
        orderMapper.deleteOrder(orderId);
        return CommonResult.success("deleteOrder ok");
    }

    @Override
    public CommonResult getUser() {
        List<User> users = userMapper.listUser();
        return CommonResult.success(users, "ok");
    }

    @Override
    public CommonResult addUser(User userInfo) {
        //查询该用户名是否已经注册
        User user = userMapper.queryUserByUserName(userInfo.getUsername());
        if (user != null) {
            return CommonResult.failed("addUser failed,用户名已经存在!");
        }
        // 密码加密
        String passwordHash = BCrypt.hashpw(userInfo.getPassword());
        userInfo.setPassword(passwordHash);
        try {
            userMapper.addUser(userInfo);
        } catch (Exception e) {
            return CommonResult.failed("addUser failed!异常");
        }
        return CommonResult.success("addUser ok");
    }

    @Override
    public CommonResult updateUser(User user) {
        // 密码加密
        String passwordHash = BCrypt.hashpw(user.getPassword());
        user.setPassword(passwordHash);
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            return CommonResult.failed("updateUser failed");
        }
        return CommonResult.success("updateUser ok");
    }

    @Override
    public CommonResult deleteUser(int userId) {
        User user = userMapper.queryUserById(userId);
        if (user == null) {
            return CommonResult.failed("用户不存在");
        }
        userMapper.deleteUser(userId);
        return CommonResult.success("deleteUser ok");
    }
}
