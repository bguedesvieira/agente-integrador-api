package br.com.workday.g4.integrador.zenity;

import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import br.com.workday.g4.integrador.zenity.repository.ZenityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ZenityService {

    private final ZenityRepository zenityRepository;

    public ZenityService(ZenityRepository zenityRepository) {

        this.zenityRepository = zenityRepository;
    }

    public List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException {
        return zenityRepository.recuperarHabilidadesPessoa(pessoaId);
    }

    public Page<HabilidadesContract> recuperarHabilidadesPessoaPaginado(String pessoaId, int page, int size) throws IOException {

        var habilidades = recuperarHabilidadesPessoa(pessoaId);
        var pageable = PageRequest.of(page, size);
        return new PageImpl<>(extrairPagina(habilidades, page,size), pageable, habilidades.size());
    }

    public Page<CursosCertificadosContract> recuperarCursosCertificadosPaginado(String pessoaId, int page, int size) throws IOException {
        var cursosCertificados = zenityRepository.recuperarCursosCertificados(pessoaId);
        var pageable = PageRequest.of(page, size);
        return new PageImpl<>(extrairPagina(cursosCertificados, page,size), pageable, cursosCertificados.size());
    }

    private <T> List<T> extrairPagina(List<T> lista, int page, int size) {
        int start = (page - 1) * size;

        int end = Math.min(start + size, lista.size());

        if (start >= lista.size() || start < 0) {
            return new ArrayList<>();
        }

        return lista.subList(start, end);
    }
}
