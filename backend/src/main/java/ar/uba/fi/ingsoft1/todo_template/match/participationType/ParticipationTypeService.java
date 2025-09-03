package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.team.Team;
import ar.uba.fi.ingsoft1.todo_template.team.TeamDTO;
import ar.uba.fi.ingsoft1.todo_template.team.TeamService;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ParticipationTypeService {

    private final TeamService teamService;
    private final UserService userService;

    public ParticipationTypeService(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    public ParticipationType buildFromDTO(ParticipationTypeDTO dto) {
        if (dto instanceof CloseDTO closeDTO) {
            return buildCloseFromDTO(closeDTO);
        } else if (dto instanceof OpenDTO openDTO) {
            return buildOpenFromDTO(openDTO);
        } else {
            throw new IllegalArgumentException("Unknown participation type");
        }
    }

    private ParticipationType buildCloseFromDTO(CloseDTO dto) {
        Optional<TeamDTO> teamADTO = Optional.ofNullable(teamService.getTeam(dto.getTeama()));
        Optional<TeamDTO> teamBDTO = Optional.ofNullable(teamService.getTeam(dto.getTeamb()));
        if (teamADTO.isEmpty() || teamBDTO.isEmpty()){
            throw new IllegalArgumentException("Both teams must exist");
        }
        Team teama = teamADTO.get().asTeam();
        Team teamb = teamBDTO.get().asTeam();
        return new Close(teama, teamb);
    }

    private ParticipationType buildOpenFromDTO(OpenDTO dto) {
        HashSet<User> players = new HashSet<>();
        for(String email: dto.getPlayers()){
            try{
                players.add(userService.getUserByEmail(email));
            } catch (Exception e) {
                continue;
            }
        }
        return new Open(dto.getMinPlayersCount(), dto.getMaxPlayersCount(), players);
    }


    public void updateParticipationType(ParticipationType oldPartType,ParticipationType newPartType){
        newPartType.setId(oldPartType.getId());
    }
}
