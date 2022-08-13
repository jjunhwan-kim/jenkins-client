package com.example.jenkins.dto;

import lombok.Data;

import java.util.List;

@Data
public class JenkinsRequestDto {
    private String serverUrl;
    private Long id;
    private String status;
    private List<DataDto> dataDtoList;
}
