import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
public class NewConfigParser {
   private final String filePath = "/Users/a/Downloads/Task-Two-master/src/"; //replace this line with the path to the file to be read.
   private final String fileName;
   private Map<String, String> map = new HashMap<>();

    public NewConfigParser() {
        this("config.txt"); //default to this file name if an empty parameter is to the constructor
    }
    
    public NewConfigParser(String fileName) {
        this.fileName = fileName;
    }
    
    public String get(String key){
        this.parseConfigFile();
        return this.map.get(key);
    }
    
    private void parseConfigFile() {
        BufferedReader br = null;
        String line = "";
        try
        {
            br = Files.newBufferedReader(Paths.get(filePath + fileName));
            while ( (line = br.readLine()) != null )
            {
                if(!line.contains("=") && !line.equals(""))
                {
                    String prefix = line.substring(1, line.length() - 1);
                    while ((line = br.readLine()) != null  && !line.isEmpty())
                        this.populate(prefix.concat("."), line, "=");
                } else if(!line.isEmpty()) this.populate("", line, "=");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * populate this class map using underlay parameters
     * @param keyPrefix, prefix to be appended to map key
     * @param data, String to be splited
     * @param delimiter to split data
     */
    public void populate(String keyPrefix, String data, String delimiter) {
        String[] parts =  data.split(delimiter);
        String key = keyPrefix + parts[0].trim();
        String value = parts[1].trim();
        if( !this.map.containsKey(key) ) this.map.put(key, value);
    }
}
