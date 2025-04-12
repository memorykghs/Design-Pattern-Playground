package pers.design.pattern.strategy;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author memorykghs
 * @date 2025/4/10
 */
@Getter
public enum ChannelEnum {

    CATHAY_CREDIT_CARD("credit card", "Cathay United Bank"),
    LINE_BANK_CREDIT_CARD("credit card", "LINE Bank"),
    NEXT_BANK_CREDIT_CARD("credit card", "Next Bank")
    ;

    ChannelEnum(String channel, String provider) {
        this.channel = channel;
        this.provider = provider;
    }

    private final String channel;

    private final String provider;

    public static ChannelEnum getByChannelAndProvider(String channel, String provider){
        return Arrays.stream(ChannelEnum.values())
            .filter(e -> e.getChannel().equals(channel) && e.getProvider().equals(provider))
            .findFirst()
            .orElse(null);
    }
}
