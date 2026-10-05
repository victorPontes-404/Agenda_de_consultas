package com.portfolio.sistemaDeAgendamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sistemaDeAgendamento.entity.Specialty;
import java.util.Optional;
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByName(String name);
    boolean existsByName(String name);
}
