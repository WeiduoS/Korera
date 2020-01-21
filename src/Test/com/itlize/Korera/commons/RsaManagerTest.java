package com.itlize.Korera.commons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/20 - 12:31 PM
 */

@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RsaManagerTest {

    @Autowired
    @Qualifier("rsaManager")
    RsaManager manager;


    private String privateFilePath = "src/main/resources/auth_key/id_key_rsa";
    private String publicFilePath = "src/main/resources/auth_key/id_key_rsa.pub";

    @Test
    public void generateRsaKey() throws Exception {
//        RsaUtils.generateKey(publicFilePath, privateFilePath, "hello", 2048);
        System.out.println(manager.getPublicKey());
        System.out.println(manager.getPrivateKey());
    }

}