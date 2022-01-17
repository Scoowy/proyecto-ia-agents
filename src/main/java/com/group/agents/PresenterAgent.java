package com.group.agents;

import com.group.ui.TweetRaterUI;
import com.group.models.AgentId;
import com.group.models.TweetStats;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import javax.swing.JFrame;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.1831
 */
public class PresenterAgent extends Agent {
    public AID analyzerAgentId;
    public TweetRaterUI UI;

    @Override
    protected void setup() {
        analyzerAgentId = new AID(AgentId.AGENT_A.getName(), AID.ISLOCALNAME);

        JFrame frameRoot = new JFrame("Tweet Rater");
        UI = new TweetRaterUI();
        frameRoot.setContentPane(UI.mainPnl);
        frameRoot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UI.btnRun.addActionListener(e -> {
            requestData(UI.txtTweetId.getText());
        });

        frameRoot.pack();
        frameRoot.setVisible(true);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    try {
                        var tweet = (TweetStats) msg.getContentObject();
                        System.out.println(tweet);
                        presentTweetStats(tweet);
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

    public void requestData(String tweetId) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(analyzerAgentId);
        msg.setConversationId("AnalyzeData");
        msg.setContent(tweetId);
        send(msg);
    }

    public void presentTweetStats(TweetStats tweet) {
        UI.dataset.setValue("Malos", tweet.getBadCommentsRate());
        UI.dataset.setValue("Buenos", tweet.getGoodCommentsRate());
        UI.dataset.setValue("Neutrales", tweet.getNeutralCommentsRate());
        UI.lblContent.setText(tweet.getTweet().getContent());
        UI.lblImpact.setText(tweet.getSocialImpact());
        UI.lblNumComments.setText(String.valueOf(tweet.getTweet().getComments()));
        UI.lblRetweetRate.setText(String.format("%.2f%%", tweet.getRetweetRate()));
    }
}
