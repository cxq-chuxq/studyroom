package club.banyuan.studyroom.controller;

import club.banyuan.studyroom.common.CommonResult;
import club.banyuan.studyroom.model.Order;
import club.banyuan.studyroom.model.Seat;
import club.banyuan.studyroom.model.User;
import club.banyuan.studyroom.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/logic")
public class LogicController {
    @Autowired
    LogicService logicService;

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@RequestBody User userInfo) {
        // 用户名和密码不能为空
        if (StringUtils.isEmpty(userInfo.getUsername()) || StringUtils.isEmpty(userInfo.getPassword())) {
            return CommonResult.failed("注册失败,没有输入!");
        }
        return logicService.userRegister(userInfo);
    }

    /**
     * 用户登录
     *
     * @param request
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(HttpServletRequest request, @RequestBody User userInfo) {
        // 用户名和密码不能为空
        if (StringUtils.isEmpty(userInfo.getUsername()) || StringUtils.isEmpty(userInfo.getPassword())) {
            return CommonResult.failed("登录失败,没有输入!");
        }
        HttpSession session = request.getSession();
        return logicService.userLogin(userInfo, session);
    }

    /**
     * 检查登录状态
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public CommonResult checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        return logicService.checkUserLogin(user);
    }

    @GetMapping("/logout")
    public CommonResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommonResult.success("OK");
    }

    @RequestMapping(value = "/seat", method = RequestMethod.GET)
    public CommonResult getSeat() {
        return logicService.getSeat();
    }

    @RequestMapping(value = "/seat/{seatId}", method = RequestMethod.GET)
    public CommonResult getSeatById(@PathVariable("seatId") @Pattern(regexp = "\\d{1,4}") String seatId) {
        int id = Integer.parseInt(seatId);
        return logicService.getSeatById(id);
    }

    @RequestMapping(value = "/seat", method = RequestMethod.POST)
    public CommonResult addSeat(@RequestBody Seat seat) {
        return logicService.addSeat(seat);
    }

    @RequestMapping(value = "/seat", method = RequestMethod.PUT)
    public CommonResult updateSeat(@RequestBody Seat seat) {
        return logicService.updateSeat(seat);
    }

    @RequestMapping(value = "/seat/{seatId}", method = RequestMethod.DELETE)
    public CommonResult deleteSeat(@PathVariable("seatId") @Pattern(regexp = "\\d{1,10}") String seatId) {
        int id = Integer.parseInt(seatId);
        return logicService.deleteSeat(id);
    }

    @RequestMapping(value = "/order/{seatId}", method = RequestMethod.GET)
    public CommonResult getOrderBySeatId(@PathVariable("seatId") @Pattern(regexp = "\\d{1,10}") String seatId) {
        int id = Integer.parseInt(seatId);
        return logicService.getOrderBySeatId(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public CommonResult getOrder() {
        return logicService.getOrder();
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public CommonResult addOrder(@RequestBody Order order) {
        return logicService.addOrder(order);
    }

//    @RequestMapping(value = "/order", method = RequestMethod.PUT)
//    public CommonResult updateOrder(@RequestBody Order order) {
//        return logicService.updateOrder(order);
//    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public CommonResult deleteOrder(@PathVariable("orderId") @Pattern(regexp = "\\d{1,10}") String orderId) {
        int id = Integer.parseInt(orderId);
        return logicService.deleteOrder(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public CommonResult getUser() {
        return logicService.getUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public CommonResult addUser(@RequestBody User user) {
        return logicService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public CommonResult updateUser(@RequestBody User user) {
        return logicService.updateUser(user);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public CommonResult deleteUser(@PathVariable("userId") @Pattern(regexp = "\\d{1,10}") String userId) {
        int id = Integer.parseInt(userId);
        return logicService.deleteUser(id);
    }
}
