package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.Todo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GistService {

    private static final Logger logger = LoggerFactory.getLogger(GistService.class);

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Value("${github.token}")
    private String githubToken;

    public ResponseEntity<ResponseDto> exportProjectSummaryAsGist(Project project) {
        ResponseDto responseDto = new ResponseDto();
        if (project == null || project.getTitle() == null || project.getTodos() == null) {
            logger.error("Invalid project data. Project, title, or todos cannot be null.");
            responseDto.setMessage("Invalid project data.");
            responseDto.setStatus(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        try {
            String markdownContent = generateMarkdownContent(project);
            JSONObject gistRequest = createGistRequest(project.getTitle(), markdownContent);
            ResponseEntity<String> response = postGist(gistRequest);

            if (response.getStatusCode() == HttpStatus.CREATED) {
                logger.info("Gist created successfully.");
                JSONObject responseBody = new JSONObject();
                responseDto.setMessage("Gist created successfully.");
                responseDto.setResponse(null);
                responseDto.setStatus(true);
                return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
            } else {
                logger.error("Failed to create Gist: {}", response.getStatusCode());
                logger.error(response.getBody());
                responseDto.setMessage("Failed to create Gist.");
                responseDto.setResponse(new JSONObject());
                responseDto.setStatus(false);
                return new ResponseEntity<>(responseDto, response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("An error occurred while creating the gist: ", e);
            responseDto.setMessage("An error occurred while creating the gist.");
            responseDto.setResponse(new JSONObject());
            responseDto.setStatus(false);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateMarkdownContent(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(project.getTitle()).append("\n\n");
        long completedTodos = project.getTodos().stream().filter(todo -> "completed".equals(todo.getStatus())).count();
        sb.append("Summary: ").append(completedTodos).append(" / ").append(project.getTodos().size()).append(" completed.\n\n");

        appendTodos(sb, project, "Pending Todos", "pending");
        appendTodos(sb, project, "Completed Todos", "completed");

        logger.debug("Generated markdown content: {}", sb.toString());
        return sb.toString();
    }

    private void appendTodos(StringBuilder sb, Project project, String sectionTitle, String status) {
        sb.append("## ").append(sectionTitle).append("\n");
        int index = 1;
        for (Todo todo : project.getTodos()) {
            if (status.equals(todo.getStatus())) {
                sb.append(index).append(". [").append(status.equals("completed") ? "✓" : " ").append("] ").append(todo.getDescription()).append("\n");
                index++;
            }
        }
        sb.append("\n");
    }

    private JSONObject createGistRequest(String title, String content) {
        JSONObject gistRequest = new JSONObject();
        gistRequest.put("description", "Project Summary");
        gistRequest.put("public", false);

        JSONObject files = new JSONObject();
        JSONObject fileContent = new JSONObject();
        fileContent.put("content", content);
        files.put(title + ".md", fileContent);

        gistRequest.put("files", files);
        return gistRequest;
    }

    private ResponseEntity<String> postGist(JSONObject gistRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(githubToken);

        HttpEntity<String> request = new HttpEntity<>(gistRequest.toJSONString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(githubApiUrl, request, String.class);
    }
}
