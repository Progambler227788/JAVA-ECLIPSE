import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Subscriber class representing a telephone subscriber
class Subscriber {
    private String name; // his name
    private String surName; // his surName
    private String residence; // his living place
    private String phoneNumber; // his phone number
    private Account account; // account class object

    // Constructor
    public Subscriber(String name,String surName, String residence, String phoneNumber, Account account) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.surName = surName;
        this.residence = residence;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSurName() {
        return surName;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getResidence() {
        return residence;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void makeCall(double duration) {
        double cost =  account.calculateCallCost(duration);
        account.deductBalance(cost);
    }

    public void sendMessage() {
        double cost = account.calculateMessageCost();
        account.deductBalance(cost);
    }

    public void makeDataConnection(int kilobytes) {
        double cost = account.calculateDataConnectionCost(kilobytes);
        account.deductBalance(cost);
    }

    public void joinPromotion(Promotion promotion) {
        account.applyPromotion(promotion);
    }

    public void topUp(double amount) {
        account.topUp(amount);
    }


}
class TariffPlan {
    private double smsCost; // per sms cost, like user send 1 sms == $0.1 for that one message
    private double callCostPerMinute; // Call cost per minute
    private double dataCostPerKiloByte; // data connection charges pet KB

    public TariffPlan(double smsCost, double callCostPerMinute) {
        this.smsCost = smsCost;
        this.callCostPerMinute = callCostPerMinute;
    }

    public void setDataCostPerKiloByte(double dataCostPerKiloByte) {
        this.dataCostPerKiloByte = dataCostPerKiloByte;
    }

    public double getDataCostPerKiloByte() {
        return dataCostPerKiloByte;
    }

    public void setCallCostPerMinute(double callCostPerMinute) {
        this.callCostPerMinute = callCostPerMinute;
    }

    public void setSmsCost(double smsCost) {
        this.smsCost = smsCost;
    }

    public double getSmsCost() {
        return smsCost;
    }

    public double getCallCostPerMinute() {
        return callCostPerMinute;
    }
}

// Account class representing the account of a subscriber
class Account {
    private double balance;

    public double dataCost;

    private double totalCallCost;

    private double totalMessagesCost;

    private double totalDataUsageCost;

    TariffPlan tariffPlan;
    private Promotion currentPromotion;

    public Account(double balance, double callRate, double textMessageCost, double dataRate) {
        this.balance = balance;

    }
    public double calculateMessageCost() {
        // Retrieve the cost per message from the tariff plan
        double cost = tariffPlan.getSmsCost();
        totalMessagesCost += cost;
        return cost;
    }

    public double calculateCallCost(double minutes) {
        // Calculate the cost of the call based on the duration and rate per minute
        double cost = minutes * tariffPlan.getCallCostPerMinute();
        totalCallCost += cost;
        return cost;
    }

    public double calculateDataConnectionCost(int kilobytes) {
        // Calculate the cost based on the tariff plan's rate per kilobyte
        double cost = kilobytes * tariffPlan.getDataCostPerKiloByte();
        totalDataUsageCost += cost;
        return cost;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public void applyPromotion(Promotion promotion) {
        this.currentPromotion = promotion;
    }

    public void topUp(double amount) {
        balance += amount;
    }

    // Getters and setters
}

//Promotion class representing a promotional offer
class Promotion {
    private String name;
    private double discountRate;
    private double dataConnectionCost;
    private double minutesCost;
    private double smsCost;

    public Promotion(String name, double discountRate, double dataConnectionCost, double minutesCost, double smsCost) {
        this.name = name;
        this.discountRate = discountRate;
        this.dataConnectionCost = dataConnectionCost;
        this.minutesCost = minutesCost;
        this.smsCost = smsCost;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountRate() {
        return minutesCost+smsCost+dataConnectionCost;
    }

    public void setDiscountRateByInput(double discountRate) {
        this.discountRate = discountRate;
    }
    public void setDiscountRate() {
        this.discountRate = minutesCost+smsCost+dataConnectionCost;
    }


    public double getDataConnectionCost() {
        return dataConnectionCost;
    }

    public void setDataConnectionCost(double dataConnectionCost) {
        this.dataConnectionCost = dataConnectionCost;
    }

    public double getMinutesCost() {
        return minutesCost;
    }

    public void setMinutesCost(double minutesCost) {
        this.minutesCost = minutesCost;
    }

    public double getSmsCost() {
        return smsCost;
    }

    public void setSmsCost(double smsCost) {
        this.smsCost = smsCost;
    }
}


// PaymentStrategy interface representing different payment methods
interface PaymentStrategy {
    void pay(double amount);
}

// CashPaymentStrategy class for cash payments
class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via cash.");
    }
}

// CreditCardPaymentStrategy class for credit card payments
class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via credit card.");
    }
}

