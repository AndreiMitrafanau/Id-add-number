public class Main {
    public static void main(String[] args) {
        System.out.println(MessageHelper.welcomeMessage);
        if (args.length == 1) {
            FindAndChange.apply(args[0]);
        } else if (args.length == 2) {
            FindAndChange.apply(args[0], args[1]);
        } else {
            System.err.println(MessageHelper.argumentsCountError(args));
            System.exit(0);
        }
    }
}
