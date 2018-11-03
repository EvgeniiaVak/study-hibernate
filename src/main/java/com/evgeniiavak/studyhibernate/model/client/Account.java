package com.evgeniiavak.studyhibernate.model.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Embeddable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Embeddable
public class Account {

    @Min(value = 0)
    private Long amount;

    @CreditCardNumber
    private String creditCardNumber;


    public Account(@Min(value = 0) Long amount) {
        this.amount = amount;
    }

    public Account(@CreditCardNumber String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @AssertTrue
    boolean amountXorCreditCardNumber() {
        return amount != null ^ creditCardNumber != null;
    }
}
