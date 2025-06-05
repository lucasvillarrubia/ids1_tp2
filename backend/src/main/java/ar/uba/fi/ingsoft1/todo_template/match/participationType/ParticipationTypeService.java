package ar.uba.fi.ingsoft1.todo_template.match.participationType;

import ar.uba.fi.ingsoft1.todo_template.equipo.Equipo;
import ar.uba.fi.ingsoft1.todo_template.equipo.EquipoDTO;
import ar.uba.fi.ingsoft1.todo_template.equipo.EquipoService;
import ar.uba.fi.ingsoft1.todo_template.user.User;
import ar.uba.fi.ingsoft1.todo_template.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ParticipationTypeService {

    private final EquipoService teamService;
    private final UserService userService;

    public ParticipationTypeService(EquipoService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    public ParticipationType buildFromDTO(ParticipationTypeDTO dto) {
        // habria q implementar un factory may be (tal vez service no es tan necesario sino mas bien un factory)
        if (dto instanceof CloseDTO closeDTO) {
            EquipoDTO teamADTO = teamService.obtenerEquipo(closeDTO.getTeama()); // TODO deberia devolver algun error en casos de invalid
            Equipo teama = new Equipo(teamADTO.getNombre(),teamADTO.getCapitan());
            EquipoDTO teamBDTO = teamService.obtenerEquipo(closeDTO.getTeamb()); // TODO deberia devolver algun error en casos de invalid
            Equipo teamb = new Equipo(teamBDTO.getNombre(),teamBDTO.getCapitan());
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
