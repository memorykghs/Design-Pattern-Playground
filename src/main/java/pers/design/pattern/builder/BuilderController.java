package pers.design.pattern.builder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@RestController
@RequestMapping("/builder")
@Slf4j
public class BuilderController {

    private final UserService userService;

    public BuilderController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/accessor")
    public void accessorTest(@Valid @RequestBody UserAccessor userAccessor) {
        log.info("accessor = {}", userService.getUserAccessor());
    }

    @GetMapping("/builder")
    public void builderTest(@Valid @RequestBody UserBuilder userBuilder) {
        log.info("builder = {}", userService.getUser());
    }
}
