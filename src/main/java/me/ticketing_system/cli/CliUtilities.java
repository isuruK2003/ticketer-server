package me.ticketing_system.cli;

import java.util.Scanner;

public class CliUtilities {

    public static final String logo = """
             _____            _ _   _                  _______ _      _        _     __  __                                                   _      _____           _                \s
            |  __ \\          | | | (_)                |__   __(_)    | |      | |   |  \\/  |                                                 | |    / ____|         | |               \s
            | |__) |___  __ _| | |_ _ _ __ ___   ___     | |   _  ___| | _____| |_  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  | (___  _   _ ___| |_ ___ _ __ ___ \s
            |  _  // _ \\/ _` | | __| | '_ ` _ \\ / _ \\    | |  | |/ __| |/ / _ \\ __| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|  \\___ \\| | | / __| __/ _ \\ '_ ` _ \\\s
            | | \\ \\  __/ (_| | | |_| | | | | | |  __/    | |  | | (__|   <  __/ |_  | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_   ____) | |_| \\__ \\ ||  __/ | | | | |
            |_|  \\_\\___|\\__,_|_|\\__|_|_| |_| |_|\\___|    |_|  |_|\\___|_|\\_\\___|\\__| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| |_____/ \\__, |___/\\__\\___|_| |_| |_|
                                                                                                               __/ |                                        __/ |                     \s
                                                                                                              |___/                                        |___/                      \s                                                    \s
            """;

    public static final String[] mainMenuItems = {
            "Configure Simulation",
            "Start Simulation",
            "Stop Simulation"
    };

    public static String exitKey = "e";

    public static void displayMainMenu() {
        for (int i = 0; i < mainMenuItems.length; i++) {
            System.out.println("[" + i + "] " + mainMenuItems[i]);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static String readStringInput(String prompt) {
        String userInput = "";
        while (true) {
            System.out.println(prompt);
            userInput = scanner.nextLine().trim();
            if (userInput.isBlank()) {
                System.out.println("Error: Cannot be blank");
                continue;
            }
            break;
        }
        return userInput;
    }

    public static Integer readIntegerInput(String prompt) {
        Integer userInput = null;
        while (true) {
            System.out.println(prompt);
            try {
                userInput = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numerical value.");
            }
        }
        return userInput;
    }
}
