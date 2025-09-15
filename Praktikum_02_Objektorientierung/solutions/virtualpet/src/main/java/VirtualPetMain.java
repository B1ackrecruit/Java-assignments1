
/**
 * Main class containing the main-method which executes some interactions
 * between Owner and Pet
 */
public class VirtualPetMain{

  public static void main(String[] args) {

    // Four pets
    Pet rosco = new Pet("Rosco", PetType.DOG);
    Pet morgana = new Pet("Morgana", PetType.CAT);
    Pet rabbit = new Pet("Rabbit of Caerbannog", PetType.RABBIT);

    // Output
    System.out.println(rosco);
    System.out.println(morgana);
    System.out.println(rabbit);

    // equality and identity
    Pet roscoClone = new Pet(rosco);
    System.out.printf("roscoClone.equals(rosco): %b%n", roscoClone.equals(rosco));
    System.out.printf("roscoClone == rosco: %b%n", roscoClone == rosco);
    roscoClone = rosco;
    System.out.printf("roscoClone == rosco: %b%n", roscoClone == rosco);

    // Owners
    PetOwner jimmy = new PetOwner("Jimmy", rosco, rabbit); 
    PetOwner timmy = new PetOwner("Timmy", morgana);

    // Output
    System.out.println(jimmy);
    System.out.println(timmy);

    // Create clone
    PetOwner jimmyClone = new PetOwner(jimmy);

    System.out.printf("jimmyClone.equals(jimmy) = %b%n", jimmyClone.equals(jimmy));
    System.out.printf("jimmyClone == jimmy = %b%n", jimmyClone == jimmy);
    System.out.printf("jimmyClone.getFirstPet().equals(jimmy.getFirstPet()) = %b%n", jimmyClone.getFirstPet().equals(jimmy.getFirstPet()));
    System.out.printf("jimmyClone.getFirstPet() == jimmy.getFirstPet() = %b%n", jimmyClone.getFirstPet() == jimmy.getFirstPet());

    // 
    for (int i = 0; i < 10; i++){
      jimmy.takeCareOfPets();
      timmy.takeCareOfPets();

      System.out.printf("Iteration %d%n", i);
      System.out.println(jimmy);
      System.out.println(timmy);
      System.out.println();
    }
    

  }


}


