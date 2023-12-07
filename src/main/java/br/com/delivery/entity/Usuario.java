package br.com.delivery.entity;

import br.com.delivery.entity.dto.UsuarioDto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Cacheable
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String nome;
    @NotNull
    public String cpf;
    @NotNull
    public LocalDate dataNascimento;
    @NotNull
    public String cidade;
    @NotNull
    public String longradouro;
    @NotNull
    public String longradouroNumero;
    @NotNull
    public String telefone;
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
//    public List<CarrinhoDeCompras> carrinhos_do_usuario;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @CreationTimestamp
    public ZonedDateTime createAt;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @UpdateTimestamp
    public ZonedDateTime updateAt;


    public static List<Usuario> findAllUsers(){
        return  findAll().list();
    }

    public static void createUsuarioWithCarrinho(UsuarioDto usuarioDto){
        //criando usuario
//        Usuario usuario = new Usuario();
//        usuario = usuario.builder()
//                .cidade(usuarioDto.getCidade())
//                .cpf(usuarioDto.getCpf())
//                .dataNascimento(usuarioDto.getDataNascimento())
//                .longradouro(usuarioDto.getLongradouro())
//                .longradouroNumero(usuarioDto.getLongradouroNumero())
//                .nome(usuarioDto.getNome())
//                .telefone(usuarioDto.getTelefone())
//                .build();
//        usuario.persist();

        CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
        carrinhoDeCompras = carrinhoDeCompras
                .builder()
                .nome("carrinho" )
                .qtdProdutos(0)
                .carTotal(0)
//                .usuario(usuario)
                .build();

        carrinhoDeCompras.persist();

    }



}
