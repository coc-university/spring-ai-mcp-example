# Based on 

- Dan Vega: https://github.com/danvega/dvaas/tree/master  
- Josh Long: https://github.com/joshlong-attic/2026-02-18-bootiful-dogumentary/tree/main/scheduler  
- Examples: https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol

# Important Setup Steps

- spring-ai-starter-mcp-server-webmvc dependency in pom.xml
- spring.ai.mcp.server config in application.yml
- @McpTool on a spring bean method (GitHubRepoService)
- doku: https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html

# Testing:
- https://modelcontextprotocol.io/docs/tools/inspector
- CMD: npx @modelcontextprotocol/inspector
  - TYPE: Streamable HTTP
  - URL: http://localhost:8080/mcp

# Setup for Claude Desktop

- Not working with new config !!! (streamable now, instead of stdio)
- https://modelcontextprotocol.io/quickstart/user  
- check out example_claude_desktop_config.json
