package view;

import controller.ElevatorController;
import model.ElevatorsSystem;
import model.SingleElevator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ElevatorsPanel extends JPanel implements ActionListener {
    private final int numberOfElevators;
    private final int buildingHeight;
    private final int screenWidth = 850;
    private final int screenHeight = 800;
    private final JFrame window = new JFrame();
    private final ElevatorController elevatorController;
    private final ElevatorsSystem elevatorsSystem;
    private final ArrayList<SingleElevatorPanel> elevatorPanelsList = new ArrayList<>();
    private final ArrayList<SingleElevator> elevatorsList;
    private JButton callTheElevatorButton;
    private JComboBox<Integer> floorsListComboBox;
    private JPanel controlPanel;
    private JLabel chooseFloorLabel;

    public ElevatorsPanel(ElevatorController elevatorController, ElevatorsSystem elevatorsSystem){
        this.numberOfElevators = elevatorsSystem.getNumberOfElevators();
        this.elevatorController = elevatorController;
        this.elevatorsSystem = elevatorsSystem;
        this.elevatorsList = elevatorsSystem.getElevatorsList();
        this.buildingHeight = elevatorsSystem.getBuildingHeight();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        addElevators();
        makeControlPanel();
        makeListOfFloors();
    }

    public void addElevators(){
        for(int i = 0; i < numberOfElevators; i++){
            elevatorPanelsList.add(new SingleElevatorPanel(i, elevatorsList.get(i), this));
            this.add(elevatorPanelsList.get(i));
        }
    }

    public void makeControlPanel(){
        controlPanel = new JPanel();
        chooseFloorLabel = new JLabel("What floor are you on?");
        floorsListComboBox = new JComboBox<>();
        callTheElevatorButton = new JButton("Take the elevator");
        callTheElevatorButton.addActionListener(this);
        controlPanel.add(chooseFloorLabel);
        controlPanel.add(floorsListComboBox);
        controlPanel.add(callTheElevatorButton);
        this.add(controlPanel);
    }

    public void makeListOfFloors(){
        for(int i = 0; i <= buildingHeight; i++){
            floorsListComboBox.addItem(i);
        }
    }

    public void callTheElevator(){
        int selectedFloor = floorsListComboBox.getItemAt(floorsListComboBox.getSelectedIndex());
        elevatorController.pickup(selectedFloor);
    }

    public void setSelectedFloor(int elevatorID, int selectedFloor){
        elevatorController.addSelectedFloor(elevatorID, selectedFloor);
    }

    public int getBuildingHeight() {
        return buildingHeight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == callTheElevatorButton){
            callTheElevator();
        }
    }
}
