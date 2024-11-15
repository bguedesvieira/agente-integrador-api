package br.com.workday.g4.integrador.alura.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetalheCursoContract(
    String metadescription,
    @JsonProperty("quantidade_aulas")
    int quantidadeAulas,
    @JsonProperty("minutos_video")
    int minutosEmVideo,

    @JsonProperty("carga_horaria")
    int cargaHoraria,

    @JsonProperty("data_criacao")
    LocalDate dataCriacao,

    @JsonProperty("publico_alvo")
    String publicoAlvo,

    @JsonProperty("chamadas")
    List<String> chamadas,

    @JsonProperty("ementa")
    List<EmentaCursoContract> ementa

)  {}
