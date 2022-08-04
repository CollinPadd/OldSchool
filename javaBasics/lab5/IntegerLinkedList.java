// Collin Paddock
// Lab 5 Linked Lists

public  class IntegerLinkedList {
    private IntegerNode head;
    public boolean isEmpty() {   
        return head == null;  }
    public void insertFront(int val) { 
        IntegerNode node = new IntegerNode(val);
        node.setNext(head);
        head = node; 
    //given info
    }    
    public void insertBack(int val) {    
        IntegerNode node = new IntegerNode(val);      
        if(isEmpty()) {   
        head = node;   
        }   
        else {  
            IntegerNode cur = head;
            while (cur.getNext() != null) {// While the successor is not null
            cur=cur.getNext(); //update cur to point to its successor 
            }
            cur.setNext(node);//Cur successor is node
        }
    }
    public void print() {// Code given
        IntegerNode cur = head;
        while (cur != null) {
            System.out.printf("%d ", cur.getValue()); 
            cur=cur.getNext();// CODE CHANGED
        }      
        System.out.println(); 
    }    
    public int get(int index) {   
    int minusOne=-1;// index needs to be reduced by one
    IntegerNode cur = head;
    IntegerNode checkBound= head;// TO check bounds of value
    for(int j=0;j<=index;j++){// makes sure value is able to be put in
        if (checkBound==null){// this means you broke my program
            System.out.println("IndexOutOfBoundsException, index "+index+" is out of bounds.");// error when value is wrong
            break;
        }
        checkBound=checkBound.getNext();
    }
    for(int i=0;i<=index+minusOne;i++){       
            cur=cur.getNext();
        } 
    return cur.getValue(); 
    }
    public void removeFront() {
        if (head!=null){// While the head is not null
        head=head.getNext();
        }
    }    
    public void removeBack() {
        IntegerNode cur = new IntegerNode();
        if (head!= null){   // While the head is not null
            if (head.getNext()==null){// while successor is not empty
            head=head.getNext(); //set head to successor
            } 
            else{     
                IntegerNode next = head;    
                while (next.getNext()!=null){       
                    cur = next;//current is now set to next
                    next=next.getNext();//set next to its successor
                }
                cur.setNext(null);// set the next value to null  
            }
        } 
    }
    public void removeAt(int val){
        IntegerNode checkBound=head;// check if in bound
        IntegerNode newHead = new IntegerNode();//creates the newly formed head
        IntegerNode cur = head;// checks current
        if (val==(1-1)){// if you just want to remove the front
            head=head.getNext();
        }
        else{// 99% of the time you do this
            for(int j=0;j<=val;j++){// makes sure value is able to be put in
                if (checkBound==null){// this means you broke my program
                    System.out.println("IndexOutOfBoundsException, index "+val+" is out of bounds.");// error when value is wrong
                    return;
                }
                checkBound=checkBound.getNext();// iterate to check
            }
            for (int i=0; cur!=null && i<val-1; i++) {// while not null and smaller than val to change
                newHead = newHead.getNext();
            }
            newHead=cur.getNext().getNext();// val that hets changed
            cur.setNext(newHead);// destroys old link
        }
    }
    public static void main(String [] args){ // Primary file to be parsed
    IntegerLinkedList first = new IntegerLinkedList();
    System.out.println("---------First List--------- Inserting front and Back---");
            first.print();
            first.insertFront(1);
            first.print();
            first.insertBack(2);
            first.print();
            first.insertBack(3);
            first.print();
    IntegerLinkedList second = new IntegerLinkedList();
            System.out.println("---------Second List--------- Using Get without Exception---");
            second.insertBack(1);
            second.insertBack(2);
            second.insertBack(3);
            second.insertBack(4);
            second.print();
            System.out.println("Index 0 is " +second.get(0));
            System.out.println("Index 3 is " +second.get(3));
    IntegerLinkedList third = new IntegerLinkedList();
            System.out.println("-----------Third List-------- Removing Fronts and Backs");
            third.insertFront(5);
            third.insertBack(10);
            third.insertBack(15);
            third.insertBack(20);
            third.insertBack(25);
            third.print();
            third.removeFront();
            third.print();
            third.removeBack();
            third.print();
            third.removeFront();
            third.print();
            System.out.println("-------Fourth List-------Removing at index (1) twice also Exception------");
    IntegerLinkedList fourth = new IntegerLinkedList();
            fourth.insertFront(100);
            fourth.insertFront(1000);
            fourth.insertFront(10000);
            fourth.insertBack(0);
            fourth.print();
            fourth.removeAt(1);
            fourth.print();
            fourth.removeAt(1);
            fourth.print();
            fourth.removeAt(12);
    IntegerLinkedList fifth = new IntegerLinkedList();
            System.out.println("------------------Fifth List- Contains exception, will Break Program-----------");
            System.out.println("------------------Please Scroll up to view tests-----------");
            fifth.insertFront(1);
            fifth.insertBack(2);
            fourth.print();
            fifth.get(2);// This will end the program            }
}   

}