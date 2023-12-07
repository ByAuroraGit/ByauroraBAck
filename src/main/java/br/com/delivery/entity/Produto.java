package br.com.delivery.entity;

//import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import br.com.delivery.entity.dto.ProdutoDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@Table(name = "produtos")

public class Produto extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "nome")
    public String nome;
    @Column(name = "preco")
    public Double preco;
    @Column(name = "descricao")
    public String descricao;
    @Column(name = "categoria")
    public String categoria;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    public List<Produto_imgs> produto_imgs;

    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @CreationTimestamp
    @Column(name = "createat")
    public LocalDateTime createAt;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @UpdateTimestamp
    @Column(name = "updateat")
    public LocalDateTime updateAt;

    public static Produto findByName(String name) {
        return find("name", name).firstResult();
    }
    public static Produto findBywithId(Long id){
        return find("id",id).firstResult();
    }


    public static List<Produto> findAllProduto(int tamanhoPagina, int pagina) {
        return findAll()
                .range(pagina,tamanhoPagina)
                .list();
    }

}
