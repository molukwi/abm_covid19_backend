package server_tasks;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class Resource {
    private static final Logger LOG = LoggerFactory.getLogger(Resource.class);

    @Value("${scriptPath}")
    private String scriptPath;

    @Value("${jsonPath}")
    private String jsonPath;

    private final ScriptPython scriptPython;

    public Resource(ScriptPython scriptPython){
        this.scriptPython = scriptPython;
    }

    @GetMapping(value = "data", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getData(@RequestParam String argument1) throws IOException {
        Validate.notBlank(argument1, "Parameter must not be blank");

        scriptPython.runScript(scriptPath, argument1);

        return getJsonFile();
    }

    private byte[] getJsonFile() throws IOException {
        final InputStream is = new FileInputStream(jsonPath);
        LOG.info(jsonPath);
        return IOUtils.toByteArray(is);
    }
}
