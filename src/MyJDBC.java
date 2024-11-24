import java.sql.*;

public class MyJDBC {
    final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    final String USER = "root";
    final String PASS = "password";

    // Function to execute SQL queries
    private Connection connectToDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }

    // Function to return a formatted table from SQL query result
    public String returnTable(String sqlString) throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlString);
        StringBuilder table = new StringBuilder();

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] columnNames = new String[columnCount];

        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }

        table.append("\nTable of " + rsmd.getTableName(1) + "\n");
        for (String columnName : columnNames) {
            table.append(String.format("%-20s", columnName));
        }
        table.append("\n");
        
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                table.append(String.format("%-20s", resultSet.getString(i)));
            }
            table.append("\n");
        }
        
        resultSet.close();
        statement.close();
        return table.toString();
        
    }

    // Function to execute SQL queries (update, insert, delete)
    public void alterDatabase(String sqlString) throws SQLException {
        Statement statement = connectToDatabase().createStatement();
        statement.executeUpdate(sqlString);
    }
    
    // Function to show a table
    public void showTable(String tableName) throws SQLException {
        String sqlString = "SELECT * FROM " + tableName;
        String table = returnTable(sqlString);
        System.out.println(table);
    }

    /* 
    public static void main(String[] args) throws Exception {
        MyJDBC myjdbc = new MyJDBC();
        myjdbc.showTable("supplier");
    }
    */
}