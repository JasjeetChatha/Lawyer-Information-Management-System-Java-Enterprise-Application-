package ca.sheridancollege.chatjasj.controllers;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.chatjasj.beans.Lawyer;
import ca.sheridancollege.chatjasj.repositories.LawyerRepository;
import ca.sheridancollege.chatjasj.repositories.UserRepository;


@Controller
public class HomeController {
	@Autowired
	private LawyerRepository lawyerRepo;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String Home() {
		return "index.html";
	}

	@GetMapping("/add")
	public String Add(Model model) {
		model.addAttribute("lawyer", new Lawyer());
		return "Add.html";
	}

	@PostMapping("/add")
	public String AddG(@ModelAttribute Lawyer lawyer) {
		lawyerRepo.addLawyer(lawyer);
		return "redirect:/add";
	}

	@GetMapping("/view")
	public String View(Model model, Authentication authentication) {
		model.addAttribute("lawyer", lawyerRepo.getLawyer());

		ArrayList<String> roles = new ArrayList<String>();
		
		return "View.html";
	}

	

	
	@GetMapping("/delete/{id}")
	public String deleteTicket(Model model, @PathVariable int id) {
		Lawyer lawyer = lawyerRepo.getLawyerById(id);
		model.addAttribute("lawyer", lawyer);
		lawyerRepo.deleteLawyer(lawyer);
		return "redirect:/view";
	}

	@GetMapping("/register")
	public String goregister() {
		return "registration.html";
	}

	// LawyerController.java
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password,@RequestParam long roleid) {
		userRepo.register(username, password);
		long userId = userRepo.findUserByUsername(username).getUserId();
		userRepo.assignRoles(userId,roleid);

		return "redirect:/login";
	}

	@GetMapping("/user")
	public String user(Authentication auth) {
		String username = auth.getName();
		List<String> roles = new ArrayList<String>();

		List<GrantedAuthority> grantList = (ArrayList<GrantedAuthority>) auth.getAuthorities();
		for (GrantedAuthority g : grantList) {
			roles.add(g.getAuthority());
		}
		return "index.html";
	}

}
