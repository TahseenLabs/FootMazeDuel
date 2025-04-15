package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
public class Goal {

	    private int strikerGoals;
	    private int defenderSaves;

	    public Goal() {
	        strikerGoals = 0;
	        defenderSaves = 0;
	    }

	    public void strikerScored() {
	        strikerGoals++;
	    }

	    public void defenderSaved() {
	        defenderSaves++;
	    }

	    public int getStrikerGoals() {
	        return strikerGoals;
	    }

	    public int getDefenderSaves() {
	        return defenderSaves;
	    }

	    public void reset() {
	        strikerGoals = 0;
	        defenderSaves = 0;
	    }

	    public String getScoreText() {
	        return "Striker: " + strikerGoals + " | Defender: " + defenderSaves;
	    }
	}


