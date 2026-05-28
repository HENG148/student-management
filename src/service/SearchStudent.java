package service;

import java.util.ArrayList;
import java.util.List;

import model.Student;
import repository.StudentRepository;
import utils.InputHelper;
import utils.PrintHelper;

public class SearchStudent {
  private final StudentRepository repo;
  private final InputHelper input;

  public SearchStudent(StudentRepository repo, InputHelper input) {
    this.repo = repo;
    this.input = input;
  }

  public void run() {
    PrintHelper.printSectionHeader("SEARCH STUDENT");
    if (repo.isEmpty()) {
      System.out.println(" No Students on record.");
      input.pauseAndContinue();
      return;
    }
    System.out.println(" Search by:");
    System.out.println(" 1. Student ID");
    System.out.println(" 2. Full Name");
    System.out.println(" 3. Course");
    System.out.println(" 4. Email");

    int choice = input.promptInt("\n Your choice", 1, 4);
    String keyword = input.promptString(" Keyword: ").trim().toLowerCase();

    List<Student> results = new ArrayList<>();
    for (Student s : repo.getAll()) {
      switch (choice) {
        case 1:
          if (s.getStudentId().toLowerCase().contains(keyword))
            results.add(s);
          break;
        case 2:
          if (s.getFullName().toLowerCase().contains(keyword))
            results.add(s);
          break;
        case 3:
          if (s.getCourse().toLowerCase().contains(keyword))
            results.add(s);
          break;
        case 4:
          if (s.getEmail().toLowerCase().contains(keyword))
            results.add(s);
          break;
      }
    }
    System.out.println();
    if (results.isEmpty()) {
      System.out.println(" Found " + results.size() + " match(es):\n");
      PrintHelper.printTableHeader();
      for (Student s : results)
        System.out.println(s);
      PrintHelper.printTableFooter();
    }
    input.pauseAndContinue();
  }
}
