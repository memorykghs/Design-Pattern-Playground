package pers.design.pattern.builder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@Builder
@Data
//@RequiredArgsConstructor
public class UserBuilder {
    /**
     * lombok 的 @Builder 預設是空的建構子
     * 所以加上 @RequiredArgsConstructor 會出錯
     */
    @NotBlank(message = "user ID is required.")
    private final String userId;
    private String name;
    private int age;
}
