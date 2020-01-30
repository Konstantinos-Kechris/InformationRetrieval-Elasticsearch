import java.io.*;

public class test {

    public static void main(String [] args) {

        int RCN [] = new int [200];
        double SCORE [] = new double[200];
        String file_name = "C:\\Users\\kosta\\Desktop\\new_res.txt";
        int counter = 0;
        String part1 = "";
        String part2 = "";
        int num = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(file_name));
            String st;

            while ((st = br.readLine()) != null) {

                String result = st.replaceAll("\\s",",");
                String[] parts = result.split(",");
                part1 = parts[0];
                part2 = parts[1];

                for (int i = 0; i < 2; i++) {

                    SCORE[counter] = Double.parseDouble(part1);
                    RCN[counter] = Integer.parseInt(part2);

                }

                counter++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = "";
        String rcn = "";
        String score = "";
        String file_name1 = "C:\\Users\\kosta\\Desktop\\RESULTS.txt";

        try(FileWriter file = new FileWriter(file_name1)) {

            File fout = new File(file_name1);
            FileOutputStream fos = new FileOutputStream(fout);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            for (int i = 0; i < 200; i++) {

                num = (i/20) + 1;

                if (num != 10) {

                    s = "Q0" + Integer.toString(num);

                } else {

                     s = "Q10";
                }

                bw.write(s+ "\t" + "0" + "\t" +Integer.toString(RCN[i])+ "\t" + "0" +  "\t" + Double.toString(SCORE[i]) + "\t" + "myIRmethod");
                bw.newLine();

            }

            bw.close();

        } catch (IOException e1) {

            e1.printStackTrace();

        }



    }

}
