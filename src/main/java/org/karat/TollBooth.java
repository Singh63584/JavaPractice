package org.karat;

import java.util.*;

public class TollBooth {
//    public static void main(String[] args) {
//        testLogEntry();
//        testLogFile();
//        testCountJourneys();
//        testCatchSpeeder();
//    }
//
//    // ======================================================
//    // LogEntry Class (Bug fixed: use split("\\s+"))
//    // ======================================================
//    static class LogEntry {
//        double timestamp;
//        String licensePlate;
//        String boothType;
//        int location;
//        String direction;
//
//        // "1000.000 TST002 270W ENTRY",
//        LogEntry(String logLine) {
//            String[] p = logLine.trim().split("\\s+");              // <-- bug fix here
//            timestamp = Double.parseDouble(p[0]);
//            System.out.println("timestamp : "+timestamp);
//            licensePlate = p[1];
//            System.out.println("licensePlate : "+licensePlate);
//            location = Integer.parseInt(p[2].substring(0, p[2].length() - 1));
//            System.out.println("Location : "+location);
//            direction = p[2].substring(p[2].length() - 1);
//            System.out.println("direction : "+direction);
//            boothType = p[3];
//            System.out.println("boothType : "+boothType);
//        }
//
//        public String toString() {
//            return timestamp + " " + licensePlate + " " + location + direction + " " + boothType;
//        }
//    }
//
//    // ======================================================
//    // LogFile Class
//    // ======================================================
//    static class LogFile {
//        List<LogEntry> logEntries;
//
//        LogFile() {
//            logEntries = new ArrayList<>();
//        }
//
//        LogFile(List<String> lines) {
//            logEntries = new ArrayList<>();
//            for (String s : lines) logEntries.add(new LogEntry(s));
//        }
//
//        void addLogEntry(String line) {
//            logEntries.add(new LogEntry(line));
//        }
//
//        int size() {
//            return logEntries.size();
//        }
//
//        // --------------------------------------------------
//        // Count Valid Journeys (simply count EXIT events)
//        // --------------------------------------------------
//        int countJourneys() {
//            int c = 0;
//            for (LogEntry e : logEntries) if ("EXIT".equals(e.boothType)) c++;
//            return c;
//        }
//
//        // --------------------------------------------------
//        // Catch Unsafe Drivers (count journeys with speeding)
//        // --------------------------------------------------
//        int countUnsafeJourneys() {
//            int total = 0;
//            Set<String> on = new HashSet<>(), unsafe = new HashSet<>();
//            Map<String, LogEntry> prev = new HashMap<>();
//            Map<String, Integer> cnt120 = new HashMap<>();
//            for (LogEntry cur : logEntries) {
//                String plate = cur.licensePlate, type = cur.boothType;
//                if ("ENTRY".equals(type)) {
//                    on.add(plate);
//                    prev.put(plate, cur);
//                    cnt120.put(plate, 0);
//                    unsafe.remove(plate);
//                    continue;
//                }
//                if (!on.contains(plate)) continue; // ignore if not on highway
//                LogEntry p = prev.get(plate);
//                if (p != null) {
//                    double distKm = Math.abs(cur.location - p.location); // should be 10 km per segment
//                    double dt = cur.timestamp - p.timestamp;             // seconds
//                    if (dt > 0 && distKm > 0) {
//                        double v = distKm * 3600.0 / dt;
//                        if (v >= 130.0) unsafe.add(plate);
//                        else if (v >= 120.0) {
//                            int k = cnt120.get(plate) + 1;
//                            cnt120.put(plate, k);
//                            if (k >= 2) unsafe.add(plate);
//                        }
//                    }
//                }
//                prev.put(plate, cur);
//                if ("EXIT".equals(type)) {
//                    if (unsafe.contains(plate)) total++;
//                    on.remove(plate);
//                    prev.remove(plate);
//                    cnt120.remove(plate);
//                    unsafe.remove(plate);
//                }
//            }
//            return total;
//        }
//    }
//
//    // ======================================================
//    // Test Methods
//    // ======================================================
//    static void testLogEntry() {
//        System.out.println("Running testLogEntry...");
//        LogEntry entry = new LogEntry("34400.409   SXY288   210E    ENTRY");
//        System.out.println(entry);
//        System.out.println();
//    }
//
//    static void testLogFile() {
//        System.out.println("Running testLogFile...");
//        LogFile logFile = new LogFile();
//        logFile.addLogEntry("10000.100 ABC123 10E ENTRY");
//        logFile.addLogEntry("10020.100 ABC123 20E EXIT");
//        System.out.println("Log size: " + logFile.size());
//        //Assert.assertEquals(2, logFile.size());
//        System.out.println();
//    }
//
//    static void testCountJourneys() {
//        System.out.println("Running testCountJourneys...");
//        List<String> logs = Arrays.asList(
//                "10000.000 CAR1 10E ENTRY",
//                "10100.000 CAR1 20E EXIT",
//                "11000.000 CAR2 50W ENTRY",
//                "11100.000 CAR2 60W EXIT",
//                "12000.000 CAR3 10E ENTRY" // incomplete
//        );
//        LogFile logFile = new LogFile(logs);
//        System.out.println("Total Journeys: " + logFile.countJourneys()); // expected 2
//        System.out.println();
//    }
//
//    static void testCatchSpeeder() {
//        System.out.println("Running testCatchSpeeder...");
//        List<String> logs = Arrays.asList(
//                // 130+ km/h segment
//                "1000.000 TST002 270W ENTRY",
//                "1275.000 TST002 260W EXIT",
//                // Two 120+ segments
//                "2000.000 CAR001 100E ENTRY",
//                "2250.000 CAR001 110E MAINROAD",
//                "2500.000 CAR001 120E EXIT",
//                // Safe journey
//                "3000.000 SAFE01 10E ENTRY",
//                "3600.000 SAFE01 20E EXIT"
//        );
//        LogFile logFile = new LogFile(logs);
//        System.out.println("Unsafe Journeys: " + logFile.countUnsafeJourneys()); // expected 2
//        System.out.println();
//    }
//}


