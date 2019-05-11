package org.salaboy.k8s.knative.fn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
public class FunctionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);

    private static final String RESPONSE_STRING_FORMAT = "%s %s example-function-a => '%s' : %d\n";

    private final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");

    private static final String HOSTNAME =
        parseContainerIdFromHostname(System.getenv().getOrDefault("HOSTNAME", "unknown"));

    static String parseContainerIdFromHostname(String hostname) {
        return hostname.replaceAll("example-function-a-v\\d+-", "");
    }

    @Value("${MESSAGE_PREFIX:Hi}")
    private String prefix;

    /**
     * Counter to help us see the lifecycle
     */
    private int count = 0;


    @GetMapping("/")
    public String calculateDiscount() {
        count++;
        return String.format(RESPONSE_STRING_FORMAT, prefix, "", HOSTNAME, count);
    }

    @PostMapping("/")
    public @ResponseBody String eventCalculate(@RequestBody String cloudEventJson) {
        count++;
        //  String greeterHost = String.format(RESPONSE_STRING_FORMAT, ""," Event ", HOSTNAME, count);

        //   cloudEventJson
//                                  .put("host",greeterHost.replace("\n","").trim())
//                                  .put("time",SDF.format(new Date()));
        LOGGER.info("Event Message Received \n {}",cloudEventJson);
        return "Event Message Received: " + cloudEventJson;
    }

}
