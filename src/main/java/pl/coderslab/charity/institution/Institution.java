package pl.coderslab.charity.institution;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean isActivated;

}
