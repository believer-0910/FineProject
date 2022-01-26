package database;

import com.google.gson.Gson;
import model.Car;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDatabase {

    public static String getCarByNumber(String number){
        StringBuilder str = new StringBuilder();

        int i = 1;

        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_car(i_number := ?)");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
//            Gson gson = new Gson();
            str.append("Cars:\n");

            while(resultSet.next()){
                String string = resultSet.getString(1);
//                Car car = gson.fromJson(string, Car.class);
//                str.append(i++).append(".Car name: ").append(car.getName()).append(" || car number: ").append(car.getCarNumber()).append("\n");
                str.append(i++).append(string).append("\n");
            }

           return str.toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "Not found";
    }

}
