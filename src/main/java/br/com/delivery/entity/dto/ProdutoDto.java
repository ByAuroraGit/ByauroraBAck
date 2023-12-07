package br.com.delivery.entity.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoDto {

    public Long id;
    public String nome;
    public Double preco;
    public String descricao;
    public String categoria;
    public String dataInicio;
    public String dataFim;


    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    public LocalDateTime createAt;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    public LocalDateTime updateAt;

    public long[] produto_imgs_id;


    public ProdutoDto(String nome) {
        this.nome = nome;
    }

    public ProdutoDto(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }


    public ProdutoDto(String nome, String dataInicio, String dataFim, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public ProdutoDto(String nome, String categoria, long id) {
        this.nome = nome;
        this.categoria = categoria;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.id = id;
    }




}
