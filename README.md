# bitmap-transformer

# Classes and Methods
* Bitmap(String inputFilePath, String outputFilePath, String transformImageName)
* saveImage(BufferedImage newImg)
  * take new image and save it on a new folder
* edgeColor()
  * Transforms the outline color of the Baldy-8bit.bmp
* negativeColor()
  * Transform the image colors to the negative values.
* transformGrey()
  * Takes in an image and creates a greyscale version of the image
*reverseImageHorizontally()
* reverseImageVertically()
  * flip/mirror image
* blur()
  * smoothing the image


## References
* https://stackoverflow.com/questions/17015340/how-to-read-a-bmp-file-identify-which-pixels-are-black-in-java
 
* https://docs.oracle.com/javase/tutorial/2d/images/saveimage.html

* https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html

* https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html

* https://www.youtube.com/watch?v=lGX0Gc6d51s

* https://www.codejava.net/java-se/graphics/how-to-resize-images-in-java

* https://www.tutorialspoint.com/how-to-set-modify-the-pixels-rgb-values-of-an-image-using-java-opencv-library
