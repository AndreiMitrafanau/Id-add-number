import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindAndChange {
    private static final String defaultValueToAdd = "1";
    private static final String idRegexp = "\\((-?\\d*),";

    public static void apply(String pathString) {
        FindAndChange.apply(pathString, defaultValueToAdd);
    }

    public static void apply(String pathString, String valueToAdd) {
        try {
            Path path = Paths.get(pathString);
            Stream<String> lines = Files.lines(path);
            List<String> replaced = lines
                    .map(line -> changeIdInString(line, Integer.parseInt(valueToAdd)))
                    .collect(Collectors.toList());
            Files.write(path, replaced);
            lines.close();
            System.out.println(MessageHelper.successMessage);
        } catch (Exception e) {
            System.err.println(MessageHelper.processingError);
            e.printStackTrace();
        }
    }

    private static String changeIdInString(String line, int valueToAdd) {
        Pattern p = Pattern.compile(idRegexp);
        Matcher m = p.matcher(line);
        if (m.find()) {
            int newValue = Integer.parseInt(m.group(1)) + valueToAdd;
            return line.replaceAll(idRegexp, "(" + newValue + ",");
        } else
            return line;
    }
}

