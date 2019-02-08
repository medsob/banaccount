package com.bankaccount;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionTest {

    private Account account1;

    @Before
    public void setUp() {
        account1 = Account.builder()
                .balance(Account.getMonetaryAmount(0))
                .accountNumber("123ABC456")
                .transactionHistory(new ArrayList<>())
                .build();
    }

    @Test
    public void should_update_balance_with_deposit() {
        account1.deposit(Account.getMonetaryAmount(100));
        Assertions.assertThat(Account.getMonetaryAmount(100)).isEqualTo(account1.getBalance());
    }

    @Test
    public void should_update_balance_with_withdrawl() {
        account1.withdraw(Account.getMonetaryAmount(100));
        Assertions.assertThat(Account.getMonetaryAmount(-100)).isEqualTo(account1.getBalance());
    }

    @Test
    public void should_add_transaction_to_transaction_history() {
        LocalDateTime date1 = account1.deposit(Account.getMonetaryAmount(100));
        LocalDateTime date2 = account1.withdraw(Account.getMonetaryAmount(100));
        Assertions.assertThat(account1.getTransactionHistory().contains(Transaction.builder()
                .transactionType(TransactionType.DEPOSIT)
                .amount(Account.getMonetaryAmount(100))
                .transactionDateTime(date1)
                .build())).isTrue();
        Assertions.assertThat(account1.getTransactionHistory().contains(Transaction.builder()
                .transactionType(TransactionType.WITHDRAWL)
                .amount(Account.getMonetaryAmount(100))
                .transactionDateTime(date2)
                .build())).isTrue();
    }



}

