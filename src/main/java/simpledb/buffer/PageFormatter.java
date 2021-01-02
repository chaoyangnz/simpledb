package simpledb.buffer;

import simpledb.file.Page;

/**
 * An interface used to initialize a new block on disk.
 * There will be an implementing class for each "type" of
 * disk block.
 * @author Edward Sciore
 */
public interface PageFormatter {
   /**
    * Initializes a page, whose contents will be
    * written to a new disk block.
    * This method is called only during the method
    * {@link Buffer#assignToNew}.
    * @param p a buffer page
    */
   public void format(Page p);
}
