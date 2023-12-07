package br.com.delivery.service;

import br.com.delivery.entity.dto.ProdutoDto;
import br.com.delivery.model.mapper.ProdutosMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutosMapper produtosMapper;

    public List<ProdutoDto> filterProdutos(ProdutoDto produtoDto){
        List<ProdutoDto> produtoDto1 = produtosMapper.filter(produtoDto.nome, produtoDto.categoria, produtoDto.id, produtoDto.dataInicio, produtoDto.dataFim);
        produtoDto1.forEach(x -> {
            x.produto_imgs_id = produtosMapper.imagens(x.id);
        });
        return produtoDto1;
    }
}
