package org.aws.ec2.man;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aws.ec2.man.cli.AwsCli;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class Ec2Man{
	
	private static final Logger log = Logger.getLogger(Ec2Man.class.getName());
	
	public static final String PREFIX_IMAGE_ID = "ImageId:";	

	public static final String PREFIX_INSTANCE_ID = "InstanceId:";	
	public static final String POSTFIX_INSTANCE_ID = "," + PREFIX_IMAGE_ID;
	
	public static final String PREFIX_INSTANCE_DNS = "PublicDnsName:";
	public static final String POSTFIX_INSTANCE_DNS = ",StateTransitionReason";
	
	public Ec2Man(){
		String path = System.getProperty("ec2man.env.prop.path");
		String zone = System.getProperty("ec2man.env.prop.region");
		if (path != null && zone != null){
			log.log(Level.INFO, "*** Ec2Man wakes up in " + path);
			try {
				AwsCli.perform(new File(path), Regions.fromName(zone));
			} catch (IllegalArgumentException | IOException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		} else {
			log.log(Level.SEVERE, "unable to read property ec2man.env.prop.path");
		}
	}
	/**
	 * 
	 * @param name
	 * @param descr
	 * @return String response
	 */
    public String createSecutiryGroup(String name, String descr){
		CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
		csgr.withGroupName(name).withDescription(descr);
		try {
			return AwsCli.perform().createSecurityGroup(csgr).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String authorizeSecutiryGroup(String groupName, String protocolName, List<IpRange> ipv4, Integer port){
		IpPermission ipPermission = new IpPermission();
		ipPermission.withIpv4Ranges(ipv4).withIpProtocol(protocolName)
				.withFromPort(port).withToPort(port);
		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();
		authorizeSecurityGroupIngressRequest.withGroupName(groupName).withIpPermissions(ipPermission);
		try {
			return AwsCli.perform().authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }

    public String createInstance(String imgId, String type, String keyName, String groupName){
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
		runInstancesRequest
			.withImageId(imgId)
			.withInstanceType(type)
			.withMinCount(1)
			.withMaxCount(1)
			.withKeyName(keyName)
			.withSecurityGroups(groupName);
		try {
			return AwsCli.perform().runInstances(runInstancesRequest).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String sshInstanse(String pubDnsName, String command){
		StringBuffer sb = new StringBuffer();
    	try
        {
            String cmd = "ssh " + pubDnsName + " " + command;
            System.out.println("processing " + cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            PrintStream out = new PrintStream(p.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            out.println("ls -l /home/me");
            while (in.ready()) {
              String s = in.readLine();
              sb.append(s);
              System.out.println(s);
            }
            out.println("exit");

            p.waitFor();
        }
        catch(IOException | InterruptedException e) {
            return e.getMessage();
        }
    	return sb.toString();
    }
    
    public String describeInstances(String instanceId){
    	DescribeInstancesRequest dir = new DescribeInstancesRequest().withInstanceIds(instanceId);
    	try {
			return AwsCli.perform().describeInstances(dir).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}    	
    }
    
    public String startInstance(String instanceId){
    	StartInstancesRequest sir = new StartInstancesRequest().withInstanceIds(instanceId);
    	try {
			return AwsCli.perform().startInstances(sir).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String stopInstance(String instanceId){
    	StopInstancesRequest sir = new StopInstancesRequest().withInstanceIds(instanceId);
    	try {
			return AwsCli.perform().stopInstances(sir).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String terminateInstance(String instanceId){
		TerminateInstancesRequest runInstancesRequest = new TerminateInstancesRequest();
		runInstancesRequest.withInstanceIds(instanceId);
		try {
			return AwsCli.perform().terminateInstances(runInstancesRequest).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String deleteSecurityGroup(String name){
		DeleteSecurityGroupRequest dsgr = new DeleteSecurityGroupRequest();
		dsgr.withGroupName(name);
		try {
			return AwsCli.perform().deleteSecurityGroup(dsgr).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public static String parseInstanseId(String instanseOpeationResult){
    	return parseValue(instanseOpeationResult, PREFIX_INSTANCE_ID, POSTFIX_INSTANCE_ID);
    }
    
    public static String parseInstansePublicDns(String instanseOpeationResult){
    	return parseValue(instanseOpeationResult, PREFIX_INSTANCE_DNS, POSTFIX_INSTANCE_DNS);
    }
    
    private static String parseValue(String val, String prefix, String postfix){
    	return val.substring(val.indexOf(prefix) + prefix.length() + 1, val.indexOf(postfix));
    }
}
