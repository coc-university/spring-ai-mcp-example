# Based on

- Dan Vega: 
  - MCP-Client: https://github.com/danvega/dvaas-client/tree/master
  - Chat-Client: https://github.com/danvega/spring-ai-workshop/tree/main/src/main/java/dev/danvega/workshop
- Josh Long: https://github.com/joshlong-attic/2026-02-18-bootiful-dogumentary/tree/main/assistant
- Other Examples: 
  - https://github.com/tzolov/playground-flight-booking
  - https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol

# Important Setup Steps

- openai
  - spring-ai-starter-model-openai dependency in pom.xml
  - spring.ai.openai.api-key EnvVar in application.yml
  - ADD YOUR KEY TO THE ENVIRONMENT!!!
- client
  - spring-ai-starter-mcp-client dependency in pom.xml
  - spring.ai.mcp.client config in application.yml
  - https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html

# Concepts:

- https://docs.spring.io/spring-ai/reference/api/chatclient.html
- ChatClient: main entry point for all ai-interactions
- Advisor: interceptor before and after ai-calls, can be used for logging, modifying input/output, etc.
- Tool: a function that can be called by the ai to get data, either local or remote (via mcp)
- Structured Output: convert ai-response into structured data (e.g. Java object) for easier processing
- all implemented in ChatController & ChatClientConfig

# MCP:

- Model Context Protocol: standard for communication between ai-clients and tools on a server
- it's like usb-c for ai-tools: you can connect any compliant tool that speaks the protocol 
- the ai-client can use it without caring about the implementation details

# Testing:

- first start the server, then the client (otherwise client-start will fail because it can't connect to the server)
- client tools (local function calling)
  - enter http://localhost:8080/chat/docs in the browser to trigger ai-chat with local tools of client
- server tools (mcp-server calling)
  - enter http://localhost:8080/chat/repos in the browser to trigger ai-chat with server tools via mcp

# Tool-Calling Flow:

- answer from the llm:
  - DefaultToolCallingManager → executeToolCalls() → breakpoint after AssistantMessage → check toolCalls-List
  - the llm tells the mcp-client in the AssistantMessage which tools it wants to call
- tool call execution:
  - SyncMcpToolCallback → call() → breakpoint at mcpClient.callTool(request) → check CallToolRequest
  - call the requested tool on the mcp-server

# Tool-Calling Architecture:

- https://docs.spring.io/spring-ai/reference/api/tools.html#_overview

![Bild](docs/Tool-Calling-Architecture.drawio.png)
