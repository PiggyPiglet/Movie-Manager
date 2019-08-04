package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.mysql.implementations.MoviesTable;
import me.piggypiglet.moviemanager.objects.Movie;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;

import java.util.Scanner;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleRegisterable extends Registerable {
    @Inject private MoviesTable moviesTable;

    @Override
    protected void execute() {
        Task.async(r -> {
            Scanner input = new Scanner(System.in);

            while (true) {
                switch (input.nextLine().toLowerCase()) {
                    case "stop": System.exit(0); break;
                    case "add-movie":
                        moviesTable.addMovie(new Movie("test", "test", "test", "test", "null"));
                        break;
                }
            }
        });
    }
}
