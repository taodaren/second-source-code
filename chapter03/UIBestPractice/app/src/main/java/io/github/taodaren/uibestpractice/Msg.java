package io.github.taodaren.uibestpractice;

/**
 * 消息实体类
 */

public class Msg {
    public static final int TYPE_RECEIVED = 0;//收到的消息
    public static final int TYPE_SENT = 1;//发出的消息

    private String input;//消息内容
    private int type;//消息类型

    public Msg(String input, int type) {
        this.input = input;
        this.type = type;
    }

    public String getInput() {
        return input;
    }

    public int getType() {
        return type;
    }
}
