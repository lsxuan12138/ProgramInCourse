package practice2;

public class Queue<E> {
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public Queue() {
    }

    public Queue(QueueNode<E> head, QueueNode<E> tail) {
        this.head = head;
        this.tail = tail;
    }

    public QueueNode getHead() {
        return head;
    }

    public void setHead(QueueNode<E> head) {
        this.head = head;
    }

    public QueueNode getTail() {
        return tail;
    }

    public void setTail(QueueNode<E> tail) {
        this.tail = tail;
    }
    public boolean isEmpty(){
        if(this.head == null)return true;
        return false;
    }

    /**
     * 入队
     * @param node
     */

    public void enQueue (QueueNode<E> node){
        if(node==null){
            throw new NullPointerException();
        }
        if(isEmpty()){
            this.setHead(node);
            this.setTail(node);
        }else {
            this.tail.setNext(node);
            this.setTail(node);
        }
    }

    /**
     * 出队
     * @return
     */
    public QueueNode<E> deQueue(){
        if(this.isEmpty()){
            System.out.println("queue is null");
            return null;
        }
        QueueNode<E> node = this.head;
        this.setHead(node.getNext());
        return node;
    }
}
