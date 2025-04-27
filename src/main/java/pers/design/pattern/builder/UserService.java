package pers.design.pattern.builder;

import org.springframework.stereotype.Service;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@Service
public class UserService {
    public User getUser(){
        // 自己手寫 builder
        return new User.UserBuilder()
//            .name("ashley") 會噴錯
            .build();
    }

    public UserTrasform getUserTransform(){
        // 變形的 builder
        return new UserTrasform()
            .setName("ashley")
            .build();
    }

    public UserBuilder getUserBuilder() {
        // lombok builder
        return UserBuilder.builder()
            .name("ashley")
            .build();
    }

    public UserAccessor getUserAccessor() {
        // lombok accessor
        return new UserAccessor()
            .setName("ashley");
    }
}
