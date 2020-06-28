package club.banyuan.studyroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StudyRoomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
//        return authenticationProvider;
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////         增加自定义的UserDetailService
//        auth.userDetailsService(userDetailsService);
////         设置一个Provider
//        auth.authenticationProvider(authenticationProvider());
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
//        return authenticationProvider;
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] allowList = {
                "/actuator/**",
                "/static/**",
                "/**.js",
                "/**.css",
                "/**.jpg",
                "/**.jpeg",
                "/**.png",
                "/**.gif",
                "/studyroom/**",
                "/api/user/login",
                "/api/user/logout",
                "/api/user/current",
                "/logic/**"
        };

        http.authorizeRequests()//开启登录配置
                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(allowList).permitAll()
                .anyRequest().authenticated() // 其他接口，登录之后就能访问
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                // 异常
                .exceptionHandling();
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    @Override
//                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
//                        if (httpServletRequest.getRequestURI().contains("/api/")) {
//                            // 是接口
//                            response.setHeader("Access-Control-Allow-Origin", "*");
//                            response.setHeader("Cache-Control", "no-cache");
//                            response.setCharacterEncoding("UTF-8");
//                            response.setContentType("application/json");
//                            response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
//                            response.getWriter().flush();
//                        } else {
//                            // 是页面
//                            response.sendRedirect(FORBIDDEN_PAGE_URI);
//                        }
//                    }
//                })
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                        if (httpServletRequest.getRequestURI().contains("/api/")) {
//                            // 是接口
//                            response.setHeader("Access-Control-Allow-Origin", "*");
//                            response.setHeader("Cache-Control", "no-cache");
//                            response.setCharacterEncoding("UTF-8");
//                            response.setContentType("application/json");
//                            response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(e.getMessage())));
//                            response.getWriter().flush();
//                        } else {
//                            // 是页面
//                            response.sendRedirect(LOGIN_PAGE_URI);
//                        }
//                    }
//                });
    }
}