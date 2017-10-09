package Lecture5;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.lang.Math;


/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * This version modified by Scot Drysdale to demonstrate arraylists.
 * (used in reduceTo8).  See end of listing.
 * 
 * Copyright Georgia Institute of Technology 2004-2008
 * @author Barbara Ericson ericson@cc.gatech.edu
 * Modified by Scot Drysdale to eliminate some warnings.
 */
public class Picture extends SimplePicture { 

  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture () {
    
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName) {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height) {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to be copied
   */
  public Picture(Picture copyPicture) {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image) {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  
  /////////////// Part 1 ////////////////
  
  
  /**
   * Method to reduce to 8 colors, using given colors
   */
  public Picture mapToColorList(ArrayList<Color> colors) {
	  
	  Picture mapToPic = this.copy();
	  int pictureWidth = this.getWidth();
	  int pictureHeight = this.getHeight();
	  
	  for(int x = 0; x < pictureWidth; x++) {
		  for(int y = 0; y < pictureHeight; y++) {
			  Pixel currPixel = mapToPic.getPixel(x, y);
			  int index = computeNearestColor(colors, currPixel.getColor());
			  currPixel.setColor(colors.get(index));
		  }
	  }  
	  return mapToPic;
  }
  
  public int computeNearestColor(ArrayList<Color> colors, Color color2) {
	  // Color minColor = color2;
	  double minLength = -1;
	  int n = colors.size();
	  int index = 0;
	  
	  for(int i=0 ; i < n ; i++) {
		  double Length = Pixel.colorDistance(colors.get(i), color2);
		  if(i==0) {
			  minLength = Length;
			  // minColor = colors.get(i);
			  index = i;
		  } else {
			  if(Length < minLength) {
				  minLength = Length;
				  // minColor = colors.get(i);
				  index = i;
			  }
		  }
	  }
	  return index;
  }
  
   /////////////// Part 2 ////////////////
  
  public ArrayList<Color> generateRandomColorList(int n) {
	  ArrayList<Color> colors = new ArrayList<Color>();
	  
	  for( int i = 0 ; i < n ; i++) {
		  int red = (int)(256 * Math.random());
		  int green = (int)(256 * Math.random());
		  int blue = (int)(256 * Math.random());
		  Color color = new Color(red, green, blue);
		  colors.add(i,color);
	  }
	  return colors;  
  }
  
  /////////////// Part 3 ////////////////
  
  public void printList(ArrayList<Color> refColors) {
	  System.out.println("New Iteration: \n");
	  System.out.println("| i |  r  |  g  |  b  | \n");
	  for(int i = 0; i < refColors.size() ; i++) {
		System.out.print("| " + i + " | ");
		printNumber(refColors.get(i).getRed());
		System.out.print(" | ");
		printNumber(refColors.get(i).getGreen());
		System.out.print(" | ");
		printNumber(refColors.get(i).getBlue());
		System.out.println(" | \n");
	  }
  }
  
  public void printNumber(int number) {
	  if(0 <= number && number < 10) {
		  System.out.print("  " + number);
	  }
	  else if(10 <= number && number <100 ) {
		  System.out.print(" " + number);
	  }
	  else if(100 <= number) {
		  System.out.print(number);
	  }
  }
  
  public Picture reduceColors(int number) {
	  return mapToColorList(computeColors(number));
  }
  
  public ArrayList<Color> computeColors(int number){
	  ArrayList<Color> oldRefColors = startArrayListOfColors(number); 
	  ArrayList<Color> newRefColors = new ArrayList<Color>();
	  // ArrayList<Color> checkColors = oldRefColors;
	  
	  int tick = 0;
	  
	  while(tick == 0) {
		  newRefColors = recluster(oldRefColors, number);
		  if(!newRefColors.equals(oldRefColors)) {
			  oldRefColors = newRefColors;
		  }
		  else if(newRefColors.equals(oldRefColors)) {
			  tick = 1;
		  }
	  }
	  	  
	  return newRefColors;
  }
  
  public ArrayList<Color> recluster(ArrayList<Color> oldRefColors, int number) {
	  ArrayList<ArrayList<Color>> clusters = new ArrayList<ArrayList<Color>>();
	  
	  for(int i = 0; i < number; i++) {
		  clusters.add(new ArrayList<Color>());  
	  }
	  
	  int pictureWidth = this.getWidth();
	  int pictureHeight = this.getHeight();
	  
	  for(int x = 0; x < pictureWidth; x++) {
		  for(int y = 0; y < pictureHeight; y++) {
			  //getColor of pixel
			  Pixel currPixel = this.getPixel(x, y);
			  //findNearestColorFromList
			  int index = computeNearestColor(oldRefColors, currPixel.getColor());
			  //assignColorToCluster
			  assignColorToCluster(currPixel,index,clusters);
		  }
	  }
	  
	  ArrayList<Color> newRefColors = new ArrayList<Color>();
	  for(int i = 0; i < number; i++) {
		  newRefColors.add(calculateCentroid(clusters, i));
	  }
	  
	  printList(newRefColors);
	  
	  return newRefColors;
  }
  
  public void assignColorToCluster(Pixel currPixel, int index, ArrayList<ArrayList<Color>> clusters) {
	  clusters.get(index).add(currPixel.getColor());
  }
  
  public Color calculateCentroid(ArrayList<ArrayList<Color>> clusters, int i) {
	  int length = clusters.get(i).size();
	  int sumRed = 0;
	  int sumGreen = 0;
	  int sumBlue = 0;
	  int avgRed = 0;
	  int avgGreen = 0;
	  int avgBlue = 0;
	  
	  for(int j = 0; j < length; j++) {
		  sumRed = sumRed + clusters.get(i).get(j).getRed();
		  sumGreen = sumGreen + clusters.get(i).get(j).getGreen();
		  sumBlue = sumBlue + clusters.get(i).get(j).getBlue();
	  }
	  
	  if(length != 0) {
		  avgRed = sumRed / length;
		  avgGreen = sumGreen / length;
		  avgBlue = sumBlue / length;
	  }
	  
	  Color centroid = new Color(avgRed,avgGreen,avgBlue);
	  
	  return centroid;
  }
  
  public ArrayList<Color> startArrayListOfColors(int number){
	  return generateRandomColorList(number);
  }
  

  
  
 
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString() {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
   /**
   * Class method to let the user pick a file name and then create the picture 
   * and show it
   * @return the picture object
   */
  public static Picture pickAndShow() {
    String fileName = FileChooser.pickAFile();
    Picture picture = new Picture(fileName);
    picture.show();
    return picture;
  }
  
  /**
   * Class method to create a picture object from the passed file name and 
   * then show it
   * @param fileName the name of the file that has a picture in it
   * @return the picture object
   */
  public static Picture showNamed(String fileName) {
    Picture picture = new Picture(fileName);
    picture.show();
    return picture;
  }
  
  /**
   * A method create a copy of the current picture and return it
   * @return the copied picture
   */
  public Picture copy()
  {
    return new Picture(this);
  }
  
  /**
   * Method to increase the red in a picture.
   */
  public void increaseRed() {
    Pixel [] pixelArray = this.getPixels();
    for (Pixel pixelObj : pixelArray) {
      pixelObj.setRed(pixelObj.getRed()*2);
    }
  }
  
  /**
   * Method to negate a picture
   */
  public void negate() {
    Pixel [] pixelArray = this.getPixels();
    int red,green,blue;
    
    for (Pixel pixelObj : pixelArray) {
      red = pixelObj.getRed();
      green = pixelObj.getGreen();
      blue = pixelObj.getBlue();
      pixelObj.setColor(new Color(255-red, 255-green, 255-blue));
    }
  }
  
  /**
   * Method to flip a picture 
   * @return the flipped version of the picture
   */
  public Picture flip() {
    Pixel currPixel = null;
    Pixel targetPixel = null;
    Picture target = 
      new Picture(this.getWidth(),this.getHeight());
    
    for (int srcX = 0, trgX = getWidth()-1; 
         srcX < getWidth();
         srcX++, trgX--) {
      for (int srcY = 0, trgY = 0; 
           srcY < getHeight();
           srcY++, trgY++) {
        
        // get the current pixel
        currPixel = this.getPixel(srcX,srcY);
        targetPixel = target.getPixel(trgX,trgY);
        
        // copy the color of currPixel into target
        targetPixel.setColor(currPixel.getColor());
      }
    }
    return target;
  }
  
  /**
   * Method to decrease the red by half in the current picture
   */
  public void decreaseRed() {
  
    Pixel pixel = null; // the current pixel
    int redValue = 0;       // the amount of red

    // get the array of pixels for this picture object
    Pixel[] pixels = this.getPixels();

    // start the index at 0
    int index = 0;

    // loop while the index is less than the length of the pixels array
    while (index < pixels.length) {

      // get the current pixel at this index
      pixel = pixels[index];
      // get the red value at the pixel
      redValue = pixel.getRed();
      // set the red value to half what it was
      redValue = (int) (redValue * 0.5);
      // set the red for this pixel to the new value
      pixel.setRed(redValue);
      // increment the index
      index++;
    }
  }
  
  /**
   * Method to decrease the red by an amount
   * @param amount the amount to change the red by
   */
  public void decreaseRed(double amount) {
 
    Pixel[] pixels = this.getPixels();
    Pixel p = null;
    int value = 0;

    // loop through all the pixels
    for (int i = 0; i < pixels.length; i++) {
 
      // get the current pixel
      p = pixels[i];
      // get the value
      value = p.getRed();
      // set the red value the passed amount time what it was
      p.setRed((int) (value * amount));
    }
  }
  
  /**
   * Method to compose (copy) this picture onto a target picture
   * at a given point.
   * @param target the picture onto which we copy this picture
   * @param targetX target X position to start at
   * @param targetY target Y position to start at
   */
  public void compose(Picture target, int targetX, int targetY) {
 
    Pixel currPixel = null;
    Pixel newPixel = null;

    // loop through the columns
    for (int srcX=0, trgX = targetX; srcX < this.getWidth();
         srcX++, trgX++) {
  
      // loop through the rows
      for (int srcY=0, trgY=targetY; srcY < this.getHeight();
           srcY++, trgY++) {

        // get the current pixel
        currPixel = this.getPixel(srcX,srcY);

        /* copy the color of currPixel into target,
         * but only if it'll fit.
         */
        if (trgX < target.getWidth() && trgY < target.getHeight()) {
          newPixel = target.getPixel(trgX,trgY);
          newPixel.setColor(currPixel.getColor());
        }
      }
    }
  }
  
  /**
   * Method to scale the picture by a factor, and return the result
   * @param factor the factor to scale by (1.0 stays the same,
   *    0.5 decreases each side by 0.5, 2.0 doubles each side)
   * @return the scaled picture
   */
  public Picture scale(double factor) {
    
    Pixel sourcePixel, targetPixel;
    Picture canvas = new Picture(
                                 (int) (factor*this.getWidth())+1,
                                 (int) (factor*this.getHeight())+1);
    // loop through the columns
    for (double sourceX = 0, targetX=0;
         sourceX < this.getWidth();
         sourceX+=(1/factor), targetX++) {
      
      // loop through the rows
      for (double sourceY=0, targetY=0;
           sourceY < this.getHeight();
           sourceY+=(1/factor), targetY++) {
        
        sourcePixel = this.getPixel((int) sourceX,(int) sourceY);
        targetPixel = canvas.getPixel((int) targetX, (int) targetY);
        targetPixel.setColor(sourcePixel.getColor());
      }
    }
    return canvas;
  }
  
  /**
   * Method to do chromakey using an input color for the background
   * and a point for the upper left corner of where to copy
   * @param target the picture onto which we chromakey this picture
   * @param bgColor the color to make transparent
   * @param threshold within this distance from bgColor, make transparent
   * @param targetX target X position to start at
   * @param targetY target Y position to start at
   */
  public void chromakey(Picture target, Color bgColor, int threshold,
                        int targetX, int targetY) {
 
    Pixel currPixel = null;
    // loop through the columns
    for (int srcX=0, trgX=targetX;
        srcX<getWidth() && trgX<target.getWidth();
        srcX++, trgX++) {

      // loop through the rows
      for (int srcY=0, trgY=targetY;
        srcY<getHeight() && trgY<target.getHeight();
        srcY++, trgY++) {

        // get the current pixel
        currPixel = this.getPixel(srcX,srcY);

        /* if the color at the current pixel is within threshold of
         * the input color, then don't copy the pixel
         */
        if (currPixel.colorDistance(bgColor)>threshold) {
          target.getPixel(trgX,trgY).setColor(currPixel.getColor());
        }
      }
    }
  }
  
    /**
   * Method to do chromakey assuming a blue background 
   * @param target the picture onto which we chromakey this picture
   * @param targetX target X position to start at
   * @param targetY target Y position to start at
   */
  public void blueScreen(Picture target,
                        int targetX, int targetY) {

    Pixel currPixel = null;
    // loop through the columns
    for (int srcX=0, trgX=targetX;
         srcX<getWidth() && trgX<target.getWidth();
         srcX++, trgX++) {

      // loop through the rows
      for (int srcY=0, trgY=targetY;
           srcY<getHeight() && trgY<target.getHeight();
           srcY++, trgY++) {

        // get the current pixel
        currPixel = this.getPixel(srcX,srcY);

        /* if the color at the current pixel mostly blue (blue value is
         * greater than red and green combined), then don't copy pixel
         */
        if (currPixel.getRed() + currPixel.getGreen() > currPixel.getBlue()) {
          target.getPixel(trgX,trgY).setColor(currPixel.getColor());
        }
      }
    }
  }
  
  /**
   * Method to change the picture to gray scale with luminance
   */
  public void grayscaleWithLuminance()
  {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int luminance = 0;
    double redValue = 0;
    double greenValue = 0;
    double blueValue = 0;

    // loop through all the pixels
    for (int i = 0; i < pixelArray.length; i++)
    {
      // get the current pixel
      pixel = pixelArray[i];

      // get the corrected red, green, and blue values
      redValue = pixel.getRed() * 0.299;
      greenValue = pixel.getGreen() * 0.587;
      blueValue = pixel.getBlue() * 0.114;

      // compute the intensity of the pixel (average value)
      luminance = (int) (redValue + greenValue + blueValue);

      // set the pixel color to the new color
      pixel.setColor(new Color(luminance,luminance,luminance));

    }
  }
  
  /** 
   * Method to do an oil paint effect on a picture
   * @param dist the distance from the current pixel 
   * to use in the range
   * @return the new picture
   */
  public Picture oilPaint(int dist) {
    
    // create the picture to return
    Picture retPict = new Picture(this.getWidth(),this.getHeight());
    
    // declare pixels
    Pixel currPixel = null;
    Pixel retPixel = null;
    
    // loop through the pixels
    for (int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        currPixel = this.getPixel(x,y);
        retPixel = retPict.getPixel(x,y);
        retPixel.setColor(currPixel.getMostCommonColorInRange(dist));
      }
    }
    return retPict;
  }
  
  
  public Picture convolve(double [][] matrix, String title) {
	  
	  Picture convolvePic = new Picture(this.getWidth(),this.getHeight());
	  convolvePic.setTitle(title);
	  
	  int pictureWidth = this.getWidth();
	  int pictureHeight = this.getHeight();
	  
	  for(int x = 1; x < pictureWidth - 1; x++) {
		  for(int y = 1; y < pictureHeight - 1; y++) {
			  Pixel currPixel = convolvePic.getPixel(x, y);
			  this.convolvePixel(matrix,x,y, currPixel);
		  }
	  }
	  
	  return convolvePic;
  }
  
  
  private void convolvePixel(double [][] matrix, int x, int y, Pixel newPixel) {
	  // Added Picture convolvePic to signature, so that I could modify convolvePic in the helper method
	  
	  int red = 0;
	  int blue = 0;
	  int green = 0;
	  
	  double matrixValue = 0;
	  	  
	  for(int i = -1; i <= 1; i++) {
		  for(int j = -1; j <= 1; j++) {
			  
			  Pixel curr = this.getPixel(x+i, y+j);
		      matrixValue = matrix[i+1][j+1];
			  
		      red += (int)(curr.getRed() * matrixValue);
		      
		      blue += (int)(curr.getBlue() * matrixValue);
		      
		      green += (int)(curr.getGreen() * matrixValue);
		  }
	  }
	  
	  Picture.setColor(newPixel, Pixel.correctValue(red), 1);  //changed first argument to changePixel, rather than getPixel(x,y)
	  Picture.setColor(newPixel, Pixel.correctValue(blue), 3);
	  Picture.setColor(newPixel, Pixel.correctValue(green), 2);
  }
    

  
  /***********
   * Methods added by Scot Drysdale to demonstrate ArrayLists
   **********/
 
 /**
  * Reduces the number of colors to 8 by picking two values for red,
  * two for green, and two for blue.  The two red values chosen are the
  * average of the pixel red value that are greater than a threshold
  * and the average of the pixel red values less than or equal to the threshold.
  * The same is done for green and blue
  */
 public void reduceTo8() {
   Pixel [] pixelArray = this.getPixels();  // Array of all pixels in the image
   final int THRESHOLD = 126;     // Dividing line between low and high color values
   
   for(int colorNum = 1; colorNum <= 3; colorNum++) {
     ArrayList<Pixel> lowValues = new ArrayList<Pixel>();
     ArrayList<Pixel> highValues = new ArrayList<Pixel>();
     
     for(Pixel pixel : pixelArray) {
       // Split the pixels into low and high color values for color colorNum
       if(getColor(pixel, colorNum) <= THRESHOLD)
         lowValues.add(pixel);
       else
         highValues.add(pixel);
     } 
       
     int lowAve = Math.round(averageColors(lowValues, colorNum));
     int highAve = Math.round(averageColors(highValues, colorNum));
       
     // Reset the color values to the average values
     for (Pixel lowPix : lowValues)
       setColor(lowPix, lowAve, colorNum);
     for (Pixel highPix : highValues)
       setColor(highPix, highAve, colorNum);                                 
   }
 }
 
 /**
  * Gets the value of the color corresponding to colorNum.
  * In an ideal world this would be added to the Pixel class
  * Precondition - colorNum is 1, 2, or 3.  (We will learn to throw exceptions later.)
  * 
  * @param pixel the pixel whose color is returned
  * @param colorNum the color to choose: 1 = red, 2 = green, 3 = blue
  * @return the color value corresponding to colorNum in pixel
  */
 public static int getColor(Pixel pixel, int colorNum) {
   if(colorNum == 1)
     return pixel.getRed();
   else if (colorNum == 2)
     return pixel.getGreen();
   else
     return pixel.getBlue();
 }
 
   /**
  * Sets the value of the color corresponding to colorNum to newValue.
  * In an ideal world this would be added to the Pixel class
  * Precondition - colorNum is 1, 2, or 3.  (We will learn to throw exceptions later.)
  * 
  * @param pixel the pixel whose color is set
  * @param newValue the new value for the color
  * @param colorNum the color to choose: 1 = red, 2 = green, 3 = blue
  */
 public static void setColor(Pixel pixel, int newValue, int colorNum) {
   if(colorNum == 1)
     pixel.setRed(newValue);
   else if (colorNum == 2)
     pixel.setGreen(newValue);
   else
     pixel.setBlue(newValue);
 }
 
 /**
  * Averages the chosen color for all the pixels in an ArrayList.
  * Returns 0 if ArrayList is empty.
  * Precondition - colorNum is 1, 2, or 3.  (We will learn to throw exceptions later.)
  * 
  * @param pixels the list of pixels to be averaged
  * @param colorNum the color to average: 1 = red, 2 = green, 3 = blue
  * @return the average of the chosen color value
  */
 public static float averageColors(ArrayList<Pixel> pixels, int colorNum) {
   float sum = 0.0f;
   
   for(int i = 0; i < pixels.size(); i++)
     sum += getColor(pixels.get(i), colorNum);
   
   if(pixels.size() > 0)
     return sum/pixels.size();
   else
     return 0.0f;
 }
 
 
  
 /* 
  * End of additional methods added by Scot Drysdale.
  */
 
 
  public static void main(String[] args)
  {
    Picture p = 
      new Picture(FileChooser.pickAFile());
      p.explore();
      
/*      double ninth = 1.0/9.0;
      double [][] blur = {{ninth, ninth, ninth}, 
                         {ninth, ninth, ninth}, 
                         {ninth, ninth, ninth}};
      
      double [][] edges = {{-1.0, -1.0, -1.0},
                           {-1.0, 8.0, -1.0},
                           {-1.0, -1.0, -1.0}};

      double [][] sharpen = {{-1.0, -1.0, -1.0},
                             {-1.0, 9.0, -1.0},
                             {-1.0, -1.0, -1.0}};      
      
      Picture q = p.convolve(edges,"edges");
      Picture q2 = p.convolve(blur,"blur");
      Picture q3 = p.convolve(sharpen,"sharpen");

      q.explore();
      q2.explore();
      q3.explore();*/
      
      /*ArrayList<Color> colors = new ArrayList<Color>();
      int numColors = 256;
      colors = p.generateRandomColorList(numColors);*/
      
      /*colors.add(Color.red);
      colors.add(Color.green);
      colors.add(Color.blue);
      colors.add(Color.cyan);
      colors.add(Color.orange);
      colors.add(Color.yellow);
      colors.add(Color.black);
      colors.add(Color.white);      */
      
      // Picture q = p.mapToColorList(colors);
      
      Picture q = p.reduceColors(256);
      q.explore(); 
  }
        
} // this } is the end of class Picture, put all new methods before this


