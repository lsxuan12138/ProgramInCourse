package practice2;

import java.util.Scanner;

public class Tree<E> {
    private TreeNode<E> root;

    public Tree() {
    }

    public Tree(TreeNode<E> root) {
        this.root = root;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }


    //判断二叉树是否为空
    public boolean isEmpty(){
        return root == null;
    }

    public void printTree(){
        if(this.root==null)throw new NullPointerException();
        System.out.println("根节点为："+this.getRoot().getElement());
        Queue<TreeNode<E>> queue = new Queue<>();
        queue.enQueue(new QueueNode<TreeNode<E>>(this.root));
        while (!queue.isEmpty()){

            QueueNode<TreeNode<E>> node = queue.deQueue();

            if(node.getElement().getLeftChild()!=null){
                System.out.println(node.getElement().getElement()+"的左孩子为："+node.getElement().getLeftChild().getElement());
                queue.enQueue(new QueueNode<TreeNode<E>>(node.getElement().getLeftChild()));
            }

            if(node.getElement().getRightChild()!=null){
                System.out.println(node.getElement().getElement()+"的右孩子为："+node.getElement().getRightChild().getElement());
                queue.enQueue(new QueueNode<TreeNode<E>>(node.getElement().getRightChild()));
            }
        }
    }
    public TreeNode<E> addNode (TreeNode<E> parent,TreeNode<E> node ,boolean isLeft){
        if(parent==null){
            throw new NullPointerException();
        }
        if(isLeft && parent.getLeftChild() != null){
            throw new RuntimeException(parent + "节点已有左子节点，不能添加左子节点！");
        }

        if(!isLeft && parent.getRightChild() != null){
            throw new RuntimeException(parent + "节点已有右子节点，不能添加右子节点！");
        }

        if(isLeft){
            parent.setLeftChild(node);
        }else{
            parent.setRightChild(node);
        }

        return node;
    }
    /**
     * 添加节点
     * @param parent
     * @param ele
     * @param isLeft 是否在右子树添加
     * @return
     */
    public TreeNode<E> addNode (TreeNode<E> parent,E ele ,boolean isLeft){
        if(parent==null){
            throw new NullPointerException();
        }
        if(isLeft && parent.getLeftChild() != null){
            throw new RuntimeException(parent + "节点已有左子节点，不能添加左子节点！");
        }

        if(!isLeft && parent.getRightChild() != null){
            throw new RuntimeException(parent + "节点已有右子节点，不能添加右子节点！");
        }
        TreeNode<E> newNode = new TreeNode<E>(ele);
        if(isLeft){
            parent.setLeftChild(newNode);
        }else{
            parent.setRightChild(newNode);
        }

        return newNode;
    }

    /**
     * 递归先序
     * @param root
     */
    public void PreOrderTraverse(TreeNode<E> root){
        System.out.print(root.getElement());
        if(root.getLeftChild()!=null)
        PreOrderTraverse(root.getLeftChild());
        if(root.getRightChild()!=null)
        PreOrderTraverse(root.getRightChild());
    }
    /**
     * 递归中序
     * @param root
     */
    public void InOrderTraverse(TreeNode<E> root){
        if(root.getLeftChild()!=null)
            InOrderTraverse(root.getLeftChild());
        System.out.print(root.getElement());
        if(root.getRightChild()!=null)
            InOrderTraverse(root.getRightChild());
    }
    /**
     * 递归后序
     * @param root
     */
    public void PosOrderTraverse(TreeNode<E> root){
        if(root.getLeftChild()!=null)
            PosOrderTraverse(root.getLeftChild());
        if(root.getRightChild()!=null)
            PosOrderTraverse(root.getRightChild());
        System.out.print(root.getElement());
    }

