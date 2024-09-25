import java.util.ArrayList;
import java.util.List;

// Command Interface (Behavioral Design Pattern)
interface Command {
    void execute();
}

// Direction Enum to manage directions
enum Direction {
    N, E, S, W;
    
    public Direction turnLeft() {
        return values()[(this.ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        return values()[(this.ordinal() + 1) % 4];
    }
}

// Position class to represent the coordinates and direction
class Position {
    private int x, y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }

    public void moveForward() {
        switch (direction) {
            case N -> y++;
            case S -> y--;
            case E -> x++;
            case W -> x--;
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + direction + ")";
    }
}

// Rover class that uses Commands for movement
class Rover {
    private Position position;
    private Grid grid;

    public Rover(Position startPosition, Grid grid) {
        this.position = startPosition;
        this.grid = grid;
    }

    public Position getPosition() {
        return position;
    }

    public Grid getGrid() {
        return grid;
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public void sendStatusReport() {
        System.out.println("Rover is at " + position + ". No Obstacles detected.");
    }
}

// MoveCommand class to encapsulate forward movement
class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        Position currentPos = rover.getPosition();
        if (!rover.getGrid().isObstacleAhead(currentPos)) {
            currentPos.moveForward();
        } else {
            System.out.println("Obstacle detected! Cannot move forward.");
        }
    }
}

// TurnLeftCommand to encapsulate turning left
class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.getPosition().turnLeft();
    }
}

// TurnRightCommand to encapsulate turning right
class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.getPosition().turnRight();
    }
}

// Composite Pattern for Grid and Obstacles
class Grid {
    private int width, height;
    private List<Position> obstacles = new ArrayList<>();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addObstacle(int x, int y) {
        obstacles.add(new Position(x, y, null));
    }

    public boolean isObstacleAhead(Position position) {
        final int nextX = position.getX();
        final int nextY = position.getY();

        switch (position.getDirection()) {
            case N -> {
                return isObstacleAt(nextX, nextY + 1);
            }
            case S -> {
                return isObstacleAt(nextX, nextY - 1);
            }
            case E -> {
                return isObstacleAt(nextX + 1, nextY);
            }
            case W -> {
                return isObstacleAt(nextX - 1, nextY);
            }
        }
        return false;
    }

    private boolean isObstacleAt(final int x, final int y) {
        return (x < 0 || x >= width || y < 0 || y >= height) || 
               obstacles.stream().anyMatch(obs -> obs.getX() == x && obs.getY() == y);
    }
}

public class MarsRoverSimulation {
    public static void main(String[] args) {
        // Define grid and obstacles
        Grid grid = new Grid(10, 10);
        grid.addObstacle(2, 2);
        grid.addObstacle(3, 5);

        // Initialize Rover
        Position startPosition = new Position(0, 0, Direction.N);
        Rover rover = new Rover(startPosition, grid);

        // List of commands to execute
        List<Command> commands = List.of(
            new MoveCommand(rover),
            new MoveCommand(rover),
            new TurnRightCommand(rover),
            new MoveCommand(rover),
            new TurnLeftCommand(rover),
            new MoveCommand(rover)
        );

        // Execute the commands
        for (Command command : commands) {
            rover.executeCommand(command);
        }

        // Send status report
        rover.sendStatusReport();
    }
}
