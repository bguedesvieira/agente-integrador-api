package br.com.workday.g4.integrador.zenity.repository;

import br.com.workday.g4.integrador.aws.service.S3Service;
import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.ExpertisesContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import br.com.workday.g4.integrador.zenity.contract.RemuneracaoContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Profile("!default")
@Component
public class ZenityRepositoryS3 implements ZenityRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZenityRepositoryS3.class);
    private final String nomeBucket;
    private final S3Service s3Service;
    private final String caminhoHabilidades;
    private final String caminhoCursosCertificados;
    private final String caminhoExpertises;
    private final String caminhoRemuneracao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ZenityRepositoryS3(@Value("${aws.s3.bucket.mockzenity}") String nomeBucket,
                              @Value("${aws.s3.mockzenity.caminho.habilidades}") String caminhoHabilidades,
                              S3Service s3Service,
                              @Value("${aws.s3.mockzenity.caminho.cursos_certificados}") String caminhoCursosCertificados,
                              @Value("${aws.s3.mockzenity.caminho.expertises}") String caminhoExpertises,
                              @Value("${aws.s3.mockzenity.caminho.remuneracao}") String caminhoRemuneracao) {
        this.nomeBucket = nomeBucket;
        this.s3Service = s3Service;
        this.caminhoHabilidades = caminhoHabilidades;
        this.caminhoCursosCertificados = caminhoCursosCertificados;
        this.caminhoExpertises = caminhoExpertises;
        this.caminhoRemuneracao = caminhoRemuneracao;
    }

    @Override
    public List<HabilidadesContract> recuperarHabilidadesPessoa(String pessoaId) throws IOException {
        LOGGER.info("recuperando habilidades pessoa {} s3", pessoaId);
        return recuperarDados(pessoaId, caminhoHabilidades,
                new TypeReference<List<HabilidadesContract>>() {});
    }

    @Override
    public List<CursosCertificadosContract> recuperarCursosCertificados(String pessoaId) throws IOException {
        LOGGER.info("recuperando certificados pessoa {} s3", pessoaId);
        return recuperarDados(pessoaId, caminhoCursosCertificados,
                new TypeReference<List<CursosCertificadosContract>>() {});
    }

    @Override
    public ExpertisesContract recuperarExpertizes(String pessoaId) throws IOException{
        LOGGER.info("recuperando expertises pessoa {} s3", pessoaId);
        return recuperarDados(pessoaId, caminhoExpertises,ExpertisesContract.class);
    }

    @Override
    public RemuneracaoContract recuperarRemuneracao(String pessoaId) throws IOException {
        LOGGER.info("recuperando remuneracao pessoa {} s3", pessoaId);
        return recuperarDados(pessoaId, caminhoRemuneracao, RemuneracaoContract.class);
    }

    protected <T> T recuperarDados(String pessoaId, String caminho, TypeReference<T> typeReference) throws IOException {
        LOGGER.info("Recuperando dados da pessoa {} do caminho {}", pessoaId, caminho);
        InputStream inputStream = s3Service.getFile(this.nomeBucket, String.format(caminho, pessoaId));
        return objectMapper.readValue(inputStream, typeReference);
    }

    protected <T> T recuperarDados(String pessoaId, String caminho, Class<T> clazz) throws IOException {
        LOGGER.info("Recuperando dados da pessoa {} do caminho {}", pessoaId, caminho);
        InputStream inputStream = s3Service.getFile(this.nomeBucket, String.format(caminho, pessoaId));
        return objectMapper.readValue(inputStream, clazz);
    }

}
