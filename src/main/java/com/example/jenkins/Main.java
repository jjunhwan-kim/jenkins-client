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
        options.addOption("h", "help", false, "print this message");

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }

            if (commandLine.hasOption("input")) {
                // 옵션 값을 반환
                String input = commandLine.getOptionValue("input");
                log.info("input={}", input);

                JenkinsRequestDto jenkinsRequestDto = objectMapper.readValue(input, JenkinsRequestDto.class);
                log.info("jenkinsRequestDto={}", jenkinsRequestDto);
            }
        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
