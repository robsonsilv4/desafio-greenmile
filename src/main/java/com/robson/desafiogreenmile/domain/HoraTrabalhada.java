package com.robson.desafiogreenmile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

  @NotEmpty
  @JsonFormat(pattern = "dd/MM/yyyy")
  @NotEmpty(message = "Preenchimento obrigatório!")
  private LocalDate data;

  @NotEmpty(message = "Preenchimento obrigatório!")
  private Integer quantidade;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
}
