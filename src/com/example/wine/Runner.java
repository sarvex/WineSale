package com.example.wine;

/**
 * This is the starting point of Applicaiton. The whole operation is
 * divided in three parts
 *  - Reader reads the input and generates the required data structures
 *  - Processor does not necessary computation for sales
 *  - Writer generates the output file in the format specified.
 *
 *  Runner just joins together all the actions.
 *
 * Created by Sarvex on 18/03/2015.
 */
public class Runner {

    /**
     *  First command line argument is assumed to be string specifying the path of input file
     *  Second command line argument is assume to be string specifying the path of output file
     *
     *  Proper defaults are given for both the parameters.
     *
     * @param args
     */
    public static void main(String[] args) {
        String input = "person_wine_4.txt";
        if (args.length > 0) {
            input = args[0];
        }
        Reader.read(input);

        Processor.initialize();

        String output = "sale.txt";
        if (args.length > 1) {
            output = args[1];
        }
        Writer.write(output);
    }
}
