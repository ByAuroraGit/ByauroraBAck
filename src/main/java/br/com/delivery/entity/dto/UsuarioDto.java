package br.com.delivery.entity.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDto {

    @NotNull
    public String nome;
    @NotNull
    public String cpf;
    @NotNull
    @JsonbDateFormat(value = "yyyy-MM-dd")
    public LocalDate dataNascimento;
    @NotNull
    @NotBlank(message = "Cidade não pode ser vazia")
    public String cidade;
    @NotNull
    public String longradouro;
    @NotNull
    public String longradouroNumero;
    @NotNull
    public String telefone;
}
