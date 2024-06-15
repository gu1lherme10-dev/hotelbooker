package com.hotelbooker.domain.user;

import com.hotelbooker.entity.UserRole;

public record LoginResponseDTO(String token, UserRole role) {
}