import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.io.IOException;


public class FractalExplorer {

    private int size;
    private JImageDisplay JID;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double aDouble;

    private JComboBox<FractalGenerator> JCB;
    private Button btnSave;
    private Button btnReset;

    public FractalExplorer(int s) {
        size = s;
        aDouble = new Rectangle2D.Double();

        new Mandelbrot().getInitialRange(aDouble);
        fractalGenerator = new Mandelbrot();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JID = new JImageDisplay(size, size);
        JID.addMouseListener(new MyMouseListener().mouseListener);
        JPanel jPForCB = new JPanel(); // for combo box


        JCB = new JComboBox<>();
        JCB.addItem(new Mandelbrot());
        JCB.addItem(new Tricorn());
        JCB.addItem(new BurningShip());

        JCB.addActionListener(e -> {
            fractalGenerator = (FractalGenerator) JCB.getSelectedItem();

            if (fractalGenerator != null) {
                fractalGenerator.getInitialRange(aDouble);
                drawFractal();
                JID.repaint();
            }
        });

        jPForCB.add(new JLabel("Fractal"));
        jPForCB.add(JCB);

        JPanel jPForB = new JPanel();  //for buttons

        btnReset = new Button("Reset Display");
        ActionListener actionListenerForBtnReset = e -> {
            fractalGenerator.getInitialRange(aDouble);
            drawFractal();
            JID.repaint();
        };
        btnReset.addActionListener(actionListenerForBtnReset);

        btnSave = new Button("Save Image");
        ActionListener actionListenerForBtnSave = e -> {

            JFileChooser jFileChooser = new JFileChooser();
            FileFilter fileFilter = new FileNameExtensionFilter("Png Images", "png");
            jFileChooser.setFileFilter(fileFilter);
            jFileChooser.setAcceptAllFileFilterUsed(false);

            if (jFileChooser.showDialog(frame, "Save") == jFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(JID.getBufferedImage(), "png", jFileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(frame, ioException.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
        btnSave.addActionListener(actionListenerForBtnSave);

        jPForB.add(btnSave);
        jPForB.add(btnReset);

        frame.add(JID, BorderLayout.CENTER);
        frame.add(jPForB, BorderLayout.SOUTH);
        frame.add(jPForCB, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private int rowsRemaining;

    private void drawFractal() {
/**        double xCoord;
        double yCoord;

        int numIters;
**/
        enableUI(false);
        rowsRemaining = size;
        for (int y = 0; y < size; y++) {
            FractalWorker fractalWorker = new FractalWorker(y);
            fractalWorker.execute();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(400);
        fractalExplorer.createAndShowGUI();

        fractalExplorer.drawFractal();
    }

    class MyMouseListener extends MouseAdapter{
        public MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (rowsRemaining <= 0) {
                double xCord;
                double yCord;

                xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, e.getX());
                yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, e.getY());

                FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);

                drawFractal();
            }
        }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
    };
    }


    private void enableUI (boolean val){
        if (val){
            btnReset.setEnabled(true);
            btnSave.setEnabled(true);
            JCB.setEnabled(true);
        } else {
            btnReset.setEnabled(false);
            btnSave.setEnabled(false);
            JCB.setEnabled(false);
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object>{
        private int y;
        private int[] pixelsRGB;

        private FractalWorker(int y){this.y = y;}

        @Override
        protected Object doInBackground() throws Exception{
        pixelsRGB =new int[size+1];

        double xCoord;
        double yCoord;

        int numIters;

        yCoord = FractalGenerator.getCoord(aDouble.y,aDouble.y + aDouble.height,size,y);

        for(int x = 0; x<size;x++){
            xCoord = FractalGenerator.getCoord(aDouble.x,aDouble.x + aDouble.width,size,x);

            numIters = fractalGenerator.numIterations(xCoord,yCoord);

            if(numIters!=-1){
                float hue = 0.7f + (float) numIters / 200f;
                pixelsRGB[x] = Color.HSBtoRGB(hue, 1f, 1f);
            }
            else pixelsRGB[x] = 0;
            }
        return null;
        }

        @Override
        protected void done(){
            super.done();
            for(int x = 0; x<size;x++) {
                JID.drawPixel(x,y,pixelsRGB[x]);
            }
            JID.repaint(1,y,size,1);

            rowsRemaining--;
                if(rowsRemaining<=0)enableUI(true);
        }
    }

}