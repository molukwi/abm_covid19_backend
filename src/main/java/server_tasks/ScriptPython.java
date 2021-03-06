package server_tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ScriptPython {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptPython.class);

    public void runScript(String pythonPath, String scriptPath, Parameters parameters) {
        final Process process;
        try {
            String command = "python " + scriptPath + " " + parameters;
            LOG.info("Execute command: {}", command);
            process =
                    Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            throw new RuntimeException("Failing executing script", e.getCause());
        }
        final InputStream stdout = process.getInputStream();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                LOG.info("Script output: {}", line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception in reading output {0}", e.getCause());
        }
    }
}
