package com.javachallenge.JavaChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javachallenge.JavaChallenge.models.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    // Buscar topico por id
    @Query("SELECT t FROM Topico t WHERE t.id = :id")
    Topico findTopicoById(@Param("id") int id);
    
    // Buscar topico por titulo y mensaje (para validar duplicados)
    @Query("SELECT t FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    Topico findByTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}
