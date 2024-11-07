package br.com.workday.g4.integrador.alura;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class AluraCourseController {


    @GetMapping
    public ResponseEntity<List<Course>> findCourseByLanguage(@RequestParam String language) {

        List<Course> courses = Database.findAllCoursesByLanguage(language);
        return ResponseEntity.ok(courses);
    }
}
