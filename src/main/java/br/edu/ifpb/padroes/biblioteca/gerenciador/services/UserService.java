package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UserRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UserRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User getUsuarioById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public User addUsuario(UserRequestDTO userRequestDTO) {
        User novoUsuario = new User(userRequestDTO);
        return repository.save(novoUsuario);
    }


}
