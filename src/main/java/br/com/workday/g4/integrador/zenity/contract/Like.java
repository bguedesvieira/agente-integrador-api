package br.com.workday.g4.integrador.zenity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Like(
        int count,
        boolean isLiked,
        boolean liked
) {
}
