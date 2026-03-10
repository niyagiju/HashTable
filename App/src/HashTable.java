import java.util.*;
public class HashTable {

    HashMap<String, Integer> usernameMap = new HashMap<>();

    HashMap<String, Integer> attemptCount = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptCount.put(username, attemptCount.getOrDefault(username, 0) + 1);

        return !usernameMap.containsKey(username);
    }
    public void registerUser(String username, int userId) {
        usernameMap.put(username, userId);
    }
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String newName = username + i;

            if (!usernameMap.containsKey(newName)) {
                suggestions.add(newName);
            }
        }

        suggestions.add(username.replace("_", "."));
        suggestions.add(username + "_official");

        return suggestions;
    }
    public String getMostAttempted() {

        String result = "";
        int max = 0;

        for (String key : attemptCount.keySet()) {

            if (attemptCount.get(key) > max) {
                max = attemptCount.get(key);
                result = key;
            }
        }

        return result;
    }
    public static void main(String[] args) {

        HashTable ht = new HashTable();
        ht.registerUser("john_doe", 1001);
        ht.registerUser("alex23", 1002);

        System.out.println("Check john_doe: " + ht.checkAvailability("john_doe"));
        System.out.println("Check jane_smith: " + ht.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe:");
        System.out.println(ht.suggestAlternatives("john_doe"));

        System.out.println("Most attempted username: " + ht.getMostAttempted());
    }
}