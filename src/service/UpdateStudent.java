package service;

import java.util.regex.Pattern;

import model.Student;
import repository.StudentRepository;
import utils.InputHelper;
import utils.PrintHelper;

public class UpdateStudent {
  private final StudentRepository repo;
  private final InputHelper input;
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

  public UpdateStudent(StudentRepository repo, InputHelper input) {
    this.repo = repo;
    this.input = input;
  }

  public void run() {
    PrintHelper.printSectionHeader("UPDATE STUDENT");
    if (repo.isEmpty()) {
      System.out.println(" No students on record.");
      input.pauseAndContinue();
      return;
    }
    String id = input.promptString(" Enter student ID to update: ").trim();
    Student student = repo.findbyId(id);
    if (student == null) {
      System.out.println(" [!] No student found with ID: " + id);
      input.pauseAndContinue();
      return;
    }
    System.out.println("\n Current record: ");
    System.out.println(student.toDetailString());
    System.out.println(" (Press ENTER on any field to keep the current value)\n");
  
    String name = input.promptString(" New Full Name [" + student.getFullName() + "]:");
    if (!name.isEmpty()) {
      if (name.length() >= 2)
        student.setFullName(name);
      else {
        System.out.println(" [!] Too short - kept original.");
      }
    }

    String ageInput = input.promptString(" New Age [" + student.getAge() + "]: ").trim();
    if (!ageInput.isEmpty()) {
      try {
        int newAge = Integer.parseInt(ageInput);
        if (newAge >= 5 && newAge <= 25)
          student.setAge(newAge);
        else
          System.out.println(" [!] Out of range - kept original.");
      } catch (NumberFormatException e) {
        System.out.println(" [!] Not a number - kept original.");
      }
    }
    
    String genderInput = input.promptString("  New Gender [" + student.getGender() + "]: ").trim();
    if (!genderInput.isEmpty()) {
      if (genderInput.equalsIgnoreCase("male")
        || genderInput.equalsIgnoreCase("female")
        || genderInput.equalsIgnoreCase("other")) {
            student.setGender(Character.toUpperCase(genderInput.charAt(0))
              + genderInput.substring(1).toLowerCase());
      } else {
          System.out.println("  [!] Invalid gender — kept original.");
      }
    }

    String course = input.promptString("  New Course [" + student.getCourse() + "]: ");
    if (!course.isEmpty()) student.setCourse(course);
 
    String email = input.promptString("  New Email [" + student.getEmail() + "]: ").trim();
      if (!email.isEmpty()) {
        if (EMAIL_PATTERN.matcher(email).matches()) student.setEmail(email);
        else System.out.println("  [!] Invalid email — kept original.");
      }
 
    System.out.println("\n  [OK] Student ID '" + id + "' updated successfully!");
    input.pauseAndContinue();
  }
}