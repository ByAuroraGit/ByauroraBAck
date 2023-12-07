package br.com.delivery.entity;

import br.com.delivery.entity.dto.ProdutoImgsDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import lombok.*;
import org.jboss.resteasy.annotations.providers.multipart.PartType;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@Table(name = "produto_imgs")
public class Produto_imgs extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "grp_Data")
    @JsonbTransient
    public byte[] grpData;

    @Column(name = "file_name")
    public String fileName;
    @Column(name = "file_type")
    public String  file_type;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonbTransient
    public Produto produto;


    public static Produto_imgs findByWithFileName(String file_name){
        return find("file_name", file_name).firstResult();
    }


}
