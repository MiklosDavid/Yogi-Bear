package yogibear;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    private Connection conn;
    private static final String CONN_STRING = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7274149";
    private static final String USERNAME = "sql7274149";
    private static final String PASSWORD = "SpKwn6zqis";
    
    public Database(){
        connect();
    }
    
    private void connect(){
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            connect();
        }
    }
    
    public void close(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void insert(DataStructure data){
        try {
            Statement stmt = (Statement)conn.createStatement();
            stmt.executeUpdate("INSERT INTO ranklist (name,difficulty,points) VALUES ('" + data.getName() + "', '" + data.getDifficulty() + "', '" + data.getPoints() + "')");
            try{
                stmt.close();
            }catch(SQLException ex2){
                System.out.println(ex2.getMessage());
            }
        } catch (SQLException ex) {
            insert(data);
        }
        
    }
    
    public ArrayList<DataStructure> query(){
        ArrayList<DataStructure> ranklist = new ArrayList<DataStructure>();
        try {
            Statement stmt = (Statement)conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name,difficulty,points FROM ranklist ORDER BY points DESC LIMIT 10");
            while(rs.next()){
                ranklist.add(new DataStructure(rs.getString("name"), rs.getString("difficulty"), rs.getInt("points")));
            }
            try{
                rs.close();
            }catch(SQLException ex2){
                System.out.println(ex2.getMessage());
            }
        } catch (SQLException ex) {
            ranklist = query();
        }
        return ranklist;
    }
    
    
}

class DataStructure{
    
    String name;
    String difficulty;
    int points;

    public DataStructure(String name, String difficulty, int points) {
        this.name = name;
        this.difficulty = difficulty;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
}
