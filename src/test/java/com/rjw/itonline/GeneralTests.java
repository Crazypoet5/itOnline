package com.rjw.itonline;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @program: itonline
 * @description:
 * @author: 饶嘉伟
 * @create: 2020-12-09 20:10
 **/

public class GeneralTests {

    @Test
    void changeFiles() throws IOException {
        BufferedWriter bos=new BufferedWriter(
                new FileWriter (new File ("output.txt")));
        BufferedReader bis=new BufferedReader (
                (new FileReader (new File("input.txt"))));
        String s="";
        while((s=bis.readLine ())!=null){
            if(s.trim ().length ()<3){
                if(s.length ()>0&&'0'<=s.charAt (0)&&s.charAt (0)<='9'){
                    continue;
                }
            }
            bos.write (s);
            bos.newLine ();
            bos.flush ();
        }
        bis.close ();
        bos.close ();
    }
}
