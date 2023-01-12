package com.idat.gestionjalsuri.model.response;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GenericResponse {
    private String cod;
    private String mensage;
}
