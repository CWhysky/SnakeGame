package snakegame;

/**
 * Holds the information for the background velocities and positions
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
    * 
    * @return The x value of the background velocity
    */
    public double getBGVelX() {
        return bgVelX;
    }

    /**
     * 
     * @return The y value of the background velocity
     */
    public double getBGVelY() {
        return bgVelY;
    }

    /**
     * Sets the x value of the background velocity
     * @param num the double to be set to background velocity X
     */
    public void setBGVelX(double num) {
        bgVelX = num;
    }

    /**
     * Sets the y value of the background velocity
     * @param num the double to be set to background velocity Y
     */
    public void setBGVelY(double num) {
        bgVelY = num;
    }

    /**
     * 
     * @return the position of the background on the x plane
     */
    public double getBGX() {
        return bgX;
    }

    /**
     * 
     * @return the position of the background on the y plane
     */
    public double getBGY() {
        return bgY;
    }
    
    /**
     * sets the position of the background to value num on the x plane
     * @param num 
     */
    public void setBGX(double num) {
        bgX = num;
    }

    /**
     * sets the position of the background to value num on the y plane
     * @param num 
     */
    public void setBGY(double num) {
        bgY = num;
    }
}
