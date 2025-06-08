package pers.design.pattern.bridge;

import com.alibaba.fastjson.JSON;
import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public class JsonUserExporter extends UserExporter {
    public JsonUserExporter(UserDataSource source) {
        super(source);
    }

    public String export(String userId) {
        UserDTO dto = source.fetch(userId);
        return toJson(dto); // 用 ObjectMapper 實作也可
    }

    /**
     * 將 UserDTO 轉換為 JSON 字符串
     * @param dto
     * @return
     */
    private String toJson(UserDTO dto) {
        return JSON.toJSONString(dto);
    }
}
