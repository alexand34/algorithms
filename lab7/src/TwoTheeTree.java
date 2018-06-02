import java.util.Collection;
import java.util.function.Predicate;


public class TwoTheeTree<T extends Comparable<T>> {

    private Node root;

    private int size;

    private static final int ROOT_IS_BIGGER = 1;
    private static final int ROOT_IS_SMALLER = -1;

    private boolean addition;

    public TwoTheeTree() {

        this.root = new Node();

        size = 0;
    }

    public TwoTheeTree(Collection<T> elements) {

        this.root = new Node();

        this.size = 0;

        elements.forEach(this::add);    // Java 8
    }

    public boolean add(T element) {
        size++;
        addition = false;
        if (root == null || root.getLeftElement() == null) { // first case
            if (root == null) root = new Node();
            root.setLeftElement(element);
            addition = true;
        } else {
            Node newRoot = addElementI(root, element); // Immersion
            if (newRoot != null) root = newRoot;
        }
        if (!addition) size--;
        return addition;
    }

    private Node addElementI(Node current, T element) {
        Node newParent = null;
        if (!current.isLeaf()) {
            Node sonAscended = null;
            if (current.leftElement.compareTo(element) == 0 || (current.is3Node() && current.rightElement.compareTo(element) == 0)) {
            } else if (current.leftElement.compareTo(element) == ROOT_IS_BIGGER) {
                sonAscended = addElementI(current.left, element);
                if (sonAscended != null) {
                    if (current.is2Node()) {
                        current.rightElement = current.leftElement;
                        current.leftElement = sonAscended.leftElement;
                        current.right = current.mid;
                        current.mid = sonAscended.mid;
                        current.left = sonAscended.left;
                    } else {
                        Node rightCopy = new Node(current.rightElement, null, current.mid, current.right);
                        newParent = new Node(current.leftElement, null, sonAscended, rightCopy);
                    }
                }
            } else if (current.is2Node() || (current.is3Node() && current.rightElement.compareTo(element) == ROOT_IS_BIGGER)) {
                sonAscended = addElementI(current.mid, element);
                if (sonAscended != null) {
                    if (current.is2Node()) {
                        current.rightElement = sonAscended.leftElement;
                        current.right = sonAscended.mid;
                        current.mid = sonAscended.left;
                    } else {
                        Node left = new Node(current.leftElement, null, current.left, sonAscended.left);
                        Node mid = new Node(current.rightElement, null, sonAscended.mid, current.right);
                        newParent = new Node(sonAscended.leftElement, null, left, mid);
                    }
                }
            } else if (current.is3Node() && current.rightElement.compareTo(element) == ROOT_IS_SMALLER) {
                sonAscended = addElementI(current.right, element);
                if (sonAscended != null) { // Split, the right element goes up
                    Node leftCopy = new Node(current.leftElement, null, current.left, current.mid);
                    newParent = new Node(current.rightElement, null, leftCopy, sonAscended);
                }
            }
        } else {
            addition = true;
            if (current.leftElement.compareTo(element) == 0 || (current.is3Node() && current.rightElement.compareTo(element) == 0)) {
                addition = false;
            } else if (current.is2Node()) {
                if (current.leftElement.compareTo(element) == ROOT_IS_BIGGER) {
                    current.rightElement = current.leftElement;
                    current.leftElement = element;
                } else if (current.leftElement.compareTo(element) == ROOT_IS_SMALLER) current.rightElement = element;
            } else newParent = split(current, element);
        }
        return newParent;
    }

    private Node split(Node current, T element) {
        Node newParent = null;
        if (current.leftElement.compareTo(element) == ROOT_IS_BIGGER) {
            Node left = new Node(element, null);
            Node right = new Node(current.rightElement, null);
            newParent = new Node(current.leftElement, null, left, right);
        } else if (current.leftElement.compareTo(element) == ROOT_IS_SMALLER) {
            if (current.rightElement.compareTo(element) == ROOT_IS_BIGGER) {
                Node left = new Node(current.leftElement, null);
                Node right = new Node(current.rightElement, null);
                newParent = new Node(element, null, left, right);

            } else {
                Node left = new Node(current.leftElement, null);
                Node right = new Node(element, null);
                newParent = new Node(current.rightElement, null, left, right);
            }
        }

        return newParent;
    }

