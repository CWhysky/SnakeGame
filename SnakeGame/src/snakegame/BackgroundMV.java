/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author Manuel
 */
public class BackgroundMV 
{
    private double bgVelX;
    private double bgVelY;
    private double bgX;
    private double bgY = 0.0;
   
    //constructor
    public BackgroundMV()
    {
        bgVelX = 0.0;
        bgVelY = 0.0;
        bgX = 0.0;
        bgY = 0.0;
        
    }
     
    // Background Velocity, Accessors, and Settings
    double getBGVelX() 
    {
        return bgVelX;
    }

    double getBGVelY() 
    {
        return bgVelY;
    }

    void setBGVelX(double num) 
    {
        bgVelX = num;
    }

    void setBGVelY(double num) 
    {
        bgVelY = num;
    }

    // Background Position, Accessors, and Setter
    double getBGX() 
    {
        return bgX;
    }

    double getBGY()
    {
        return bgY;
    }

    void setBGX(double num) 
    {
        bgX = num;
    }

    void setBGY(double num) 
    {
        bgY = num;
    }
    
}