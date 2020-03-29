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

    @Value("${pythonPath}")
    private String pythonPath;

    @Value("${scriptPath}")
    private String scriptPath;

    @Value("${jsonPath}")
    private String jsonPath;

    private final ScriptPython scriptPython;

    public Resource(ScriptPython scriptPython){
        this.scriptPython = scriptPython;
    }

    @GetMapping(value = "data", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getData(@RequestParam float random_encounters,
                          @RequestParam float[] prob_communities,
                          @RequestParam float initial_fraction_infected,
                          @RequestParam float fraction_interacting,
                          @RequestParam float p_infection,
                          @RequestParam float p_contact) throws IOException {
     final Parameters parameters =
             new Parameters.Builder()
                     .withRandomEncounters(random_encounters)
                     .withProbCommunities(prob_communities)
                     .withInitialFractionInfected(initial_fraction_infected)
             .withFractionInteracting(fraction_interacting)
             .withPInfection(p_infection)
             .withPContact(p_contact).build();

        scriptPython.runScript(pythonPath, scriptPath, parameters);

        return getJsonFile();
    }

    private byte[] getJsonFile() throws IOException {
        final InputStream is = new FileInputStream(jsonPath);
        LOG.info(jsonPath);
        return IOUtils.toByteArray(is);
    }
}