    @Override
    public TwoTheeTree<T> clone() {

        TwoTheeTree<T> clone = new TwoTheeTree<>();

        if (!isEmpty()) cloneI(root, clone);

        return clone;
    }

    private void cloneI(Node current, TwoTheeTree<T> clone) {

        if (current != null) {

            if (current.isLeaf()) {

                clone.add(current.getLeftElement());
                if (current.getRightElement() != null) clone.add(current.getRightElement());
            } else {
                cloneI(current.getLeftSon(), clone);
                clone.add(current.getLeftElement());
                cloneI(current.getMidSon(), clone);
                if (current.getRightElement() != null) {
                    if (!current.isLeaf()) clone.add(current.getRightElement());
                    cloneI(current.getRightSon(), clone);
                }
            }
        }
    }

    public T find(T element) {

        return findI(root, element);
    }

    private T findI(Node current, T element) {
        T found = null;
        if (current != null) {
            if (current.leftElement != null && current.leftElement.equals(element)) found = current.leftElement;
            else {
                if (current.rightElement != null && current.rightElement.equals(element)) found = current.rightElement;
                else {
                    if (current.leftElement.compareTo(element) == ROOT_IS_BIGGER) {
                        found = findI(current.left, element);
                    } else if (current.right == null || current.rightElement.compareTo(element) == ROOT_IS_BIGGER) {
                        found = findI(current.mid, element);
                    } else if (current.rightElement.compareTo(element) == ROOT_IS_SMALLER) {
                        found = findI(current.right, element);
                    } else return null;
                }
            }
        }

        return found;
    }

    public void inOrder() {

        if (!isEmpty()) {

            inOrderI(root);    // Immersion
        } else System.out.println("The tree is empty");
    }

    private void inOrderI(Node current) {

        if (current != null) {

            if (current.isLeaf()) {

                System.out.println(current.getLeftElement().toString());
                if (current.getRightElement() != null) System.out.println(current.getRightElement().toString());
            } else {

                inOrderI(current.getLeftSon());
                System.out.println(current.getLeftElement().toString());

                inOrderI(current.getMidSon());

                if (current.getRightElement() != null) {

                    if (!current.isLeaf()) System.out.println(current.getRightElement().toString());

                    inOrderI(current.getRightSon());
                }
            }
        }
    }

    public void inOrder(Predicate<T> predicate) {

        if (!isEmpty()) {

            inOrderI(root, predicate);    // Immersion
        } else System.out.println("The tree is empty");
    }

    private void inOrderI(Node current, Predicate<T> predicate) {

        if (current != null) {
            if (current.isLeaf()) {
                if (predicate.test(current.getLeftElement())) System.out.println(current.getLeftElement().toString());
                if (current.getRightElement() != null && predicate.test(current.getRightElement())) {
                    System.out.println(current.getRightElement().toString());
                }
            } else {
                inOrderI(current.getLeftSon(), predicate);
                if (predicate.test(current.getLeftElement())) System.out.println(current.getLeftElement().toString());
                inOrderI(current.getMidSon(), predicate);
                if (current.getRightElement() != null) {
                    if (!current.isLeaf() && predicate.test(current.getRightElement()))
                        System.out.println(current.getRightElement().toString());
                    inOrderI(current.getRightSon(), predicate);
                }
            }
        }
    }

    public boolean isEmpty() {

        if (root == null) return true;

        if (root.getLeftElement() == null) return true;

        return false;
    }

    private void preOrderI(Node current) {

        if (current != null) {

            System.out.println(current.leftElement.toString());
            preOrderI(current.left);
            preOrderI(current.mid);

            if (current.rightElement != null) {

                System.out.println(current.rightElement.toString());
                preOrderI(current.right);
            }
        }
    }

