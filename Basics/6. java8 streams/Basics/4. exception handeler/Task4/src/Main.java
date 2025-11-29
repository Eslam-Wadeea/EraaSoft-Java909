import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int [] myArray = new int [5];
        myArray[0] = 1;
        myArray[1] = 2;
        myArray[2] = 3;
        myArray[3] = 4;
        myArray[4] = 5;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter index");
        int index = sc.nextInt();

        try {
            System.out.println("Value of index " +index +"= "+ myArray[index]);

        }catch (ArrayIndexOutOfBoundsException e){
            if(index<0 || index>4){
                System.out.println("Invalid index");
            }

        }


    }
}