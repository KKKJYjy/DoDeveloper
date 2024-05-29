package com.dodeveloper.message.vodto;

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
public class MessageBoxVO {
    private int messageBoxNo;
    private int messageNo;
    private String receiver;
    private String isRead;
    private String isDeleted;
}
