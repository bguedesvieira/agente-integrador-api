package br.com.workday.g4.integrador.alura.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoriaCursoContract(
        String nome,
        String slug
) {
}
