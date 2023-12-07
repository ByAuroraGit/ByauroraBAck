package br.com.delivery.model.mapper;

import br.com.delivery.entity.CarrinhoProduto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarrinhoComprasMapper {


    @Select("select cp.produto_id,  sum(cp.preco) as preco, cp.carrinho_id , sum(cp.quantidade)as quantidade from carrinho_produto cp " +
            "where cp.carrinho_id = #{id} " +
            "group by cp.produto_id , cp.carrinho_id " +
            "order by cp.produto_id ")
    List<CarrinhoProduto> listaDeProdutoPorCarrinho(@Param("id") Long cartId);



}
