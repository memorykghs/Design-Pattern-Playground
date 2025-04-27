package pers.design.pattern.builder.hamburger;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;

/**
 * @author memorykghs
 * @date 2025/4/24
 */
@Getter
public class Hamburger {

    private final StringBuilder sb = new StringBuilder();
    private String topBun;
    private String vegetables;
    private String tomato;
    private String cheese;
    private String meet;
    private String bottomBun;


    private Hamburger(HamburgerBuilder builder){
        this.topBun = builder.topBun;
        this.vegetables = builder.vegetables;
        this.tomato = builder.tomato;
        this.cheese = builder.cheese;
        this.meet = builder.meet;
        this.bottomBun = builder.bottomBun;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        if (StringUtils.isNotBlank(topBun)) {
            sb.append(topBun).append("\n");
        }
        if (StringUtils.isNotBlank(vegetables)) {
            sb.append(vegetables).append("\n");
        }
        if (StringUtils.isNotBlank(tomato)) {
            sb.append(tomato).append("\n");
        }
        if (StringUtils.isNotBlank(cheese)) {
            sb.append(cheese).append("\n");
        }
        if (StringUtils.isNotBlank(meet)) {
            sb.append(meet).append("\n");
        }
        if (StringUtils.isNotBlank(bottomBun)) {
            sb.append(bottomBun).append("\n");
        }

        String str = sb.toString();

        sb.setLength(0);
        return str;
    }

    public static class HamburgerBuilder {
        private String topBun;
        private String vegetables;
        private String tomato;
        private String cheese;
        private String meet;
        private String bottomBun;

        public Hamburger build(){
            return new Hamburger(this);
        }

        public HamburgerBuilder topBun(String topBun) {
            this.topBun = topBun;
            return this;
        }

        public HamburgerBuilder vegetables(String vegetables) {
            this.vegetables = vegetables;
            return this;
        }

        public HamburgerBuilder tomato(String tomato) {
            this.tomato = tomato;
            return this;
        }

        public HamburgerBuilder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public HamburgerBuilder meet(String meet) {
            this.meet = meet;
            return this;
        }

        public HamburgerBuilder bottomBun(String bottomBun) {
            this.bottomBun = bottomBun;
            return this;
        }
    }
}