// DebitCardPaymentStrategy class for debit card payments
class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via debit card.");
    }
}

// Observer interface for observing changes in account balance
interface AccountObserver {
    void update(double newBalance);
}

// SubscriberObserver class for observing changes in subscriber's account balance
class SubscriberObserver implements AccountObserver {
    private Subscriber subscriber;

    public SubscriberObserver(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void update(double newBalance) {
        System.out.println("Account balance updated for subscriber " + subscriber.getName() + ": " + newBalance);
    }
}
// Factory Design Pattern
// SubscriberFactory interface
interface SubscriberFactory {
    Subscriber createSubscriber(String name, String surName, String residence, String phoneNumber, Account account);
}

class ConcreteSubscriberFactory implements SubscriberFactory {
    @Override
    public Subscriber createSubscriber(String name, String surName, String residence, String phoneNumber, Account account) {
        return new Subscriber(name, surName, residence, phoneNumber, account);
    }
}

interface PromotionFactory {
    Promotion createPromotion(String name, double discountRate, double dataConnectionCost, double minutesCost, double smsCost);
}
class ConcretePromotionFactory implements PromotionFactory {
    @Override
    public Promotion createPromotion(String name, double discountRate, double dataConnectionCost, double minutesCost, double smsCost) {
        return new Promotion(name,discountRate, dataConnectionCost, minutesCost, smsCost);
    }
}

// Command interface representing user actions
interface Command {
    void execute();
}

// RegisterSubscriberCommand class for registering a new subscriber
class RegisterSubscriberCommand implements Command {
    private Administrator administrator;
    private String name;
    private String surName;
    private String residence;
    private String phoneNumber;
    private Account account;

    public RegisterSubscriberCommand(Administrator administrator, String name, String surName, String residence, String phoneNumber, Account account) {
        this.administrator = administrator;
        this.name = name;
        this.surName = surName;
        this.residence = residence;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    @Override
    public void execute() {
        administrator.registerSubscriber(name, surName, residence, phoneNumber, account);
    }
}

// InsertPromotionCommand class for inserting a new promotion
class InsertPromotionCommand implements Command {
    private Administrator administrator;
    private String name;
    private double discountRate;
    private double dataConnectionCost;
    private double minutesCost;
    private double smsCost;

    public InsertPromotionCommand(Administrator administrator, String name, double discountRate, double dataConnectionCost, double minutesCost, double smsCost) {
        this.administrator = administrator;
        this.name = name;
        this.discountRate = discountRate;
        this.dataConnectionCost = dataConnectionCost;
        this.minutesCost = minutesCost;
        this.smsCost = smsCost;
    }

    @Override
    public void execute() {
        administrator.insertPromotion(name, discountRate, dataConnectionCost, minutesCost, smsCost);
    }
}

// UsagePercentageCommand class for showing the percentages of use of each service for a subscriber
class UsagePercentageCommand implements Command {
    private Administrator administrator;
    private Subscriber subscriber;

    public UsagePercentageCommand(Administrator administrator, Subscriber subscriber) {
        this.administrator = administrator;
        this.subscriber = subscriber;
    }

    @Override
    public void execute() {
        // Logic to calculate and display usage percentages for the subscriber
        administrator.showUsagePercentage(subscriber);
    }
}

// Administrator class responsible for administrator operations
class Administrator {
    private Map<String, Subscriber> subscribers;
    private Map<String, Promotion> promotions;

    public Administrator() {
        this.subscribers = new HashMap<>();
        this.promotions = new HashMap<>();
    }

    public void registerSubscriber(String name, String surName, String residence, String phoneNumber, Account account) {

        ConcreteSubscriberFactory createSubs = new ConcreteSubscriberFactory ();
        Subscriber subscriber = createSubs.createSubscriber(name, surName, residence, phoneNumber, account);
        subscribers.put(phoneNumber, subscriber);
        System.out.println("New subscriber registered: " + subscriber.getName());
    }

    public void insertPromotion(String name, double discountRate, double dataConnectionCost, double minutesCost, double smsCost) {
        ConcretePromotionFactory createProm = new ConcretePromotionFactory();
        Promotion promotion = createProm.createPromotion(name, discountRate, dataConnectionCost, minutesCost, smsCost);
        promotions.put(name, promotion);
        System.out.println("New promotion inserted: " + promotion.getName());
    }

    public void showUsagePercentage(Subscriber subscriber) {
        // Logic to calculate and display usage percentages for the subscriber
        System.out.println("Usage percentages for subscriber: " + subscriber.getName());
    }
}

// Main class to demonstrate the telephone carrier system
public class Classes {
    public static void main(String[] args) {

    }
}
