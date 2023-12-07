package br.com.delivery.entity.dto;

import br.com.delivery.entity.Produto;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.*;

import java.io.InputStream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoImgsDto {


    public byte[] file;

    public String fileName;

    public String fileType;

    public Long produto_id;
}
