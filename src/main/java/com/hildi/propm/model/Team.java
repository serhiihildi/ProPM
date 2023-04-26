package com.hildi.propm.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "team")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

}

