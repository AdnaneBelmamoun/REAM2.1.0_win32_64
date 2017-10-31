package reamStartup;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

// @license

/**
 * @author pcdavid
 */
public class GetEnv {
    public static Map<String, String> getEnvironment() {
        Map<String, String> env = new HashMap<String, String>();
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader("/proc/self/environ"));
            String[] lines = reader.readLine().split("\000");
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                int n = line.indexOf('=');
                env.put(line.substring(0, n), line.substring(n+1));
            }            
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ioe) {
                /* ignore */
            }
        }
        return env;
    }
    
    public static String getEnv(String varName) {
        return getEnvironment().get(varName); 
    }
    
    
    public static void main(String[] args) {
        Map<String,String> env = GetEnv.getEnvironment();
        
        String wsvarname ="REAMlws";
        if(env.containsKey(wsvarname)){
        	System.out.println("***"+wsvarname + "=" + env.get(wsvarname));
        }else{
        	System.out.println( "The key " + wsvarname + "  don't exist in this OS");
        }
        
        //for (Iterator<String> iter = env.keySet().iterator(); iter.hasNext();) {
          //  String var= iter.next();
            //System.out.println("***"+var + "=" + env.get(var));
            
        //}
    }
}
