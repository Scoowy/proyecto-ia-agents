package com.group.models;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.2249
 */
public enum AgentId {
    AGENT_A("AA"),
    AGENT_D("AD"),
    AGENT_P("AP");

    private final String name;
    private AgentId(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
