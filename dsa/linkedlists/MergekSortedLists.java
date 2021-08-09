/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}ac
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
     public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
         if(n == 0) {
             return null;
         }
         
         Comparator<ListNode> cmp = new Comparator<>() {
             @Override
             public int compare(ListNode l1, ListNode l2) {
                 return l1.val - l2.val;
             }
         };
         
         Queue<ListNode> pq = new PriorityQueue<>(cmp);
         for(ListNode list : lists) {
             if(list != null) {
                pq.add(list);                 
             }
         }
         
         
         ListNode head = new ListNode(Integer.MAX_VALUE);
         ListNode temp = head;
         while(!pq.isEmpty()) {
             temp.next = pq.poll();
             temp = temp.next;
             if(temp.next != null) {
                 pq.add(temp.next);
             }
         }
         return head.next;
         
     }
    
    public ListNode mergeKLists1(ListNode[] lists) {
        //solve two two linked lists        
        int n = lists.length;
        if(n == 0) {
            return null;
        }
        
        int interval = 1;
        
        while(interval < n) {
            for(int i = 0; i + interval < n; i += (interval * 2)) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        
        return lists[0];
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode temp = head;
                
        while(l1 != null & l2 != null) {
            if(l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        
        if(l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }
        
        return head.next;
    }
    
}
