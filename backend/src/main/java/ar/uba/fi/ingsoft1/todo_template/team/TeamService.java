package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public TeamDTO createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = teamCreateDTO.asEquipo();

        teamRepository.findById(team.getName()).ifPresent(existingTeam -> {
            throw new IllegalArgumentException("Team with that name already exists");
        });
        
        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public TeamDTO searchTeam(String name) {
        Team team = teamRepository.findById(name).orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return new TeamDTO(team);
    }

    public List<TeamDTO> searchTeams() {
        return teamRepository.findAll().stream()
                .map(TeamDTO::new)
                .toList();
    }
    
    public TeamDTO updateTeams(String nombre, TeamCreateDTO equipoCreateDTO) {
        Team team = teamRepository.findById(nombre).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        Team nuevoEquipo = equipoCreateDTO.asEquipo();

        if (teamRepository.existsById(nuevoEquipo.getName()) && !team.getName().equals(nuevoEquipo.getName())) {
            throw new IllegalArgumentException("Team with that name already exists");
        }
        
        team.setName(nuevoEquipo.getName());    
        team.setLogo(nuevoEquipo.getLogo());
        team.setColors(nuevoEquipo.getColors());
        team.setSkill(nuevoEquipo.getSkill());

        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public boolean deleteTeam(String name) {
        if (!teamRepository.existsById(name)) {
            return false;
        }

        teamRepository.deleteById(name);
        return true;
    }
}
