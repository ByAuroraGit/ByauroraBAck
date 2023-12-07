package br.com.delivery.model.mapper;

import br.com.delivery.entity.Produto;
import br.com.delivery.entity.Produto_imgs;
import br.com.delivery.entity.dto.ProdutoDto;
import io.quarkiverse.mybatis.runtime.meta.MapperDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface ProdutosMapper {

    @Select("select * from produtos p " +
            "where ((#{nome, jdbcType=VARCHAR} is null) or lower(p.nome) like lower(concat(#{nome, jdbcType=VARCHAR} , '%'))) " +
            "and (#{categoria, jdbcType=VARCHAR} is null or lower(p.categoria) = lower(#{categoria, jdbcType=VARCHAR})) " +
            "and (((#{dataInicio}::timestamp is null) or (#{dataFim}::timestamp is null)) or p.createat BETWEEN #{dataInicio}::timestamp AND #{dataFim}::timestamp) "  +
            "and (#{id, jdbcType=BIGINT} is null or p.id = #{id, jdbcType=BIGINT})" +
            " order by p.createat asc, p.nome asc")
    List<ProdutoDto> filter(@Param("nome") String nome, @Param("categoria") String categoria, @Param("id") Long id, @Param("dataInicio")String dataInicio, @Param("dataFim") String dataFim);

    @Select("select pi.id from produto_imgs pi where pi.produto_id = #{produto_id} ")
    long[] imagens(@Param("produto_id") Long produto_id);

}
