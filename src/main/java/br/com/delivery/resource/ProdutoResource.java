package br.com.delivery.resource;

import br.com.delivery.entity.CarrinhoProduto;
import br.com.delivery.entity.Produto;
import br.com.delivery.entity.Produto_imgs;
import br.com.delivery.entity.dto.ProdutoDto;
import br.com.delivery.model.mapper.ProdutosMapper;
import br.com.delivery.service.ProdutoService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("v1/products")
public class ProdutoResource {

    @Inject
    ProdutosMapper produtosMapper;

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getById(@PathParam("id") Long id){
        return Produto.findBywithId(id);
    }



    @POST
    @Path("filters")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProdutoDto> filterProdutos(ProdutoDto produtoDto){
        return produtoService.filterProdutos(produtoDto);
    }



}
