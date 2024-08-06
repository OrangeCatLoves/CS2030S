import java.util.Random;

/**
 * CS2030S Exercise 0: RandomPoint.java
 * Semester 2, 2023/24
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
// TODO
class RandomPoint extends Point {
  
  private static Random rng = new Random(1);

  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super(getRandomPt(minX, maxX), getRandomPt(minY, maxY)); 
  }

  public static void setSeed(int value) {
    RandomPoint.rng = new Random(value);
  }

  public static double getRandomPt(double min, double max) {
    return rng.nextDouble() * (max - min) + min;
  }
}
