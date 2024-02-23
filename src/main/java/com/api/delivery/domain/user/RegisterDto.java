package com.api.delivery.domain.user;

public record RegisterDto(String username, String password, UserRole role) {
}
