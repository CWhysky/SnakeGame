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
    private double backgroundVelX;
    private double backgroundVelY;
    private double backgroundPosX;
    private double backgroundPosY;
   
    //constructor
    public BackgroundMV()
    {
        backgroundVelX = 0.0;
        backgroundVelY = 0.0;
        backgroundPosX = 0.0;
        backgroundPosY = 0.0;  
    }
    
    // Background Velocity, Accessors, and Settings
    double getBackGroundVelX() 
    {
        return backgroundVelX;
    }

    double getBackGroundVelY() 
    {
        return backgroundVelY;
    }

    void setBackGroundVelX(double velocity) 
    {
        backgroundVelX = velocity;
    }

    void setBackGroundVelY(double velocity) 
    {
        backgroundVelY = velocity;
    }

    // Background Position, Accessors, and Setter
    double getBackGroundPosX() 
    {
        return backgroundPosX;
    }

    double getBackGroundPosY()
    {
        return backgroundPosY;
    }

    void setBackGroundPosX(double Xposition) 
    {
        backgroundPosX = Xposition;
    }

    void setBackGroundPosY(double Yposition) 
    {
        backgroundPosY = Yposition;
    }
    
}