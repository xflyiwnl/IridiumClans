package me.xflyiwnl.iridiumclans.object;

public class Bank implements BankHandler {

    private double currentAmount = 0;

    public Bank() {
    }

    public Bank(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public double get() {
        return currentAmount;
    }

    @Override
    public void withdraw(double amount) {
        currentAmount -= amount;
    }

    @Override
    public void deposit(double amount) {
        currentAmount += amount;
    }

    @Override
    public void set(double amount) {
        currentAmount = amount;
    }

}
