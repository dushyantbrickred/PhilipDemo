import java.io.InputStream;
import java.util.Properties;
import java.util.stream.IntStream;

public class DynamicLoad {

    public static String loadValue(String key)
    {
        Properties properties = new Properties();

        try {
            InputStream in =DynamicLoad.class.getClassLoader().getResourceAsStream("login.properties");
            properties.load(in);

        }catch (Exception e)
        {
            e.getMessage();
        }

        return properties.getProperty(key);
    }
}
