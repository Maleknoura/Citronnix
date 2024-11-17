package org.wora.citronnix.farm.domain.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.citronnix.farm.domain.valueObject.Superficie;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String localisation;

    @Embedded
    private Superficie superficie;

    @NotNull
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    @Size(max = 10)
    private Set<Field> fields = new HashSet<>();
}