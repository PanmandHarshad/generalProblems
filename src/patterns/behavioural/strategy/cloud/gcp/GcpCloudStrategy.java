package patterns.behavioural.strategy.cloud.gcp;

import patterns.behavioural.strategy.cloud.CloudStrategy;

public class GcpCloudStrategy implements CloudStrategy {

    @Override
    public void downloadFile() {
        System.out.println("Downloading file from AWS");
    }
}