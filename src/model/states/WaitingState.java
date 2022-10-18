package model.states;

import model.SingleElevator;

public class WaitingState extends State{
    private int waitingTime = 0;
    public WaitingState(SingleElevator singleElevator) {
        super(singleElevator);
    }

    @Override
    public void updateDirection() {
        singleElevator.setDirection(singleElevator.getDirection());
    }

    @Override
    public void moveElevator() {
        singleElevator.setElevatorWaiting(true);
        singleElevator.notifyObserversThatElevatorWaiting();

        if(waitingTime < 2){
            waitingTime++;
        }else {
            singleElevator.setState(singleElevator.getStandingState());
            waitingTime = 0;
        }
    }
}
