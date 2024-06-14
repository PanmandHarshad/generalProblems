package patterns.behavioural.strategy.cloud;

import patterns.behavioural.strategy.cloud.aws.AwsCloudStrategy;
import patterns.behavioural.strategy.cloud.gcp.GcpCloudStrategy;
import patterns.behavioural.strategy.cloud.service.CloudServiceContext;

public class Main {
    public static void main(String[] args) {
        int flag = 2;

        CloudServiceContext cloud = new CloudServiceContext();
        CloudStrategy strategy = getFlagBaseStrategy(flag);
        cloud.setStrategy(strategy);
        cloud.executeTheDownload();
    }

    private static CloudStrategy getFlagBaseStrategy(int flag) {
        if (flag == 1) {
            return new AwsCloudStrategy();
        } else {
            return new GcpCloudStrategy();
        }
    }

}
