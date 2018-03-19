import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<K, V> implements HashTableADT<K, V> {
    
    protected class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    /* Instance variables and constructors
     */
    private int initialCapacity;
    private double loadFactor;
    private ArrayList<Node<K, V>> table;
    private double curLoadFactor;
    private int numberOfItems = 0;
    
    public HashTable(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new ArrayList<Node<K, V>>();
        for (int i = 0; i < initialCapacity; i++) {
            table.add(null);
        }
    }
    
    // returns hash index
    private int hash(K key) {
        int hashIndex = (int) Math.abs(key.hashCode() % table.size());
        return hashIndex;
    }

    @Override
    public V put(K key, V value) throws NullPointerException {
        //TODO: Implement put method - using efficient algorithm
        if (key == null) {
            throw new NullPointerException();
        }
        V orig = null;
        int hashIndex = hash(key);
        Node<K, V> head = table.get(hashIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                orig = head.value;
                head.value = value;
            }
            head = head.next;
        }
        Node<K, V> newNode = new Node<K, V>(key, value);
        head = table.get(hashIndex);
        newNode.next = head;
        table.set(hashIndex, newNode);
        numberOfItems++;
        if ((numberOfItems * 1.0 / table.size()) >= loadFactor) {
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

    @Override
    public V get(K key) throws NoSuchElementException {
        //TODO: Implement the get method
        int hashIndex = hash(key);
        if (table.get(hashIndex) == null) {
            throw new NoSuchElementException(); 
        } 
        else {
            Node<K, V> node = table.get(hashIndex);
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public void clear() {
       //TODO: Implement this method
        for (int i = 0; i < table.size(); i++) {
            table.remove(i);
        }
        numberOfItems = 0;
    }

    @Override
    public boolean isEmpty() {
        //TODO: Implement the method
        return numberOfItems == 0;
    }

    @Override
    public V remove(K key) {
       //TODO: Implement the remove method
        int hashIndex = hash(key);
        Node<K, V> node = table.get(hashIndex);
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                break;
            }
            prev = node;
            node = node.next;
        }
        if (node == null) {
            return null;
        }
        numberOfItems--;
        if (prev != null) {
            prev.next = node.next;
        }
        else {
            table.set(hashIndex, node.next);
        }
        return node.value;
    }

    @Override
    public int size() {
        //TODO: Implement this method
        return numberOfItems;
    }
    
//    public static void main(String[] args) {
//        HashTable<Integer, String> hash = new HashTable<Integer, String>(3, .7);
//        hash.put(1, "hi");
//        hash.put(17, "hiasdf");
//        hash.put(3, "hif");
//        hash.put(2, "hasdff");
//        hash.put(25, "ggsi");
//        hash.put(45, "laksdjf");
//        hash.put(67, "fruit");
//        hash.put(78, "carrot");
//        hash.put(123, "12341");
//        hash.put(67, "78");
//        hash.put(8, "tyrj");
//        System.out.println(hash.get(3));
//        System.out.println(hash.get(8));
//        System.out.println(hash.remove(3));
//        System.out.println(hash.get(3));
//        hash.clear();
//        System.out.println(hash.size());
//    }
}
