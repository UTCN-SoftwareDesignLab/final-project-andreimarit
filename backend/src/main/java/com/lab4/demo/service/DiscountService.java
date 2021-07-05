package com.lab4.demo.service;

import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.model.Discount;
import com.lab4.demo.model.User;
import com.lab4.demo.repo.DiscountRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.sending.EmailSenderService;
import com.lab4.demo.service.mapper.DiscountMapper;
import com.lab4.demo.sms.SMSService;
import com.lab4.demo.websocket.WsServerEndpoint;
import com.twilio.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountService {


    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    private final UserRepository userRepository;
    private final SMSService smsService;
    private final EmailSenderService emailSenderService;
    private final SimpMessagingTemplate webSocket;

    public List<DiscountDTO> findAll() {
        return discountRepository.findAll().stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DiscountDTO> findAllByClient(Long clientId) {
        List<DiscountDTO> discountDTOList = discountRepository.findAll().stream()
                .map(discountMapper::toDto)
                .collect(Collectors.toList());

        List<DiscountDTO> discountDTOListClient = new ArrayList<>();

        for (DiscountDTO discountDTO : discountDTOList){
            if(clientId == discountDTO.getClient()) discountDTOListClient.add(discountDTO);
        }

        return  discountDTOListClient;
    }

    private Discount findById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount not found: " + id));
    }

    public DiscountDTO create(DiscountDTO discount) {

        try {
            smsService.send();
        }
        catch (ApiException e){
            webSocket.convertAndSend("/consultation/sms", getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend("/consultation/sms", getTimeStamp() + ": SMS has been sent!");

        WsServerEndpoint.sendAlert("A discount of " + discount.getPercent() + "% was create for client " + discount.getClient());
        return discountMapper.toDto(discountRepository.save(
                discountMapper.fromDto(discount)
        ));
    }

    public DiscountDTO edit(DiscountDTO discount, Long id) {
        Discount actDiscount = findById(id);
        actDiscount.setId(discount.getId());
        User client = userRepository.findById( discount.getClient()).orElseThrow(() -> new EntityNotFoundException("Client NOT FOUND!!"));
        actDiscount.setClient(client);
        actDiscount.setPercent(discount.getPercent());
        return discountMapper.toDto(
                discountRepository.save(actDiscount)
        );
    }

    public void delete(Long id){

        WsServerEndpoint.sendAlert("Discount with id " + id + " was used");
        discountRepository.deleteById(id);
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
    }
}


