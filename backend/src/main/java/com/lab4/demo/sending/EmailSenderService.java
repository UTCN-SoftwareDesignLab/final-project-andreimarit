package com.lab4.demo.sending;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.Patient;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.service.mapper.ConsultationMapper;
import com.lab4.demo.service.mapper.ConsultationMapperImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.lab4.demo.UrlMapping.PASSWORD;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapperImplementation consultationMapper;


    public void sendEmail() throws Exception{
        final String mail = "andreizeke@gmail.com";
        final String password = PASSWORD;

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("entered in authentification");
                return new PasswordAuthentication(mail, password);
            }
        });

        List<Consultation> consultations = consultationRepository.findAll().stream().filter(consultation -> consultation.getId() == 0).collect(Collectors.toList());

        for (Consultation consultation : consultations) {
            ConsultationDTO consultationDTO = consultationMapper.toDto(consultation);
            createEmail(session, messageToSend(consultationDTO));
        }
    }

    private void createEmail(Session session, String message) throws Exception{
        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("andreizeke@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andreizeke@gmail.com"));
        msg.setSubject("Consultation reminder");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        msg.setContent(multipart);
        //msg.setSentDate(new Date());

        Transport.send(msg);
    }

    private String messageToSend(ConsultationDTO consultationDTO){
        StringBuilder message=new StringBuilder();
        message.append("Hello! \n")
                .append("This email is a reminder that you have a consultation.\n")
                .append("Consultation details: ")
                .append(consultationDTO.getDate())
                .append(".\n")
                .append("Please call us if you don't make it.\n")
                .append("Have a nice day!");
        return message.toString();
    }
}
