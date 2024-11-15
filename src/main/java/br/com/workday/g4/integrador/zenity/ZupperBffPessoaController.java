package br.com.workday.g4.integrador.zenity;

import br.com.workday.g4.integrador.zenity.contract.ContentResponseContract;
import br.com.workday.g4.integrador.zenity.contract.ExpertisesContract;
import br.com.workday.g4.integrador.zenity.contract.HabilidadesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/zupper-bff/v1/customers/{pessoaId}")
public class ZupperBffPessoaController {

    private final ZenityService zenityService;

    @Autowired
    public ZupperBffPessoaController(ZenityService habilidadesService) {
        this.zenityService = habilidadesService;
    }

    @GetMapping("/skills/paged")
    public ResponseEntity<ContentResponseContract<HabilidadesContract>> getCustomerSkillsPaged(
            @PathVariable("pessoaId") String pessoaId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "6") int size) throws IOException {


        var habilidades = zenityService.recuperarHabilidadesPessoaPaginado(pessoaId,page,size);

        ContentResponseContract<HabilidadesContract> response = new ContentResponseContract<>(
                habilidades.getContent(),
                habilidades.getNumber(),
                habilidades.getTotalElements(),
                habilidades.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/skills")
    public ResponseEntity<List<HabilidadesContract>> getCustomerSkills(
            @PathVariable("pessoaId") String pessoaId) throws IOException {

        return ResponseEntity.ok(zenityService.recuperarHabilidadesPessoa(pessoaId));
    }

    @GetMapping("/expertises")
    public ResponseEntity<ExpertisesContract> getExpertises(@PathVariable("pessoaId") String pessoaId) throws IOException {
        return ResponseEntity.ok(zenityService.recuperarExpertizes(pessoaId));
    }

}
