import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Planet.db = new DB();
        System.out.println("Welcome to the Dragon ball encyclopedia! \n");

        boolean shouldContinue = true;
        Scanner sc = new Scanner(System.in);

        Planet pl1 = new Planet("The Earth", true, 796);
        Planet pl2 = new Planet("Planet Vegeta", false, 737);
        Planet pl3 = new Planet("Namek", true, 796);

        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(pl1);
        planets.add(pl2);
        planets.add(pl3);


        while (shouldContinue) {
            menu();
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 0:
                    System.out.println("Thank you for using our application!");
                    shouldContinue = false;
                    break;
                case 1:
                    System.out.println("Printing the planets");
                    Planet.printPlanets();
                    break;
                case 2:
                    System.out.println("Type in the planet name you would like to add to the list." +
                            "If planet still exists type 'true', otherwise 'false'. Then type in the age of the planet");
                    Planet.addPlanet(sc);
                    break;
                case 3:
                    System.out.println("Choose the number of the planet you would like to edit");
                    Planet.editPlanets(sc, planets);
                    break;
                case 4:
                    System.out.println("Choose the number of the planet you would like to delete from the list");
                    Planet.removePlanet(sc);
                    break;
                default:
                    System.out.println("Did you really choose the correct number?");
            }
        }
    }

    public static void menu() {
        System.out.println("If you want to see a list of the plants, press 1.");
        System.out.println("If you want to add a new planet, press 2.");
        System.out.println("If you want to edit the planet, press 3.");
        System.out.println("If you want to delete the planet, press 4.");
        System.out.println("If you want to quit the application, press 0.");
    }





}