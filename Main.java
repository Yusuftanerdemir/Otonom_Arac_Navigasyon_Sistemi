public class Main {
    public static void main(String[] args) {
        ObstacleSet environment = new ObstacleSet();

        // Sınıf 1 Engelleri (+1) - Örneğin sağ üstte kümelenmiş
        environment.addObstacle(new Coordinate(2, 3, 1));
        environment.addObstacle(new Coordinate(3, 3, 1));
        environment.addObstacle(new Coordinate(2, 4, 1));
        environment.addObstacle(new Coordinate(4, 5, 1));

        // Sınıf 2 Engelleri (-1) - Örneğin sol altta kümelenmiş
        environment.addObstacle(new Coordinate(-1, -2, -1));
        environment.addObstacle(new Coordinate(-2, -3, -1));
        environment.addObstacle(new Coordinate(-3, -1, -1));
        environment.addObstacle(new Coordinate(-2, -1, -1));

        // Optimizer'ı başlat (Learning Rate: 0.001, Lambda: 0.01, Epoch: 10000)
        SafetyOptimizer optimizer = new SafetyOptimizer(0.001, 0.01, 10000);

        System.out.println("Otonom Araç Güvenlik Modülü Başlatılıyor...");
        System.out.println("Optimizasyon yapılıyor, lütfen bekleyin...");

        long startTime = System.nanoTime();
        optimizer.train(environment);
        long endTime = System.nanoTime();

        optimizer.printEquation();
        System.out.printf("Optimizasyon Süresi: %.2f ms%n", (endTime - startTime) / 1_000_000.0);
    }
}