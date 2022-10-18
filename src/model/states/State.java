package model.states;

import model.SingleElevator;

import java.util.Deque;

public abstract class State {
    SingleElevator singleElevator;
    Deque<Integer> elevatorDestinationFloors;

    State(SingleElevator singleElevator){
        this.singleElevator = singleElevator;
        this.elevatorDestinationFloors = singleElevator.getElevatorDestinationFloors();
    }

    public abstract void updateDirection();

    public abstract void moveElevator();

}
