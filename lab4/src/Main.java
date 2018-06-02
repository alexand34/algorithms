public class Main {
    public static void main(String[] args){

        DoublyCircularLinkedList list = new DoublyCircularLinkedList();

        list.addDNode(1);
        list.addDNode(2);
        list.addDNode(3);
        list.addDNode(4);
        list.addDNode(5);
        list.addDNode(6);

        list.print();
        System.out.println();
        list.printBackwards();
        System.out.println();

        System.out.println();
        System.out.println("The number of DNodes in the Linked List is " + DNode.noOfDLinkedList);

        list.deleteDNode(DNode.noOfDLinkedList);
        list.deleteDNode(DNode.noOfDLinkedList);

        list.print();
        System.out.println();
        list.printBackwards();
        System.out.println();

        System.out.println();
        System.out.println("The number of DNodes in the Linked List is " + DNode.noOfDLinkedList);
    }
}


