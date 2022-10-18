package model;

public interface ElevatorsSystemInterface {
    void pickup(int elevatorCalledFloor);
    void addSelectedFloor(int elevatorID, int elevatorDestinationFloor);
    int findElevator(int elevatorCalledFloor);
}
