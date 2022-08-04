
public class SQueueTest{
public static void main(String[] args) {
    System.out.println("First Stack test 2");
    SQueue stacker = new SQueue();
     stacker.add(1);
     stacker.add(2);
     stacker.add(3);
     stacker.add(4);
     stacker.add(5);
     stacker.remove();
     stacker.remove();
     stacker.remove();
     System.out.println(stacker.remove());
     System.out.println("Second test 2");
     stacker.add(2);
     stacker.add(4);
     stacker.add(8);
     stacker.remove();
     stacker.remove();
     if (stacker.isEmpty()){// determies contents
     System.out.println("queue of stack is Empty");
     }
     else{
        System.out.println("queue of stack is  not Empty");
    }
    System.out.println("Third test ");
     stacker.add(3);
     stacker.add(6);
     stacker.add(9);
     stacker.remove();
     System.out.println(stacker.remove());
     if (stacker.isEmpty()){
     System.out.println("queue is Empty");
     }
     else{
        System.out.println("queue is  not Empty");
    }
 }
}
