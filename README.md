# Agendador de Tarefas - Usuários

API REST desenvolvida em Java com Spring Boot para gerenciamento de usuários, endereços e telefones, com autenticação e autorização utilizando JWT.

---

## 📋 Sobre o projeto

O projeto consiste em um serviço backend responsável pelo gerenciamento de usuários.

Atualmente, a aplicação permite:

- Cadastro de usuários;
- Autenticação de usuários;
- Geração de tokens JWT;
- Busca de usuários por e-mail;
- Atualização de dados do usuário;
- Exclusão de usuários;
- Cadastro de endereços;
- Atualização de endereços;
- Cadastro de telefones;
- Atualização de telefones;
- Criptografia de senhas utilizando BCrypt.

A aplicação utiliza uma arquitetura organizada em camadas, separando responsabilidades entre controllers, services, DTOs, converters, repositories, entidades, exceções e componentes de segurança.

---

## 🛠️ Tecnologias utilizadas

### Backend

- **Java 17**
- **Spring Boot**
- **Spring WebMVC**
- **Spring Data JPA**
- **Spring Security**
- **JWT**
- **Lombok**

### Banco de dados

- **PostgreSQL**

### Build e gerenciamento

- **Gradle**
- **Gradle Wrapper**

### Testes

- **JUnit**
- **Spring Boot Test**

### CI

- **GitHub Actions**

---

## 🏗️ Arquitetura

A aplicação está organizada seguindo uma separação de responsabilidades entre diferentes camadas:

```text
src/main/java/com/usuarios/
│
├── business/
│   ├── UsuarioService.java
│   ├── converter/
│   │   └── UsuarioConverter.java
│   │
│   └── dto/
│       ├── UsuarioDTO.java
│       ├── EnderecoDTO.java
│       └── TelefoneDTO.java
│
├── controller/
│   └── UsuarioController.java
│
└── infrastructure/
    ├── entity/
    │   ├── Usuario.java
    │   ├── Endereco.java
    │   └── Telefone.java
    │
    ├── exception/
    │   ├── ConflictException.java
    │   └── ResourceNotFoundException.java
    │
    ├── repository/
    │   ├── UsuarioRepository.java
    │   ├── EnderecoRepository.java
    │   └── TelefoneRepository.java
    │
    └── security/
        ├── JwtRequestFilter.java
        ├── JwtUtil.java
        ├── SecurityConfig.java
        └── UserDetailsServiceImpl.java
```

---

## Autor

Lucas Vinícius
