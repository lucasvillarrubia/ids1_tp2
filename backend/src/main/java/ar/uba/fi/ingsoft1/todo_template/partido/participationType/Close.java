package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Close")
public class Close extends ParticipationType{

    public Close(){

    }
}
