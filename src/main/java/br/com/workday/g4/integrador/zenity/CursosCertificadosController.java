package br.com.workday.g4.integrador.zenity;

import br.com.workday.g4.integrador.zenity.contract.ContentResponseContract;
import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/neptune/v1")
public class CursosCertificadosController {

    private final ZenityService zenityService;

    public CursosCertificadosController(ZenityService zenityService) {
        this.zenityService = zenityService;
    }

    @GetMapping("person/{pessoaId}/license")
    public ResponseEntity<ContentResponseContract<CursosCertificadosContract>> getCustomerSkillsPaged(@PathVariable("pessoaId") String pessoaId,
                                                                                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                                                                                      @RequestParam(value = "size", defaultValue = "6") int size) throws IOException {

        var cursosCertificados = zenityService.recuperarCursosCertificadosPaginado(pessoaId,page,size);

        ContentResponseContract<CursosCertificadosContract> response = new ContentResponseContract<>(
                cursosCertificados.getContent(),
                cursosCertificados.getNumber(),
                cursosCertificados.getTotalElements(),
                cursosCertificados.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }
}
