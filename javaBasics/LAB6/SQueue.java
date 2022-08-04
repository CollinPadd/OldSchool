
import java.util.Stack; 
public class SQueue{
    Stack<Integer> stacker = new Stack<Integer>();// main stack to be used
    public void add(int x){// adding to stack
        stacker.push(x);
    }
    public int remove(){// taking the first off
        return stacker.pop();
    }
    public int peek(){// merely returning the top
        return stacker.peek();
    }
    public int size(){// illiciting size of the function
        return stacker.size();
    }
    public boolean isEmpty(){// checking if the function has any contents
        return stacker.isEmpty();
    }
    
    
}