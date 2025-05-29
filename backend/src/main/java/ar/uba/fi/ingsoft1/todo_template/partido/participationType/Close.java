package ar.uba.fi.ingsoft1.todo_template.partido.participationType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Close")
public class Close extends ParticipationType{

    public Close(){

    }
}
