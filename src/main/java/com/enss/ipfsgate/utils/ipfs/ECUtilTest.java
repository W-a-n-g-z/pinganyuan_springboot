package com.enss.ipfsgate.utils.ipfs;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ECUtilTest {
    public void test(){
        try {

            /* play.min.io for test and development. */
            MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F",
                    "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");

            /* Amazon S3: */
            // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
            //
            // Upload 'my-filename' as object 'my-objectname' in 'my-bucketname'.
            minioClient.putObject("my-bucketname", "my-objectname", "my-filename", null, null, null, null);
            System.out.println("my-filename is uploaded to my-objectname successfully");
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException | XmlPullParserException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
