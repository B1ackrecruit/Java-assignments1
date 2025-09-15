import java.util.Scanner;

public class PseudoRandomNumbers{

  private final static long MODULUS = 2_147_483_647;
  private final static long MULTIPLIER = 48_271;

  public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);

    long anzahl;

    do{
      System.out.print("Anzahl Zufallszahlen (>=0): ");
      anzahl = scanner.nextInt();
    } while (anzahl < 0);

    long seed;
    do {
      System.out.printf("Seed (>0): ");
      seed = scanner.nextInt();
    } while (seed <= 0);

    long min;
    System.out.print("Min: ");
    min = scanner.nextInt();

    long max;
    do {
      System.out.print("Max (exklusiv, >=Min): ");
      max = scanner.nextInt();
    } while (max < min);

    long x=seed;

    for (long i = 0; i < anzahl; i++){

      x = (x * MULTIPLIER) % MODULUS;

      long zufallszahl = min+(x % (max-min));
      System.out.printf("%d%n", zufallszahl);

    }
    scanner.close();
  }
}

