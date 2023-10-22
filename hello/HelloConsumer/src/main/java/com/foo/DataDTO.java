package com.foo;

import java.time.Instant;

public record DataDTO(Long id, String value, Instant tm) {
}
