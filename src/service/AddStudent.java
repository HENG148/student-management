package service;

import java.util.regex.Pattern;

import model.Student;
import repository.StudentRepository;
import utils.InputHelper;
import utils.PrintHelper;

public class AddStudent {
  private final StudentRepository repo;
  private final InputHelper input;
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

  public AddStudent(StudentRepository repo, InputHelper input) {
    this.repo = repo;
    this.input = input;
  }

  public void run() {
    PrintHelper.printSectionHeader("ADD NEW STUDENT");
    String id;
    while (true) {
      id = input.promptString("Enter Student Id (e.g. STU001): ");
      if (id.isEmpty()) {
        System.out.println("[!] Student ID cannot be empty.");
      } else if (repo.existsById(id)) {
        System.out.println("[!] ID '" + id + "' already exists. Try another.");
      } else {
        break;
      }
    }
    String name;
    while (true) {
      name = input.promptString("Enter Full Name");
      if (name.isEmpty()) {
        System.out.println("[!] Name cannot be empty.");
      } else if (name.length() < 2) {
        System.out.println("[!] Name must be at least 2 characters.");
      } else {
        break;
      }
    }

    int age = input.promptInt("Enter age (5-25):", 5, 25);

    String gender;
    while (true) {
      gender = input.promptString("Enter Gender (Male / Female / Other): ").trim();
      if (gender.equalsIgnoreCase("male")
          || gender.equalsIgnoreCase("female")
          || gender.equalsIgnoreCase("other")) {
        gender = Character.toUpperCase(gender.charAt(0)) + gender.substring(1).toLowerCase();
        break;
      }
      System.out.println("[!] Enter Male, Female, or Other.");
    }
    String course;
    while (true) {
      course = input.promptString(" Enter Course: ");
      if (!course.isEmpty())
        break;
      System.out.println(" [!] Course cannot be empty.");
    }

    String email;
    while (true) {
      email = input.promptString(" Enter Email: ").trim();
      if (EMAIL_PATTERN.matcher(email).matches())
        break;
      System.out.println(" [!] Invalid email. Example: user@domain.com");
    }
    repo.add(new Student(id, name, age, gender, course, email));
    System.out.println("\n [ok] Student '" + name + "' added successfully!");
    input.pauseAndContinue();
  }
}