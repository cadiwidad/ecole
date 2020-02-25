package ma.cadi.ecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cadi.ecole.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
