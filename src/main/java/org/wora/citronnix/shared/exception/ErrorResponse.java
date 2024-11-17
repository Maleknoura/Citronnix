package org.wora.citronnix.shared.exception;


import lombok.Getter;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String message, LocalDateTime timestamp, String details) {
}
