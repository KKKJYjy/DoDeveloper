package com.dodeveloper.message.vodto;

import javax.annotation.processing.Generated;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//messageFileNo, messageNo, uploadName, ext, originalName

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class MessageFileDTO {
	private int messageNo;
	private String uploadName;
	private String ext;
	private String originalName;
}
