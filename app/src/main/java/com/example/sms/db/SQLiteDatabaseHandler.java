
package com.example.sms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sms.contact.ContactModelClass;
import com.example.sms.modelclass.GroupSelectModelClass;
import com.example.sms.modelclass.HistoryModelClass;
import com.example.sms.modelclass.PlannedDisplayModelClass;

import java.util.ArrayList;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {




    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactDB";
    private static final String TABLE_NAME = "Contact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_POSITION = "position";
    private static final String KEY_HEIGHT = "height";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_POSITION,
            KEY_HEIGHT };



    private static final String TABLE_NAME1 = "Groups";
    private static final String KEY_ID1 = "groupid";
    private static final String KEY_NAME1 = "groupname";
    private static final String KEY_NUMBER = "contactid";
    private static final String[] COLUMNS1 = { KEY_ID1, KEY_NAME1, KEY_NUMBER};


    private static final String TABLE_HISTORY = "History";
    private static final String KEY_ID2 = "historyid";
    private static final String KEY_NAME2 = "sentname";
    private static final String KEY_MSG = "msg";
    private static final String KEY_TIME = "time";
    private static final String[] COLUMNS2 = { KEY_ID2, KEY_NAME2, KEY_MSG , KEY_TIME};

    private static final String TABLE_NAME3 = "Later";
    private static final String KEY_ID3 = "laterid";
    private static final String KEY_NAME3 = "sendname";
    private static final String KEY_MSG3 = "msg3";
    private static final String KEY_CSV = "csv";
    private static final String KEY_TIME3 = "time3";
    private static final String KEY_DATE = "date";
    private static final String KEY_FREQ = "freq";
    private static final String KEY_CHECK3 = "check3";
    private static final String[] COLUMNS3 = { KEY_ID3, KEY_NAME3, KEY_MSG3 , KEY_CSV , KEY_TIME3 , KEY_DATE , KEY_FREQ , KEY_CHECK3};

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        createContactDB(db);
        createGroupDB(db);
        createHistoryDB(db);
        createLaterDB(db);


    }

    private void createLaterDB(SQLiteDatabase db) {
        String CREATION_TABLE3 = "CREATE TABLE IF NOT EXISTS Later ( "
                + "laterid INTEGER PRIMARY KEY AUTOINCREMENT, " + "sendname TEXT, "
                + "msg3 TEXT, " + "csv TEXT, "  + "time3 TEXT, " + "date TEXT, " + "freq TEXT, " + "check3 TEXT )";

        db.execSQL(CREATION_TABLE3);


    }
    private void createContactDB(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Contact ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
                + "position TEXT, " + "height INTEGER )";

        db.execSQL(CREATION_TABLE);


    }

    private void createGroupDB(SQLiteDatabase db) {


        String CREATION_TABLE1 = "CREATE TABLE IF NOT EXISTS Groups ( "
                + "groupid INTEGER PRIMARY KEY AUTOINCREMENT, " + "groupname TEXT, "
                + "contactid TEXT )";

        db.execSQL(CREATION_TABLE1);

    }


    private void createHistoryDB(SQLiteDatabase db) {


        String CREATION_TABLE2 = "CREATE TABLE IF NOT EXISTS History ( "
                + "historyid INTEGER PRIMARY KEY AUTOINCREMENT, " + "sentname TEXT, "
                + "msg TEXT, " + "time TEXT )";

        db.execSQL(CREATION_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        this.onCreate(db);
    }

    public void deleteOne(ContactModelClass contactModelClass) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(contactModelClass.getId()) });
        db.close();
    }

    public ContactModelClass getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        ContactModelClass contactModelClass = new ContactModelClass();
        contactModelClass.setId(Integer.parseInt(cursor.getString(0)));
        contactModelClass.setName(cursor.getString(1));
        contactModelClass.setNumber(cursor.getString(2));
        contactModelClass.setHeight(Integer.parseInt(cursor.getString(3)));

        return contactModelClass;
    }

    public ArrayList<ContactModelClass> getAllContacts() {

        ArrayList<ContactModelClass> contactModelClasses = new ArrayList<ContactModelClass>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ContactModelClass contactModelClass = null;

        if (cursor.moveToFirst()) {
            do {
                contactModelClass = new ContactModelClass();
                contactModelClass.setId(Integer.parseInt(cursor.getString(0)));
                contactModelClass.setName(cursor.getString(1));
                contactModelClass.setNumber(cursor.getString(2));
                contactModelClass.setHeight(Integer.parseInt(cursor.getString(3)));
                contactModelClasses.add(contactModelClass);
            } while (cursor.moveToNext());
        }

        return contactModelClasses;
    }


    public ArrayList<GroupSelectModelClass> getAllGroups() {

        ArrayList<GroupSelectModelClass> groupSelectModelClasses = new ArrayList<GroupSelectModelClass>();
        String query1 = "SELECT  * FROM " + TABLE_NAME1;
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor = db1.rawQuery(query1, null);
        GroupSelectModelClass groupSelectModelClass = null;

        if (cursor.moveToFirst()) {
            do {
                groupSelectModelClass = new GroupSelectModelClass();

                groupSelectModelClass.setId(Integer.parseInt(cursor.getString(0)));
                groupSelectModelClass.setName(cursor.getString(1));
                groupSelectModelClass.setNumber(cursor.getString(2));

                groupSelectModelClasses.add(groupSelectModelClass);
            } while (cursor.moveToNext());
        }

        return groupSelectModelClasses;
    }

    public ArrayList<HistoryModelClass> getAllHistory() {

        ArrayList<HistoryModelClass> historyModelClasses = new ArrayList<HistoryModelClass>();
        String query2 = "SELECT  * FROM " + TABLE_HISTORY;
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor cursor = db2.rawQuery(query2, null);
        HistoryModelClass historyModelClass = null;

        if (cursor.moveToFirst()) {
            do {
                historyModelClass = new HistoryModelClass();

                historyModelClass.setId(Integer.parseInt(cursor.getString(0)));
                historyModelClass.setName(cursor.getString(1));
                historyModelClass.setMsg(cursor.getString(2));
                historyModelClass.setTime(cursor.getLong(3));

                historyModelClasses.add(historyModelClass);
            } while (cursor.moveToNext());
        }

        return historyModelClasses;
    }


    public ArrayList<PlannedDisplayModelClass> getAllLater() {

        ArrayList<PlannedDisplayModelClass> plannedDisplayModelClasses = new ArrayList<PlannedDisplayModelClass>();
        String query3 = "SELECT  * FROM " + TABLE_NAME3;
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor cursor = db3.rawQuery(query3, null);
        PlannedDisplayModelClass plannedDisplayModelClass = null;

        if (cursor.moveToFirst()) {
            do {
                plannedDisplayModelClass = new PlannedDisplayModelClass();

                plannedDisplayModelClass.setId(Integer.parseInt(cursor.getString(0)));
                plannedDisplayModelClass.setName(cursor.getString(1));
                plannedDisplayModelClass.setMsg(cursor.getString(2));
                plannedDisplayModelClass.setCSV(cursor.getString(3));
                plannedDisplayModelClass.setTime(cursor.getString(4));
                plannedDisplayModelClass.setDate(cursor.getString(5));
                plannedDisplayModelClass.setFreq(cursor.getString(6));
                plannedDisplayModelClass.setCheck(cursor.getString(7));

                plannedDisplayModelClasses.add(plannedDisplayModelClass);
            } while (cursor.moveToNext());
        }

        return plannedDisplayModelClasses;
    }


    public void deleteAll()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }


    public void deleteAllHistory()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY,null,null);
        db.execSQL("delete from "+ TABLE_HISTORY);
        db.close();
    }


    public void addContact(ContactModelClass contactModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contactModelClass.getName());
        values.put(KEY_POSITION, contactModelClass.getNumber());
        values.put(KEY_HEIGHT, contactModelClass.getHeight());
        db.insert(TABLE_NAME,null, values);
        db.close();
    }


    public void addGroup(GroupSelectModelClass groupSelectModelClass) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(KEY_NAME1, groupSelectModelClass.getName());
        values1.put(KEY_NUMBER, groupSelectModelClass.getNumber());
        db1.insert(TABLE_NAME1,null, values1);
        db1.close();
    }


    public void addHistory(HistoryModelClass historyModelClass) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        values2.put(KEY_NAME2, historyModelClass.getName());
        values2.put(KEY_MSG, historyModelClass.getMsg());
        values2.put(KEY_TIME, historyModelClass.getTime());
        db2.insert(TABLE_HISTORY,null, values2);
        db2.close();
    }


    public long addLater(PlannedDisplayModelClass plannedDisplayModelClass) {
        SQLiteDatabase db3 = this.getWritableDatabase();
        ContentValues values3 = new ContentValues();
        values3.put(KEY_NAME3, plannedDisplayModelClass.getName());
        values3.put(KEY_MSG3, plannedDisplayModelClass.getMsg());
        values3.put(KEY_CSV, plannedDisplayModelClass.getCSV());
        values3.put(KEY_TIME3, plannedDisplayModelClass.getTime());
        values3.put(KEY_DATE, plannedDisplayModelClass.getDate());
        values3.put(KEY_FREQ, plannedDisplayModelClass.getFreq());
        values3.put(KEY_CHECK3, plannedDisplayModelClass.getCheck());
        long id = db3.insert(TABLE_NAME3, null, values3);
        db3.close();

        return id;
    }


    public boolean existsColumnInTable(SQLiteDatabase inDatabase, String inTable, String columnToCheck) {
        Cursor mCursor = null;
        try {
            // Query 1 row
            mCursor = inDatabase.rawQuery("SELECT * FROM " + inTable + " LIMIT 0", null);

            // getColumnIndex() gives us the index (0 to ...) of the column - otherwise we get a -1
            if (mCursor.getColumnIndex(columnToCheck) != -1)
                return true;
            else
                return false;

        } catch (Exception Exp) {
            // Something went wrong. Missing the database? The table?
            //Log.d("... - existsColumnInTable", "When checking whether a column exists in the table, an error occurred: " + Exp.getMessage());
            return false;
        } finally {
            if (mCursor != null) mCursor.close();
        }
    }

    public String fetchGroup(SQLiteDatabase inDatabase,  String valueCheck){

        String query = "SELECT * FROM groups WHERE groupname=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {valueCheck});

        String csv = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                csv = cursor.getString(cursor.getColumnIndex("contactid"));
            }
            while(cursor.moveToNext());
        }cursor.close();
