package com.biz.configServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigServer
@Slf4j
public class ConfigServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ConfigServerApplication.class);
        Environment env = app.run(args).getEnvironment();

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }

        // @formatter:off

		log.info("\n----------------------------------------------------------\n\t" +
				"Application '{}' is running!\n\t" +
				"Access URLs\n\t" +
				"Local: \t\t{}://localhost:{}\n\t" +
				"External: \t{}://{}:{}\n" +
				"----------------------------------------------------------",
			env.getRequiredProperty("info.app.name"),
			protocol,
			env.getRequiredProperty("server.port"),
			protocol,
			InetAddress.getLocalHost().getHostAddress(),
			env.getRequiredProperty("server.port")
		);

		// @formatter:on
    }

}
