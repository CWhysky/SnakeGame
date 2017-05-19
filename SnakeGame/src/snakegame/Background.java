package snakegame;

/**
 * Backgroun -- Class that holds the information for the background velocities
 * and positions.
 *
 * @author kamuela94
 */
public class Background {

    // Background Position, Accessors, and Setters
    public double bgX = 0.0;
    public double bgY = 0.0;

    // Background Velocites, Accessors and Setters
    double bgVelX = 0.0;
    double bgVelY = 0.0;

    /**
     * getBGVelX - Returns the background's velocity in the X direction.
     *
     * @return The x value of the background velocity
     */
    public double getBGVelX() {
        return bgVelX;
    }

    /**
     * getBGVelY - Returns the background's velocity in the Y direction.
     *
     * @return The y value of the background velocity
     */
    public double getBGVelY() {
        return bgVelY;
    }

    /**
     * setBGVelX - Sets the backround's velocity in the X direction.
     *
     * @param num the double to be set to background velocity X
     */
    public void setBGVelX(double num) {
        bgVelX = num;
    }

    /**
     * setBGVelY - Sets the backround's velocity in the Y direction.
     *
     * @param num the double to be set to background velocity Y
     */
    public void setBGVelY(double num) {
        bgVelY = num;
    }

    /**
     * getBGX - Return background's X position.
     *
     * @return the position of the background on the x plane
     */
    public double getBGX() {
        return bgX;
    }

    /**
     * getBGY - Return the background's Y position.
     *
     * @return the position of the background on the y plane
     */
    public double getBGY() {
        return bgY;
    }

    /**
     * setBGX - Sets the position of the background to value num on the X axis.
     *
     * @param num
     */
    public void setBGX(double num) {
        bgX = num;
    }

    /**
     * setBGY - Sets the position of the background to value num on the Y axis.
     *
     * @param num
     */
    public void setBGY(double num) {
        bgY = num;
    }
}
