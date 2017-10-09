package Lecture5;
public class WanderingPulsar2 extends Pulsar {
	public WanderingPulsar2 (double x, double y, double r) {
		super (x, y);
		this.r = r;
	}
	
	public WanderingPulsar2 (double x, double y) {
		super (x, y);
	}
		
	@Override
	public void step() {
			
		super.step();

		// Choose a new step between -1 and +1 in each of x and y

		dx = 2 * (Math.random()-0.5);
		dy = 2 * (Math.random()-0.5);
		x += dx;
		y += dy;
		
	}
}

