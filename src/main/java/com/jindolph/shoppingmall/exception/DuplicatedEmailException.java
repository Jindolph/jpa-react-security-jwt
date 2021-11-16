package com.jindolph.shoppingmall.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String email) {
        super("Email is already registered.");
        log.warn("Email {} is already registered", email);
    }
}