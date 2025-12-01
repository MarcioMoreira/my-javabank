package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.exceptions.CustomerNotFoundException;
import io.codeforall.bootcamp.javabank.exceptions.RecipientNotFoundException;
import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.Recipient;
import io.codeforall.bootcamp.javabank.persistence.daos.RecipientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * An {@link RecipientService} implementation
 */
@Service
public class RecipientServiceImpl implements RecipientService {

    private RecipientDao recipientDao;
    private CustomerService customerService;

    /**
     * @see RecipientService#getCustomerRecipients(int)
     */
    @Override
    public List<Recipient> getCustomerRecipients(int cid) throws CustomerNotFoundException {
        Customer customer = customerService.get(cid);

        return new ArrayList<>(customer.getRecipients());
    }

    /**
     * @see RecipientService#getRecipient(int)
     */
    @Override
    public Recipient getRecipient(int rid) throws RecipientNotFoundException {
        return Optional.ofNullable(recipientDao.findById(rid)).orElseThrow(RecipientNotFoundException::new);
    }

    /**
     * Sets the recipient data access object
     * @param recipientDao the recipient DAO to set
     */
    @Autowired
    public void setRecipientDao(RecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    /**
     * Sets the customer service
     * @param customerService to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
