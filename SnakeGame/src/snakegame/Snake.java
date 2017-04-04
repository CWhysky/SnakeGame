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
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

/**
 *
 * @author ascott
 */
public class Snake {
    private LinkedList snake;
	public Snake(){
            snake = new LinkedList();
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
        
        public void velocityChanges(){
            if(snake.getFirst() != snake.getLast()){
                for(int i = 0; i < snake.size(); i++){
                    Sprite temp = (Sprite)snake.get(i);
                    if(temp.checkVelChange()){
                        if(temp != snake.getLast()){
                            Sprite temp2 = (Sprite)snake.get(i+1);
                            temp2.setChgs(temp.getX(),temp.getY(),
                                          temp.getVelocityX(),temp.getVelocityY(),
                                          temp.getAngle());
                        }
                    }
                }
            }
        }
        public void setSecondChgs(){
            if(snake.getFirst() != snake.getLast()){
                Sprite temp = (Sprite)snake.getFirst();
                Sprite temp2 = (Sprite)snake.get(1);
                temp2.setChgs(temp.getX(), temp.getY(), temp.getVelocityX(), 
                              temp.getVelocityY(), temp.getAngle());
            }
        }
}
