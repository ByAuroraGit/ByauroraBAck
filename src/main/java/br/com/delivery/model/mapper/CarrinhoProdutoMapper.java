package br.com.delivery.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CarrinhoProdutoMapper {

    @Delete("delete from carrinho_produto where carrinho_id = #{carrinho_id, jdbcType=BIGINT} and produto_id = #{produto_id, jdbcType=BIGINT} and tamanho = #{tamanho, jdbcType=VARCHAR}")
    void deleter(@Param("carrinho_id") Long carrinho_id, @Param("produto_id") Long produto_id, @Param("tamanho") String tamanho);


    @Update("update carrinho_produto " +
            "set quantidade = (quantidade + #{quantidade}) " +
            "where carrinho_id = #{carrinho_id, jdbcType=BIGINT} and produto_id = #{produto_id, jdbcType=BIGINT} and tamanho = #{tamanho, jdbcType=VARCHAR}")
    void updateQuantidadeDeProdutoCarrinho(@Param("carrinho_id") Long carrinho_id, @Param("produto_id") Long produto_id, @Param("quantidade") int quantidade, @Param("tamanho") String tamanho);


    @Update("update carrinho_produto " +
            "set quantidade = (quantidade - #{quantidade}) " +
            "where carrinho_id = #{carrinho_id, jdbcType=BIGINT} and produto_id = #{produto_id, jdbcType=BIGINT} and tamanho = #{tamanho, jdbcType=VARCHAR}")
    void decrementCarrinhoProduto(@Param("carrinho_id") Long carrinho_id, @Param("produto_id") Long produto_id, @Param("quantidade") int quantidade, @Param("tamanho") String tamanho);
}
