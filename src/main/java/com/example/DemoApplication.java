package com.example;

import com.example.model.tables.Player;
import com.example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private PlayerRepository repository;

	@Override
	public void run(String... args) throws Exception{
		this.repository.deleteAll();

		this.repository.save(new Player("player1", "xxx"));
		this.repository.save(new Player("player2", "xxx"));
		this.repository.save(new Player("admin", "admin"));


		System.out.println("Players found with findAll():");
		System.out.println("-------------------------------");
		for(Player player : this.repository.findAll()){
			System.out.println(player);
		}
		System.out.println();

		System.out.println("Players found with findByLogin()");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByLogin("player1"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
