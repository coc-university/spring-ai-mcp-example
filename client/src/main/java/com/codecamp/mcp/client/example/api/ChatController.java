package com.codecamp.mcp.client.example.api;

import com.codecamp.mcp.client.example.tools.DocumentTools;
import com.codecamp.mcp.client.example.domain.Documents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder, DocumentTools clientTools, ToolCallbackProvider serverTools) {

        this.chatClient = builder
                .defaultTools(clientTools)
                .defaultToolCallbacks(serverTools)
                .defaultAdvisors(new SimpleLoggerAdvisor()) // set log-level in app.yaml to see the advisor in action
                .build();

        printTools(clientTools, serverTools);
    }

    @GetMapping("/chat/docs") // the ai-model should trigger the @Tool from the client (DocumentService)
    public Documents docs() {
        Documents documents = chatClient.prompt()
                .user("Welche Dokumente hat Robin?")
                .call()
                .entity(Documents.class); // use extra type, because it's a list
        log.info("\n\nChat message as entity via Structured Output Converter: \n{}\n", documents);
        return documents;
    }

    @GetMapping("/chat/repos") // the ai-model should trigger the @McpTool from the server via mcp (GitHubRepoService)
    public String repos() {
        String message = chatClient.prompt()
                .user("Welche GitHub Repos hat Robin?")
                .call()
                .content();
        log.info("\n\nChat message as raw string: \n{}\n", message);
        return message;
    }

    private void printTools(DocumentTools clientTools, ToolCallbackProvider serverTools) {

        // print all registered tools of the mcp-server (GitHubRepoService)
        Arrays.stream(serverTools.getToolCallbacks()).forEach(toolCallback -> {
            log.info("\n\nServer Tool: \n{}\n", toolCallback.getToolDefinition());
        });

        // print all registered tools of the client (DocumentService)
        Arrays.stream(ToolCallbacks.from(clientTools)).forEach(toolCallback -> {
            log.info("\n\nClient Tool: \n{}\n", toolCallback.getToolDefinition());
        });
    }

}
