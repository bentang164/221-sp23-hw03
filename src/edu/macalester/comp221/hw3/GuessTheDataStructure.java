package edu.macalester.comp221.hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuessTheDataStructure {
    
    private String guessDS(List<Integer> input, List<Integer> output) {
        List<Integer> backwards = new ArrayList<>();
        
        // If these are equivalent with no modification, it's either a Queue or a Priority Queue (or not sure/both).
        if (input.equals(output)) {
            // If these are equivalent and the first element in the output is the maximum value in the input, it could be either
            if (output.get(0) == Collections.max(input)) {
                return "not sure";
            }
            
            // Otherwise, it's definitely a queue.
            return "queue";
        }

        // If input doesn't equal output, it's either a Priority Queue, a Stack, or impossible
        if (!input.equals(output)) {
            // Only reverse if the size is greater than 1. 
            if (output.size() > 1) {
                for (int i = output.size() - 1; i >= 0; i--) {
                    backwards.add(output.get(i));
                }
            }

            // If the reversed output equals the input, it's either a Priority Queue or a Stack
            if (backwards.equals(input)) {
                // If the reversed output's first element is equal to the maximum value in the input, it could be either
                if (output.get(0) == Collections.max(input)) {
                    return "not sure";
                }

                // Otherwise, it's definitely a stack.
                return "stack";
            }
        }

        // If it can't be a stack or a queue and the first element in the output is equivalent to the maximum element in input, it's a priority queue
        if (output.get(0) == Collections.max(input)) {
            return "priority queue";
        }

        // barring everything else, it's impossible.
        return "impossible";
    }

    public static void main(String[] args) throws Exception {
        GuessTheDataStructure ds = new GuessTheDataStructure();
        List<Integer> input = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        
        File inputFile = new File("res/input.txt");
        BufferedReader readIn = new BufferedReader(new FileReader(inputFile));

        String element;

        while ((element = readIn.readLine()) != null) {
            // If the start of the line equals 1, it's an input
            // If it's 2, it's an output
            // Otherwise, we're done with that test case and should call the method, and then clear the lists.
            if (element.substring(0, 1).equals("1") && element.length() > 1) {
                input.add(Integer.parseInt(element.substring(2, 3)));
            } else if (element.substring(0, 1).equals("2") && element.length() > 1) {
                output.add(Integer.parseInt(element.substring(2, 3)));
            } else if (input.size() != 0 && output.size() != 0) {
                System.out.println(ds.guessDS(input, output));

                input.clear();
                output.clear();
            }
        }

        readIn.close();  
    }
}