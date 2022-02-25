package project; 
import java.awt.event.*;  
import javax.swing.*;    
import java.awt.*; 
import java.io.*;
import java.util.*;

class avl 
{   
    static JTextArea g = new JTextArea();   
    static String IN = ""; 
    static String PRE = "";
    static String POST = ""; 
    static JFrame firstframe = new JFrame("file");
    static JButton firstbutton = new JButton("submit");
	static JTextField firstfield = new JTextField(16); 
	static JPanel p  = new JPanel();
    static String str; 
    static Node root = null;
    static JButton readfile = new JButton("readfile"); 
    static String noOfNodes; 
    static String checkEmptyStr; 
    static JFrame f=new JFrame("avl");  
    static JLabel title=new JLabel("AVLIMPLIMENTATION");
    static JButton checkempty=new JButton("CHECKEMPTY"); 
    static JButton checknodes=new JButton("CHECKNODES");
    static JButton inorder=new JButton("INORDER"); 
    static JButton preorder=new JButton("PREORDER"); 
    static JButton postorder=new JButton("POSTORDER");
    static JButton terminate=new JButton("TERMINATE"); 

    static class Node{
        String key;
        Node left;
        Node right;
        int height;
        int count;
    }
    static int height(Node N){
        if (N == null)
            return -1;
        return N.height;  
    }
    static int max(int a, int b){
        if(a>b)
        return a; 
        else 
        return b;
    }
    static Node newNode(String key){
        Node node = new Node();
        node.key = key;
        node.left = null;
        node.right = null;
        node.height = 0;
        node.count = 1;
        return (node);
    }  
    static boolean checkEmpty(Node root){  
        if(root == null)  
            return true;  
        else   
            return false;  
    }
    static Node rightRotate(Node y){
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }
    static Node leftRotate(Node x){
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }
    static int getBalance(Node N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    static Node insert(Node Node, String key)
    {
        if (Node == null)
            return (newNode(key));

        if (key.compareTo(Node.key) == 0)
        {
            (Node.count)++;
            return Node;
        }

        if (key.compareTo(Node.key) < 0)
            Node.left = insert(Node.left, key);
        else
            Node.right = insert(Node.right, key);

        Node.height = max(height(Node.left), height(Node.right)) + 1;
       int balance = getBalance(Node);
        if (balance > 1 && key.compareTo(Node.left.key)<0)
            return rightRotate(Node);//rr
        if (balance < -1 && key.compareTo(Node.right.key) > 0)
            return leftRotate(Node);//ll
        if (balance > 1 && key.compareTo(Node.left.key) > 0) {
            Node.left = leftRotate(Node.left);
            return rightRotate(Node);//lr
        }
        if (balance < -1 && (key.compareTo(Node.right.key) < 0) ) {
            Node.right = rightRotate(Node.right);
            return leftRotate(Node);//rl 
        }
        return Node;
    }
    static void inorderTraversal(Node head)
    {  
        if (head != null)
        {  
            inorderTraversal(head.left);  
            IN = IN+("Word = "+head.key+" : Count = "+head.count+"\n");  
            inorderTraversal(head.right);  
        }  
    }
    static void preOrder(Node head)
    {
        if (head != null) 
        {
            PRE = PRE+("Word = "+head.key+" : Count = "+head.count+"\n"); 
            preOrder(head.left);
            preOrder(head.right);
        }
    }
    static void postorderTraversal(Node head)
    {  
        if (head != null)
        {  
            postorderTraversal(head.left);               
            postorderTraversal(head.right);  
            POST = POST+("Word = "+head.key+" : Count = "+head.count+"\n");
        }  
    } 
    static int getTotalNumberOfNodes(Node head)
    {  
        if (head == null)  
            return 0;  
        else{  
            int length = 1;  
            length = length + getTotalNumberOfNodes(head.left);  
            length = length + getTotalNumberOfNodes(head.right);  
            return length;  
        }  
    }  
    public static void main(String args[])throws IOException{
        title = new JLabel("AVL IMPLIMENTATION"); 
		title.setFont(new Font("Arial", Font.BOLD, 15)); 
		title.setSize(300, 20); 
		title.setLocation(300, 30); 
        f.add(title);
        g.setBounds(300,60,200,200);
        checkempty.setBounds(300,300,200,30);
        checknodes.setBounds(300,350,200,30);
        inorder.setBounds(300,400,200,30);
        preorder.setBounds(300,450,200,30);
        postorder.setBounds(300,500,200,30);
        terminate.setBounds(300,550,200,30); 
        firstfield.setBounds(300,60,200,200);;
        firstbutton.setBounds(300,350,200,30);
        readfile.setBounds(300,400,200,30);
        f.add(g);
        f.add(checkempty); 
        f.add(checknodes);
        f.add(inorder);
        f.add(preorder);
        f.add(postorder);    
        f.add(terminate);
        f.setSize(850,700);  
        p.setLayout(null);
        f.setLayout(null);  
        f.setVisible(false); 
        p.add(readfile); 
		p.add(firstfield); 
		p.add(firstbutton);
    	firstframe.add(p);
		firstframe.setSize(850, 700);
		firstframe.setVisible(true);
        firstbutton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){    
                    str = firstfield.getText();
                    System.out.println("read ->"+str); 
                    try{
                        BufferedWriter out = new BufferedWriter(new FileWriter("text.txt"));
                                        out = new BufferedWriter(new FileWriter("text.txt",true));                    
                                        out.append(str);
                                        out.close();
                                    }
                    catch (IOException g){
                        System.out.println(g);          
                    }
                    }            
                    });
        readfile.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                try{
                    File f1=new File("text.txt"); 
                    ArrayList<String> words = new ArrayList<String>();    
                    Scanner fr = new  Scanner(f1);     
                    String s[]  = null;
                    while(fr.hasNext())
                    {
                    String z = fr.nextLine() ;
                    s= z.split(" ");
                    for(String i : s )
                    words.add(i);   
                    }
                    System.out.println("file ->"+words);
                    for(String i : words )
                    root = insert(root,i);
                    } 
                    catch(IOException h){System.out.print(g);}

                    inorderTraversal(root);
                    postorderTraversal(root);
                    preOrder(root);  
                    noOfNodes = "Number of Nodes -> "+String.valueOf(getTotalNumberOfNodes(root));
                    checkEmptyStr = String.valueOf(checkEmpty(root));
                    firstframe.setVisible(false);
                    f.setVisible(true);
                    }   
                    });         
        checkempty.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    g.setText(checkEmptyStr);
                    }  
                    });         
        checknodes.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    g.setText(noOfNodes);
                    }  
                    });         
        inorder.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    g.setText(IN);
                    }  
                    });         
        preorder.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    g.setText(PRE);
                    }  
                    });         
        postorder.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    g.setText(POST);
                    }  
                    });       
        terminate.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    IN = null; 
                    POST = null; 
                    PRE = null; 
                    noOfNodes = null; 
                    checkEmptyStr = null;
                    System.out.print("strings reset\nTERMINATED\n");
                    System.exit(0); 
                    }  
                    });  
        firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}