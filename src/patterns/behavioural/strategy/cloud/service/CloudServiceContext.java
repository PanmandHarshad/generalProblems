package patterns.behavioural.strategy.cloud.service;

import patterns.behavioural.strategy.cloud.CloudStrategy;

public class CloudServiceContext {

    private CloudStrategy strategy;

    public void setStrategy(CloudStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeTheDownload() {
        if (strategy != null) {
            strategy.downloadFile();
        } else {
            System.out.println("Strategy is not defined");
        }
    }

}
