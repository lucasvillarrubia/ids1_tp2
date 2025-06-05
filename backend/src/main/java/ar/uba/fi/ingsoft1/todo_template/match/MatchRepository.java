package ar.uba.fi.ingsoft1.todo_template.match;

import ar.uba.fi.ingsoft1.todo_template.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = "SELECT * FROM match p JOIN participation_type pt ON p.participation_type_id = pt.part_type_id WHERE pt.type = 'Open'", nativeQuery = true)
    Page<Match> findAllWithOpenParticipation(Pageable pageable);

    Page<Match> findByOrganizer(Pageable pageable, User organizer);

    @Query(
            value = """
        SELECT * FROM match m
        JOIN open_players op ON m.participation_type_id = op.open_id
        WHERE op.user_id = :userId
    """,
            nativeQuery = true
    )
    Page<Match> findAllMatchesUserPlaysIn(Pageable pageable, @Param("userId") Long userId);
}
