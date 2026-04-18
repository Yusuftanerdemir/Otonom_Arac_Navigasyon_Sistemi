import java.util.List;

public class SafetyOptimizer {
    private double[] weights; // w1 ve w2
    private double bias;      // b

    private final double learningRate;
    private final double lambda; // Düzenlileştirme (Regularization) parametresi
    private final int epochs;

    public SafetyOptimizer(double learningRate, double lambda, int epochs) {
        this.learningRate = learningRate;
        this.lambda = lambda;
        this.epochs = epochs;
        this.weights = new double[]{0.0, 0.0};
        this.bias = 0.0;
    }

    public void train(ObstacleSet obstacleSet) {
        List<Coordinate> data = obstacleSet.getObstacles();

        // Gradient Descent Döngüsü
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (Coordinate point : data) {
                // Hinge Loss Koşulu: y_i * (w * x_i + b) >= 1
                double output = (weights[0] * point.getX()) + (weights[1] * point.getY()) + bias;
                double condition = point.getLabel() * output;

                if (condition >= 1) {
                    // Sadece margin maksimizasyonu için ağırlıkları güncelle (Ceza yok)
                    weights[0] -= learningRate * (2 * lambda * weights[0]);
                    weights[1] -= learningRate * (2 * lambda * weights[1]);
                } else {
                    // Nokta koridorun içinde veya yanlış tarafta (Ceza uygula)
                    weights[0] -= learningRate * (2 * lambda * weights[0] - point.getX() * point.getLabel());
                    weights[1] -= learningRate * (2 * lambda * weights[1] - point.getY() * point.getLabel());
                    bias += learningRate * point.getLabel();
                }
            }
        }
    }

    public void printEquation() {
        System.out.println("Optimum Güvenlik Sınırı Denklemi:");
        System.out.printf("(%.4f * x) + (%.4f * y) + (%.4f) = 0%n", weights[0], weights[1], bias);

        // Koridor genişliği (Margin) = 2 / ||w||
        double magnitude = Math.sqrt(weights[0]*weights[0] + weights[1]*weights[1]);
        System.out.printf("Güvenlik Koridoru Genişliği (Margin): %.4f birim%n", (2.0 / magnitude));
    }
}