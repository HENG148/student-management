
import java.io.IOException;
import java.util.Scanner;

import repository.StudentRepository;
import service.AddStudent;
import service.DeleteStudent;
import service.SearchStudent;
import service.UpdateStudent;
import service.ViewStudentService;
import utils.FileHandler;
import utils.InputHelper;
import utils.PrintHelper;

public class App {

  private static final String BANNER =
    "\n" +
    "  *************************************************************\n" +
    "  *        STUDENT MANAGEMENT SYSTEM  v2.0                   *\n" +
    "  *        Pure Java  |  OOP  |  File Persistence            *\n" +
    "  *************************************************************\n";
 
  private static final String MENU =
    "\n" +
    "  +---------------------------------------+\n" +
    "  |             MAIN MENU                 |\n" +
    "  +---------------------------------------+\n" +
    "  |   1.  Add Student                     |\n" +
    "  |   2.  Update Student                  |\n" +
    "  |   3.  Delete Student                  |\n" +
    "  |   4.  Search Student                  |\n" +
    "  |   5.  View All Students               |\n" +
    "  |   6.  Save Data to File               |\n" +
    "  |   7.  Load Data from File             |\n" +
    "  |   8.  Exit                            |\n" +
    "  +---------------------------------------+";
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentRepository repo = new StudentRepository();
    InputHelper input = new InputHelper(scanner);

    AddStudent addService = new AddStudent(repo, input);
    UpdateStudent updateService = new UpdateStudent(repo, input);
    DeleteStudent deleteStudent = new DeleteStudent(repo, input);
    SearchStudent searchStudent = new SearchStudent(repo, input);
    ViewStudentService viewStudent = new ViewStudentService(repo, input);

    System.out.println(BANNER);
    System.out.println(" Welcome! Choose an option from the menu below.");
    boolean running = true;

    while (running) {
      System.out.println("MENU");
      System.out.println("\n Enter your choice (1-8): ");
      String choice = scanner.nextLine().trim();

      switch (choice) {
        case "1":
          addService.run();
          break;
        case "2":
          updateService.run();
          break;
        case "3":
          deleteStudent.run();
          break;
        case "4":
          searchStudent.run();
          break;
        case "5":
          viewStudent.run();
          break;
        case "6":
          PrintHelper.printSectionHeader("SAVE DATA");
          if (repo.isEmpty()) {
            System.out.println(" Nothing to save - list is empty.");
          } else {
            try {
              FileHandler.saveToFile(repo.getAll());
              System.out.println(" [OK] " + repo.count() + "student(s) saved to: " + FileHandler.DEFAULT_FILE_PATH);
            } catch (IOException e) {
              System.out.println(" [ERROR] Save failed: " + e.getMessage());
            }
          }
          input.pauseAndContinue();
          break;
        case "7":
          PrintHelper.printSectionHeader("LOAD DATA");
          if (!FileHandler.dataFileExists()) {
            System.out.println("  [!] File not found: " + FileHandler.DEFAULT_FILE_PATH);
            System.out.println("  Use option 6 (Save Data) to create it first.");
          } else {
            try {
              repo.setAll(FileHandler.loadFromFile());
              System.out
                  .println("  [OK] " + repo.count() + " student(s) loaded from: " + FileHandler.DEFAULT_FILE_PATH);
            } catch (IOException e) {
              System.out.println("  [ERROR] Load failed: " + e.getMessage());
            }
          }
          input.pauseAndContinue();
          break;

        case "8":
          running = false;
          System.out.println(
            """
            +------------------------------------------+ 
            |  Thanks for using Student Manager! Bye!  |
            +------------------------------------------+ 
            """
          );
          break;

        default:
          System.out.println("\n  [!] '" + choice + "' is not valid. Enter a number from 1 to 8.");
      }
    }
    scanner.close();
  }
}
