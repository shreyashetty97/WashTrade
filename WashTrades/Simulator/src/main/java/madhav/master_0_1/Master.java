
package madhav.master_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 





@SuppressWarnings("unused")

/**
 * Job: Master Purpose: <br>
 * Description:  <br>
 * @author madhav2800@gmail.com
 * @version 6.5.1.20180116_1512
 * @status 
 */
public class Master implements TalendJob {
	static {System.setProperty("TalendJob.log", "Master.log");}
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Master.class);



	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";
	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}
	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
		}

	}
	private ContextProperties context = new ContextProperties();
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "Master";
	private final String projectName = "MADHAV";
	public Integer errorCode = null;
	private String currentComponent = "";
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	private String currentComponent = null;
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				Master.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(Master.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tRunJob_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRunJob_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRunJob_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_4_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRunJob_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRunJob_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRunJob_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRunJob_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRunJob_4_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRunJob_3_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
		




	
public void tRunJob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRunJob_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		


	
	/**
	 * [tRunJob_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tRunJob_1", false);
		start_Hash.put("tRunJob_1", System.currentTimeMillis());
		
	
	currentComponent="tRunJob_1";

	
		int tos_count_tRunJob_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRunJob_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tRunJob_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRunJob_1 = new StringBuilder();
            log4jParamters_tRunJob_1.append("Parameters:");
                    log4jParamters_tRunJob_1.append("USE_DYNAMIC_JOB" + " = " + "false");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("PROCESS" + " = " + "MasterDataGen");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("USE_INDEPENDENT_PROCESS" + " = " + "false");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("DIE_ON_CHILD_ERROR" + " = " + "true");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("TRANSMIT_WHOLE_CONTEXT" + " = " + "false");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("CONTEXTPARAMS" + " = " + "[]");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("PROPAGATE_CHILD_RESULT" + " = " + "false");
                log4jParamters_tRunJob_1.append(" | ");
                    log4jParamters_tRunJob_1.append("PRINT_PARAMETER" + " = " + "false");
                log4jParamters_tRunJob_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tRunJob_1 - "  + (log4jParamters_tRunJob_1) );
    		}
    	}
    	
        new BytesLimit65535_tRunJob_1().limitLog4jByte();


 



/**
 * [tRunJob_1 begin ] stop
 */
	
	/**
	 * [tRunJob_1 main ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	
	java.util.List<String> paraList_tRunJob_1 = new java.util.ArrayList<String>();
	
	        			paraList_tRunJob_1.add("--father_pid="+pid);
	      			
	        			paraList_tRunJob_1.add("--root_pid="+rootPid);
	      			
	        			paraList_tRunJob_1.add("--father_node=tRunJob_1");
	      			
	        			paraList_tRunJob_1.add("--context=Default");
	      			
			if(!"".equals(log4jLevel)){
				paraList_tRunJob_1.add("--log4jLevel="+log4jLevel);
			}
		
	//for feature:10589
	
		paraList_tRunJob_1.add("--stat_port=" + portStats);
	

	if(resuming_logs_dir_path != null){
		paraList_tRunJob_1.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
	}
	String childResumePath_tRunJob_1 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
	String tRunJobName_tRunJob_1 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
	if("tRunJob_1".equals(tRunJobName_tRunJob_1) && childResumePath_tRunJob_1 != null){
		paraList_tRunJob_1.add("--resuming_checkpoint_path=" + ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
	}
	paraList_tRunJob_1.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_1");
	
	java.util.Map<String, Object> parentContextMap_tRunJob_1 = new java.util.HashMap<String, Object>();

	

	Object obj_tRunJob_1 = null;

	
	
		madhav.masterdatagen_0_1.MasterDataGen childJob_tRunJob_1 = new madhav.masterdatagen_0_1.MasterDataGen();
	    // pass DataSources
	    java.util.Map<String, routines.system.TalendDataSource> talendDataSources_tRunJob_1 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
	            .get(KEY_DB_DATASOURCES);
	    if (null != talendDataSources_tRunJob_1) {
	        java.util.Map<String, javax.sql.DataSource> dataSources_tRunJob_1 = new java.util.HashMap<String, javax.sql.DataSource>();
	        for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry_tRunJob_1 : talendDataSources_tRunJob_1
			        .entrySet()) {
	            dataSources_tRunJob_1.put(talendDataSourceEntry_tRunJob_1.getKey(),
	                    talendDataSourceEntry_tRunJob_1.getValue().getRawDataSource());
	        }
	        childJob_tRunJob_1.setDataSources(dataSources_tRunJob_1);
	    }
		  
			childJob_tRunJob_1.parentContextMap = parentContextMap_tRunJob_1;
		  
		
			log.info("tRunJob_1 - The child job 'madhav.masterdatagen_0_1.MasterDataGen' starts on the version '0.1' with the context 'Default'.");
		
		String[][] childReturn_tRunJob_1 = childJob_tRunJob_1.runJob((String[]) paraList_tRunJob_1.toArray(new String[paraList_tRunJob_1.size()]));
		
			log.info("tRunJob_1 - The child job 'madhav.masterdatagen_0_1.MasterDataGen' is done.");
		
	  	
				errorCode = childJob_tRunJob_1.getErrorCode();
		    
	            
	    	if(childJob_tRunJob_1.getErrorCode() == null){
				globalMap.put("tRunJob_1_CHILD_RETURN_CODE", childJob_tRunJob_1.getStatus() != null && ("failure").equals(childJob_tRunJob_1.getStatus()) ? 1 : 0);
	    	}else{
				globalMap.put("tRunJob_1_CHILD_RETURN_CODE", childJob_tRunJob_1.getErrorCode());
		    }
		    if (childJob_tRunJob_1.getExceptionStackTrace() != null) { 
		    	globalMap.put("tRunJob_1_CHILD_EXCEPTION_STACKTRACE", childJob_tRunJob_1.getExceptionStackTrace());
		    }
	  
			 
				if (childJob_tRunJob_1.getErrorCode() != null || ("failure").equals(childJob_tRunJob_1.getStatus())) {
	        		throw new RuntimeException("Child job running failed.\n"+childJob_tRunJob_1.getExceptionStackTrace());
				}
			
	  	
 


	tos_count_tRunJob_1++;

/**
 * [tRunJob_1 main ] stop
 */
	
	/**
	 * [tRunJob_1 end ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 
                if(log.isDebugEnabled())
            log.debug("tRunJob_1 - "  + ("Done.") );

ok_Hash.put("tRunJob_1", true);
end_Hash.put("tRunJob_1", System.currentTimeMillis());




/**
 * [tRunJob_1 end ] stop
 */
				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRunJob_1:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
								} 
							
							tRunJob_2Process(globalMap); 
						



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tRunJob_1 finally ] start
	 */

	

	
	
	currentComponent="tRunJob_1";

	

 



