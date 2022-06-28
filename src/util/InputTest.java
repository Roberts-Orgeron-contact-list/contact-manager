package util;

public class InputTest {

    public static void main(String[] args) {
        Input input = new Input ();
        System.out.println(input.getInt());
        System.out.println(input.getInt(1, 10));
        System.out.println(input.getDouble(1, 10));
        System.out.println(input.getDouble());
        System.out.println(input.yesNo());
    }
}
