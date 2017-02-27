package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Date;

import com.google.common.collect.*;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	private Table<Date, Integer, Integer> table = HashBasedTable.create();
	
	@Override
	public void followSpeed() {
		
		if (referenceSpeed + step < 0) {
			referenceSpeed = 0;
		} else {
			referenceSpeed += step;
		}

		enforceSpeedLimit();
		
		table.put(new Date(), step , referenceSpeed);
		
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	@Override
	public int getTableSize(){
		return table.size();
	}
	
}
