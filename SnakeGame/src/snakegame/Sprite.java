package snakegame;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private ArrayList changeX;
    private ArrayList changeY;
    private ArrayList changeVelX;
    private ArrayList changeVelY;
    private ArrayList changeAngle;
    private double width;
    private double height;
    private double angle;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        changeX = new ArrayList();
        changeY = new ArrayList();
        changeVelX = new ArrayList();
        changeVelY = new ArrayList();
        changeAngle = new ArrayList();
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
	// angle = 90;
    }
    
    public double getWidth(){
        return width;
    }
    

    public Image getImage()
    {
        return image;
    }
    public double getAngle()
    {
        return angle;
    }


    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);

	// theImageView = new ImageView();
	// Image theImage = new Image(filename);
	// theImageView.setImage(i);
	// theImageView.setRotate(angle);
    }
    
    public void setPosition(double x, double y, double angle)
    {
        positionX = x;
        positionY = y;
	this.angle = angle;
    }
    
    public void setPosition(Snake snake){
        if(snake.getLast().getVelocityX() == 0){
            positionX = snake.getLast().getX();
            if(snake.getLast().getVelocityY() == 250){
                positionY = (snake.getLast().getY()) - (snake.getLast().getWidth());
            }else{
                positionY = (snake.getLast().getY()) + (snake.getLast().getWidth());
            }
        }else{
            positionY = snake.getLast().getY();
            if(snake.getLast().getVelocityX() == 250){
                positionX = (snake.getLast().getX()) - (snake.getLast().getWidth());
            }else{
                positionX = (snake.getLast().getX()) + (snake.getLast().getWidth());
            }
        }
    }
    
    public double getX(){
        return positionX;
    }
    
    public double getY(){
        return positionY;
    }

    public void setVelocity(double x, double y, double angle)
    {
        velocityX = x;
        velocityY = y;
        this.angle = angle;

	    // ImageView iv = new ImageView(new Image("gfx/earth.png"));
	    ImageView iv = new ImageView(new Image("snake_head_red.png"));
	    // ImageView iv = new ImageView(new Image(theSnake.getImage()));
	    iv.setRotate(angle);
	    SnapshotParameters params = new SnapshotParameters();
	    params.setFill(Color.TRANSPARENT);
	    Image rotatedImage = iv.snapshot(params, null);
	    setImage(rotatedImage);

	
    }
    
    public void setVelocity(Snake snake){
        velocityX = snake.getLast().getVelocityX();
        velocityY = snake.getLast().getVelocityY();
        this.angle = snake.getLast().getAngle();
    }
    
    public double getVelocityX(){
        return velocityX;
    }
    
    public double getVelocityY(){
        return velocityY;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
	// gc.rotate(angle);
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
    public boolean checkVelChange(){
        if(!changeX.isEmpty()){
            if(checkPositions()){
                System.out.println("Passed position check");
                velocityX = (double)changeVelX.get(0);
                velocityY = (double)changeVelY.get(0);
                angle = (double)changeAngle.get(0);
                changeX.remove(0);
                changeY.remove(0);
                changeVelX.remove(0);
                changeVelY.remove(0);
                changeAngle.remove(0);
                return true;
            }
        }
        return false;
    }
    
    public void setChgs(double x, double y, double velX, double velY, double angle){
//      System.out.println("x=" + x+ " y="+y+" velX="+velX+"velY="+velY+" angle="+angle);
        changeX.add(x);
        changeY.add(y);
        changeVelX.add(velX);
        changeVelY.add(velY);
        changeAngle.add(angle);
    }
    
    private boolean checkPositions(){
        return ((((double)changeX.get(0) - 2.5 < positionX) && 
                positionX < (double)changeX.get(0) + 2.5)
                &&(((double)changeY.get(0) - 2.5 < positionY) && 
                    (double)changeY.get(0) + 2.5 > positionY));
   
  //    return (((double)changeX.get(0) == positionX)&&((double)changeY.get(0) == positionY));
    }
}