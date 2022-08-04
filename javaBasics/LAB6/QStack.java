
import java.util.LinkedList;// to use the linked list
import java.util.Queue; 
public class QStack{
    Queue<Integer> queue=new LinkedList<Integer>();//main que to be used
    public void push(int x){
    queue.add(x);// adding to the program
    }
    public int pop(){//remove the top function
    return queue.remove();
    }
    public int size(){// returns the size of the queue
        return queue.size();
    }
    public boolean isEmpty(){
        return (queue.peek()==null);// if contents are null returns false
    }
    public int top(){// seeing what the top value 
        return queue.peek();
    }
}