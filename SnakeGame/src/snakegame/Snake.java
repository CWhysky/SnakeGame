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
    private LinkedList<Sprite> snake;
    private int growCount;
    private String bodyColor;
    private int score;
    
    public Snake(){
        snake = new LinkedList();
        growCount = 0;
        score = 0;
    }
    
    /**
     * sets the head of the snake
     * @param s where the sprite is the new head of the snake
     */
    public void setHead(Sprite s){
        snake.addFirst(s);
    }
    
    /**
     * gets the head of the snake
     * @return the head of the snake
     */
    public Sprite getHead(){
        return snake.getFirst();
    }
    
    /**
     * adds the sprite to the body at the end of the snake
     * @param s the sprite to be added
     */
    public void addBody(Sprite s){
        snake.add(s);
    }
    
    public LinkedList<Sprite> getBody() {
        return snake;
    }
    
    public void setBodyColor(String bc) {
        bodyColor = bc;
    }
    
    public String getBodyColor() {
        return bodyColor;
    }
    
    public void dropTail() {
        Sprite h = snake.getFirst();
        snake = new LinkedList();
        snake.addFirst(h);
    }
    
    /**
     * returns the size of the snake
     * @return the size of the snake
     */
    public int getSize(){
        return snake.size();
    }
    
    public int getGrowCount() {
        return growCount;
    }
    
    public void setGrowCount(int gc) {
        growCount = gc;
    }
    
    /**
     * gets the segment in the snake at position i
     * @param i the index of the segment to return
     * @return the segment at position i
     */
    public Sprite getSegment(int i){
        return snake.get(i);
    }
    
    /**
     * gets the last segment in the snake
     * @return the last segment of the snake
     */
    public Sprite getLast(){
        return snake.getLast();
    }   
    
    public void setScore(int s) {
        score = s;
    }
    
    public int getScore() {
        return score;
    }
    
}
