package com.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.money.MonetaryAmount;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    public final TransactionType transactionType;
    public final MonetaryAmount amount;
    public final LocalDateTime transactionDateTime;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction trx = (Transaction) o;
        return Objects.equals(transactionType, trx.transactionType)
                && Objects.equals(amount, trx.amount)
                && Objects.equals(transactionDateTime, trx.transactionDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDateTime, amount, transactionDateTime);
    }
}
