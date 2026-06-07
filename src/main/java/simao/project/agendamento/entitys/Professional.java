package simao.project.agendamento.entitys;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "professionals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "professional")
    private List<Scheduling> schedulings;
}
