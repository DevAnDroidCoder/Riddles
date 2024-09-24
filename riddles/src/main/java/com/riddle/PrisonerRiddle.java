package com.riddle;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class PrisonerRiddle {
  public static void main(String[] args) {
    int prisionersCount = Integer.parseInt(args[0]);

    ArrayList<Integer> prisionersList = new ArrayList<>();
    ArrayList<Integer> slipsList = new ArrayList<Integer>();
    HashMap<Integer, Integer> box = new HashMap<Integer, Integer>();

    // Add n number of slips, prisioners in list
    for (int i = 0; i < prisionersCount; ++i) {
      slipsList.add(i + 1);
      prisionersList.add(i + 1);
    }

    // Assign random numbers to boxes
    // Remove slip from as slipsList as it is assigned to box

    System.out.println("\n{");

    for (int i = 0; i < prisionersCount; ++i) {
      int randomPosition = new Random().nextInt(slipsList.size());

      box.put(i + 1, slipsList.get(randomPosition));

      System.out.println(
          "\t Box "
              .concat(String.valueOf(i + 1))
              .concat(" : Contains slip number ")
              .concat(String.valueOf(slipsList.get(randomPosition))));

      slipsList.remove(randomPosition);
    }

    System.out.println("}\n");

    if (!tryRandomlyMethod(prisionersList, box, prisionersCount / 2)) {
      System.out.println("\nRandomly opening slips didn't worked.\n");
    } else {
      System.out.println("\nRandomly opening slips worked.\n");
    }

    if (!tryPlannedMethod(prisionersList, box, prisionersCount / 2)) {
      System.out.println("\nPlanned method didn't worked.\n");
    } else {
      System.out.println("\nPlanned method worked.\n");
    }
  }

  public static boolean tryRandomlyMethod(
      ArrayList<Integer> prisionersList, HashMap<Integer, Integer> box, int allowedToOpenBoxes) {
    System.out.println("\nPrisioners are going to open randomly...");

    for (int i = 0; i < prisionersList.size(); ++i) {
      int prisionerNumber = prisionersList.get(i);
      ArrayList<Integer> boxesList = new ArrayList<>();
      boolean isFound = false;

      for (int i2 = 0; i2 < prisionersList.size(); ++i2) {
        boxesList.add(i2 + 1);
      }

      for (int i2 = 0; i2 < allowedToOpenBoxes; ++i2) {
        if (isFound) continue;

        int randomNumber = new Random().nextInt(boxesList.size());
        int boxNumber = boxesList.get(randomNumber);
        int slipNumber = box.get(boxNumber);

        System.out.println(
            " "
                .concat(String.valueOf(prisionerNumber))
                .concat(" opened ")
                .concat(String.valueOf(boxNumber))
                .concat(" and got slip number ")
                .concat(String.valueOf(slipNumber))
                .concat(" (number of box opened ")
                .concat(String.valueOf(i2 + 1))
                .concat(")"));

        boxesList.remove(randomNumber);

        if (slipNumber == prisionerNumber) {
          isFound = true;
        }
      }

      if (!isFound) {
        return false;
      }
      boxesList = null;
    }

    return true;
  }

  public static boolean tryPlannedMethod(
      ArrayList<Integer> prisionersList, HashMap<Integer, Integer> box, int allowedToOpenBoxes) {
    System.out.println("Prisioners are going to open with planned method...");

    for (int i = 0; i < prisionersList.size(); ++i) {
      int prisionerNumber = prisionersList.get(i);
      ArrayList<Integer> boxesList = new ArrayList<>();
      boolean isFound = false;
      int boxNumber = prisionerNumber;

      for (int i2 = 0; i2 < prisionersList.size(); ++i2) {
        boxesList.add(i2 + 1);
      }

      for (int i2 = 0; i2 < allowedToOpenBoxes; ++i2) {
        if (isFound) continue;

        int slipNumber = box.get(boxNumber);

        System.out.println(
            " "
                .concat(String.valueOf(prisionerNumber))
                .concat(" opened ")
                .concat(String.valueOf(boxNumber))
                .concat(" and got slip number ")
                .concat(String.valueOf(slipNumber))
                .concat(" (number of box opened ")
                .concat(String.valueOf(i2 + 1))
                .concat(")"));

        for (int i3 = 0; i3 < boxesList.size(); ++i3) {
          if (boxesList.get(i3) == boxNumber) {
            boxesList.remove(i3);
            break;
          }
        }

        if (slipNumber == prisionerNumber) {
          isFound = true;
        } else {
          boxNumber = slipNumber;
        }
      }

      if (!isFound) {
        return false;
      }
      boxesList = null;
    }

    return true;
  }
}
