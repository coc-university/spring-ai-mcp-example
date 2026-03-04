package com.codecamp.mcp.server.example;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubRepoService {

    private final List<GitHubRepo> repos = new ArrayList<>();

    public GitHubRepoService() {
        repos.addAll(List.of(
                new GitHubRepo("Robin","spring-app-template", "Template for building spring web apps"),
                new GitHubRepo("Patrick","python_app_template","Template for building python apps"),
                new GitHubRepo("Robin","spring-modulith-template","Template for building modular spring apps"),
                new GitHubRepo("Daniel","kafka-optin-optout","Demo app for kafka")
        ));
    }


    @McpTool(name = "get-all-github-repos", description = "Get a list of all github repos")
    public List<GitHubRepo> getAllRepos() {
        return repos;
    }

    @McpTool(name = "get-github-repo-by-name", description = "Get a single repo by name")
    public GitHubRepo getRepo(String name) {
        return repos
                .stream()
                .filter(gitHubRepo -> gitHubRepo.name().equals(name))
                .findFirst()
                .orElse(null);
    }

}
