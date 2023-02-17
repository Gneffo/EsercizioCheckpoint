import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {
    List users = new ArrayList<>();

    public List<Object> getAllDeviceWIthHearthRateAndGps(String username) {
        Device device = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gneffgneff1M");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from device where DEVICE_HAS_GPS <> 0 and DEVICE_HAS_HEARTRATE <> 0 and USERNAME = ?");
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                device = new Device(resultSet.getString("DEVICE_PART_NUMBER"), resultSet.getString("DEVICE_MANUFACTURER"), resultSet.getString("DEVICE_MODEL"),
                        resultSet.getShort("DEVICE_HAS_GPS"), resultSet.getShort("DEVICE_HAS_HEARTRATE"), resultSet.getString("USERNAME"));
                users.add(device);

            }
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;

    }

}
