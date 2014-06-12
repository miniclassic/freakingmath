package vn.pp.freakingadult.utils;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class MyColor {
	public static Color color1=new Color(169f/255,193f/255,59f/255,1f);
	public static Color color2=new Color(183f/255,86f/255,63f/255,1f);
	public static Color color3=new Color(169f/255,139f/255,81f/255,1f);
	public static Color color4=new Color(81f/255,140f/255,75f/255,1f);
	public static Color color5=new Color(75f/255,140f/255,138f/255,1f);
	public static Color color6=new Color(142f/255,78f/255,102f/255,1f);
	public static Color color7=new Color(78f/255,98f/255,142f/255,1f);
	public static Color color8=new Color(228f/255,111f/255,33f/255,1f);
	public static Color color9=new Color(193f/255,59f/255,59f/255,1f);
	
	
	
	public static Color getPlayColor(){
		int rd=new Random().nextInt(9)+1;
		if(rd==1) return color1;
		if(rd==2) return color2;
		if(rd==3) return color3;
		if(rd==4) return color4;
		if(rd==5) return color5;
		if(rd==6) return color6;
		if(rd==7) return color7;
		if(rd==8) return color8;
		if(rd==9) return color9;
		return color1;
	}
}
