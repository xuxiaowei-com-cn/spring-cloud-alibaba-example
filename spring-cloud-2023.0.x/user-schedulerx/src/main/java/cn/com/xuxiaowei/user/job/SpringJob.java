package cn.com.xuxiaowei.user.job;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringJob {

	/**
	 * 每5分钟跑一次
	 */
	@Scheduled(cron = "0 */5 * * * ?")
	@SchedulerLock(name = "SpringJob.2023.0.x.job1", lockAtMostFor = "2m", lockAtLeastFor = "1m")
	public void job1() {
		System.out.println("time=" + DateTime.now().toString("YYYY-MM-dd HH:mm:ss") + " do job1...");
	}

	/**
	 * 每5秒跑一次
	 */
	@Scheduled(fixedRate = 5000)
	@SchedulerLock(name = "SpringJob.2023.0.x.job2", lockAtMostFor = "4s", lockAtLeastFor = "4s")
	public void job2() {
		System.out.println("time=" + DateTime.now().toString("YYYY-MM-dd HH:mm:ss") + " do job2...");
	}

	/**
	 * 上次跑完之后隔5秒再跑
	 * @throws InterruptedException
	 */
	@Scheduled(fixedDelay = 5000)
	@SchedulerLock(name = "SpringJob.2023.0.x.job3", lockAtMostFor = "4s", lockAtLeastFor = "4s")
	public void job3() throws InterruptedException {
		System.out.println("time=" + DateTime.now().toString("YYYY-MM-dd HH:mm:ss") + " do job3...");
		Thread.sleep(10000);
	}

}
