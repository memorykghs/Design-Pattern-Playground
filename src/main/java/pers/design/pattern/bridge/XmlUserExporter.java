package pers.design.pattern.bridge;

import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public class XmlUserExporter extends  UserExporter {

    public XmlUserExporter(UserDataSource source) {
        super(source);
    }

    @Override
    public String export(String userId) {
        UserDTO dto = source.fetch(userId);
        return toXml(dto); // 用 XmlMapper 實作也可
    }

    /**
     * 將 UserDTO 轉換為 XML 字符串
     * @param dto
     * @return
     */
    private String toXml(UserDTO dto) {
        // 假設有一個方法將 UserDTO 轉換為 XML 字符串
        return "<user><id>" + dto.getId() + "</id><username>" + dto.getUsername() + "</username></user>";
    }
}
