package br.com.delivery.entity;

import br.com.delivery.entity.dto.CarrinhoProdutoDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import io.smallrye.common.constraint.NotNull;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "carrinho_produto")

public class CarrinhoProduto extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbTransient
    public Long id;
    public Double preco;
    @ManyToOne(fetch = FetchType.LAZY)
//    @NotNull
    @JoinColumn(name = "carrinho_id", nullable = false)
    @JsonbTransient
    public CarrinhoDeCompras carrinhoDeCompras;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    public Produto produto;
    public int quantidade;
    public String tamanho;
    @CreationTimestamp
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    public ZonedDateTime createAt;



    public void calcularTotal() {
        preco = produto.getPreco() * quantidade;
    }
}