package com.educacionit.student.api.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKey123";
    public static final long EXPIRATION_TIME = 3_600_000; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORITIES_KEY = "ROLES";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
