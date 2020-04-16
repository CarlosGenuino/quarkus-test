package br.com.cgenuino.resources;

import br.com.cgenuino.domain.User;
import br.com.cgenuino.dto.CriarUserDTO;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    @GET
    public List<User> listar() {
        return User.listAll();
    }

    @GET
    @Path("{id}")
    public User buscarPorId(@PathParam("id") Long id){
        Optional<User> opt = User.findByIdOptional(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new NotFoundException();
    }


    @POST
    @Transactional
    public void cadastrar(CriarUserDTO UserDTO){
        User p = new User();
        p.setNome(UserDTO.getNome());
        p.setEmail(UserDTO.getEmail());
        p.setPassword(UserDTO.getPassword());
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, CriarUserDTO UserDTO){
        Optional<User> p = User.findByIdOptional(id);
        if(p.isPresent()) {
            p.get().setNome(UserDTO.getNome());
            p.get().persist();
        }else{
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id){
        Optional<User> p = User.findByIdOptional(id);
        if(p.isPresent()) {
            p.get().delete();
        }else{
            throw new NotFoundException();
        }
    }


}
