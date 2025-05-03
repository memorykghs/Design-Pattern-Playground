package pers.design.pattern.observer;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author memorykghs
 * @date 2025/5/1
 */
@Service
public class UuidGenerator {
    public String genUuid() {
        return UUID.randomUUID().toString();
    }
}
