import java.io.*;
import java.util.*;

/*
 * This program reads in text file arguments from the command line and 
 * executes a series of tests to determine if strings follow the 
 * required pattern. It returns the results and prints out the results.
 * 
 * The Language class executes main(), which initiates the readLang() 
 * to read in command line arguments from a file. The Language class holds 
 * the verification stacks where the results of each test are stored. 
 * 
 */
public class Language {
    LinkedStack test1Valid = new LinkedStack();
    LinkedStack test2Valid = new LinkedStack();
    LinkedStack test3Valid = new LinkedStack();
    LinkedStack test4Valid = new LinkedStack();
    LinkedStack test5Valid = new LinkedStack();
    LinkedStack test6Valid = new LinkedStack();

    /*
     * Main method intakes the command line arguments and passes them with a call to
     * readLang().
     * 
     * input: command line arguments; first is output file, followed by variable
     * number of input files. All input files write to the same output
     */
    public static void main(String[] args) throws IOException
    {
        Language lang = new Language();
        String outFileLocation = args[0];
        int n; // number of varargs
        for (n = 1; n < args.length; n++) {
            lang.readLang(outFileLocation, args[n]);
        }
    }

    /*
     * This method takes inputs and depending on the value of the input char,
     * returns a statement indicating that a test was valid or invalid.
     * 
     * @ Input: char tOrF is the true or false determination of each test
     * 
     * @ Input: Language is a short string indicating which language the results are
     * valid for
     * 
     * @Output: Statement regarding the validity of the test
     */
    public String printValidityStmt(char tOrF, String language)
    {
        String results = " ";
        if (tOrF == 't') {
            results = "This language is valid for " + language;
            System.out.println(results);
        }
        else if (tOrF == 'f') {
            results = "This language is not valid for " + language;
            System.out.println(results);
        }

        return results;
    }

    /*
     * This method takes the string input from the text file specified as command
     * line arguments and inputs them, character by character one line at a time
     * into the language LinkedStack.
     * 
     * readLang calls the test methods with the language stack.
     * 
     * @input: command line args
     * 
     * @end state: input stack is copied once for each string test;
     * 
     * @end state: the original stack which holds the input string is reversed and
     * printed
     * 
     * @end state: all tests have been performed
     * 
     * calls methods: printValidityStmt
     * 
     * @throws IOException
     */
    public void readLang(String outFileLoc, String input) throws IOException
    {
        Test6 test6 = new Test6();
        Test5 test5 = new Test5();
        Test4 test4 = new Test4();
        Test3 test3 = new Test3();
        Test2 test2 = new Test2();
        Test1 test1 = new Test1();
        WriteResults wr = new WriteResults();
        LinkedStack test1Return = new LinkedStack();
        LinkedStack test2Return = new LinkedStack();
        LinkedStack test3Return = new LinkedStack();
        LinkedStack test4Return = new LinkedStack();
        LinkedStack test5Return = new LinkedStack();
        LinkedStack test6Return = new LinkedStack();
        LinkedStack language = new LinkedStack();
        LinkedStack language1 = new LinkedStack();
        LinkedStack language2 = new LinkedStack();
        LinkedStack language3 = new LinkedStack();
        LinkedStack language4 = new LinkedStack();
        LinkedStack language5 = new LinkedStack();
        LinkedStack language6 = new LinkedStack();

        BufferedReader readlang = new BufferedReader(
                        new FileReader(new File(input)));
        try {
            int y;
            char tempChar;

            while ((y = readlang.read()) != -1) {
                tempChar = (char) y;
                while (y != -1 && tempChar != '\r') {
                    if (tempChar != '\n') {
                        language.push(tempChar);
                    }
                    y = readlang.read();
                    tempChar = (char) y;
                }
                language.reverse();
                language1 = language.copy();
                language2 = language.copy();
                language3 = language.copy();
                language4 = language.copy();
                language5 = language.copy();
                language6 = language.copy();
                language.printAll(); // print one string then each result
                wr.writeFile(outFileLoc, language);
                try {
                    String results = null;
                    test1Return = test1.testL1(language1);
                    results = printValidityStmt(test1Return.pop().data, "L1");
                    wr.writeFile(outFileLoc, results);
                    test2Return = test2.testL2(language2);
                    results = printValidityStmt(test2Return.pop().data, "L2");
                    wr.writeFile(outFileLoc, results);
                    test3Return = test3.testL3(language3);
                    results = printValidityStmt(test3Return.pop().data, "L3");
                    wr.writeFile(outFileLoc, results);
                    test4Return = test4.testL4(language4);
                    results = printValidityStmt(test4Return.pop().data, "L4");
                    wr.writeFile(outFileLoc, results);
                    test5Return = test5.testL5(language5);
                    results = printValidityStmt(test5Return.pop().data, "L5");
                    wr.writeFile(outFileLoc, results);
                    test6Return = test6.testL6(language6);
                    results = printValidityStmt(test6Return.pop().data, "L6");
                    wr.writeFile(outFileLoc, results);

                } catch (EmptyStackException esex) {
                    esex.printStackTrace();
                } catch (NullPointerException npex) {
                    System.out.println("Here");
                    npex.printStackTrace();
                }
                language.clear();
            }

            readlang.close();
            // return language;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // public void writeFile( String outputSpot, String results ) {
    // BufferedWriter writer= null;
    //
    // try {
    // String ouputRes = results;
    // File outFile = new File (outputSpot);
    //
    //
    // if (!outFile.exists())
    // {
    // outFile.createNewFile();
    // }
    //
    // FileWriter newWriter = new FileWriter(outFile);
    // writer = new BufferedWriter(newWriter);
    // writer.write(results);
    // }
    // catch( IOException ioex)
    // {
    // ioex.printStackTrace();
    // }
    // }
}
