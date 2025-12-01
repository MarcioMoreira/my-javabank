package io.codeforall.bootcamp.javabank.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * A class which represent the data transfer object related to a complete transaction record
 */
public class TransactionRecordDto {

    @NotNull(message = "transactionId is mandatory")
    private Integer transactionId;

    @NotNull(message = "amount is mandatory")
    @NotBlank(message = "amount is mandatory")
    private String amount;

    @NotNull(message="The type of the transaction is mandatory")
    @NotBlank(message="The type of the transaction is mandatory")
    private String type;

    private String establishmentName;
    private String recipientName;

    /**
     * Get the transaction id
     * @return the transaction id
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Get the amount of the transaction
     * @return the transaction amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Get the type of the transaction
     * @return the transaction type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the establishment related to the transaction
     * @return the establishment's name
     */
    public String getEstablishmentName() {
        return establishmentName;
    }

    /**
     * Get the recipient related to the transaction
     * @return the recipient's name
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * Set the transaction id
     * @param transactionId to set
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Set the transaction amount
     * @param amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Set the transaction type
     * @param type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the establishment's name
     * @param establishmentName to set
     */
    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    /**
     * Set the recipient's name
     * @param recipientName to set
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
