package dungeon1;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Game
{
    private int WIDTH, HEIGHT;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void update()
    {
    }

    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
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
