package model.states;

import model.SingleElevator;

public class GoesUpState extends State{
    public GoesUpState(SingleElevator singleElevator) {
        super(singleElevator);
    }

    @Override
    public void updateDirection() {
        singleElevator.setDirection("up");
    }

    @Override
    public void moveElevator() {
        int currentFloor = singleElevator.getCurrentFloor();
        if(!elevatorDestinationFloors.isEmpty()) {
            if (currentFloor < elevatorDestinationFloors.getFirst()) {
                singleElevator.setCurrentFloor(currentFloor + 1);
                singleElevator.notifyObserversAboutFloor();
            }else if(currentFloor > elevatorDestinationFloors.getFirst()){
                singleElevator.setState(singleElevator.getGoesDownState());
            }else{
                elevatorDestinationFloors.removeFirst();
                singleElevator.setState(singleElevator.getWaitingState());
            }
        }else{
            singleElevator.setState(singleElevator.getStandingState());
        }
    }
}
