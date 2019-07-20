package me.piggypiglet.moviemanager.registerables.implementations;

import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;

import java.util.Scanner;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleRegisterable extends Registerable {
    @Override
    protected void execute() {
        Task.async(r -> {
            Scanner input = new Scanner(System.in);

            while (true) {
                switch (input.nextLine().toLowerCase()) {
                    case "stop": System.exit(0); break;
                }
            }
        });
    }
}