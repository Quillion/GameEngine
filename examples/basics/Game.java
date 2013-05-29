/**
 * @author      Edgar Quillion <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import QLibrary.*;

public class Game
{
    private int WIDTH, HEIGHT;

    private QMCharacter box;

    private List<QPlatform> platforms;
    private QPlatform temp_platform;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        platforms = new ArrayList<QPlatform>();

        temp_platform = new QPlatform();
        temp_platform.setX(0);
        temp_platform.setY(400);
        temp_platform.setWidth(630);
        temp_platform.setHeight(50);
        temp_platform.setColor(Color.GREEN);
        platforms.add(temp_platform);

        temp_platform = new QPlatform();
        temp_platform.setX(0);
        temp_platform.setY(0);
        temp_platform.setWidth(10);
        temp_platform.setHeight(390);
        temp_platform.setColor(Color.GREEN);
        platforms.add(temp_platform);

        temp_platform = new QPlatform();
        temp_platform.setX(210);
        temp_platform.setY(350);
        temp_platform.setWidth(50);
        temp_platform.setHeight(10);
        temp_platform.setColor(Color.GREEN);
        platforms.add(temp_platform);

        temp_platform = new QPlatform();
        temp_platform.setX(610);
        temp_platform.setY(10);
        temp_platform.setWidth(20);
        temp_platform.setHeight(380);
        temp_platform.setColor(Color.GREEN);
        platforms.add(temp_platform);

        box = new QMCharacter();
        box.setX(150);
        box.setY(150);
        box.setWidth(30);
        box.setHeight(30);
        box.setGravity(1);
        box.setMaxSpeed(5);
        box.setJump(17);
        box.setAcceleration(0.7);
        box.setGroundFriction(3);
        box.setAirFriction(2);
        box.setLeftKey(KeyEvent.VK_LEFT);
        box.setRightKey(KeyEvent.VK_RIGHT);
        box.setJumpKey(KeyEvent.VK_UP);
    }

    public void draw(Graphics2D g)
    {
        for(int i = 0; i < platforms.size(); i++)
        {
            platforms.get(i).draw(g);
            platforms.get(i).drawBox(g);
        }

        box.drawBox(g);
        box.draw(g);
    }

    public void update()
    {
        QEngine.preUpdate(box);
        // character is always falling until proven otherwise
        box.setStanding(false);
        for(int i = 0; i < platforms.size(); i++)
        {
            int vert = QEngine.verticalCollision(box, platforms.get(i));
            int hort = QEngine.horizontalCollision(box, platforms.get(i));

            if(hort == QConstants.RIGHT)
            {
                box.setRight(false);
                box.setX(platforms.get(i).getLeftX() - box.getWidth());
                box.setXVector(0);
            }
            else if(hort == QConstants.LEFT)
            {
                box.setLeft(false);
                box.setX(platforms.get(i).getRightX());
                box.setXVector(0);
            }

            if(vert == QConstants.UP)
            {
                box.setY(platforms.get(i).getBottomY());
                box.setYVector(0);
            }
            else if(vert == QConstants.DOWN)
            {
                box.setStanding(true);
                box.setY(platforms.get(i).getTopY()-box.getHeight());
                box.setYVector(0);
            }
        }
        QEngine.postUpdate(box);
    }

    public void keyPressed(KeyEvent e)
    {
        QEngine.keyPressed(e.getKeyCode(), box);
    }

    public void keyReleased(KeyEvent e)
    {
        QEngine.keyReleased(e.getKeyCode(), box);
    }

    public void mouseEntered(MouseEvent e) 
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public int getWIDTH ()
    {
        return WIDTH;
    }

    public int getHEIGHT ()
    {
        return HEIGHT;
    }
}
