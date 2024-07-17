import java.util.Scanner;

public class URLShortenerCLI {
    private URLShortener urlShortener = new URLShortener();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a command (shorten, expand, or quit):");
            String command = scanner.nextLine();

            if (command.equals("quit")) {
                break;
            } else if (command.equals("shorten")) {
                System.out.println("Enter a long URL:");
                String longURL = scanner.nextLine();
                String shortID = urlShortener.shortenURL(longURL);
                System.out.println("Short URL: " + shortID);
            } else if (command.equals("expand")) {
                System.out.println("Enter a short URL:");
                String shortID = scanner.nextLine();
                String longURL = urlShortener.expandURL(shortID);
                System.out.println("Long URL: " + longURL);
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    public static void main(String[] args) {
        URLShortenerCLI cli = new URLShortenerCLI();
        cli.run();
    }
}