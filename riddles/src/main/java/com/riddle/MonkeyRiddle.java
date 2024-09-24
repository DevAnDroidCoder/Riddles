package com.riddle;

import java.util.ArrayList;

public class MonkeyRiddle {

  public static void main(String[] args) {
    ArrayList<Boolean> doors = new ArrayList<Boolean>();

    for (int i = 0; i < Integer.valueOf(args[0]); i++) {
      doors.add(new Boolean(false));
    }
    System.out.println("Number of doors: ".concat(args[0]));
    System.out.print("\n"); // Prints next line
    for (int i = 0; i < Integer.valueOf(args[0]); i++) {
      for (int j = 0; j < Integer.valueOf(args[0]); j++) {
        // i is monkey, j is door
        // i th monkey on j th door
        if ((j + 1) % (i + 1) == 0) {
          doors.set(j, new Boolean(!doors.get(j)));
          System.out.println(
              String.valueOf(i + 1)
                  .concat("th monkey ")
                  .concat(doors.get(j) ? "opened " : "closed ")
                  .concat(String.valueOf(j + 1))
                  .concat("th door"));
        }
      }
    }
    System.out.print("\n\nLogging final summary\n"); // Prints neext line

    for (int i = 1; i < doors.size(); i++) {
      System.out.println(
          String.valueOf(i + 1).concat("th door is ").concat(doors.get(i) ? "opened" : "closed"));
    }
  }
}
