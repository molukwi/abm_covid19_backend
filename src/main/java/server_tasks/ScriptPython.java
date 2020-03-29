package server_tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ScriptPython {
    private Process mProcess;

    private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

    public String runScript(String argument1){
        String result = "";
        Process process;
        try{
            process = Runtime.getRuntime().exec("python C:\\project\\covid-19\\server_tasks\\script\\dummy.py " + argument1);
            mProcess = process;
        }catch(Exception e) {
            LOG.error("Exception Raised {0}", e);
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
        String line;
        try{
            while((line = reader.readLine()) != null){
                result += line;
            }
        }catch(IOException e){
            LOG.error("Exception in reading output {0}", e);
        }
        return result;
    }
}
