import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class stores values that can be retrieved with keys
 *
 * @param <K> The key to find values in the hash table
 * @param <V> The value to be stored in the hash table
 */
public class HashTable<K, V> implements HashTableADT<K, V> {
    
    /**
     * This class creates nodes that are stored in the hash table
     *
     * @param <K> The key of the node
     * @param <V> The value of the node
     */
    protected class Node<K, V> {
        private K key; // The key of the node
        private V value; // The value of the node
        private Node<K, V> next; // The next node at a hashIndex
        
        /**
         * This method constructs the nodes
         * 
         * @param key: the key of the node
         * @param value: the value of the node
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int initialCapacity; // Initial capacity of the hash table
    private double loadFactor; // Load factor of the hash table used to determine when to rezise
    private ArrayList<Node<K, V>> table; // The hash table of nodes
    private int numberOfItems = 0; // The number of items in the hash table
    
    /**
     * This method constructs the hash table
     * 
     * @param initialCapacity: the initial capacity of the hash table
     * @param loadFactor: the load factor of the hash table
     */
    public HashTable(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new ArrayList<Node<K, V>>();
        for (int i = 0; i < initialCapacity; i++) {
            table.add(null); // Set the table nodes to null to initialize size
        }
    }
    
    /**
     * This method returns a hash index based off the key argument
     */
    private int hash(K key) {
        int hashIndex = (int) Math.abs(key.hashCode() % table.size()); // This hash function prevents returning 
        return hashIndex;                                              // a negative index and is efficient
    }

    /**
    * @param key: The key that goes into the hashtable
    * @param value: The Value associated with the key
    * @return value of the key added to the hashtable,
    *      throws NullPointerException if key is null
    */
    @Override
    public V put(K key, V value) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        V orig = null;
        int hashIndex = hash(key);
        Node<K, V> head = table.get(hashIndex); // Start at front of table index
        while (head != null) { // Search through the table at hashIndex to find the correct node
            if (head.key.equals(key)) {
                orig = head.value;
                head.value = value;
            }
            head = head.next; // Move to next node if node's key is not equal to parameter key
        }
        Node<K, V> newNode = new Node<K, V>(key, value); 
        head = table.get(hashIndex);
        newNode.next = head; // Set newNode to be head's predecessor
        table.set(hashIndex, newNode); // Put newNode in the table
        numberOfItems++;
        if ((numberOfItems * 1.0 / table.size()) >= loadFactor) { // Resize the table if loadFactor is reached
            ArrayList<Node<K, V>> oldTable =  table;
            table = new ArrayList<Node<K, V>>();
            for (int i = 0; i < oldTable.size() * 2; i++) {
                table.add(null);
            }
            for (Node<K, V> node : oldTable) {
                if (node != null) {
                    int newHashIndex = (hash(node.key));
                    table.set(newHashIndex, node);
                }
            }
        }
        return orig;
    }

    /**
     * @param key: The key for which the value is returned
     * @return The value associated with the key,
     *          else throws NoSuch Element Exception
     */
    @Override
    public V get(K key) throws NoSuchElementException {
        int hashIndex = hash(key);
        if (table.get(hashIndex) == null) {
            throw new NoSuchElementException(); 
        } 
        else {
            Node<K, V> node = table.get(hashIndex);
            while (node != null) { // Search through the table at hashIndex to find the correct node
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next; // Move to next node if node's key is not equal to parameter key
            }
        }
        return null;
    }

    /**
     * Clear the hashtable of all its contents
     */
    @Override
    public void clear() {
        for (int i = 0; i < table.size(); i++) {
            table.remove(i);
        }
        numberOfItems = 0;
    }

    /**
     * Checks if the hashtable is empty
     * @return true : if Empty, else False
     */
    @Override
    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    /**
    * @param key: Key of the entry to be removed
    * @return value: Value of the key-value pair removed,
    *          null if key did not have a mapping
    * @throws NullPointerException if key is null
    */
    @Override
    public V remove(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException();
        }
        int hashIndex = hash(key);
        Node<K, V> node = table.get(hashIndex); // Start at front of table index
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                break;
            }
            prev = node;
            node = node.next; // Move to next node if node's key is not equal to parameter key
        }
        if (node == null) {
            return null;
        }
        numberOfItems--;
        if (prev != null) {
            prev.next = node.next; // Set next node's place to node's place if there is one
        }
        else {
            table.set(hashIndex, node.next); 
        }
        return node.value;
    }

    /**
    * @return: The total number of entries in the hashtable
    */
    @Override
    public int size() {
        return numberOfItems;
    }
}
