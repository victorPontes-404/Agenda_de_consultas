package com.portfolio.sistemaDeAgendamento.Repository;

import com.portfolio.sistemaDeAgendamento.entity.UserDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface UserDoctorRepository extends JpaRepository<UserDoctor, Long> {
    Optional<UserDoctor> findByProfessionalRegistration(String registration);
    List<UserDoctor> findByUnitsId(Long unitId);
    List<UserDoctor> findBySpecialtiesName(String name);
}
