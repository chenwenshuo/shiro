package com.example.demo1;

import com.example.demo1.bean.User;
import com.example.demo1.bean.User1;
import com.example.demo1.repository.UserRepository;
import junit.framework.Assert;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.security.Key;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

    @Autowired
    UserRepository userRepository;


      //Base64加密解密
    public void testBase64(String password) {
        User user=new User();
        user.setUsername("a");
        //加密
        String base64Encoded = Base64.encodeToString(password.getBytes());
        user.setPassword(base64Encoded);
        userRepository.save(user);
        String str2 = Base64.decodeToString(userRepository.findUserByUsername("a").getPassword());
        System.out.println(base64Encoded);
        System.out.println(str2);

    }
    @Test
    public void test() throws Exception {
        String password="admin123";
        //Base64加密解密
        testBase64(password);
       //Hex进行 16 进制字符串编码 / 解码操作
        testHex(password);
        //双向经典加密/解密算法
        // AES 和 Blowfish两种加密解密算法。
        //AES 对称式加密/解密算法
        testAesCipherService(password);
        // Blowfish加密解密算法。
        testBlowfishCipherService(password);
        //分组加密，分组是指加密的过程是先进行分组，然后加密。AES 和 Blowfish都是分组加密算法
        testDefaultBlockCipherService(password);
    }

   //Hex进行 16 进制字符串编码 / 解码操作
    public void testHex(String password) {
        User user=new User();
        user.setUsername("cwsb");
        String hexEncoded = Hex.encodeToString(password.getBytes());
        user.setPassword(hexEncoded);
        userRepository.save(user);
        //Hex.decode(base64Encoded.getBytes()
        String str2 = new String(Hex.decode(userRepository.findUserByUsername("cwsb").getPassword().getBytes()));
        System.out.println("---------------------------");
        System.out.println(password+":"+str2);
        System.out.println(hexEncoded);

    }


    // toBytes(str,"utf-8") / toString(bytes,"utf-8") 用于在 byte 数组 /String
    public void testCodecSupport() {
        String password="admin123";
        User user=new User();
        user.setUsername("cwsc");
        byte[] bytes = CodecSupport.toBytes(password, "utf-8");

        user.setPassword(bytes.toString());
        userRepository.save(user);
        String str2 = CodecSupport.toString(userRepository.findUserByUsername("cwsc").getPassword().getBytes(), "utf-8");
        System.out.println("-------------------");
        System.out.println(bytes);
        System.out.println(str2);
    }





    //双向经典加密/解密算法
    // AES 和 Blowfish两种加密解密算法。
    //AES 对称式加密/解密算法
    public void testAesCipherService(String password) {
        User user=new User();
        user.setUsername("cwsd");
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);//设置key长度

        //生成key
        Key key = aesCipherService.generateNewKey();

        //加密
        String encrptText = aesCipherService.encrypt(password.getBytes(), key.getEncoded()).toHex();
        //解密
        user.setPassword(encrptText);
        userRepository.save(user);
        String text2 = new String(aesCipherService.decrypt(Hex.decode(userRepository.findUserByUsername("cwsd").getPassword()), key.getEncoded()).getBytes());
        System.out.println(text2);

    }

//BlowFish
    public void testBlowfishCipherService(String password) {
        User user=new User();
        user.setUsername("cwse");
        BlowfishCipherService blowfishCipherService = new BlowfishCipherService();
        blowfishCipherService.setKeySize(128);

        //生成key
        Key key = blowfishCipherService.generateNewKey();
        //加密
        String encrptText = blowfishCipherService.encrypt(password.getBytes(), key.getEncoded()).toHex();
        //解密
        user.setPassword(encrptText);
        userRepository.save(user);
        String text2 = new String(blowfishCipherService.decrypt(Hex.decode(userRepository.findUserByUsername("cwse").getPassword()), key.getEncoded()).getBytes());

    }


    //分组加密，分组是指加密的过程是先进行分组，然后加密。AES 和 Blowfish都是分组加密算法
    public void testDefaultBlockCipherService(String password) throws Exception {
        User user=new User();
        user.setUsername("cwsf");
        //对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
        DefaultBlockCipherService cipherService = new DefaultBlockCipherService("AES");
        cipherService.setKeySize(128);

        //生成key
        Key key = cipherService.generateNewKey();

        //加密
        String encrptText = cipherService.encrypt(password.getBytes(), key.getEncoded()).toHex();
        user.setPassword(encrptText);
        userRepository.save(user);
        //解密
        String text2 = new String(cipherService.decrypt(Hex.decode(userRepository.findUserByUsername("cwsf").getPassword()), key.getEncoded()).getBytes());
        System.out.println(text2);
    }


    @Test
    public void contextLoads() {
        List<User> list = userRepository.findAll();
        for (User user : list) {
            System.out.println(  user.toString());
        }

    }





    //散列算法
    //散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如 MD5、SHA 等
    @Test
    public void testMd5() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
        String md51 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
        System.out.println(md5);
        System.out.println(md51);


    }





    @Test
    public void testHashService() {
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }
    @Test
    public void testRandom() {
        //生成随机数
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        System.out.println(randomNumberGenerator.nextBytes().toHex());
    }

    @Test
    public void test01(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置key长度
//生成key
        Key key = aesCipherService.generateNewKey();
        String text = "admin";
//加密
        String encrptText =
                aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
/*//解密
        String text2 =
                new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
        Assert.assertEquals(text, text2);*/
User user=new User();
user.setUsername("cws");
user.setPassword(encrptText);
        userRepository.save(user);
        List<User> list = userRepository.findAll();
        for (User user1 : list) {
            System.out.println( user1.toString());
        }
    }





    @Test
    public void testSha1() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str, salt).toString();
        System.out.println(sha1);

    }


    public void testSha256() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);

    }


    public void testSha384() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha384Hash(str, salt).toString();
        System.out.println(sha1);

    }


    public void testSha512() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        System.out.println(sha1);

    }


    public void testSimpleHash() {
        String str = "hello";
        String salt = "123";
        //MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);

    }

    private static final Logger log = LoggerFactory.getLogger(Demo1ApplicationTests.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;


    @Test
    public void get() {
        // TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);

        String key = "battcn:user:1";
        redisCacheTemplate.opsForValue().set(key, new User1(1,"陈文硕","167989"));
        final User1 user1 = (User1) redisCacheTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user1);
    }
}
