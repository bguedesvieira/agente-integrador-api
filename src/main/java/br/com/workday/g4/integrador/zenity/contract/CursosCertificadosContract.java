package br.com.workday.g4.integrador.zenity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record CursosCertificadosContract (
    int id,
    String title,
    String category,
    String institution,
    String conclusionDate,
    String createdAt,
    String version,
    String personId,
    int year,
    String link
)
{}
