/**
Ojas Kandhare
CSE 122

MusicPlaylist allows users to manage a playlist.
Users can add songs, play songs, view playlist, check/modify history, and interact with console.
Playlist is built using insane fields and avoids forbidden feaftures.
*/

import java.util.*;
import java.io.*;

public class MusicPlaylist {
    //Scanner for all console input
    private static final Scanner CONSOLE = new Scanner(System.in);

    //Queue holding songs
    private static final Queue<String> playlist = new LinkedList<>();

    //Stack holding listening history (reverse order)
    private static final Stack<String> history = new Stack<>();

    /**
    Main method: entry point that starts playlist application.

    B: Creates MusicPlaylist object and begins user interaction loop.
    E: None
    R: None
    P: None
    */
    public static void main(String[] args) {
        new MusicPlaylist().run();
    }


    public void run() {
        System.out.println("Welcome to the CSE 122 Music Playlist!");
        boolean quit = false;
        while (!quit) {
            printMenu();
            String choice = CONSOLE.nextLine();

            if (choice.equalsIgnoreCase("q")) {
                quit = true; //breaks
            } else if (choice.equalsIgnoreCase("a")) {
                addSong();
            } else if (choice.equalsIgnoreCase("p")) {
                playSong();
            } else if (choice.equalsIgnoreCase("h")) {
                printHistory();
            } else if (choice.equalsIgnoreCase("c")) {
                clearHistory();
            } else if (choice.equalsIgnoreCase("d")) {
                deleteHistory();
            } else if (choice.equalsIgnoreCase("v")) {
                viewPlaylist();
            }

        }
    }

    /**
    Displays menu of available commands to user 

    B: Prints options user can select from (letter)
    E: None
    R: None
    P: None
    */

    private static void printMenu() {
        System.out.println("(A) Add song");
        System.out.println("(P) Play song");
        System.out.println("(H) Print history");
        System.out.println("(V) View playlist");
        System.out.println("(C) Clear history");
        System.out.println("(D) Delete from history");
        System.out.println("(Q) Quit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    
    /**
    Adds song to playlist queue.

    B: Prompts user to enter song name nad adds it to queue.
    E: None 
    R: None
    P: None 
    */ 

    private static void addSong() {
        System.out.print("Enter song name: ");
        String song = CONSOLE.nextLine().trim();
        playlist.add(song);
        System.out.println("Successfully added " + song);
        System.out.println();
    }

    /**
    Plays next song from playlist and stores it in listening history.

    B: Removes next song from queue and pushes it onto history stack.
    E: Throws IllegalStateException if playlist is empty 
    R: None 
    P: None
    */ 

    private static void playSong() {
        if (playlist.isEmpty()) {
            throw new IllegalStateException();
        }
        String nowPlaying = playlist.remove();
        history.push(nowPlaying);
        System.out.println("Playing song: " + nowPlaying);
    }

    /**
    Prints listening historyh in reverse order of listening (latest first)

    B: Outputs all songs in history without modifying stack 
    E: Throws IllegalStateException if history is empty 
    R: None 
    P: None 
    */
    private static void printHistory() {
        if (history.isEmpty()) {
            throw new IllegalStateException();
        }

        Stack<String> temp = new Stack<>();

        while (!history.isEmpty()) {
            String song = history.pop();
            System.out.println("    " + song);
            temp.push(song);
        }

        while (!temp.isEmpty()) {
            history.push(temp.pop());
        }
    }

    /**
    Views current playlist using only queue operations 

    B: Prints songs currently in playlist queue without changing queue 
    E: Throws IllegalStateException if playlist is empty 
    R: None 
    P: None 
    */ 

    private static void viewPlaylist() {
        if (playlist.isEmpty()) {
            throw new IllegalStateException();
        } 
        for (String song : playlist) {
            System.out.println(song);
        }
    }

    /**
    Clears entire history stack

    B: Empties history stack completely by popping 
    E: None 
    R: None 
    P: None 
    */

    private static void clearHistory() {
        while(!history.isEmpty()) {
            history.pop();
        }
    }

    /**
    Deletes songs from history stack based on user input.

    B: Deletes a specified number of songs from most recent/earliest history 
    E: Throws IllegalArgumentException if input exceeds history size 
    R: None 
    P: None 
    */

    private static void deleteHistory() {
        if (history.isEmpty()) {
            //System.out.println("A positive number will delete from recent history.");
            //System.out.println("A negative number will delete from the beginning of history.");

        }

        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");


        System.out.print("Enter number of songs to delete: ");
        int n;
        try {
            n = Integer.parseInt(CONSOLE.nextLine().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid integer.");
            return;
        }

        if (n == 0) {
            return; 
        }

        if (Math.abs(n) > history.size()) {
            throw new IllegalArgumentException();
                                                
        }

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                history.pop();
            }

        } else {
            int k = Math.abs(n);
            Stack<String> temp = new Stack<>();

            while (!history.isEmpty()) {
                temp.push(history.pop());
            }

            for (int i = 0; i < k; i++) {
                temp.pop();
            }

            while (!temp.isEmpty()) {
                history.push(temp.pop());
            }
        }
    }

    /**
    Copies and prints contents of stack without modifying order.

    B: Pops each element into new temp stack for printing, then restores original
    E: None
    R: None 
    P: stack to be printed and restored
    */
    private void copyAndPrintStack(Stack<String> stack) {
        Stack<String> temp = new Stack<>();
        while (!stack.isEmpty()) {
            String song = stack.pop();
            System.out.println("   " + song);
            temp.push(song);
        }
        
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
        //System.out.println("Deleted " + Math.abs(n) + " song(s) from history.");
    }
