/*
 * @fileoverview {CategoriaDTO} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {CategoriaDTO} fue realizada el 31/07/2022.
 * @Dev - La primera version de {CategoriaDTO} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.definicion.servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code CategoriaDTO}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CategoriaDTO {

    private Long intIdCategoria;
    private String strCategoria;
    private Integer intPesoMaximo;
    private Integer intTolerancia;
    private String strDescripcion;
    private Integer intEjeSencillo;
    private Integer intEjeTandem;
    private Integer intTotalEjes;

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        return "CategoriaDTO{"
                + "intIdCategoria=" + intIdCategoria
                + ", strCategoria='" + strCategoria + '\''
                + ", intPesoMaximo=" + intPesoMaximo
                + ", intTolerancia=" + intTolerancia
                + ", strDescripcion='" + strDescripcion + '\''
                + ", intEjeSencillo=" + intEjeSencillo
                + ", intEjeTandem=" + intEjeTandem
                + ", intTotalEjes=" + intTotalEjes
                + '}';
    }

}
