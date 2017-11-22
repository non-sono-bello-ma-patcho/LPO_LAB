package timers;

public class TimerClass {
	private int time; // in seconds

	public TimerClass() { // initializes time with the default value 0
		this.time=0;
	}

	public boolean isRunning() {
		return this.time > 0;
	}

	public int getTime() {
		return this.time;
	}

	public void tick() {
		if (this.time > 0)
			this.time--;
	}

	public int reset(int minutes) {
		if (minutes < 0 || minutes > 60)
			throw new IllegalArgumentException();
		int prevTime = this.time;
		this.time = minutes * 60;
		return prevTime;
	}

	public int resetWith(TimerClass timer){
		int prevtime=this.time;
		this.time = timer.time;
		return prevtime;
	}
}
