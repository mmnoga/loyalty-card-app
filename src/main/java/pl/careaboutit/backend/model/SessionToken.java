package pl.careaboutit.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "session_tokens")
public class SessionToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "issued_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedAt;

    @Column(name = "expiration", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

}
