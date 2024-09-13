package com.example.commerce_clientes.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDto(@NotBlank String name, @NotNull int cpf, String email, @NotBlank Date dateNasc, int telefone) {

}
