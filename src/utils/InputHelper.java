package utils;

import java.util.Scanner;

public class InputHelper {
  private final Scanner scanner;

  public InputHelper(Scanner scanner) {
    this.scanner = scanner;
  }

  public String promptString(String prompt) {
    System.out.println(prompt);
    return scanner.nextLine();
  }

  public int promptInt(String prompt, int min, int max) {
    while (true) {
      System.out.println(prompt);
      String input = scanner.nextLine().trim();
      try {
        int value = Integer.parseInt(input);
        if (value >= min && value <= max)
          return value;
        System.out.println("[!] Enter a number between " + min + "and" + max + ".");
      } catch (NumberFormatException e) {
        System.out.println("[!] Not a valid number. Try again.");
      }
    }
  }
  
  public void pauseAndContinue() {
    System.out.println();
    System.err.println(" Press ENTER to return to the menu...");
    scanner.nextLine();
  }
}
