package com.example.jenkins;

import com.example.jenkins.dto.JenkinsRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "input json data");
        options.addOption("p1", "param1", true, "input string parameter 1");
        options.addOption("p2", "param2", true, "input string parameter 2");
        options.addOption("h", "help", false, "print this message");

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }

            if (commandLine.hasOption("input")) {
                String input = commandLine.getOptionValue("input");
                log.info("input={}", input);

                JenkinsRequestDto jenkinsRequestDto = objectMapper.readValue(input, JenkinsRequestDto.class);
                log.info("jenkinsRequestDto={}", jenkinsRequestDto);
            }

            if (commandLine.hasOption("param1")) {
                String param1 = commandLine.getOptionValue("param1");
                log.info("param1={}", param1);
            }

            if (commandLine.hasOption("param2")) {
                String param2 = commandLine.getOptionValue("param2");
                log.info("param2={}", param2);
            }
        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
