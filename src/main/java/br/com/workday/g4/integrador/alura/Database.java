package br.com.workday.g4.integrador.alura;


import br.com.workday.g4.integrador.alura.contract.DetalheCursoContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Database {

    private static Map<String, Course> courses = new HashMap<>();
    private static Map<String, DetalheCursoContract> coursesDetail = new HashMap<>();

    public static void addCourse(Course course) {
        courses.put(course.getSlug(),course);
    }

    public static void addAll(List<Course> newCourses) {

        newCourses.forEach(course -> courses.put(course.getSlug(), course));
    }

    public static void clear() {
        courses = new HashMap<>();
        coursesDetail = new HashMap<>();
    }

    public static List<Course> findAllCoursesByLanguage(String courseLanguage) {

        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(courseLanguage) + "\\b", Pattern.CASE_INSENSITIVE);

        return courses.values().stream().filter(course -> pattern.matcher(course.getName()).find()).toList();
    }

    public static Course findCourseBySlug(String slug){
        return courses.get(slug);
    }

    public static DetalheCursoContract findCourseDetailBySlug(String slug){
        return coursesDetail.get(slug);
    }

    public static void addCouseDetail(String courseSlug, DetalheCursoContract details) {
        coursesDetail.put(courseSlug, details);
    }
}
