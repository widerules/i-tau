package com.google.unizone.server;


import java.util.Comparator;


public class AvailableComperator implements Comparator<Available> {
	
	public int compare(Available a1, Available a2) {
		if (a1.getBeginTm()>a2.getBeginTm()){
			return 1;
		}else if (a1.getBeginTm()<a2.getBeginTm()){
			return -1;
		}
		if (a1.getEndTm()>a2.getEndTm()){
			return 1;
		}else if (a1.getEndTm()<a2.getEndTm()){
			return -1;
		}
		return 0;
	}
	
	public boolean equals(Lesson lesson) {
		return false;
	}

}
