/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author Darren ZL Zhen
 */
public class SnakeAI {
    
    Snake snake;
    Sprite snakeSprite;
    public double memAngle = 0.0;
    public boolean mem = false;
    
    public SnakeAI(Snake snake){
        this.snake = snake;
        this.snakeSprite = null;
    }
    
    public SnakeAI(Sprite snakeSprite){
        this.snake = null;
        this.snakeSprite = snakeSprite;
    }
    
    //calculate the shortest Distance apple with snake
    public void shortestApple(Sprite apple){
        
        return;
    }
    
    //calculate the angle between the snake and apple
    public double calAngle(Sprite apple){
        double x = apple.getPosX() - snakeSprite.getPosX();
        double y = apple.getPosY() - snakeSprite.getPosY();
        double newAngle = (Math.toDegrees(Math.atan2(y, x))+90.0);
        
        return newAngle;
    }
    
    
    
}
