package com.fantasy.club.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationException extends Throwable {
    private String message;
}
