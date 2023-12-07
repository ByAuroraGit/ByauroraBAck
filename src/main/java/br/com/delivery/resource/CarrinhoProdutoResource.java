package br.com.delivery.resource;

import br.com.delivery.entity.CarrinhoProduto;
import br.com.delivery.entity.dto.CarrinhoProdutoDto;
import br.com.delivery.model.mapper.CarrinhoComprasMapper;
import br.com.delivery.service.CarrinhoProdutoService;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Path("v1/carsProducts")
public class CarrinhoProdutoResource {

    @Inject
    CarrinhoProdutoService carrinhoProdutoService;

    @Inject
    CarrinhoComprasMapper carrinhoComprasMapper;


    @POST
    @Path("addProducts")
    @Transactional
    public void addProdutcs(CarrinhoProdutoDto carrinhoProdutoDto, @Context HttpServerRequest request){
        carrinhoProdutoService.addProdutos(carrinhoProdutoDto, request);
    }

    @POST
    @Path("decrementar")
    @Transactional
    public void decrementarProduto(CarrinhoProdutoDto carrinhoProdutoDto, @Context HttpServerRequest request) {
        carrinhoProdutoService.decrementarProduto(carrinhoProdutoDto, request);
    }

    @GET
    @Path("produto/carrinho")
    public List<CarrinhoProduto> getProdutoCarrinho(@QueryParam("id") Long cartId) {
        return carrinhoComprasMapper.listaDeProdutoPorCarrinho(cartId);
    }

    @POST
    @Path("deletar")
    @Transactional
    public void deletarProdutoLista(CarrinhoProdutoDto carrinhoProdutoDto, @Context HttpServerRequest request) {
         carrinhoProdutoService.deletarProdutoDaLista(carrinhoProdutoDto, request);
    }



}
