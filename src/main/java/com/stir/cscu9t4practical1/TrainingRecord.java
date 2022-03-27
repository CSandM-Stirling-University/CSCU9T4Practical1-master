// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;

public class TrainingRecord {
    private List<Entry> tr;

    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor

    // add a record to the list
    public void addEntry(Entry e) {
        tr.add(e);
    } // addClass

    // look up the entry of a given day and month
    public String lookupEntry(int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "No entries found";
        while (iter.hasNext()) {
            Entry current = iter.next();
            // swim
            if (current.getClass() == SwimEntry.class) {
                if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                    result = ((SwimEntry) current).getSwimEntry();
                    break;
                }
            }
            // sprint
            if (current.getClass() == SprintEntry.class) {
                if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                    result = ((SprintEntry) current).getSprintEntry();
                    break;
                }
            }
            // cycle
            if (current.getClass() == CycleEntry.class) {
                if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                    result = ((CycleEntry) current).getCycleEntry();
                    break;
                }
            }
            // generic training
            if (current.getClass() == Entry.class) {
                if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
                    result = current.getEntry();
            }
        }
        return result;
    } // lookupEntry

    public String findAllByDate(int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        if (!iter.hasNext()) {
            result = "No entries found";
        }
        while (iter.hasNext()) {
            Entry current = iter.next();
            System.out.println(current);
            if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                if (current.getClass() == Entry.class) {
                    result = result + current.getEntry();
                }
                if (current.getClass() == SwimEntry.class) {
                    result = result + ((SwimEntry) current).getSwimEntry();
                }
                if (current.getClass() == SprintEntry.class) {
                    result = result + ((SprintEntry) current).getSprintEntry();
                }
                if (current.getClass() == CycleEntry.class) {
                    result = result + ((CycleEntry) current).getCycleEntry();
                }
            }
            else {
                result = "No entries found";
            }
        }
        return result;
    }

    public void remove(String s, int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getName().equals(s) && current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
                System.out.println("Removing one record");
                iter.remove();
            }
        }
    }

    // Count the number of entries
    public int getNumberOfEntries() {
        return tr.size();
    }

    // Clear all entries
    public void clearAllEntries() {
        tr.clear();
    }

} // TrainingRecord