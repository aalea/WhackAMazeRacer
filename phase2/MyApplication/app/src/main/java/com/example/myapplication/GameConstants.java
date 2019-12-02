package com.example.myapplication;

import android.graphics.Color;

public abstract class GameConstants {
    public static final String USERMANAGER = "UserManager";
    public static final String USER_STATS_FILE = "user_stats.txt";
    public static final String USER_FILE = "user_data.txt";
    public static final String update = "update";
    public static final String set = "set";
    public static final String getPassword = "getPassword";
    public static final String changePassword = "changePassword";
    public static final int defaultLevel = 0;

    //In-app purchase Constants
    public static final int pointsFromCollectingGem = 10;

    // Whack-A-Mole Game Constants
    public static final int moleDefaultLives = 5;
    public static final int moleDefaultHolesX = 2;
    public static final int moleDefaultHolesY = 2;
    public static final int moleDefaultDuration = 2400;
    public static final int molePowerPlayCost = 10;
    public static final int powerPlayPoint = 10;
    public static final int moleEasyLives = 10;
    public static final int moleNormalLives = 5;
    public static final int moleDifficultLives = 3;
    public static final int moleHardcoreLives = 1;
    public static final int moleFirstSpeedUp = 10;
    public static final int moleSecondSpeedUp = 20;
    public static final int moleFirstSpeed = 1500;
    public static final int moleSecondSpeed = 600;
    public static final int gemMoleValue = 5;
    public static final int nonGemMoleValue = 0;
    public static final int moleValue = 1;
    public static final int doubleMoleValue = 2;
    public static final int negativeMoleValue = -1;
    public static final int whackAMoleLevel = 1;

    // TypeRacer Game Constants
    public static final int numOfQuestions = 5;
    public static final double goldenQuestionFrequency = 0.4;
    public static final int regularQuestionPoint = 1;
    public static final int goldenQuestionPoint = 5;
    public static final long timeLimitInMills = 30000;
    public static final int backGroundDefault = Color.WHITE;
    public static final int textColorDefault = Color.BLACK;
    public static final int difficultyDefault = 5;
    public static final int minLife = 1;
    public static final int maxLife = 5;
    public static final int typeRacerLevel = 2;


    // Maze Game Constants
    public static final int TotalMazeGames = 2;
    public static final int MazeWallThickness = 4;
    public static final int NumberOfMazeCollectibles = 3;
    public static final int PointsForACollectible = 10;
    public static final int IndexOfColsInFile = 3;
    public static final int IndexOfRowsInFile = 2;
    public static final int IndexOfBgColorInFile = 0;
    public static final int IndexOfPlayerTypeInFile = 1;
    public static final int IndexOfPlayerColInFile = 5;
    public static final int IndexOfPlayerRowInFile = 4;
    public static final int StartingIndexOfMazeMapInFile = 6;
    public static final int RowsForEasyMaze = 7;
    public static final int ColsForEasyMaze = 5;
    public static final int RowsForNormalMaze = 11;
    public static final int ColsForNormalMaze = 7;
    public static final int RowsForHardMaze = 15;
    public static final int ColsForHardMaze = 12;
    public static final int mazeLevel = 3;

    // 3 stats for user and 6 stats for the games
    public static final int TOTAL_NUM_OF_STATISTICS = 9;
    public static final String gameName = "name";
    public static final String moleName = "mole";
    public static final String racerName = "racer";
    public static final String mazeNameForIntent = "maze";
    public static final int NumPeopleOnScoreBoard = 15;
    public static final String WHACK_A_MOLE = "Whack-A-Mole";
    public static final String TYPE_RACER = "TypeRacer";
    public static final String MAZE = "Maze";
    public static final String MoleHit = "MoleHit";
    public static final String MoleStats = "MoleStats";
    public static final String MoleScore = "MoleScore"; //this is key for updating overall score
    public static final String MoleHigh = "MoleHigh"; //this is key for moleAllTimeHigh
    public static final String MoleAllTimeHigh = "MoleAllTimeHigh";
    public static final String NumMazeGamesPlayed = "NumMazeGamesPlayed";
    public static final String TypeRacerStreak = "TypeRacerStreak";
    public static final String NumCollectiblesCollectedMaze = "NumCollectiblesCollectedMaze";
    public static final String[] WhackAMoleStatistics = new String[]{MoleStats, MoleHit, MoleAllTimeHigh};
    public static final String[]  TypeRacerStatistics = new String[]{TypeRacerStreak};
    public static final String[]  MazeStatistics = new String[]{NumMazeGamesPlayed, NumCollectiblesCollectedMaze};
    public static String[] GameNames = new String[]{WHACK_A_MOLE, TYPE_RACER, MAZE};

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public static String[] getArrayOfStatistics(String GameName){
        switch (GameName){
            case WHACK_A_MOLE:
                return WhackAMoleStatistics;
            case TYPE_RACER:
                return TypeRacerStatistics;
            case MAZE:
                return MazeStatistics;
        }
        return new String[]{};
    }

    public static int countOccurrences(String line, char character) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }

}
