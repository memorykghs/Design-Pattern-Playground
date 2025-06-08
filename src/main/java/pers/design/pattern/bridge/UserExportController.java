package pers.design.pattern.bridge;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
@RestController
@RequestMapping("/api/bridge")
public class UserExportController {

    private final Map<String, UserDataSource> sourceMap;

    public UserExportController(List<UserDataSource> sources) {
        // 用 Bean 名稱當 key：mysql / redis
        this.sourceMap = sources.stream()
            .collect(Collectors.toMap(s -> s.getClass().getAnnotation(Component.class).value(), s -> s));
    }

    @GetMapping("/export")
    public String exportUser(@RequestParam String userId, @RequestParam String source, @RequestParam String format) {
        UserDataSource dataSource = sourceMap.get(source);
        if (dataSource == null) {
            return "Invalid source";
        }

        UserExporter exporter = switch (format.toLowerCase()) {
            case "json" -> new JsonUserExporter(dataSource);
            case "xml" -> new XmlUserExporter(dataSource);
            default -> null;
        };

        if (exporter == null) {
            return "Invalid format";
        }

        return exporter.export(userId);
    }
}
