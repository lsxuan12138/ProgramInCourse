package practice2;

public class QueueNode<E> {
    private E element;
    private QueueNode next;

    public QueueNode() {
    }
    public QueueNode(E element) {
        this.element = element;
    }
    public QueueNode(E element, QueueNode next) {
        this.element = element;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}
