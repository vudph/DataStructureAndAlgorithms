package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
1) Problem (VN student computer olympiad 2008)
There are N contest. Si is the start time of contest i-th, and Fi is the end time. At the same time there is only one contest which is competing at a room, excepts that a contest has the start time be equal to the end time of another contest. 
 
2) Constrants
- N <= 1000
- 0 <= Si < Fi <= 70000

3) Sample
input:
5 => number of contest
0 2 => start and end time of a contest
1 2
3 4
2 5
4 5
output:
2 => rooms require.

*/

public class RoomScheduler {

	public static void main(String[] args) {
		int n = 5;
		Contest contests[] = {new Contest(0, 2), new Contest(1,  2), new Contest(3, 4), new Contest(2, 5), new Contest(4, 5)};
		Arrays.sort(contests);
		for (Contest contest : contests) {
			System.out.println("s: " + contest.getStart() + ", e: " + contest.getEnd());
		}
		System.out.println(schedule(n, contests));
	}
	
	private static int schedule(int n, Contest[] contests) {
		List<Room> rooms = new ArrayList<Room>();
		for (Contest con : contests) {
			Room room = getAvailableRoom(con, rooms);
			room.pushContestToRoom(con);
		}
		for (int i = 0; i < rooms.size(); i++) {
			System.out.println("room: " + i);
			System.out.println(rooms.get(i).toString());
		}
		return rooms.size();
	}
	
	private static Room getAvailableRoom(Contest con, List<Room> rooms) {
		Room availRoom = null;
		if (rooms.isEmpty()) {
			availRoom = new Room();
			
		} else {
			for (Room ro : rooms) {
				if (con.getStart() >= ro.getLastContest().getEnd()) {
					return ro;
				}
			}
			availRoom =  new Room();
		}
		rooms.add(availRoom);
		return availRoom;
	}
}

class Contest implements Comparable<Contest>{
	private int start;
	private int end;
	public Contest(int s, int e) {
		this.start = s;
		this.end = e;
	}
	
	@Override
	public int compareTo(Contest o) {
		if (this.start <= o.start) 
			return -1;
		return 1;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}

class Room {
	private Stack<Contest> assignedContests = new Stack<Contest>();

	public Stack<Contest> getAssignedContests() {
		return assignedContests;
	}

	public void setAssignedContests(Stack<Contest> assignedContests) {
		this.assignedContests = assignedContests;
	}
	
	public Contest getLastContest() {
		return assignedContests.peek();
	}
	
	public void pushContestToRoom(Contest con) {
		assignedContests.push(con);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Contest contest : assignedContests) {
			sb.append("Contest: " + contest.getStart() + " : " + contest.getEnd());
			sb.append("\n");
		}
		return sb.toString();
	}
}
