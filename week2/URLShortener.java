import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Map<String, String> urlMap = new HashMap<>();
    private Map<String, String> reverseMap = new HashMap<>();

    public String shortenURL(String longURL) {
        if (urlMap.containsValue(longURL)) {
            // Handle duplicate long URL
            return "Error: Duplicate long URL";
        }

        String shortID = generateShortID();
        urlMap.put(shortID, longURL);
        reverseMap.put(longURL, shortID);
        return shortID;
    }

    public String expandURL(String shortID) {
        if (!urlMap.containsKey(shortID)) {
            // Handle invalid short URL
            return "Error: Invalid short URL";
        }

        return urlMap.get(shortID);
    }

    private String generateShortID() {
        // Simple hash function for generating short IDs
        int hash = 0;
        for (char c : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray()) {
            hash = (hash * 31 + c) % 1000000;
        }
        return "short-" + String.valueOf(hash);
    }
}