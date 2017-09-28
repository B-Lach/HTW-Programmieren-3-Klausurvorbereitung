
public class UpdatingHoursState implements ClockState {

	@Override
	public void modeButtonAction(Clock c) {
		c.changeHour();
		
	}

	@Override
	public void changeButtonAction(Clock c) {
	 	System.out.println("** UPDATING MIN: Press CHANGE button to increase minutes.");
	 	c.setState(new UpdatingMinutesState());		
	}

}
