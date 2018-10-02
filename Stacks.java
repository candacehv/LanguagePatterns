/*
 * The stack interface is extended to the LinkedStacks class and could be 
 * extended to an array-based implementation if needed; 
 */
public interface Stacks {

    public void push(char x); /* LinkedStack ls, char x ); */

    public LSNode pop(); /* LinkedStack ls ); */

    public void reverse();

    public void printAll();

    public LSNode peek();

    public boolean isEmpty();
}
