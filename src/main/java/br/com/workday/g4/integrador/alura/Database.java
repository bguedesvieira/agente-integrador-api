package br.com.workday.g4.integrador.alura;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Database {

    private static List<Course> courses = new ArrayList<>();

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void addAll(List<Course> newCourses) {
        courses.addAll(newCourses);
    }

    public static void clear() {
        courses = new ArrayList<>();
    }

    public static List<Course> findAllCoursesByLanguage(String courseLanguage) {

        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(courseLanguage) + "\\b", Pattern.CASE_INSENSITIVE);

        return courses.stream().filter(course -> pattern.matcher(course.getName()).find()).toList();
    }
}
