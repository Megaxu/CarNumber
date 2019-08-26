import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

  private static ArrayList<String> list = new ArrayList<>();
  private static HashSet<String> hSet = new HashSet<>();
  private static TreeSet<String> tSet = new TreeSet<>();

  private static final String REGEX_NUMBER = "[A-ZА-Я][0-9]{3}[A-ZА-Я]{2}[0-9]{1,4}";

  public static void main(String[] args) {

    char[] letters = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y',
        'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

    for (int i = 0; i < letters.length; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 10; k++) {
          for (int l = 0; l < 10; l++) {
            for (int m = 1; m < 198; m++) {
              if (j == k && j == l) {
                for (int x = 0; x < letters.length; x++) {
                  for (int y = 0; y < letters.length; y++) {
                    if (i != x && i != y & x != y) {
                      list.add(
                          letters[i] + Integer.toString(j) + k + l + letters[x] + letters[y] + m);
                    }
                  }
                }
              } else {
                list.add(letters[i] + Integer.toString(j) + k + l + letters[i] + letters[i] + m);
              }
            }
          }
        }
      }
    }

    hSet.addAll(list);
    tSet.addAll(list);
    Collections.sort(list);

    System.out.println("Всего сгенерировано номеров: " + list.size());
    System.out.println("Введите номер в формате X000XX0: ");
    Scanner scan = new Scanner(System.in);

    while (true) {
      String input = scan.nextLine();
      while (!input.matches(REGEX_NUMBER)) {
        System.out.println("Неправильно указан формат номера, повторите ввод");
        input = scan.nextLine();
      }
      enumeration(input);
      binarySearch(input);
      hashSearch(input);
      treeSearch(input);
    }
  }

  private static void enumeration(String input) {
    long start = System.currentTimeMillis();
    String got = list.contains(input) ? "найден" : "не найден";
    long finish = System.currentTimeMillis();
    long time = finish - start;
    System.out.println("Прямой перебор: " + "Номер " + got + " поиск занял " + time + " мс.");
  }

  private static void binarySearch(String input) {
    long start = System.currentTimeMillis();
    int index = Collections.binarySearch(list, input);
    long finish = System.currentTimeMillis();
    long time = finish - start;
    String got = "найден";
    if (index < 0) {
      got = "не найден";
    }
    System.out.println("Бинарный поиск: " + "Номер " + got + " поиск занял " + time + " мс.");
  }

  private static void hashSearch(String input) {
    long start = System.currentTimeMillis();
    String got = list.contains(input) ? "найден" : "не найден";
    long finish = System.currentTimeMillis();
    long time = finish - start;
    System.out.println("Поиск в HashSet: " + "Номер " + got + " поиск занял " + time + " мс.");
  }

  private static void treeSearch(String input) {
    long start = System.currentTimeMillis();
    String got = list.contains(input) ? "найден" : "не найден";
    long finish = System.currentTimeMillis();
    long time = finish - start;
    System.out.println("Поиск в TreeSet: " + "Номер " + got + " поиск занял " + time + " мс.");
  }
}