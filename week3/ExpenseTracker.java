import java.io.*;
import java.util.*;

public class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private ArrayList<Category> categories;
    private User user;
    private Scanner scanner;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        categories = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Expense Tracker");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        user = new User(username, password);
        System.out.println("User registered successfully!");
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            mainMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void mainMenu() {
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Category-wise Summation");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    categoryWiseSummation();
                    break;
                case 4:
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Enter expense date (yyyy-mm-dd): ");
        String date = scanner.next();
        System.out.print("Enter expense category: ");
        String category = scanner.next();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();

        Category cat = getCategory(category);
        if (cat == null) {
            cat = new Category(category);
            categories.add(cat);
        }

        Expense expense = new Expense(date, cat, amount);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
        } else {
            System.out.println("Expenses:");
            for (Expense expense : expenses) {
                System.out.println(
                        expense.getDate() + " - " + expense.getCategory().getName() + ": " + expense.getAmount());
            }
        }
    }

    private void categoryWiseSummation() {
        for (Category category : categories) {
            double total = 0;
            for (Expense expense : expenses) {
                if (expense.getCategory().equals(category)) {
                    total += expense.getAmount();
                }
            }
            System.out.println(category.getName() + ": " + total);
        }
    }

    private Category getCategory(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("expenses.dat"))) {
            oos.writeObject(expenses);
            oos.writeObject(categories);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("expenses.dat"))) {
            expenses = (ArrayList<Expense>) ois.readObject();
            categories = (ArrayList<Category>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        expenseTracker.loadData();
        expenseTracker.run();
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Expense {
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