package pers.design.pattern.proxy.oauth;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
public interface IDocumentService {
    /**
     * 獲取文檔內容
     *
     * @param documentId 文檔 ID
     * @return 文檔內容
     */
    String getDocumentContent(String documentId);
}
