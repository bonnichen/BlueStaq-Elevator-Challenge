import model.Simulation;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Simulation simulation = new Simulation(10);
        simulation.setUpFloor();
        simulation.createPassengers(0,9);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,8);
        simulation.createPassengers(0,3);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,6);
        simulation.createPassengers(0,7);
        simulation.setUpPassenger();
        simulation.setUpElevator();
        simulation.runElevator();


    }
}