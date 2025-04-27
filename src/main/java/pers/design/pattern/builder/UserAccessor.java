package pers.design.pattern.builder;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@Accessors(chain = true)
@Data
//@RequiredArgsConstructor
public class UserAccessor {
    /**
     * lombok 的 @Accessors 預設是空的建構子
     * 所以加上 @RequiredArgsConstructor 會出錯
     */
    @NotBlank(message = "user ID is required.")
    private String userId;
    private String name;
    private int age;
}
