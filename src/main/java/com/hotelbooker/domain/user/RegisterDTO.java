package com.hotelbooker.domain.user;

import com.hotelbooker.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}