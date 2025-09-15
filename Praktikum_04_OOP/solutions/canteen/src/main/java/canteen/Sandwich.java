package canteen;

import java.util.Arrays;


public class Sandwich extends CanteenProduct implements Ratable{

    /** The ingredients that make up a sandwich */
    private final SandwichIngredients[] ingredients;

    /** The sum of the ratings */
    private int sumRatings;

    /** The number of ratings */
    private int countRatings;

    /**
     * The constructor of the Sandwich class
     * @param name the name of the sandwich
     * @param ingredients the ingredients of the sandwich
     * @throws IllegalArgumentException 
     *          thrown if name is null or has less than 4 characters
     * @throws SandwichHasTooFewIngredientsException
     *          thrown if a sandwich has less than 2 ingredients
     * @throws SandwichHasNoBreadException
     *          thrown if a sandwich has no bread
     */
    public Sandwich(String name, SandwichIngredients... ingredients){
        super(name);
        if(ingredients.length < 2){
            throw new SandwichHasTooFewIngredientsException("A sanchwich must have at least two ingredients");
        }

        if(!ingrediantsContainBread(ingredients)){
            throw new SandwichHasNoBreadException("A sandwich without bread is not a sandwich");
        }

        this.ingredients = ingredients;
    }


    /**
     * Checks whether the transferred ingredients contain bread
     * @param ingredients the ingredients to be checked
     * @return a statement about whether the ingredients handed over contain bread
     */
    private boolean ingrediantsContainBread(SandwichIngredients[] ingredients){
        for(SandwichIngredients si: ingredients){
            if(si == SandwichIngredients.WHOLE_GRAIN_BREAD || si == SandwichIngredients.BREAD)
                return true;
        }
        return false;
    }

    /**
     * Provides the price of a sandwich. The price is determined by the four most expensive ingredients.
     * All other ingredients are free of charge.
     */
    @Override
    public double getPrice() {
        double price = 0;
        int maxCountIngredients  = 4;
        int counter = ingredients.length < maxCountIngredients?ingredients.length:maxCountIngredients;

        sortIngredientsDescByPrice();
        for(int i = 0 ; i < counter; i++){
            price+=ingredients[i].getPrice();
        }
        return price;
    }

    /**
     * Provides the sum of kcal of all ingredients of a sandwich
     * @return The sum of kcal
     */
    public int getKcal(){
        int sumKcal = 0;
        for(SandwichIngredients si: ingredients){
            sumKcal += si.getKcal();
        }
        return sumKcal;
    }

    /**
     * The method uses a simple BubbleSort to sort the ingredients attribute based on the 
     * price of the ingredients. It is used for calculating the total price.
     */
    private void sortIngredientsDescByPrice(){
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < ingredients.length - 1; i++) {
                if (ingredients[i].getPrice() < ingredients[i + 1].getPrice()) {
                    // Swap people[i] and people[i + 1]
                    SandwichIngredients temp = ingredients[i];
                    ingredients[i] = ingredients[i + 1];
                    ingredients[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /**
     * Allows the rating of the sandwich in a scale of points from 1 to 5
     * @param rating das abzugebende Rating
     * throws IllegalArgumentException
     *          throw if the rating is outside the range from 1 to 5.
     */
    @Override
    public void rateProduct(int rating){
        if(rating<1 || rating>5){
            throw new IllegalArgumentException("The rating must be between 1 and 5.");
        }
        this.countRatings++;
        this.sumRatings+=rating;
    }

    
    /** 
     * Returns the average rating of the product
     * @return double the average rating of the product
     */
    @Override
    public double getAvgRating(){
        return (double)sumRatings/countRatings;
    }

    /** 
     * Returns the number of ratings
     * @return int the number of ratings
     */
    @Override
    public int getNumberOfRatings(){
        return countRatings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(ingredients);
        result = prime * result + sumRatings;
        result = prime * result + countRatings;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sandwich other = (Sandwich) obj;
        if (!Arrays.equals(ingredients, other.ingredients))
            return false;
        if (sumRatings != other.sumRatings)
            return false;
        if (countRatings != other.countRatings)
            return false;
        return true;
    }

    @Override
    public String toString(){

        return String.format("%s(%d kcal)\t\t\t\t%.2f",this.getName(), this.getKcal(), this.getPrice());
    }

    
    
}
