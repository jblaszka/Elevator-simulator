package model;

import model.states.*;
import view.ElevatorObserver;

import java.util.*;

public class SingleElevator {
    private final int elevatorID;
    private final State waitingState;
    private final State goesUpState;
    private final State goesDownState;
    private final State standingState;
    private final Deque<Integer> elevatorDestinationFloors = new ArrayDeque<>();
    private final ArrayList<ElevatorObserver> elevatorObservers;

    private State state;
    private String direction;
    private int currentFloor;
    private boolean elevatorWaiting = true;

    public SingleElevator(int elevatorID) {
        this.elevatorID = elevatorID;
        this.waitingState = new WaitingState(this);
        this.goesDownState = new GoesDownState(this);
        this.goesUpState = new GoesUpState(this);
        this.standingState = new StandingState(this);
        this.elevatorObservers = new ArrayList<>();
        this.currentFloor = 0;
        this.direction = "up";
        state = standingState;
    }

    public void moveElevator(){
        state.moveElevator();
    }

    public void setCurrentFloor(int floor){
        this.currentFloor = floor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setElevatorWaiting(boolean elevatorWaiting){
        this.elevatorWaiting = elevatorWaiting;
    }

    public boolean isElevatorWaiting(){
        return this.elevatorWaiting;
    }

    public void addToElevatorDestinationFloorsAndSort(int floor){
        elevatorDestinationFloors.add(floor);
        sortFloors();
    }

    public Deque<Integer> getElevatorDestinationFloors() {
        return elevatorDestinationFloors;
    }

    public int getSizeElevatorDestinationFloors(){
        return elevatorDestinationFloors.size();
    }

    public void addElevatorObserver(ElevatorObserver elevatorObserver) {
        elevatorObservers.add(elevatorObserver);
    }

    public void notifyObserversAboutFloor(){
        for (ElevatorObserver elevatorObserver : elevatorObservers) {
            elevatorObserver.updateElevatorPosition();
        }
    }

    public void notifyObserversThatElevatorWaiting(){
        for (ElevatorObserver elevatorObserver : elevatorObservers) {
            elevatorObserver.canYouGetInElevator();
        }
    }

    public void setDirection(String newDirection){
        direction = newDirection;
    }

    public String getDirection(){
        return direction;
    }

    public void sortFloors(){
        state.updateDirection();
        int currentTrack;
        Deque<Integer> elevatorDestinationFloors_temp = new ArrayDeque<>();
        Iterator<Integer> it = elevatorDestinationFloors.iterator();

        Vector<Integer> up = new Vector<>();
        Vector<Integer> down = new Vector<>();
        Vector<Integer> seekSequence = new Vector<>();

        while (it.hasNext()) {
            int temp = it.next();
            if (temp > currentFloor) {
                up.add(temp);
            } else if (temp <= currentFloor) {
                down.add(temp);
            }
        }
        Collections.sort(up);
        Collections.sort(down);

        int run = 2;
        while (run-- >0) {
            if (direction.equals("up")) {
                for (int i = 0; i < up.size(); i++) {
                    currentTrack = up.get(i);
                    seekSequence.add(currentTrack);
                    elevatorDestinationFloors_temp.add(currentTrack);
                }
                direction = "down";
            } else if(direction.equals("down")){
                for (int i = down.size() - 1; i >= 0; i--) {
                    currentTrack = down.get(i);
                    seekSequence.add(currentTrack);
                    elevatorDestinationFloors_temp.add(currentTrack);
                }
                direction = "up";
            }
        }
        elevatorDestinationFloors.clear();
        elevatorDestinationFloors.addAll(elevatorDestinationFloors_temp);
    }

     public void setState(State state){
        this.state = state;
    }

        public State getWaitingState() {
            return waitingState;
        }

        public State getGoesUpState() {
            return goesUpState;
        }

        public State getGoesDownState() {
            return goesDownState;
        }

        public State getStandingState() {
            return standingState;
        }
}