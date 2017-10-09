package Lecture5;
public class PurposefulWanderer extends Blob {

	int stepsBetweenChanges = 0;
	int stepsSinceLastChange = 0;

	public PurposefulWanderer(double x, double y) {
		super(x, y);
		stepsBetweenChanges = (int)(Math.random() * 20 + 10);
	}
	
	public PurposefulWanderer(double x, double y, double r) {
		super(x, y, r);
		stepsBetweenChanges = (int)(Math.random() * 20 + 10);
	}
	
	@Override
	public void step() {
		x += dx;
		y += dy;
		stepsSinceLastChange++;
		
		if(stepsSinceLastChange == stepsBetweenChanges) {
			dx = 2 * (Math.random()-0.5);
			dy = 2 * (Math.random()-0.5);
			x += dx;
			y += dy;
			stepsSinceLastChange = 0;
		}
	}
}