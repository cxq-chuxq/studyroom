package club.banyuan.studyroom.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @PostMapping(value = "/login")
//    public CommonResult login(@RequestBody User user, HttpServletRequest request, HttpSession session) {
//
//        // 获取用户在不在
//        UserExample example = new UserExample();
//        example.createCriteria().andUsernameEqualTo(user.getUsername());
//        List<User> tmpUsers = userMapper.selectByExample(example);
//        if (CollectionUtils.isEmpty(tmpUsers)) {
//            return CommonResult.failed("用户不存在！");
//        }
//
//        User tmpUser = tmpUsers.get(0);
//
//        // 判断密码对不对
//        try {
//            if (!BCrypt.checkpw(user.getPassword(), tmpUser.getPassword())) {
//                return CommonResult.failed("密码错误！[99982]");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return CommonResult.failed("密码错误！[99983]");
////            return CommonResult.failed(e.getMessage());
//        }
//
//        // 放进 Session
////        session.setAttribute("user", tmpUser);
//
//        String role = "ROLE_USER";
//        if (tmpUser.getUsername().equals("trump")) {
//            role = "ROLE_ADMIN";
//        }
//
//        List<GrantedAuthority> authorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("BOOK_READ,SEAT_WRITE"); // TODO u.getRoles()
//        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(tmpUser.getUsername(), tmpUser.getPassword(), authorities);
//
//        // PreAuthenticatedAuthenticationToken 当然可以用其他 token,如 UsernamePasswordAuthenticationToken
//        PreAuthenticatedAuthenticationToken authentication =
//                new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
//        // 设置authentication中details
//        authentication.setDetails(new WebAuthenticationDetails(request));
//
//        // 存放 authentication到SecurityContextHolder
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // 在session中存放security context,方便同一个session中控制用户的其他操作
//        //session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//
//        return CommonResult.success("OK");
//    }
//
//    @GetMapping("/logout")
//    public CommonResult logout(HttpSession session) {
//        // session.removeAttribute("SPRING_SECURITY_CONTEXT");
//        SecurityContextHolder.getContext().setAuthentication(null);
//        return CommonResult.success("OK");
//    }
//
//    @GetMapping(value = "/current")
//    public CommonResult getCurrentUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal != null) {
//            System.out.println("principal 的内容是：" + principal);
//            try {
//                UserDetails userDetails = (UserDetails) principal;
////            String username = userDetails.getUsername();
//                return CommonResult.success(principal);
//            } catch (ClassCastException e) {
//                return CommonResult.failed("用户未登录!" + principal);
//            }
//        }
//
//        return CommonResult.success("未登录");
//    }
//
//    @PostMapping(value = "/register")
//    public CommonResult register(@RequestBody User user) {
//
//        // 用户名和密码不能为空
//        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
//            return CommonResult.failed("注册失败!");
//        }
//
//        String passwordHash = BCrypt.hashpw(user.getPassword());
//        user.setPassword(passwordHash);
//
//        userMapper.insertSelective(user);
//        return CommonResult.success("OK");
//    }
//
//    @Autowired
//    private DataService dataService;
//
//    @GetMapping(value = "/weather")
//    public CommonResult weather() {
////        Map<String, Object> map = new HashMap<>();
////        map.put("weather", dataService.getWeather());
////        map.put("cities", WeatherCityCode.cities);
//
//        return CommonResult.success(dataService.getWeather("扬州"));
//    }
}