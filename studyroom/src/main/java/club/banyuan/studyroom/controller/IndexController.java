package club.banyuan.studyroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    @GetMapping("/studyroom/login")
    public String studyRoomLogin() {
        return "studyroom/login";
    }

    @GetMapping("/studyroom/register")
    public String studyRoomRegister() {
        return "studyroom/register";
    }

    @GetMapping("/studyroom/index")
    public String studyRoomIndex() {
        return "studyroom/index";
    }

    @GetMapping("/studyroom/book")
    public String studyRoomBook() {
        return "studyroom/book";
    }

    @GetMapping("/studyroom/history")
    public String studyRoomHistory() {
        return "studyroom/history";
    }

    @GetMapping("/studyroom/admin")
    public String studyRoomAdmin() {
        return "studyroom/admin";
    }

    @GetMapping("/studyroom/admin/records")
    public String studyRoomAdminRecords() {
        return "studyroom/adminRecords";
    }

    @GetMapping("/studyroom/admin/positions")
    public String studyRoomAdminPositions() {
        return "studyroom/adminPositions";
    }

    @GetMapping("/studyroom/admin/login")
    public String studyRoomAdminLogin() {
        return "studyroom/adminLogin";
    }
}
