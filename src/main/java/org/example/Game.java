package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private final int countPlayTime = 1000;
    private Map<Integer, String> results;
    private final int countDoors = 3;


    public Game() {
        results = new HashMap<>();
        for (int i = 1; i <= countPlayTime; i++) {
            boolean win = playMontyHallGame();
            results.put(i, win ? "Победа" : "Поражение");
        }

        // Вывод статистики по победам и поражениям
        double wins = 0;
        for (String result : results.values()) {
            if (result.equals("Победа")) {
                wins++;
            }
        }
        int procentWin = (int) (wins / countPlayTime * 100);
        int losses = 100 - procentWin;
        System.out.println("Статистика после " + countPlayTime + " тестов при наличии " + countDoors + " дверей: ");
        System.out.println("Побед: " + procentWin + "%");
        System.out.println("Поражений: " + losses + "%");
    }


    private boolean playMontyHallGame() {
        Random random = new Random();

        // Имитация выбора одной из трех дверей
        int chosenDoor = random.nextInt(countDoors) + 1; // от 1 до 3 вкл

        // Определение, за какой дверью находится приз
        int prizeDoor = random.nextInt(countDoors) + 1;

        // непонятно нужно это или нет????
        if (chosenDoor == prizeDoor) {
            return true;
        }

        // Ведущий открывает одну из дверей, за которой нет приза
        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3) + 1;
        } while (revealedDoor == chosenDoor || revealedDoor == prizeDoor);

        // Игрок принимает решение изменить свой выбор или оставить его неизменным
        boolean switchDoor = random.nextBoolean();

        // Если игрок меняет выбор, меняем дверь
        if (switchDoor) {
            do {
                chosenDoor = random.nextInt(3) + 1;
            } while (chosenDoor == revealedDoor);
        }

        // Возвращаем true, если игрок выбрал дверь с призом
        return chosenDoor == prizeDoor;
    }

}