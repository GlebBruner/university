package lab5;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Enterprise implements Callable{

    private static final int PROCESSING_TIME = 1000;
    private static final int SENDING_TIME = 1000;
    private static final int HOW_MANY_SEND_TO_COMPANIES = 3;

    private Map<Company, List<Packet>> notProcessedPackets = new HashMap<>();
    private Lock lockForNotProcessedPackets = new ReentrantLock();

    private void processSomePacketsFromCompany(Map.Entry<Company, List<Packet>> notProcessedPacketsFromCompany) throws InterruptedException {
        Iterator<Packet> iterator = notProcessedPacketsFromCompany.getValue().iterator();

        lockForNotProcessedPackets.lock();
        try {
            while (iterator.hasNext()) {
                System.out.println("Processing " + iterator.next().getId() + " packet by " + notProcessedPacketsFromCompany.getKey().getId() + " company");
                iterator.remove();
                Thread.sleep(PROCESSING_TIME); //proc 1 s
            }
        }  finally {
            lockForNotProcessedPackets.unlock();
        }


    }

    void sendPacketsToLocal(Company company) throws InterruptedException {
        for (int i = 0; i < HOW_MANY_SEND_TO_COMPANIES; i++) {
            Packet packet = new Packet();
            packet.setId(i);
            packet.setSender(this);
            packet.setReciever(company);
            packet.setBody("Packets body from " + this + " to " + company);

            company.getAdditionalPacketsForProcess().add(packet);

            System.out.println("Enterprise sends packet " + packet.getId() + " to company " + company.getId());

            Thread.sleep(SENDING_TIME);
        }
        company.processPacketsLocally();
    }

    private void listen() throws InterruptedException {
        System.out.println("Enterprise listens for upcoming packets for processing from different companies");
        for (Map.Entry<Company, List<Packet>> notProcPackFromCompany : notProcessedPackets.entrySet()) {
            if (notProcPackFromCompany.getValue().size() != 0) {
                System.out.println("Found not processed packet from company " + notProcPackFromCompany.getKey().getId());
                new Thread(() -> {
                    try {
                        processSomePacketsFromCompany(notProcPackFromCompany);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        }
    }
    @Override
    public Object call() throws Exception {
        while (true) {
            Thread.sleep(500);
            listen();
            Thread.sleep(3000);
        }
    }

    private List<Company> companies;

    public List<Company> getCompanies() {
        if (this.companies == null) {
            this.companies = new ArrayList<>();
            return this.companies;
        } else return this.companies;
    }

    Map<Company, List<Packet>> getNotProcessedPackets() {
        if (this.notProcessedPackets == null) {
            this.notProcessedPackets = new HashMap<>();
            return this.notProcessedPackets;
        } else {
            return this.notProcessedPackets;
        }
    }

    Lock getLockForNotProcessedPackets() {
        return lockForNotProcessedPackets;
    }
}