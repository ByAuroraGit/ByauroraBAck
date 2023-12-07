package br.com.delivery.service;

import br.com.delivery.entity.Produto;
import br.com.delivery.entity.Produto_imgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProdutoImgsService {

    public Produto_imgs findBywithId(Long id){
        return Produto_imgs.find("id",id).firstResult();
    }

    public Response uploadArquivos(MultipartFormDataInput input, Long id) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);

                String[] file_type = fileName.trim().split("\\.");

                Produto_imgs produtoImgs = new Produto_imgs();
                produtoImgs.fileName = fileName;
                produtoImgs.file_type = file_type[1];
                produtoImgs.grpData = bytes;
                produtoImgs.produto = Produto.findBywithId(id);
                produtoImgs.persist();

            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(500).entity("Erro ao carregar o arquivo.").build();
            }
        }
        return Response.status(200).entity("Arquivos enviados com sucesso").build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    public Response download(Long id) {
        Produto_imgs produtoImgs = findBywithId(id);
        byte[] fileData = produtoImgs.grpData;

        String contentType = "image/"+produtoImgs.file_type;
        Response.ResponseBuilder response = Response.ok(new ByteArrayInputStream(fileData), contentType);
//        response.header("Content-Disposition");
        return response.build();
    }

}
