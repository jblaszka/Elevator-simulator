package controller;

import model.ElevatorsSystem;
import view.ElevatorsPanel;

public class ElevatorController implements ElevatorControllerInterface{
    ElevatorsSystem elevatorsSystem;
    ElevatorsPanel elevatorsPanel;

    public ElevatorController(ElevatorsSystem elevatorsSystem){
        this.elevatorsSystem = elevatorsSystem;
        elevatorsPanel = new ElevatorsPanel(this, elevatorsSystem);
    }

    @Override
    public void pickup(int elevatorCalledFloor) {
        elevatorsSystem.pickup(elevatorCalledFloor);
    }

    @Override
    public void addSelectedFloor(int elevatorID, int floor) {
        elevatorsSystem.addSelectedFloor(elevatorID, floor);
    }
}
