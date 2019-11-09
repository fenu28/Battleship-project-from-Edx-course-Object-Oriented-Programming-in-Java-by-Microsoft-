// Project Dcumentation - https://courses.edx.org/courses/course-v1:Microsoft+DEV277x+3T2019/courseware/76c11a375a0e495e83ab68121566fb12/8f250da826d7405d8fecf99aca3a5e9a/?child=first
// Project Author - Fenil Kamdar
//   No. of computer ships are represented by 'compShips'
//   No.of user ships are represented by 'userShips'
//   'DeployUserShips' method contains code by which user will deploy ships
//   'DeployCompShips' method contains code by which computer will deploy ships
//   'Grid' method displays the map of the game


import java.util.Scanner;
import java.util.Random;
public class Battleship {
    static int compShips=0;
    static int userShips=0;
    static int temp=0;
    static char[][] grid= new char[10][10];// This array stores co-ordinates of ships
    public static void main(String[] args) {

        for(int i=0;i<10;i++)//loop for initializing empty array
        {
            for(int j=0;j<10;j++)
            {
                grid[i][j]= ' ';
            }
        }
        Grid();// display map
        System.out.println("Deploy your ships");
        DeployUserShips();//user deploys ships



        System.out.println("Computer deploying ships");
        DeployCompShips();// computer deploys ships
        // The ships of computer won't be visible to the user
        System.out.println("Your ships are represented by @");
        Grid();/*display map to user before battle.
                User ships are printed t console as '@' but are stored in array as '1'
                */

        System.out.println("Battle starts now");
        
        while(true)/* this loop is used so that user and computer enter co-ordinates to attack.
                        It will run until either of userShips or compShips are 0.*/
        {
            userBattle();
            System.out.println("Computer's move");
            compBattle();
            if(userShips==0) {
                System.out.println("Computer wins");
                break;
            }
            else if(compShips==0)
            {
                System.out.println("You win :)");
                break;
            }
            
        }
        System.out.println("Your Ships: "+userShips+" Computer's ship: "+compShips);
       
        Grid();// display final map after battle ends
    }
    public static void Grid()// this loop works as described in Project Documentation.
    {
        int i,j;
        System.out.println("\n   0123456789");
        for(i=0;i<10;i++)
        {
            System.out.print(i+" |");
            for(j=0;j<10;j++)
            {
                if(grid[i][j]=='1' && userShips==5)
                System.out.print("@");

                else if(grid[i][j]=='2')
                {
                    System.out.print(" ");
                }

                else if(grid[i][j]==' ')
                    System.out.print(grid[i][j]+"");
                else
                    System.out.print(grid[i][j]+"");
            }
            System.out.println("| "+i);
        }
        System.out.println("\n   0123456789");
    }
    public static void DeployUserShips()
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<5;i++) {
            if(userShips==5)
            {
                break;
            }
            System.out.println("Enter X co-ordinate");
            int x = sc.nextInt();
            System.out.println("Enter Y-co-ordinate");
            int y = sc.nextInt();
            if (x > 9 || x < 0 || y > 9 || y < 0) {
                System.out.println("Enter valid co-ordinates");
                DeployUserShips();
            }
            else if (grid[x][y] == ' ') {
                grid[x][y] = '1';
                userShips++;
            } else if (grid[x][y] == '1') {
                System.out.println("One ship is already placed there. Enter co-ordinates again");
                DeployUserShips();

            }
        }
    }
    public static void DeployCompShips()
    {
        Random ship = new Random();
        for(int i=0;i<5;i++) {
            if(compShips==5)
            {
                break;
            }
            int x = ship.nextInt(10);
            int y = ship.nextInt(10);

            if(x>9 || x<0 || y<0 || y>9)
            {
                DeployCompShips();
                // Invalid Co-ordinates

            }
            else if(grid[x][y]=='1')
            {
                DeployCompShips();// As user ships are already deployed there

            }
            else if(grid[x][y]==' ')
            {
                compShips++;
                grid[x][y]='2';
                System.out.println("Ship deployed");
            }
        }

    }
    public static void userBattle()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter X co-ordinates");
        int x = sc.nextInt();
        System.out.println("Enter Y - co-ordinates");
        int y = sc.nextInt();
        if(x>9 || x<0 || y>9 || y<0)
        {
            System.out.println("Enter valid co-ordinates");
            userBattle();

        }
        else if(grid[x][y]==' ')
        {
            System.out.println("You missed");
            grid[x][y]='-';
        }
        else if(grid[x][y]=='2')
        {
            System.out.println("You sunk Computer's ship");
            compShips--;
            grid[x][y]='!';
        }
        else if(grid[x][y]=='1')
        {

            System.out.println("Oh no! You sunk your own ship.");
            userShips--;
            grid[x][y]='x';
        }
        else
            System.out.println("you missed");

    }
    
    public static void compBattle()
    {
        Random sc= new Random();
        int x = sc.nextInt(10);
        int y = sc.nextInt(10);
        if(x>9 || x<0 || y>9 || y<0)
        {
            compBattle();

        }
        
            else if(grid[x][y]=='2')
            {
                System.out.println("Computer sunk its own ship");
                compShips--;
                grid[x][y]='!';
            }
            else if(grid[x][y]=='1')
            {
                System.out.println("Oops! Computer sunk your ship");
                userShips--;
                grid[x][y]='x';
            }
        else
        {
            System.out.println("Computer missed");
        }
            

    }
}
