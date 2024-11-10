package com.capgemini.wsb.fitnesstracker.user.api.dtos;

import jakarta.annotation.Nullable;

public record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {
}
