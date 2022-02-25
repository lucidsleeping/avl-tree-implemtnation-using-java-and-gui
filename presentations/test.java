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
    
    // An AVL tree Node
    static class Node 
    {
        String key;
        Node left;
        Node right;
        int height;
        int count;
    }

    // A utility function to get height of the tree
    static int height(Node N)
    {
        if (N == null)
            return -1;
        return N.height;  
    }

    // A utility function to get maximum of two integers
    static int max(int a, int b)
    {
        if(a>b)
        return a; 
        else 
        return b;
    }

    /* Helper function that allocates a new Node with the given key and 
    null left and right pointers. */
    static Node newNode(String key)
    {
        Node node = new Node();
        node.key = key;
        node.left = null;
        node.right = null;
        node.height = 0; // new Node is initially added at leaf
        node.count = 1;
        return (node);
    }

    // create checkEmpty() method to check whether the AVL Tree is empty or not  
    static boolean checkEmpty(Node root)
    {  
        if(root == null)  
            return true;  
        else   
            return false;  
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    static Node rightRotate(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    static Node leftRotate(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of Node N
    static int getBalance(Node N)
    {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    static Node insert(Node Node, String key)
    {
        /*1.  Perform the normal BST rotation */
        if (Node == null)
            return (newNode(key));

        // If key already exists in BST, increment count and return
        if (key.compareTo(Node.key) == 0)
        {
            (Node.count)++;
            return Node;
        }

        /* Otherwise, recur down the tree */
        if (key.compareTo(Node.key) < 0)
            Node.left = insert(Node.left, key);
        else
            Node.right = insert(Node.right, key);

        /* 2. Update height of this ancestor Node */
        Node.height = max(height(Node.left), height(Node.right)) + 1;

        /* 3. Get the balance factor of this ancestor Node to check whether 
    this Node became unbalanced */
        int balance = getBalance(Node);

        // If this Node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key.compareTo(Node.left.key)<0)
            return rightRotate(Node);

        // Right Right Case
        if (balance < -1 && key.compareTo(Node.right.key) > 0)
            return leftRotate(Node);

        // Left Right Case
        if (balance > 1 && key.compareTo(Node.left.key) > 0) {
            Node.left = leftRotate(Node.left);
            return rightRotate(Node);
        }

        // Right Left Case
        if (balance < -1 && (key.compareTo(Node.right.key) < 0) ) {
            Node.right = rightRotate(Node.right);
            return leftRotate(Node);
        }

        /* return the (unchanged) Node pointer */
        return Node;
    }

    /* Given a non-empty binary search tree, return the Node with minimum 
     key value found in that tree. Note that the entire tree does not 
    need to be searched. */
    static Node minValueNode(Node Node)
    {
        Node current = Node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }
    // A utility function to print Inorder traversal of the tree.
    // The function also prints height of every Node
    static void inorderTraversal(Node head)
    {  
        if (head != null)
        {  
            inorderTraversal(head.left);  
            IN = IN+("Word = "+head.key+" : Count = "+head.count+"\n");  
            inorderTraversal(head.right);  
        }  
    }
    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every Node
    static void preOrder(Node head)
    {
        if (head != null) 
        {
            PRE = PRE+("Word = "+head.key+" : Count = "+head.count+"\n"); 
            preOrder(head.left);
            preOrder(head.right);
        }
    }
    // A utility function to print postorder traversal of the tree.
    // The function also prints height of every Node
    static void postorderTraversal(Node head)
    {  
        if (head != null)
        {  
            postorderTraversal(head.left);               
            postorderTraversal(head.right);  
            POST = POST+("Word = "+head.key+" : Count = "+head.count+"\n");
        }  
    } 
    //create getTotalNumberOfNodes() method to get total number of Nodes in the AVL Tree  
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
    public static void main(String args[])throws IOException
    {
        JFrame f=new JFrame("avl");  
        JLabel title=new JLabel("AVLIMPLIMENTATION");
        JButton checkempty=new JButton("CHECKEMPTY"); 
        JButton checknodes=new JButton("CHECKNODES");
        JButton inorder=new JButton("INORDER"); 
        JButton preorder=new JButton("PREORDER"); 
        JButton postorder=new JButton("POSTORDER");
        Node root = null;

        //loading the words from file
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
            for(String i : words )
            root = insert(root,i);  
        String noOfNodes = "Number of Nodes -> "+String.valueOf(getTotalNumberOfNodes(root));
        String checkEmptyStr = String.valueOf(checkEmpty(root));

        //calling travesal methods to store the data in the strings.
        inorderTraversal(root);
        postorderTraversal(root);
        preOrder(root);  

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
        f.add(g);
        f.add(checkempty); 
        f.add(checknodes);
        f.add(inorder);
        f.add(preorder);
        f.add(postorder);    
        f.setSize(850,700);  
        f.setLayout(null);  
        f.setVisible(true); 
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
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
    }//MAIN 
}//CLASS