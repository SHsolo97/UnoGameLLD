package org.example;

import org.example.service.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players [2-4] : ");
        int n = sc.nextInt();
        if(n<2 || n>4){
            System.out.println("The number of player must be in the range [2,4].");
            return;
        }
        List<String> playerNames = new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.println("Enter the name of player " + (i+1) + " : ");
            playerNames.add(sc.next());
        }
        GameService gameService = new GameService(playerNames);
        gameService.play(sc);
    }
}