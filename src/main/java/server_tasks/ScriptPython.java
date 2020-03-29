package server_tasks;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class ScriptPython {
    private Process mProcess;

    private static final Logger LOG = LoggerFactory.getLogger(ScriptPython.class);

    @Value("${scriptPath}")
    private String scriptPath;

    public String runScript(String argument1){
        String result = "";
        Process process;
        try{
            LOG.info("Execute python {}", scriptPath);
            process =
                    Runtime.getRuntime().exec("python " + scriptPath + " " + argument1);
            mProcess = process;
        }catch(Exception e) {
            LOG.error("Exception Raised {0}", e);
        }
        InputStream stdout = mProcess.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout,StandardCharsets.UTF_8));
        String line;
        try{
            while((line = reader.readLine()) != null){
                LOG.info("Script output: {}", line);
            }
        }catch(IOException e){
            LOG.error("Exception in reading output {0}", e);
        }
        return result;
    }
}
