package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UsuarioDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.Usuario;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario getUsuarioById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Usuario addUsuario(UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = new Usuario(usuarioDTO);
        return repository.save(novoUsuario);
    }

//    public Usuario deleteUser(Long id){
//        if(repository.existsById(id)){
//            return repository.delete(id);
//        }
//        throw new EntityNotFoundException("Inexistente");
//    }
//
//    public Usuario putUser(Long id, UsuarioDTO usuarioDTO){
//        UsuarioDTO usuarioExistente = repository.findById(id).orElseThrow();
//        usuarioExistente.setNome(usuarioDTO.nome());
//        usuarioExistente.setEndereco(usuarioDTO.endereco());
//
//        return repository.save(usuarioExistente);
//    }


}
