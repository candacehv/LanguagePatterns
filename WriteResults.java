import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/*
 * This class holds the variables for file inputs and outputs. 
 * It has two methods for writing to a file, one that takes two 
 * String args to write the output statement in human readable form
 * and an overloaded method that accepts a String and a LinkedList
 * to write the original input string to file. 
 * 
 * This is the only class that makes uses of Strings
 */



public class WriteResults {
    
    
    /*
     * This method takes inputs String, String to write the results
     * statement to file. 
     * 
     * input: outputSpot is the file name for the output file
     * 
     * input: results is the input returned from the 
     * printValidityMethod
     * 
     * returns: none
     * 
     * end state: a file is written than contains the results for all
     * test strings in all input files.
     */
    public void writeFile( String outputSpot, String results ) {
        BufferedWriter writer= null; 
        FileWriter newWriter;
        
        try {
            String outputRes = results;
            File outFile = new File (outputSpot); 
            
            
            if (!outFile.exists())
            {
                outFile.createNewFile();
            }
            
            newWriter = new FileWriter(outFile, true); 
            writer = new BufferedWriter(newWriter); 
            writer.write(outputRes);
            writer.newLine();
            writer.flush();
            writer.close();
        }
        catch( IOException ioex)
        {
            ioex.printStackTrace();
        }
        finally
        {
            
        }
    } 
    

    /*
     * @overload 
     * 
     * This method accomplishes the same as the above, but accepts
     * a String and a LinkedList. It prints the original string to file
     * 
     * returns: none
     * 
     * end state: string has been written to file
     */
    public void writeFile( String outputSpot, LinkedStack origStrTest ) {  
        BufferedWriter writer= null; 
        FileWriter newWriter;
        try {
            LinkedStack outputRes = origStrTest;
            File outFile = new File (outputSpot); 
            
            
            if (!outFile.exists())
            {
                outFile.createNewFile();
            }
            
            newWriter = new FileWriter(outFile, true); 
            writer = new BufferedWriter(newWriter); 
            writer.newLine();
            while (!origStrTest.isEmpty())
            {
                writer.write(outputRes.pop().data);
            }
            writer.newLine();
            writer.flush();
            writer.close();
        }
        catch( IOException ioex)
        {
            ioex.printStackTrace();
        }
    } 
}
