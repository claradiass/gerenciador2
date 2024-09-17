package br.edu.ifpb.padroes.biblioteca.gerenciador.controllers;

import br.edu.ifpb.padroes.biblioteca.gerenciador.config.security.TokenService;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.AuthenticationDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.LoginResponseDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.dtos.UserRequestDTO;
import br.edu.ifpb.padroes.biblioteca.gerenciador.models.User;
import br.edu.ifpb.padroes.biblioteca.gerenciador.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO userDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userDTO.cpf(), userDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDTO userDTO) {
        if (this.repository.findByCpf(userDTO.cpf()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.senha());
        User newUser = new User(userDTO.nome(), userDTO.cpf(), encryptedPassword, userDTO.dataNascimento(), userDTO.endereco(), userDTO.cargo());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}