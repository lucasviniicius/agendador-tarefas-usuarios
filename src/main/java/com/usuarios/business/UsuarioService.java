package com.usuarios.business;

import com.usuarios.business.converter.UsuarioConverter;
import com.usuarios.business.dto.UsuarioDTO;
import com.usuarios.infrastructure.entity.Usuario;
import com.usuarios.infrastructure.exception.ConflictException;
import com.usuarios.infrastructure.exception.ResourceNotFoundException;
import com.usuarios.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        if(usuarioRepository.existsByEmail(usuarioDTO.getEmail())){
            throw new ConflictException("Email já existe.");
        }

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Usuário não foi encontrado."));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
