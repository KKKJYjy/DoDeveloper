package com.dodeveloper.commons.interceptor;

public interface SessionNames {
	static final String LOGIN_MEMBER = "loginMember";
	static final String LOGIN_DTO = "loginDTO";
	static final String LOGIN_COOKIE = "loginCookie";
	static final String ATTEMPTED = "attemptedLocation";

	static final int EXPIRE = 7 * 24 * 60 * 60;
}
