package muscleGosup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
    @Table(name = "sets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
    })
public class Set {

}
