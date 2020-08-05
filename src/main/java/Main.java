import Utils.Environment;

import java.util.Scanner;

public class Main
{
    static String fileToRead;
    public static void main(String[] args)
    {
        ConfigParser config;
        String environment;
        try
        {
            //ask user for application environment
            environment = startApplication(args[0]); //block incorrect parameter using recursion

            //get the file name for the environment to load
            String fileName = Environment.getFile(environment);
            config = new ConfigParser(fileName); //if fileName is empty, use default configParser file name
            environment = fileName.isEmpty() ? config.getDefaultEnvironment() : environment;
            fileToRead = environment + " " + fileName; //file to read from
            loadEnvironment(config); //load the environment
        }
        catch (ArrayIndexOutOfBoundsException e) //if error, then user invoked without command line argument
        {

            config = new ConfigParser(); //load to the default
            fileToRead = config.getDefaultEnvironment() + " " + config.getFileName();
            loadEnvironment(config);
        }

    }

    private static String startApplication(String environment)
    {
        if (environment.equalsIgnoreCase("production") ||
                environment.equalsIgnoreCase("staging") ||
                environment.equalsIgnoreCase("development"))
        {
            return environment;
        }
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter correct application environment(e.g production, development or staging): ");
        //recursively call this method till the correct parameter is entered
        return startApplication(sc.next());
    }

    private static void loadEnvironment(ConfigParser config)
    {
        System.out.println("Loading... " + fileToRead + " file environment");
        System.out.println("----------------------------------------------------");
        System.out.println("database-name : " + config.get("dbname"));
        System.out.println("host :  " + config.get("host"));
        System.out.println("mode :  " + config.get("mode"));
        System.out.println("theme :  " + config.get("theme"));
        System.out.println("pipeline :  " + config.get("pipeline"));
        System.out.println("name :  " + config.get("application.name"));
        System.out.println("port :  " + config.get("application.port"));
        System.out.println("context :  " + config.get("application.context-url"));
    }

}
