class AquariumJava {

    private int mTemperature;

    public AquariumJava() {}

    public int getTemperature() {
        return mTemperature;
    }

    public void setTemperature(int mTemperature) {
        this.mTemperature = mTemperature;
    }

    @Override
    public String toString() {
        return "Aquarium{" +
                "mTemperature=" + mTemperature +
                '}';
    }

    public static void main(String[] args) {
        AquariumJava aq = new AquariumJava();
        aq.setTemperature(25);
        System.out.println(aq.getTemperature());
    }
}