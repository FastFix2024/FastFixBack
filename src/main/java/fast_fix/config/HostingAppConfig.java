//package fast_fix.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HostingAppConfig {
//    @Bean
//    public AmazonS3 hostingClient(HostingProperties properties){
//        AWSCredentials credentials =
//                new BasicAWSCredentials(
//                        properties.getAccessKey(),
//                        properties.getSecretKey()
//                );
//        AwsClientBuilder.EndpointConfiguration endpointConfiguration =
//                new AwsClientBuilder.EndpointConfiguration(
//                        properties.getEndpoint(),
//                        properties.getRegion()
//                );
//        AmazonS3ClientBuilder clientBuilder= AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withEndpointConfiguration(endpointConfiguration);
//        return clientBuilder.build();
//    }
//}
