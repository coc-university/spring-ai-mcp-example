# Based on

- Dan Vega: https://github.com/danvega/dvaas-client/tree/master
- Josh Long: https://github.com/joshlong-attic/2026-02-18-bootiful-dogumentary/tree/main/assistant
- Examples: https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol

# Important Setup Steps

- openai
  - spring-ai-starter-model-openai dependency in pom.xml
  - spring.ai.openai.api-key EnvVar in application.yml
- client
  - spring-ai-starter-mcp-client dependency in pom.xml
  - spring.ai.mcp.client config in application.yml
  - https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html

# Testing:

- first start the server, then the client (otherwise client-start will fail because it can't connect to the server)
- client tools (local function calling)
  - enter http://localhost:8080/chat/docs in the browser to trigger ai-chat with local tools of client
- server tools (mcp-server calling)
  - enter http://localhost:8080/chat/repos in the browser to trigger ai-chat with server tools via mcp

# Concepts:

- https://docs.spring.io/spring-ai/reference/api/chatclient.html
- ChatClient: main entry point for all ai-interactions
- Advisor: interceptor before and after ai-calls, can be used for logging, modifying input/output, etc.
- Tool: a function that can be called by the ai to get data, either local or remote (via mcp)
- Structured Output: convert ai-response into structured data (e.g. Java object) for easier processing
- all implemented in ChatController

# Not Covered Concepts:

- RAG: 
  - via QuestionAnswerAdvisor (query in vector database)
  - or manually (check of https://github.com/coc-university/spring-ai-rag-example)
- Chat Memory: PromptChatMemoryAdvisor (retrieve the conversation history from the memory)
- check out example: https://github.com/joshlong-attic/2026-02-18-bootiful-dogumentary/blob/main/assistant/src/main/java/com/example/assistant/AssistantApplication.java

# Tool Calling Architecture:

- https://docs.spring.io/spring-ai/reference/api/tools.html#_overview

![Bild](docs/Tool-Calling-Architecture.drawio.png)
