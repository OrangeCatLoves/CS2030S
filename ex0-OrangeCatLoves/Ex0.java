import java.util.Scanner;

/**
 * CS2030S Exercise 0: Estimating Pi with Monte Carlo
 * Semester 2, 2023/24
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX (00X)
 */

class Ex0 {

  public static double estimatePi(int numOfPoints, int seed) {
    RandomPoint.setSeed(seed);
    double radius = 0.5;
    double x = 0.5;
    double y = 0.5;
    double minX = x - radius;
    double maxX = x + radius;
    double minY = y - radius;
    double maxY = y + radius;
    Point e = new Point(x, y);
    Circle c = new Circle(e, radius);
    double numOfPointsInCircle = 0;
    Point p = new RandomPoint(minX, maxX, minY, maxY);
 
    for (int i = 0; i < numOfPoints; i++) {
      // p = new RandomPoint(minX, maxX, minY, maxY);
      if (c.contains(p)) {
        numOfPointsInCircle++;
      }
      p = new RandomPoint(minX, maxX, minY, maxY);
    }
    double estimatedPI = 4 *numOfPointsInCircle / numOfPoints;
    return estimatedPI;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
