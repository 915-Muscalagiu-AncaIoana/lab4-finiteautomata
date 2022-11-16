import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FiniteAutomata {
    List<String> states = new ArrayList<>();
    List<String> finalStates = new ArrayList<>();
    List<String> alphabet = new ArrayList<>();
    String initialState;
    List<Transition> transitionList = new ArrayList<>();
    void parseInputFile(){
        int index = 0;
        Path filePath = Paths.get("FA.in");
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for(String line: lines) {
                line = line.strip();
                if (index == 0) {
                    List<String> tokens = List.of(line.split(","));
                    states.addAll(tokens);
                }
                else if (index == 1) {
                    List<String> tokens = List.of(line.split(","));
                    alphabet.addAll(tokens);
                }
                else if(index == 2){
                    initialState = line;
                }
                else if(index == 3){
                    List<String> tokens = List.of(line.split(","));
                    finalStates.addAll(tokens);
                }
                else {
                    String[] tokens = line.split(",");
                    Transition transition = new Transition(tokens[0],tokens[2],tokens[1]);
                    transitionList.add(transition);
                }
                index++;
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    public List<String> getStates () {
        return states;
    }

    public List<String> getFinalStates () {
        return finalStates;
    }

    public List<String> getAlphabet () {
        return alphabet;
    }

    public String getInitialState () {
        return initialState;
    }

    public List<Transition> getTransitionList () {
        return transitionList;
    }

    public Boolean isDFA(){
        HashMap<Tuple<String,String>,String> tuples = new HashMap<>();
        for (Transition transition : transitionList){
            Tuple tuple = new Tuple(transition.startState,transition.symbolTransition);
            if (tuples.containsKey(tuple)){
                return false;
            }
            else {
                tuples.put(tuple, transition.endState);
            }
        }
        return true;
    }


    public Boolean isSequenceAccepted(String currentState , String sequence){
        if(sequence.isEmpty() && finalStates.contains(currentState)){
            return true;
        }
        if(sequence.isEmpty()){
            return false;
        }

        for(Transition transition : transitionList){
            if(Objects.equals(transition.startState, currentState) && String.valueOf(sequence.charAt(0)).equals(transition.symbolTransition)){
                Boolean result = isSequenceAccepted(transition.endState,sequence.substring(1));
                if(result)
                    return true;
            }
        }
        return false;
    }
}
