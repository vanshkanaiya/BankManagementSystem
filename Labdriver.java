import java.util.Scanner;

class Account {
    private static long tempNumber = 1;
    private long accNumber;
    private String accHolderName;
    private double accBalance;

    Account(String _name, double _balance) {
        accHolderName = _name;
        accBalance = _balance;
        accNumber = tempNumber;
        tempNumber++;
    }

    public long getAccNumber() {
        return accNumber;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccHolderName(String _name) {
        accHolderName = _name;
    }

    public void  depositBalance(double _depo) {
        if (_depo > 0) {
            accBalance +=_depo;
        }
        else {
            System.out.println("Invalid amount");
        }
    }

    public void  widthrawBalance(double _widthraw) {
        if (_widthraw == 0) {
            System.out.println("You can't widthraw 0 amount from your account");
        }
        else if (_widthraw < 0) {
            System.out.println("You can't widthraw negative amount from your account");
        }
        else if (getAccBalance() < _widthraw) {
            System.out.println("You dont sufficient amount in your account");
        }
        else {
            accBalance -= _widthraw;
        }
    }

    public String toString() {
        String str;
        str = "Account Name :" + getAccNumber() +  "\nAccount Holder Name : " + getAccHolderName() + "\nAccount Balance : " + getAccBalance();
        return str;
    }
}

class Saving extends Account {
    private double intrestRate;

    Saving(String _name,double _balance,double _intrest) {
        super(_name,_balance);
        intrestRate = _intrest;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public double calculateIntrestRate() {
        double inte;
        return inte = getAccBalance() * intrestRate * 1 / 100;
    }

    public String toString() {
        String str = super.toString() + "\nIntrest Rate : " + getIntrestRate() + "\nIntrest Amount : " + calculateIntrestRate();
        return str;
    }
}

class checkingAccount extends Account {
    private double fees;

    checkingAccount(String _name,double _balance) {
        super(_name,_balance);
        fees = 10;
    }

    checkingAccount(String _name,double _balance,double _fees) {
        super(_name,_balance);
        fees = _fees;
    }

    public double getFees() {
        return fees;
    }

    public void depositBalance(double _depo) {
        if (getAccBalance() < fees) {
            System.out.println("Amount is less than fees!!!!");
        }
        else {
            super.depositBalance(_depo - fees);
        }
    }

    public void widthrawBalance(double _widthraw) {
        if (getAccBalance() < (getAccBalance() + fees)) {
            System.out.println("Insufficient Amount!!!!");
        }
        else {
            super.widthrawBalance(_widthraw + fees);
        }
    }

    public String toString() {
        return super.toString() + "\nTransaction Fees : " + getFees();
    }
}

public class Labdriver {
    static Scanner scr = new Scanner(System.in);
    static int accountCount = 0;
    public static void main(String [] args) {
        boolean isRunning = true;
        Account[] accountArr = new Account[100];
        while(isRunning) {
            System.out.println("\tChoice option");
            System.out.println("1.Open Account :");
            System.out.println("2.Withdraw :");
            System.out.println("3.Deposit :");
            System.out.println("4.Account Details :");
            System.out.println("5.Check Balance :");
            System.out.println("6.Change Account Details :");
            System.out.println("0.Exit :");
            System.out.print("Enter the operation :");
            int choice = scr.nextInt();

            switch (choice) {
                case 1:
                    while (isRunning) {
                        System.out.println("\n1. Saving Account");
                        System.out.println("2. Current Account");
                        System.out.println("0. Exit");
                        System.out.print("Enetr your operation :");
                        int accOpt = scr.nextInt();

                        if (accOpt == 1) {
                            System.out.print("Enter Account Holder Name :");
                            String holderName = scr.next();
                            System.out.print("Enter Opeaning Balance :");
                            double opnBalance = scr.nextDouble();
                            System.out.print("Enter the Current Intrest Rate :");
                            double rate = scr.nextDouble();

                            Saving sav = new Saving(holderName,opnBalance,rate);
                            accountArr[accountCount] = sav;
                            System.out.println(accountArr[accountCount].getAccHolderName());
                            accountCount++;
                            System.out.println("Account Opened Sycessfully");
                            isRunning = false;
                        }

                        else if (accOpt == 2) {
                            System.out.print("Enter Account Holder Name :");
                            String holderName = scr.next();
                            System.out.print("Enter Opeaning Balance :");
                            double opnBalance = scr.nextDouble();
                            System.out.print("Enter the Current Fees :");
                            double fees = scr.nextDouble();
                            
                            checkingAccount crr = new checkingAccount(holderName,opnBalance,fees);
                            accountArr[accountCount] = crr;
                            accountCount++;
                            System.out.println("Account Opened Sycessfully");
                            isRunning = false;
                        }

                        else if (accOpt == 0) {
                            break;
                        }

                        else {
                            System.out.println("Invalid Option!!!!!");
                        }
                    }
                    isRunning = true;
                    break;

                case 2:
                    System.out.print("Enter Account Number :");
                    double widNumber = scr.nextDouble();

                    for (int i=0 ; i < accountCount ; i++) {
                        if (accountArr[i].getAccNumber() == widNumber) {
                        
                        System.out.print("Enter the amount you want to widthraw :");
                        double widMoney = scr.nextDouble();

                        accountArr[i].widthrawBalance(widMoney);
                        break;
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number :");
                    double depoNumber = scr.nextDouble();
                    
                    for (int i=0 ; i < accountCount ; i++) {
                        if (accountArr[i].getAccNumber() == depoNumber) {
                        
                        System.out.print("Enter the amount you want to deposit :");
                        double depoMoney = scr.nextDouble();

                        accountArr[i].depositBalance(depoMoney);
                        break;
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Holder Number :");
                    long holderName = scr.nextLong();

                    for (int i=0 ; i < accountCount ; i++) {
                        if (accountArr[i].getAccNumber() == holderName) {
                        
                        System.out.println(accountArr[i].toString());
                        break;
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number :");
                    double accNumber = scr.nextDouble();

                    for (int i=0 ; i < accountCount ; i++) {
                        if (accountArr[i].getAccNumber() == accNumber) {
                        
                        System.out.println(accountArr[i].getAccBalance());
                        break;
                        }
                    }
                    break;

                case 6:
                    System.out.println("\tWhich Operation you want to perform :-");
                    System.out.println("1.Change Account Holder Name :");
                    System.out.println("2.Change Account pin :");
                    System.out.print("Enter your choice :");
                    int changeChoice = scr.nextInt();
                    if (changeChoice == 1) {
                        System.out.print("Enter your Account Number :");
                        double aHName = scr.nextDouble();
                        for (int i=0; i < accountCount ; i++) {
                            if (accountArr[i].getAccNumber() == aHName) {
                                System.out.println("Enter old Holder Name :");
                                String oldName = scr.next();

                                if (accountArr[i].getAccHolderName().equals(oldName)) {
                                    System.out.println("Enter New Holder Name :");
                                    String newName = scr.next();
                                    accountArr[i].setAccHolderName(newName);
                                    break;
                                }
                                else {
                                    System.out.println("Old Account Name Not Found :");
                                }
                            }
                        }
                    }
                    else {
                                System.out.println("Account Number Did not Matched :");
                    }
                    break;
                case 0:
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
    }
}