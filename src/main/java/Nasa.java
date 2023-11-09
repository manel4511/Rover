import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Nasa {
    private static List<String> readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = null;
        try(Stream<String> linesStream = Files.lines(path)) {
            lines = linesStream.collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            System.out.println("File not found!!");
        }
        return lines;
    }

    private static void moveRovers(List<String> lines) {
        if (lines != null && lines.size() > 3) {
            int xMax = Integer.parseInt(lines.get(0).split(" ")[0]);
            int yMax = Integer.parseInt(lines.get(0).split(" ")[1]);

            for (int i = 1; i < lines.size(); i += 2) {
                if(i+1<lines.size()) {
                    Rover rover = new Rover(lines.get(i), lines.get(i + 1));
                    System.out.println(rover.finalPosition(xMax, yMax));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            List<String> lines = readFile(args[0]);
            moveRovers(lines);
        }
    }
}