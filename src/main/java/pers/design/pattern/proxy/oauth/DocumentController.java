package pers.design.pattern.proxy.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.design.pattern.utils.ApplicationContext;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
@RestController
@RequestMapping("/proxy")
@Slf4j
public class DocumentController {

    private final IDocumentService documentService;

    public DocumentController(@Qualifier("passwordAuthProxy") IDocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/read")
    public void handleReadRequest(@RequestBody DocumentReq readRequest) {
        try {
            String userRole = readRequest.getUserRole();
            log.info("🔑 模擬登入角色： {}", userRole);
            ApplicationContext.setBasicInfo(userRole, readRequest.getDocumentId());

            // 透過 Proxy 進入
            documentService.getDocumentContent(readRequest.documentId);

        } finally {
            // 請求結束清除
            ApplicationContext.clear();
        }
    }

}
