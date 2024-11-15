package br.com.workday.g4.integrador.zenity.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public record ContentResponseContract<T>(List<T> content,int page,  long totalElements, int totalPages) {}