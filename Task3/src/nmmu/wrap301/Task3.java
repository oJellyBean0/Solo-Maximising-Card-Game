package nmmu.wrap301;

/**
 * Created by s2133 on 2017/02/25.
 */
public class Task3 {
    static Game newGame = new Game();

    public static void main(String[] args) {
        Menu mainMenu = buildMenu();

        mainMenu.run();
    }


    /**
     * The menu is built here with all the functionality
     * @return The whole menu is returned
     */
    private static Menu buildMenu(){
        Menu mainMenu = new Menu("Play game", false);


        mainMenu.addChoice(new Pair<>("New Game ", () -> newGame.runGame()));
        mainMenu.addChoice(new Pair<>("Load Game", () -> newGame.load()));


        return  mainMenu;
    }


}
