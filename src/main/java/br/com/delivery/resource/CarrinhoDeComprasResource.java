package br.com.delivery.resource;

import br.com.delivery.entity.CarrinhoDeCompras;
import br.com.delivery.entity.CarrinhoProduto;
import br.com.delivery.entity.Produto;
//import io.quarkus.hibernate.reactive.panache.common.WithSession;
import br.com.delivery.entity.dto.CarrinhoDto;
import br.com.delivery.service.CarrinhoDeComprasService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
//import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.logging.annotations.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("v1/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoDeComprasResource {

    @Inject
    CarrinhoDeComprasService carrinhoDeComprasService;

    @GET
    @Path("{id}")
    public CarrinhoDeCompras getById(@PathParam("id") Long id) {
        CarrinhoDeCompras carrinhoDeCompras = CarrinhoDeCompras.findById(id);
        carrinhoDeCompras.carrinho_produto.forEach(CarrinhoProduto::calcularTotal);
        carrinhoDeCompras.calculatedCarTotalAndQtd();
        return carrinhoDeCompras;
    }

    @GET
    @Path("all")
    public List<CarrinhoDeCompras> getAll(@QueryParam("tamPag") int tamanhoPagina, @QueryParam("pagina") int paginas){
        tamanhoPagina = 0;
        paginas = 5;
        List<CarrinhoDeCompras> carrinhoDeCompras = carrinhoDeComprasService.findAllCars(paginas, tamanhoPagina);
        carrinhoDeCompras.forEach(CarrinhoDeCompras::calculatedCarTotalAndQtd);
        return carrinhoDeCompras;
    }

    @POST
    @Path("/createcars")
    @Transactional
    public CarrinhoDeCompras createCar(@Context HttpServerRequest httpServerRequest) {
        return carrinhoDeComprasService.createCar(httpServerRequest);
    }

}
