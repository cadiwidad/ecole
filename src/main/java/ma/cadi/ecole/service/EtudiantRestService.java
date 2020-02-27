package ma.cadi.ecole.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.cadi.ecole.dao.EtudiantRepository;
import ma.cadi.ecole.entities.Etudiant;

@RestController
public class EtudiantRestService {
	@Autowired
	EtudiantRepository etudiantRepository;	
	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(value = "/listeEtudiants")
	public Page<Etudiant> listEtudiants(int page,int size) {
		return etudiantRepository.findAll(PageRequest.of(page, size));
	}

	
	@Secured(value = {"ROLE_ADMIN"})
	@GetMapping(value="/ajouterEtudiant")
	public Etudiant ajouterUser(Etudiant etudiant) {
		return etudiantRepository.save(etudiant);
	}
	@Secured(value = {"ROLE_ADMIN"})
	@GetMapping(value="/getLogedUser")
	public Map<String,Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT") ;
		String username=securityContext.getAuthentication().getName();
		List<String> listeRoles=new ArrayList<String>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			listeRoles.add(ga.getAuthority());
		}
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("username", username);
		params.put("listeRoles", listeRoles);
		return params;
		
	}

}
