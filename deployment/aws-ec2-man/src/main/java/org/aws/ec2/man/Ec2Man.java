package org.aws.ec2.man;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class Ec2Man{
	
	public final static Logger log = Logger.getLogger(Ec2Man.class.getName());
	
	public Ec2Man(){
		String path = System.getProperty("ec2man.env.prop.path");
		String zone = System.getProperty("ec2man.env.prop.region");
		if (path != null && zone != null){
			log.log(Level.INFO, "*** Ec2Man wakes up in %s ***", path);
			try {
				AwsCli.instance.createNew(new File(path), Regions.fromName(zone));
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
			return AwsCli.instance.getCurrent().createSecurityGroup(csgr).toString();
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
			return AwsCli.instance.getCurrent().authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest).toString();
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
			return AwsCli.instance.getCurrent().runInstances(runInstancesRequest).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String launchInstance(){
    	return "ok";
    }
    
    public String stopInstance(){
    	return "ok";
    }
    
    public String terminateInstance(String instanseId){
		TerminateInstancesRequest runInstancesRequest = new TerminateInstancesRequest();
		runInstancesRequest.withInstanceIds(instanseId);
		try {
			return AwsCli.instance.getCurrent().terminateInstances(runInstancesRequest).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
    
    public String deleteSecurityGroup(String name){
		DeleteSecurityGroupRequest dsgr = new DeleteSecurityGroupRequest();
		dsgr.withGroupName(name);
		try {
			return AwsCli.instance.getCurrent().deleteSecurityGroup(dsgr).toString();
		} catch (IllegalArgumentException | IOException e) {
			return e.getMessage();
		}
    }
}
