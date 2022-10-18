package model;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class ElevatorsSystem implements Runnable, ElevatorsSystemInterface{
    private static final int BUILDING_HEIGHT = 10;
    private final int numberOfElevators;
    private final ArrayList<SingleElevator> elevatorsList;
    private Thread elevatorThread;

    public ElevatorsSystem(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
        this.elevatorsList = new ArrayList<>();
        makeElevators();
        startElevatorThread();
    }

    public void makeElevators(){
        for(int i = 0; i < numberOfElevators; i ++){
            this.elevatorsList.add(new SingleElevator(i));
        }
    }

    public int findElevator(int elevatorCalledFloor){
        int minimum = 0;
        int selectedElevator = -1;
        int distance = BUILDING_HEIGHT;
        while(true) {
            for (int i = 0; i < numberOfElevators; i++) {
                if(elevatorsList.get(i).getSizeElevatorDestinationFloors() == minimum & abs(elevatorsList.get(i).getCurrentFloor() - elevatorCalledFloor) < distance){
                    distance = abs(elevatorsList.get(i).getCurrentFloor() - elevatorCalledFloor);
                    selectedElevator = i;
                }
            }
            if (selectedElevator != -1) {
                return selectedElevator;
            }
            minimum++;
        }
    }

    @Override
    public void pickup(int elevatorCalledFloor) {
        elevatorsList.get(findElevator(elevatorCalledFloor)).addToElevatorDestinationFloorsAndSort(elevatorCalledFloor);
    }

    @Override
    public void addSelectedFloor(int elevatorID, int elevatorDestinationFloor) {
        elevatorsList.get(elevatorID).addToElevatorDestinationFloorsAndSort(elevatorDestinationFloor);
    }

    public ArrayList<SingleElevator> getElevatorsList(){
        return elevatorsList;
    }

    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public int getBuildingHeight(){
        return BUILDING_HEIGHT;
    }
    public void startElevatorThread(){
        elevatorThread = new Thread(this);
        elevatorThread.start();
    }

    @Override
    public void run() {
        while (elevatorThread != null){
            update();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        for(int i = 0; i < numberOfElevators; i ++){
            elevatorsList.get(i).moveElevator();
        }
    }
}
