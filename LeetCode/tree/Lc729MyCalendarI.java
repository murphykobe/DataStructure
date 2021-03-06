// https://leetcode.com/problems/my-calendar-i/submissions/

/** IDEA ::::::::::::::::::::::::::
Evidently, two events [s1, e1) and [s2, e2) do not conflict if and only if 
one of them starts after the other one ends: either e1 <= s2 OR e2 <= s1. 

By De Morgan's laws, this means the events conflict when s1 < e2 AND s2 < e1.

If we maintained our events in sorted order, 
we could check whether an event could be booked in O(\log N)O(logN) time.
We would also have to insert the event in our sorted structure.

SO-> We need a data structure that keeps elements sorted and supports fast insertion.
TreeMap

*/
// ============= Method 1: BST ===========
// 78ms
class MyCalendar {
    TreeMap<Integer, Integer> calendar; // <start, end>
    public MyCalendar() {
        calendar = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),    // greatest key less then start -> prev start
                next = calendar.ceilingKey(start);  // least key larger than start  -> next start
        if ((prev == null || calendar.get(prev) <= start) &&
            (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        
        return false;
    }
}

// ============== Method 2: BST Like Calendar II ==========
// 242ms
class MyCalendar {
    TreeMap<Integer, Integer> delta;
    public MyCalendar() {
        delta = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);
        
        int active = 0;
        for (Map.Entry<Integer, Integer> entry : delta.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            
            active += val;
            
            // there is a conflict
            if (active == 2) {
                // reset the start and end
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0){
                    delta.remove(start);
                }
                if (delta.get(end) == 0) {
                    delta.remove(end);
                }  
                return false;
            }
        }
        
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
