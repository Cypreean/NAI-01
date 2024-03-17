public class Iris {
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String species;
    private double distance;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String species) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.species = species;
    }
    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String species,double distance) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.species = species;
        this.distance = distance;
    }

    //getters and setters
    public double getSepalLength() {
        return sepalLength;
    }
    public double getSepalWidth() {
        return sepalWidth;
    }
    public double getPetalLength() {
        return petalLength;
    }
    public double getPetalWidth() {
        return petalWidth;
    }
    public String getSpecies() {
        return species;
    }
    public double getDistance() {
        return distance;
    }
    public String toString() {
        return "Iris{" +
                "sepalLength=" + sepalLength +
                ", sepalWidth=" + sepalWidth +
                ", petalLength=" + petalLength +
                ", petalWidth=" + petalWidth +
                ", species='" + species + '\'' +
                '}';
    }


}