/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangwordapp;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author acer
 */
public class Service {

    private TreeMap<String, List<String>> map = new TreeMap<>();
    private static Service obj = new Service();
    private int sizeMap;
    private String FILE_SLANGWORD = "slang.txt";
    private String FILE_ORIGINAL_SLANGWORD = "slang-root.txt";
    private static String FILE_HISTORY = "history.txt";

    Service() {
        try {
            String current = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + current);
            FILE_SLANGWORD = current + "\\" + FILE_SLANGWORD;
            FILE_ORIGINAL_SLANGWORD = current + "\\" + FILE_ORIGINAL_SLANGWORD;
            FILE_HISTORY = current + "\\" + FILE_HISTORY;
            readFile(FILE_SLANGWORD);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Service getInstance() {
        if (obj == null) {
            synchronized (Service.class) {
                if (obj == null) {
                    obj = new Service();// instance will be created at request time
                }
            }
        }
        return obj;
    }

    public void readFile(String file) throws Exception {
        map.clear();
        String slag = null;
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("`");
        scanner.next();
        String temp = scanner.next();
        String[] part = temp.split("\n");
        int i = 0;
        int flag = 0;
        sizeMap = 0;
        while (scanner.hasNext()) {
            List<String> meaning = new ArrayList<String>();
            slag = part[1].trim();
            temp = scanner.next();
            part = temp.split("\n");
            if (map.containsKey(slag)) {
                meaning = map.get(slag);
            }
            if (part[0].contains("|")) {
                //System.out.println(part[0]);
                String[] d = (part[0]).split("\\|");
                //for (int ii = 0; ii < d.length; ii++) {
                //System.out.println(d[ii]);
                //}
                Collections.addAll(meaning, d);
                sizeMap += d.length - 1;
            } else {
                meaning.add(part[0]);
            }
            // map.put(slag.trim(), meaning);
            map.put(slag, meaning);
            i++;
            sizeMap++;
        }
        scanner.close();
    }

    public void saveFile(String file) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(file));
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Slag`Meaning\n");
            String s[][] = new String[map.size()][3];
            Set<String> keySet = map.keySet();
            Object[] keyArray = keySet.toArray();
            for (int i = 0; i < map.size(); i++) {
                Integer in = i + 1;
                s[i][0] = in.toString();
                s[i][1] = (String) keyArray[i];
                List<String> meaning = map.get(keyArray[i]);
                stringBuilder.append(s[i][1] + "`" + meaning.get(0));
                for (int j = 1; j < meaning.size(); j++) {
                    stringBuilder.append("|" + meaning.get(j));
                }
                stringBuilder.append("\n");
            }
            // System.out.println(stringBuilder.toString());
            printWriter.write(stringBuilder.toString());
            printWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    public String[][] getData() {
        String s[][] = new String[sizeMap][3];
        Set<String> slagListSet = map.keySet();
        Object[] slagList = slagListSet.toArray();
        int index = 0;
        for (int i = 0; i < sizeMap; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = (String) slagList[index];
            List<String> meaning = map.get(slagList[index]);
            s[i][2] = meaning.get(0);
            System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
            for (int j = 1; j < meaning.size(); j++) {
                if (i < sizeMap) {
                    i++;
                }
                s[i][0] = String.valueOf(i);
                s[i][1] = (String) slagList[index];
                s[i][2] = meaning.get(j);
                System.out.println(s[i][0] + "\t" + s[i][1] + "\t" + s[i][2]);
            }
            index++;
        }
        return s;
    }

    public String[][] getMeaning(String key) {
        List<String> listMeaning = map.get(key);
        if (listMeaning == null) {
            return null;
        }
        int size = listMeaning.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = key;
            s[i][2] = listMeaning.get(i);
        }
        return s;
    }

    public static String[][] searchSlangWord(String key) {
        String[][] tempStringses = null;
        tempStringses = obj.getMeaning(key);
        try {
            for (int j = 0; j < tempStringses.length; j++) {
                obj.saveHistory(tempStringses[j][1], tempStringses[j][2]);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return tempStringses;
    }

    public String[][] findDefinition(String query) {
        // Get all slang contain key
        List<String> keyList = new ArrayList<>();
        List<String> meaningList = new ArrayList<>();
        for (Entry<String, List<String>> entry : map.entrySet()) {
            List<String> meaning = entry.getValue();
            for (int i = 0; i < meaning.size(); i++) {
                if (meaning.get(i).toLowerCase().contains(query.toLowerCase())) {
                    keyList.add(entry.getKey());
                    meaningList.add(meaning.get(i));
                }
            }
        }
        int size = keyList.size();
        String s[][] = new String[size][3];

        for (int i = 0; i < size; i++) {
            s[i][0] = String.valueOf(i);
            s[i][1] = keyList.get(i);
            s[i][2] = meaningList.get(i);
        }
        return s;
    }

    public static String[][] searchDefinition(String key) {
        String[][] tempStringses = null;
        tempStringses = obj.findDefinition(key);
        try {
            for (int j = 0; j < tempStringses.length; j++) {
                obj.saveHistory(tempStringses[j][1], tempStringses[j][2]);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return tempStringses;
    }

    public void saveHistory(String slag, String meaning) throws Exception {
        // String file = "history.txt";
        File file1 = new File(FILE_HISTORY);
        FileWriter fr = new FileWriter(file1, true);
        fr.write(slag + "`" + meaning + "\n");
        fr.close();
    }

    public static String[][] readHistory() {
        List<String> historySlag = new ArrayList<>();
        List<String> historyDefinition = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILE_HISTORY));
            scanner.useDelimiter("`");
            String temp = scanner.next();
            String[] part = scanner.next().split("\n");
            historySlag.add(temp);
            historyDefinition.add(part[0]);
            while (scanner.hasNext()) {
                temp = part[1];
                part = scanner.next().split("\n");
                historySlag.add(temp);
                historyDefinition.add(part[0]);
            }
            scanner.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int size = historySlag.size();
        String s[][] = new String[size][3];
        for (int i = 0; i < size; i++) {
            s[size - i - 1][0] = String.valueOf(size - i);
            s[size - i - 1][1] = historySlag.get(i);
            s[size - i - 1][2] = historyDefinition.get(i);
        }
        return s;
    }

}
