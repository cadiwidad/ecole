package ma.cadi.ecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cadi.ecole.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
