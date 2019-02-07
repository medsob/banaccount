package com.bankaccount;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionTest {

    private Account account1;
    private Account account2;

    @Before
    public void setUp() {
        account1 = Account.builder()
                .balance(Account.getMonetaryAmount(0))
                .accountNumber("123ABC456")
                .transactionHistory(new ArrayList<>())
                .build();
        account2 = Account.builder()
                .balance(Account.getMonetaryAmount(0))
                .accountNumber("456123ABC")
                .transactionHistory(new ArrayList<>())
                .build();
    }

    @Test
    public void should_update_sender_receiver_balance() {
        account1.send(Account.getMonetaryAmount(100), account2);
        Assertions.assertThat(Account.getMonetaryAmount(-100)).isEqualTo(account1.getBalance());
        Assertions.assertThat(Account.getMonetaryAmount(100)).isEqualTo(account2.getBalance());
    }

    @Test
    public void should_add_transaction_to_transaction_history() {
        LocalDateTime date1 = account1.send(Account.getMonetaryAmount(120), account2);
        LocalDateTime date2 = account1.send(Account.getMonetaryAmount(500), account2);
        Assertions.assertThat(account1.getTransactionHistory().contains(Transaction.builder()
                .sender("123ABC456")
                .receiver("456123ABC")
                .amount(Account.getMonetaryAmount(120))
                .transactionDateTime(date1)
                .build())).isTrue();
        Assertions.assertThat(account1.getTransactionHistory().contains(Transaction.builder()
                .sender("123ABC456")
                .receiver("456123ABC")
                .amount(Account.getMonetaryAmount(500))
                .transactionDateTime(date2)
                .build())).isTrue();
    }



}

