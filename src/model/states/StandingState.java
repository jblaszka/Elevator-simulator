package model.states;

import model.SingleElevator;

public class StandingState extends State{
    public StandingState(SingleElevator singleElevator) {
        super(singleElevator);
    }

    @Override
    public void updateDirection() {
        singleElevator.setDirection(singleElevator.getDirection());
    }

    @Override
    public void moveElevator() {
        int currentFloor = singleElevator.getCurrentFloor();
        if(!elevatorDestinationFloors.isEmpty()) {
            if (currentFloor < elevatorDestinationFloors.getFirst()) {
                singleElevator.setState(singleElevator.getGoesUpState());
                singleElevator.setElevatorWaiting(false);
                singleElevator.notifyObserversThatElevatorWaiting();
            }else if(currentFloor > elevatorDestinationFloors.getFirst()){
                singleElevator.setElevatorWaiting(false);
                singleElevator.notifyObserversThatElevatorWaiting();
                singleElevator.setState(singleElevator.getGoesDownState());
            }else {
                elevatorDestinationFloors.removeFirst();
                singleElevator.setState(singleElevator.getWaitingState());
            }
        }
    }
}
