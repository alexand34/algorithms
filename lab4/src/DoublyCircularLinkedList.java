public class DoublyCircularLinkedList {
    //Declarations are the same!
    static DNode root;
    static DNode current;
    static DNode temp;

    public void addDNode(int data){

        DNode dNode = new DNode(data);

        if(root==null){

            root = dNode;
            //previous and next were null - now 'root'
            root.nextDNode = root;
            root.previousDNode = root;

        }else{

            current = root;

            //root was equal to null - now it's root.
            while(current.nextDNode!=root){

                current = current.nextDNode;

            }

            //previous
			/*
			current.nextNode = dNode;
			dNode.previousNode = current;
			dNode.nextNode = null;
			 */

            //New
            current.nextDNode = dNode;
            dNode.nextDNode = root;
            dNode.previousDNode = current;
            root.previousDNode = dNode;

        }

    }

    //remains the same! largely because no roots were used
    public void insertDNode(int data,int after){

        DNode dNode = new DNode(data);
        int ithDNode = 1;
        current = root;

        while(ithDNode != after){

            current = current.nextDNode;
            ithDNode++;

        }

        temp = current.nextDNode;

        current.nextDNode = dNode;
        dNode.nextDNode = temp;
        temp.previousDNode = dNode;
        dNode.previousDNode = current;

    }

    public void deleteDNode(int DNodeNumber){

        int ithDNode = 1;
        current = root;


        if(DNodeNumber == 1){

            //previous
			/*
			 * 	root = current.nextNode;
				current.nextNode = null;
				current.previousNode = null;
			 */

            //new
            root.nextDNode.previousDNode = root.previousDNode;
            root.previousDNode.nextDNode = root.nextDNode;
            root = root.nextDNode;

        }else{

            while(ithDNode != DNodeNumber){

                current = current.nextDNode;
                ithDNode++;

            }

            //previous
			/*
			if(current.nextNode == null){

				current.previousNode.nextNode = null;
				current.previousNode = null;

			}else{

				current.previousNode.nextNode = current.nextNode;
				current.nextNode.previousNode = current.previousNode;

			}
			 */

            //new
            current.nextDNode.previousDNode = current.previousDNode;
            current.previousDNode.nextDNode = current.nextDNode;
            current = current.nextDNode;

        }

        //add this, you always forget!
        DNode.noOfDLinkedList--;

    }

    //a bunch of print functions!
    public void print(){

        current = root;
        boolean arrow = true;

        do{

            System.out.print((arrow) ? "|" + current.data + "|" : " --> " + "|" + current.data + "|" );

            arrow = false;

            current = current.nextDNode;

        }while(current!=root);

    }

    public void printBackwards(){

        current = root;
        boolean arrow = true;

        do{

            current = current.nextDNode;

        }while(current.nextDNode!=root);

        do{

            System.out.print((arrow) ? "|" + current.data + "|" : " --> " + "|" + current.data + "|" );

            arrow = false;

            current = current.previousDNode;

        }while(current!=root);

        System.out.print(" --> " + "|" + current.data + "|" );

    }

    public void printCont() {

        current = root;
        boolean arrow = true;

        for(int i = 0; i < 15; i++){

            System.out.print((arrow) ? "|" + current.data + "|" : " --> " + "|" + current.data + "|" );

            arrow = false;

            current = current.nextDNode;

        }

    }

    public void printContBackwards() {

        current = root.previousDNode;
        boolean arrow = true;

        for(int i = 0; i < 15; i++){

            System.out.print((arrow) ? "|" + current.data + "|" : " --> " + "|" + current.data + "|" );

            arrow = false;

            current = current.previousDNode;

        }

    }
}
