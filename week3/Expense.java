public class Expense {
    private String date;
    private Category category;
    private double amount;

    public Expense(String date, Category category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}