package org.wora.citronnix.shared.service;

import java.util.List;

public interface GenericService<T, ID, RequestDTO, ResponseDTO> {
    ResponseDTO findById(ID id);
    List<ResponseDTO> findAll();
    ResponseDTO save(RequestDTO requestDTO);
    void deleteById(ID id);
}

