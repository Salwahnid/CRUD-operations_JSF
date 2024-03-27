

import jakarta.persistence.*;

import javax.lang.model.element.Name;

@Entity
@Table(name = "StdTable")

public class Student {
    @Id @GeneratedValue
    private long id ;
    @Column
    private String nom ;
    private String prenom;



}
