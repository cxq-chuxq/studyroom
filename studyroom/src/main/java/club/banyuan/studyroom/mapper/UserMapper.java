package club.banyuan.studyroom.mapper;

import club.banyuan.studyroom.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User queryUserById(@Param("id") int id);

//    void insert(@Param("userInfo") User userInfo);

    User queryUserByUserName(@Param("userName") String userName);

    void deleteUser(@Param("userId") int userId);

    void updateUser(@Param("user") User user);

    void addUser(@Param("user") User user);

    List<User> listUser();
}