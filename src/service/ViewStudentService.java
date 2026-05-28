package service;

import model.Student;
import repository.StudentRepository;
import utils.InputHelper;
import utils.PrintHelper;

public class ViewStudentService {
  private final StudentRepository repo;
  private final InputHelper input;

  public ViewStudentService(StudentRepository repo, InputHelper input) {
    this.repo = repo;
    this.input = input;
  }

  public void run() {
    PrintHelper.printSectionHeader("ALL STUDENTS");
    if (repo.isEmpty()) {
      System.out.println(" No records yet. Use option 1 to add students.");
      input.pauseAndContinue();
      return;
    }
    System.out.println(" Total: " + repo.count() + " student(s)\n");
    PrintHelper.printTableHeader();
    for(Student s: repo.getAll())
      System.out.println(s);
    PrintHelper.printTableFooter();
    input.pauseAndContinue();
  }
}
