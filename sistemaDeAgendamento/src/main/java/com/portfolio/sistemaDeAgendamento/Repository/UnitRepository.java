package com.portfolio.sistemaDeAgendamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sistemaDeAgendamento.entity.Unit;
import java.util.List;
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByActiveTrue();
    List<Unit> findByNameContainingIgnoreCase(String name);
}
