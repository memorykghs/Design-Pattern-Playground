package pers.design.pattern.builder;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@Getter
public class UserTrasform {
    // required
    private String name;

    // not required
    private String email;
    private int age = 18;
    private boolean isAdult;
    private LocalDateTime createdAt;

    public UserTrasform setName(String name) {
        this.name = name;
        return this;
    }

    public UserTrasform setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserTrasform setAge(int age) {
        this.age = age;
        return this;
    }

    public UserTrasform setIsAdult(boolean isAdult){
        this.isAdult = isAdult;
        return this;
    }

    public UserTrasform setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
        return this;
    }

    public UserTrasform build(){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }

        this.isAdult = this.age >= 18;
        this.createdAt = LocalDateTime.now();
        return this;
    }
}
