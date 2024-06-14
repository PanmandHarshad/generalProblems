package patterns.creational.builder.computer;

public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3090")
                .build();

        System.out.println(computer.toString());
    }
}
