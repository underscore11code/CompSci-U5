package io.github.underscore11code.compsci;

/*
* Mad Libs
* Recreation of the word game "Mad Libs".
* E*** D***
* April 8th, 2021
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws FileNotFoundException {
    boolean again = false;
    do {
      madLib().run();
      System.out.println("\n\n");
      final String response = prompt("Do you wish to go again? (y/n)",
              r -> r.equalsIgnoreCase("y") || r.equalsIgnoreCase("n"));
      if (response.equalsIgnoreCase("y")) again = true;
    } while (again);
    scanner.close();
    System.out.println("Thank you.");
  }

  public static File directory() {
    return new File("./madlibs");
  }

  public static MadLib madLib() throws FileNotFoundException {
    final File[] files = directory().listFiles();
    if (files == null) throw new NullPointerException();
    final String txt = new Scanner(files[new Random().nextInt(files.length)]).nextLine();
    return new MadLib(txt);
  }

  public static String prompt(final String prompt, final Predicate<String> condition) {
    System.out.println(prompt);
    while (true) {
      final String in = scanner.nextLine();
      if (condition.test(in)) return in;
      System.out.println("Invalid input.\n" + prompt);
    }
  }
}
