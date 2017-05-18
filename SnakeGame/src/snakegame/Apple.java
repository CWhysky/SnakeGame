/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author ascott
 */
/**
 * this class is actually apple Factory class
 */
public class Apple
{
    private double x;
    private double y;
    private Sprite SpawnApple;
    private int GameGridWidth;
    
    public Apple()
    {
        x = 0.0;
        y = 0.0;
        SpawnApple = new Sprite();
        GameGridWidth = 8192;
    
    }
    /**
     * generate an apple sprite with position 
     * @return apple sprite
     */
    public Sprite GenerateApples()
    { 
       
        Sprite apple = new Sprite();
	apple.setImage("apple.png");
	double px = 350 * Math.random() + 50;
	double py = 350 * Math.random() + 50;          
	apple.setPosition(px, py);
        apple.setVelocity(px + 40, py + 40);
        
        return apple;
    }
    
    /**
     * spawn an apple in an area
     * @param Xquadrant x position of the eaten apple
     * @param Yquadrant y position of the eaten apple
     * @return apple sprite
     */
    public Sprite SpanwAppleInSameQ(double Xquadrant, double Yquadrant)
    {
      x = Xquadrant;
      y = Yquadrant;
      SpawnApple.setImage("apple.png");
      
      if (x < 0 && y > 0)
      {
        x = -GameGridWidth/2 * Math.random() + 50;
        y = GameGridWidth/2 * Math.random() + 50;
        SpawnApple.setPosition(x, y);
      }
      else if (x > 0 && y < 0)
      {
        x = GameGridWidth/2 * Math.random() + 50;
        y = -GameGridWidth/2 * Math.random() + 50;
        SpawnApple.setPosition(x, y);
      }
      else if (x < 0 && y < 0)
      {
        x = -GameGridWidth/2 * Math.random() + 50;
        y = -GameGridWidth/2 * Math.random() + 50;
        SpawnApple.setPosition(x, y);
      }
      else
      {
        x = GameGridWidth/2 * Math.random() + 50;
        y = GameGridWidth/2 * Math.random() + 50;
        SpawnApple.setPosition(x, y);
      }
    
      return SpawnApple;
    }
    
    /**
     * get the x position of spawn apple
     * @return spawned apple's x position
     */
    public double getXposition()
    {
        return this.SpawnApple.getPosX();
    }
    
    /**
     * get the y position of spawn apple
     * @return spawned apple's y position
     */
    public double getYpostion()
    {
        return this.SpawnApple.getPosY();
    }
	
}
