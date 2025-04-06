package pers.design.pattern.chain;

/**
 * @author memorykghs
 * @date 2025/4/6
 */
public enum NotificationPriorityEnum {

    EMAIL("EmailNotification.class", 1),
    LINE("LineNotification.class", 2),
    SMS("SmsNotification.class", 3);

    private String className;
    private int priority;

    NotificationPriorityEnum(String className, int priority){

    }
}
