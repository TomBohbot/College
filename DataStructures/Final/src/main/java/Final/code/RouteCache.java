package Final.code;


import java.util.HashMap;
import java.util.Map;
/**
 * This is a bounded cache that maintains only the most recently accessed IP Addresses
 * and their routes.  Only the least recently accessed route will be purged from the
 * cache when the cache exceeds capacity.  There are 2 closely coupled data structures:
 *   -  a Map keyed to IP Address, used for quick lookup
 *   -  a Queue of the N most recently accessed IP Addresses
 * All operations must be O(1).  A big hint how to make that happen is contained
 * in the type signature of the Map on line 38.
 * 
 * @author Tom Bohbot 
 * @version May 26, 2020
 */
public class RouteCache
{
    // instance variables - add others if you need them
    // do not change the names of any fields as the test code depends on them
    
    // Cache total capacity and current fill count.
    private final int capacity;
    private int nodeCount;
    
    // private class for nodes in a doubly-linked queue
    // used to keep most-recently-used data
    private class Node {
        private Node prev, next;
        private final IPAddress elem; 
        private final int route;

        Node(IPAddress elem, int route) {
            prev = next = null;
            this.elem = elem;
            this.route = route;
        }  
    }
    private Node head = null;
    private Node tail = null;
    private Map<IPAddress, Node> nodeMap; // the cache itself

    /**
     * Constructor for objects of class RouteCache
     */
    public RouteCache(int cacheCapacity) {
        // your code goes here
        nodeMap = new HashMap <IPAddress, Node> ();
        this.capacity = cacheCapacity;
    }

    /**
     * Lookup the output port for an IP Address in the cache.
     * 
     * @param  addr   a possibly cached IP Address
     * @return     the cached route for this address, or null if not found 
     */
    public Integer lookupRoute(IPAddress addr) {
        // your code goes here
        if (nodeMap.get(addr) == null) {
            return null;
        }
        Node node = nodeMap.get(addr);
        Integer route = node.route;
        return route;
        // return null;
     }
     
    /**
     * Update the cache each time an element's route is looked up.
     * Make sure the element and its route is in the Map.
     * Enqueue the element at the tail of the queue if it is not already in the queue.  
     * Otherwise, move it from its current position to the tail of the queue.  If the queue
     * was already at capacity, remove and return the element at the head of the queue.
     * 
     * @param  elem  an element to be added to the queue, which may already be in the queue. 
     *               If it is, don't add it redundantly, but move it to the back of the queue
     * @return       the expired least recently used element, if any, or null
     */
    public IPAddress updateCache(IPAddress elem, int route) {
        // your code goes here
        IPAddress deletedAddy = null;
        if (nodeMap.get(elem) != null) {
            // I am just adjusting what is currently going on in the queue:
            Node newNode = nodeMap.get(elem);
            if (tail.equals(newNode) ) { return null; }
            if (head.equals(newNode) ) {
                head = newNode.next;
                head.prev = newNode;
                newNode.next = tail;
                tail = newNode;    
                return null;            
            }
            Node currentNode = newNode.prev;
            newNode.prev.next = newNode.next;
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
            return null;
        }
        if (nodeCount == capacity ) {
            // I'm gonna go over capacity now:
            deletedAddy = head.elem;
            head = head.next;
            nodeCount --;
            nodeMap.remove(deletedAddy);
        }
        // // happy path of adding an obj to the queue:
        Node newNode = new Node (elem, route);
        nodeCount ++;
        if (nodeCount == 1) { 
            tail = newNode; 
            head = newNode;
        }
        else {
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
        }
        nodeMap.put(elem , newNode);
    	return deletedAddy; //placeholder
    }

    
    /**
     * For testing and debugging, return the contents of the LRU queue in most-recent-first order,
     * as an array of IP Addresses in CIDR format. Return a zero length array if the cache is empty
     * 
     */
    String[] dumpQueue() {
        if (this.capacity == 0) {
            String [] emptyArray = new String [0];
            return emptyArray;
        }
        // your code goes here
        String [] array = new String [this.capacity];
        Node currentNode = tail;
        int i = 0;
        while (currentNode != null && i < this.capacity) {
            array[i] = currentNode.elem.toCIDR();
            i ++;
            if (currentNode.prev == null || currentNode.prev.equals(tail) ) {
                break;
            }
            currentNode = currentNode.prev;
        }
    	return array;
    }
}
