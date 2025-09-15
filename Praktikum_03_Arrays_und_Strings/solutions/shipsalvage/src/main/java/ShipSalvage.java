
import static java.lang.System.out;

import java.util.Random;
import java.util.Scanner;

public class ShipSalvage {

  private static final char[][] exampleMap = {
               // A   B   C   D   E   F   G   H   I   J
      /*  1 */ { 'O','O',' ','O',' ',' ','O','O','O','O' },
      /*  2 */ { ' ',' ',' ','O',' ',' ',' ',' ',' ',' ' },
      /*  3 */ { 'O',' ',' ','O',' ','O','O','O',' ','O' },
      /*  4 */ { 'O',' ',' ',' ',' ',' ',' ',' ',' ','O' },
      /*  5 */ { 'O',' ',' ',' ',' ',' ',' ',' ',' ',' ' },
      /*  6 */ { 'O',' ',' ',' ',' ','O',' ',' ',' ','O' },
      /*  7 */ { ' ',' ',' ',' ',' ','O',' ',' ',' ','O' },
      /*  8 */ { 'O','O',' ',' ',' ',' ',' ',' ',' ','O' },
      /*  9 */ { ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' },
      /* 10 */ { ' ',' ',' ',' ','O','O','O','O','O','O' }
    };

  public static FieldState[][] getExample(){

    FieldState[][] map = new FieldState[10][10];

    for (int row = 0; row < 10; row++){
      for (int column = 0; column < 10; column++){
        map[row][column] = FieldState.fromOutput(exampleMap[row][column]);
      }
    }

    return map;


  }

  public static void printRowSeparator(){
    out.print("  |");
    for (int column = 0; column < 9; column++){
      out.print("---+");
    }
    out.println("---|");
  }

  public static void checkValidMap(FieldState[][] map){

    if (map == null)
      throw new IllegalArgumentException("map must not be null");

    if (map.length != 10)
      throw new IllegalArgumentException("map must have exactly 10 rows");

    for (int row = 0; row < 10; row++){

      if (map[row] == null)
        throw new IllegalArgumentException("map rows must not be null");

      if (map[row].length != 10)
        throw new IllegalArgumentException("map row must have exactly 10 columns");

      for (int column = 0; column < 10; column++){

        if (map[row][column] == null)
          throw new IllegalArgumentException("no entry on the map must be null");
      }

    }

  }

  public static void printMap(FieldState[][] map, boolean showHidden){

    checkValidMap(map);

    out.println("   ABCDEFGHIJ");
    out.println("  +----------+");
    for (int row = 0; row < 10; row++){

      out.printf( "%2d|", (row+1));

      for (int column = 0; column < 10; column++){

        char output = map[row][column].getOutput();

        if (map[row][column] == FieldState.OCCUPIED_HIDDEN && !showHidden)
          output = FieldState.EMPTY.getOutput();

        out.print(output);


      }

      out.println("|");

    }
    out.println("  +----------+");


  }

  public static boolean allSalvaged(FieldState[][] map){

    checkValidMap(map);

    for (int row = 0; row < 10; row++){
      for (int column = 0; column < 10; column++){
        if (map[row][column] == FieldState.OCCUPIED_HIDDEN){
          return false;
        }
      }
    }

    return true;
  }

  public static void probeField(FieldState[][] map, String line){

      try {


        if (line.length() < 2){
          System.out.println("Bitte Spalte und Zeile eingeben");
          return;
        }

        char columnChar = Character.toUpperCase(line.charAt(0));

        if (Character.compare(columnChar, 'A') < 0 || Character.compare(columnChar, 'J') > 0){
          System.out.println("Ungültige Spalte! Einen Buchstaben zwischen A und J eingeben!");
          return;
        }

        // hacky but simple
        int column = columnChar - 'A';

        line = line.substring(1);
        int row = Integer.parseInt(line)-1;

        if (row < 0 || row > 9 ){
          System.out.println("Ungültige Zeile! Einen Zahl zwischen 1 und 10 eingeben!");
          return;
        }

        FieldState state = map[row][column];

        switch (state){

          case EMPTY:
            out.println("Nichts zu finden!");
            map[row][column] = FieldState.MISS;
            break;

          case OCCUPPIED_SALVAGED, MISS:
            out.println("Bereits untersucht!");
            break;

          case OCCUPIED_HIDDEN:
            out.println("Wrack gefunden!");
            map[row][column] = FieldState.OCCUPPIED_SALVAGED;
            break;

        }


      } catch (NumberFormatException exception){
        System.out.println("Ungültige Eingabe für Zeilennummer!");

      }

  }

  public static void main(String[] args){
    //FieldState[][] map = getExample();
    FieldState[][] map = generateRandomMap();

    Scanner input = new Scanner(System.in);

    while (!allSalvaged(map)){

      printMap(map, true);

      String field = input.next();
      probeField(map, field);


    }

    out.println("Alle Schiffe geborgen!");

    printMap(map, false);

    input.close();


  }

  // BONUS
  private static FieldState[][] generateRandomMap(){
                        //  0  1  2  3  4  5  6
    int[] amountPerSize = { 0, 0, 4, 3, 2, 0, 1 };
    FieldState[][] map = new FieldState[10][10];
    Random random = new Random();

    for (int row = 0; row < 10; row++){
      for (int column = 0; column < 10; column++){

        map[row][column] = FieldState.EMPTY;
      }
    }

    for (int size = 2; size < 7; size++){

      for (int i = 0; i < amountPerSize[size]; i++){

        boolean placed = false;
        

        do {
          boolean horizontal = random.nextBoolean();

          int startColumn = random.nextInt(0, (horizontal ? 10-size : 10));
          int startRow = random.nextInt(0, (!horizontal ? 10-size : 10));

          if (placementPossible(map, startColumn, startRow, size, horizontal)){
            int column = startColumn;
            int row = startRow;
            for (int field = 0; field < size; field++){

              map[row][column] = FieldState.OCCUPIED_HIDDEN;

              if (horizontal)
                column++;
              else
                row++;

              placed = true;
            }
          }

        } while (!placed);

      }

    }

    printMap(map, true);

    return map;

  }

  private static boolean placementPossible(FieldState[][] map, int startColumn, int startRow, int size, boolean horizontal){

    int column = startColumn;
    int row = startRow;

    for (int i = 0; i < size; i++){

      if (map[row][column] != FieldState.EMPTY)
        return false;

      if (horizontal)
        column++;
      else
        row++;

    }

    return true;

  }

  
}
