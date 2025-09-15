package canteen;

public class SandwichHasNoBreadException extends RuntimeException{

    public SandwichHasNoBreadException(String message){
        super(message);
    }

    public SandwichHasNoBreadException(Throwable cause){
        super(cause);
    }

    public SandwichHasNoBreadException(String message, Throwable cause){
        super(message, cause);
    }
    
}
