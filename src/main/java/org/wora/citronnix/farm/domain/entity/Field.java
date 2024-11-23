package org.wora.citronnix.farm.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.wora.citronnix.farm.domain.valueObject.Superficie;
import org.wora.citronnix.tree.domain.entity.Tree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @Embedded
    private Superficie superficie;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Tree> trees = new ArrayList<>();



}

