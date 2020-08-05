import Utils.ConfigParserHelper;
import Utils.Property;
import java.util.Map;

public class ConfigParser extends Property
{
    //field and helper class to aid retrieval of key-value from the config files
    private final ConfigParserHelper configHelper;
    private final String fileName;
    private final String defaultEnvironment;

    public ConfigParser()
    {
        this("config.txt"); //use this file name as default if no argument is passed
    }

    public ConfigParser(String fileName)
    {
        this.fileName = fileName;
        defaultEnvironment = "production"; //default environment
        configHelper = new ConfigParserHelper(); //call the config helper class to initiate configuration
    }

    public String get(String key)
    {
        String property = "";
        //load configuration settings to this map
        Map<String, String> mapConfigFile = configHelper.loadConfigurationForFile(fileName);

        switch (key) //check which key was entered and return the corresponding value in map
        {
            case NAME: property = mapConfigFile.get(NAME);
            break;

            case MODE: property = mapConfigFile.get(MODE);
            break;

            case PORT: property = mapConfigFile.get(PORT);
            break;

            case DBNAME: property = mapConfigFile.get(DBNAME);
            break;

            case HOST: property = mapConfigFile.get(HOST);
            break;

            case PIPELINE: property = mapConfigFile.get(PIPELINE);
            break;

            case THEME: property = mapConfigFile.get(THEME);
            break;

            case CONTEXT_URL: property = mapConfigFile.get(CONTEXT_URL);
            break;

            default: property = "enter valid key";
            break;
        }

        return property;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getDefaultEnvironment()
    {
        return defaultEnvironment;
    }
}
