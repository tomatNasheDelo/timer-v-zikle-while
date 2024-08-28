import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class Mult2 {
    

    public static void main(String[] args) throws InterruptedException {

          ReentrantLock locker = new ReentrantLock(); // создаем заглушку

        String url = "jdbc:mysql://localhost:3306/multt";
     String user = "root";
     String password = "Mypassword";



        Thread write1 = new Thread(new String11("Pishet", user, url, password,locker));
        Thread write2 = new Thread(new String22("Pishet2", user, url, password,locker));

     //   Thread.sleep(1000);
        Thread read = new Thread(new  ReadString ("Chitaet", user, url, password,locker));
        //Thread read = new Thread(new ReadString2("Chitaet", user, url, password));
       // read.start();

        write1.start();
        write2.start();
        read.start();

       
    }
}


class String11 implements Runnable {

    String name;
    Connection con;
    Statement stmt;
    ResultSet rs;
    ReentrantLock locker;

    String url ;
    String user ;
    String password;

    String11(String name, String user, String url, String password, ReentrantLock lock ){

        this.name = name;
        this.user = user;
        this.password = password;
        this.url = url;
        this.locker = lock;
    }



      public void run(){

        try {
            con = DriverManager.getConnection(url, user, password);

        //   stmt= con.createStatement();

        for(int i=1; i<6; i++){

           stmt = con.createStatement();
            RandString34 rs2 = new RandString34();
            String letter2 = rs2.usingUUID();
    
          
           String query3 = "INSERT INTO multt.mult1 \n"  + " VALUES ("+i+","+ "'"+letter2+"'"+");";
          // System.out.println(query3);
          stmt.executeUpdate(query3);
                
    }
}  catch(SQLException e){
    e.printStackTrace();
} 
finally {
    // close connection ,stmt and resultset here
    try {
        con.close();
    } catch (SQLException se) {
        /* can't do anything */ }
    try {
        stmt.close();
    } catch (SQLException se) {
        /* can't do anything */ }

        locker.unlock(); // снимаем блокировку

       

    // try {
    //     rs.close();
    // } catch (SQLException se) {
    //     /* can't do anything */ }


}



      }

}

class String22 implements Runnable {

    String name;
    Connection con;
    Statement stmt;
    ResultSet rs;
    ReentrantLock locker;

    String url ;
    String user ;
    String password;

    String22(String name, String user, String url, String password ,ReentrantLock lock){

        this.name = name;
        this.user = user;
        this.password = password;
        this.url = url;
        this.locker = lock;
    }



      public void run(){

        try {
            con = DriverManager.getConnection(url, user, password);

        //   stmt= con.createStatement();

        for(int i=6; i<11; i++){

           stmt = con.createStatement();
            RandString34 rs2 = new RandString34();
            String letter2 = rs2.usingUUID();
    
          
           String query3 = "INSERT INTO multt.mult1 \n"  + " VALUES ("+i+","+ "'"+letter2+"'"+");";
          // System.out.println(query3);
          stmt.executeUpdate(query3);
                
    }
}  catch(SQLException e){
    e.printStackTrace();
} 
finally {
    // close connection ,stmt and resultset here
    try {
        con.close();
    } catch (SQLException se) {
        /* can't do anything */ }
    try {
        stmt.close();
    } catch (SQLException se) {
        /* can't do anything */ }
        locker.unlock(); // снимаем блокировку
       
    // try {
    //     rs.close();
    // } catch (SQLException se) {
    //     /* can't do anything */ }


}



      }

}

class ReadString  implements Runnable{


    String name;
    Connection con;
    Statement stmt;
    ResultSet rs;
    ReentrantLock locker;


    String url ;
    String user ;
    String password;


    ReadString(String name, String user, String url, String password, ReentrantLock lock ){

        this.name = name;
        this.user = user;
        this.password = password;
        this.url = url;
        this.locker=lock;
    }

    public void run(){


        try{

            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery("SELECT * FROM mult1");
            // Process the results
            while (rs.next()) {

                int id = rs.getInt("id");
                String letter = rs.getString("letter");
                //String author = resultSet2.getString("author");
                System.out.println("ID: " + id + ", Letter: " + letter );
            }





        }  catch(SQLException sqlEx){

        } finally {
            // close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) {
                /* can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) {
                /* can't do anything */ }
            // try {
            //     rs.close();
            // } catch (SQLException se) {
                /* can't do anything */ }
                locker.unlock(); // снимаем блокировку
               
        }



      

          
        
    }





class RandString34 {


    String usingUUID(){

        UUID randomU = UUID.randomUUID();
        return randomU.toString().replaceAll("-","").substring(0, 8);
    }

    String newusingUUID(){

        return usingUUID();
    }
}