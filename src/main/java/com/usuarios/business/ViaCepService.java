package com.usuarios.business;

import com.usuarios.infrastructure.clients.ViaCepClient;
import com.usuarios.infrastructure.clients.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
// 26:00
@Service
@RequiredArgsConstructor
public class ViaCepService {
    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscarDadosEndereco(String cep){
        return viaCepClient.buscaDadosEndereco(processarCep(cep));
    }

    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ", "").replace("-", "");

        if(!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentException("CEP com caracteres inválido.");
        }

        return cepFormatado;
    }
}
