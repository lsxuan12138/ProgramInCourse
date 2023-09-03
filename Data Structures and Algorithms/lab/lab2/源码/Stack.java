package practice2;

public class Stack<E> {
    private StackNode<E> stackBottom;
    private StackNode<E> stackTop;

    public Stack() {
        this.stackBottom = null;
        this.stackTop = null;
    }

    public Stack(E ele) {
        StackNode<E> node = new StackNode<E>(ele);
        this.stackBottom = node;
        this.stackTop = node;
    }
    public Stack(StackNode<E> stackBottom, StackNode<E> stackTop) {
        this.stackBottom = stackBottom;
        this.stackTop = stackTop;
    }

    public StackNode<E> getStackBottom() {
        return stackBottom;
    }

    public void setStackBottom(StackNode<E> stackBottom) {
        this.stackBottom = stackBottom;
    }

    public StackNode<E> getStackTop() {
        return stackTop;
    }

    public void setStackTop(StackNode<E> stackTop) {
        this.stackTop = stackTop;
    }


    public boolean isEmpty(){
        if(this.stackTop==null)return true;
        return false;
    }
    /**
     * 弹栈
     * @return
     */
    public StackNode<E> pop(){
        //System.out.println(this.stackTop);
        StackNode<E> node = this.getStackTop();
        if (node == null) {
            System.out.println("Stack is NULL");
            return null;
        }

        this.setStackTop(node.getNext());
        if(node.getNext()==null)this.setStackBottom(null);
        return node;
    }

    /**
     * 压栈
     * @param node
     */
    public void push(StackNode<E> node){
        if(this.getStackTop()==null){
            this.setStackTop(node);
            this.setStackBottom(node);
        }else{
            node.setNext(this.stackTop);
            this.setStackTop(node);
        }

    }
}
