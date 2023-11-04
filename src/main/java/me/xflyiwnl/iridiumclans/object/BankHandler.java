package me.xflyiwnl.iridiumclans.object;

public interface BankHandler {

    double get();
    void withdraw(double amount);
    void deposit(double amount);
    void set(double amount);

}
