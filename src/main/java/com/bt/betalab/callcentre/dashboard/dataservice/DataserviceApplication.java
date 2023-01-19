package com.bt.betalab.callcentre.dashboard.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;
import org.hibernate.dialect.PostgreSQL95Dialect;

@SpringBootApplication
@TypeHint(types = PostgreSQL95Dialect.class, typeNames = "org.hibernate.dialect.PostgreSQLDialect")
public class DataserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataserviceApplication.class, args);
	}

}