    public boolean remove(T element) {
        boolean deleted;

        // We decrease the number of levels at the start, if the element is not deleted, we increase the value at the end
        this.size--;

        deleted = removeI(root, element); // Immersion

        root.rebalance();

        if (root.getLeftElement() == null) root = null; // We have deleted the last element of the tree

        if (!deleted) this.size++;

        return deleted;
    }

    private boolean removeI(Node current, T element) {
        boolean deleted = true;

        // Trivial case, we are in the deepest level of the tree but we have not found the element (it does not exist)
        if (current == null) deleted = false;

        else {
            if (!current.getLeftElement().equals(element)) {
                if (current.getRightElement() == null || current.getRightElement().compareTo(element) == ROOT_IS_BIGGER) {
                    if (current.getLeftElement().compareTo(element) == ROOT_IS_BIGGER) {
                        deleted = removeI(current.left, element);
                    } else {
                        deleted = removeI(current.mid, element);
                    }
                } else {
                    if (!current.getRightElement().equals(element)) {
                        deleted = removeI(current.right, element);
                    } else {
                        if (current.isLeaf()) current.setRightElement(null);
                        else {
                            T replacement = current.getRightSon().replaceMin();
                            current.setRightElement(replacement);
                        }
                    }
                }
            } else {
                if (current.isLeaf()) {
                    if (current.getRightElement() != null) {
                        current.setLeftElement(current.getRightElement());
                        current.setRightElement(null);
                    } else {
                        current.setLeftElement(null);
                        return true;
                    }
                } else {
                    T replacement = current.getLeftSon().replaceMax();
                    current.setLeftElement(replacement);
                }
            }
        }

        if (current != null && !current.isBalanced()) {
            current.rebalance();
        } else if (current != null && !current.isLeaf()) {
            boolean balanced = false;
            while (!balanced) {
                if (current.getRightSon() == null) {
                    if (current.getLeftSon().isLeaf() && !current.getMidSon().isLeaf()) {
                        T replacement = current.getMidSon().replaceMin();
                        T readdition = current.getLeftElement();
                        current.setLeftElement(replacement);
                        add(readdition);
                    } else if (!current.getLeftSon().isLeaf() && current.getMidSon().isLeaf()) {
                        if (current.getRightElement() == null) {
                            T replacement = current.getLeftSon().replaceMax();
                            T readdition = current.getLeftElement();
                            current.setLeftElement(replacement);
                            add(readdition);
                        }
                    }
                }
                if (current.getRightSon() != null) {
                    if (current.getMidSon().isLeaf() && !current.getRightSon().isLeaf()) {
                        current.getRightSon().rebalance();
                    }
                    if (current.getMidSon().isLeaf() && !current.getRightSon().isLeaf()) {
                        T replacement = current.getRightSon().replaceMin();
                        T readdition = current.getRightElement();
                        current.setRightElement(replacement);
                        add(readdition);
                    } else balanced = true;
                }
                if (current.isBalanced()) balanced = true;
            }
        }

        return deleted;
    }

    public class Node {
        private Node left;
        private Node mid;
        private Node right;
        private T leftElement;
        private T rightElement;

        private Node() {
            left = null;
            mid = null;
            right = null;
            leftElement = null;
            rightElement = null;
        }

        private Node(T leftElement, T rightElement) {
            this.leftElement = leftElement;
            this.rightElement = rightElement;
            left = null;
            mid = null;
            right = null;
        }

        private Node(T leftElement, T rightElement, Node left, Node mid) {
            this.leftElement = leftElement;
            this.rightElement = rightElement;
            this.left = left;
            this.mid = mid;
        }

        private T getLeftElement() {
            return leftElement;
        }

        private T getRightElement() {
            return rightElement;
        }

        private void setLeftElement(T element) {
            this.leftElement = element;
        }

        private void setRightElement(T element) {
            this.rightElement = element;
        }

        private void setLeftSon(Node son) {
            this.left = son;
        }

        private Node getLeftSon() {
            return left;
        }

