package pers.design.pattern.decorator;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import pers.design.pattern.user.UserDTO;
import pers.design.pattern.utils.MaskUtil;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
@Component("UserNameMaskDecorator")
public class UserNameMaskDecorator implements IUserProcessor {
    private final IUserProcessor processor;

    public UserNameMaskDecorator(IUserProcessor processor) {
        this.processor = processor;
    }

    @Override
    public UserDTO mask(UserDTO userDTO) {
        if (userDTO == null || StringUtils.isBlank(userDTO.getUsername())) {
            return userDTO;
        }

        if(processor == null) {
            throw new IllegalArgumentException("processor is not set.");
        }

        processor.mask(userDTO);
        userDTO.setUsername(MaskUtil.maskUsername(userDTO.getUsername()));
        return userDTO;
    }
}
