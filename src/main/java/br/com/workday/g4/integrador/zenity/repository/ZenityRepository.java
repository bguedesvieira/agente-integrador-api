package br.com.workday.g4.integrador.zenity.repository;

import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.ExpertisesContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import br.com.workday.g4.integrador.zenity.contract.RemuneracaoContract;

import java.io.IOException;
import java.util.List;

public interface ZenityRepository {
    List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException;

    List<CursosCertificadosContract> recuperarCursosCertificados(String pessoaId) throws IOException;

    ExpertisesContract recuperarExpertizes(String pessoaId) throws IOException;

    RemuneracaoContract recuperarRemuneracao(String pessoaId) throws IOException;
}
