package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.dtos.TransactionRecordDto;
import io.codeforall.bootcamp.javabank.model.transaction.Transaction;
import org.springframework.stereotype.Component;

/**
 * A converter class which converts {@link Transaction} objects into {@link TransactionRecordDto} objects
 */
@Component
public class TransactionToTransactionRecordDto extends AbstractConverter<Transaction, TransactionRecordDto> {

    /**
     * Convert a transaction into a TransactionRecordDto
     * @param source to take the info out of
     * @return the TransactionRecordDto
     */
    @Override
    public TransactionRecordDto convert(Transaction source) {

        TransactionRecordDto transactionRecordDto = new TransactionRecordDto();
        transactionRecordDto.setTransactionId(source.getId());
        transactionRecordDto.setType(source.getTransactionType().toString().toLowerCase());
        transactionRecordDto.setAmount(source.getAmount().toString());
        transactionRecordDto.setRecipientName(source.getRecipient() != null ? source.getRecipient().getName() : null);
        transactionRecordDto.setEstablishmentName(source.getPurchase() != null ? source.getPurchase().getEstablishment().getName() : null);

        return transactionRecordDto;
    }
}
