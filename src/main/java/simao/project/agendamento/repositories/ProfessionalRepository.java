package simao.project.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simao.project.agendamento.entitys.Professional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}
