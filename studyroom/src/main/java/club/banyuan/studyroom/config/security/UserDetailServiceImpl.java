package club.banyuan.studyroom.config.security;

import club.banyuan.studyroom.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        club.banyuan.studyroom.model.User user = userMapper.queryUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!"); // 用户名或密码错误
        }
        if (user.getAuthority() != 1) {
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");
            return new User(user.getUsername(), user.getPassword(), authorities);
        } else {
            List<GrantedAuthority> authorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            return new User(user.getUsername(), user.getPassword(), authorities);
        }
    }
}