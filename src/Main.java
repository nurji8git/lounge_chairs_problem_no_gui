import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number of all chairs: ");
        CircularDataStructure all_chairs = new CircularDataStructure(scanner.nextInt());
        currentStateOfRentableChairs(all_chairs);
    }

    public static void currentStateOfRentableChairs(CircularDataStructure all_chairs)
    {
        for(Chairs_Group chairs_group: all_chairs.getChairs_circular_list())
        {
            if(chairs_group.isIs_free())
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.println("free");
                }
            }
            else
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.println(chairs_group.getGroup_id());
                }
            }
        }
    }
}
