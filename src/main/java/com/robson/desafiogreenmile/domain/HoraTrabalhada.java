package com.robson.desafiogreenmile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoraTrabalhada implements Serializable {

  private static final long serialVersionUID = -192174945664887574L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @Column(unique = true, nullable = false)
  private LocalDate data;

  // TODO: 16/03/19
  // Dividir quantidade em horas e minutos
  private Integer quantidade;

  @ManyToOne
  @JsonManagedReference
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
}
