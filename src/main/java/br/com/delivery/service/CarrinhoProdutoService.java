package br.com.delivery.service;

import br.com.delivery.entity.CarrinhoDeCompras;
import br.com.delivery.entity.CarrinhoProduto;
import br.com.delivery.entity.Logstore_standard_log;
import br.com.delivery.entity.Produto;
import br.com.delivery.entity.dto.CarrinhoProdutoDto;
import br.com.delivery.model.mapper.CarrinhoProdutoMapper;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;

@ApplicationScoped
public class CarrinhoProdutoService {

    @Inject
    Logstore_standard_logService logstoreStandardLogService;

    @Inject
    CarrinhoProdutoMapper carrinhoProdutoMapper;


    public  void addProdutos (CarrinhoProdutoDto carrinhoProdutoDto, @Context HttpServerRequest request) {
        Produto produto = Produto.findBywithId(carrinhoProdutoDto.getProduto_id());
        CarrinhoDeCompras carrinhoDeCompras = CarrinhoDeCompras.findById(carrinhoProdutoDto.carrinho_de_compras_id);
        if(carrinhoDeCompras.combinarCarrinhoProdutos(carrinhoProdutoDto.produto_id, carrinhoProdutoDto.tamanho)){
            atualizarCarrinhoProduto(carrinhoProdutoDto.getCarrinho_de_compras_id(), carrinhoProdutoDto.produto_id, carrinhoProdutoDto.getQuantidade(), carrinhoProdutoDto.getTamanho());
            Logstore_standard_log logstore_standard_log =
                    Logstore_standard_log
                            .builder()
                            .carrinho_compras_id(carrinhoProdutoDto.getCarrinho_de_compras_id())
                            .user_id(carrinhoProdutoDto.getUser_id())
                            .event_name("addProduto")
                            .crud("u")
                            .target("user")
                            .produto_id(produto.id)
                            .ip(request.remoteAddress().host())
                            .action("updated")
                            .build();
            logstore_standard_log.persist();


        } else {
            CarrinhoProduto carrinhoProduto = CarrinhoProduto
                    .builder()
                    .produto(produto)
                    .carrinhoDeCompras(carrinhoDeCompras)
                    .preco((produto.preco * carrinhoProdutoDto.quantidade))
                    .quantidade(carrinhoProdutoDto.getQuantidade())
                    .tamanho(carrinhoProdutoDto.getTamanho())
                    .build();
            carrinhoProduto.persist();

            Logstore_standard_log logstore_standard_log =
                    Logstore_standard_log
                            .builder()
                            .carrinho_compras_id(carrinhoProdutoDto.getCarrinho_de_compras_id())
                            .user_id(carrinhoProdutoDto.getUser_id())
                            .event_name("addProduto")
                            .crud("c")
                            .target("user")
                            .produto_id(produto.id)
                            .ip(request.remoteAddress().host())
                            .action("created")
                            .build();
            logstore_standard_log.persist();
        }
    }

    public void atualizarCarrinhoProduto(Long carrinho_id, Long produto_id, int quantidade, String tamanho) {
         carrinhoProdutoMapper.updateQuantidadeDeProdutoCarrinho(carrinho_id, produto_id, quantidade, tamanho);
    }

    public void decrementarProduto (CarrinhoProdutoDto carrinhoProdutoDto, HttpServerRequest request) {
        carrinhoProdutoMapper.decrementCarrinhoProduto(carrinhoProdutoDto.getCarrinho_de_compras_id(), carrinhoProdutoDto.getProduto_id(), carrinhoProdutoDto.getQuantidade(), carrinhoProdutoDto.tamanho);
    }

    public void deletarProdutoDaLista(CarrinhoProdutoDto carrinhoProdutoDto, HttpServerRequest request) {
        carrinhoProdutoMapper.deleter(carrinhoProdutoDto.getCarrinho_de_compras_id(), carrinhoProdutoDto.getProduto_id(), carrinhoProdutoDto.getTamanho());
        Logstore_standard_log logstore_standard_log =
                Logstore_standard_log
                        .builder()
                        .carrinho_compras_id(carrinhoProdutoDto.getCarrinho_de_compras_id())
                        .user_id(carrinhoProdutoDto.getUser_id())
                        .event_name("deletarProdutoDaLista")
                        .crud("d")
                        .target("user")
                        .produto_id(carrinhoProdutoDto.getProduto_id())
                        .ip(request.remoteAddress().host())
                        .action("delete")
                        .build();
        logstore_standard_log.persist();
    }
}
