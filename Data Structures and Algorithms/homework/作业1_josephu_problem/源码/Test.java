package practice1;

public class Test {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node curr = head;
        int n = 30;//此为人数，暂定为30
        int m = 3;
        for(int i = 2;i<=n;i++){
            Node.increase(curr,new Node(i));
            curr = curr.getNext();
        }
        int k=0;
        curr = head;
        while (n>0){
            k++;
            if(k>m)k=1;
            if(k==m){
                Node temp;
                if ((temp=Node.deleteNode(curr)) != null) {
                    if(temp==curr){head=null;}
                    head = temp;
                }
                n--;
            }
            if(curr.getNext()==null){
                curr=head;
            }else {
                curr=curr.getNext();
            }
        }
    }
}
