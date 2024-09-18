package g58399.ATLIR4.model;

import g58399.ATLIR4.designPattern.command.CommandAddMur;
import g58399.ATLIR4.designPattern.strategie.EasyMode;
import g58399.ATLIR4.designPattern.strategie.HardMode;
import g58399.ATLIR4.designPattern.command.Command;
import g58399.ATLIR4.designPattern.command.CommandAddPiece;
import g58399.ATLIR4.designPattern.command.CommandManages;
import g58399.ATLIR4.view.javaFX.sceneGame.GamePane;
import org.jetbrains.annotations.NotNull;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private final PropertyChangeSupport observables;
    private final Board board;
    private final Player white;
    private final Player black;
    private Player currentPlayer;
    private GameState state;
    private CommandManages commandManages;

    /**
     * Here the constructor will initialize the white and black player and will
     * create an empty board.
     */
    public Game(CommandManages manages) {
        this.white = new Player(Color.WHITE);
        this.black = new Player(Color.BLACK);
        this.board = new Board();
        this.commandManages = manages;
        this.observables = new PropertyChangeSupport(this);
    }

    public void addObserver(GamePane view){
        observables.addPropertyChangeListener(view);
    }
    public Board getBoard() {
        return board;
    }
    public CommandManages getComManages(){
        return this.commandManages;
    }
    public void setCurrentPlayer(){
        this.currentPlayer = getOppositePlayer();
    }
    public void setState( @NotNull GameState state) {
        this.state = state;
    }

    public List<Position> getAllPossibleAddMur(){
        return Mur.getPossibleAdd(this.board);
    }


    /**
     * This methode it's use such as a computeur
     * opponent. The reflexion of this IA is to calculte which move can capture most pawn.
     * And make the move if it's possible.
     */
    public void IAHardAction(){
        List<Position> allPositionMoves = getAllPossibleMoves();
        HardMode hardMode = new HardMode(allPositionMoves, this);
        Position bestPosition = hardMode.IaAction();
        if(isValidMove(Objects.requireNonNull(bestPosition))) {
            setMovePawn(bestPosition);
            //capturePawns(bestPosition);
        }
    }

    /**
     * This methode it's use such as a computeur
     * opponent. The reflexion of this IA is to choose one position
     * into a list of all position move possible whit a random index.
     * And make the move if it's possible.
     */

    public void IASimpleAction(){
        List<Position> allPositionMoves = getAllPossibleMoves();
        EasyMode easyMode = new EasyMode(allPositionMoves);
        Position bestPosition = easyMode.IaAction();
        if(isValidMove((bestPosition))) {
            setMovePawn(bestPosition);
        }
    }

    /**
     * Get the piece of the board located on a given position
     * @param pos the position
     * @return the piece located at P
     * @throws IllegalArgumentException pos is not located on the board.
     */
    public Piece getPiece(@NotNull Position pos) {
        if (!board.contains(pos)) {
            throw new IllegalArgumentException("The position is outside the board.");
        }
        return board.getPiece(pos);
    }

    /**
     * Getter for the current player. Getter pour le joueur actuel.
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    /**
     * Getter for the current player. Getter pour le joueur actuel.
     * @return the current player.
     */
    public Player getOppositePlayer() {
        return (currentPlayer.getColor().opposite() == Color.BLACK) ? black : white;
    }
    /**
     *
     * @return The state of the game at all times.
     */
    public GameState getState() {
        return this.state;
    }

    /**
     * Get the possible moves for the piece located at the specified position.
     * @return the liste of admissible positions.
     */
    public List<Position> getAllPossibleMoves() {
        /*
        * Go through the board square by square and if the piece inside is a piece
        * of the current color it will see all the positions that this piece can do
        * and add all the positions to the list.
        */
        List<Position> allPossibleMoves = new ArrayList<>();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int column = 0; column < Board.BOARD_SIZE; column++) {
                Position pos = new Position(row, column);
                if(this.board.contains(pos) && this.board.getPiece(pos) != null){
                    Piece piece = this.board.getPiece(pos);
                    allPossibleMoves.addAll(piece.getPossibleMoves(pos,this.board, this.currentPlayer.getColor()));
                }
            }
        }
        return allPossibleMoves;

    }

    /**
     * @return If player can't move (if allpossibleMoves is empty).
     */
    public boolean noMovePlayer(){
        List<Position> allPossibleMoves = getAllPossibleMoves();
        return allPossibleMoves.isEmpty();
    }

    /**
     * Start the game: create the pieces and put them on the board, initialize
     * the current player to 'WHITE'.
     */
    public void start() {
        this.currentPlayer = this.black;
        this.state = GameState.PLAY;

        Piece pieceBlack = new Piece(Color.BLACK);
        Position posPieceBlack = new Position(3, 4);
        this.board.setPiece(pieceBlack, posPieceBlack);

        Piece pieceBlack2 = new Piece(Color.BLACK);
        Position posPieceBlack2 = new Position(4, 3);
        this.board.setPiece(pieceBlack2, posPieceBlack2);

        for(int i = 0; i <= 1 ; i ++) {
            Piece pieceWhite = new Piece(Color.WHITE);
            Position posPieceWhite = new Position(3 + i, 3 + i);
            this.board.setPiece(pieceWhite, posPieceWhite);
        }

        this.observables.firePropertyChange("gameStarted", null, board);

    }

    /**
     * @param pos is the position choose to verificate if is possible move to this position.
     * @return if the position is on the list of all possible move of all piece on board.
     */
    public boolean isValidMove(Position pos) {
        List<Position> allPossibleMoves = getAllPossibleMoves();
        return pos != null && this.board.contains(pos) && this.board.isFree(pos) && allPossibleMoves.contains(pos);
    }
    public boolean isValidAdd(Position pos){
        List<Position> allPossibleAdd = getAllPossibleAddMur();
        return pos != null && this.board.contains(pos) && this.board.isFree(pos) && allPossibleAdd.contains(pos);
    }

    /**
     * @param position : create a piece on this position.
     */
    public void setMovePawn(@NotNull Position position){
        Command commandAddPiece = new CommandAddPiece(this, position);
        this.commandManages.doCommande(commandAddPiece);
        this.observables.firePropertyChange("boardChanged", null, board);
    }
    public void addMur(@NotNull Position position){
        Command commandAddMur = new CommandAddMur(this, position);
        this.commandManages.doCommande(commandAddMur);
        this.observables.firePropertyChange("boardChanged", null, board);
    }

    public void undoMovePawn(@NotNull Position position){
        this.board.dropPiece(position);
        this.observables.firePropertyChange("boardChanged", null, board);
    }

    /**
     * This function capture all piece on the board after the move.
     * @param positionPiece
     */
    public void capturePawns(@NotNull Position positionPiece){
        // call getPossiblesCapture and change the piece of the opponent to a piece of player.
        Piece piece = getPiece(positionPiece);
            List<Position> AllCapturesPawns = piece.getPossiblesCaptures(positionPiece, this.board, this.currentPlayer);
            if (AllCapturesPawns.size() != 0) {
                for (Position positionList : AllCapturesPawns) {
                    Piece pieceList = this.board.getPiece(positionList);
                    if (pieceList != null && pieceList.getColor() != this.currentPlayer.getColor()) {
                        pieceList.setColor(this.currentPlayer.getColor());
                    }
                }
            }
            this.setCurrentPlayer();
        this.observables.firePropertyChange("boardChanged", null, board);
    }

    /**
     * @return if the partie is end or not.
     */
    public boolean isGameOver(){
        int totalPiece = this.getCurrentPlayer().getScore() + this.getOppositePlayer().getScore();
        if(totalPiece == 64){
            return true;
        }
        //as long as there are empty squares on the board
        //the game continues and therefore isGameOver is false
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int column = 0; column < Board.BOARD_SIZE; column++) {
                Position pos = new Position(row, column);
                if(this.board.getPiece(pos) == null){
                    return false;
                }
            }
        }
        return true;
    }

    public void updateScorePlayer(Player playerCurrent, Player playerOpponent){
        int scoreCurrent = 0;
        int scoreOpponent = 0;
        for (int lg = 0; lg < Board.BOARD_SIZE; lg++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Position pos = new Position(lg, col);
                if(this.board.getPiece(pos) != null && !this.board.getPiece(pos).getColor().equals(Color.MUR)) {
                    if (this.board.getPiece(pos).getColor().equals(playerCurrent.getColor())) {
                        scoreCurrent++;
                    }else{
                        scoreOpponent++;
                    }
                }
            }
        }
        playerCurrent.setScore(scoreCurrent);
        playerOpponent.setScore(scoreOpponent);
    }

    /**
     * this function go to count how much they are piece of black on board and how much they are piece of white on board
     * @return wich player had win !
     */
    public Player isWinner() {
        Color winner;
        int numberPawnBlack = 0;
        int numberPawnWhite = 0;
        for (int lg = 0; lg < Board.BOARD_SIZE; lg++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Position pos = new Position(lg, col);
                if(this.board.getPiece(pos) != null){
                    if(this.board.getPiece(pos).getColor().equals(Color.BLACK)){
                        numberPawnBlack++;
                    }else{
                        numberPawnWhite++;
                    }
                }
            }
        }
        if(numberPawnBlack < numberPawnWhite){
            winner = Color.WHITE;
        }else{
            winner = Color.BLACK;
        }
        if(this.currentPlayer.getColor().equals(winner)){
            return this.currentPlayer;
        }else{
            return getOppositePlayer();
        }
    }
}