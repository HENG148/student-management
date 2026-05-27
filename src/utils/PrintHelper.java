package utils;

public class PrintHelper {
  public static void printSectionHeader(String title) {
    System.out.println();
    System.out.println("  ============================================================");
    System.out.printf ("  ==  %-54s==%n", title);
    System.out.println("  ============================================================");
    System.out.println();
  }

  public static void printTableHeader() {
    System.out.println("  +------------+------------------------+-----+----------+----------------------+------------------------------+");
    System.out.println("  | Student ID | Full Name              | Age | Gender   | Course               | Email                        |");
    System.out.println("  +------------+------------------------+-----+----------+----------------------+------------------------------+");
  }

  public static void printTableFooter() {
    System.out.println("  +------------+------------------------+-----+----------+----------------------+------------------------------+");
  }
}
