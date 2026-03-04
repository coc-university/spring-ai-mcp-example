package com.codecamp.mcp.server.example;

public record GitHubRepo(
        String owner,
        String name,
        String description
) {}
