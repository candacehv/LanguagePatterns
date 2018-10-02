import java.io.IOException;

/*
 * Test 3 determines whether each string complies with the following
 * pattern: 
 * 
 * (A^nB^2n) An example of a valid input is: 
 * AABBBB
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
 * once manipulations have concluded the string is not valid for L3.
 * 
 * tempChar holds the data value (char) of the current stack node.
 */

public class Test3 {

    /*
     * Purpose: This method checks the validity of each string passed in from the
     * inputs against the (A^nB^2n) pattern. It returns the results via the
     * test3Valid stack to the printValidityStmt method. First, the function reads
     * in characters from the input stack and assigns them to in1 in2 or in3. If it
     * is determined at any point that the string is invalid, the char 'f' will be
     * pushed to the test3Valid stack and returned to the calling method, readLang,
     * which then sends it to the printValidityStmt to return readable results.
     * 
     * This method uses methods push, pop, and peek heavily to determine validity of
     * each character based on its position in the string relative to other
     * characters.
     * 
     * inputs: from readLang, LinkedStack language3 (language), which holds the
     * string to be checked for validity
     * 
     * returns: test3Valid stack which holds 1 item, which is a char, 't' if the
     * statement is valid, 'f' is it is not.
     * 
     * The state of language3 string passed from readLang and all class stacks will
     * be altered through this method in ways that depend on the string.
     */
    public LinkedStack testL3(LinkedStack language) throws IOException
    {
        Language lang = new Language();

        LinkedStack in1 = new LinkedStack(); // hold As
        LinkedStack in2 = new LinkedStack(); // hold Bs
        LinkedStack in3 = new LinkedStack(); // hold anything else
        char tempChar;
        if (language.isEmpty())
        {
//            System.out.println("handling es");
            lang.test3Valid.push('f');
            return lang.test3Valid;
        }
        while (!language.isEmpty()) {
            tempChar = language.pop().data;
            if (tempChar != 'A' && tempChar != 'B') // first char invalid
            {
                in3.push(tempChar);
                lang.test3Valid.clear();
                lang.test3Valid.push('f');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
            else if (tempChar == '\n' && !language.isEmpty()) {
                tempChar = language.pop().data;
            }

            if (tempChar == 'A' && in1.isEmpty()) {
                while (tempChar == 'A' && !language.isEmpty()) {
                    in1.push('A');
                    tempChar = language.pop().data;
                }
            }
            // As on As
            else if (tempChar == 'A' && (!in1.isEmpty() && in2.isEmpty())) 
            {
                in1.push('A');
            }
         // As after other As and Bs
            else if (tempChar == 'A' && (!in2.isEmpty())) 
            {
                in3.push('A');
                lang.test3Valid.clear();
                lang.test3Valid.push('f'); // As coming after Bs
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
            // first B after As
            if (tempChar == 'B' && (!in1.isEmpty() && in2.isEmpty()))
            {
                while (tempChar == 'B' && !language.isEmpty()) {
                    in2.push('B');
                    tempChar = language.pop().data;
                }
                if (tempChar != 'B') {
                    in3.push(tempChar);
                    while (!language.isEmpty()) {
                        tempChar = language.pop().data;
                    }
                }
            }
            else if (tempChar == 'B' && in1.isEmpty()) // Bs before As
            {
                in3.push('B');
                lang.test3Valid.clear();
                lang.test3Valid.push('f');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
        }
        if (in1.isEmpty() && in2.isEmpty() && in3.isEmpty())
        {
            //handling empty string
            lang.test3Valid.push('f');
            return lang.test3Valid;
        }

        while (language.isEmpty()) {
            while (!in1.isEmpty() && !in2.isEmpty()) {
                in1.pop();
                in2.pop();
                in2.pop();
            }
            if (!in3.isEmpty()) {
                lang.test3Valid.push('f');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
            else if (in1.isEmpty() && in2.isEmpty()) {
                lang.test3Valid.push('t');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
            else if (!in1.isEmpty() && in2.isEmpty()) {
                lang.test3Valid.push('f');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
            else if (in1.isEmpty() && !in2.isEmpty()) {
                lang.test3Valid.push('f');
                return lang.test3Valid;
                // lang.printValidityStmt(lang.test3Valid.pop().data, "L3");
            }
        }
        return lang.test3Valid;
    }
}
