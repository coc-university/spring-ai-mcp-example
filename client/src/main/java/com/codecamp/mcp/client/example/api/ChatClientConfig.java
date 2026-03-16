package com.codecamp.mcp.client.example.api;

import com.codecamp.mcp.client.example.tools.ClientDocumentTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ChatClientConfig {

    private static final Logger log = LoggerFactory.getLogger(ChatClientConfig.class);

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ClientDocumentTools clientTools, ToolCallbackProvider serverTools) {

        printTools(clientTools, serverTools);

        return chatClientBuilder
                .defaultTools(clientTools)
                .defaultToolCallbacks(serverTools)
                .defaultAdvisors(new SimpleLoggerAdvisor()) // set log-level in app.yaml to see the advisor in action
                .build();
    }

    private void printTools(ClientDocumentTools clientTools, ToolCallbackProvider serverTools) {

        // print all registered tools of the mcp-server (ServerGitHubRepoTools)
        Arrays.stream(serverTools.getToolCallbacks()).forEach(toolCallback -> {
            log.info("\n\nServer Tool: \n{}\n", toolCallback.getToolDefinition());
        });

        // print all registered tools of the client (ClientDocumentTools)
        Arrays.stream(ToolCallbacks.from(clientTools)).forEach(toolCallback -> {
            log.info("\n\nClient Tool: \n{}\n", toolCallback.getToolDefinition());
        });
    }
}
