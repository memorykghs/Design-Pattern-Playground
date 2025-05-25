package pers.design.pattern.proxy.oauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pers.design.pattern.utils.ApplicationContext;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
@Service("userRoleAuthProxy")
public class UserRoleAuthProxy extends AbstractProxyDocumentService {

    public UserRoleAuthProxy(@Qualifier("realDocumentService") IDocumentService next) {
        super(next);
    }

    @Override
    public String getDocumentContent(String documentId) {
        if (isAuthorized()) {
            return next.getDocumentContent(documentId);
        } else {
            return "🚫 無權限訪問此文檔。";
        }
    }

    /**
     * 模擬權限檢查
     *
     * @return 是否有權限
     */
    private boolean isAuthorized() {
        return "admin".equals(ApplicationContext.getCurrentUserRole());
    }
}
