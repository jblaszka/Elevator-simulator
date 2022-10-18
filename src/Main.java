import controller.ElevatorController;
import model.ElevatorsSystem;

public class Main {
    public static void main(String[] args) {
        ElevatorsSystem elevatorsSystem = new ElevatorsSystem(16);
        ElevatorController elevatorController = new ElevatorController(elevatorsSystem);
    }
}
