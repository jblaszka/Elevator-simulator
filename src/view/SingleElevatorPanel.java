package view;

import model.SingleElevator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleElevatorPanel extends JPanel implements ElevatorObserver, ActionListener {
    private final ElevatorsPanel elevatorsPanel;
    private final SingleElevator singleElevator;
    private final int elevatorID;
    private final int buildingHeight;
    private JLabel elevatorPositionLabel;
    private JButton selectFloorButton;
    private JComboBox<Integer> floorsListComboBox;

    public SingleElevatorPanel(int elevatorID, SingleElevator singleElevator, ElevatorsPanel elevatorsPanel){
        this.elevatorID = elevatorID;
        this.singleElevator = singleElevator;
        this.elevatorsPanel = elevatorsPanel;
        this.buildingHeight = elevatorsPanel.getBuildingHeight();
        singleElevator.addElevatorObserver(this);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBackground(Color.gray);
        this.setBorder(blackLine);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(175, 175));

        addControlPanel();
        makeListOfFloors();
    }

    public void addControlPanel(){
        elevatorPositionLabel = new JLabel("0", SwingConstants.CENTER);
        selectFloorButton = new JButton("Select a floor");
        floorsListComboBox = new JComboBox<>();

        selectFloorButton.addActionListener(this);
        selectFloorButton.setEnabled(false);
        floorsListComboBox.setEnabled(false);

        elevatorPositionLabel.setPreferredSize(new Dimension(120, 50));
        selectFloorButton.setPreferredSize(new Dimension(120, 35));
        floorsListComboBox.setPreferredSize(new Dimension(120, 35));

        elevatorPositionLabel.setOpaque(true);
        elevatorPositionLabel.setBackground(Color.pink);

        this.add(elevatorPositionLabel, JPanel.BOTTOM_ALIGNMENT);
        this.add(floorsListComboBox, JPanel.BOTTOM_ALIGNMENT);
        this.add(selectFloorButton, JPanel.BOTTOM_ALIGNMENT);
    }

    public void setWhichFloorIsElevator(int floor){
        elevatorPositionLabel.setText(String.valueOf(floor));
    }

    public void makeListOfFloors(){
        for(int i = 0; i <= buildingHeight; i++){
            floorsListComboBox.addItem(i);
        }
    }

    @Override
    public void updateElevatorPosition() {
        setWhichFloorIsElevator(singleElevator.getCurrentFloor());
    }

    @Override
    public void canYouGetInElevator() {
        floorsListComboBox.setEnabled(singleElevator.isElevatorWaiting());
        selectFloorButton.setEnabled(singleElevator.isElevatorWaiting());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedFloor = floorsListComboBox.getItemAt(floorsListComboBox.getSelectedIndex());
        elevatorsPanel.setSelectedFloor(elevatorID, selectedFloor);
    }
}
