import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BSTT {

    public static Node root;

    public BSTT() {

        this.root = null;

    }

    public boolean find(int id) {

        Node current = root;

        while (current != null) {

            if (current.data == id) {

                return true;

            } else if (current.data > id) {

                current = current.left;

            } else {

                current = current.right;

            }

        }

        return false;

    }

    
    public void insert(int id) {

        Node newNode = new Node(id);

        if (root == null) {

            root = newNode;

            return;

        }

        Node current = root;

        Node parent = null;

        while (true) {

            parent = current;

            if (id < current.data) {

                current = current.left;

                if (current == null) {

                    parent.left = newNode;

                    return;

                }

            } else {

                current = current.right;

                if (current == null) {

                    parent.right = newNode;

                    return;

                }

            }

        }

    }

    public void display(Node root) {

        if (root != null) {

            display(root.left);

            System.out.print(" " + root.data);

            display(root.right);

        }

    }

    public static void main(String arg[]) throws IOException {

		BSTT b = new BSTT();

		BufferedReader doWhat = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type integers, press return after each one.");
        System.out.println("Type End when Done. ");
        String addNode="";
        int addInt;
        boolean loop=true;
        while(loop){
            addNode=(doWhat.readLine());
            if(addNode.equalsIgnoreCase("end")){
                loop=false;
            }
            else{
                addInt=Integer.parseInt(addNode);
                b.insert(addInt);
            }
            
        }

		System.out.println("Original Tree : ");

		b.display(b.root);		

		System.out.println("");


		b.display(root);

		System.out.println("\n Delete Node with one child (4) : " + b.delete(4));		

		b.display(root);

		System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));		

		b.display(root);

	}

}



class Node{

	int data;

	Node left;

	Node right;	

	public Node(int data){

		this.data = data;

		left = null;

		right = null;

	}

}