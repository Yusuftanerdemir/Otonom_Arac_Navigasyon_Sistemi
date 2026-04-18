import java.util.ArrayList;
import java.util.List;

public class ObstacleSet {
    private final List<Coordinate> obstacles;

    public ObstacleSet() {
        this.obstacles = new ArrayList<>();
    }

    public void addObstacle(Coordinate coordinate) {
        this.obstacles.add(coordinate);
    }

    public List<Coordinate> getObstacles() {
        return this.obstacles; // Gerçekte encapsulation için unmodifiableList dönülebilir
    }
}