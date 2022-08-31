package web.model;

public class Car {

    private String model;

    private int series;

    private int wheels;

    public Car() {}

    public Car(String model, int series, int wheels) {
        this.model = model;
        this.series = series;
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "Model='" + model + '\'' +
                ", series=" + series +
                ", wheels=" + wheels;
    }
}
