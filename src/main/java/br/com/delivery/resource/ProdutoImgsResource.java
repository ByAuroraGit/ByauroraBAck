package br.com.delivery.resource;

import br.com.delivery.entity.Produto;
import br.com.delivery.entity.Produto_imgs;
import br.com.delivery.entity.dto.ProdutoImgsDto;
import br.com.delivery.service.ProdutoImgsService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/arquivos")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ProdutoImgsResource {

    @Inject
    ProdutoImgsService produtoImgsService;

    @Context
    private ServletContext context;


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadFile(MultipartFormDataInput input, @QueryParam("id") Long id) throws IOException {
        return produtoImgsService.uploadArquivos(input,id);
    }



    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileWithGet(@QueryParam("id") Long id) {
       return produtoImgsService.download(id);
    }


}
