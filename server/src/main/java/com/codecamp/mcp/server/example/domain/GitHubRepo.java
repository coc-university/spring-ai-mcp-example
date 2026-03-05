package com.codecamp.mcp.server.example.domain;

public record GitHubRepo(
        String owner,
        String name,
        String description
) {}
