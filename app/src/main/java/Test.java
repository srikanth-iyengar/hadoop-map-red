import java.util.*;

public class Test {
    public static void main(String args[]) {
        Scanner fs = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        System.out.println(a);
        try {
            throw new RuntimeException("Hello");
        }
        catch (Exception e) {
            System.out.println("Hello");
        }
    }
}
