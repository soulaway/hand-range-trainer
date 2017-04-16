package org.aws.ec2.man;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.ec2.model.IpRange;

public class AppTest {
	
	public final static Logger log = Logger.getLogger(AppTest.class.getName());
	
	private static final String TEST_IMG_ID = "ami-5b06d634";
	
	private static final String PREFIX_RESERVATION_ID = "ReservationId:";
	
	private static final String NO_CONTENT_OK_RESPONCE = "{}";
	
	@BeforeClass
	public static void init(){
		System.setProperty("ec2man.env.prop.path", "//home//soul//eclipse//space//tests//ng-app-workspase//hand-range-trainer//hand-range-trainer//deployment//aws-ec2-man//src//test//resources//greenman.properties");
		System.setProperty("ec2man.env.prop.region", "eu-central-1");
	}
	
	@Test
	public void awsEc2LifeciceTest() throws FileNotFoundException, IllegalArgumentException, IOException {

		Ec2Man man = new Ec2Man();

		String sgName = "SG " + System.currentTimeMillis();
		
		String creteSGresponse = man.createSecutiryGroup(sgName, sgName);
		log.log(Level.INFO, "create SG " + sgName + " \n response: " + creteSGresponse);
		Assert.assertTrue(creteSGresponse.contains("GroupId"));
		
		String authSGresponse = man.authorizeSecutiryGroup(sgName, "tcp", Arrays.asList(new IpRange[] {
				new IpRange().withCidrIp("111.111.111.111/32"), 
				new IpRange().withCidrIp("150.150.150.150/32") 
		}), 22);
		log.log(Level.INFO, "authorize SG " + sgName + " \n response: " + authSGresponse);
		Assert.assertTrue(authSGresponse.equals(NO_CONTENT_OK_RESPONCE));
		
		String createInstanceResponse = man.createInstance(TEST_IMG_ID, "t2.micro", "mrgreen", sgName);
		Assert.assertTrue(
				createInstanceResponse.contains(PREFIX_RESERVATION_ID) 
				&& createInstanceResponse.contains(Ec2Man.PREFIX_INSTANCE_ID)
				&& createInstanceResponse.contains(Ec2Man.PREFIX_IMAGE_ID + " " + TEST_IMG_ID));

		String instanseId = Ec2Man.parseInstanseId(createInstanceResponse);

		log.log(Level.INFO, "created instanse " + instanseId + " \n response: " + createInstanceResponse);

		log.log(Level.INFO, "waiting 20 sec.");
		
		wait4Test(20000);
		
		String instanseDns = Ec2Man.parseInstansePublicDns(man.describeInstances(instanseId));
		
		System.out.println(man.sshInstanse(instanseDns, "ls -l /home"));
		
		String terminateInstanceResponse = man.terminateInstance(instanseId);
		
		log.log(Level.INFO, "terminate instanse " + instanseId + " \n response " + terminateInstanceResponse);		

		log.log(Level.INFO, "waiting 90 sec.");

		wait4Test(90000);
		
		String deleteSGResponse = man.deleteSecurityGroup(sgName);
		Assert.assertTrue(authSGresponse.equals(NO_CONTENT_OK_RESPONCE));

		log.log(Level.INFO, "delete SG " + sgName + " \n response " + deleteSGResponse);		
	
	}

	private static void wait4Test(long timeout) {
		long time = System.currentTimeMillis() + timeout;
		while (System.currentTimeMillis() < time) {
			Thread.yield();
		}
	}
}
