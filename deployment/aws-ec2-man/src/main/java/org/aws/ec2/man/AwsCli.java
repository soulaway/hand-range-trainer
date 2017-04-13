package org.aws.ec2.man;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

public enum AwsCli {

	instance {

		private AmazonEC2 cli;

		private File cred;

		private Regions region = Regions.EU_CENTRAL_1;

		@Override
		public AmazonEC2 getCurrent() throws FileNotFoundException, IllegalArgumentException, IOException {
			if (cli == null){
				return createNew(cred, region);
			}
			return cli;
		}

		@Override
		public AmazonEC2 createNew(File cred, Regions region)
				throws FileNotFoundException, IllegalArgumentException, IOException {
			
			if (cred != null && region != null) {
				if (cli != null) {
					log.log(Level.INFO, " *** shutting down AWS cli *** ");
					cli.shutdown();
					cli = null;
				}
				
				AWSCredentials awsCredentials = new PropertiesCredentials(cred);
				cli = AmazonEC2ClientBuilder.standard()
						.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region).build();
				log.log(Level.INFO, " *** AWS cli created*** ");
				return cli;
			} else {
				log.log(Level.SEVERE, "unable to create client with cred " + cred + " region " + region);
				return null;
			}
		}
	};
	public abstract AmazonEC2 getCurrent() throws FileNotFoundException, IllegalArgumentException, IOException;
	
	public final static Logger log = Logger.getLogger(AwsCli.class.getName());
	
	public abstract AmazonEC2 createNew(File cred, Regions region)
			throws FileNotFoundException, IllegalArgumentException, IOException;

}
