package practice2;


public class TreeNode<E> {
    private E element;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode() {
    }
    public TreeNode(E element) {
        this.element = element;
    }
    public TreeNode(E element, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public TreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

}
