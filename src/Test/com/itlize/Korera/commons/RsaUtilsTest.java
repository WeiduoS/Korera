package com.itlize.Korera.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Weiduo
 * @date 2020/1/20 - 11:32 AM
 */
public class RsaUtilsTest {

    private String privateFilePath = "src/Test/resources/auth_key/id_key_rsa";
    private String publicFilePath = "src/Test/resources/auth_key/id_key_rsa.pub";

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicFilePath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateFilePath));
    }

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicFilePath, privateFilePath, "hello", 2048);
    }
}