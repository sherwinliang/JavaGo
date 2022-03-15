package poi;

import java.time.LocalDate;

public class SimpleHoldPojo {
    private long id;
    private String account;
    private LocalDate holdDate;
    private double holdUnits;
    private double cost;
    private double increment;
    private double marketValue;

    public SimpleHoldPojo(long id, String account, LocalDate holdDate, double holdUnits, double cost, double increment, double marketValue) {
        this.id = id;
        this.account = account;
        this.holdDate = holdDate;
        this.holdUnits = holdUnits;
        this.cost = cost;
        this.increment = increment;
        this.marketValue = marketValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public LocalDate getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(LocalDate holdDate) {
        this.holdDate = holdDate;
    }

    public double getHoldUnits() {
        return holdUnits;
    }

    public void setHoldUnits(double holdUnits) {
        this.holdUnits = holdUnits;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }
}
