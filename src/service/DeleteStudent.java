package service;

import model.Student;
import repository.StudentRepository;
import utils.InputHelper;
import utils.PrintHelper;

public class DeleteStudent {
  private final StudentRepository repo;
  private final InputHelper input;

  public DeleteStudent(StudentRepository repo, InputHelper input) {
    this.repo = repo;
    this.input = input;
  }

  public void run() {
    PrintHelper.printSectionHeader("DELETE STUDENT");
    if (repo.isEmpty()) {
      System.out.println(" No students on record.");
      input.pauseAndContinue();
      return;
    }
    String id = input.promptString(" Enter student ID to delete: ").trim();
    Student student = repo.findbyId(id);
    if (student == null) {
      System.out.println(" [!] No student found with ID: " + id);
      input.pauseAndContinue();
      return;
    }

    System.out.println("\n Found");
    System.out.println(student.toDetailString());

    String confirm = input.promptString("\n Delete this record? (yes/no): ").trim().toLowerCase();
    if (confirm.equals("yes") || confirm.equals("y")) {
      repo.remove(student);
      System.out.println(" [OK] '" + student.getFullName() + "' deleted.");
    } else {
      System.out.println(" Deletion cancelled.");
    }
    input.pauseAndContinue();
  }
}
