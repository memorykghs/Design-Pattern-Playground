package pers.design.pattern.decorator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.design.pattern.user.UserDTO;
import pers.design.pattern.utils.MaskUtil;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
@Component("UserIdMaskDecorator")
@Slf4j
public class UserIdMaskDecorator implements IUserProcessor {
    private final IUserProcessor processor;

    public UserIdMaskDecorator(IUserProcessor processor) {
        this.processor = processor;
    }

    @Override
    public UserDTO mask(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        if (processor != null) {
            log.info("doing ");
            userDTO = processor.mask(userDTO);
        }

        userDTO.setId(MaskUtil.maskId(userDTO.getId()));
        return userDTO;
    }
}
