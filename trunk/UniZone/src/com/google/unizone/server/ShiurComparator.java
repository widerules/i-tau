package com.google.unizone.server;

import java.util.Comparator;


public class ShiurComparator implements Comparator<IShiur> {
	
	public int compare(IShiur s1, IShiur s2) {
		if (s1.getDay()>s2.getDay()){
			return 1;
		}else if (s1.getDay()<s2.getDay()){
			return -1;
		}
		if (s1.getBeginTime().getHours()>s2.getBeginTime().getHours()){
			return 1;
		}else if (s1.getBeginTime().getHours()<s2.getBeginTime().getHours()){
			return -1;
		}
		if (s1.getBeginTime().getMinutes()>s2.getBeginTime().getMinutes()){
			return 1;
		}else if (s1.getBeginTime().getMinutes()<s2.getBeginTime().getMinutes()){
			return -1;
		}
		return 0;
	}
	
	public boolean equals(Lesson lesson) {
		return false;
	}

}
