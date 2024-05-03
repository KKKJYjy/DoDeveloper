package com.dodeveloper.message.vodto;

import java.util.LinkedList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class SendMessageDTO {
    public MessageDTO message;
    public LinkedList<String> receiverIdList;
}
