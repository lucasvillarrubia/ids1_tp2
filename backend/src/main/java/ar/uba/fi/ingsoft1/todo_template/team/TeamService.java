package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.common.exception.DuplicateEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public TeamDTO createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = teamCreateDTO.asTeam();

        teamRepository.findById(team.getName()).ifPresent(existingTeam -> {
            throw new DuplicateEntityException("Team", "name");
        });
        
        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public TeamDTO searchTeam(String name) {
        Team team = teamRepository.findById(name).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return new TeamDTO(team);
    }

    public List<TeamDTO> getTeams() {
        List<TeamDTO> teams = teamRepository.findAll()
                                        .stream()
                                    .map(TeamDTO::new)
                                .toList();
        
        if (teams.isEmpty()) {
            throw new IllegalArgumentException("No teams found");
        }

        return teams;
    }
    
    public TeamDTO updateTeams(String nombre, TeamCreateDTO equipoCreateDTO) {
        Team team = teamRepository.findById(nombre).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        Team newTeam = equipoCreateDTO.asTeam();

        if (teamRepository.existsById(nuevoEquipo.getName()) && !team.getName().equals(nuevoEquipo.getName())) {
            throw new DuplicateEntityException("Team", "name");
        }
        
        team.setName(newTeam.getName());    
        team.setLogo(newTeam.getLogo());
        team.setColors(newTeam.getColors());
        team.setSkill(newTeam.getSkill());
        team.setPlayers(newTeam.getPlayers());

        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public String addPlayer(String teamName, String playerName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if (team.isComplete()) {
            throw new IllegalArgumentException("Team is already complete");
        }

        if (!playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name can not be empty");
        }

        if (team.addPlayer(playerName)) {
            teamRepository.save(team);
            return playerName;
        }
        else {
            throw new IllegalArgumentException("Player already exists in the team");
        }
    }

    public void removePlayer(String teamName, String playerName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if (team.getCaptain().equals(playerName)) {
            throw new IllegalArgumentException("Can not remove the captain player from the team");
        }

        if (team.removePlayer(playerName)) {
            teamRepository.save(team);
        }
        else {
            throw new IllegalArgumentException("Player not found in the team");
        }
    }

    public void deleteTeam(String teamName) {
        if (!teamRepository.existsById(teamName)) {
            throw new IllegalArgumentException("Team not found");
        }

        teamRepository.deleteById(teamName);
    }
}
