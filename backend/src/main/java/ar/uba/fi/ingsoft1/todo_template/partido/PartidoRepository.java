package ar.uba.fi.ingsoft1.todo_template.partido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    @Query(value = "SELECT * FROM partido p JOIN participation_type pt ON p.participation_type_id = pt.part_type_id WHERE pt.type = 'Open'", nativeQuery = true)
    Page<Partido> findAllWithOpenParticipationNative(Pageable pageable);
}
