package Final.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * IPRouter simulates the decision process for an IP router dispatching packets
 * according a prefix trie of routing rules.
 * 
 * @author Van Kelly
 * @version 1.0
 */
public class IPRouter {
    final int nPorts; 
    final int cacheSize;
    final BitVectorTrie<Integer> trie = new BitVectorTrie<>();
    private HashMap <Integer , IPAddress> map = new HashMap <Integer , IPAddress> ();
    private HashMap <IPAddress , Integer> bigMap = new HashMap <IPAddress , Integer> ();
    private RouteCache routeCache;
    private int cacheCounter;
    private int portCount; // counts how many ports are currently used.

    /** Router constructor
     * @param nPorts    the number of output ports, numbered 0 ... nPorts-1.  Pseudo-port -1 is 
     *                  always used for errors.
     * @param cacheSize the number of IP Addresses to be kept in a cache of the most recently routed 
     *                  UNIQUE IP Addresses
     */
    public IPRouter (int nPorts, int cacheSize) {
        this.nPorts = nPorts;
        this.cacheSize = cacheSize;
        this.routeCache = new RouteCache(cacheSize);
    }

    /**
     * Add a routing rule to the router. Each rule associates an IP Address prefix with an output port.
     * In case rules overlap, longest prefix wins.  If two rules specify exactly the same prefix, then
     * the second rule triggers an IllegalArgumentException.  The port must be in the permitted range
     * for this router, or an IllegalArgumentException will be triggered as well.
     * 
     * @param  prefix    an IP Address prefix in CIDR (dotted decimal) notation
     * @param  port
     * @return        true if rule is accepted. 
     */
    public void addRule(String prefix, int port) {
        // "finish this (1 or 2 lines)" Kelly
        // convert prefix to bits and then insert to trie:
        // so in the trie i want to be comparing the prefix which is the key, but returning the value which is the port. don't really care what the content of the port is then.
        if (port >= this.nPorts) {
            throw new IllegalArgumentException();
        }
        if (portCount >= nPorts) {
            throw new IllegalArgumentException();
        }
        IPAddress cidrToBits = new IPAddress(prefix);
        if (map.get(port) != null) {
            IPAddress oldAddress = map.get(port);
            if (cidrToBits.size() > oldAddress.size() ) {
                portCount = portCount - 1;
                deleteRulePrivate(oldAddress);
            }
            // if (cidrToBits.equals(oldAddress) ) {    // muted out per Piazza post 496
            //     throw new IllegalArgumentException();
            // }
        }
        portCount ++;
        trie.put(cidrToBits, port);
        bigMap.put(cidrToBits , port);
        map.put(port , cidrToBits);
        // routeCache.updateCache(cidrToBits, port);
    }

    private void deleteRulePrivate(IPAddress address) {
        // finish this (1 or 2 lines)
        trie.delete(address);
        Integer port = bigMap.get(address);
        map.remove(port);
    }

    public void deleteRule(String prefix) {
        // finish this (1 or 2 lines)
        IPAddress cidrToBits = new IPAddress(prefix);
        trie.delete(cidrToBits);
        Integer port = bigMap.get(cidrToBits);
        map.remove(port);
    }

    /**
     * Simulate routing a packet to its output port based on a binary IP Address.
     * If no rules apply to an address, route it to port -1 and log an error to System.err
     * 
     * @param  address    an IP Address object
     * @return  number of output port 
     */
    public int getRoute(IPAddress address) {
        // finish this (~6 lines)
        if (trie.get(address) == null) {
            routeCache.updateCache(address, -1);
            System.err.println("no rules apply.");
            return -1;
        }
        if (cacheCounter < cacheSize) {
            cacheCounter ++;
        }
        int port = trie.get(address);
        routeCache.updateCache(address, port);
        return port;
    }

    /**
     * Tell whether an IP Address is currently in the cache of most recently routed addresses
      * 
     * @param  address    an IP Address in dotted decimal notation
     * @return  number of output port 
     */
    boolean isCached(IPAddress address) {
        // finish this 
        if (routeCache.lookupRoute(address) == null) {
            return false;
        }
        return true;
        // return false;
    }
    
    /**
     * For testing and debugging, return the contents of the LRU queue in most-recent-first order,
     * as an array of IP Addresses.  Return a zero length array if the cache is empty
     * 
     */
    String[] dumpCache() {
        // put your code here
        if (this.cacheSize == 0 || cacheCounter == 0) {
            String [] emptyArray = new String [0];
            return emptyArray;
        }
        // your code goes here
        String [] arrayAsString = routeCache.dumpQueue();
        return arrayAsString;
    }
    
    /**
     * For testing and debugging, load a routing table from a text file
     * 
     */
    public void loadRoutes(String filename) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.startsWith("+")) {
                String[] pieces = line.substring(1).split(",");
                int port = Integer.parseInt(pieces[1]);
                this.addRule(pieces[0].trim(), port);
            }
        }
    }
}
