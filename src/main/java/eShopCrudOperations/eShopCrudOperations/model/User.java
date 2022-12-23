package eShopCrudOperations.eShopCrudOperations.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


@Entity
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Long id;

    @Column(name = "username",nullable = false,  unique = true, length = 100)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "role", nullable = false, length = 100)
    private String role;


}
