package model.states;

import model.SingleElevator;

public class GoesDownState extends State {
    public GoesDownState(SingleElevator singleElevator) {
        super(singleElevator);
    }

    @Override
    public void updateDirection() {
        singleElevator.setDirection("down");
    }

    @Override
    public void moveElevator() {
        int currentFloor = singleElevator.getCurrentFloor();
        if(!elevatorDestinationFloors.isEmpty()) {
            if (currentFloor < elevatorDestinationFloors.getFirst()) {
                singleElevator.setState(singleElevator.getGoesUpState());
            }else if(currentFloor > elevatorDestinationFloors.getFirst()){
                singleElevator.setCurrentFloor(currentFloor - 1);
                singleElevator.notifyObserversAboutFloor();
            }else {
                elevatorDestinationFloors.removeFirst();
                singleElevator.setState(singleElevator.getWaitingState());
            }
        }else{
            singleElevator.setState(singleElevator.getStandingState());
        }
    }
}
