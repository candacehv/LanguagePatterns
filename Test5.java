/*
 * Test 5 determines whether each string complies with the following
 * pattern: 
 * 
 * (AB) + (A^2B^2) + (A^nB^n) An example of a valid input is: 
 * ABAABBAAABBB
 * 
 * It holds the following stacks:
 * in1Copy: makes a copy of the input stack language and reverses it
 * 
 * template: initialized with B, then A, so that when popped, match the
 * in1Copy to determine equivalence of all values
 * 
 * cls5Stk: makes a copy of the template that can be used to pop and 
 * order they appear in the text input
 * 
 * aMult and bMult: multipliers to determine how many additional As and 
 * Bs need to be added on each successive copy
 * 
 */
public class Test5 {

    /*
     * Purpose: This method checks the validity of each string passed in from the
     * inputs against the (AB) + (A^2B^2) + (A^nB^n) pattern. It returns the results
     * via the test5Valid stack to the printValidityStmt method. First, the function
     * copies the LinkedStack language5 (language) and reverse it. It will then
     * perform multiple copies and pushes based characters in the in1Copy stack.
     * Throughout, it will determine validity and return the validity stack
     * test5Valid.
     * 
     * inputs: from readLang, LinkedStack language, which hold the string to be
     * checked for validity
     * 
     * returns: test5Valid stack which holds 1 item, which is a char, 't' if the
     * statement is valid, 'f' is it is not.
     * 
     * The state of language5 string passed from readLang and all class stacks will
     * be altered through this method in ways that depend on the string.
     */
    public LinkedStack testL5(LinkedStack language)
    {
        Language lang = new Language();
        LinkedStack aMult = new LinkedStack(); // multiplier to add As
        LinkedStack bMult = new LinkedStack(); // multiplier to add Bs
        LinkedStack template = new LinkedStack(); // holds current point
        LinkedStack cls5Stk = new LinkedStack(); // to pop and check
        LinkedStack in1Copy = new LinkedStack(); // string passed in

        template.push('B');
        template.push('A');
        cls5Stk = template.copy();
        aMult.push('a');
        bMult.push('a');
        in1Copy = language.copy();

        if (in1Copy.isEmpty()) {
            // System.out.println("handling es");
            lang.test5Valid.push('f');
            return lang.test5Valid;
        }
        while (!in1Copy.isEmpty() && !cls5Stk.isEmpty()) // pop and check values
        {
            while (!in1Copy.isEmpty() && !cls5Stk.isEmpty()) {
                char temp1Pop = in1Copy.pop().data;
                char temp2Pop = cls5Stk.pop().data;
                if (temp1Pop != temp2Pop) {
                    // System.out.println("contents of temps not equal");

                    // do multiple times until the final result
                    lang.test5Valid.clear();
                    lang.test5Valid.push('f');
                    return lang.test5Valid;
                }
            }
            if (!in1Copy.isEmpty() && cls5Stk.isEmpty()) {
                aMult.push('a');
                bMult.push('a');
                LinkedStack bCopy = new LinkedStack();
                LinkedStack aCopy = new LinkedStack();
                // loop and pop to see how many as to add to template
                aCopy = aMult.copy();
                bCopy = bMult.copy();// above but with bs
                template.reverse();
                while (!bCopy.isEmpty()) {
                    template.push('A');
                    bCopy.pop();
                }
                while (!aCopy.isEmpty()) {
                    template.push('B');
                    aCopy.pop();
                }
                template.reverse();
                cls5Stk = template.copy();
                in1Copy = language.copy();
            }
        }

        if (in1Copy.isEmpty() && !cls5Stk.isEmpty()) {
            // System.out.println(//
            // "Not enough chars to complete the AB round");
            lang.test5Valid.push('f');
            return lang.test5Valid;
        }
        if (in1Copy.isEmpty() && cls5Stk.isEmpty()) {
            lang.test5Valid.push('t');
            return lang.test5Valid;
        }
        return lang.test5Valid;
    }
}
