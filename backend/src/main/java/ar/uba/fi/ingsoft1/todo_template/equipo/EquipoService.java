package ar.uba.fi.ingsoft1.todo_template.equipo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EquipoService {
    @Autowired
    EquipoRepository equipoRepository;

    public EquipoDTO crearEquipo(EquipoCreateDTO equipoCreateDTO) {
        Equipo equipo = equipoCreateDTO.asEquipo();

        if (equipoRepository.existsById(equipo.getNombre())) {
            return null;
        }
        
        equipoRepository.save(equipo);
        return new EquipoDTO(equipo);
    }

    public EquipoDTO obtenerEquipo(String nombre) {
        Equipo equipo = equipoRepository.findById(nombre).orElse(null);
        if (equipo == null) {
            return null;
        }
        return new EquipoDTO(equipo);
    }

    public List<EquipoDTO> obtenerEquipos() {
        return equipoRepository.findAll().stream()
                .map(EquipoDTO::new)
                .toList();
    }
    
    public EquipoDTO actualizarEquipo(String nombre, EquipoCreateDTO equipoCreateDTO) {
        Equipo equipo = equipoRepository.findById(nombre).orElse(null);

        if (equipo == null) {
            return null;
        }

        Equipo nuevoEquipo = equipoCreateDTO.asEquipo();

        if (equipoRepository.existsById(nuevoEquipo.getNombre()) && !equipo.getNombre().equals(nuevoEquipo.getNombre())) {
            equipo.setNombre(null);
            return new EquipoDTO(equipo);
        }
        
        equipo.setNombre(nuevoEquipo.getNombre());    
        equipo.setLogo(nuevoEquipo.getLogo());
        equipo.setColores(nuevoEquipo.getColores());
        equipo.setNivel(nuevoEquipo.getNivel());

        equipoRepository.save(equipo);
        return new EquipoDTO(equipo);
    }

    public boolean eliminarEquipo(String nombre) {
        if (!equipoRepository.existsById(nombre)) {
            return false;
        }

        equipoRepository.deleteById(nombre);
        return true;
    }
}
