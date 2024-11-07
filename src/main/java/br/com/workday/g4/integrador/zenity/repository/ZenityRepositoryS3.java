package br.com.workday.g4.integrador.zenity.repository;

import br.com.workday.g4.integrador.aws.service.S3Service;
import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Profile("!default")
@Component
public class ZenityRepositoryS3 implements ZenityRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZenityRepositoryS3.class);
    private final String nomeBucket;
    private final S3Service s3Service;
    private final String caminhoHabilidades;
    private final String caminhoCursosCertificados;

    public ZenityRepositoryS3(@Value("${aws.s3.bucket.mockzenity}") String nomeBucket,
                              @Value("${aws.s3.mockzenity.caminho.habilidades}") String caminhoHabilidades,
                              S3Service s3Service,
                              @Value("${aws.s3.mockzenity.caminho.cursos_certificados}")String caminhoCursosCertificados) {
        this.nomeBucket = nomeBucket;
        this.s3Service = s3Service;
        this.caminhoHabilidades = caminhoHabilidades;
        this.caminhoCursosCertificados = caminhoCursosCertificados;
    }

    @Override
    public List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException {
        LOGGER.info("recuperando habilidades pessoa {} s3", pessoaId);
        var inputStream = s3Service.getFile(this.nomeBucket, String.format(caminhoHabilidades, pessoaId));
        var habilidades = new ObjectMapper().readValue(inputStream,
                new TypeReference<List<HabilidadesContract>>() {});
        return habilidades;
    }

    @Override
    public List<CursosCertificadosContract> recuperarCursosCertificados(String pessoaId) throws IOException {
        LOGGER.info("recuperando certificados pessoa {} s3", pessoaId);
        var inputStream = s3Service.getFile(this.nomeBucket, String.format(this.caminhoCursosCertificados,pessoaId));
        var cursosCertificados = new ObjectMapper().readValue(inputStream,
                new TypeReference<List<CursosCertificadosContract>>(){});
        return cursosCertificados;
    }
}
