package ar.uba.fi.ingsoft1.todo_template.team;

import java.util.List;

import ar.uba.fi.ingsoft1.todo_template.common.exception.DuplicateEntityException;
import ar.uba.fi.ingsoft1.todo_template.common.exception.InvalidActionException;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserService userService;

    public TeamDTO createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = teamCreateDTO.asTeam(userService.getCurrentUserEmail());

        teamRepository.findById(team.getName()).ifPresent(existingTeam -> {
            throw new DuplicateEntityException("Team", "name");
        });
        
        teamRepository.save(team);
        return new TeamDTO(team);
    }

    public TeamDTO getTeam(String name) {
        Team team = teamRepository.findById(name).orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return new TeamDTO(team);
    }

    public List<TeamDTO> getTeams() {
        List<TeamDTO> teams = teamRepository.findAll()
                                        .stream()
                                    .map(TeamDTO::new)
                                .toList();
        
        if (teams.isEmpty()) {
            throw new EntityNotFoundException("Teams not found");
        }

        return teams;
    }
    
    public TeamDTO updateTeams(String teamName, TeamCreateDTO teamCreateDTO) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        checkIfUserIsCaptain(teamName);

        Team newTeam = teamCreateDTO.asTeam(userService.getCurrentUserEmail());

        if (teamRepository.existsById(newTeam.getName()) && !team.getName().equals(newTeam.getName())) {
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
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        if (team.isComplete()) {
            throw new IllegalArgumentException("Team is already complete");
        }

        if (!playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name can not be empty");
        }
        checkIfUserIsCaptain(teamName);

        if (team.addPlayer(playerName)) {
            teamRepository.save(team);
            return playerName;
        }
        else {
            throw new DuplicateEntityException("Player", "name");
        }
    }

    public void removePlayer(String teamName, String playerName) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        if (team.getCaptain().equals(playerName)) {
            throw new IllegalArgumentException("Can not remove the captain player from the team");
        }

        checkIfUserIsCaptain(teamName);

        if (team.removePlayer(playerName)) {
            teamRepository.save(team);
        }
        else {
            throw new EntityNotFoundException("Player not found in the team");
        }
    }

    public void deleteTeam(String teamName) {
        if (!teamRepository.existsById(teamName)) {
            throw new EntityNotFoundException("Team not found");
        }
        checkIfUserIsCaptain(teamName);
        teamRepository.deleteById(teamName);
    }

    private void checkIfUserIsCaptain(String teamName){
        TeamDTO team = getTeam(teamName);
        if (!userService.getCurrentUserEmail().equals(team.captain())) {
            throw new InvalidActionException("Solo el capitán puede editar el equipo");
        }
    }


}
