package br.com.workday.g4.integrador.alura;

import br.com.workday.g4.integrador.alura.contract.DetalheCursoContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "Alura-public-API", url = "https://www.alura.com.br/api/")
public interface AluraFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "cursos")
    List<Course> getCourses();

    @RequestMapping(method = RequestMethod.GET, value = "/{courseSlug}")
    DetalheCursoContract getDetail(@PathVariable("courseSlug")String courseSlug);

}