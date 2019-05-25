package org.salaboy.k8s.knative.fn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class FunctionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);


    private int count = 0;


    //@TODO: improve response type to ASCII art
    @GetMapping("/")
    public String calculateDiscount() {
        count++;
        return "Function A call: " + count;
    }

    @PostMapping("/")
    public @ResponseBody
    String eventCalculate(@RequestBody String cloudEventJson) {
        count++;
        LOGGER.info("Event Message Received \n {}", cloudEventJson);
        return "Event Message Received: " + cloudEventJson;
    }

}
