package pers.design.pattern.proxy.oauth;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
public abstract class AbstractProxyDocumentService implements IDocumentService {

    protected IDocumentService next;

    public AbstractProxyDocumentService(IDocumentService next) {
        this.next = next;
    }
}
