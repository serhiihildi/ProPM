package com.hildi.propm.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "project_roles", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();

    public Project(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(roles, project.roles) && Objects.equals(tasks, project.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, roles, tasks);
    }
}
