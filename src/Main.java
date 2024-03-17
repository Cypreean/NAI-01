import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("czy chcesz wprowadzic wlasne k? (t/n)");
        Scanner scanner2 = new Scanner(System.in);
        String input2 = scanner2.nextLine();
        boolean fromKeyboard2 = input2.equals("t");
        int k = 3;
        if (fromKeyboard2){
            System.out.println("Podaj k: ");
            Scanner scanner3 = new Scanner(System.in);
            k = scanner3.nextInt();

        }

        System.out.println("Czy chcesz użyć właśnych danych? (t/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean fromKeyboard = input.equals("t");
        if (input.equals("t")) {
            BufferedReader reader = new BufferedReader(new FileReader("wlasnedane.csv"));
            BufferedReader reader2 = new BufferedReader(new FileReader("IRIS.csv"));
            Iris[] trainingData = new Iris[150];
            reader2.readLine();
            for (int i = 0; i < 150; i++) {
                String line = reader2.readLine();
                String[] row = line.split(",");
                double sepalLength = Double.parseDouble(row[0]);
                double sepalWidth = Double.parseDouble(row[1]);
                double petalLength = Double.parseDouble(row[2]);
                double petalWidth = Double.parseDouble(row[3]);
                String species = row[4];
                trainingData[i] = new Iris(sepalLength, sepalWidth, petalLength, petalWidth, species);
            }
            Iris[] testingData = new Iris[150];
            reader.readLine();
            int liczba = 0;
            for (int i = 0; i < 150; i++) {
                String line = reader.readLine();
                if (line == null) {
                    liczba = i;
                    break;

                }
                String[] row = line.split(",");
                double sepalLength = Double.parseDouble(row[0]);
                double sepalWidth = Double.parseDouble(row[1]);
                double petalLength = Double.parseDouble(row[2]);
                double petalWidth = Double.parseDouble(row[3]);
                String species = row[4];
                testingData[i] = new Iris(sepalLength, sepalWidth, petalLength, petalWidth, species);
            }
            testingData = Arrays.copyOf(testingData, liczba);

            calculatedistance(trainingData, testingData, k);


        } else {


            BufferedReader reader = new BufferedReader(new FileReader("IRIS.csv"));
            Iris[] irisData = new Iris[150];
            Iris[] setosa = new Iris[50];
            Iris[] versicolor = new Iris[50];
            Iris[] virginica = new Iris[50];
            String line;
            int index = 0;
            int setosaIndex = 0;
            int versicolorIndex = 0;
            int virginicaIndex = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                double sepalLength = Double.parseDouble(row[0]);
                double sepalWidth = Double.parseDouble(row[1]);
                double petalLength = Double.parseDouble(row[2]);
                double petalWidth = Double.parseDouble(row[3]);
                String species = row[4];
                Iris iris = new Iris(sepalLength, sepalWidth, petalLength, petalWidth, species);
                irisData[index++] = iris;
                switch (species) {
                    case "Iris-setosa":
                        setosa[setosaIndex++] = iris;

                        break;
                    case "Iris-versicolor":
                        versicolor[versicolorIndex++] = iris;
                        break;
                    case "Iris-virginica":
                        virginica[virginicaIndex++] = iris;
                        break;
                }
            }


            Random rand = new Random();
            shuffleArray(setosa, rand);
            shuffleArray(versicolor, rand);
            shuffleArray(virginica, rand);


            Iris[] testingData = new Iris[30];
            System.arraycopy(setosa, 0, testingData, 0, 10);
            System.arraycopy(versicolor, 0, testingData, 10, 10);
            System.arraycopy(virginica, 0, testingData, 20, 10);


            Iris[] trainingData = new Iris[120];
            System.arraycopy(setosa, 10, trainingData, 0, 40);
            System.arraycopy(versicolor, 10, trainingData, 40, 40);
            System.arraycopy(virginica, 10, trainingData, 80, 40);


            System.out.println("\n\nTraining data:");
            printData(trainingData);

            System.out.println("\n\nTesting data:");
            printData(testingData);
            calculatedistance(trainingData, testingData, k);
        }
    }

    private static void shuffleArray(Iris[] array, Random rand) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = rand.nextInt(array.length);
            Iris temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
    private static void printData(Iris[] data) {
        for (Iris iris : data) {
            System.out.println(iris);
        }
    }
    private static void calculatedistance (Iris[]trainingdata, Iris[]testingdata, int k){
        int [] wyniki = new int[testingdata.length];
        for ( int i = 0; i< testingdata.length;i++){
            Iris[]distance = new Iris[trainingdata.length];
            for (int j =0; j<trainingdata.length;j++){
                double dystans = Math.sqrt(Math.pow(testingdata[i].getSepalLength()-trainingdata[j].getSepalLength(),2)+
                        Math.pow(testingdata[i].getSepalWidth()-trainingdata[j].getSepalWidth(),2)+
                        Math.pow(testingdata[i].getPetalLength()-trainingdata[j].getPetalLength(),2)+
                        Math.pow(testingdata[i].getPetalWidth()-trainingdata[j].getPetalWidth(),2));
                distance[j] = new Iris(trainingdata[j].getSepalLength(),trainingdata[j].getSepalWidth(),trainingdata[j].getPetalLength(),trainingdata[j].getPetalWidth(),trainingdata[j].getSpecies(),dystans);
            }


            for (int j = 0; j<trainingdata.length;j++){
                for (int l = 0; l<trainingdata.length-1;l++){
                    if (distance[l].getDistance()>distance[l+1].getDistance()){
                        Iris temp = distance[l];
                        distance[l] = distance[l+1];
                        distance[l+1] = temp;
                    }
                }
            }


            int dobre=0;
            for (int j = 0; j<k;j++){

               if (distance[j].getSpecies().equals(testingdata[i].getSpecies())){
                   dobre++;
               }
            }
            double wynik = (double)dobre/k;
            if (wynik>0.5){
                wyniki[i]=1;
            }
            else {
                wyniki[i]=0;
            }
        }
        double dokladnosc = 0;
        for (int i = 0; i<wyniki.length;i++){
            dokladnosc+=wyniki[i];
        }
        dokladnosc = dokladnosc/wyniki.length;
        System.out.println("Dokladnosc: "+dokladnosc*100 + "%" );
    }
}