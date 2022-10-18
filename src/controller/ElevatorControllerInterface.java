package controller;

public interface ElevatorControllerInterface{
    void pickup(int elevatorCalledFloor);
    void addSelectedFloor(int elevatorID, int floor);
}
