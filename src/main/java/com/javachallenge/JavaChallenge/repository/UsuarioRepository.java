package com.javachallenge.JavaChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javachallenge.JavaChallenge.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Buscar usuario por email
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);
    
    // Buscar usuario por id
    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Usuario findUsuarioById(@Param("id") int id);
}
