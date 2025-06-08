package pers.design.pattern.bridge;

import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public class RedisUserSource implements UserDataSource {
    @Override
    public UserDTO fetch(String id) {
        // 模擬從 Redis 獲取用戶資料並轉換成 DTO
        return new UserDTO(
            "redisUser",
            "redisPassword",
            "R998877134",
            "redis@mail.com");
    }
}
