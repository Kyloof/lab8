import java.util.ArrayList;
import java.util.Random;

public class BST <T extends Comparable<T>>  {

    private Node<T> root;
    private int index = 0;

    class Node<E>{
        private E key;
        private Node<E> LChild;
        private Node<E> RChild;

        public Node(E key){
            this.key = key;
        }
        public E getKey() {
            return key;
        }
        public Node<E> getLChild() {
            return LChild;
        }
        public Node<E> getRChild() {
            return RChild;
        }
        public void setKey(E key) {
            this.key = key;
        }

        public void setLChild(Node<E> LChild) {
            this.LChild = LChild;
        }

        public void setRChild(Node<E> RChild) {
            this.RChild = RChild;
        }
    }

    public void delete(T key) {
        Node<T> node = searchNode(key);
        Node<T> parentNode = searchParent(node);

        if (parentNode == null) {
            root = null; // If the node to remove is the root and the only node in the tree
        } else if (isLeaf(node)) {
            // If the node is a leaf, remove it directly from the parent
            if (parentNode.getLChild() == node) {
                parentNode.setLChild(null);
            } else {
                parentNode.setRChild(null);
            }
        } else if (node.getLChild() != null && node.getRChild() != null) {
            // If the node has two children, remove it by swapping with its successor
            removeNodeWithTwoChildren(node);
        } else {
            // If the node has only one child, remove it by updating the parent's child pointer
            Node<T> child = (node.getLChild() != null) ? node.getLChild() : node.getRChild();
            if (parentNode.getLChild() == node) {
                parentNode.setLChild(child);
            } else {
                parentNode.setRChild(child);
            }
        }
    }

    private void removeNodeWithTwoChildren(Node<T> node) {
        Node<T> nextNode = findNextNodeForTwoChildren(node);
        T nextKey = nextNode.getKey();
        //  delete the successor
        delete(nextNode.getKey());
        // Swap values with successor
        node.setKey(nextKey);


    }

    private Node<T> findNextNodeForTwoChildren(Node<T> node) {
        Node<T> current = node.getRChild();
        while (current.getLChild() != null) {
            current = current.getLChild();
        }
        return current;
    }
    public ArrayList<T> lowerThan(T value) {
        ArrayList<T> result = new ArrayList<>();
        inOrderLowerThan(root, value, result);
        return result;
    }

    private void inOrderLowerThan(Node<T> node, T value, ArrayList<T> result) {
        if (node == null) {
            return;
        }

        inOrderLowerThan(node.getLChild(), value, result);

        if (node.getKey().compareTo(value) < 0) {
            result.add(node.getKey());
        } else {
            return;
        }

        inOrderLowerThan(node.getRChild(), value, result);
    }

    private boolean isLeaf(Node<T> node){
        return node.getRChild()==null && node.getLChild()==null;
    }

    private Node<T> searchParent(Node<T> node){
        return searchParent(root, node);
    }

    private Node<T> searchParent(Node<T> parentNode, Node<T> node){

        if (parentNode==null){
            return null;
        }
        if (parentNode.getLChild() == node || parentNode.getRChild() == node){
            return parentNode;
        }

        if (node.getKey().compareTo(parentNode.getKey()) < 0) {
            return searchParent(parentNode.getLChild(), node);
        }
        //  search for the parent in the right subtree
        else {
            return searchParent(parentNode.getRChild(), node);
        }

    }


    public void insert(T key){
        Node<T> nodeY = null;
        Node<T> nodeX = root;
        Node<T> nodeZ = new Node<>(key);

        while (nodeX != null){
            nodeY = nodeX;
            if (key.compareTo(nodeX.getKey()) < 0){
                nodeX = nodeX.getLChild();
            }
            else{
                nodeX = nodeY.getRChild();
            }
        }
        if (nodeY == null){
            this.root = new Node<>(key);
        }
        else if(nodeZ.getKey().compareTo(nodeY.getKey()) < 0){
            nodeY.setLChild(nodeZ);
        }
        else{
            nodeY.setRChild(nodeZ);
        }
    }


    public T findNext(T key) {
        Node<T> keyNode = searchNode(key);
        if (keyNode.getRChild() != null) {
            return minimum(keyNode.getRChild());
        }
        else{
            Node<T> nextNode = findNextExecute(keyNode);
            return nextNode != null ? nextNode.getKey() : null;
        }
    }

    // Helper method to find the next node
    private Node<T> findNextExecute(Node<T> keyNode) {
        Node<T> successor = null;
        Node<T> current = root;

        while (current != null) {
            if (keyNode.getKey().compareTo(current.getKey()) < 0) {
                successor = current;
                current = current.getLChild();
            } else if (keyNode.getKey().compareTo(current.getKey()) > 0) {
                current = current.getRChild();
            } else {
                return successor; // Found the key node, exit the loop
            }
        }

        return successor;
    }


    private Node<T> searchNode(T searched) {
        return search(root, searched);
    }

    public <R> void preOrderWalk(IExecutor<T,R> exec){
        preOrderWalk(root, exec);
    }

    private<R> void preOrderWalk(Node<T> node, IExecutor<T,R> exec){
        if (node!=null) {
            exec.execute(node.getKey());
            preOrderWalk(node.getLChild(),exec);
            preOrderWalk(node.getRChild(),exec);
        }
    }


    public T maximum(){
        return maximum(root);
    }

    private T maximum(Node<T> node){
        if (node.getRChild()!=null){
            return maximum(node.getRChild());
        }
        return node.getKey();
    }

    public T minimum(){
        return minimum(root);
    }
    private T minimum(Node<T> node){
        if (node.getLChild()!=null){
            return minimum(node.getLChild());
        }
        return node.getKey();
    }


    public T search(T searched) {
        Node<T> foundNode = search(root, searched);
        if (foundNode == null){
            return null;
        }
        return foundNode.getKey();
    }

    // Overloaded method with two parameters
    private Node<T> search(Node<T> currentNode, T searched) {
        if (currentNode==null){
            return null;
        }
        if (currentNode.getKey() == searched){
            return currentNode;
        }

        if(searched.compareTo(currentNode.getKey())<0){
            return search(currentNode.getLChild(), searched);
        }
        return search(currentNode.getRChild(),searched);
    }

}
