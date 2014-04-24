/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pubsub.publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import pubsub.publisher.broker.Client;
import pubsub.publisher.utils.Constants;

/**
 *
 * @author thirumalaisamy
 */
public class PubSubPublisher {
    
    private String brokerAddress;
    
    private Client broker;
    private boolean isBorkerAvailable = false;
    public PubSubPublisher(String address){
        this.brokerAddress = address;
        this.broker = new Client(brokerAddress, Constants.BROKER_PORT);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the address of the broker");
            String address = br.readLine();
            PubSubPublisher publisher = new PubSubPublisher(address);
            publisher.displayMenu();
        } catch (IOException ex) {
            Logger.getLogger(PubSubPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayMenu() {
        try {
            this.broker.connect();
            isBorkerAvailable = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
            System.out.println("===========Menu===========");
                String readLine = br.readLine();
            }
        } catch (Exception ex) {
            isBorkerAvailable = false;
            
            Logger.getLogger(PubSubPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
