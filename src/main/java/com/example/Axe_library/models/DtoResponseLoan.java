package com.example.Axe_library.models;

import java.time.LocalDateTime;

public record DtoResponseLoan(
        Long id,
        String client,
        String bookName,
        String bookAutor,
        Boolean returned,
        LocalDateTime loanDate,
        LocalDateTime loanReturn
) {
}
