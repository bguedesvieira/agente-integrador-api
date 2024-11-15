package br.com.workday.g4.integrador.zenity;

import br.com.workday.g4.integrador.zenity.contract.ContentResponseContract;
import br.com.workday.g4.integrador.zenity.contract.CursosCertificadosContract;
import br.com.workday.g4.integrador.zenity.contract.RemuneracaoContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/neptune/v1/person/{pessoaId}")
public class ZenityNeptuneController {

    private final ZenityService zenityService;

    public ZenityNeptuneController(ZenityService zenityService) {
        this.zenityService = zenityService;
    }

    @GetMapping("/license")
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

    @GetMapping("/remuneration")
    public ResponseEntity<RemuneracaoContract> getRemuneracao(@PathVariable("pessoaId") String pessoaId) throws IOException {
        var remuneracaco = zenityService.recuperarRemuneracao(pessoaId);

        return ResponseEntity.ok(remuneracaco);
    }


}
