import java.util.*;

/**
 * LeetCode 207: Course Schedule
 * https://leetcode.com/problems/course-schedule/description/
 *
 * Algorithm Approach:
 *
 * - This solution uses Kahn's Algorithm (BFS Topological Sort) to detect cycles in a directed graph.
 * - Each course is a node, and prerequisites are directed edges.
 * - Build an in-degree array to count prerequisites for each course.
 * - Build an adjacency list (map) to represent which courses depend on each course.
 * - Add all courses with in-degree 0 (no prerequisites) to a queue.
 * - Repeatedly remove nodes from the queue, decrementing the in-degree of their dependent courses.
 * - If a dependent course's in-degree becomes 0, add it to the queue.
 * - If all courses are processed (count == numCourses), return true (possible to finish all courses).
 * - If not, a cycle exists and return false.
 *
 * Time Complexity: O(N + E), where N = numCourses and E = number of prerequisites.
 * Space Complexity: O(N + E) for the adjacency list, in-degree array, and queue.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length ==0) return  true;
        int[] inDegreeArray = new int[numCourses];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int count =0;
        for (int i = 0; i < prerequisites.length; i++) {
            inDegreeArray[prerequisites[i][0]]++;
            if(!map.containsKey(prerequisites[i][1])){
                map.put(prerequisites[i][1], new ArrayList<>());
            }
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for(int i = 0;i<numCourses;i++){
            if(inDegreeArray[i] == 0){
                queue.add(i);
                count++;
            }
        }
        if(count == numCourses) return  true;
        if(queue.isEmpty()) return  false;
        while(!queue.isEmpty()){
            int indi = queue.poll();
            ArrayList<Integer> list = map.get(indi);
            if(list != null) {
                for (Integer dep : list) {
                    inDegreeArray[dep]--;
                    if (inDegreeArray[dep] == 0) {
                        queue.add(dep);
                        count++;
                        if (count == numCourses) return true;
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        boolean canFinish =courseSchedule.canFinish(7, new int[][]{{2, 0}, {5, 3}, {3, 2}, {1, 0}, {4, 1}, {3, 1}, {5, 2}, {1, 6}, {5, 4}});
        System.out.println(canFinish);
    }
}
