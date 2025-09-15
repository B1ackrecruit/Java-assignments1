
/** 
 * Enumeration of the different pet types.
 *
 * A pet type defines multipliers which are applied to the state values of the
 * pet when an action (e.g. play) is executed.
 */
public enum PetType {

  DOG(2,2,2), CAT(1,3,2), BIRD(0.75,1,0.75), RABBIT(0.5,0.5,1);

  private double happinessMultiplier;
  private double hungrinessMultiplier; 
  private double sleepinessMultiplier; 

  private PetType(double happinessMultiplier, double hungrinessMultiplier, double sleepinessMultiplier){
    this.happinessMultiplier = happinessMultiplier;
    this.hungrinessMultiplier = hungrinessMultiplier;
    this.sleepinessMultiplier = sleepinessMultiplier;
  }

  public double getHappinessMultiplier() {
    return happinessMultiplier;
  }

  public double getHungrinessMultiplier() {
    return hungrinessMultiplier;
  }

  public double getSleepinessMultiplier() {
    return sleepinessMultiplier;
  }


}
