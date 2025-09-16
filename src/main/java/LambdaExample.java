public class LambdaExample {
    public static void main(String[] args) {
        Lambda lambda = (x, y) -> x + y;

        int a = 1;
        int b = 2;

        System.out.println(lambda.plus(a, b));
    }
}

@FunctionalInterface
interface Lambda {
    int plus(int a, int b);
}
