package ma.cadi.ecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cadi.ecole.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
