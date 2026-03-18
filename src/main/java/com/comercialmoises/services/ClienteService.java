package com.comercialmoises.services;

import com.comercialmoises.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listar();

    ClienteDTO buscarPorId(Integer id);

    ClienteDTO guardar(ClienteDTO dto);

    ClienteDTO actualizar(Integer id, ClienteDTO dto);

    void eliminar(Integer id);
}
