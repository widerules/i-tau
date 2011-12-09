package com.google.code.iTau.servlets;

import java.util.Comparator;

import com.google.code.iTau.ILesson;

public class LessonComparator implements Comparator<ILesson> {
	
	public int compare(ILesson l1, ILesson l2) {
		// TODO: implement by comparing the day and hours of the lessons
		// 		 must decide first how days and times will be represented
		return 0;
	}
	
	public boolean equals(ILesson lesson) {
		return false;
	}

}
