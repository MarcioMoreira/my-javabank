package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.dtos.RecipientDto;
import io.codeforall.bootcamp.javabank.exceptions.RecipientNotFoundException;
import io.codeforall.bootcamp.javabank.model.Recipient;
import io.codeforall.bootcamp.javabank.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A converter class which converts {@link RecipientDto} objects into {@link Recipient} objects
 */
@Component
public class RecipientDtoToRecipient {

    private RecipientService recipientService;

    /**
     * Convert recipientDto into recipient
     * @param recipientDto to take info out of
     * @return the recipient
     * @throws RecipientNotFoundException if the recipient is not found
     */
    public Recipient convert(RecipientDto recipientDto) throws RecipientNotFoundException {

        Recipient recipient = (recipientDto.getId() != null ? recipientService.getRecipient(recipientDto.getId()) : new Recipient());

        recipient.setAccountNumber(recipientDto.getAccountNumber());
        recipient.setName(recipientDto.getName());
        recipient.setDescription(recipientDto.getDescription());

        return recipient;
    }

    /**
     * Set the recipient service
     * @param recipientService to set
     */
    @Autowired
    public void setRecipientService(RecipientService recipientService) {
        this.recipientService = recipientService;
    }
}
