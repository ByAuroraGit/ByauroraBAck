package br.com.delivery.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarrinhoProdutoDto {
    public Long produto_id ;
    public Long carrinho_de_compras_id;
    public Long carrinho_produto;
    public int quantidade;
    public Long user_id;

    public String tamanho;
}
