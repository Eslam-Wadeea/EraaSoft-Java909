
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            readFile();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
    public static void readFile() throws IOException {
        FileReader fr = new FileReader("test.txt");
        fr.close();



    }
}