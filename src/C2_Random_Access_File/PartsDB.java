package C2_Random_Access_File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by sayarasin on 07/19/2016.
 */
public class PartsDB {

    private final static int PNUMLEN = 20;
    private final static int DESCLEN = 30;
    private final static int QUANLEN = 4;
    private final static int COSTLEN = 4;


    public static class Part {

        private String partNum;
        private String desc;
        private int qty;
        private int cost;

        Part(String partNum, String desc, int qty, int cost) {
            this.partNum = partNum;
            this.desc = desc;
            this.qty = qty;
            this.cost = cost;
        }

        /**
         * Getter and setter of 'PartNum'
         */
        public void setPartNum(String partNum) {
            this.partNum = partNum;
        }
        public String getPartNum() {
            return partNum;
        }

        /**
         * Getter and setter of 'getDesc'
         */
        public String getDesc() {
            return desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * Getter and setter of 'getQty'
         */
        public int getQty() {
            return qty;
        }
        public void setQty(int qty) {
            this.qty = qty;
        }

        /**
         * Getter and setter of 'getCost'
         */
        public int getCost() {
            return cost;
        }
        public void setCost(int cost) {
            this.cost = cost;
        }
    }

    private final static int RECLEN = 2 * PNUMLEN + 2 * DESCLEN
            + QUANLEN + COSTLEN;

    private RandomAccessFile raf = null;


    /**
     * Open a database indicate by 'path'
     * @param path database file path
     * @throws FileNotFoundException
     */
    public PartsDB(String path) throws FileNotFoundException {
        raf = new RandomAccessFile(path, "rw");
    }


    /**
     * Close database
     */
    public void Close() {
        if(raf != null) {
            try {
                raf.close();
            } catch (IOException e) { /* do nothing */ }
        }
        raf = null;
    }


    /**
     * Append a new record
     * @param partNum part number
     * @param partDesc part description
     * @param qty qty
     * @param cost cost
     * @return true or false
     */
    public boolean Insert(String partNum, String partDesc, int qty, int cost) throws IOException {

        raf.seek(raf.length());
        return Write(partNum, partDesc, qty, cost);
    }


    /**
     * get current record number
     * @return record number
     * @throws IOException
     */
    public int NumRecs() throws IOException {
        return (int) raf.length() / RECLEN;
    }


    /**
     * Select a record from database
     * @param recNo record number
     * @return true or false
     * @throws IOException
     */
    public Part Select(int recNo) throws IOException {
        if(recNo < 0 || recNo >= NumRecs())
            throw new IllegalArgumentException(recNo + " out of range");
        raf.seek(recNo * RECLEN);
        return Read();
    }


    /**
     * Update a record
     * @param recNo record number
     * @param partNum part number
     * @param partDesc part description
     * @param qty qty
     * @param cost cost
     * @return true or false
     * @throws IOException
     */
    public boolean Update(int recNo, String partNum, String partDesc,
                          int qty, int cost) throws IOException {
        if(recNo < 0 || recNo >= NumRecs())
            throw new IllegalArgumentException(recNo + " out of range");
        raf.seek(recNo * RECLEN);
        return Write(partNum, partDesc, qty, cost);
    }


    /**
     * Delete all record
     * @throws IOException
     */
    public void Delete() throws IOException {
        raf.setLength(0);
        raf.getFD().sync();
    }


    /**
     * File read helper
     * @return A part
     * @throws IOException
     */
    private Part Read() throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < PNUMLEN ; i++)
            sb.append(raf.readChar());
        String partNum = sb.toString().trim();

        sb.setLength(0); // clean the buffer
        for(int i = 0 ; i < DESCLEN ; i++)
            sb.append(raf.readChar());
        String partDesc = sb.toString().trim();

        int qty  = raf.readInt();
        int cost = raf.readInt();

        return new Part(partNum, partDesc, qty, cost);
    }


    /**
     *
     * @param sb The StringBuffer instance
     * @param wt Writed string
     * @param length Write max length
     */
    private void WriteStringHelper(StringBuilder sb, String wt, int length) {
        if(wt.length() > length)
            sb.append(wt, 0, length);
        else {
            sb.append(wt);
            for(int i = 0 ; i < length - wt.length() ; ++i)
                sb.append(" ");
        }
    }


    /**
     * Write to a new record
     * @param partNum Part number
     * @param partDesc Part description
     * @param qty qty
     * @param cost cost
     * @return true or false
     * @throws IOException
     */
    private boolean Write(String partNum, String partDesc, int qty, int cost)
            throws IOException {

        StringBuilder sb = new StringBuilder();
        WriteStringHelper(sb, partNum, PNUMLEN);
        WriteStringHelper(sb, partDesc, DESCLEN);
        raf.writeChars(sb.toString());
        raf.writeInt(qty);
        raf.writeInt(cost);

        return true;
    }
}
