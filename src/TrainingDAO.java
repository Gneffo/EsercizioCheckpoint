import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainingDAO {

    public TrainingSessionWithData getTraningSessionDataByTrainingId(Integer trainingId){
        TrainingSessionData trainingSessionData = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gneffgneff1M");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from TRAINING_SESSION ts inner join TRAINING_SESSION_DATA tsd on ts.ID_TRAINING_SESSION = tsd.TRAINING_SESSION");
            preparedStatement.setInt(1,trainingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                trainingSessionData = new TrainingSessionData(resultSet.getLong("ID_TRAINING_SESSION_DATA"),resultSet.getDouble("LATITUDE"),
                        resultSet.getDouble("LONGITUDE"),resultSet.getDouble("MOVEMENT_SPEED"),
                        resultSet.getDouble("HEART_RATE"),resultSet.getInt("TRAINING_SESSION"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
