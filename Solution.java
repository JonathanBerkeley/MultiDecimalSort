package googlechallenge;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Jonathan
 */
public class Solution {

    public static String[] solution(String[] l) {
        String sortedList[] = new String[l.length];
        ArrayList<UnpackedNumber> unpackedList = new ArrayList<>();

        //Looping over list, unpacking number and adding it to arraylist
        for (int i = 0; i < l.length; ++i) {
            unpackedList.add(new UnpackedNumber(i, l[i]));
        }

        //Sort arraylist
        unpackedList = sortUnpacked(unpackedList);
        for (int i = 0; i < unpackedList.size(); ++i) {
            //System.out.println(unpackedList.get(i));
        }

        //Unpack from arraylist into sortedlist
        for (int i = 0; i < unpackedList.size(); ++i) {
            //If section 1, 2 & 3 aren't empty
            if (!(unpackedList.get(i).getSection(1).equals("")) && !(unpackedList.get(i).getSection(2).equals("")) && !(unpackedList.get(i).getSection(3).equals(""))) {
                sortedList[i] = (unpackedList.get(i).getSection(1)
                        + "." + unpackedList.get(i).getSection(2)
                        + "." + unpackedList.get(i).getSection(3));
            } else if (unpackedList.get(i).getSection(2).equals("") && unpackedList.get(i).getSection(3).equals("")) {
                sortedList[i] = (unpackedList.get(i).getSection(1));
            } else if (unpackedList.get(i).getSection(3).equals("")) {
                sortedList[i] = (unpackedList.get(i).getSection(1)
                        + "." + unpackedList.get(i).getSection(2));
            }
        }
        return sortedList;
    }

    private static ArrayList<UnpackedNumber> sortUnpacked(ArrayList<UnpackedNumber> un) {
        UnpackedNumber tmp;

        //Loop ordering
        for (int x = 0; x < un.size(); ++x) {
            //Loop over arraylist
            for (int i = 0; i < un.size(); ++i) {
                try {
                    //Sort for section 1
                    if (un.get(i).getSectionAsDouble(1) > un.get(i + 1).getSectionAsDouble(1)) {
                        tmp = un.get(i);
                        un.set(i, un.get(i + 1));
                        un.set(i + 1, tmp);
                    }
                } catch (Exception ex) {
                    break;
                }
            }
        }

        for (int x = 0; x < un.size(); ++x) {
            //Loop over arraylist
            for (int i = 0; i < un.size(); ++i) {
                try {
                    //Sort for section 2
                    if (Objects.equals(un.get(i).getSectionAsDouble(1), un.get(i + 1).getSectionAsDouble(1))) {
                        if (un.get(i).getSectionAsDouble(2) != null) {
                            tmp = un.get(i);
                            un.set(i, un.get(i + 1));
                            un.set(i + 1, tmp);
                            if (un.get(i).getSectionAsDouble(2) > un.get(i + 1).getSectionAsDouble(2)) {
                                //System.out.println(un.get(i).getSection(1) + "." + un.get(i).getSection(2) + " is bigger than " + un.get(i + 1).getSection(1) + "." + un.get(i + 1).getSection(2));
                                tmp = un.get(i);
                                un.set(i, un.get(i + 1));
                                un.set(i + 1, tmp);
                            }
                        }
                    }
                } catch (Exception ex) {
                    break;
                }
            }
        }

        for (int x = 0; x < un.size(); ++x) {
            //Loop over arraylist
            for (int i = 0; i < un.size(); ++i) {
                try {
                    //Sort for section 3
                    if (Objects.equals(un.get(i).getSectionAsDouble(1), un.get(i + 1).getSectionAsDouble(1)) && Objects.equals(un.get(i).getSectionAsDouble(2), un.get(i + 1).getSectionAsDouble(2))) {
                        if (un.get(i).getSectionAsDouble(3) != null) {
                            tmp = un.get(i);
                            un.set(i, un.get(i + 1));
                            un.set(i + 1, tmp);
                            if (un.get(i).getSectionAsDouble(3) > un.get(i + 1).getSectionAsDouble(3)) {
                                tmp = un.get(i);
                                un.set(i, un.get(i + 1));
                                un.set(i + 1, tmp);
                            }
                        }
                    }
                } catch (Exception ex) {
                    break;
                }
            }
        }

        return un;
    }

}

class UnpackedNumber {

    public static int id = 0;
    int sid = 0;
    String section1, section2, section3;

    public UnpackedNumber(String s1, String s2, String s3) {
        this.section1 = s1;
        this.section2 = s2;
        this.section3 = s3;
    }

    public UnpackedNumber(String fullNum) {
        UnpackedNumber unpacked = unpackString(fullNum);
        this.section1 = unpacked.section1;
        this.section2 = unpacked.section2;
        this.section3 = unpacked.section3;
    }

    public UnpackedNumber(int id, String fullNum) {
        this.sid = id;
        UnpackedNumber unpacked = unpackString(fullNum);
        this.section1 = unpacked.section1;
        this.section2 = unpacked.section2;
        this.section3 = unpacked.section3;
    }

    public static UnpackedNumber unpackString(String version) {
        ++id;
        String num1 = "";
        String num2 = "";
        String num3 = "";
        int dotCount = 0;
        //Looping over word
        for (int i = 0; i < version.length(); ++i) {
            if (version.charAt(i) == '.') {
                ++dotCount;
            } else {
                if (dotCount == 0) {
                    num1 += version.charAt(i);
                } else if (dotCount == 1) {
                    num2 += version.charAt(i);
                } else if (dotCount == 2) {
                    num3 += version.charAt(i);
                } else {
                    break;
                }
            }
        }
        UnpackedNumber un = new UnpackedNumber(num1, num2, num3);
        return un;
    }

    public String getSection(int section) {
        switch (section) {
            case 1:
                return this.section1;
            case 2:
                return this.section2;
            case 3:
                return this.section3;
            default:
                return null;
        }
    }

    public Double getSectionAsDouble(int section) {
        switch (section) {
            case 1:
                return (this.section1.equals("")) ? null : Double.parseDouble(this.section1);
            case 2:
                return (this.section2.equals("")) ? null : Double.parseDouble(this.section2);
            case 3:
                return (this.section3.equals("")) ? null : Double.parseDouble(this.section3);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.sid
                + "\nSection 1: " + this.getSection(1)
                + "\nSection 2: " + this.getSection(2)
                + "\nSection 3: " + this.getSection(3);
    }
}
