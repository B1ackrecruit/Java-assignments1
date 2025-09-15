public class Ziegenproblem{

  private final static int ITERATIONS = 10_000_000;

  private static int getRemainingDoor(int door1, int door2){

    if (door1 == 0 && door2 == 1 || door1 == 1 && door2 ==0)
      return 2;

    if (door1 == 1 && door2 == 2 || door1 == 2 && door2 ==1)
      return 0;

    return 1;

  }

  public static void main(String[] args){

    int winsA = 0; // Anzahl Hauptgewinne für Strategie A
    int winsB = 0; // Anzahl Hauptgewinne für Strategie B

    for (int i = 0; i < ITERATIONS; i++){

      int candidateDoor = (int)(Math.random()*3);
      int goatDoor;

      // Showmaster
      if (candidateDoor == 0)        
        goatDoor = Math.random()<0.5 ? 1 : 2;
      else
        goatDoor = getRemainingDoor(0, candidateDoor);

      // Gewinnshowteilnehmer
      
      // Strategie: Bei Tür bleiben
      if (candidateDoor == 0)
        winsA++;

      // Strategie: Tür wechseln
      candidateDoor = getRemainingDoor(candidateDoor, goatDoor);
      if (candidateDoor == 0)
        winsB++;

    }

    System.out.printf("Strategie A: %d Gewinne (%.2f%%)%n", winsA, 100.0*winsA/ITERATIONS);
    System.out.printf("Strategie B: %d Gewinne (%.2f%%)%n", winsB, 100.0*winsB/ITERATIONS);
    

  }

}

