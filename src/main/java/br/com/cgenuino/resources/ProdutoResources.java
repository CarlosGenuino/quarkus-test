package br.com.cgenuino.resources;

import br.com.cgenuino.domain.Produto;
import br.com.cgenuino.dto.CriarProdutoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResources {

    @GET
    public List<Produto> listar() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void cadastrar(CriarProdutoDTO produtoDTO){
        Produto p = new Produto();
        p.setNome(produtoDTO.getNome());
        p.setValor(produtoDTO.getValor());
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, CriarProdutoDTO produtoDTO){
        Optional<Produto> p = Produto.findByIdOptional(id);
        if(p.isPresent()) {
            p.get().setValor(produtoDTO.getValor());
            p.get().persist();
        }else{
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id){
        Optional<Produto> p = Produto.findByIdOptional(id);
        if(p.isPresent()) {
            p.get().delete();
        }else{
            throw new NotFoundException();
        }
    }


}
