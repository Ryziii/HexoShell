package core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class Exec{

    public Object start(String cmd){
        String[] cmdA = {"zsh","-c",cmd};
        try {
            Process process = Runtime.getRuntime().exec(cmdA);
            if(!cmd.equals(Watch.hexos)) {
                LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
//                System.out.println(line);
                    sb.append(line).append("\n");
                }
                if (process != null)
                    process.destroy();
                return sb.toString();
            }
//            return "成功";

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错误1");
        }
        return null;
    }
}