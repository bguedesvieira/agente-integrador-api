package br.com.workday.g4.integrador.zenity.repository;

import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Profile("default")
@Component
public class ZenityRepositoryLocal implements ZenityRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZenityRepositoryLocal.class);

    private final String caminhoHabilidades;
    private final String caminhoCursosCertificados;

    public ZenityRepositoryLocal(@Value("${aws.s3.mockzenity.caminho.habilidades}") String caminhoHabilidades,
                                 @Value("${aws.s3.mockzenity.caminho.cursos_certificados}") String caminhoCursosCertificados) {
        this.caminhoHabilidades = caminhoHabilidades;
        this.caminhoCursosCertificados = caminhoCursosCertificados;
    }

    @Override
    public List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException {
        LOGGER.info("recuperando habilidades pessoa {} local", pessoaId);
        InputStream inputStream = getInputStream(pessoaId,this.caminhoHabilidades);

        var habilidades = new ObjectMapper().readValue(inputStream,
                new TypeReference<List<HabilidadesContract>>() {});
        return habilidades;
    }

    private InputStream getInputStream(String pessoaId, String caminho) throws IOException {
        String filePath = "dados/" + String.format(caminho, pessoaId);

        InputStream inputStream = new ClassPathResource(filePath).getInputStream();
        return inputStream;
    }

    @Override
    public List<CursosCertificadosContract> recuperarCursosCertificados(String pessoaId) throws IOException {
        LOGGER.info("recuperando certificados pessoa {} local", pessoaId);
        InputStream inputStream = getInputStream(pessoaId,this.caminhoCursosCertificados);
        var cursosCertificados = new ObjectMapper().readValue(inputStream,
                new TypeReference<List<CursosCertificadosContract>>(){});
        return cursosCertificados;
    }
}
