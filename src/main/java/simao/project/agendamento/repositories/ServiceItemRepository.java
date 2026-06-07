package simao.project.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simao.project.agendamento.entitys.ServiceItem;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
}
