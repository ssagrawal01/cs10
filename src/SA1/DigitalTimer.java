package SA1;
public class DigitalTimer {
		private Counter timerHours;
		private Counter timerMinutes;
	

/**
	 * Creates a timer initialized to 00:00
	 */

	// Constructors

	public DigitalTimer() {
		this.timerHours = new Counter(24);
		this.timerMinutes = new Counter(60);
		}
		
	
	/**
	 * Creates a timer initialized to hours:minutes
	 * Precondition: 0 <= hours <= 23 and 0 <= minutes <= 59
	 *  
	 * @param hours value for hours part of timer
	 * @param minutes value of minutes part of timer
	 */
	public DigitalTimer(int hours, int minutes) {
		this.timerHours = new Counter(24);
		this.timerMinutes = new Counter(60);
		timerHours.set(hours);
		timerMinutes.set(minutes);
	}
		
	/**
	 * @return the time in hours:minutes format
	 */
	public String toString() {
		String timeHH = timerHours.toString(); 
		String timeMM = timerMinutes.toString();
		return timeHH+":"+timeMM;
	}
	
	
	/**
	 * Increases the time by a minute, wrapping if necessary
	 */
	public void tick() {
		timerMinutes.tick();
		if(timerMinutes.getValue() == 0) {
			timerHours.tick();	
		}
	}
	
	
	/**
	 * Sets the current time to hours:minutes.
	 * Precondition: 0 <= hours <= 23 and 0 <= minutes <= 59
	 * 
	 * @param hours value for hours part of timer 
	 * @param minutes value for minutes part of timer
	 */
	public void set(int hours, int minutes) {
		timerHours.set(hours);
		timerMinutes.set(minutes);
	}
	
}
	
	
	