package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Student;

public class StudentService {
  private List<Student> students;
  private Scanner scanner;
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

  public StudentService(Scanner scanner) {
    this.students = new ArrayList<>();
    this.scanner = scanner;
  }

  public void addStudent() {
    printSectionHeader("ADD NEW STUDENT");

  }

  private void printSectionHeader(String title) {
    System.out.println();
    System.out.println("  ============================================================");
    System.out.printf("  ==  %-54s==%n", title);
    System.out.println("  ============================================================");
    System.out.println();
  }

  private void printTableHeader() {
    System.out.println(
        "  +------------+------------------------+-----+----------+----------------------+------------------------------+");
    System.out.println(
        "  | Student ID | Full Name              | Age | Gender   | Course               | Email                        |");
    System.out.println(
        "  +------------+------------------------+-----+----------+----------------------+------------------------------+");
  }
  
  private void printTableFooter() {
        System.out.println("  +------------+------------------------+-----+----------+----------------------+------------------------------+");
    }
 
  private void pauseAndContinue() {
    System.out.println();
    System.out.print("  Press ENTER to return to the menu...");
    scanner.nextLine();
  }
}
