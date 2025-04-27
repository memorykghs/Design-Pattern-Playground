package pers.design.pattern.builder.hamburger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@RestController
@RequestMapping("/builder/hamburger")
@Slf4j
public class HamburgerController {

    @GetMapping
    public void makeHamburger() {
        Hamburger doubleMeet = new Hamburger.HamburgerBuilder()
            .topBun("▟████████████▙")
            .vegetables(" ◠◡◠◡◠◡◠◡◠◡◠◡")
            .tomato(" ○○○○○○○○○○○○")
            .cheese(" ════════════")
            .meet(" ▒▒▒▒▒▒▒▒▒▒▒▒")
            .bottomBun("▜████████████▛")
            .build();

        log.info(doubleMeet.toString());

        Hamburger pickyOne = new Hamburger.HamburgerBuilder()
            .topBun("▟████████████▙")
            .cheese(" ════════════")
            .meet(" ▒▒▒▒▒▒▒▒▒▒▒▒")
            .bottomBun("▜████████████▛")
            .build();

        log.info(pickyOne.toString());
    }
}
