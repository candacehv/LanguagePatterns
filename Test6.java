/*
 * This class holds the variables: 
 * in1: collects all valid As and Bs before the C character
 * 
 * in2: collects all valid As and Bs after the C character
 * 
 * in3: collects all invalid characters
 * 
 * in4: collets the C characters to determine when they appear, and if 
 * there are more than 1
 */
public class Test6 {

    /*
     * Test6 determines whether each string complies with the following pattern:
     * 
     * (set of AB) C (set of AB)^-1 An example of a valid input is: ABAABCBABBA
     * where each letter in the set of As and Bs before the C is exactly in order,
     * only the opposite letter is in its place.
     * 
     * It holds the following stacks: in1: holds all As and Bs before the C
     * 
     * in2: holds all letters that have been popped from in1 and changed to the
     * opposite char
     * 
     * returns the LinkedStack test6Valid with the true or false determinations
     * 
     * 
     * the LinkedStack language variable will be changed in ways determined by the
     * string
     * 
     * some print debugging statements left as comments
     */

    public LinkedStack testL6(LinkedStack language)
    {
        Language lang = new Language();

        LinkedStack in1 = new LinkedStack(); // hold all chars before the C
        LinkedStack in2 = new LinkedStack(); // hold popped/reversed chars
        LinkedStack in3 = new LinkedStack(); // hold anything else
        LinkedStack in4 = new LinkedStack(); // hold Cs
        char tempChar;
        if (language.isEmpty()) {
            // System.out.println("handling es");
            lang.test6Valid.clear();
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }

        // read in chars before the C
        while (!language.isEmpty()) {
            tempChar = language.pop().data;

            if (tempChar == 'C' && in1.isEmpty()) {
                // System.out.println("C before other chars");
                lang.test6Valid.push('f');
                return lang.test6Valid;
            }
            else if (tempChar == 'C' && !in1.isEmpty()) {
                if (in4.isEmpty()) {
                    in4.push('C');

                    // begin popping and changing
                    while (!in1.isEmpty()) {

                        // pop in1 change char and push into in2
                        char origChar = in1.pop().data;
                        char newChar;
                        if (origChar == 'A') {
                            newChar = 'B';
                            in2.push(newChar);
                        }
                        else if (origChar == 'B') {
                            newChar = 'A';
                            in2.push(newChar);
                        }
                        else if (origChar != 'A' && origChar != 'B') {
                            in3.push(origChar);
                            lang.test6Valid.clear();
                            lang.test6Valid.push('f');
                            return lang.test6Valid;
                        }
                    } // end char equal to C handling, popping and changing
                }
                // Not the first C
                else if (tempChar == 'C' && !in4.isEmpty()) {
                    in3.push('C');
                    // second C, not valid
                    lang.test6Valid.push('f');
                    return lang.test6Valid;
                }
            }
            if (tempChar != 'A' && tempChar != 'B' && tempChar != 'C') // invalid Char
            {
                in3.push(tempChar);
                lang.test6Valid.clear();
                lang.test6Valid.push('f');
                return lang.test6Valid;
            }
            if (tempChar == '\n' && !language.isEmpty()) {
                tempChar = language.pop().data;
            }

            // no Cs yet, first AB set - no determination
            if ((tempChar == 'A' || tempChar == 'B') && in4.isEmpty()) {
                in1.push(tempChar);
            }

            // first C happened already - no determination
            if ((tempChar == 'A' || tempChar == 'B') && !in4.isEmpty()) {
                in2.push(tempChar);
            }

            // pop in2 and pop the chars after C and see if they are equal
            while (!in2.isEmpty() && !language.isEmpty()) {
                tempChar = language.pop().data;
                if (!in2.isEmpty() && tempChar == 'A'
                                && in2.pop().data != 'A') {
                    lang.test6Valid.clear();
                    lang.test6Valid.push('f');

                    return lang.test6Valid;
                }
                if (!in2.isEmpty() && tempChar == 'B'
                                && in2.pop().data != 'B') {
                    lang.test6Valid.clear();
                    lang.test6Valid.push('f');
                    return lang.test6Valid;
                }
                else if (in2.isEmpty() && language.isEmpty()
                                && in3.isEmpty()) {
                    lang.test6Valid.push('t');
                    return lang.test6Valid;

                }
            }
        } // end !language.isEmpty()

        
        // begin comparisons
        if (language.isEmpty() && in4.isEmpty()) {
//            System.out.println("There were no Cs");
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }
        if (in2.isEmpty() && language.isEmpty() && in3.isEmpty()) {
            lang.test6Valid.push('t');
            return lang.test6Valid;
        }
        if (!in3.isEmpty()) {
            lang.test6Valid.clear();
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }
        if (in2.isEmpty() && !in1.isEmpty()) {
//            System.out.println("C did not occur");
            lang.test6Valid.clear();
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }
        if (!in2.isEmpty() && language.isEmpty()) {
//            System.out.println("first half shorter than second half");
            lang.test6Valid.clear();
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }
        if (in2.isEmpty() && !language.isEmpty()) {
//            System.out.println("second half shorter than first half");
            lang.test6Valid.clear();
            lang.test6Valid.push('f');
            return lang.test6Valid;
        }
        return lang.test6Valid;
    }
}
