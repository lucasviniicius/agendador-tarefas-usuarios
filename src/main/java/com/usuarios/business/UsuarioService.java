package com.usuarios.business;

import com.usuarios.business.converter.UsuarioConverter;
import com.usuarios.business.dto.EnderecoDTO;
import com.usuarios.business.dto.TelefoneDTO;
import com.usuarios.business.dto.UsuarioDTO;
import com.usuarios.infrastructure.entity.Endereco;
import com.usuarios.infrastructure.entity.Telefone;
import com.usuarios.infrastructure.entity.Usuario;
import com.usuarios.infrastructure.exception.ConflictException;
import com.usuarios.infrastructure.exception.ResourceNotFoundException;
import com.usuarios.infrastructure.repository.EnderecoRepository;
import com.usuarios.infrastructure.repository.TelefoneRepository;
import com.usuarios.infrastructure.repository.UsuarioRepository;
import com.usuarios.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        if(usuarioRepository.existsByEmail(usuarioDTO.getEmail())){
            throw new ConflictException("Email já existe.");
        }

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public UsuarioDTO buscaUsuarioPorEmail(String email){
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(
                "Usuário não foi encontrado."
        ));

        return usuarioConverter.paraUsuarioDTO(usuarioEntity);
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO usuarioDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(
                "Usuário não foi encontrado."
        ));

        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public EnderecoDTO atualizaEndereco(Long id, EnderecoDTO enderecoDTO){
        Endereco enderecoEntity = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Endereço não foi encontrado."
        ));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, enderecoEntity);
        endereco = enderecoRepository.save(endereco);
        return usuarioConverter.paraEnderecoDTO(endereco);
    }

    public TelefoneDTO atualizaTelefone(Long id, TelefoneDTO telefoneDTO){
        Telefone telefoneEntity = telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Telefone não foi encontrado."
        ));

        Telefone telefone = usuarioConverter.updateTelefone(telefoneDTO, telefoneEntity);
        telefone = telefoneRepository.save(telefone);
        return usuarioConverter.paraTelefoneDTO(telefone);
    }
}
