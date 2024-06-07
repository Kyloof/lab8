public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        IntegerToStringExec exec=new IntegerToStringExec();

        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(11);
        bst.insert(4);
        bst.insert(2);
        bst.insert(12);
        bst.insert(10);

        System.out.println(bst.lowerThan(10));
        System.out.println("Czy znajduje się 4?");
        System.out.println(bst.search(4));
        System.out.println();
        System.out.println("Czy znajduje się 1?");
        System.out.println(bst.search(1));
        System.out.println();
        System.out.println("Jakie jest maksiumum?");
        System.out.println(bst.maximum());
        System.out.println();
        System.out.println("Jakie jest minimum?");
        System.out.println(bst.minimum());
        System.out.println();
        System.out.println("Preorder walk");
        exec=new IntegerToStringExec();
        bst.preOrderWalk(exec);
        System.out.println(exec.getResult());
        System.out.println();
        System.out.println();
        System.out.println("Następnik 5:");
        System.out.println(bst.findNext(5));
        System.out.println();
        System.out.println("Usuwanie liścia: ");
        bst.delete(4);
        exec=new IntegerToStringExec();
        bst.preOrderWalk(exec);
        System.out.println(exec.getResult());
        System.out.println();
        System.out.println();

        System.out.println("Usuwanie pojedynczego rodzica: ");
        bst.delete(3);
        exec=new IntegerToStringExec();
        bst.preOrderWalk(exec);
        System.out.println(exec.getResult());
        System.out.println();
        System.out.println();

        System.out.println("Usuwanie podwójnego rodzica: ");
        bst.delete(11);
        exec=new IntegerToStringExec();
        bst.preOrderWalk(exec);
        System.out.println(exec.getResult());


        bst.insert(50);
        System.out.println();
        System.out.println();
        System.out.println("Wstawianie 50");
        exec=new IntegerToStringExec();
        bst.preOrderWalk(exec);
        System.out.println(exec.getResult());

    }
}