        private void setMidSon(Node son) {
            this.mid = son;
        }

        private Node getMidSon() {
            return mid;
        }

        private void setRightSon(Node son) {
            this.right = son;
        }

        private Node getRightSon() {
            return right;
        }

        private boolean isLeaf() {
            return left == null && mid == null && right == null;
        }

        private boolean is2Node() {
            return rightElement == null;
        }

        private boolean is3Node() {
            return rightElement != null;
        }

        private boolean isBalanced() {
            boolean balanced = false;
            if (isLeaf()) {
                balanced = true;
            } else if (left.getLeftElement() != null && mid.getLeftElement() != null) {
                if (rightElement != null) { // 3 Node
                    if (right.getLeftElement() != null) {
                        balanced = true;
                    }
                } else {
                    balanced = true;
                }
            }
            return balanced;
        }


        private T replaceMax() {
            T max = null;
            if (!isLeaf()) {
                if (getRightElement() != null) {
                    max = right.replaceMax();
                } else max = mid.replaceMax();
            } else {
                if (getRightElement() != null) {
                    max = getRightElement();
                    setRightElement(null);
                } else {
                    max = getLeftElement();
                    setLeftElement(null);
                }
            }
            if (!isBalanced()) rebalance();

            return max;
        }

        private T replaceMin() {
            T min = null;
            if (!isLeaf()) {
                min = left.replaceMin();
            } else {
                min = leftElement;
                leftElement = null;
                if (rightElement != null) {
                    leftElement = rightElement;
                    rightElement = null;
                }
            }

            if (!isBalanced()) {
                rebalance();
            }

            return min;
        }

        private void rebalance() {
            while (!isBalanced()) {
                if (getLeftSon().getLeftElement() == null) { // The unbalance is in the left child
                    getLeftSon().setLeftElement(getLeftElement());
                    setLeftElement(getMidSon().getLeftElement());
                    if (getMidSon().getRightElement() != null) {
                        getMidSon().setLeftElement(getMidSon().getRightElement());
                        getMidSon().setRightElement(null);
                    } else {
                        getMidSon().setLeftElement(null);
                    }

                } else if (getMidSon().getLeftElement() == null) { // The unbalance is in the right child
                    if (getRightElement() == null) {
                        if (getLeftSon().getLeftElement() != null && getLeftSon().getRightElement() == null && getMidSon().getLeftElement() == null) {
                            setRightElement(getLeftElement());
                            setLeftElement(getLeftSon().getLeftElement());
                            setLeftSon(null);
                            setMidSon(null);
                            setRightSon(null);
                        } else {
                            getMidSon().setLeftElement(getLeftElement());
                            if (getLeftSon().getRightElement() == null) {
                                setLeftElement(getLeftSon().getLeftElement());
                                getLeftSon().setLeftElement(null);
                            } else {
                                setLeftElement(getLeftSon().getRightElement());
                                getLeftSon().setRightElement(null);
                            }
                            if (getLeftSon().getLeftElement() == null && getMidSon().getLeftElement() == null) {
                                setLeftSon(null);
                                setMidSon(null);
                                setRightSon(null);
                            }
                        }
                    } else {
                        getMidSon().setLeftElement(getRightElement());
                        setRightElement(getRightSon().getLeftElement());
                        if (getRightSon().getRightElement() != null) {
                            getRightSon().setLeftElement(getRightSon().getRightElement());
                            getRightSon().setRightElement(null);
                        } else {
                            getRightSon().setLeftElement(null);
                        }
                    }
                } else if (getRightElement() != null && getRightSon().getLeftElement() == null) {
                    if (getMidSon().getRightElement() != null) { // (1)
                        getRightSon().setLeftElement(getRightElement());
                        setRightElement(getMidSon().getRightElement());
                        getMidSon().setRightElement(null);
                    } else {
                        getMidSon().setRightElement(getRightElement());
                        setRightElement(null);
                    }
                }
            }
        }



        /* Shouldn't be other cases in 2-3 trees */
    }
}