package com.example.restapimvc.scraper.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;

public class CsvUtil {
    public static <T extends CsvConvertible> void toCsv(Collection<T> csvConvertibles, String filePath, Boolean canAppend) {
        File csv = new File(filePath);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csv,canAppend));
            for (CsvConvertible csvConvertible: csvConvertibles) {
                bw.write(csvConvertible.toCsvString());
                bw.newLine();
            }
        } catch (Exception e) {
        }finally {
            try {
                if (bw!=null) {
                    bw.flush();
                    bw.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
