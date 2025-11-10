package com.ipn.mx.administracioneventos.core.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Evento", schema = "public")
public class Evento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvento", nullable = false)
    // SQL ==> idEvento serial primary key
    private Long idEvento;

    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "nombreEvento", length = 200, nullable = false, unique = true)
    private String nombreEvento;

    @NotEmpty(message = "No puede estar vacío")
    @Column(name = "descripcionEvento", length = 500, nullable = false)
    private String descripcionEvento;

    @NotNull(message = "La fecha no puede ser null")
    @FutureOrPresent(message = "La fecha de inicio deberá ser hoy o posterior")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio; // Cambiar el tipo de dato a LocalDate
    @NotNull(message = "La fecha no puede ser null")
    private Date fechaFin; // Cambiar el tipo de dato a LocalDate

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idEvento")
    @JsonManagedReference
    private List<Asistente> asistentes;

}