    static class LogEntry {
        double t;
        String p, b;
        int l;
        String d;

        LogEntry(String s) {
            String[] a = s.trim().split("\\s+");
            t = Double.parseDouble(a[0]);
            p = a[1];
            l = Integer.parseInt(a[2].substring(0, a[2].length() - 1));
            d = a[2].substring(a[2].length() - 1);
            b = a[3];
        }
    }

    static class LogFile {
        List<LogEntry> e = new ArrayList<>();

        LogFile(List<String> l) {
            for (String s : l) e.add(new LogEntry(s));
        }

        int countJourneys() {
            int c = 0;
            for (LogEntry x : e) if ("EXIT".equals(x.b)) c++;
            return c;
        }

        int countUnsafeJourneys() {
            int ans = 0;
            Set<String> on = new HashSet<>(), bad = new HashSet<>();
            Map<String, LogEntry> prev = new HashMap<>();
            Map<String, Integer> c120 = new HashMap<>();
            for (LogEntry x : e) {
                String p = x.p;
                if ("ENTRY".equals(x.b)) {
                    on.add(p);
                    prev.put(p, x);
                    c120.put(p, 0);
                    bad.remove(p);
                    continue;
                }
                if (!on.contains(p)) continue;
                LogEntry y = prev.get(p);
                if (y != null) {
                    double v = Math.abs(x.l - y.l) * 3600 / (x.t - y.t);
                    if (v >= 130) bad.add(p);
                    else if (v >= 120 && c120.merge(p, 1, Integer::sum) >= 2) bad.add(p);
                }
                prev.put(p, x);
                if ("EXIT".equals(x.b)) {
                    if (bad.contains(p)) ans++;
                    on.remove(p);
                    prev.remove(p);
                    c120.remove(p);
                    bad.remove(p);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList("1000.000 TST002 270W ENTRY", "1275.000 TST002 260W EXIT", "2000.000 CAR001 100E ENTRY", "2250.000 CAR001 110E MAINROAD", "2500.000 CAR001 120E EXIT", "3000.000 SAFE01 10E ENTRY", "3600.000 SAFE01 20E EXIT");
        LogFile f = new LogFile(logs);
        System.out.println(f.countJourneys());      // 3
        System.out.println(f.countUnsafeJourneys()); // 2
    }
}