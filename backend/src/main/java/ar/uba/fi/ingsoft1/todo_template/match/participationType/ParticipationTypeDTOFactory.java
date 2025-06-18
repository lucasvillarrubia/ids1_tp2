package ar.uba.fi.ingsoft1.todo_template.match.participationType;

public class ParticipationTypeDTOFactory {

    public ParticipationTypeDTOFactory(){}

    public static ParticipationTypeDTO createParticipationTypeDTO(ParticipationType partType) {
        if(partType instanceof Open open) {
            return new OpenDTO(open);
        } else if (partType instanceof Close close){
            return new CloseDTO(close);
        } else {
            throw new RuntimeException("Invalid class in participationTypeFactory");
        }
    }
}