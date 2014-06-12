package vn.pp.freakingadult.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MyPrefs {
	private static final String PREFS_NAME="ppFreakingMath";
	private static final String SOUND="sound"; 
	private static final String MUSIC="music";
	private static final String BEST="bestscore";
	private static final String TIME="time";
	private static final String MATH="math_";
	private static final String LEVEL="level_";
	private static final String SCORE_GGS="SCORE_GGS";
	
	public static Preferences prefs=Gdx.app.getPreferences(PREFS_NAME);
	
	public static void setMusic(boolean isMusic){
		prefs.putBoolean(MUSIC, isMusic);
		prefs.flush();
	}
	
	public static boolean getMusic(){
		return prefs.getBoolean(MUSIC,true);
	}
	
	public static void setSound(boolean isSound){
		prefs.putBoolean(SOUND, isSound);
		prefs.flush();
	}
	
	public static boolean getSound(){
		return prefs.getBoolean(SOUND,true);
	}
	
	public static void setBestScore(int score){
		if(score>getBestScore()){
			prefs.putInteger(BEST, score);
			prefs.flush();
		}
	}
	
	public static int getBestScore(){
		return prefs.getInteger(BEST,0);
	}
	
	public static void setTime(float time){
		prefs.putFloat(TIME, time);
		prefs.flush();
	}
	
	public static float getTime(){
		return prefs.getFloat(TIME,Setting.TIME_DEFAULT);
	}
	
	public static void setMath(int indexMath,boolean isChoice){
		prefs.putBoolean(MATH+indexMath, isChoice);
		prefs.flush();
	}
	
	public static boolean getMath(int indexMath){
//	    System.out.println("----prefs.getBoolean(MATH+indexMath,false): "+prefs.getBoolean(MATH+indexMath,false));
		return prefs.getBoolean(MATH+indexMath,false);
	}
	
	public static void setLevel(int level){
		prefs.putInteger(LEVEL, level);
		prefs.flush();
	}
	
	public static int getLevel(){
		return prefs.getInteger(LEVEL,1);
	}
	
	public static void setScore(int score){
	    prefs.putInteger(SCORE_GGS, score);
	    prefs.flush();
	}
	
	public static int getScore(){
	    return prefs.getInteger(SCORE_GGS,0);
	}
}
