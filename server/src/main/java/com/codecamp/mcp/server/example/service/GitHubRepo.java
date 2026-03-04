package com.codecamp.mcp.server.example.service;

public record GitHubRepo(
        String owner,
        String name,
        String description
) {}
