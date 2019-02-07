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

    public LocalDateTime send(MonetaryAmount amount, Account receiverAccount) {
        balance = balance.subtract(amount);
        LocalDateTime date = LocalDateTime.now();
        receiverAccount.receive(amount, this, date);
        Transaction transaction = new Transaction(this.accountNumber, receiverAccount.accountNumber, amount,
                date);
        transactionHistory.add(transaction);
        return date;
    }

    public void receive(MonetaryAmount amount, Account senderAccount, LocalDateTime date) {
        balance = balance.add(amount);
        transactionHistory.add(Transaction.builder()
                .sender(senderAccount.getAccountNumber())
                .receiver(this.accountNumber)
                .amount(amount)
                .transactionDateTime(date)
                .build());
    }

}
