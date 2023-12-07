package br.com.delivery.entity;

//import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carrinhos_de_compras")
@Builder
public class CarrinhoDeCompras extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public double carTotal;
    public String nome;
    @OneToMany(mappedBy = "carrinhoDeCompras", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CarrinhoProduto> carrinho_produto;
//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    @JsonbTransient
//    public Usuario usuario;

    public int qtdProdutos;

    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @CreationTimestamp
    public ZonedDateTime createAt;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @UpdateTimestamp
    public ZonedDateTime updateAt;


    public void calculatedCarTotalAndQtd() {
        qtdProdutos = carrinho_produto.stream().mapToInt(CarrinhoProduto::getQuantidade).sum();
        carTotal = carrinho_produto.stream().mapToDouble(CarrinhoProduto::getPreco).sum();
    }

    public boolean combinarCarrinhoProdutos(Long id, String tamanho) {
        for (CarrinhoProduto carrinhoProduto : carrinho_produto) {
            Produto produto = carrinhoProduto.produto;
            if (Objects.equals(produto.id, id) && carrinhoProduto.tamanho.equals(tamanho)) {
                return true;
            }
        }
        return false;
    }

    public static  List<CarrinhoDeCompras> buscrCarrinhoPorId(Long carrinhoId) {
        return  find("carrinhoDeCompras.id", carrinhoId).list();
    }



}
