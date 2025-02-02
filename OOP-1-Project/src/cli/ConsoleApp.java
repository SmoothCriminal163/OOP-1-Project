package cli;

import app.AppContext;

import java.util.Scanner;

/**
 * Runs the interactive console loop (CLI).
 */
public class ConsoleApp {
    private final CommandProcessor commandProcessor;
    private final AppContext context;

    public ConsoleApp() {
        this.commandProcessor = new CommandProcessor();
        this.context = new AppContext();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SVG Editor started. Type 'help' for available commands.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            commandProcessor.processCommand(input, context);
        }
    }
}

