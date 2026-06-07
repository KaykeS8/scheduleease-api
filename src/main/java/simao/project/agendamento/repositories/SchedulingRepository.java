package simao.project.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import simao.project.agendamento.entitys.Scheduling;

import java.time.LocalDateTime;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    @Query("""
            SELECT COUNT(s) > 0 FROM Scheduling s
            WHERE s.professional.id = :professionalId
            AND s.status = 'SCHEDULED'
            AND s.startDateTime < :endDateTime
            AND s.endDateTime > :startDateTime
            """)
    boolean existDateConflict(
            @Param("professionalId") Long professionalId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
}
