package com.robson.desafiogreenmile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class HoraTrabalhada implements Serializable {

  private static final long serialVersionUID = -192174945664887574L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @Column(unique = true, nullable = false)
  private LocalDate data;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime horaEntrada;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  private LocalTime horaSaida;

  private Long horasTrabalhadas;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
}
