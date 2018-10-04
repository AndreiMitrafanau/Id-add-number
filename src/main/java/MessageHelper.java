public class MessageHelper {
    public static final String welcomeMessage =
            "This is the simple tool that allow you add or subtract value from number between \"(\" and ,\n" +
                    "Use gradle wrapper run task with parameters as follow (Windows users): \n" +
                    "gradlew run --args=\"Path\\To\\file.txt -2\" \\\\\\\\ subtract 2\"\n" +
                    "or \n" +
                    "gradlew run --args=\"Path\\To\\file.txt\"  \\\\ add one by default \n\nProcessing...";

    public static String argumentsCountError(String[] args) {
        return String.format("Invalid arguments count: %d.\nPlease provide args to gradlew run task as follows: \n" +
                "run --args=\"Path\\To\\file.txt\" or gradlew run --args=\"Path\\To\\file.txt -2\"", args.length);
    }

    public static final String processingError =
            "Something wrong happened! Try to restart the app... :(\nSee stacktrace below for more examples: ";

    public static final String successMessage = "Find and Replace done!!!";
}
