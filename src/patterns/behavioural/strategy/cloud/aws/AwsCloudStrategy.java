package patterns.behavioural.strategy.cloud.aws;

import patterns.behavioural.strategy.cloud.CloudStrategy;

public class AwsCloudStrategy implements CloudStrategy {

    @Override
    public void downloadFile(){
        System.out.println("Downloading file from AWS");
    }
}

