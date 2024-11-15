package br.com.workday.g4.integrador.zenity.repository;

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
    private final String caminhoExpertises;

    public ZenityRepositoryLocal(@Value("${aws.s3.mockzenity.caminho.habilidades}") String caminhoHabilidades,
                                 @Value("${aws.s3.mockzenity.caminho.cursos_certificados}") String caminhoCursosCertificados,
                                 @Value("${aws.s3.mockzenity.caminho.expertises}") String caminhoExpertises) {
        this.caminhoHabilidades = caminhoHabilidades;
        this.caminhoCursosCertificados = caminhoCursosCertificados;
        this.caminhoExpertises = caminhoExpertises;
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

    @Override
    public ExpertisesContract recuperarExpertizes(String pessoaId) throws IOException {
        LOGGER.info("recuperando expertises pessoa {} local", pessoaId);
        InputStream inputStream = getInputStream(pessoaId,this.caminhoExpertises);

        var expertises = new ObjectMapper().readValue(inputStream, ExpertisesContract.class);
        return expertises;
    }

    @Override
    public RemuneracaoContract recuperarRemuneracao(String pessoaId) throws IOException {
        LOGGER.info("recuperando remuneracao pessoa {} local", pessoaId);
        InputStream inputStream = getInputStream(pessoaId,this.caminhoExpertises);

        var remuneracao = new ObjectMapper().readValue(inputStream, RemuneracaoContract.class);
        return remuneracao;
    }
}
