import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int current_group_id = 1;
    public static void main(String[] args)
    {
        newSituation();
    }

    public static void newSituation()
    {
        current_group_id = 1;
        System.out.println("#####--BEGINNING-OF-DAY--#####");
        System.out.print("Input the number of all chairs: ");
        int number_of_chairs = scanner.nextInt();
        CircularDataStructure all_chairs = new CircularDataStructure(number_of_chairs);
        while(true)
        {
            System.out.println("#####--START-COMMANDS-SECTION--#####");
            System.out.println("#-Commands-#");
            System.out.println("##-  Serve a new group of customers input:   1");
            System.out.println("##-  Remove a group of customers input:      2");
            System.out.println("##-  Show current state of all chairs input: 3");
            System.out.println("##-  If beginning of working day input:      4");
            System.out.println("##-  If end of working day input:            5");
            System.out.print("Command: ");
            switch(scanner.nextInt())
            {
                case 1:
                    serveNewGroup(all_chairs);
                    System.out.println("#####--END-COMMANDS-SECTION--#####\n");
                    break;
                case 2:
                    removeGroup(all_chairs);
                    System.out.println("#####--END-COMMANDS-SECTION--#####\n");
                    break;
                case 3:
                    currentStateOfRentableChairs(all_chairs);
                    System.out.println("#####--END-COMMANDS-SECTION--#####\n");
                    break;
                case 4:
                    // My solution works for any number of chairs. n is up to 2**32
                    // Every new situation we can input a new number of chairs
                    newSituation();
                case 5:
                    System.out.println("#####--END-COMMANDS-SECTION--#####\n");
                    System.out.println("End of day");
                    System.out.println("#####--END-OF-DAY--#####\n");
                    return;
                default:
                    System.out.println("#####--END-COMMANDS-SECTION--#####\n");
                    System.out.println("Choose another command!");
            }
        }
    }
    public static void serveNewGroup(CircularDataStructure all_chairs)
    {
        System.out.println("#####--START--Serving a new group of customers--START--#####");
        System.out.println("#--Input the number of customers in new group: --#");
        Customers_Group new_customers_group = new Customers_Group(current_group_id++, scanner.nextInt());
        if(all_chairs.addNewGroup(new_customers_group))
        {
            System.out.println("#####--END--The new group of customers with ID: #" + new_customers_group.getGroup_id() + " rented " + new_customers_group.getGroup_size() + " chairs" + "--END--#####\n");
        }
        else
        {
            System.out.println("#####--END--There is no free chairs for this group of customers--END--#####\n");
        }
    }

    public static void removeGroup(CircularDataStructure all_chairs)
    {
        System.out.println("#####--START--Removing a group of customers--START--#####");
        System.out.println("#--Input the ID of group to remove: --#");
        int index = scanner.nextInt();
        if(all_chairs.removeGroup(index))
        {
            System.out.println("#####--END--The group of customers with ID: " + index + " gone--END--#####\n");
        }
        else
        {
            System.out.println("#####--END--There is no group with ID: " + index + "--END--#####\n");
        }
    }

    public static void currentStateOfRentableChairs(CircularDataStructure all_chairs)
    {
        System.out.println("#####--START--Current state of all chairs--START--#####");
        for(Chairs_Group chairs_group: all_chairs.getChairs_circular_list())
        {
            if(chairs_group.isIs_free())
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.println("chair: free");
                }
            }
            else
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.print("chair: member_of_group: ");
                    System.out.println(chairs_group.getGroup_id());
                }
            }
        }
        System.out.println("#####--END--Current state of all chairs--END--#####\n");
    }
}
