/* Set.java */

import list.*;

/**
 * A Set is a collection of Comparable elements stored in sorted order.
 * Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  protected List setList;

  /**
   * Set ADT invariants: 1) The Set's elements must be precisely the elements of
   * the List. 2) The List must always contain Comparable elements, and those
   * elements must always be sorted in ascending order. 3) No two elements in the
   * List may be equals().
   **/

  /**
   * Constructs an empty Set.
   *
   * Performance: runs in O(1) time.
   **/
  public Set() {
    // Your solution here.
    setList = new DList();
  }

  /**
   * cardinality() returns the number of elements in this Set.
   *
   * Performance: runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return this.setList.length();
  }

  /**
   * insert() inserts a Comparable element into this Set.
   *
   * Sets are maintained in sorted order. The ordering is specified by the
   * compareTo() method of the java.lang.Comparable interface.
   *
   * Performance: runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if (this.setList.isEmpty()) {
      this.setList.insertFront(c);
    } else {
      DListNode node = (DListNode) this.setList.front();
      try {
        if (((Comparable) node.item()).compareTo(c) > 0) {
          this.setList.insertFront(c);
        } else {
          while (node.next().isValidNode()) {
            if (((Comparable) node.item()).compareTo(c) < 0 && ((Comparable) node.next().item()).compareTo(c) > 0) {
              node.insertAfter(c);
              node = (DListNode) node.next();
            }
            if (((Comparable) node.item()).compareTo(c) == 0 || ((Comparable) node.next().item()).compareTo(c) == 0) {
              return;
            }
            if ((((Comparable) node.next().item()).compareTo(c) < 0)) {
              node = (DListNode) node.next();
            }
            // System.out.println("Now is" + node.item());
          }
          if (!node.next().isValidNode() && ((Comparable) node.item()).compareTo(c) < 0) {
            this.setList.insertBack(c);
          }
        }
      } catch (InvalidNodeException e) {
        System.out.println("Invalid node~~~~~~~~~~");
      }
    }
  }

  /**
   * union() modifies this Set so that it contains all the elements it started
   * with, plus all the elements of s. The Set s is NOT modified. Make sure that
   * duplicate elements are not created.
   *
   * Performance: Must run in O(this.cardinality() + s.cardinality()) time.
   *
   * Your implementation should NOT copy elements of s or "this", though it will
   * copy _references_ to the elements of s. Your implementation will create new
   * _nodes_ for the elements of s that are added to "this", but you should reuse
   * the nodes that are already part of "this".
   *
   * DO NOT MODIFY THE SET s. DO NOT ATTEMPT TO COPY ELEMENTS; just copy
   * _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    DListNode node = (DListNode) s.setList.front();
    while (node.isValidNode()) {
      try {
        this.insert((Comparable) node.item());
        node = (DListNode) node.next();
      } catch (InvalidNodeException e) {
        System.out.println("Invalid node#################");
      }
    }
  }

  /**
   * intersect() modifies this Set so that it contains the intersection of its own
   * elements and the elements of s. The Set s is NOT modified.
   *
   * Performance: Must run in O(this.cardinality() + s.cardinality()) time.
   *
   * Do not construct any new ListNodes during the execution of intersect. Reuse
   * the nodes of "this" that will be in the intersection.
   *
   * DO NOT MODIFY THE SET s. DO NOT CONSTRUCT ANY NEW NODES. DO NOT ATTEMPT TO
   * COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    DListNode sNode = (DListNode) s.setList.front();
    DListNode thisNode = (DListNode) this.setList.front();
    while (sNode.isValidNode() && thisNode.isValidNode()) {
      try {
        if (((Comparable) sNode.item()).compareTo(thisNode.item()) < 0) {
          sNode = (DListNode) sNode.next();
          
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) == 0) {
          sNode = (DListNode) sNode.next();
          thisNode = (DListNode) thisNode.next();
          
        } else if (((Comparable) sNode.item()).compareTo(thisNode.item()) > 0) {
          thisNode = (DListNode) thisNode.next();
          thisNode.prev().remove();
        }
      } catch (InvalidNodeException e) {
        System.out.println("Invalid nodeaaaaaaaaaaaaa");
      }
    }
    while (thisNode.isValidNode() && !sNode.isValidNode()) {
      try {
        thisNode = (DListNode) thisNode.next();
        thisNode.prev().remove();
      } catch (InvalidNodeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }  
    }
  }

  /**
   * toString() returns a String representation of this Set. The String must have
   * the following format: { } for an empty Set. No spaces before "{" or after
   * "}"; two spaces between them. { 1 2 3 } for a Set of three Integer elements.
   * No spaces before "{" or after "}"; two spaces before and after each element.
   * Elements are printed with their own toString method, whatever that may be.
   * The elements must appear in sorted order, from lowest to highest according to
   * the compareTo() method.
   *
   * WARNING: THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS FORMAT,
   * RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS. ANY DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    String result = "{ ";
    DListNode current = (DListNode) this.setList.front();
    while (current.isValidNode()) {
      try {
        result = result + current.item() + " ";
        current = (DListNode) current.next();
      } catch (InvalidNodeException e) {
        System.out.println("Invalid node!!!!!!!!!!!!!");
      }
    }
    return result + "}";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
