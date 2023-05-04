package com.hildi.propm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Task(Long id, String name, String description, Project project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(project, task.project) && role == task.role && Objects.equals(createdDate, task.createdDate) && Objects.equals(updatedDate, task.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, project, role, createdDate, updatedDate);
    }
}
