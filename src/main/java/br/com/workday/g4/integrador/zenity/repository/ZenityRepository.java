package br.com.workday.g4.integrador.zenity.repository;

import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;

import java.io.IOException;
import java.util.List;

public interface ZenityRepository {
    List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException;

    List<CursosCertificadosContract> recuperarCursosCertificados(String pessoaId) throws IOException;
}
