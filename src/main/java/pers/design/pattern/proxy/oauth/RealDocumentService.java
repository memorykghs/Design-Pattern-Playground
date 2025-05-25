package pers.design.pattern.proxy.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
@Service("realDocumentService")
@Slf4j
public class RealDocumentService implements IDocumentService {

    @Override
    public String getDocumentContent(String documentId) {
        log.info("Document content for ID: {}", documentId);
        // 模擬從數據庫或其他存儲中獲取文檔內容
        return "📄 文件內容：這是一份機密文件。 ID: " + documentId;
    }
}
