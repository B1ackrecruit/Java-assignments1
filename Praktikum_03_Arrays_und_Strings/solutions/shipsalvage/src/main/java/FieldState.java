
public enum FieldState {

  EMPTY(' '), MISS('X'), OCCUPIED_HIDDEN('O'), OCCUPPIED_SALVAGED('#');

  private char output;

  private FieldState(char output) {
    this.output = output;
  }

  public char getOutput() { 
    return output; 
  }

  public static FieldState fromOutput(char output){

    for (FieldState state : values()){
      if (state.getOutput() == output){
        return state;
      }
    }

    throw new IllegalArgumentException("No field state for output character " + output);


  }


}
