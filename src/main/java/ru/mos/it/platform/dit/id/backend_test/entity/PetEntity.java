package ru.mos.it.platform.dit.id.backend_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "pet")
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class PetEntity {

    @Id
    @SequenceGenerator(name = "pet_id_seq", sequenceName = "pet_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pet_id_seq")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PetStatus status;

}
