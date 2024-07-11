public class Main {

    public static void main(String[] args) {

        SingleMethodInterface instance = new SingleMethodInterface() {
            @java.lang.Override
            public void doSomething() {
                System.out.println("The anonymous class does something");
            }
        };

        instance.doSomething();
    }
}

interface SingleMethodInterface {

    void doSomething();
}