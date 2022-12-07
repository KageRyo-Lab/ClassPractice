import java.util.EmptyStackException;
import java.util.Stack;

public class week13_4 {
    public static void main(String args[]){
        Stack<Integer> stack = new Stack<Integer>();
        System.out.println("stack："+stack);
        showPush(stack,42);
        showPush(stack,66);
        showPop(stack);
        showPop(stack);
        try{
            showPop(stack);
        }catch (EmptyStackException e){
            System.out.println("empty stack");
        }
    }
    static void showPush(Stack<Integer> stack, int num){
        stack.push(new Integer(num));
        System.out.println("push("+num+")");
        System.out.println("stack: "+stack);
    }
    static void showPop(Stack<Integer> stack){
        System.out.println("pop -> ");
        Integer num=(Integer) stack.pop();
        System.out.println(num);
        System.out.println("Stack: "+stack);
    }
}