return csv;
    }

    public String fetchNameGroup(SQLiteDatabase inDatabase,  String valueCheck){

        String query = "SELECT * FROM groups WHERE contactid=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {valueCheck});

        String csv = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                csv = cursor.getString(cursor.getColumnIndex("groupname"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return csv;
    }

    public String fetchMsg(SQLiteDatabase inDatabase,  String valueCheck){

        String query = "SELECT * FROM later WHERE laterid=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        String msg = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                msg = cursor.getString(cursor.getColumnIndex("msg3"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return msg;
    }

    public String fetchCSV(SQLiteDatabase inDatabase,  String valueCheck){

        String query = "SELECT * FROM later WHERE laterid=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        String msg = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                msg = cursor.getString(cursor.getColumnIndex("csv"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return msg;
    }

    public String fetchContact(SQLiteDatabase inDatabase, String valueCheck){

        String query = "SELECT * FROM contact WHERE id=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        String csv = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                csv = cursor.getString(cursor.getColumnIndex("position"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return csv;
    }

    public String fetchNameContact(SQLiteDatabase inDatabase, String valueCheck){

        String query = "SELECT * FROM contact WHERE id=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        String csv = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                csv = cursor.getString(cursor.getColumnIndex("name"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return csv;
    }

    public String fetchCheck(SQLiteDatabase inDatabase, String valueCheck){

        String query = "SELECT * FROM later WHERE laterid=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        String csv = "";

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                csv = cursor.getString(cursor.getColumnIndex("check3"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return csv;
    }

    public Long fetchID(SQLiteDatabase inDatabase, String valueCheck){

        String query = "SELECT * FROM contact WHERE position=?";

        Cursor cursor = inDatabase.rawQuery(query,new String[] {String.valueOf(valueCheck)});

        Long id = null;

        if (cursor.moveToFirst()){
            do{
                //if you not need the loop you can remove that
                id = cursor.getLong(cursor.getColumnIndex("id"));
                //id = cursor.getString(cursor.getColumnIndex("id"));
            }
            while(cursor.moveToNext());
        }cursor.close();
        return id;
    }



    public boolean CheckIsDataAlreadyInDBorNot(SQLiteDatabase inDatabase,String TableName,
                                                      String dbfield, String fieldValue) {

        String Query = "SELECT * FROM " + TableName + " WHERE " + dbfield + " = " + fieldValue;
        Cursor cursor = inDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public boolean hasObject(SQLiteDatabase db,String TableName,
                             String dbfield, String fieldValue) {
        //SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + TableName + " WHERE " + dbfield + " =?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {fieldValue});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d("uzair", String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }

    public int updateContact(ContactModelClass contactModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contactModelClass.getName());
        values.put(KEY_POSITION, contactModelClass.getNumber());
        values.put(KEY_HEIGHT, contactModelClass.getHeight());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(contactModelClass.getId()) });

        db.close();

        return i;
    }

}