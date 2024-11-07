package br.com.workday.g4.integrador.alura;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {

    @JsonProperty("tempo_estimado")
    private int duration;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("nome")
    private String name;

    public Course() {
    }

    public Course(int duration, String slug, String name) {
        this.duration = duration;
        this.slug = slug;
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }
}