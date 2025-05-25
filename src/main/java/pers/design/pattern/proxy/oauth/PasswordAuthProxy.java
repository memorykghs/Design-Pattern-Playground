package pers.design.pattern.proxy.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.design.pattern.utils.ApplicationContext;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
@Service("passwordAuthProxy")
@Slf4j
public class PasswordAuthProxy extends AbstractProxyDocumentService {
    public PasswordAuthProxy(@Qualifier("userRoleAuthProxy") IDocumentService next) {
        super(next);
    }

    @Override
    public String getDocumentContent(String documentId) {
        String password = ApplicationContext.getCurrentPassword();
        if ("123456".equals(password)) {
            log.info("✅ 密碼驗證成功");
            return next.getDocumentContent(documentId);
        } else {
            log.error("❌ 密碼錯誤，拒絕存取！");
            return "🚫 無權限訪問此文檔。";
        }
    }
}
