package practice2;

public class StackNode<E> {
    private E ele;
    private StackNode<E> next;
    public StackNode(E ele) {
        this.ele = ele;
        this.next = null;
    }
    public StackNode(E ele, StackNode<E> next) {
        this.ele = ele;
        this.next = next;
    }

    public E getEle() {
        return ele;
    }

    public void setEle(E ele) {
        this.ele = ele;
    }

    public StackNode<E> getNext() {
        return next;
    }

    public void setNext(StackNode<E> next) {
        this.next = next;
    }

}
