package controllers;

import java.io.*;

public abstract class EasyBufferInt {

    static RandomAccessFile canelita;

    public static void createBuffer(String dir) throws IOException
    {
        canelita = new RandomAccessFile(dir, "rw");
    }

    public static void closeBuffer() throws IOException
    {
        canelita.close();
    }

    public static void writeLine(String dir, int line) throws IOException
    {
        createBuffer(dir);
        canelita.writeInt(line);
        closeBuffer();
    }

    public static int readLine(String dir) throws IOException
    {
        createBuffer(dir);
        int line = canelita.readInt();
        closeBuffer();
        return line;
    }
}