package com.group.agents;

import com.group.models.AgentId;
import com.group.models.TweetData;
import com.group.models.TweetStats;
import com.group.utils.Operations;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.1934
 */
public class AnalyzerAgent extends Agent {
    private List<TweetStats> tweetsStats = new ArrayList<>();
    private AID dataAgentId;
    private AID presenterAgentId;

    // =D2*0.1 + L2*0.2 - M2*0.1 + N2*0.1 + K2*0.05
    private final Map<String, Double> WEIGHTS = new HashMap<>() {{
        put("likes", 0.1);
        put("badComments", 0.2);
        put("neutralComments", 0.1);
        put("goodComments", 0.3);
        put("retweets", 0.2);
    }};

    @Override
    protected void setup() {
        dataAgentId = new AID(AgentId.AGENT_D.getName(), AID.ISLOCALNAME);
        presenterAgentId = new AID(AgentId.AGENT_P.getName(), AID.ISLOCALNAME);

        // Tarea a la escucha de envio de datos desde PresenterAgent
        addBehaviour(new CyclicBehaviour() {
            private final MessageTemplate mt = MessageTemplate.MatchConversationId("AnalyzeData");

            @Override
            public void action() {
                ACLMessage msg = receive(mt);

                if (msg != null) {
                    String tweetId = msg.getContent();

                    TweetStats tweet = getTweetStats(tweetId);
                    if (tweet != null) {
                        var reply = msg.createReply();

                        try {
                            reply.setContentObject(tweet);
                            send(reply);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ACLMessage newMsg = new ACLMessage(ACLMessage.INFORM);
                        newMsg.addReceiver(dataAgentId);
                        newMsg.setContent(tweetId);
                        send(newMsg);
                    }
                } else {
                    block();
                }
            }
        });

        // Tarea a la escucha de envio de datos desde DataAgent
        addBehaviour(new CyclicBehaviour() {
            private final MessageTemplate mt = MessageTemplate.MatchConversationId("QueryData");

            @Override
            public void action() {
                ACLMessage msg = receive(mt);

                if (msg != null) {
                    TweetData tweet = null;
                    try {
                       tweet = (TweetData) msg.getContentObject();
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }

                    if (tweet != null) {
                        TweetStats tweetStats = analyzeTweet(tweet);
                        tweetsStats.add(tweetStats);

                        ACLMessage newMsg = new ACLMessage(ACLMessage.INFORM);
                        newMsg.addReceiver(presenterAgentId);
                        try {
                            newMsg.setContentObject(tweetStats);
                            send(newMsg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    block();
                }
            }
        });
    }

    public TweetStats getTweetStats(String tweetId) {
        for (TweetStats tweet : tweetsStats) {
            if (tweetId.equals(tweet.getTweet().getId())) {
                return tweet;
            }
        }
        return null;
    }

    public TweetStats analyzeTweet(TweetData tweet) {
        double retweetRate = Operations.getPercentageFromValue(tweet.getFollowers(), tweet.getRetweets());
        double badCommentsRate = Operations.getPercentageFromValue(tweet.getComments(), tweet.getBadComments());
        double neutralCommentsRate = Operations.getPercentageFromValue(tweet.getComments(), tweet.getNeutralComments());
        double goodCommentsRate = Operations.getPercentageFromValue(tweet.getComments(), tweet.getGoodComments());
        double likes = (double) tweet.getLikes();

        // =D2*0.1 + L2*0.2 - M2*0.1 + N2*0.1 + K2*0.05
        double acceptanceLevel = (likes * WEIGHTS.get("likes")) + (goodCommentsRate * WEIGHTS.get("goodComments")) - (badCommentsRate * WEIGHTS.get("badComments")) + (neutralCommentsRate * WEIGHTS.get("neutralComments")) + (retweetRate * WEIGHTS.get("retweets"));
        String socialImpact = "POSITIVO";

        if (acceptanceLevel < 55.0) {
            socialImpact = "NEUTRAL";
        }

        if (acceptanceLevel < 30.0) {
            socialImpact = "NEGATIVO";
        }

        return new TweetStats(tweet, socialImpact, retweetRate, badCommentsRate, neutralCommentsRate, goodCommentsRate, acceptanceLevel);
    }
}
