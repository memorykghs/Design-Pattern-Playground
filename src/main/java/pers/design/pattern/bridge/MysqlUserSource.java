package pers.design.pattern.bridge;

import pers.design.pattern.user.UserDAO;
import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public class MysqlUserSource implements UserDataSource {

    @Override
    public UserDTO fetch(String id) {
        // 模擬從 MySQL 獲取用戶資料並轉換成 DTO
        return new UserDTO(
            "ashley",
            "rawPassword",
            "A123456789",
            "ashley@mail.com");
    }
}
