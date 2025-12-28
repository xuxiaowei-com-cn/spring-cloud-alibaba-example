package cn.com.xuxiaowei.user;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "3m")
@SpringBootApplication
public class UserSchedulerxApplication_2025_1_x {

	public static void main(String[] args) {
		SpringApplication.run(UserSchedulerxApplication_2025_1_x.class, args);
	}

}
