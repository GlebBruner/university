package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Company implements Callable<Object> {

    private int id;
    private Enterprise dealer;
    private List<Packet> additionalPacketsForProcess;

    private static final boolean PROCESS_LOCALLY = true;

    private static final int PROCESSING_TIME = 1000;
    private static final int SENDING_TIME = 1000;
    private static final int IF_LOCAL_MODE_NUM_OF_PACKAGES = 17; // in code it will be *0.6 and pass to func
    private static final int IF_CENTRAL_MODE_NUM_OF_PACKAGES = 5; //

    private void sendInformationToEnterprise(boolean isInformationFull) throws InterruptedException {

        if (isInformationFull){
            generateAndSendPackets(IF_CENTRAL_MODE_NUM_OF_PACKAGES);

        } else {
            Double num = IF_LOCAL_MODE_NUM_OF_PACKAGES * 0.6;
            generateAndSendPackets(num.intValue());
            dealer.sendPacketsToLocal(this);
        }

    }

    private void sendSomePackets(boolean processLocally) throws InterruptedException {
        if (processLocally) {
            sendInformationToEnterprise(false);
        } else {
            sendInformationToEnterprise(true);
        }
    }

    private  void generateAndSendPackets(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            Packet packetToSend = new Packet();
            packetToSend.setId(i);
            packetToSend.setReciever(dealer);
            packetToSend.setBody("info from package " + packetToSend.getId());
            packetToSend.setSender(this);

            if (dealer.getNotProcessedPackets().containsKey(this)) {

                if (dealer.getLockForNotProcessedPackets().tryLock()) {
                    try {
                        dealer.getNotProcessedPackets().get(this).add(packetToSend);
                        System.out.println("Sending packet " + packetToSend.getId() + " from company" + this.getId());
                    } finally {
                        dealer.getLockForNotProcessedPackets().unlock();
                    }
                } else {
                    i--;
                }
            } else {
                if (dealer.getLockForNotProcessedPackets().tryLock()) {
                    try {
                        List<Packet> packets = new ArrayList<>();
                        packets.add(packetToSend);
                        dealer.getNotProcessedPackets().put(this, packets);

                        System.out.println("Sending packet " + packetToSend.getId() + " from company" + this.getId());
                    } finally {
                        dealer.getLockForNotProcessedPackets().unlock();
                    }
                } else {
                    i--;
                }
            }
            Thread.sleep(SENDING_TIME);
        }
    }

    void processPacketsLocally() throws InterruptedException {
        for (int i = 0; i < additionalPacketsForProcess.size(); i++) {
            System.out.println("Company "+ getId() + "Processing packet #" + additionalPacketsForProcess.get(i).getId() + " from "
                    + additionalPacketsForProcess.get(i).getSender());
            Thread.sleep(PROCESSING_TIME);
        }
        for (int i = 0; i < 7; i++) {
            System.out.println("Company "+ getId() +  "Processing own packet #" + i);
            Thread.sleep(PROCESSING_TIME);
        }
    }

    @Override
    public Object call() throws Exception {
        while (true) {
            sendSomePackets(PROCESS_LOCALLY);
            Thread.sleep(45000);
        }
    }

    public Company(int id, Enterprise dealer) {
        this.id = id;
        this.dealer = dealer;
        this.additionalPacketsForProcess = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    List<Packet> getAdditionalPacketsForProcess() {
        return additionalPacketsForProcess;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                '}';
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Enterprise enterprise = new Enterprise();

        Company company1 = new Company(1, enterprise);
        Company company2 = new Company(2, enterprise);
        enterprise.getCompanies().add(company1);
        enterprise.getCompanies().add(company2);

        List<Callable<Object>> POTOKI = new ArrayList<>();
        POTOKI.add(enterprise);
        POTOKI.add(company1);
        POTOKI.add(company2);



        try {
            executorService.invokeAll(POTOKI);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}