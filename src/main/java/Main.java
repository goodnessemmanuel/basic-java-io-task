
public class Main
{
    public static final String PRODUCTION = "production";
    public static final String DEVELOPMENT = "development";
    public static final String STAGING = "staging";
    public static void main(String[] args)
    {
        ConfigParser config = new ConfigParser();
        String fileToRead;
        String arguement = args[0];
        boolean invalidArguement = true;

        switch (arguement.toLowerCase())
        {
            //if argument is empty, production environment should load by default
            case "" :
            case PRODUCTION :
                    config = new ConfigParser();
                    fileToRead = config.getFileName();
                    invalidArguement = false;
                break;

            case "staging":
                    config = new ConfigParser("config-staging.txt");
                    invalidArguement = false;
                break;

            case "development":
                    config = new ConfigParser("config-dev.txt");
                    invalidArguement = false;
                break;

            default:
                    invalidArguement = true;
                break;
        }


        System.out.println(config.get("application.name"));
        System.out.println(config.get("dbname"));
        System.out.println(config.get("host"));
        System.out.println(config.get("application.port"));
        System.out.println(config.get("mode"));
        System.out.println(config.get("application.context-url"));
    }

}
