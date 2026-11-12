package kz.donerlong.wallet.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Account {
    private final UUID id;
    private final UUID ownerId;
    private Money balance;
    private final Instant createdAt;

    public Account(UUID id, UUID ownerId, Money balance, Instant createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.ownerId = Objects.requireNonNull(ownerId, "ownerId must not be null");
        this.balance = Objects.requireNonNull(balance, "balance must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static Account open(UUID id, UUID ownerId, Instant createdAt) {
        return new Account(id, ownerId, Money.ZERO, createdAt);
    }

    public void deposit(Money amount) {
        requirePositive(amount);

        balance = balance.plus(amount);
    }

    public void withdraw(Money amount) {
        requirePositive(amount);
        if (balance.isLessThan(amount)) {
            throw new InsufficientFundsException(
                    "Insufficient funds: balance " + balance.amount() + ", requested " + amount.amount());
        }

        balance = balance.minus(amount);
    }

    private static void requirePositive(Money amount) {
        Objects.requireNonNull(amount, "amount must not be null");
        if (!amount.isPositive()) {
            throw new InvalidAmountException("Amount must be positive " + amount.amount());
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Money getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}