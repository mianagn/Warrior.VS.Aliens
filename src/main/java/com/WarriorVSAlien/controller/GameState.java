package main.java.com.WarriorVSAlien.controller;

import main.java.com.WarriorVSAlien.model.characters.Alien;
import main.java.com.WarriorVSAlien.model.characters.CharacterDomain;
import main.java.com.WarriorVSAlien.model.characters.Human;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private CharacterDomain player;
    private List<CharacterDomain> enemies;
    private boolean started;
    private boolean gameOver;
    private int currentEnemyIndex;

    public GameState(String playerName){
        this.player = new Human(playerName);

        this.enemies = new ArrayList<>();
        this.enemies.add(new Alien("Zorath the Conqueror"));
        this.enemies.add(new Alien("Xyloxis the Voidbringer"));
        this.enemies.add(new Alien("Kaldrix the Devourer"));
        this.currentEnemyIndex = 0;
        this.gameOver = false;
    }

    public CharacterDomain getPlayer() {
        return player;
    }

    public void setPlayer(CharacterDomain player) {
        this.player = player;
    }

    public List<CharacterDomain> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<CharacterDomain> enemies) {
        this.enemies = enemies;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }

    public void setCurrentEnemyIndex(int currentEnemyIndex) {
        this.currentEnemyIndex = currentEnemyIndex;
    }


}
