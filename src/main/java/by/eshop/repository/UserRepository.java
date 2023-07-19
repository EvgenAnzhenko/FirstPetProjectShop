package by.eshop.repository;

import by.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmailAndPassword(String email, String password);

}