/**
 * [tRunJob_1 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRunJob_1_SUBPROCESS_STATE", 1);
	}
	

public void tRunJob_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRunJob_2_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		


	
	/**
	 * [tRunJob_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tRunJob_2", false);
		start_Hash.put("tRunJob_2", System.currentTimeMillis());
		
	
	currentComponent="tRunJob_2";

	
		int tos_count_tRunJob_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRunJob_2 - "  + ("Start to work.") );
    	class BytesLimit65535_tRunJob_2{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRunJob_2 = new StringBuilder();
            log4jParamters_tRunJob_2.append("Parameters:");
                    log4jParamters_tRunJob_2.append("USE_DYNAMIC_JOB" + " = " + "false");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("PROCESS" + " = " + "FraudGen");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("USE_INDEPENDENT_PROCESS" + " = " + "false");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("DIE_ON_CHILD_ERROR" + " = " + "true");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("TRANSMIT_WHOLE_CONTEXT" + " = " + "false");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("CONTEXTPARAMS" + " = " + "[]");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("PROPAGATE_CHILD_RESULT" + " = " + "false");
                log4jParamters_tRunJob_2.append(" | ");
                    log4jParamters_tRunJob_2.append("PRINT_PARAMETER" + " = " + "false");
                log4jParamters_tRunJob_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tRunJob_2 - "  + (log4jParamters_tRunJob_2) );
    		}
    	}
    	
        new BytesLimit65535_tRunJob_2().limitLog4jByte();


 



/**
 * [tRunJob_2 begin ] stop
 */
	
	/**
	 * [tRunJob_2 main ] start
	 */

	

	
	
	currentComponent="tRunJob_2";

	
	java.util.List<String> paraList_tRunJob_2 = new java.util.ArrayList<String>();
	
	        			paraList_tRunJob_2.add("--father_pid="+pid);
	      			
	        			paraList_tRunJob_2.add("--root_pid="+rootPid);
	      			
	        			paraList_tRunJob_2.add("--father_node=tRunJob_2");
	      			
	        			paraList_tRunJob_2.add("--context=Default");
	      			
			if(!"".equals(log4jLevel)){
				paraList_tRunJob_2.add("--log4jLevel="+log4jLevel);
			}
		
	//for feature:10589
	
		paraList_tRunJob_2.add("--stat_port=" + portStats);
	

	if(resuming_logs_dir_path != null){
		paraList_tRunJob_2.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
	}
	String childResumePath_tRunJob_2 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
	String tRunJobName_tRunJob_2 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
	if("tRunJob_2".equals(tRunJobName_tRunJob_2) && childResumePath_tRunJob_2 != null){
		paraList_tRunJob_2.add("--resuming_checkpoint_path=" + ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
	}
	paraList_tRunJob_2.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_2");
	
	java.util.Map<String, Object> parentContextMap_tRunJob_2 = new java.util.HashMap<String, Object>();

	

	Object obj_tRunJob_2 = null;

	
	
		madhav.fraudgen_0_1.FraudGen childJob_tRunJob_2 = new madhav.fraudgen_0_1.FraudGen();
	    // pass DataSources
	    java.util.Map<String, routines.system.TalendDataSource> talendDataSources_tRunJob_2 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
	            .get(KEY_DB_DATASOURCES);
	    if (null != talendDataSources_tRunJob_2) {
	        java.util.Map<String, javax.sql.DataSource> dataSources_tRunJob_2 = new java.util.HashMap<String, javax.sql.DataSource>();
	        for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry_tRunJob_2 : talendDataSources_tRunJob_2
			        .entrySet()) {
	            dataSources_tRunJob_2.put(talendDataSourceEntry_tRunJob_2.getKey(),
	                    talendDataSourceEntry_tRunJob_2.getValue().getRawDataSource());
	        }
	        childJob_tRunJob_2.setDataSources(dataSources_tRunJob_2);
	    }
		  
			childJob_tRunJob_2.parentContextMap = parentContextMap_tRunJob_2;
		  
		
			log.info("tRunJob_2 - The child job 'madhav.fraudgen_0_1.FraudGen' starts on the version '0.1' with the context 'Default'.");
		
		String[][] childReturn_tRunJob_2 = childJob_tRunJob_2.runJob((String[]) paraList_tRunJob_2.toArray(new String[paraList_tRunJob_2.size()]));
		
			log.info("tRunJob_2 - The child job 'madhav.fraudgen_0_1.FraudGen' is done.");
		
	  	
				errorCode = childJob_tRunJob_2.getErrorCode();
		    
	            
	    	if(childJob_tRunJob_2.getErrorCode() == null){
				globalMap.put("tRunJob_2_CHILD_RETURN_CODE", childJob_tRunJob_2.getStatus() != null && ("failure").equals(childJob_tRunJob_2.getStatus()) ? 1 : 0);
	    	}else{
				globalMap.put("tRunJob_2_CHILD_RETURN_CODE", childJob_tRunJob_2.getErrorCode());
		    }
		    if (childJob_tRunJob_2.getExceptionStackTrace() != null) { 
		    	globalMap.put("tRunJob_2_CHILD_EXCEPTION_STACKTRACE", childJob_tRunJob_2.getExceptionStackTrace());
		    }
	  
			 
				if (childJob_tRunJob_2.getErrorCode() != null || ("failure").equals(childJob_tRunJob_2.getStatus())) {
	        		throw new RuntimeException("Child job running failed.\n"+childJob_tRunJob_2.getExceptionStackTrace());
				}
			
	  	
 


	tos_count_tRunJob_2++;

