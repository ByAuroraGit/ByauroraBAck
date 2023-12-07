package br.com.delivery.service;

import br.com.delivery.entity.CarrinhoDeCompras;
import br.com.delivery.entity.Logstore_standard_log;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;

import java.util.List;

@ApplicationScoped
public class CarrinhoDeComprasService {


    @Inject
    Logstore_standard_logService logstoreStandardLogService;

    public  List<CarrinhoDeCompras> findAllCars(int tamanhoPagina, int pagina) {
        return CarrinhoDeCompras.findAll()
                .range(pagina,tamanhoPagina)
                .list();
    }



    public  CarrinhoDeCompras createCar(@Context HttpServerRequest httpServerRequest) {
        // criando carrinho do usuario
        CarrinhoDeCompras carrinhoDeCompras = CarrinhoDeCompras
                .builder()
                .nome("carrinho do" + " " )
                .qtdProdutos(0)
                .carTotal(0)
//                .usuario(usuario)
                .build();
        carrinhoDeCompras.persist();

        this.logstoreStandardLogService
                .create(
                        Logstore_standard_log
                                .builder()
                                .carrinho_compras_id(carrinhoDeCompras.id)
                                .action("create")
                                .crud("c")
                                .event_name("createCar")
                                .ip(httpServerRequest.remoteAddress().host())
                                .build());
        return carrinhoDeCompras;
    }
}
