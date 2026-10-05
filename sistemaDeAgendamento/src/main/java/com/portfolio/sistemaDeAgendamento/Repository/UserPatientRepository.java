package com.portfolio.sistemaDeAgendamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sistemaDeAgendamento.entity.UserPatient;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
public interface UserPatientRepository extends JpaRepository<UserPatient, Long> {
    Optional<UserPatient> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    List<UserPatient> findByBirthDateBefore(LocalDate date);
   
}
