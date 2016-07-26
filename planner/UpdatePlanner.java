package simpledb.planner;

import simpledb.tx.Transaction;
import simpledb.parse.*;

/**
 * The interface implemented by the planners
 * for SQL insert, delete, and modify statements.
 * @author Edward Sciore
 */
public interface UpdatePlanner {
   
   /**
    * Executes the specified insert statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the insert statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeInsert(InsertData data, Transaction tx);
   
   /**
    * Executes the specified delete statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the delete statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeDelete(DeleteData data, Transaction tx);
   
   /**
    * Executes the specified modify statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the modify statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeModify(ModifyData data, Transaction tx);
   
   /**
    * Executes the specified create table statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the create table statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeCreateTable(CreateTableData data, Transaction tx);
   
   /**
    * Executes the specified create view statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the create view statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeCreateView(CreateViewData data, Transaction tx);
   
   /**
    * Executes the specified create index statement, and
    * returns the number of affected records.
    * @param data the parsed representation of the create index statement
    * @param tx the calling transaction
    * @return the number of affected records
    */
   public int executeCreateIndex(CreateIndexData data, Transaction tx);
}
