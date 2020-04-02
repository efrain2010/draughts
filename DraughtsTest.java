
public class DraughtsTest {

    public static void main(String[] args) {

        Player player1 = new Player("Efra", 1);
        Player player2 = new Player("Grac", 2);

        Board board = new Board(player1, player2);
        board.setVisible(true);
    }

}