package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

public class Sprite
{
    private Image image;
    private ImageView theImageView;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double angle;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
	// angle = 90;
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
	angle = angle;
    }

    public void setVelocity(double x, double y, double angle)
    {
        velocityX = x;
        velocityY = y;
	angle = angle;

	    // ImageView iv = new ImageView(new Image("gfx/earth.png"));
	    ImageView iv = new ImageView(new Image("snake_head_red.png"));
	    // ImageView iv = new ImageView(new Image(theSnake.getImage()));
	    iv.setRotate(angle);
	    SnapshotParameters params = new SnapshotParameters();
	    params.setFill(Color.TRANSPARENT);
	    Image rotatedImage = iv.snapshot(params, null);
	    setImage(rotatedImage);

	
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
}