package pers.design.pattern.chain;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
@Component
public class LineBindingDao {
    Map<String, String> mockLineBinding = Map.of(
        "86594", "8b3f9c32-3c41-4e50-b9a0-1a6405eb5ff1"
    );

    public String getLineBindingUUID(String userId){
        return mockLineBinding.get(userId);
    }
}
