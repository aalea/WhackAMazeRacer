package com.example.myapplication.UserInfo;

import android.content.Context;

import com.example.myapplication.GameConstants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ReadAndUpdate implements Serializable {

    /**
     *
     * @param context of the device
     * @param user whose statistics need to be set or updated
     * @param setOrUpdate the string which will decide if the statistics will be set from the file or
     *                   updated to the file. The string should be one one of either the
     *                   string "update" or "set".
     */

    public void setOrUpdateStatistics(Context context, IUser user, String setOrUpdate) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader br = openFileForReading(context, GameConstants.USER_STATS_FILE);
            if (br == null) {
                return;
            }
            String text;

            while ((text = br.readLine()) != null) {
                int indexOfFirstComma = text.indexOf(",");
                String otherUsername = text.substring(0, indexOfFirstComma);
                if (user.getEmail().equals(otherUsername)) {
                    if (setOrUpdate.equals(GameConstants.update)) {
                        String new_text = updateLine(user);
                        sb.append(new_text);
                    } else if (setOrUpdate.equals(GameConstants.set)) {
                        setInfoInLineToUser(text, user);
                        return;
                    }
                } else {
                    sb.append(text).append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        writeStringToFile(context, sb, GameConstants.USER_STATS_FILE);
    }

    /**
     *
     * @param context of the device
     * @param fileName the name of the file which needs to be opened for reading
     * @return the buffered reader with the file input stream to read over the file.
     */

    BufferedReader openFileForReading(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            return new BufferedReader(isr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param context of the device
     * @param sb the string to write/overwrite the file
     * @param FileName the name of the file which will have the contents of sb
     */


    void writeStringToFile(Context context, StringBuilder sb, String FileName) {
        try {
            FileOutputStream fileOut = context.openFileOutput(FileName, MODE_PRIVATE);
            fileOut.write(sb.toString().getBytes());
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param user with the updated statistics
     * @return the new line to be stored in the file with the updated statistics
     */

    private String updateLine(IUser user) {
        return user.getEmail() + ", " + user.getLastPlayedLevel() + ", " + user.getOverallScore()
                + ", " + user.getStatistic(GameConstants.NameGame2, GameConstants.TypeRacerStreak)
                + ", " + user.getStatistic(GameConstants.NameGame3, GameConstants.NumMazeGamesPlayed)
                + ", " + user.getStatistic(GameConstants.NameGame3, GameConstants.NumCollectiblesCollectedMaze)
                + ", " + user.getStatistic(GameConstants.NameGame1, GameConstants.MoleHit)
                + ", " + user.getStatistic(GameConstants.NameGame1, GameConstants.MoleStats)
                + ", " + user.getStatistic(GameConstants.NameGame1, GameConstants.MoleAllTimeHigh)
                + ", " + user.getCurrency() + "\n";
    }

    /**
     * Helper method to transfer the user statistics in the file to the user object
     *
     * @param line in the file
     * @param user whose information needs to be updated
     */
    void setInfoInLineToUser(String line, IUser user) {
        String cleanLine = line.replaceAll("\\s", "");
        int numCommas = GameConstants.countOccurrences(cleanLine, ',');
        String[] userAndGameStats = cleanLine.split(",", numCommas + 1);
        int lastPlayedLevel = Integer.parseInt(userAndGameStats[1]);
        int overallScore = Integer.parseInt(userAndGameStats[2]);
        int streaks = Integer.parseInt(userAndGameStats[3]);
        int numMazeGame = Integer.parseInt(userAndGameStats[4]);
        int numMazeItemsCollected = Integer.parseInt(userAndGameStats[5]);
        int moleHit = Integer.parseInt(userAndGameStats[6]);
        String loadMolesStats = userAndGameStats[7];
        int MoleAllTimeHigh = Integer.parseInt(userAndGameStats[8]);
        int gemsRemaining = Integer.parseInt(userAndGameStats[9]);

        Object[] typeRacer = new Object[]{GameConstants.NameGame2, GameConstants.TypeRacerStreak, streaks};
        Object[] whackAMole = new Object[]{GameConstants.NameGame1, GameConstants.MoleStats,
                loadMolesStats, GameConstants.MoleHit, moleHit, GameConstants.MoleAllTimeHigh, MoleAllTimeHigh};
        Object[] maze = new Object[]{GameConstants.NameGame3, GameConstants.NumMazeGamesPlayed,
                numMazeGame, GameConstants.NumCollectiblesCollectedMaze, numMazeItemsCollected};
        List<Object[]> ListOfGameStats = Arrays.asList(typeRacer, whackAMole, maze);
        ArrayList<Object[]> arrayOfGameStats = new ArrayList<>(ListOfGameStats);
        user.setStatisticsInMap(arrayOfGameStats);
        user.setLastPlayedLevel(lastPlayedLevel);
        user.setOverallScore(overallScore);
        user.setCurrency(gemsRemaining);
    }

    /**
     * @param context     of the device
     * @param username    of the user
     * @param newPassword to be changed
     * @param getOrChange the string which will tell the method if you want to return the password
     *                    or change the password
     * @return the password or return true if the password was successfully changed
     */

    Object getOrChangePassword(Context context, String username, String newPassword, String getOrChange) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = openFileForReading(context, GameConstants.USER_FILE);
            String text;

            while ((text = br.readLine()) != null) {
                int indexOfComma = text.indexOf(",");
                String otherUsername = text.substring(0, indexOfComma);
                if (username.equals(otherUsername)) {
                    int indexOfSpace = text.indexOf(" ");
                    if (getOrChange.equals(GameConstants.getPassword)) {
                        return text.substring(indexOfSpace + 1);
                    } else if (getOrChange.equals(GameConstants.changePassword)) {
                        String newLine = otherUsername + ", " + newPassword + "\n";
                        sb.append(newLine);

                    }
                } else {
                    sb.append(text).append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        writeStringToFile(context, sb, GameConstants.USER_FILE);
        return true;
    }
}
