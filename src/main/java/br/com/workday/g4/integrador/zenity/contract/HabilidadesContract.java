package br.com.workday.g4.integrador.zenity.contract;

public record HabilidadesContract(
        int id,
        int skillId,
        String name,
        int rating,
        String type,
        Like like
) {}

