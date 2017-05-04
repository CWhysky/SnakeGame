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
    private Sprite temp;
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
            return snake.get(i);
        }
        
        public Sprite getLast(){
            return snake.getLast();
        }
        
        public void updateHead(){
            snake.getFirst().setHeadVelocityChanges();
        }
        
   /*     public void velocityChanges(){
            if(snake.getFirst() != snake.getLast()){
                for(int i = 2; i < snake.size(); i++){
                    if(velocityCheck(i)){
                        temp = snake.get(i);
                        temp.setChgs(snake.get(i-1).getPosX(), snake.get(i-1).getPosY(), 
                                     snake.get(i-1).getVelocityX(), snake.get(i-1).getVelocityY(), snake.get(i-1).getAngle());
                        temp.checkPositions();
                    }
                }
            }
        }
        public void setSecondChgs(){
            if((snake.getFirst() != snake.getLast()) && velocityCheck(1)){
                    Sprite temp = (Sprite)snake.getFirst();
                    Sprite temp2 = (Sprite)snake.get(1);
                    temp2.setChgs(temp.getSpriteX(), temp.getSpriteY(), temp.getVelocityX(), 
                            temp.getVelocityY(), temp.getAngle());
                    temp2.checkPositions();
            }    
        }

        public int size(){
            return snake.size();
        }
        
        private boolean velocityCheck(int i){
            return ((snake.get(i).getVelocityX() != snake.get(i-1).getVelocityX())&&
                    (snake.get(i).getVelocityY() != snake.get(i-1).getVelocityY()));
        }*/
}
