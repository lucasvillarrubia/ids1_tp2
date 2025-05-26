package ar.uba.fi.ingsoft1.todo_template.partido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidosRepository extends JpaRepository<Partido, Long> {
}
