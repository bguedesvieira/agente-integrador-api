package br.com.workday.g4.integrador.alura.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SubcategoriaCursoContract(
        String nome,
        String slug
) {
}
