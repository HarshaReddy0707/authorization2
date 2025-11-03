package authorization.authorization;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;
import java.util.Date; // Import Date

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column; // Import Column
import lombok.Data;

@Entity
@Data
public class authuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles_auth",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<authrole> role = new HashSet<>();

    

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "lock_time")
    private Date lockTime;

    private LocalDateTime locktime;
    
}