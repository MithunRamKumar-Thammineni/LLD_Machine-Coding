package Cricbuzz.MatchDetails;

import Cricbuzz.Enums.UmpireType;
import Cricbuzz.TeamDetails.Playing11;

import java.util.*;

public class Match {
    private int number;
    private Date startTime;
    private String result;
    private Playing11[] teams;
    private List<Innings> innings;
    private List<UmpireType> umpires;
    private String referee;
    private List<Commentary> commentators;
    private String matchStats;

    public boolean assignStadium(String stadium) {
        return true;
    }

    public boolean assignReferee(String referee) {
        return true;
    }

}
