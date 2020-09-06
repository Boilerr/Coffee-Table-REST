package com.boilerr.coffeetablerest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "log")
public class Log extends AuditModel{
    @Id
    @GeneratedValue(generator = "log_generator")
    @SequenceGenerator(
            name = "log_generator",
            sequenceName = "log_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String source;

    @NotBlank
    @Size(min = 3, max = 100)
    private String category;

    @NotBlank
    @Size(min = 3, max = 100)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

