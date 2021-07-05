package com.lab4.demo.sms;
import com.lab4.demo.sms.SMS;
import com.lab4.demo.websocket.WsServerEndpoint;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@Component
public class SMSService {

    private final static String ACCOUNT_SID = "AC095d68a87bb96280882a5389bcf33ac8";
    private final static String AUTH_ID = "";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }
    //
//    @Value("AC033cce1caf8223d2afd65896ed731911")
//    private String ACCOUNT_SID;
//
//    @Value("b13a52406b9294503a3383218a3d99a5")
//    private String AUTH_TOKEN;
//
//    @Value("+15415277023")
//    private String FROM_NUMBER;

    public void send() {
        SMS sms = new SMS("You received a new discount! Visit our store and buy what you need!");

        WsServerEndpoint.sendAlert("The SMS has been sent!");
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+40744813445"), new PhoneNumber("+19083864823"), sms.getMessage())
                .create();

        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

}