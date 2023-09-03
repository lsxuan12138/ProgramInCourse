package practice2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        //构造一棵简单的树
        TreeNode<Character> root = new TreeNode<Character>('A');
        TreeNode<Character> node1  = new TreeNode<Character>('B');
        TreeNode<Character> node2 = new TreeNode<Character>('C');
        TreeNode<Character> node3 = new TreeNode<Character>('D');
        TreeNode<Character> node4 = new TreeNode<Character>('E');
        TreeNode<Character> node5 = new TreeNode<Character>('F');
        Tree<Character> tree = new Tree<Character>(root);
        tree.addNode(root,node1,true);
        tree.addNode(root,node2,false);
        tree.addNode(node1,node3,true);
        tree.addNode(node1,node4,false);
        tree.addNode(node2,node5,false);
        tree.printTree();
        //递归先序遍历
        System.out.println("递归先序遍历");
        tree.PreOrderTraverse(tree.getRoot());
        System.out.println("\n");
        //非递归先序
        System.out.println("非递归先序");
        tree.PreOrderWithoutRecursion(tree.getRoot());
        System.out.println("\n");
        //递归中序遍历
        System.out.println("递归中序遍历");
        tree.InOrderTraverse(tree.getRoot());
        System.out.println("\n");
        //非递归中序
        System.out.println("非递归中序");
        tree.InOrderWithoutRecursion(tree.getRoot());
        System.out.println("\n");
        //递归后序遍历
        System.out.println("递归后序遍历");
        tree.PosOrderTraverse(tree.getRoot());
        System.out.println("\n");
        //层序遍历
        System.out.println("非递归后序");
        tree.PostOrderWithoutRecursion(tree.getRoot());
        System.out.println("\n");
        //层序遍历
        System.out.println("层序遍历");
        tree.LeverOrderTraverse(tree.getRoot());
        System.out.println("\n");
        System.out.println(tree.check(tree.getRoot()));

        TreeNode root2 = createTree();
        Tree<Character> tree1 = new Tree<Character>(root2);
        tree1.printTree();
        //递归先序遍历
        System.out.println("递归先序遍历");
        tree.PreOrderTraverse(tree1.getRoot());
        System.out.println("\n");
        //非递归先序
        System.out.println("非递归先序");
        tree.PreOrderWithoutRecursion(tree1.getRoot());
        System.out.println("\n");
        //递归中序遍历
        System.out.println("递归中序遍历");
        tree.InOrderTraverse(tree1.getRoot());
        System.out.println("\n");
        //非递归中序
        System.out.println("非递归中序");
        tree.InOrderWithoutRecursion(tree1.getRoot());
        System.out.println("\n");
        //递归后序遍历
        System.out.println("递归后序遍历");
        tree.PosOrderTraverse(tree1.getRoot());
        System.out.println("\n");
        //层序遍历
        System.out.println("非递归后序");
        tree.PostOrderWithoutRecursion(tree1.getRoot());
        System.out.println("\n");
        //层序遍历
        System.out.println("层序遍历");
        tree.LeverOrderTraverse(tree1.getRoot());
        System.out.println("\n");
        System.out.println(tree1.check(tree1.getRoot()));

    }
    public static TreeNode<Character> createTree(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入根节点：");
        char rootEle = scanner.next().charAt(0);
        if(rootEle=='#'){
            return null;
        }
        TreeNode<Character> root = new TreeNode<Character>(rootEle);
        Queue<TreeNode<Character>> queue = new Queue<>();
        queue.enQueue(new QueueNode<TreeNode<Character>>(root));
        while (!queue.isEmpty()){

            QueueNode<TreeNode<Character>> node = queue.deQueue();
            System.out.print(node.getElement().getElement()+"的左孩子为（#为空）：");
            char L = scanner.next().charAt(0);
            System.out.print(node.getElement().getElement()+"的右孩子为（#为空）：");
            char R = scanner.next().charAt(0);
            if(L!='#')node.getElement().setLeftChild(new TreeNode<Character>(L));
            if(R!='#')node.getElement().setRightChild(new TreeNode<Character>(R));
            if(node.getElement().getLeftChild()!=null){
                queue.enQueue(new QueueNode<TreeNode<Character>>(node.getElement().getLeftChild()));
            }

            if(node.getElement().getRightChild()!=null){
                queue.enQueue(new QueueNode<TreeNode<Character>>(node.getElement().getRightChild()));
            }
        }
        return root;
    }
}
