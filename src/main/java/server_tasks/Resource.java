package server_tasks;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

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

    public Resource(ScriptPython scriptPython) {
        this.scriptPython = scriptPython;
    }

    @GetMapping(value = "data", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getData(@RequestParam float random_encounters,
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

    private String getJsonFile() throws IOException {
        LOG.info(jsonPath);
        return Files.toString(Paths.get(jsonPath).toFile(), StandardCharsets.UTF_8);
    }
}
