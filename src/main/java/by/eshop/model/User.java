package by.eshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "birthday")
    private String birthday;
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;
    @NotEmpty(message = "Email must not be empty")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    @Email(message = "Email must be valid")
    @Column(name = "email")
    private String email;
    @Size(min = 2, max = 30, message = "Password must be between 2 and 30 characters")
    @NotEmpty(message = "Password must not be empty")
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user")
    private Cart cart;

}
