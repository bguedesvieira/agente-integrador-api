package br.com.workday.g4.integrador.alura.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmentaCursoContract(
        @JsonProperty("capitulo")
        String capitulo,

        @JsonProperty("secoes")
        List<String> secoes
) {
}
