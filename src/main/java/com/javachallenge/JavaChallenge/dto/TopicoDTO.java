package com.javachallenge.JavaChallenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDTO {
    
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;
    
    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
    
    @NotBlank(message = "El autor es obligatorio")
    private String autor;
    
    @NotBlank(message = "El curso es obligatorio")
    private String curso;
}
