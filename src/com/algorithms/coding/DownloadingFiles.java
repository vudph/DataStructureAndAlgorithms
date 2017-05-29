package com.algorithms.coding;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
1. Problem Statement: https://community.topcoder.com/stat?c=problem_statement&pm=8400&rd=10800

You are downloading some files from the Internet, and you want to know how long it will take until they are completely downloaded.
For each download, you are given its current speed (in KB/s) and remaining time based on that speed (in seconds). The sum of all the speeds is your total bandwidth, which remains constant and is utilized fully at all times during the downloads. This means that when files finish downloading, the newly available bandwidth is distributed among the remaining files. The way it's distributed does not affect the final answer.
For example, consider the following scenario where you are downloading two files.
	1) Speed = 3 KB/s Remaining Time 57 seconds
	2) Speed = 2 KB/s Remaining Time 22 seconds

After 22 seconds, the second file will finish downloading. The first file still has 35 seconds remaining, but that time is based on the original speed. The bandwidth freed up by the second file is now allocated to the first file, and its new speed is 3+2=5 KB/s. The new remaining time is: Old_Remaining_Time * Old_Speed / New_Speed = 35*3/5 = 21 seconds.
So the actual remaining time for all the files is 21+22=43 seconds.
You will be given a String[] tasks, each element of which represents a single file being downloaded. Each file is represented as two positive integers with no leading zeroes, separated by a single space. The first integer is the current download speed in KB/s and the second integer is the remaining time in seconds based on the current speed. Return a double representing the remaining time in seconds for all the downloads to finish.

2. Definition

Class:	DownloadingFiles
Method:	actualTime
Parameters:	String[]
Returns:	double
Method signature:	double actualTime(String[] tasks)
(be sure your method is public)


3. Constraints
-	tasks will contain between 1 and 50 elements, inclusive.
-	Each element of tasks will be formatted "<speed> <time>" (quotes for clarity), where <speed> is an integer between 1 and 100, inclusive, with no leading zeroes, and <time> is an integer between 1 and 10000, inclusive, with no leading zeroes.

4. Examples
0)	
{"3 57","2 22"}
Returns: 43.0
The example from above.

1)	
{"3 1057","2 1022"}
Returns: 1043.0
This is the same as the first example but all the files will take 1000 seconds more to completely download.

2)	
{"25 1000","5 5000","10 5000"}
Returns: 2500.0
In this case, when the first file finishes downloading, we will have 25 KB/s of newly available bandwidth. We can share it between the remaining downloads however we want without affecting the final answer. Suppose that 15 KB/s goes to the second file and 10 KB/s goes to the third file. The new speeds and remaining times for those files will be:
20 KB/s and remaining time 2000 seconds
20 KB/s and remaining time 1000 seconds
Those would take 1500 seconds to complete. So the answer is 1000 + 1500 = 2500.

3)	
{"1 10","1 20","2 40"}
Returns: 27.5
For this example, suppose that all newly available bandwidth goes to the slowest task every time. When the first download finishes (after 10 seconds), the second task doubles its speed to 2, and thus halves its remaining time from (20-10=10) to 5. When the second download finishes, the third one doubles its speed, so its remaining time goes from (40-15=25) to 12.5. The total time is 10+5+12.5=27.5.

4)	
{"6 88","39 7057","63 2502","45 2285","28 8749","62 3636","1 5546","49 5741"}
Returns: 4414.542662116041
And here is a nice random example for you.
*/

public class DownloadingFiles {

	public static void main(String[] args) {
		String tasks[] = {"6 88","39 7057","63 2502","45 2285","28 8749","62 3636","1 5546","49 5741"};
		System.out.println(actualTime(tasks));
	}
	
	private static double actualTime1(String[] tasks) {
		double sumSize = 0;
		long sumBandwidth = 0;
		for (int i = 0; i < tasks.length; i++) {
			String[] s = tasks[i].split(" ");
			sumBandwidth += Integer.parseInt(s[0]);
			sumSize += Integer.parseInt(s[0]) * Integer.parseInt(s[1]);
		}
		return (double) sumSize / (double) sumBandwidth;
	}
	
	private static double actualTime(String[] tasks) {
		Task theTasks[] = new Task[tasks.length];
		int i = 0;
		for (String task : tasks) {
			String info[] = task.split(" ");
			theTasks[i++] = new Task(Double.parseDouble(info[0]), Double.parseDouble(info[1]));
		}
		Arrays.sort(theTasks);
		
		Queue<Task> queue = new LinkedList<>(Arrays.asList(theTasks));
		double actual = 0.0;
		while (!queue.isEmpty()) {
			Task t = queue.remove();
			actual += t.remaining;
			if (!queue.isEmpty()) {
				updateSpeedRemainingTime(queue, t.speed, t.remaining);
			}
		}
		
		return actual;
	}
	
	private static void updateSpeedRemainingTime(Queue<Task> queue, double sp, double ti) {
		for (Task task : queue) {
			task.remaining = task.remaining - ti;
		}
		Task t = queue.peek();
		
		double newSpeed = t.speed + sp;
		t.remaining = (t.remaining * t.speed) / newSpeed;
		t.speed = newSpeed;
	}
	
	static class Task implements Comparable<Task> {
		double speed;
		double remaining;
		
		public Task(double speed, double remaining) {
			this.speed = speed;
			this.remaining = remaining;
		}
		
		@Override
		public int compareTo(Task o) {
			if (this.remaining < o.remaining) {
				return -1;
			} else if (this.remaining > o.remaining) {
				return 1;
			} else if (this.speed > o.speed) {
				return -1;
			} else if (this.speed < o.speed) {
				return 1;
			}
			return 0;
		}
		
		public String toString() {
			return "(" + this.speed + ", " + this.remaining + ")";
		}
	}

}
