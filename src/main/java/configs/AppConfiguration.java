package configs;

import com.datastructures.App;

public final class AppConfiguration {

    public static String dictionary = AppConfiguration.class.getClassLoader().getResource("dictionary.txt").toString();

    public static String map = AppConfiguration.class.getClassLoader().getResource("assn9_data.csv").toString();
    
}
