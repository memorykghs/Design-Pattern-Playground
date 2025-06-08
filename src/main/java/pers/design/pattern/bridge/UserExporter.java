package pers.design.pattern.bridge;

import lombok.Setter;

/**
 * 抽象的用戶導出器
 *
 * @author memorykghs
 * @date 2025/6/8
 */
@Setter
public abstract class UserExporter {
    protected final UserDataSource source;

    public UserExporter(UserDataSource source) {
        this.source = source;
    }

    public abstract String export(String userId);
}
