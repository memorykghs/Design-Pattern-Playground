package pers.design.pattern.adapter;

/**
 * @author memorykghs
 * @date 2025/6/8
 */

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.design.pattern.user.UserDAO;
import pers.design.pattern.user.UserDTO;

@RestController
@RequestMapping("/api/adapter")
@RequiredArgsConstructor
@Slf4j
public class AdapterController {

    private final IUserAdapter userAdapter;

    @PostMapping("/create")
    public UserDAO createUser(@RequestBody UserDTO userDTO) {
        return userAdapter.toDAO(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable String id) {
        // 模擬從資料庫獲取用戶資料
        UserDAO userDAO = mockGetUserFromDB(id);
        return userAdapter.toDTO(userDAO);
    }

    private UserDAO mockGetUserFromDB(String id) {
        return new UserDAO()
            .setId(id)
            .setUsername("testUser")
            .setEncryptedPassword("encryptedPassword")
            .setEmail("test@example.com");
    }
}