/**
 * [tRunJob_2 main ] stop
 */
	
	/**
	 * [tRunJob_2 end ] start
	 */

	

	
	
	currentComponent="tRunJob_2";

	

 
                if(log.isDebugEnabled())
            log.debug("tRunJob_2 - "  + ("Done.") );

ok_Hash.put("tRunJob_2", true);
end_Hash.put("tRunJob_2", System.currentTimeMillis());




/**
 * [tRunJob_2 end ] stop
 */
				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRunJob_2:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk3", 0, "ok");
								} 
							
							tRunJob_4Process(globalMap); 
						



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tRunJob_2 finally ] start
	 */

	

	
	
	currentComponent="tRunJob_2";

	

 



/**
 * [tRunJob_2 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRunJob_2_SUBPROCESS_STATE", 1);
	}
	

public void tRunJob_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRunJob_4_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		


	
	/**
	 * [tRunJob_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tRunJob_4", false);
		start_Hash.put("tRunJob_4", System.currentTimeMillis());
		
	
	currentComponent="tRunJob_4";

	
		int tos_count_tRunJob_4 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRunJob_4 - "  + ("Start to work.") );
    	class BytesLimit65535_tRunJob_4{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRunJob_4 = new StringBuilder();
            log4jParamters_tRunJob_4.append("Parameters:");
                    log4jParamters_tRunJob_4.append("USE_DYNAMIC_JOB" + " = " + "false");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("PROCESS" + " = " + "CombFCP");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("USE_INDEPENDENT_PROCESS" + " = " + "false");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("DIE_ON_CHILD_ERROR" + " = " + "true");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("TRANSMIT_WHOLE_CONTEXT" + " = " + "false");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("CONTEXTPARAMS" + " = " + "[]");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("PROPAGATE_CHILD_RESULT" + " = " + "false");
                log4jParamters_tRunJob_4.append(" | ");
                    log4jParamters_tRunJob_4.append("PRINT_PARAMETER" + " = " + "false");
                log4jParamters_tRunJob_4.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tRunJob_4 - "  + (log4jParamters_tRunJob_4) );
    		}
    	}
    	
        new BytesLimit65535_tRunJob_4().limitLog4jByte();


 



/**
 * [tRunJob_4 begin ] stop
 */
	
	/**
	 * [tRunJob_4 main ] start
	 */

	

	
	
	currentComponent="tRunJob_4";

	
	java.util.List<String> paraList_tRunJob_4 = new java.util.ArrayList<String>();
	
	        			paraList_tRunJob_4.add("--father_pid="+pid);
	      			
	        			paraList_tRunJob_4.add("--root_pid="+rootPid);
	      			
	        			paraList_tRunJob_4.add("--father_node=tRunJob_4");
	      			
	        			paraList_tRunJob_4.add("--context=Default");
	      			
			if(!"".equals(log4jLevel)){
				paraList_tRunJob_4.add("--log4jLevel="+log4jLevel);
			}
		
	//for feature:10589
	
		paraList_tRunJob_4.add("--stat_port=" + portStats);
	

	if(resuming_logs_dir_path != null){
		paraList_tRunJob_4.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
	}
	String childResumePath_tRunJob_4 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
	String tRunJobName_tRunJob_4 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
	if("tRunJob_4".equals(tRunJobName_tRunJob_4) && childResumePath_tRunJob_4 != null){
		paraList_tRunJob_4.add("--resuming_checkpoint_path=" + ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
	}
	paraList_tRunJob_4.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_4");
	
	java.util.Map<String, Object> parentContextMap_tRunJob_4 = new java.util.HashMap<String, Object>();

	

	Object obj_tRunJob_4 = null;

	
	
		madhav.combfcp_0_1.CombFCP childJob_tRunJob_4 = new madhav.combfcp_0_1.CombFCP();
	    // pass DataSources
	    java.util.Map<String, routines.system.TalendDataSource> talendDataSources_tRunJob_4 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
	            .get(KEY_DB_DATASOURCES);
	    if (null != talendDataSources_tRunJob_4) {
	        java.util.Map<String, javax.sql.DataSource> dataSources_tRunJob_4 = new java.util.HashMap<String, javax.sql.DataSource>();
	        for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry_tRunJob_4 : talendDataSources_tRunJob_4
			        .entrySet()) {
	            dataSources_tRunJob_4.put(talendDataSourceEntry_tRunJob_4.getKey(),
	                    talendDataSourceEntry_tRunJob_4.getValue().getRawDataSource());
	        }
	        childJob_tRunJob_4.setDataSources(dataSources_tRunJob_4);
	    }
		  
			childJob_tRunJob_4.parentContextMap = parentContextMap_tRunJob_4;
		  
		
			log.info("tRunJob_4 - The child job 'madhav.combfcp_0_1.CombFCP' starts on the version '0.1' with the context 'Default'.");
		
		String[][] childReturn_tRunJob_4 = childJob_tRunJob_4.runJob((String[]) paraList_tRunJob_4.toArray(new String[paraList_tRunJob_4.size()]));
		
			log.info("tRunJob_4 - The child job 'madhav.combfcp_0_1.CombFCP' is done.");
		
	  	
				errorCode = childJob_tRunJob_4.getErrorCode();
		    
	            
	    	if(childJob_tRunJob_4.getErrorCode() == null){
				globalMap.put("tRunJob_4_CHILD_RETURN_CODE", childJob_tRunJob_4.getStatus() != null && ("failure").equals(childJob_tRunJob_4.getStatus()) ? 1 : 0);
	    	}else{
				globalMap.put("tRunJob_4_CHILD_RETURN_CODE", childJob_tRunJob_4.getErrorCode());
		    }
		    if (childJob_tRunJob_4.getExceptionStackTrace() != null) { 
		    	globalMap.put("tRunJob_4_CHILD_EXCEPTION_STACKTRACE", childJob_tRunJob_4.getExceptionStackTrace());
		    }
	  
			 
				if (childJob_tRunJob_4.getErrorCode() != null || ("failure").equals(childJob_tRunJob_4.getStatus())) {
	        		throw new RuntimeException("Child job running failed.\n"+childJob_tRunJob_4.getExceptionStackTrace());
				}
			
	  	
 


	tos_count_tRunJob_4++;

