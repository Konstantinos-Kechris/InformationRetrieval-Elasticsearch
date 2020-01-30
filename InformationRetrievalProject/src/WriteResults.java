import java.io.*;
import java.util.StringTokenizer;

public class WriteResults {

    public static void main (String [] args) {

        String file_name = "";
        String names [] = new String [10];

        for (int i = 0; i < 10; i++) {

            names[i] = "Q_" + Integer.toString(i+1) + ".txt";

        }

        for (int j = 0; j < 10; j++) {

            int counter = 0;
            file_name = "C:\\Users\\kosta\\Desktop\\Results\\" +names[j];
            System.out.println(file_name);

            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name));
                String st;
                while ((st = br.readLine()) != null) {
                    if (st.contains("_score") && !st.contains("max_score")) {
                        st = st.replaceAll("\"","");
                        st = st.replaceAll(",","");
                        st = st.replaceAll("\\s+","");
                        StringTokenizer multiTokenizer2 = new StringTokenizer(st, ":");
                        while (multiTokenizer2.hasMoreTokens()) {
                            counter++;
                            if (counter == 1) {
                                st = multiTokenizer2.nextToken();
                                counter = 0;
                            }
                        }
                        System.out.print(st);
                        System.out.print("\t");
                    }
                    if (st.contains("Rcn")) {
                        st = st.replaceAll("\"","");
                        st = st.replaceAll(",","");
                        st = st.replaceAll("\\s+","");
                        StringTokenizer multiTokenizer = new StringTokenizer(st, ":");
                        while (multiTokenizer.hasMoreTokens()) {
                            counter++;
                            if (counter == 1) {
                                st = multiTokenizer.nextToken();
                                counter = 0;
                            }
                        }
                        System.out.print(st);
                        System.out.println();
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }
}