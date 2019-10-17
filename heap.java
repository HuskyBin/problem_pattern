// https://blog.csdn.net/lcore/article/details/9100073

package com.kiritor;
 
import java.util.Arrays;
 
 
public class BinaryHeap
{
    
    public BinaryHeap( )
    {
        this( DEFAULT_CAPACITY );
    }
    public BinaryHeap( Comparable[] items ){  
        currentSize = items.length;  
        array = new Comparable[ (currentSize + 2) * 11 / 10 ];  
          
        int i=1;  
        for( Comparable item : items ){  
            array[ i++ ] = item;  
        }  
        buildHeap();  
    }  
    
    public BinaryHeap( int capacity )
    {
        currentSize = 0;
        array = new Comparable[ capacity + 1 ];
    }
 
    public void insert( Comparable x ) 
    {
        
            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }
 
  
    public Comparable findMin( )
    {
        if( isEmpty( ) )
            return null;
        return array[ 1 ];
    }
 
   
    public Comparable deleteMin( )
    {
        if( isEmpty( ) )
            return null;
 
        Comparable minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );
 
        return minItem;
    }
 
   
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }
 
   
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }
 
    
    public boolean isFull( )
    {
        return currentSize == array.length - 1;
    }
 
   
    public void makeEmpty( )
    {
        currentSize = 0;
    }
 
    private static final int DEFAULT_CAPACITY = 100;
 
    private int currentSize;      // Number of elements in heap
    private Comparable [ ] array; // The heap array
 
   
    private void percolateDown( int hole )
    {
      int child;
     Comparable tmp = array[ hole ];
 
     for( ; hole * 2 <= currentSize; hole = child )
        {
          child = hole * 2;
          if( child != currentSize &&
                 array[ child + 1 ].compareTo( array[ child ] ) < 0 )
              child++;
          if( array[ child ].compareTo( tmp ) < 0 )
              array[ hole ] = array[ child ];
            else
              break;
        }
      array[ hole ] = tmp;
    }
 
        // Test program
    public static void main( String [ ] args )
    {
        int numItems = 50;
        BinaryHeap h = new BinaryHeap( numItems );
        int i = 37;
 
        try
        {
            for( i = 37; i != 0; i = ( i + 37 ) % numItems )
                h.insert( new Integer( i ) );
             System.out.println(Arrays.toString(h.array));
             System.out.println(h.findMin());
             h.deleteMin();
             System.out.println(Arrays.toString(h.array));
        }
        catch( Exception e )
          { System.out.println( "Overflow (expected)! " + i  ); }
    }
}
————————————————
版权声明：本文为CSDN博主「LCore」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/lcore/article/details/9100073
