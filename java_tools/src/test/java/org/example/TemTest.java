package org.example;

import org.junit.jupiter.api.Test;
import ssh.SSHTools;

/**
 * @author ean
 * @FileName TemTest
 * @Date 2022/12/2 17:42
 **/
public class TemTest {


    @Test
    public void testSSH() {
        SSHTools ssh = new SSHTools();
        ssh.ipAddr = "114.55.33.197";
        ssh.username = "root";
        ssh.password = "B1acksuit";

        ssh.login();

        String testCmd = "ls";
        StringBuilder execResult = ssh.exec(testCmd);
        System.out.println(execResult.toString());
    }
}
