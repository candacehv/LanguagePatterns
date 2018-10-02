import java.io.IOException;

/*
 * Test 2 determines whether each string complies with the following
 * pattern: 
 * 
 * (A^nB^n) Examples of a valid input is: 
 * AABB or AB
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
 * once manipulations have concluded the string is not valid for L2.
 * 
 * tempChar holds the data value (char) of the current stack node.
 */
public class Test2 {

    /*
     * Purpose: This method checks the validity of each string passed in from the
     * inputs against the (A^nB^n) pattern. It returns the results via the
     * test2Valid stack to the printValidityStmt method. First, the function reads
     * in characters from the input stack and assigns them to in1 in2 or in3. If it
     * is determined at any point that the string is invalid, the char 'f' will be
     * pushed to the test2Valid stack and returned to the calling method, readLang,
     * which then sends it to the printValidityStmt to return readable results.
     * 
     * This method uses methods push, pop, and peek heavily to determine validity of
     * each character based on its position in the string relative to other
     * characters.
     * 
     * inputs: from readLang, LinkedStack language2 (language), which holds the
     * string to be checked for validity
     * 
     * returns: test2Valid stack which holds 1 item, which is a char, 't' if the
     * statement is valid, 'f' is it is not.
     * 
     * The state of language2 string passed from readLang and all class stacks will
     * be altered through this method in ways that depend on the string.
     */
    public LinkedStack testL2(LinkedStack language) throws IOException // fix later
    {
        Language lang = new Language();
        LinkedStack in1 = new LinkedStack(); // hold As
        LinkedStack in2 = new LinkedStack(); // hold Bs
        LinkedStack in3 = new LinkedStack(); // hold anything else

        char tempChar;
        if (language.isEmpty()) //empty string
        {
            lang.test2Valid.push('f');
            return lang.test2Valid;
        }
        
        
        while (!language.isEmpty()) {   // assign chars to stacks
            tempChar = language.pop().data;
            if (tempChar != 'A' && tempChar != 'B' && tempChar != '\n') // invalid letters
            {

                in3.push(tempChar);
                lang.test2Valid.push('f');
                return lang.test2Valid;
            }
            if (tempChar == 'A' && in2.isEmpty() && in3.isEmpty()) // starts with A
            {
                in1.push('A');
            }
            
            if (tempChar == 'A' && !in2.isEmpty()) {
                //More after valid As and Bs
                in3.push('A');
                lang.test2Valid.push('f');
                return lang.test2Valid;
            }
            
            if (tempChar == 'B' && in1.isEmpty() && in3.isEmpty()) {
                in3.push('B');
                // System.out.println("Bs before As");

                lang.test2Valid.clear();
                lang.test2Valid.push('f');
                return lang.test2Valid;
            }
            
            else if (tempChar == 'B' && !in1.isEmpty() && in3.isEmpty()) {
                in2.push('B');
            }
            
            else if (tempChar == 'B' &&  !in2.isEmpty()) {
                in2.push('B');
            }
        }    // end char assignment
        
//        System.out.println("in1 at end");
//        in1.printAll();
//        System.out.println("in2 at end");
//        in2.printAll();
        if (!in1.isEmpty() && in2.isEmpty())  //initially no Bs in stack
        {
//            System.out.println("Only As");
            lang.test2Valid.push('f');
            return lang.test2Valid;
        }
        while (!in1.isEmpty() && !in2.isEmpty()) // end of string after first set of As and Bs
        {
            in1.pop();
            in2.pop();
            if (in1.isEmpty() && in2.isEmpty() && in3.isEmpty())
            {
                lang.test2Valid.clear();
                lang.test2Valid.push('t');
                return lang.test2Valid;
            }
        }
            
        if (in1.isEmpty() && in2.isEmpty() && in3.isEmpty()) {
            lang.test2Valid.clear();
            lang.test2Valid.push('t');
            return lang.test2Valid;
        }
        else 
        {
            lang.test2Valid.push('f');
            return lang.test2Valid;
        }
    
            
//    return lang.test2Valid;
    }

}
