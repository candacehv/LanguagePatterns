import java.io.IOException;

/*
 * Test 4 determines whether each string complies with the following
 * pattern: 
 * 
 * (A^nB^m)^p An example of a valid input is: 
 * AABBBAABBBAABBB
 * 
 * It holds the following stacks:
 * in1: This stack holds all valid As and Bs from first set
 * 
 * in2: this stack  holds all valid As and Bs from following sets
 * 
 * in3: this stack holds values that are not valid - illegal 
 * chars, Bs that start the string. If not empty
 * once manipulations have concluded the string is not valid for L4.
 * 
 */
public class Test4 {

    /*
     * Purpose: This method checks the validity of each string passed in from the
     * inputs against the (A^nB^m)^p pattern. It returns the results via the
     * test4Valid stack to the printValidityStmt method. First, the function reads
     * in characters from the input stack and assigns them to in1 in2 or in3. If it
     * is determined at any point that the string is invalid, the char 'f' will be
     * pushed to the test4Valid stack and returned to the calling method, readLang,
     * which then sends it to the printValidityStmt to return readable results.
     * 
     * This method uses methods push, pop, and peek heavily to determine validity of
     * each character.
     * 
     * inputs: from readLang, LinkedStack language4 (language), which holds the
     * string to be checked for validity
     * 
     * returns: test4Valid stack which holds 1 item, which is a char, 't' if the
     * statement is valid, 'f' is it is not.
     * 
     * The state of language4 string passed from readLang and all class stacks will
     * be altered through this method in ways that depend on the string.
     */
    public LinkedStack testL4(LinkedStack language) throws IOException
    {
        Language lang = new Language();
        LinkedStack in1 = new LinkedStack(); // hold As
        LinkedStack in2 = new LinkedStack(); // hold Bs
        LinkedStack in3 = new LinkedStack(); // hold anything else

        char tempChar;
        while (!language.isEmpty()) {
            tempChar = language.pop().data;
            if (tempChar != 'A' && tempChar != 'B' && tempChar != '\r') {
                in3.push(tempChar);
                // System.out.println(
                // ":\t\tThis language is not part of L4 because it contains invalid
                // characters.");
                lang.test4Valid.push('f');
                return lang.test4Valid;
            }

            else if (tempChar == 'A' && in1.isEmpty() && in2.isEmpty()
                            && in3.isEmpty()) {
                // System.out.println("pushing A on empty list");
                in1.push('A');
            }
            else if ((tempChar == 'A' || tempChar == 'B') && !in2.isEmpty()) {
                in2.push(tempChar);
            }

            else if (tempChar == 'B' && in1.isEmpty()) {
                in3.push('B');
                // System.out.println("B is first");
                lang.test4Valid.push('f');
                return lang.test4Valid;
            }

            else if (tempChar == 'A' && (in1.peek().data == 'A')
                            && in2.isEmpty()) {
                in1.push('A');
                // System.out.println("pushing more As on As in 1");
            }
            else if (tempChar == 'B' && (in2.isEmpty() && in3.isEmpty()
                            && (!in1.isEmpty()))) {
                // System.out.println("pushing b in in1 after As");
                in1.push('B');
            }

            else if (tempChar == 'A' && in1.peek().data == 'B'
                            && in2.isEmpty()) {
                in2.push('A');
                // System.out.println("pushing A in 2 after in1 peek is b");
            }
        } // end char assignment

        LinkedStack tempStack = new LinkedStack();
        LinkedStack tempStack2 = new LinkedStack();
        tempStack = in1.copy();
        tempStack2 = in2.copy();

        if (in1.isEmpty()) {
            // System.out.println("handling empty string");
            lang.test4Valid.push('f');
            return lang.test4Valid;
        }

        if (!in3.isEmpty()) {
            lang.test4Valid.clear();
            lang.test4Valid.push('f');
            return lang.test4Valid;
        }
        if (in1.peek().data != 'B') {
            lang.test4Valid.clear();
            lang.test4Valid.push('f');
            return lang.test4Valid;
        }

        if (!in1.isEmpty() && in2.isEmpty() && in3.isEmpty()) 
            // tests that original in2 was smaller than orig in1
        {
            lang.test4Valid.clear();
            lang.test4Valid.push('t');
            return lang.test4Valid;
        }

        else if (!tempStack.isEmpty() && tempStack2.isEmpty()) {
            // System.out.println(
            // ":\t\t\tThis language is not in L4 becauseAB is larger 
            //than rest.");
            lang.test4Valid.clear();
            lang.test4Valid.push('f');
            return lang.test4Valid;
        }
        else if (tempStack.isEmpty() && !tempStack2.isEmpty()) {

            tempStack = in1.copy();
            // System.out.println("Copying");
        }

        if (tempStack.isEmpty() && tempStack2.isEmpty() && in3.isEmpty()) {
            lang.test4Valid.clear();
            lang.test4Valid.push('t');
            return lang.test4Valid;
        }
        while (!tempStack.isEmpty() && !tempStack2.isEmpty()) {
            char temp1Pop;
            char temp2Pop;
            if (!tempStack.isEmpty() && !tempStack2.isEmpty()) {
                temp1Pop = tempStack.pop().data;
                temp2Pop = tempStack2.pop().data;
                if (temp1Pop != temp2Pop) {
                    lang.test4Valid.clear();
                    lang.test4Valid.push('f');
                    // System.out.println("contents of stacks not equal");
                }
                if (tempStack.isEmpty() && tempStack2.isEmpty()) {
                    // System.out.println("Stacks are equal");
                    lang.test4Valid.clear();
                    lang.test4Valid.push('t');
                    return lang.test4Valid;
                }
            }
        }
        if (tempStack.isEmpty() && tempStack2.isEmpty() && in3.isEmpty()) {
            lang.test4Valid.clear();
            lang.test4Valid.push('t');
            return lang.test4Valid;
        }
        return lang.test4Valid;
    }
}
