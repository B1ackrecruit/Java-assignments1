package canteen;

/**
 * CanteenProduct is the abstract superclass for all products offered in a canteen.
 */
public abstract class CanteenProduct {

    /** The name of the object */
    private final String name;

    /** The product on which the current is based.*/
    private final CanteenProduct baseProduct;

    /**
     * The constructor that expects only a string (name) as a parameter. baseProduct is set to null.
     * @param name The name of the object
     * @throws IllegalArgumentException 
     *          thrown if name is null or has less than 3 characters
     */
    public CanteenProduct(String name){
        this(name,null);
    }

    /**
     * The constructor that expects name and baseProduct as parameters.
     * @param name The name of the object
     * @param baseProduct The product on which the current is based.
     * @throws IllegalArgumentException 
     *          thrown if name is null or has less than 4 characters
     */
    public CanteenProduct(String name, CanteenProduct baseProduct){
        if(name == null || name.isEmpty() || name.trim().length() < 4){
            throw new IllegalArgumentException("Name is null or has less than 3 characters");
        }

        this.name = name.trim();
        this.baseProduct = baseProduct;
    }

    public CanteenProduct getBaseProduct(){
        return this.baseProduct;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((baseProduct == null) ? 0 : baseProduct.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CanteenProduct other = (CanteenProduct) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (baseProduct == null) {
            if (other.baseProduct != null)
                return false;
        } else if (!baseProduct.equals(other.baseProduct))
            return false;
        return true;
    }
    
}
