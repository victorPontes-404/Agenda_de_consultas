package com.portfolio.sistemaDeAgendamento.Repository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sistemaDeAgendamento.entity.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
