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
public class Apple extends Sprite 
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
    
    public double getXposition()
    {
        return this.SpawnApple.getPosX();
    }
    
    public double getYpostion()
    {
        return this.SpawnApple.getPosY();
    }
	
}
