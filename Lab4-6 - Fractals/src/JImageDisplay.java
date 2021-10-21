import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent {

    private BufferedImage bufferedImage;

    public JImageDisplay (int x, int y){
        bufferedImage = new BufferedImage(x,y, BufferedImage.TYPE_INT_RGB);

        setPreferredSize(new Dimension(x,y));
    }
    protected void paintComponent (Graphics g){
        g.drawImage (bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }


    public void clearImage (int x, int y){
        bufferedImage.setRGB(x,y,0);
    }

    public void drawPixel (int x, int y, int rgbColor){
        bufferedImage.setRGB(x,y,rgbColor);
    }

    public BufferedImage getBufferedImage () {
        return bufferedImage;
    }
}
