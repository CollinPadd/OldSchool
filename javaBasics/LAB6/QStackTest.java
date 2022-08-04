
public class QStackTest{
public static void main(String[] args) {
    QStack stacker = new QStack();// creating the stack to be used
    System.out.println("Case 1");
     stacker.push(1);
     stacker.push(2);
     stacker.push(3);
     stacker.push(4);
     stacker.push(5);
     stacker.pop();
     stacker.pop();
     stacker.pop();
     System.out.println(stacker.top());
     System.out.println("Case 2");
     stacker.push(2);
     stacker.push(4);
     stacker.push(8);
     stacker.pop();
     stacker.pop();
     if (stacker.isEmpty()){// check if stack is empty and response for either way
     System.out.println("stack is Empty");
     }
     else{
        System.out.println("stack is  not Empty");
    }
    System.out.println("Case 3");// third required test
     stacker.push(3);
     stacker.push(6);
     stacker.push(9);
     stacker.pop();
     System.out.println(stacker.top());// print to make sure
     if (stacker.isEmpty()){// checks to make sure stack is empty
     System.out.println("stack is Empty");
     }
     else{
        System.out.println("stack is  \not Empty");
    }
 }
}