/**
 * [tRunJob_4 main ] stop
 */
	
	/**
	 * [tRunJob_4 end ] start
	 */

	

	
	
	currentComponent="tRunJob_4";

	

 
                if(log.isDebugEnabled())
            log.debug("tRunJob_4 - "  + ("Done.") );

ok_Hash.put("tRunJob_4", true);
end_Hash.put("tRunJob_4", System.currentTimeMillis());




/**
 * [tRunJob_4 end ] stop
 */
				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRunJob_4:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
								} 
							
							tRunJob_3Process(globalMap); 
						



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tRunJob_4 finally ] start
	 */

	

	
	
	currentComponent="tRunJob_4";

	

 



/**
 * [tRunJob_4 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRunJob_4_SUBPROCESS_STATE", 1);
	}
	

public void tRunJob_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRunJob_3_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;





	
	/**
	 * [tRunJob_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tRunJob_3", false);
		start_Hash.put("tRunJob_3", System.currentTimeMillis());
		
	
	currentComponent="tRunJob_3";

	
		int tos_count_tRunJob_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRunJob_3 - "  + ("Start to work.") );
    	class BytesLimit65535_tRunJob_3{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRunJob_3 = new StringBuilder();
            log4jParamters_tRunJob_3.append("Parameters:");
                    log4jParamters_tRunJob_3.append("USE_DYNAMIC_JOB" + " = " + "false");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("PROCESS" + " = " + "Merger");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("USE_INDEPENDENT_PROCESS" + " = " + "false");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("DIE_ON_CHILD_ERROR" + " = " + "true");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("TRANSMIT_WHOLE_CONTEXT" + " = " + "false");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("CONTEXTPARAMS" + " = " + "[]");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("PROPAGATE_CHILD_RESULT" + " = " + "false");
                log4jParamters_tRunJob_3.append(" | ");
                    log4jParamters_tRunJob_3.append("PRINT_PARAMETER" + " = " + "false");
                log4jParamters_tRunJob_3.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tRunJob_3 - "  + (log4jParamters_tRunJob_3) );
    		}
    	}
    	
        new BytesLimit65535_tRunJob_3().limitLog4jByte();


 



/**
 * [tRunJob_3 begin ] stop
 */
	
	/**
	 * [tRunJob_3 main ] start
	 */

	

	
	
	currentComponent="tRunJob_3";

	
	java.util.List<String> paraList_tRunJob_3 = new java.util.ArrayList<String>();
	
	        			paraList_tRunJob_3.add("--father_pid="+pid);
	      			
	        			paraList_tRunJob_3.add("--root_pid="+rootPid);
	      			
	        			paraList_tRunJob_3.add("--father_node=tRunJob_3");
	      			
	        			paraList_tRunJob_3.add("--context=Default");
	      			
			if(!"".equals(log4jLevel)){
				paraList_tRunJob_3.add("--log4jLevel="+log4jLevel);
			}
		
	//for feature:10589
	
		paraList_tRunJob_3.add("--stat_port=" + portStats);
	

	if(resuming_logs_dir_path != null){
		paraList_tRunJob_3.add("--resuming_logs_dir_path=" + resuming_logs_dir_path);
	}
	String childResumePath_tRunJob_3 = ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path);
	String tRunJobName_tRunJob_3 = ResumeUtil.getRighttRunJob(resuming_checkpoint_path);
	if("tRunJob_3".equals(tRunJobName_tRunJob_3) && childResumePath_tRunJob_3 != null){
		paraList_tRunJob_3.add("--resuming_checkpoint_path=" + ResumeUtil.getChildJobCheckPointPath(resuming_checkpoint_path));
	}
	paraList_tRunJob_3.add("--parent_part_launcher=JOB:" + jobName + "/NODE:tRunJob_3");
	
	java.util.Map<String, Object> parentContextMap_tRunJob_3 = new java.util.HashMap<String, Object>();

	

	Object obj_tRunJob_3 = null;

	
	
		madhav.merger_0_1.Merger childJob_tRunJob_3 = new madhav.merger_0_1.Merger();
	    // pass DataSources
	    java.util.Map<String, routines.system.TalendDataSource> talendDataSources_tRunJob_3 = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
	            .get(KEY_DB_DATASOURCES);
	    if (null != talendDataSources_tRunJob_3) {
	        java.util.Map<String, javax.sql.DataSource> dataSources_tRunJob_3 = new java.util.HashMap<String, javax.sql.DataSource>();
	        for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry_tRunJob_3 : talendDataSources_tRunJob_3
			        .entrySet()) {
	            dataSources_tRunJob_3.put(talendDataSourceEntry_tRunJob_3.getKey(),
	                    talendDataSourceEntry_tRunJob_3.getValue().getRawDataSource());
	        }
	        childJob_tRunJob_3.setDataSources(dataSources_tRunJob_3);
	    }
		  
			childJob_tRunJob_3.parentContextMap = parentContextMap_tRunJob_3;
		  
		
			log.info("tRunJob_3 - The child job 'madhav.merger_0_1.Merger' starts on the version '0.1' with the context 'Default'.");
		
		String[][] childReturn_tRunJob_3 = childJob_tRunJob_3.runJob((String[]) paraList_tRunJob_3.toArray(new String[paraList_tRunJob_3.size()]));
		
			log.info("tRunJob_3 - The child job 'madhav.merger_0_1.Merger' is done.");
		
	  	
				errorCode = childJob_tRunJob_3.getErrorCode();
		    
	            
	    	if(childJob_tRunJob_3.getErrorCode() == null){
				globalMap.put("tRunJob_3_CHILD_RETURN_CODE", childJob_tRunJob_3.getStatus() != null && ("failure").equals(childJob_tRunJob_3.getStatus()) ? 1 : 0);
	    	}else{
				globalMap.put("tRunJob_3_CHILD_RETURN_CODE", childJob_tRunJob_3.getErrorCode());
		    }
		    if (childJob_tRunJob_3.getExceptionStackTrace() != null) { 
		    	globalMap.put("tRunJob_3_CHILD_EXCEPTION_STACKTRACE", childJob_tRunJob_3.getExceptionStackTrace());
		    }
	  
			 
				if (childJob_tRunJob_3.getErrorCode() != null || ("failure").equals(childJob_tRunJob_3.getStatus())) {
	        		throw new RuntimeException("Child job running failed.\n"+childJob_tRunJob_3.getExceptionStackTrace());
				}
			
	  	
 


	tos_count_tRunJob_3++;

