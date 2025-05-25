package pers.design.pattern.proxy.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
@Data
@AllArgsConstructor
public class DocumentReq {
    String documentId;
    String userRole;
    String password;
}
