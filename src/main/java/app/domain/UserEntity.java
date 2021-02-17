package app.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String password;
}