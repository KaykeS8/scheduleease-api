package simao.project.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simao.project.agendamento.entitys.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
