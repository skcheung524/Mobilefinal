package com.skc.mobilefinal;


/**
 * POJO to store budget items for scrolling list content
 */
public class BudgetItem {

    private String id;
    private String name;
    private String money;
    private String notes;

    public BudgetItem(String id, String name, String money, String notes) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * The to string is used to display the list text for the ArrayAdapter
     * @return
     */
    @Override
    public String toString() {
        return "ID: " + " " + id + " "  + "\nName: " + name + "\n$$: " + money;
    }
}
