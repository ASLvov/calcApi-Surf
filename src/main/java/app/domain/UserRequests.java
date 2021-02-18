package app.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Data
public class UserRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String statement;

    private String result;

    private LocalDateTime date;

    private Long userId;
}