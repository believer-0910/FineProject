package database;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.User;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {

    public static String getUserByPassport(String passport){
        String str = "User not found";
        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_user (i_p_seriya := ?)");
            preparedStatement.setString(1, passport);
            ResultSet resultSet = preparedStatement.executeQuery();
//            Gson gson = new Gson();


            while(resultSet.next()){
                String string = resultSet.getString(1);
//                User user = gson.fromJson(string, User.class);
//                str = "Name: " + user.getName() + "\nPassport number: "+ user.getPassportSerial();
                return string;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }

    public static String getUserById(String id){
        String str = "User not found";
        try(Connection connection  = BaseDB.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement("select get_fines(i_u_id := ?)");
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
//            Gson gson = new Gson();


            while(resultSet.next()){
                String string = resultSet.getString(1);
//                User user = gson.fromJson(string, User.class);
//                str = "Name: " + user.getName() + "\nPassport number: "+ user.getPassportSerial();
                string = "User: " + string;
                return string;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return str;
    }

}
