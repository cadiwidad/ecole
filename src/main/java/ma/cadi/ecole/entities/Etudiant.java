package ma.cadi.ecole.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Etudiant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String prenom;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateNaiss;
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etudiant(Long id, String nom, String prenom, Date dateNaiss) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

}
