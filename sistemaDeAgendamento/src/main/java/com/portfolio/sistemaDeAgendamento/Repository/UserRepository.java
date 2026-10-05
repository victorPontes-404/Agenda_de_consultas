package com.portfolio.sistemaDeAgendamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.sistemaDeAgendamento.entity.User;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByCpf(String cpf);
}
