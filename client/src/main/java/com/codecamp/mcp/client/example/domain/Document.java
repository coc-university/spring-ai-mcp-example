package com.codecamp.mcp.client.example.domain;

public record Document(
        String owner,
        String name,
        String description
) {}