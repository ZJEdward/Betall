package com.betall.app.util;

import android.content.Context;

import com.betall.app.MyApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by fly on 2018/2/3.
 */

public final class FileUtil {
    public static void write(String filename, String content) {

        MyApplication app = MyApplication.shared();

        BufferedWriter writer = null;
        try {
            FileOutputStream outputStream = app.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String read(String filename) {
        MyApplication app = MyApplication.shared();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            FileInputStream inputStream = app.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                if (line != null)
                    stringBuilder.append(line);
                else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
