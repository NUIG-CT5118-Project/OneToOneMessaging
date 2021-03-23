package com.messageattempt.springbootmessage;

import com.messageattempt.springbootmessage.dao.MessageRepository;
import com.messageattempt.springbootmessage.dao.UserRepository;
import com.messageattempt.springbootmessage.entity.Message;
import com.messageattempt.springbootmessage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootMessageApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMessageApplication.class, args);
	}

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		// user
		// create users
		User user1 = new User("Bernie", "BL4");
		User user2 = new User("Chris", "CS2");
		User user3 = new User("Jane", "JE9");
		User user4 = new User("Bob", "BB6");

		// save users
		this.userRepository.save(user1);
		this.userRepository.save(user2);
		this.userRepository.save(user3);
		this.userRepository.save(user4);

		// get all users
		System.out.println(userRepository.findAll());
		System.out.println(userRepository.findById(1l));
		System.out.println(userRepository.findByEmployeeNumber("BL4"));

		// message
		// create messages, i.e. send messages
		Message message1 = new Message("Here is message one", 1l, 2l);
		Message message2 = new Message("Here is message two", 2l, 3l);
		Message message3 = new Message("Here is message three", 3l, 2l);
		Message message4 = new Message("Here is message four", 2l, 1l);
		Message message5 = new Message("Here is message five", 1l, 2l);
		Message message6 = new Message("Here is message six", 2l, 3l);
		Message message7 = new Message("Here is message seven", 3l, 2l);
		Message message8 = new Message("Here is message eight", 2l, 1l);
		Message message9 = new Message("Here is message nine", 4l, 3l);

		// save messages
		this.messageRepository.save(message1);
		this.messageRepository.save(message2);
		this.messageRepository.save(message3);
		this.messageRepository.save(message4);
		this.messageRepository.save(message5);
		this.messageRepository.save(message6);
		this.messageRepository.save(message7);
		this.messageRepository.save(message8);
		this.messageRepository.save(message9);

		// delete message
		this.messageRepository.deleteById(1l);

	}


}
