package fr.epita.arun.services;

import fr.epita.arun.dataModel.Insurance;

import java.sql.*;
import java.util.List;

public class InsuranceDAO {

    private static final String DATABASE_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public void create(List<Insurance> insurances) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (Insurance insurance : insurances) {
                String insertQuery = "INSERT INTO INSURANCES (insurance_id, insurance_name) " +
                        "VALUES (?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, insurance.getInsuranceId());
                    preparedStatement.setString(2, insurance.getInsuranceName());

                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Insurances inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Insurance search(int insuranceId) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT * FROM INSURANCES WHERE insurance_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, insuranceId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractInsurance(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Insurance insurance) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String updateQuery = "UPDATE INSURANCES SET insurance_name = ? WHERE insurance_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, insurance.getInsuranceName());
                preparedStatement.setInt(2, insurance.getInsuranceId());

                preparedStatement.executeUpdate();
            }

            System.out.println("Insurance updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int insuranceId) {
        createTable();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String deleteQuery = "DELETE FROM INSURANCES WHERE insurance_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, insuranceId);

                preparedStatement.executeUpdate();
            }

            System.out.println("Insurance deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Insurance extractInsurance(ResultSet resultSet) throws SQLException {
        int insuranceId = resultSet.getInt("insurance_id");
        String insuranceName = resultSet.getString("insurance_name");

        return new Insurance(insuranceId, insuranceName);
    }

    private void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            Statement statement = connection.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS INSURANCES (" +
                    "insurance_id INT PRIMARY KEY," +
                    "insurance_name VARCHAR(255) NOT NULL" +
                    ")";

            statement.executeUpdate(createTableQuery);
            System.out.println("Table INSURANCES created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
