package canteen;

public class SandwichHasTooFewIngredientsException extends RuntimeException {

    public SandwichHasTooFewIngredientsException(String message){
        super(message);
    }

    public SandwichHasTooFewIngredientsException(Throwable cause){
        super(cause);
    }

    public SandwichHasTooFewIngredientsException(String message, Throwable cause){
        super(message, cause);
    }
    
}
