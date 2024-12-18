package br.com.workday.g4.integrador.zenity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record HabilidadesContract(
        int id,
        int skillId,
        String name,
        int rating,
        String type,
        Like like
) {}

