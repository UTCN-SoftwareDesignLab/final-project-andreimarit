package com.lab4.demo.sending;

import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.model.Discount;
import com.lab4.demo.repo.DiscountRepository;
import com.lab4.demo.service.mapper.DiscountMapperImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.lab4.demo.UrlMapping.PASSWORD;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final DiscountRepository discountRepository;
    private final DiscountMapperImplementation discountMapper;


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

        List<Discount> discounts = discountRepository.findAll().stream().collect(Collectors.toList());

        for (Discount discount : discounts) {
            DiscountDTO discountDTO = discountMapper.toDto(discount);
            createEmail(session, messageToSend(discountDTO));
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

    private String messageToSend(DiscountDTO discountDTO){
        StringBuilder message=new StringBuilder();
        message.append("Hello! \n")
                .append("You have a new discount.\n")
                .append("Your discount have a ")
                .append(discountDTO.getPercent())
                .append("% off.\n")
                .append("Please use it before it expires.\n")
                .append("Have a nice day!");
        return message.toString();
    }
}
