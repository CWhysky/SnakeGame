/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Darren ZL Zhen, Tim Beiko 
 */
public class SnakeAI {
    
    Snake snake;
    Sprite snakeSprite;
    public double memAngle = 0.0;
    public boolean mem = false;
    public boolean picksClosest;
    
    public SnakeAI(Snake snake, boolean picksClosest){
        this.snake = snake;
        this.snakeSprite = null;
        this.picksClosest = picksClosest;
    }
    
    public SnakeAI(Sprite snakeSprite, boolean picksClosest){
        this.snake = null;
        this.snakeSprite = snakeSprite;
        this.picksClosest = picksClosest;
    }
    
    public void setHead(Sprite head){
        this.snakeSprite = head;
    }
    
    
    //calculate the shortest Distance apple with snake
    public Sprite shortestApple(ArrayList<Sprite> appleList){
        double minDist = 999999999;
        Sprite closestApple  = null;
        
        Iterator<Sprite> appleIter = appleList.iterator();
        while (appleIter.hasNext()) {
            Sprite apple = appleIter.next();
            double ax = apple.getPosX();
            double ay = apple.getPosY();
            
            double sx = this.snakeSprite.getPosX();
            double sy = this.snakeSprite.getPosY();
            
            double dist = java.lang.Math.sqrt(java.lang.Math.pow((ax*ax - sx*sx),2) + java.lang.Math.pow((ay*ay - sy*sy),2));
            
            if (dist < minDist) {
                minDist = dist;
                closestApple = apple;
            }                
        }
        
        return closestApple;
    }
    
    //calculate the angle between the snake and apple
    public double calAngle(Sprite apple){
        double x = apple.getPosX() - snakeSprite.getPosX();
        double y = apple.getPosY() - snakeSprite.getPosY();
        double newAngle = (Math.toDegrees(Math.atan2(y, x))+90.0);
        
        return newAngle;
    }
    
    
    
}
