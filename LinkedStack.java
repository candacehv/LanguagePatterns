import java.util.EmptyStackException;
/*
 * the LinkedStack class implements the stacks using a linked list of
 * LSNodes. 
 * 
 * methods: push, pop, clear, printAll, reverse, copy, peek, isEmpty
 */

public class LinkedStack implements Stacks {
    LSNode top;

    public LinkedStack() {
        top = null;
    }

    /*
     * This method takes a value and pushes onto the stack which calls it
     * 
     * @input: char variable to be pushed to the top of the stack
     * 
     * @end state: the parameter is added to the list and the top points to the new
     * item. The old top becomes the previous item.
     * 
     * @see Stacks#push(char)
     */
    public void push(char currChar)
    {
        LSNode next = new LSNode();

        next.data = currChar;
        next.prev = top;
        next.next = null;
        top = next;
    }

    public void clear()
    {
        while (isEmpty() != true) {
            pop();
        }
    }

    public void printAll()
    {
        try {
            LSNode currNode = top;
            while (currNode != null) {
                System.out.print(currNode.data);
                currNode = currNode.prev;
            }
            System.out.println();
        } catch (NullPointerException npex) {
            npex.printStackTrace();
        }
    }

    public LSNode pop() /* LinkedStack ls ) */
    {
        LSNode popNode = null;
        try {
            if (!isEmpty()) {
                popNode = top;
                top = top.prev;
            }

        } catch (NullPointerException npex)

        {
            System.out.println("cat in pop");
        }
        return popNode;
    }

    public void reverse() /* LinkedStack ls ) */ // null pointer exception //recursive option combined with print
    {
        try {
            LSNode curr = top;
            LSNode prev = curr.prev;
            LSNode next = null;// ahead of temp2 in the new direction
            while (prev != null) {
                prev = curr.prev;
                curr.prev = next;
                curr.next = prev;
                next = curr;
                if (prev != null) {
                    curr = prev;
                }
            }
            top = curr;
        } catch (NullPointerException npex) {
            System.out.println("EmptyString");

        }
    }

    public LinkedStack copy()
    {
        LinkedStack copyStk = new LinkedStack();
        if (!isEmpty()) {
            LSNode currNode = top;
            LSNode prevNode = top.prev;
            copyStk.top = currNode;
            copyStk.top.next = prevNode;
            while (prevNode != null) {
                currNode = prevNode;
                prevNode.next = currNode;
                prevNode = prevNode.prev;
            }
        }

        return copyStk;
    }

    public LSNode peek()
    {
        LSNode peekResult = null;
        try {
            peekResult = pop();
            push(peekResult.data);
        }

        catch (EmptyStackException esex) {
            // null
        } catch (NullPointerException npex) {
            System.out.println("EmptyString");
//            peekResult = pop();
//            push(peekResult.data);
//            return peekResult;
            peekResult = peek();
        }
        return peekResult;
    }

    public boolean isEmpty()
    {
        return top == null; // True if empty, False if not empty
    }
//    public String toString( LinkedStack str )
//    {
//        return  str;
//    }

}
