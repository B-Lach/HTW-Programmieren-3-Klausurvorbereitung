
public class NormalState implements ClockState {

	@Override
	public void modeButtonAction(Clock c) {
		c.displayTimeWithLight();
	}

	@Override
	public void changeButtonAction(Clock c) {
		System.out.println(" UPDATING HR: Press CHANGE button to increase hours.");
		c.setState(new UpdatingHoursState());	
	}

}
