package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    String inputPath;
    String outputPath;
    String newImageName;

    BufferedImage image = null;

    //read bitmap image constructor
    public Bitmap(String inputPath, String outputPath, String newImageName) throws IOException {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.newImageName = newImageName;
        try {
            this.image = ImageIO.read(new File(inputPath));

        } catch (IOException ioException) {
            System.out.println(ioException);
        }

    }

    // save changed image to chosen path.
    public void saveImage(BufferedImage newImg) throws IOException {
        try {
            File output = new File(this.outputPath + "/" + newImageName + ".bmp");
            System.out.println(output);
            ImageIO.write(newImg, "bmp", output);

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    // function to change edge color
    public boolean edgeColor() throws IOException {
        boolean hasChanged = false;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int color = image.getRGB(i, j);
                System.out.println(color);
                if (color == -16777216) {
                    image.setRGB(i, j, Color.GREEN.getRGB());
                    hasChanged = true;
                }
            }
        }
        this.saveImage(this.image);
        return hasChanged;

    }

    // function to make image negative
    public boolean NegativeColor() throws IOException {
        boolean hasChanged = false;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                image.setRGB(x, y, col.getRGB());

                /*
                * you can make oit threshold by:
                * int MONO_THRESHOLD = 368;
                   if (col.getRed() + col.getGreen() + col.getBlue() > MONO_THRESHOLD)
                   col = new Color(255, 255, 255);
                   else
                   col = new Color(0, 0, 0);
                * */
                hasChanged = true;
            }
        }
        this.saveImage(this.image);
        return hasChanged;

    }

    // function to make image in grey scale
    public BufferedImage transformGrey() throws IOException {
        BufferedImage grey = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics modifications = grey.getGraphics();
        modifications.drawImage(this.image, 0, 0, null);
        modifications.dispose();
        this.saveImage(grey);
        return grey;
    }
// flip image Vertically

    public void reverseImageVertically() throws IOException {

        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight() / 2; j++) {
                int temp = this.image.getRGB(i, j);
                this.image.setRGB(i, j, this.image.getRGB(i, this.image.getHeight() - j - 1));
                this.image.setRGB(i, this.image.getHeight() - j - 1, temp);
            }
        }
        this.saveImage(image);
    }
// flip image horizontally

    public void reverseImageHorizontally() throws IOException {
        for (int i = 0; i < this.image.getHeight() / 2; i++) {
            for (int j = 0; j < this.image.getWidth(); j++) {
                int temp = this.image.getRGB(i, j);
                this.image.setRGB(i, j, this.image.getRGB(this.image.getWidth() - i - 1, j));
                this.image.setRGB(this.image.getWidth() - i - 1, j, temp);
            }
        }
        this.saveImage(image);
    }
    //Method to blur out image
    public  BufferedImage blur() throws IOException {

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType()) ;
        final int H = image.getHeight() - 1 ;
        final int W = image.getWidth() - 1 ;

        for (int c=0 ; c < image.getRaster().getNumBands() ; c++) // for all the channels/bands
            for (int x=1 ; x < W ; x++) // For all the image
                for (int y=1; y < H ; y++)
                {
                    int newPixel = 0 ;
                    for (int i=-1 ; i <= 1 ; i++) // For the neighborhood
                        for (int j=-1 ; j <= 1 ; j++)
                            newPixel += image.getRaster().getSample(x+i, y+j, c) ;
                    newPixel = (int)(newPixel/12.0 + 1) ;
                    result.getRaster().setSample(x, y, c, newPixel) ;
                }
        this.saveImage(result);
        return result;
    }

}
