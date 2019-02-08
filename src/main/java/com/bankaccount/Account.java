package com.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class Account {
private MonetaryAmount balance;
    private List<Transaction> transactionHistory;
    private String accountNumber;


    public MonetaryAmount getBalance() {
        return balance;
    }

    public static MonetaryAmount getMonetaryAmount(int amount){
        return Monetary.getDefaultAmountFactory().setNumber(amount).setCurrency("EUR").create();
    }

    public LocalDateTime deposit(MonetaryAmount amount) {
        balance = balance.add(amount);
        LocalDateTime date = LocalDateTime.now();
        transactionHistory.add(Transaction.builder()
                .transactionType(TransactionType.DEPOSIT)
                .amount(amount)
                .transactionDateTime(date)
                .build());
        return date;
    }

    public LocalDateTime withdraw(MonetaryAmount amount) {
        balance = balance.subtract(amount);
        LocalDateTime date = LocalDateTime.now();
        transactionHistory.add(Transaction.builder()
                .transactionType(TransactionType.WITHDRAWL)
                .amount(amount)
                .transactionDateTime(date)
                .build());
        return date;
    }

}
