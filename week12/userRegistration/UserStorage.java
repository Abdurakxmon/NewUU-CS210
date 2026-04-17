package userRegistration;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final String SEPARATOR = "|";
    private final Path filePath;

    public UserStorage(Path filePath) {
        this.filePath = filePath;
    }

    public void saveUser(User user) throws IOException {
        ensureFileExists();
        String line = user.getUsername() + " " + SEPARATOR + " " + user.getPassword() + " " + SEPARATOR + " " + user.getEmail();
        Files.writeString(
                filePath,
                line + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND
        );
    }

    public boolean authenticate(String username, String password) throws IOException {
        for (User user : readUsers()) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean usernameExists(String username) throws IOException {
        for (User user : readUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExists(String email) throws IOException {
        for (User user : readUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    private List<User> readUsers() throws IOException {
        ensureFileExists();
        List<User> users = new ArrayList<>();

        for (String line : Files.readAllLines(filePath, StandardCharsets.UTF_8)) {
            if (line == null || line.isBlank()) {
                continue;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length < 3) {
                continue;
            }

            String username = parts[0].trim();
            String password = parts[1].trim();
            String email = parts[2].trim();
            users.add(new User(username, password, email));
        }

        return users;
    }

    private void ensureFileExists() throws IOException {
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
