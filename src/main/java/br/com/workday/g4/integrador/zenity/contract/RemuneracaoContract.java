package br.com.workday.g4.integrador.zenity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record RemuneracaoContract (
    String level,
    String occupationName,
    String firstExpertise,
    String secondExpertise
){}
