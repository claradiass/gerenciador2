package br.edu.ifpb.padroes.biblioteca.gerenciador.services;

import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UserRepository;
import br.edu.ifpb.padroes.biblioteca.gerenciador.services.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }


}
