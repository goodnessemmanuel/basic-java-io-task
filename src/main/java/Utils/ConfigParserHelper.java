package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigParser helper class
 */
public class ConfigParserHelper
{
    //access file using this relative path
    public final String filePath = "./src/main/resources/";

    public Map<String, String> loadConfigurationForFile(String filename)
    {
        Map<String, String> mapConfigFiles = new HashMap<>();

        //connect this program with the specify file in the given path to read line by line
        try {
            Path path = Paths.get(String.format("%s%s", filePath, filename));
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            String data = bufferedReader.readLine(); //read the first data line from target file
            while (data != null)
            {
                //check if data that has square bracket at the first index
                if (!data.isEmpty() && data.charAt(0) == '[')
                {
                    //extract the text within the bracket and append dot to it
                    String prefix = data.substring(1, data.length() - 1) + ".";
                    try
                    {
                        data = bufferedReader.readLine();
                        while (data != null && !data.isEmpty())
                        {
                            String[] keyValue = data.split("=");//split the text to to key and value
                            String key = prefix + keyValue[0]; //prepend key with the extracted prefix
                            String value = keyValue[1];

                            if (!mapConfigFiles.containsKey(key)) //dont allow duplicate key-value
                            {
                                mapConfigFiles.put(key, value); //add key-value to map
                            }

                            data = bufferedReader.readLine();

                        }
                    } catch (IOException e){
                        System.err.println(e.getMessage());
                    }
                }
                else if (data.contains("="))
                {
                    String[] keyValue = data.split("=");
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (!mapConfigFiles.containsKey(key)) {
                        mapConfigFiles.put(key, value);
                    }
                }
                data = bufferedReader.readLine();
            }
        }
        catch (IOException err) //if file read was
        {
            System.err.println("Cannot read from file " + err.getMessage());
        }
        return mapConfigFiles; //return the mapped key-value pairs
    }

}

