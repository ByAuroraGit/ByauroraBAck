package br.com.delivery.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarrinhoDto {
    public String nome;
    public int qtdProdutos;
    public Long usuario_id;
}
