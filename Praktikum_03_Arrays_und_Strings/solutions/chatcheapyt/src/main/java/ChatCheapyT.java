import java.util.Scanner;

public class ChatCheapyT{

  private static String handleSilence(String input){

    if (input.isBlank())
      return "Dann sage ich aber auch nichts!";
    else
      return null;

  }

  private static String handleTooLong(String input){

    if (input.length() > 50)
      return "Das ist mir zuviel zu lesen! Bitte kürzen Sie Ihre Anfrage!";
    else
      return null;

  }

  private static String handleExam(String input){

    if (input.equalsIgnoreCase("Was kommt in der Klausur dran?"))
      return "Die Klausur orientiert sich an den Praktika!";
    else
      return null;

  }

  private static String handleQuestion(String input){

    if (input.endsWith("?"))
      return "Tut mir leid, aber die ChatCheapyT-Server sind gerade ausgelastet! Schließen Sie bitte ein ChatCheapyT-Pro-Abo ab!";
    else
      return null;

  }

  private static String handleExclamation(String input){

    if (input.contains("!")){
      if (input.toUpperCase().contains("BITTE"))
        return "Als Antwort habe ich ein YouTube-Video generiert: https://youtu.be/dQw4w9WgXcQ";
      else
        return "Wie ist das Zauberwort?";
    }

    return null;

  }

  private static String handleScream(String input){

    int uppercaseCount = 0;
    int letterCount = 0;

    for (int i = 0; i < input.length(); i++){
      char character = input.charAt(i);
      if (Character.isLetter(character)){

        letterCount++;

        if (Character.isUpperCase(character)){
          uppercaseCount++;
        }

      }
        
    }

    if (2*uppercaseCount >= letterCount)      
      return "Bitte schreien Sie mich nicht an!";
    else
      return null;

  }

  private static String handleChatGPT(String input){

    if (input.contains("ChatGPT")){
      return "Sie meinten wohl: " + input.replace("ChatGPT", "ChatCheapyT");
    } else
      return null;

  }


  private static String handleReverse(String input){

    if (input.toUpperCase().startsWith("UMDREHEN:")){
      StringBuffer output = new StringBuffer();
      String toReverse = input.substring(9).trim();

      for (int i = toReverse.length() - 1; i >= 0; i--){
        output.append(toReverse.charAt(i));
      }

      return output.toString();
    } else
      return null;

  }

  private static String handleAdd(String input){

    if (input.toUpperCase().startsWith("ADDIERE") && input.length()>7){
      String remainder = input.substring(8);

      String[] parts = remainder.split(" ");

      if (parts.length != 2)
        return "Ich kann nur zwei Zahlen addieren!";

      try {
        double first = Double.parseDouble(parts[0]);
        double second = Double.parseDouble(parts[1]);

        return String.format("%f plus %f ist gleich %f! Take that, ChatGPT!", first, second, first+second);


      } catch (NumberFormatException e){
        return "Diese Zahlen verstehe ich nicht!";
      }

    }
    return null;

  }

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    String prompt = null;

    do {

      System.out.print("Prompt: ");
      prompt = input.nextLine(); 

      String answer = handleSilence(prompt);

      if (answer == null)
        answer = handleTooLong(prompt);

      if (answer == null)
        answer = handleExam(prompt);

      if (answer == null)
        answer = handleQuestion(prompt);

      if (answer == null)
        answer = handleExclamation(prompt);

      if (answer == null)
        answer = handleScream(prompt);

      if (answer == null)
        answer = handleReverse(prompt);

      if (answer == null)
        answer = handleAdd(prompt);

      if (answer == null)
        answer = handleChatGPT(prompt);

      if (prompt.equalsIgnoreCase("bye"))
        continue;

      if (answer != null)        
        System.out.println("ChatCheapyT: " + answer);
      else
        System.out.println("ChatCheapyT: Ich verstehe Sie leider nicht!");


    } while (!prompt.equalsIgnoreCase("bye"));

    System.out.println("Bye!");

    input.close();
    
  }





}
