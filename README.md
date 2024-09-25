# Gerenciador de Biblioteca


## Resumo

Esta API de gerenciamento de biblioteca foi desenvolvida como parte da disciplina de **Padrões de Projeto**, com foco na estudo de padrões como o **Chain of Responsibility**, além de seguir os padrões comuns do **Spring Boot**. A API permite o gerenciamento de usuários, livros e empréstimos de forma segura, utilizando autenticação baseada em **JWT**. A estrutura foi projetada para diferenciar permissões entre **admin** e **usuários comuns**, com endpoints protegidos por cargos.

## Recursos Disponíveis

- Autenticação e autorização baseada em **JWT**.
- Gerenciamento completo de livros, usuários e empréstimos.
- Sistema de empréstimo e devolução de livros com controle de penalidades por atraso.
- Padrão **Chain of Responsibility** para lidar com regras de negócios relacionadas a empréstimos.
- **Admin** tem controle total sobre a aplicação, enquanto os **usuários comuns** têm acesso limitado às funcionalidades.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)


## Endpoints Principais

### Autenticação

- `POST /auth/login`
    - Autentica o usuário e retorna um token JWT.
    - **Payload**: 
        ```
		{  
			"cpf": "00000000000", 
			"senha": "minhaSenha" 
		}
	  ```
    - **Permissão**: Aberto (público)

- `POST /auth/register`
    - Registra um novo usuário.
    - **Payload**: 
	    ```
		{ 
			"nome": "Novo Usuário", 
			"endereco": "Rua Fulano Sicrano", 
			"cpf": "00000000000", 
			"dataNascimento": "1999-01-01", 
			"senha": "minhaSenha", 
			"cargo": "USER" 
		}
	  ```
    - **Permissão**: Aberto (público)

### Usuários

- `GET /usuario/all`
    - Lista todos os usuários.
    - **Permissão**: Apenas **ADMIN**.

- `GET /usuario/{id}`
    - Detalha as informações de um usuário específico.
    - **Permissão**: Apenas **ADMIN**.

### Livros

- `POST /livro/create`
    - Cadastra um novo livro.
    - **Payload**: 
	   ```
	  { 
			"titulo": "Exemplo de Título", 
			"isbn": "978-3-16-148410-0", 
			"sinopse": "Uma breve descrição do livro.",
			"quantidade": 10, 
			"dataPublicacao": "2023-09-25", 
			"autores": [1, 2, 3], 
			"generos": [1, 2] 
	  }
	  ```
    - **Permissão**: Apenas **ADMIN**.

- `PUT /livro/update/{id}`
    - Atualiza informações de um livro.
    - **Permissão**: Apenas **ADMIN**.

- `DELETE /livro/delete/{id}`
    - Remove um livro da biblioteca.
    - **Permissão**: Apenas **ADMIN**.

### Empréstimos

- `POST /emprestimo/create`
    - Cria um novo empréstimo de livro.
    - **Payload**: 
	    ```
		{ 
			"usuarioId": 123, 
			"livroId": 456, 
			"dataEmprestimo": "2024-09-25",
			"dataEntregaPrevista": "2024-10-05" 
		}
	  ```
    - **Permissão**: Apenas **ADMIN**.

- `GET /emprestimo/{id}`
    - Retorna os detalhes de um empréstimo.
    - **Permissão**: Apenas **ADMIN**.

- `GET /emprestimo/all`
    - Lista todos os empréstimos.
    - **Permissão**: Apenas **ADMIN**.

- `PUT /emprestimo/update/{id}`
    - Atualiza um empréstimo.
    - **Permissão**: Apenas **ADMIN**.

- `PUT /emprestimo/refund/{id}`
    - Marca o empréstimo como devolvido.
    - **Permissão**: Apenas **ADMIN**.

- `PUT /emprestimo/payment/{id}`
    - Registra o pagamento de uma multa.
    - **Permissão**: Apenas **ADMIN**.

- `DELETE /emprestimo/delete/{id}`
    - Remove um empréstimo.
    - **Permissão**: Apenas **ADMIN**.

## Estrutura de Pastas

```bash
src
 └── main
     ├── java
     │   └── br.edu.ifpb.padroes.biblioteca.gerenciador
     │       ├── config/security
     │       ├── controllers
     │       ├── dtos
     │       ├── models
     │       ├── repositories
     │       ├── services
     │       └── validators
     └── resources
         └── application.properties
```

## Nosso Time

<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/claradiass">
        <img src="https://avatars.githubusercontent.com/u/139878034?v=4" alt="Avatar Clara"/><br>
        <sub>
          <b>Ana Clara</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/anielmelo">
        <img src="https://avatars.githubusercontent.com/u/103321497?v=4" alt="Avatar Aniel"/><br>
        <sub>
          <b>Aniel Melo</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/dinizhugo">
        <img src="https://avatars.githubusercontent.com/u/144050489?v=4" alt="Avatar Hugo"/><br>
        <sub>
          <b>Hugo Diniz</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

---
