package canteen;

import java.util.Arrays;
/**
 * The class Coffee represents a coffee product
 */

public class Coffee extends CanteenProduct {

    private final CoffeeIngredients[] ingredients;

    /**
     * The constructor that takes a name and a set of ingredients as parameters
     * @param name the name of 
     * @param ingredients
     * @param name the name of the coffee
     * @param ingredients the ingredients that are added to the base product
     * @throws IllegalArgumentException 
     *          thrown if name is null or has less than 4 characters
     * @throws IllegalArgumentException
     *          thrown if a coffee product does not contain at least one additional ingredient
     */
    public Coffee(String name, CoffeeIngredients... ingredients){
        this(name,null,ingredients);
    }

    /**
     * The constructor, which expects the name, the baseProduct and a set of CoffeeIngredients as parameters
     * @param name the name of the coffee
     * @param baseProduct the base product of the coffee
     * @param ingredients the ingredients that are added to the base product
     * @throws IllegalArgumentException 
     *          thrown if name is null or has less than 4 characters
     * @throws IllegalArgumentException
     *          thrown if a coffee product does not contain at least one additional ingredient
     */
    public Coffee(String name, Coffee baseProduct, CoffeeIngredients... ingredients){
        super(name, baseProduct);
        if(ingredients.length==0){
            throw new IllegalArgumentException("A coffee product must contain at least one ingredient.");
        }
        this.ingredients = ingredients;
    }

    
    /** 
     * Returns the price of the coffee product. For this purpose, the price of the basic product is 
     * determined first. Then the prices of the additional ingredients are added.
     * @return double the price of the coffee product
     */
    @Override
    public double getPrice() {
        double price = super.getBaseProduct()!=null?super.getBaseProduct().getPrice():0;
        for(CoffeeIngredients ci: ingredients){
            price += ci.getPrice();
        }
        return price;
    }

    



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(ingredients);
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
        Coffee other = (Coffee) obj;
        if (!Arrays.equals(ingredients, other.ingredients))
            return false;
        return true;
    }

    @Override
    public String toString(){

        return String.format("%s\t\t\t\t%.2f",this.getName(),this.getPrice());
    }

     

    
    
}
