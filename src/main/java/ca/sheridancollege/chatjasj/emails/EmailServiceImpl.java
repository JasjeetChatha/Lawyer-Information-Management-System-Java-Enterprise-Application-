package ca.sheridancollege.chatjasj.emails;

import java.util.ArrayList;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ca.sheridancollege.chatjasj.beans.Lawyer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl {

	private JavaMailSender emailSender;
	
	
	private TemplateEngine templateEngine;
	
	public void  sendMailWithInline(String to   , String subject, String body,
									String name , String message, ArrayList<Lawyer> lawyers)
									throws MessagingException
	{
		// Represents the attributes in the HTML page
		Context ctx = new Context();
		ctx.setVariable("name"    , name	);
		ctx.setVariable("message" , message	);
		ctx.setVariable("lawyer"  , lawyers	);
		
		// Create the body of the email
		String htmlContent = this.templateEngine.process("/emailc", ctx);
		
		MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		MimeMessageHelper message1 = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		message1.setTo(to);
		message1.setSubject(subject);
		message1.setText(htmlContent, true); // true = isHTML
		
		
		emailSender.send(mimeMessage);
	}
}
