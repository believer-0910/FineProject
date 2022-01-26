package database;

import com.google.gson.Gson;
import model.Fine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FineDatabase {
    public static String getFineByMonthAndCarNumber(String month, String carNumber){
        int i = 1;

        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_fines_monthly_by_car_number(i_month := ?, i_car_number := ?)");
            preparedStatement.setInt(1, Integer.parseInt(month));
            preparedStatement.setString(2, carNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            Gson gson = new Gson();
            StringBuilder str = new StringBuilder();
            str.append("Monthly fines");
            while(resultSet.next()){
                String string = resultSet.getString(1);
//                Fine fine = gson.fromJson(string, Fine.class);
                str.append(i++).append(string).append("\n");
            }

            return str.toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Not Found";
    }

    public static String getFineByCarNumber(String carNumber){
        int i = 1;

        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_fines_car_number(i_car_number := ?)");
            preparedStatement.setString(1, carNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
//            Gson gson = new Gson();
            StringBuilder str = new StringBuilder();
            str.append("Fines by car number\n");

            while(resultSet.next()){
                String string = resultSet.getString(1);
//                Fine fine = gson.fromJson(string, Fine.class);
//                str.append(i++).append(".Fine amount: ").append(fine.getAmount()).append(" || date: "+ fine.getPayDate()).append("\n");
                 str.append(i++).append(string).append("\n");
            }

            return str.toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Not Found";
    }

    public static String getFineByUserId (String carNumber){
        int i = 1;

        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_fines_by_user_id(i_u_id := ?)");
            preparedStatement.setInt(1, Integer.parseInt(carNumber));
            ResultSet resultSet = preparedStatement.executeQuery();
//            Gson gson = new Gson();
            StringBuilder str = new StringBuilder();
            str.append("Fines by user id\n");

            while(resultSet.next()){
                String string = resultSet.getString(1);
//                Fine fine = gson.fromJson(string, Fine.class);
//                str.append(i++).append(".Fine amount: ").append(fine.getAmount()).append(" || date: "+ fine.getPayDate()).append("\n");
                str.append(i++).append(string).append("\n");
                if(string.endsWith(",)"))
                    str.append("to'lanmagan");

            }

            return str.toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Not Found";
    }
}
