package br.com.delivery.resource;

import br.com.delivery.entity.Usuario;
import br.com.delivery.entity.dto.UsuarioDto;
import br.com.delivery.exception.Result;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("v1/usuarios")
public class UsuarioResource {


    @GET
    @Path("all")
    public List<Usuario> listAll() {
        return Usuario.findAllUsers();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Result createUserWithCarrinho(@Valid UsuarioDto usuarioDto){
         try {
              Usuario.createUsuarioWithCarrinho(usuarioDto);
              return new Result("Usuário Criado com sucesso");

         } catch (ConstraintViolationException e) {
            return new Result(e.getConstraintViolations());
         }
    }
}
