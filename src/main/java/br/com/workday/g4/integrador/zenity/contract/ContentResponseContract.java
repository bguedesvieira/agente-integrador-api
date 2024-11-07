package br.com.workday.g4.integrador.zenity.contract;

import java.util.List;


public record ContentResponseContract<T>(List<T> content,int page,  long totalElements, int totalPages) {}