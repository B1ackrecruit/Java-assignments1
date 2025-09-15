import java.util.Objects;

/**
 * A pet owner who can have at most two pets ({@see Pet}).
 */
public class PetOwner {

  private final String name;

  private final Pet firstPet;

  private final Pet secondPet;

  /**
   * Initializes a pet owner with a name and two pets.
   *
   * @param name      Name of the pet owner (must not be null or empty)
   * @param firstPet  First pet (must not be null)
   * @param secondPet Second pet (can be null)
   */
  public PetOwner(final String name, final Pet firstPet, final Pet secondPet) {
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("name must not be null or empty");

    if (firstPet == null)
      throw new IllegalArgumentException("first pet must not be null");

    this.name = name;
    this.firstPet = firstPet;
    this.secondPet = secondPet;
  }

  /**
   * Initializes a pet owner with a name and one pet.
   *
   * @param name     Name of the pet owner (must not be null or empty)
   * @param firstPet First pet (must not be null)
   */
  public PetOwner(final String name, final Pet firstPet) {
    this(name, firstPet, null);
  }

  /**
   * Copy constructor, creates a deep copy of the owner.
   *
   * @param other Other owner to copy (must not be null)
   */
  public PetOwner(PetOwner other) {
    if (other == null)
      throw new IllegalArgumentException("other owner must not be null");

    this.name = other.name;
    this.firstPet = new Pet(other.firstPet);
    if (other.secondPet != null)
      this.secondPet = new Pet(other.secondPet);
    else
      this.secondPet = null;
  }

  public String getName() {
    return name;
  }

  public Pet getFirstPet() {
    return firstPet;
  }

  public Pet getSecondPet() {
    return secondPet;
  }

  /**
   * Takes care of pets.
   *
   * Feeds them, if they are hungry, puts them to bed, if they are sleepy, pays
   * with them, otherwise.
   */
  public void takeCareOfPets() {

    takeCareOfPet(firstPet);

    if (secondPet != null)
      takeCareOfPet(secondPet);
  }

  private void takeCareOfPet(Pet pet) {

    if (pet.getHungriness() >= pet.getSleepiness() && pet.getHungriness() >= pet.getSadness()) {
      pet.eat();
    } else if (pet.getSleepiness() >= pet.getHungriness() && pet.getSleepiness() >= pet.getSadness()) {
      pet.sleep();
    } else {
      pet.play();
    }

  }

  /**
   * Two pet owners are equal if all their attributes are equal (inluding their
   * pets).
   */
  @Override
  public boolean equals(Object other) {

    if (other == null)
      return false;

    if (other == this)
      return true;

    if (other.getClass() != this.getClass())
      return false;

    PetOwner otherPetOwner = (PetOwner) other;

    if (!this.name.equals(otherPetOwner.name))
      return false;

    if (!this.firstPet.equals(otherPetOwner.firstPet))
      return false;

    if (!Objects.equals(this.secondPet, otherPetOwner.secondPet))
      return false;

    return true;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    return String.format("%s%n - First pet: %s%n - Second pet: %s",
        name, firstPet, (secondPet != null ? secondPet : "none"));

  }

}
