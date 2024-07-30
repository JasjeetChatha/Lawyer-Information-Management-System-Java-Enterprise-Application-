package ca.sheridancollege.chatjasj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.chatjasj.emails.EmailServiceImpl;
import ca.sheridancollege.chatjasj.repositories.LawyerRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EmailController {

	private LawyerRepository lawyerRepo;
	private EmailServiceImpl esi;
	@GetMapping("/email")
	public String email()
	{
		return "email.html";
	}
	
	@PostMapping("/email")
	public String rootP(Model model,@RequestParam String email)
	{
		model.addAttribute("lawyer", lawyerRepo.getLawyer());
		
		try {
			esi.sendMailWithInline(email, "Jasjeet Chatha", "Mr. John", "Jasjeet Singh Chatha", "Final", lawyerRepo.getLawyer());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/email";
	}
	@GetMapping("/emailc")
	public String emailc(Model model)
	{
		model.addAttribute("lawyer", lawyerRepo.getLawyer());
		return "emailc.html";
	}
}
