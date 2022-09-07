import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Planet {
    private String planetName;
    private Boolean isStillThere;
    private Integer planetAge;
    private Integer id;
    public static DB db;

    public Planet(String planetName, Boolean isStillThere, Integer planetAge){
        this.planetName = planetName;
        this.isStillThere = isStillThere;
        this.planetAge = planetAge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanetName() {
        return this.planetName;
    }

    public void setPlanetName(String nameOfPlanet) {
        this.planetName = nameOfPlanet;
    }

    public Boolean getStillThere() {
        return this.isStillThere;
    }

    public void setStillThere(Boolean doesExist) {
        this.isStillThere = doesExist;
    }

    public Integer getPlanetAge() {
        return this.planetAge;
    }

    public void setPlanetAge(Integer ageOfPlanet) {
        this.planetAge = ageOfPlanet;
    }


    @Override
    public String toString() {
        return "Planet name is " + planetName + "." + " Planet " + ((isStillThere) ? "exists" : "does not exist")
                + "." + " Planet age is/was " + planetAge;
    }

    public static void printPlanets(){
        Statement stmt;
        try {
            stmt = Planet.db.connection.createStatement();

            String query = "Select * from `planets`";
            ResultSet rs = stmt.executeQuery(query);
            int count = 0;
            while(rs.next()){
                System.out.println( ++count + " " +
                        rs.getInt("id") + " " +
                        rs.getString("planet_name") + " " +
                                rs.getBoolean("is_still_there") + " " +
                                rs.getInt("planet_age"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addPlanet(Scanner sc){
        System.out.println("planet name");
        String planetName = sc.nextLine();
        System.out.println("still valid (true/false)");
        boolean doesExist = sc.nextBoolean();
        System.out.println("age?");
        int age = sc.nextInt();
        sc.nextLine();

        Planet pl = new Planet(planetName, doesExist, age);

        try {
            PreparedStatement stmt =
                    db.connection.prepareStatement("INSERT INTO `planets`" +
                            "(`planet_name`, `is_still_there`, `planet_age`) VALUES " +
                            "(?,?,?)");
            stmt.setString(1,pl.getPlanetName());
            stmt.setBoolean(2,pl.getStillThere());
            stmt.setInt(3,pl.getPlanetAge());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("");
    }

    public static void editPlanets(Scanner sc, ArrayList<Planet> arr){
        Planet.printPlanets();

        int nr = sc.nextInt();
        sc.nextLine();
//        System.out.println("You will edit the planet called - " + arr.get(nr - 1) + "." + "Type in new planet name " +
//                ", if planet exists type in 'true', otherwise 'false' and age of the planet");


        System.out.println("planet name");
        String planetName = sc.nextLine();
        System.out.println("still valid (true/false)");
        boolean doesExist = sc.nextBoolean();
        System.out.println("age?");
        int age = sc.nextInt();
        sc.nextLine();

        Planet pl = new Planet(planetName, doesExist, age);

        try {
            PreparedStatement stmt =
                    db.connection.prepareStatement("UPDATE `planets` SET " +
                            "`planet_name`=?," +
                            "`is_still_there`=?," +
                            "`planet_age`=? WHERE id = ?");
            stmt.setString(1,pl.getPlanetName());
            stmt.setBoolean(2,pl.getStillThere());
            stmt.setInt(3,pl.getPlanetAge());
            stmt.setInt(4,nr);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("");
    }

    public static void removePlanet(Scanner sc) {
        Planet.printPlanets();
        int itemToDelete = sc.nextInt();
        sc.nextLine();
        try {
            PreparedStatement stmt =
                    db.connection.prepareStatement("DELETE FROM `planets` WHERE id = ?");
            stmt.setInt(1,itemToDelete);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("");
    }
}