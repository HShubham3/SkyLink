package com.scm.skylink.helper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Message {

    private String content;

    @Builder.Default
    private MessageType type = MessageType.blue;

}
