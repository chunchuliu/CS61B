import java.io.*;


class Nuke2 {
    public static void main(String[] args) throws IOException {
        BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer s = new StringBuffer(keybd.readLine());
        System.out.println(s.deleteCharAt(1).toString());
    }
}