    /**
     * 层序遍历
     * @param root
     */
    public void LeverOrderTraverse(TreeNode<E> root){
        if(root==null)throw new NullPointerException();
        Queue<TreeNode<E>> queue = new Queue<>();
        queue.enQueue(new QueueNode<TreeNode<E>>(root));
        while (!queue.isEmpty()){
            QueueNode<TreeNode<E>> node = queue.deQueue();
            System.out.print(node.getElement().getElement());

            if(node.getElement().getLeftChild()!=null){
                queue.enQueue(new QueueNode<TreeNode<E>>(node.getElement().getLeftChild()));
            }

            if(node.getElement().getRightChild()!=null){
                queue.enQueue(new QueueNode<TreeNode<E>>(node.getElement().getRightChild()));
            }
        }
    }
    /**
     * 非递归前序
     */
    public void PreOrderWithoutRecursion(TreeNode<E> root){
        //空树
        if(root==null)throw new NullPointerException();
        //树非空
        TreeNode<E> p = root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        while (!stack.isEmpty()|| p!=null){
            //边遍历边打印，并存入栈中，以后需要借助这些根节点进入右子树
            while (p!=null){
                System.out.print(p.getElement());
                stack.push(new StackNode<TreeNode<E>>(p));
                p=p.getLeftChild();
            }
            //当p为空时，说明根和左子树都遍历完了，该进入右子树了
            if(!stack.isEmpty()){
                p=stack.pop().getEle();
                p=p.getRightChild();
            }
        }
    }
    /**
     * 非递归中序
     */
    public void InOrderWithoutRecursion(TreeNode<E> root){
        //空树
        if(root==null)throw new NullPointerException();
        //树非空
        TreeNode<E> p = root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        while (!stack.isEmpty()|| p!=null){
            //一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (p!=null){
                stack.push(new StackNode<TreeNode<E>>(p));
                p=p.getLeftChild();
            }
            //当p为空时，说明已经到达左子树最下边，这时需要出栈了
            if(!stack.isEmpty()){
                p=stack.pop().getEle();
                System.out.print(p.getElement());
                //进入右子树，开始新的一轮左子树遍历(这是递归的自我实现)
                p=p.getRightChild();
            }
        }
    }
    /**
     * 非递归后续
     */
    //需要判断上次访问的节点是位于左子树，还是右子树。若是位于左子树，则需跳过根节点，先进入右子树，再回头访
    // 问根节点；若是位于右子树，则直接访问根节点。
    public void PostOrderWithoutRecursion(TreeNode<E> root){
        //空树
        if(root==null)throw new NullPointerException();
        //树非空
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        //pCur:当前访问节点，pLastVisit:上次访问节点
        TreeNode<E> pCur,pLast;

        pCur = root;
        pLast = null;

        //先把pCur移动到左子树最下边
        while (pCur !=null){
            stack.push(new StackNode<TreeNode<E>>(pCur));
            pCur=pCur.getLeftChild();
        }
        while (!stack.isEmpty()){
            //走到这里，pCur都是空，并已经遍历到左子树底端(看成扩充二叉树，则空，亦是某棵树的左孩子)
            pCur=stack.pop().getEle();

            //一个根节点被访问的前提是：无右子树或右子树已被访问过
            if(pCur.getRightChild()==null || pCur.getRightChild()==pLast){
                System.out.print(pCur.getElement());
                pLast=pCur;
            }else {//若左子树刚被访问过，则需先进入右子树(根节点需再次入栈)//if (pCur.getLeftChild() == pLast)
                stack.push(new StackNode<TreeNode<E>>(pCur));
                pCur=pCur.getRightChild();
                while (pCur!=null){
                    stack.push(new StackNode<TreeNode<E>>(pCur));
                    pCur=pCur.getLeftChild();
                }
            }
        }
    }



    //一边对二叉树进行层序将每一个节点都加入到队列，一边执行下面的判断
    //当前节点有右孩子，但没有左孩子，直接返回false
    //当前节点有左孩子没右孩子，那么接下来遇到的所有节点必须是叶子节点
    /**
     * 判断是否为完全二叉树
     * @param root
     * @return
     */
    public boolean check(TreeNode<E> root) {
        boolean leaf = false; // 是否开启了状态
        Queue<TreeNode<E>> q = new Queue<TreeNode<E>>();

        q.enQueue(new QueueNode<TreeNode<E>>(root));
        while (!q.isEmpty()) {
            TreeNode<E> cur = q.deQueue().getElement();
            TreeNode<E> l = cur.getLeftChild();
            TreeNode<E> r = cur.getRightChild();
            if ((leaf && (l != null && r != null)) || (l == null && r != null))
                return false;
            if (l != null)
                q.enQueue(new QueueNode<TreeNode<E>>(l));
            if (r != null)
                q.enQueue(new QueueNode<TreeNode<E>>(r));
            else
                leaf = true;
        }
        return true;
    }
}
