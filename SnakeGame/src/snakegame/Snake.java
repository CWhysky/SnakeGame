/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

/**
 *
 * @author ascott
 */
public class Snake {
    private double velX;
    private double velY;
    private LinkedList<Sprite> snake;
    
	public Snake(){
            snake = new LinkedList();
            velX = 0;
            velY = 0;
        }
        
        public void setHead(Sprite s){
            snake.addFirst(s);
        }
        
        public Sprite getHead(){
            return (Sprite)snake.getFirst();
        }
        
        public void addBody(Sprite s){
            snake.add(s);
        }
        
        public int getSize(){
            return snake.size();
        }
        
        public Sprite getSegement(int i){
            return (Sprite)snake.get(i);
        }
        
        public Sprite getLast(){
            return (Sprite)snake.getLast();
        }
        
        public void velocityChanges(double time, Scene scene){
            if(snake.getFirst() != snake.getLast()){
                for(int i = 1; i < snake.size(); i++){
                    Sprite temp = (Sprite)snake.get(i);
                    if(temp.checkVelChange()){
                        temp.update(time, scene);
                        temp.checkPositions();
                    }
                }
            }
        }
      /*  public void setSecondChgs(){
            if(snake.getFirst() != snake.getLast()){
                Sprite temp = (Sprite)snake.getFirst();
                Sprite temp2 = (Sprite)snake.get(1);
                temp2.setChgs(temp.getSpriteX(), temp.getSpriteY(), temp.getVelocityX(), 
                              temp.getVelocityY(), temp.getAngle());
            }    
        }*/
        
        public void update(double bgVelX, double bgVelY){
            if((velX != bgVelX) || (velY != bgVelY)){
                velX = bgVelX;
                velY = bgVelY;
                Sprite head = (Sprite)snake.getFirst();
                for(int i = 1; i < snake.size(); i++){
                    Sprite temp = (Sprite)snake.get(i);
                    temp.setChgs(head.getPosX(), head.getPosY(), velX, velY);
                }
            }
        }
        public int size(){
            return snake.size();
        }
}