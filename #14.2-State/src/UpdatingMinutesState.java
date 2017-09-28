
public class UpdatingMinutesState implements ClockState {

	@Override
	public void modeButtonAction(Clock c) {
		c.changeMinute();

	}

	@Override
	public void changeButtonAction(Clock c) {
		System.out.println("** Clock is in normal display.");
		c.setState(new NormalState());
	}

}
