package alkemy.challenge.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import alkemy.challenge.dto.UserDTO;

@Service
public class MailService {

	@Value("${alkemy.challenge.sendgrid.apikey}")
	private String apiKey;

	@Value("${alkemy.challenge.sendgrid.senderemail}")
	private String senderEmail;

	public void sendWelcomeEmail(UserDTO user) {
		Email from = new Email(senderEmail);
		String subject = "Registro nuevo usuario";
		Email to = new Email(user.getEmail());
		String contentText = "Bienvenido " + user.getUsername() + ". Su registro se realizo con exito.";
		Content content = new Content("text/plain", contentText);
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(apiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
