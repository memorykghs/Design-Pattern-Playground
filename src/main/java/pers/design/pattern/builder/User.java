package pers.design.pattern.builder;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
import lombok.Getter;

@Getter
public class User {
    // required
    private final String name;

    // not required
    private final String email;
    private final int age;
    private final boolean isAdult;
    private final LocalDateTime createdAt;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.isAdult = this.age >= 18;
        // 固定為當下時間
        this.createdAt = LocalDateTime.now();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private int age = 18; // default value

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            // 驗證 name 不可為空
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("User name is required and cannot be blank.");
            }

            // 如果有填 email，要驗證格式（簡單判斷）
            if (StringUtils.isNotBlank(email) && !email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
                throw new IllegalArgumentException("Email format is invalid.");
            }

            return new User(this);
        }
    }
}
