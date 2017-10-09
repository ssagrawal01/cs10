package Lecture5;

public class ConvolveDriver {
  public static void main(String[] args) {
  	 double ninth = 1.0/9.0; 
  	 double [][] blur = {{ninth, ninth, ninth}, {ninth, ninth, ninth}, {ninth, ninth, ninth}}; 
  	 double [][] edges = {{-1.0, -1.0, -1.0}, {-1.0, 8.0, -1.0}, {-1.0, -1.0, -1.0}}; 
  	 double [][] sharpen = {{-1.0, -1.0, -1.0}, {-1.0, 9.0, -1.0}, {-1.0, -1.0, -1.0}};
  	 double [][] xtracredit1 = {{-1.0, 0.0, 1.0}, {-1.0, 0.0, 1.0}, {-1.0, 0.0, 1.0}};
  	 double [][] xtracredit2 = {{0.0, -1.0, 0.0}, {-1.0, 4.0, -1.0},{0.0,-1.0,0.0}};
  	 double [][] xtracredit3 = {{-6.0, -6.0, 0.0}, {-6.0, 0.0, 6.0}, {0.0, 6.0, 6.0}};
  	 
  	 FileChooser.pickMediaPath();
  	 Picture blurredpic = new Picture(FileChooser.getMediaPath("beach.jpg")); 
  	 blurredpic.convolve(blur,"blurbeach").explore();
  	 
  	 Picture edgepic = new Picture(FileChooser.getMediaPath("beach.jpg")); 
  	 edgepic.convolve(edges, "edgebeach").explore();
  	 
  	 Picture sharppic = new Picture(FileChooser.getMediaPath("beach.jpg")); 
  	 sharppic.convolve(sharpen,  "sharpbeach").explore();
  	 
  	 Picture xtracredit1pic = new Picture(FileChooser.getMediaPath("beach.jpg"));
  	 xtracredit1pic.convolve(xtracredit1, "xtracredit1pic").explore();
  	 
  	 Picture xtracredit2pic = new Picture(FileChooser.getMediaPath("beach.jpg"));
  	 xtracredit2pic.convolve(xtracredit2, "xtracredit2pic").explore();
  	 
  	 Picture xtracredit3pic = new Picture(FileChooser.getMediaPath("beach.jpg"));
  	 xtracredit3pic.convolve(xtracredit3,"xtracredit3pic").explore();
  	 
  	 Picture normal = new Picture(FileChooser.getMediaPath("beach.jpg")); 
  	 normal.setTitle("normal beach");
  	 normal.explore();
  }
}
