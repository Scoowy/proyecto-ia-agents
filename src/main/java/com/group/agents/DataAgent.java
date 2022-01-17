package com.group.agents;

import com.group.models.TweetData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.15.1543
 */
public class DataAgent extends Agent {
    private List<TweetData> tweetsData = new ArrayList<>();

    @Override
    protected void setup() {
        // Se carga en memoria los datos de los tweets
        readDataCSV();

        // Se declara la primera tarea que estará a la espera de recibir un mensaje
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    // Procesamos la petición del mensaje entrante
                    ACLMessage reply = msg.createReply();
                    reply.setConversationId("QueryData");
                    TweetData tweet = getTweetData(msg.getContent());

                    try {
                        reply.setContentObject(tweet);
                        send(reply);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    block(); // Se bloquea el agente mientras no reciba más mensajes
                }
            }
        });
    }

    private void readDataCSV() {
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader("datosTweet.csv"));
            String[] nextLine = null;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                tweetsData.add(
                        new TweetData(
                                nextLine[0],
                                nextLine[1],
                                nextLine[2],
                                Integer.parseInt(nextLine[3]),
                                Integer.parseInt(nextLine[4]),
                                Integer.parseInt(nextLine[5]),
                                Integer.parseInt(nextLine[6]),
                                Integer.parseInt(nextLine[7]),
                                Integer.parseInt(nextLine[8]),
                                Integer.parseInt(nextLine[9])
                        ));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("[ERROR]: " + e.getMessage());
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("[INFO]: Loaded %d tweet datas\n", tweetsData.size());
    }

    private TweetData getTweetData(String tweetId) {
        for (TweetData tweet : tweetsData) {
            if (tweetId.equals(tweet.getId())) { return tweet; }
        }
        return null;
    }

}
