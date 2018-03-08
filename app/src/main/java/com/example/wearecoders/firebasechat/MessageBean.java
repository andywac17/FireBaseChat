package com.example.wearecoders.firebasechat;

/**
 * Created by We Are Coders on 3/8/2018.
 */

public class MessageBean {
    private String content;

    public MessageBean(){
    }
    public MessageBean(String content){
        this.content=content;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
