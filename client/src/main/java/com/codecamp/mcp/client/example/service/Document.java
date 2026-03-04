package com.codecamp.mcp.client.example.service;

public record Document(
        String owner,
        String name,
        String description
) {}