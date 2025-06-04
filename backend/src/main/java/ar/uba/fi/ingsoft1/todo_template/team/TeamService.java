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

    public TeamDTO getTeam(String teamName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));
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
    
    public TeamDTO updateTeams(String teamName, TeamCreateDTO equipoCreateDTO) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        Team newTeam = equipoCreateDTO.asEquipo();

        if (teamRepository.existsById(newTeam.getName()) && !team.getName().equals(newTeam.getName())) {
            throw new IllegalArgumentException("Team with that name already exists");
        }
        
        team.setName(newTeam.getName());    
        team.setLogo(newTeam.getLogo());
        team.setColors(newTeam.getColors());
        team.setSkill(newTeam.getSkill());
        for (String player : newTeam.getPlayers()) {
            if (!team.getPlayers().contains(player)) {
                team.getPlayers().add(player);
            }
        }

        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public String addPlayer(String teamName, String playerName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if (team.isComplete()) {
            throw new IllegalArgumentException("Team is already complete");
        }

        if (team.getPlayers().contains(playerName)) {
            throw new IllegalArgumentException("Player already exists in the team");
        }

        team.addPlayer(playerName);
        teamRepository.save(team);
        return playerName;
    }

    public void removePlayer(String teamName, String playerName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if (!team.getPlayers().contains(playerName)) {
            throw new IllegalArgumentException("Player not found in the team");
        }

        if (team.getCaptain().equals(playerName)) {
            throw new IllegalArgumentException("Can not remove the captain player from the team");
        }

        team.getPlayers().remove(playerName);
        teamRepository.save(team);
    }

    public void deleteTeam(String teamName) {
        if (!teamRepository.existsById(teamName)) {
            throw new IllegalArgumentException("Team not found");
        }

        teamRepository.deleteById(teamName);
    }
}