/**
 * [tRunJob_3 main ] stop
 */
	
	/**
	 * [tRunJob_3 end ] start
	 */

	

	
	
	currentComponent="tRunJob_3";

	

 
                if(log.isDebugEnabled())
            log.debug("tRunJob_3 - "  + ("Done.") );

ok_Hash.put("tRunJob_3", true);
end_Hash.put("tRunJob_3", System.currentTimeMillis());




/**
 * [tRunJob_3 end ] stop
 */
				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				    if(!(e instanceof TalendException)){
					   log.fatal(currentComponent + " " + e.getMessage(),e);
					}
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tRunJob_3 finally ] start
	 */

	

	
	
	currentComponent="tRunJob_3";

	

 



/**
 * [tRunJob_3 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRunJob_3_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";

    private boolean execStat = true;

    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };



    private PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";

    public static void main(String[] args){
        final Master MasterClass = new Master();

        int exitCode = MasterClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'Master' - Done.");
	        }

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";

        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }

	        if(!"".equals(log4jLevel)){
				if("trace".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.TRACE);
				}else if("debug".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.DEBUG);
				}else if("info".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.INFO);
				}else if("warn".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.WARN);
				}else if("error".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.ERROR);
				}else if("fatal".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.FATAL);
				}else if ("off".equalsIgnoreCase(log4jLevel)){
					log.setLevel(org.apache.log4j.Level.OFF);
				}
				org.apache.log4j.Logger.getRootLogger().setLevel(log.getLevel());
    	    }
        	log.info("TalendJob: 'Master' - Start.");
    	

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

        if (rootPid==null) {
            rootPid = pid;
        }
        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }

        try {
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = Master.class.getClassLoader().getResourceAsStream("madhav/master_0_1/contexts/"+contextStr+".properties");
            if(isDefaultContext && inContext ==null) {

            } else {
                if (inContext!=null) {
                    //defaultProps is in order to keep the original context value
                    defaultProps.load(inContext);
                    inContext.close();
                    context = new ContextProperties(defaultProps);
                }else{
                    //print info and job continue to run, for case: context_param is not empty.
                    System.err.println("Could not find the context " + contextStr);
                }
            }

            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }


        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,parametersToEncrypt));

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();




this.globalResumeTicket = true;//to run tPreJob




this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tRunJob_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tRunJob_1) {
globalMap.put("tRunJob_1_SUBPROCESS_STATE", -1);

e_tRunJob_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Master");
        }





if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    int returnCode = 0;
    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {


    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();







        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        }else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		}

    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     54440 characters generated by Talend Data Integration 
 *     on the September 27, 2019 2:53:51 PM IST
 ************************************************************************************************/