package com.example.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;
    @JsonIgnore
    @OneToMany(mappedBy ="bloc")
    private Set<Chambre> chambres;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="idFoyer",nullable = false)
    private Foyer foyer;




}
