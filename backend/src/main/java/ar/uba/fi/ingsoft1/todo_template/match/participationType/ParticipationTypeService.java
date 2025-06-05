package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.team.Team;
import ar.uba.fi.ingsoft1.todo_template.team.TeamDTO;
import ar.uba.fi.ingsoft1.todo_template.team.TeamService;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ParticipationTypeService {

    private final TeamService teamService;
    private final UserService userService;

    public ParticipationTypeService(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    public ParticipationType buildFromDTO(ParticipationTypeDTO dto) {
        // habria q implementar un factory may be (tal vez service no es tan necesario sino mas bien un factory)
        if (dto instanceof CloseDTO closeDTO) {
            TeamDTO teamADTO = teamService.searchTeam(closeDTO.getTeama()); // TODO deberia devolver algun error en casos de invalid
            Team teama = new Team(teamADTO.getNombre(),teamADTO.captain());
            TeamDTO teamBDTO = teamService.searchTeam(closeDTO.getTeamb()); // TODO deberia devolver algun error en casos de invalid
            Team teamb = new Team(teamBDTO.getNombre(),teamBDTO.captain());
            return new Close(teama, teamb);
        } else if (dto instanceof OpenDTO openDTO) {
            if (openDTO.getMinPlayersCount() > openDTO.getMaxPlayersCount()){
                throw new IllegalArgumentException("Invalid arguments minimun player count is bigger than the players cap");
            }
            HashSet<User> players = new HashSet<>();
            for(String email: openDTO.getPlayers()){
                players.add(userService.getUser(email));
            }

            return new Open(openDTO.getMinPlayersCount(), openDTO.getMaxPlayersCount(), players); // TODO deberia devolver algun error en casos de invalid players
        } else {
            throw new IllegalArgumentException("Unknown participation type");
        }
    }

    public void updateParticipationType(ParticipationType oldPartType,ParticipationType newPartType){
        newPartType.setId(oldPartType.getId());
    }
}
