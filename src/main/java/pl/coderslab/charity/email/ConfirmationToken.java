package pl.coderslab.charity.email;

import lombok.Data;
import pl.coderslab.charity.user.AppUser;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmationToken")
@Data
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser appUser;

    public ConfirmationToken() {}

    public ConfirmationToken(AppUser appUser) {
        this.appUser = appUser;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
