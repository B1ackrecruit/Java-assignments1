
/**
 * A pet with a name a type ({@see PetType}) and three state values
 * (happiness, hungriness, sleepiness).
 */
public class Pet{

  private final String name;
  private final PetType type;
  private int happiness;
  private int hungriness;
  private int sleepiness;

  /**
   * Constructor to initialize a pet with a name and type.
   *
   * The initial happiness is set to 5, hungriness to 3 and sleepiness to to 1.
   *
   * @param name Name of pet (must not be null or empty)
   * @param type Type of pet (must not be null)
   */
  public Pet(String name, PetType type){
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("Name must not be null or empty");

    if (type == null)
      throw new IllegalArgumentException("Type must not be null");

    this.name = name;
    this.type = type;

    this.happiness = 5;
    this.hungriness = 3;
    this.sleepiness = 1;

  }

  /**
   * Copy constructor to create a (deep) copy of a pet.
   *
   * @param other Other pet to copy
   */
  public Pet(Pet other){

    if (other == null)
      throw new IllegalArgumentException("other pet must not be null");

    this.name = other.name;
    this.type = other.type;
    this.happiness = other.happiness;
    this.hungriness = other.hungriness;
    this.sleepiness = other.sleepiness;

  }

  public String getName(){
    return name;
  }

  public PetType getType(){
    return type;
  }

  public int getHappiness(){
    return happiness;
  }


  /**
   * Returns {@code true} if happiness less than or equal to 2; {@code false}
   * otherwise.
   */
  public boolean isSad(){
    return happiness <= 2;
  }

  /**
   * Returns {@code true} if happiness greater than or equal to 8; {@code false}
   * otherwise.
   */
  public boolean isHappy(){
    return happiness >= 8;
  }

  /**
   * Returns how sad the pet is, where sadness is defined as 10-happiness.
   */
  public int getSadness(){
    return 10-happiness;
  }

  /**
   * Changes the happiness value by the given amount.
   *
   * The happiness value is not changed beyond its bounds of 1 to 10 (inclusive).
   *
   * @param change value by which to change the happiness value.
   * @return new happiness value
   */
  public int changeHappiness(int change){
    this.happiness = clamp(0,10,happiness+change);
    return this.happiness;
  }

  public int getSleepiness(){
    return sleepiness;
  }

  /**
   * Changes the sleepiness value by the given amount.
   *
   * The sleepiness value is not changed beyond its bounds of 1 to 10 (inclusive).
   *
   * @param change value by which to change the sleepiness value.
   * @return new sleepiness value
   */
  public int changeSleepiness(int change){
    this.sleepiness = clamp(0,10,sleepiness+change);
    return this.sleepiness;
  }

  public int getHungriness(){
    return hungriness;
  }

  /**
   * Changes the hungriness value by the given amount.
   *
   * The hungriness value is not changed beyond its bounds of 1 to 10 (inclusive).
   *
   * @param change value by which to change the hungriness value.
   * @return new hungriness value
   */
  public int changeHungriness(int change){
    this.hungriness = clamp(0,10,hungriness+change);
    return this.hungriness;
  }

  private void change(int happinessChange, int hungrinessChange, int sleepinessChange){
    happinessChange = (int) Math.round(happinessChange * this.type.getHappinessMultiplier());
    sleepinessChange = (int) Math.round(sleepinessChange * this.type.getSleepinessMultiplier());
    hungrinessChange = (int) Math.round(hungrinessChange * this.type.getHungrinessMultiplier());

    this.changeHappiness(happinessChange);
    this.changeSleepiness(sleepinessChange);
    this.changeHungriness(hungrinessChange);
  }

  /**
   * Play with the pet.
   *
   * Changes happiness by a value of +2, sleepiness by +1, and hungriness by +1.
   * These values pre-multiplied with the characteristic pet type multipliers
   * {@see PetType}.
   */
  public void play(){
    change(2,2,2);
  }

  /**
   * Give the pet something to eat.
   *
   * Changes happiness by a value of +1, sleepiness by +1, and hungriness by -2.
   * These values pre-multiplied with the characteristic pet type multipliers.
   * {@see PetType}.
   */
  public void eat(){
    change(1,-2,1);
  }

  /**
   * Let the pet sleep.
   *
   * Changes happiness by a value of +1, sleepiness by -3, and hungriness by +2.
   * These values pre-multiplied with the characteristic pet type multipliers.
   * {@see PetType}.
   */
  public void sleep(){
    change(-2,2,-2);
  }


  /**
   * Returns true if all attributes of the pets are equal.
   */
  @Override public boolean equals(Object other){
    if (other == null)
      return false;

    if (other == this)
      return true;

    if (this.getClass() != other.getClass())
      return false;

    Pet otherPet = (Pet) other;

    if (!name.equals(otherPet.name))
      return false;

    if (type != otherPet.type)
      return false;

    if (happiness != otherPet.happiness)
      return false;

    if (sleepiness != otherPet.sleepiness)
      return false;

    if (hungriness != otherPet.hungriness)
      return false;

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override public String toString(){

    String mood = ":-|";

    if (isHappy())
      mood = ":-)";

    if (isSad())
      mood = ":-(";


    return String.format("%s %s (%s): Ha: %d, Hu: %d, Sl: %d",
        name, mood, type, happiness, hungriness, sleepiness);

  }
  
  private int clamp(int min, int max, int value){
    return Math.max(min, Math.min(max, value));
  }


}
