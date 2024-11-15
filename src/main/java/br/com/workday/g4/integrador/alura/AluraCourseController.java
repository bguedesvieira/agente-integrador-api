package br.com.workday.g4.integrador.alura;

import br.com.workday.g4.integrador.alura.contract.DetalheCursoContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alura/courses")
public class AluraCourseController {


    private final AluraFeignClient client;

    @Autowired
    public AluraCourseController(AluraFeignClient client) {
        this.client = client;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findCourseByLanguage(@RequestParam String language) {

        List<Course> courses = Database.findAllCoursesByLanguage(language);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseSlug}/detail")
    public ResponseEntity<DetalheCursoContract> getCoursesDetail(@PathVariable String courseSlug){
        Course course = Database.findCourseBySlug(courseSlug);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        var courseDetail = Database.findCourseDetailBySlug(courseSlug);
        if (courseDetail != null){
            return ResponseEntity.ok(courseDetail);
        }
        var details = client.getDetail(String.format("curso-%s",courseSlug));

        Database.addCouseDetail(courseSlug, details);

        return ResponseEntity.ok(details);
    }


}
