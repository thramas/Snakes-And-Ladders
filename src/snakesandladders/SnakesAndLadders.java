/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Samarth Khare
 * A simple demonstration of a snakes and ladders game without UI
 */
class Positions {
    int start;
    int end;

    /*
        @param start    The start position for snake or ladder
        @param end      The end position for snake or ladder
    */
    public Positions(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class SnakesAndLadders {

    int snakes,ladders,players;
    int []board;
    int []positionsOfPlayers;
    Positions []positionsOfLadders;
    Positions []positionsOfSnakes;
    String[]nameList;
    
    /*
        A handler function for input of the snake positions on the board
    */
    void handleSnakesInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of snakes");
        snakes = sc.nextInt();
        positionsOfSnakes = new Positions[snakes+1];
        System.out.println("Enter position of snakes");
        for (int i = 0; i < snakes; i++) {
            String str = sc.next();
            List<String> positionList = Arrays.asList(str.split(","));
            int a = Integer.parseInt(positionList.get(0));
            int b = Integer.parseInt(positionList.get(1));
            positionsOfSnakes[i] = new Positions(a, b);
        }
    }
    /*
        A handler function for input of the ladder positions on the board
    */
    void handleLaddersInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of Ladders");
        ladders = sc.nextInt();
        positionsOfLadders = new Positions[ladders+1];
        System.out.println("Enter position of ladders");
        for (int i = 0; i < ladders; i++) {
            String str = sc.next();
            List<String> positionList = Arrays.asList(str.split(","));
            int a = Integer.parseInt(positionList.get(0));
            int b = Integer.parseInt(positionList.get(1));
            positionsOfLadders[i] = new Positions(a, b);
        }
    }
    /*
        A handler function for input of the player positions on the board
    */
    void handlePlayerDataInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players");
        players = sc.nextInt();
        System.out.println("Enter Player names");
        nameList = new String[players+2];
        sc.nextLine();
        for (int i = 1; i <= players; i++) {
            nameList[i] = sc.nextLine();
        }
    }
    /*
        Input Function
    */
    void handleInput() {
        //handles the input for the distribution of snake positions across the game board
        handleSnakesInput();
        //handles the input for distribution of ladder positions across the game board
        handleLaddersInput();
        //handles the input for player demographics
        handlePlayerDataInput();
    }
    /*
        Init function for function demographics
    */
    void initializeBoardDemographics() {
        board = new int[105];
        for (int i = 0; i < 105; i++) board[i] = 0;

        for (int i = 0; i < snakes; i++) board[positionsOfSnakes[i].start] = positionsOfSnakes[i].end;

        for (int i = 0; i < ladders; i++) board[positionsOfLadders[i].start] = positionsOfLadders[i].end;

        //initialize players position.
        positionsOfPlayers = new int[players+1];
        for (int i = 0; i <= players; i++) {
            positionsOfPlayers[i]  = 0;
        }
    }
    
    void gamePlay() {
        Scanner sc = new Scanner(System.in);
        int player = 1;
        int dice;
        System.out.println("Game start");
        while (true) {
            System.out.println("Enter player " + nameList[player] + "'s turn");
            dice = sc.nextInt();

            if (dice < 1 || dice > 6) {
                System.out.println("Invalid Dice Count");
                continue;
            }

            //If a valid roll is present, the player moves to a new position, else passes the turn
            if (dice + positionsOfPlayers[player] <= 100) {

                dice += positionsOfPlayers[player];
                
                //Ladder or Snake check for current position on the board
                if (board[dice] != 0) {
                    positionsOfPlayers[player] = board[dice];
                } else {
                    positionsOfPlayers[player] = dice;
                }
            }

            if (positionsOfPlayers[player] == 100) {
                System.out.println("Player " + nameList[player] + " won");
                return;
            }

            System.out.println("Player Positions");
            for (int i = 1; i <= players; i++) {
                System.out.println(nameList[i] + " -> " + positionsOfPlayers[i]);
            }
            //Next player's turn.
            if (player == players) {
                player = 1;
            } else {
                player++;
            }
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SnakesAndLadders gameObject = new SnakesAndLadders();
        gameObject.handleInput();
        gameObject.initializeBoardDemographics();
        gameObject.gamePlay();
    }   
}