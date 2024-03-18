import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;



public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/student_db";
    private static final String user = "postgres";
    private static final String password = "postgres";


    public static void main(String[] args) {
         String url = "jdbc:postgresql://localhost:5432/student_db";
         String user = "postgres";
         String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to databse");
                getAllStudents();
                //test add student
                addStudent("Jacky","Zhang","jackyzhang@cmail.carleton.ca","2018-09-01");
                updateStudentEmail(1,"testemail.com");
                deleteStudent(4);


            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void getAllStudents() {
        String SQL = "SELECT * FROM students";
        try (Connection connect = DriverManager.getConnection(url, user, password);
             Statement statement = connect.createStatement();
             ResultSet resultset = statement.executeQuery(SQL)) {
            System.out.println("Populating All Students:");
            while (resultset.next()) {
                System.out.println(resultset.getInt("student_id") + " "
                        + resultset.getString("first_name") + " "
                        + resultset.getString("last_name") + " "
                        + resultset.getString("email") + " "
                        + resultset.getDate("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (Connection connect = DriverManager.getConnection(url, user, password);
             PreparedStatement temp_state = connect.prepareStatement(SQL)) {
            temp_state.setString(1, first_name);
            temp_state.setString(2, last_name);
            temp_state.setString(3, email);
            temp_state.setDate(4, Date.valueOf(enrollment_date));
            temp_state.executeUpdate();
            System.out.println("Student successfully added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStudentEmail(int student_id, String new_email) {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try (Connection connect = DriverManager.getConnection(url, user, password);
             PreparedStatement temp_state = connect.prepareStatement(SQL)) {
            temp_state.setString(1, new_email);
            temp_state.setInt(2, student_id);
            temp_state.executeUpdate();
            System.out.println("Email successfully updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudent(int student_id) {
        String SQL = "DELETE FROM students WHERE student_id = ?";
        try (Connection connect = DriverManager.getConnection(url, user, password);
             PreparedStatement temp_state = connect.prepareStatement(SQL)) {
            temp_state .setInt(1, student_id);
            temp_state .executeUpdate();
            System.out.println("Student successfully delected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
