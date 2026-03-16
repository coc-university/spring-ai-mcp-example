package com.codecamp.mcp.server.example.tools;

import com.codecamp.mcp.server.example.domain.GitHubRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServerGitHubRepoTools {

    private static final Logger log = LoggerFactory.getLogger(ServerGitHubRepoTools.class);

    // in a real application, you would probably fetch the documents from a database or an external service
    private final List<GitHubRepo> repos = new ArrayList<>();

    public ServerGitHubRepoTools() {
        repos.addAll(List.of(
                new GitHubRepo("Robin","spring-app-template", "Template for building spring web apps"),
                new GitHubRepo("Patrick","python_app_template","Template for building python apps"),
                new GitHubRepo("Robin","spring-modulith-template","Template for building modular spring apps"),
                new GitHubRepo("Daniel","kafka-optin-optout","Demo app for kafka")
        ));
    }


    @McpTool(name = "get-all-github-repos-from-mcp-server", description = "Get a list of all github repos")
    public List<GitHubRepo> getAllRepos() {
        log.info("return all repositories from mcp-server");
        return repos;
    }

    @McpTool(name = "get-github-repo-by-name-from-mcp-server", description = "Get a single repo by name")
    public GitHubRepo getRepo(String name) {
        log.info("return repository from mcp-server with name: {}", name);
        return repos
                .stream()
                .filter(gitHubRepo -> gitHubRepo.name().equals(name))
                .findFirst()
                .orElse(null);
    }

}
