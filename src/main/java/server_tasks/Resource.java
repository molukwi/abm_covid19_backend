package server_tasks;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/")
public class Resource {

    @Value("${jsonPath}")
    private String jsonPath;

    @GetMapping(value = "getJson", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getJson(@RequestParam String argument1) throws IOException {
        Validate.notBlank(argument1, "Parameter must not be blank");
        new ScriptPython().runScript(argument1);

        InputStream in = getClass().getResourceAsStream("/com/baeldung/produceimage/data.txt");
        return IOUtils.toByteArray(in);
    }
}
