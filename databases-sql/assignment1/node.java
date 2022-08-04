import java.util.ArrayList;

//Collin Paddock 
import java.util.ArrayList;
public class node{// node class
    private ArrayList<String> companyData= new ArrayList<String>();
    private String company;
    
    public String getCompany(){// pull specific node value ( is private)
        return this.company;

    }
    public ArrayList<String> getData(){// pull specific node value ( is private)
        return this.companyData;

    }
    public void insert(ArrayList<String> info){//insert this node's value
       this.companyData=info;//3pts for comments isnt a lot
    }
    public void setCo(String company){
        this.company=company;
    }
}
