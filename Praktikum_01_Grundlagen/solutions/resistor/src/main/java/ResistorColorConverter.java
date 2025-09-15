public class ResistorColorConverter{

  private static int colorToValue(String color){

    int number;

    switch (color.toUpperCase()){

      case "SCHWARZ":
        number = 0;
        break;

      case "BRAUN":
        number = 1;
        break;
        
      case "ROT":
        number = 2;
        break;
        
      case "ORANGE":
        number = 3;
        break;
        
      case "GELB":
        number = 4;
        break;
        
      case "GRÜN":
        number = 5;
        break;
        
      case "BLAU":
        number = 6;
        break;
        
      case "VIOLETT":
        number = 7;
        break;
        
      case "GRAU":
        number = 8;
        break;
        
      case "WEIß":
        number = 9;
        break;

      default:
        throw new IllegalArgumentException("Ungültiger Farbcode: " + color);
    }

    return number;

  }

  private static double colorToMultiplier(String color){
    int exponent;
    switch (color.toUpperCase()){

      case "SILBER":
        exponent = -2;
        break;

      case "GOLD":
        exponent = -1;
        break;

      default:
        exponent = colorToValue(color);
        break;
    }

    return Math.pow(10.0, exponent);
  }



  public static void main(String[] args){

    if (args.length != 3 && args.length != 4){
      System.out.println("Ungültige Anzahl an Argumenten!");
      return;
    }

    int colors = args.length;
    double multiplier = colorToMultiplier(args[colors-1]);

    double resistance = colorToValue(args[colors-2]) 
      + 10 * colorToValue(args[colors-3]);

    if (args.length == 4)
      resistance += 100 * colorToValue(args[colors-4]);

    resistance *= multiplier;

    System.out.printf("Der Widerstand hat den Wert: %f Ohm%n", resistance);


  }

}

