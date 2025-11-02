import model.Simulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ElevatorTests {
    @Test
    void testUP() {
        Simulation simulation = new Simulation(10);
        simulation.setUpFloor();
        simulation.createPassengers(0,2);
        simulation.createPassengers(0,1);
        simulation.createPassengers(3,6);
        simulation.createPassengers(6,7);
        simulation.createPassengers(1,7);
        simulation.createPassengers(1,9);
        simulation.createPassengers(0,1);
        simulation.setUpPassenger();
        simulation.setUpElevator();
        simulation.runElevator();
        Assertions.assertEquals(0, simulation.getTotalPassenger());
    }
    @Test
    void testMaxLimit() {
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
        Assertions.assertEquals(0, simulation.getTotalPassenger());
    }

    @Test
    void testUpDown() {
        Simulation simulation = new Simulation(5);
        simulation.setUpFloor();
        simulation.createPassengers(0,2);
        simulation.createPassengers(2,3);
        simulation.createPassengers(4,0);
        simulation.setUpPassenger();
        simulation.setUpElevator();
        simulation.runElevator();
        Assertions.assertEquals(0, simulation.getTotalPassenger());
    }

    @Test
    void testUpDown2() {
        Simulation simulation = new Simulation(10);
        simulation.setUpFloor();
        simulation.createPassengers(0,9);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,8);
        simulation.createPassengers(0,3);
        simulation.createPassengers(6,3);
        simulation.createPassengers(6,2);
        simulation.createPassengers(9,7);
        simulation.createPassengers(0,9);
        simulation.createPassengers(0,9);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,8);
        simulation.createPassengers(0,3);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,6);
        simulation.createPassengers(0,7);
        simulation.createPassengers(0,9);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,8);
        simulation.createPassengers(6,9);
        simulation.createPassengers(6,3);
        simulation.createPassengers(6,2);
        simulation.createPassengers(9,7);
        simulation.createPassengers(3,2);
        simulation.createPassengers(1,6);
        simulation.createPassengers(6,1);
        simulation.createPassengers(6,9);
        simulation.createPassengers(0,3);
        simulation.createPassengers(0,1);
        simulation.createPassengers(0,6);
        simulation.createPassengers(0,7);
        simulation.setUpPassenger();
        simulation.setUpElevator();
        simulation.runElevator();
        Assertions.assertEquals(0, simulation.getTotalPassenger());
    }

    @Test
    void testUpDown3() {
        Simulation simulation = new Simulation(10);
        simulation.setUpFloor();
        simulation.createPassengers(1,5);
        simulation.createPassengers(6,7);
        simulation.createPassengers(7,4);
        simulation.createPassengers(4,3);
        simulation.createPassengers(3,6);
        simulation.createPassengers(6,7);
        simulation.createPassengers(7,1);
        simulation.setUpPassenger();
        simulation.setUpElevator();
        simulation.runElevator();
        Assertions.assertEquals(0, simulation.getTotalPassenger());
    }
}
