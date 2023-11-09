import java.util.ArrayList;
import java.util.List;

public class Rover {
    private final String instructions;
    private int xCoordinate;
    private int yCoordinate;
    private Character direction;

    public String getInstructions() {
        return instructions;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Character getDirection() {
        return direction;
    }

    public Rover(String currentCoordinates, String instructions) {
        this.instructions = instructions;
        this.xCoordinate = Integer.parseInt(currentCoordinates.split(" ")[0]);
        this.yCoordinate = Integer.parseInt(currentCoordinates.split(" ")[1]);
        this.direction = currentCoordinates.split(" ")[2].charAt(0);
    }

    public void turn(String instruction) {
        ArrayList<Character> cardinalCompassLetters = new ArrayList<>(List.of('N', 'E', 'S', 'W'));
        int index = cardinalCompassLetters.indexOf(this.direction);
        int listLength = cardinalCompassLetters.size();
        index = switch (instruction) {
            case "L" -> index - 1;
            case "R" -> index + 1;
            default -> index;
        };
        index = index < 0 ? index + listLength : index % listLength;
        this.direction = cardinalCompassLetters.get(index);
    }


    public void moveForward(int xMax, int yMax) {
        switch (this.direction) {
            case 'N' -> {
                this.yCoordinate++;
            }
            case 'S' -> {
                this.yCoordinate--;
            }
            case 'W' -> {
                this.xCoordinate--;
            }
            case 'E' -> {
                this.xCoordinate++;
            }
        }
    }

    public String finalPosition(int xMax, int yMax) {
        if (this.xCoordinate > xMax || this.yCoordinate > yMax || this.xCoordinate < 0 || this.yCoordinate < 0) {
            return this.xCoordinate + " " + this.yCoordinate + " " + this.direction + "  Robot is already off board, mission failed";
        }
        for (String instruction : this.instructions.split("")) {
            if (instruction.equals("M")) {
                this.moveForward(xMax, yMax);
            } else {
                this.turn(instruction);
            }
        }
        if (this.xCoordinate > xMax || this.yCoordinate > yMax || this.xCoordinate < 0 || this.yCoordinate < 0) {
            return this.xCoordinate + " " + this.yCoordinate + " " + this.direction + "  Robot went off board, mission failed";
        }
        return this.xCoordinate + " " + this.yCoordinate + " " + this.direction;
    }
}
