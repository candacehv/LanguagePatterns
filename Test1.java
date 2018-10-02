import java.io.IOException;
/*
 * Test 1 determines whether each string is comprised of equal numbers 
 * of As and Bs and no other characters.
 * 
 * Additional characters after the initial As and Bs in the pattern
 * are invalid.
 * 
 * It holds the following stacks:
 * in1: This stack holds all valid As
 * 
 * in2: this stack  holds all valid Bs
 * 
 * in3: this stack holds values that are not valid. If not empty
 * once manipulations have concluded the string is not valid for L1.
 * 
 * tempChar holds the data value (char) of the current stack node.
 */
public class Test1 {
    
    /*
     * Purpose: This method checks the validity of each string passed in from the
     * inputs to determine if they contain equal numbers of As and Bs and
     * nothing else. It returns the results via the
     * test1Valid stack to the printValidityStmt method. First, the function reads
     * in characters from the input stack and assigns them to in1 in2 or in3. If it
     * is determined at any point that the string is invalid, the char 'f' will be
     * pushed to the test1Valid stack and returned to the calling method, readLang,
     * which then sends it to the printValidityStmt to return readable results.
     * 
     * This method pushes all As onto in1 and all Bs onto in2 regardless of their 
     * place in the string. All characters not equal to A or B are pushed onto in3 
     * and those strings are marked invalid by pushing a 't' to the test2Valid 
     * stack. 
     * 
     * inputs: from readLang, LinkedStack language1 (language), which holds the
     * string to be checked for validity
     * 
     * returns: test1Valid stack which holds 1 item, which is a char, 't' if the
     * statement is valid, 'f' is it is not.
     * 
     * The state of language1 string passed from readLang and all class stacks will
     * be altered through this method in ways that depend on the string.
     */
    public LinkedStack testL1(LinkedStack language) throws IOException
    {
        Language lang = new Language();

        LinkedStack in1 = new LinkedStack(); // hold As
        LinkedStack in2 = new LinkedStack(); // hold Bs
        LinkedStack in3 = new LinkedStack(); // hold anything else

        
        if (language.isEmpty())
        {
//            System.out.println("handling es");
            lang.test1Valid.push('t');
            return lang.test1Valid;
        }
        while (!language.isEmpty()) {
            char tempChar = language.pop().data;
            if (tempChar == 'A') {
                in1.push(tempChar);
            }

            else if (tempChar == 'B') {
                in2.push(tempChar);
            }

            if (tempChar != 'A' && tempChar != 'B') {
                in3.push(tempChar);
                lang.test1Valid.push('f');
                return lang.test1Valid;
            }
        }

            while (!in1.isEmpty() && !in2.isEmpty() ) // as soon as one empty, check that other is also
            {
                in1.pop();
                in2.pop();
            }
            if (in1.isEmpty()  && in2.isEmpty() && in3.isEmpty() ) {
                lang.test1Valid.push('t');
                return lang.test1Valid;
                // System.out.println("This language is in L1.");
            }
            else {
                lang.test1Valid.clear();
                lang.test1Valid.push('f');
                return lang.test1Valid;
                // System.out.println("This language is not in L1.");
            }
    }
}
