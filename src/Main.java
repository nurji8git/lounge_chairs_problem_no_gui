import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    static Scanner scanner = new Scanner(System.in);
    static int current_group_id = 1;
    public static void main(String[] args)
    {
        newSituation();
    }

    public static void newSituation()
    {
        current_group_id = 1;
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "##--BEGINNING-OF-DAY--##" + ANSI_RESET);
        System.out.print("Input the number of all chairs: ");
        int number_of_chairs = scanner.nextInt();
        CircularDataStructure all_chairs = new CircularDataStructure(number_of_chairs);
        while(true)
        {
            System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "#####--START-COMMANDS-SECTION--#####" + ANSI_RESET);
            System.out.println("#-Commands-#");
            System.out.println("##-  1 - Serve a new group of customers input");
            System.out.println("##-  2 - Remove a group of customers input");
            System.out.println("##-  3 - Show current state of all chairs input");
            System.out.println("##-  4 - If beginning of working day input");
            System.out.println("##-  5 - If end of working day input");
            System.out.print("Command: ");
            switch(scanner.nextInt())
            {
                case 1:
                    serveNewGroup(all_chairs);
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    break;
                case 2:
                    removeGroup(all_chairs);
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    break;
                case 3:
                    currentStateOfRentableChairs(all_chairs);
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    break;
                case 4:
                    // My solution works for any number of chairs. n is up to 2**32
                    // Every new situation we can input a new number of chairs
                    newSituation();
                case 5:
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    System.out.println("End of day");
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    return;
                default:
                    System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "##--END-COMMANDS-SECTION--##" + ANSI_RESET + "\n");
                    System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + "Choose another command!" + ANSI_RESET);
            }
        }
    }
    public static void serveNewGroup(CircularDataStructure all_chairs)
    {
        System.out.println("##--START--Serving a new group of customers--START--##");
        System.out.print("#--  Input the number of customers in new group: ");
        Customers_Group new_customers_group = new Customers_Group(current_group_id++, scanner.nextInt());
        if(all_chairs.addNewGroup(new_customers_group))
        {
            System.out.println("##--END--The new group of customers with ID: #" + new_customers_group.getGroup_id() + " rented " + new_customers_group.getGroup_size() + " chairs" + "--END--##\n");
        }
        else
        {
            System.out.println("##--END--There is no free chairs for this group of customers--END--##\n");
        }
    }

    public static void removeGroup(CircularDataStructure all_chairs)
    {
        System.out.println("##--START--Removing a group of customers--START--##");
        System.out.println("#--Input the ID of group to remove: --#");
        int index = scanner.nextInt();
        if(all_chairs.removeGroup(index))
        {
            System.out.println("##--END--The group of customers with ID: " + index + " gone--END--##\n");
        }
        else
        {
            System.out.println("##--END--There is no group with ID: " + index + "--END--##\n");
        }
    }

    public static void currentStateOfRentableChairs(CircularDataStructure all_chairs)
    {
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "##--START--Current state of all chairs--START--##" + ANSI_RESET);
        for(Chairs_Group chairs_group: all_chairs.getChairs_circular_list())
        {
            if(chairs_group.isIs_free())
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "@" + ANSI_RESET);
                    System.out.println("chair: free");
                }
            }
            else
            {
                for(int i = 0; i < chairs_group.getGroup_size(); i++)
                {
                    System.out.print(ANSI_RED_BACKGROUND + ANSI_BLACK + "@" + ANSI_RESET);
                    System.out.print("chair: member_of_group: ");
                    System.out.println(chairs_group.getGroup_id());
                }
            }
        }
        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "##--END--Current state of all chairs--END--##" + ANSI_RESET + "\n");
    }
}
