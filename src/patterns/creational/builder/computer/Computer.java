package patterns.creational.builder.computer;

public class Computer {
    private String CPU;
    private String RAM;
    private String GPU;
    private String storage;

    private Computer(String CPU, String RAM, String GPU, String storage) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.GPU = GPU;
        this.storage = storage;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String GPU;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(CPU, RAM, GPU, storage);
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", GPU='" + GPU + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
