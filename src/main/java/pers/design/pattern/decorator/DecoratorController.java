package pers.design.pattern.decorator;

/**
 * @author memorykghs
 * @date 2025/6/8
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.design.pattern.user.UserDTO;

@RestController
@RequestMapping("/api/decorator")
@RequiredArgsConstructor
@Slf4j
public class DecoratorController {

    private final IUserProcessor userProcessor;
    private final ApplicationContext applicationContext;

    @PostMapping("/mask")
    public UserDTO maskUserId(@RequestBody UserDTO userDTO) {
        return userProcessor.mask(userDTO);
    }

    @GetMapping("/mask/{id}")
    public UserDTO maskUserNameAndId(@RequestBody UserDTO userDTO) {
        IUserProcessor userNameMaskDecorator = (IUserProcessor)applicationContext.getBean("UserNameMaskDecorator");
        userNameMaskDecorator.mask(userDTO);
        return userDTO;
    }
}
