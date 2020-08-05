package Utils;

public abstract class Environment
{
    public static String getFile(String commandLineArguement)
    {
        String sourceFile;
        switch (commandLineArguement.toLowerCase())
        {
            case "production": sourceFile = "config.txt";
            break;

            case "staging": sourceFile = "config-staging.txt";
            break;

            case "development": sourceFile = "config-dev.txt";
            break;

            default:
                sourceFile = "";
        }
        return sourceFile;
    }
}
