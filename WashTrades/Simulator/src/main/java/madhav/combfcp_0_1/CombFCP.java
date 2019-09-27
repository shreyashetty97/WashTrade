
package madhav.combfcp_0_1;

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
 * Job: CombFCP Purpose: <br>
 * Description:  <br>
 * @author madhav2800@gmail.com
 * @version 6.5.1.20180116_1512
 * @status 
 */
public class CombFCP implements TalendJob {
	static {System.setProperty("TalendJob.log", "CombFCP.log");}
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CombFCP.class);



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
	private final String jobName = "CombFCP";
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
				CombFCP.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(CombFCP.this, new Object[] { e , currentComponent, globalMap});
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

			public void tOracleInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFlowToIterate_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tSetGlobalVar_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRowGenerator_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRowGenerator_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRowGenerator_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRowGenerator_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
		




	

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_CombFCP = new byte[0];
    static byte[] commonByteArray_MADHAV_CombFCP = new byte[0];

	
			    public Integer Trader_ID1;

				public Integer getTrader_ID1 () {
					return this.Trader_ID1;
				}
				
			    public Integer Trader_ID2;

				public Integer getTrader_ID2 () {
					return this.Trader_ID2;
				}
				
			    public Integer Trader_ID3;

				public Integer getTrader_ID3 () {
					return this.Trader_ID3;
				}
				
			    public Integer Broker_ID1;

				public Integer getBroker_ID1 () {
					return this.Broker_ID1;
				}
				
			    public Integer Broker_ID2;

				public Integer getBroker_ID2 () {
					return this.Broker_ID2;
				}
				
			    public Integer Broker_ID3;

				public Integer getBroker_ID3 () {
					return this.Broker_ID3;
				}
				
			    public Integer Company_ID1;

				public Integer getCompany_ID1 () {
					return this.Company_ID1;
				}
				
			    public Integer Company_ID2;

				public Integer getCompany_ID2 () {
					return this.Company_ID2;
				}
				
			    public Integer Company_ID3;

				public Integer getCompany_ID3 () {
					return this.Company_ID3;
				}
				
			    public Integer Quantity1;

				public Integer getQuantity1 () {
					return this.Quantity1;
				}
				
			    public Integer Quantity2;

				public Integer getQuantity2 () {
					return this.Quantity2;
				}
				
			    public Integer Quantity3;

				public Integer getQuantity3 () {
					return this.Quantity3;
				}
				
			    public Integer Price1;

				public Integer getPrice1 () {
					return this.Price1;
				}
				
			    public Integer Price2;

				public Integer getPrice2 () {
					return this.Price2;
				}
				
			    public Integer Price3;

				public Integer getPrice3 () {
					return this.Price3;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_MADHAV_CombFCP) {

        	try {

        		int length = 0;
		
						this.Trader_ID1 = readInteger(dis);
					
						this.Trader_ID2 = readInteger(dis);
					
						this.Trader_ID3 = readInteger(dis);
					
						this.Broker_ID1 = readInteger(dis);
					
						this.Broker_ID2 = readInteger(dis);
					
						this.Broker_ID3 = readInteger(dis);
					
						this.Company_ID1 = readInteger(dis);
					
						this.Company_ID2 = readInteger(dis);
					
						this.Company_ID3 = readInteger(dis);
					
						this.Quantity1 = readInteger(dis);
					
						this.Quantity2 = readInteger(dis);
					
						this.Quantity3 = readInteger(dis);
					
						this.Price1 = readInteger(dis);
					
						this.Price2 = readInteger(dis);
					
						this.Price3 = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.Trader_ID1,dos);
					
					// Integer
				
						writeInteger(this.Trader_ID2,dos);
					
					// Integer
				
						writeInteger(this.Trader_ID3,dos);
					
					// Integer
				
						writeInteger(this.Broker_ID1,dos);
					
					// Integer
				
						writeInteger(this.Broker_ID2,dos);
					
					// Integer
				
						writeInteger(this.Broker_ID3,dos);
					
					// Integer
				
						writeInteger(this.Company_ID1,dos);
					
					// Integer
				
						writeInteger(this.Company_ID2,dos);
					
					// Integer
				
						writeInteger(this.Company_ID3,dos);
					
					// Integer
				
						writeInteger(this.Quantity1,dos);
					
					// Integer
				
						writeInteger(this.Quantity2,dos);
					
					// Integer
				
						writeInteger(this.Quantity3,dos);
					
					// Integer
				
						writeInteger(this.Price1,dos);
					
					// Integer
				
						writeInteger(this.Price2,dos);
					
					// Integer
				
						writeInteger(this.Price3,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Trader_ID1="+String.valueOf(Trader_ID1));
		sb.append(",Trader_ID2="+String.valueOf(Trader_ID2));
		sb.append(",Trader_ID3="+String.valueOf(Trader_ID3));
		sb.append(",Broker_ID1="+String.valueOf(Broker_ID1));
		sb.append(",Broker_ID2="+String.valueOf(Broker_ID2));
		sb.append(",Broker_ID3="+String.valueOf(Broker_ID3));
		sb.append(",Company_ID1="+String.valueOf(Company_ID1));
		sb.append(",Company_ID2="+String.valueOf(Company_ID2));
		sb.append(",Company_ID3="+String.valueOf(Company_ID3));
		sb.append(",Quantity1="+String.valueOf(Quantity1));
		sb.append(",Quantity2="+String.valueOf(Quantity2));
		sb.append(",Quantity3="+String.valueOf(Quantity3));
		sb.append(",Price1="+String.valueOf(Price1));
		sb.append(",Price2="+String.valueOf(Price2));
		sb.append(",Price3="+String.valueOf(Price3));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(Trader_ID1 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Trader_ID1);
            			}
            		
        			sb.append("|");
        		
        				if(Trader_ID2 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Trader_ID2);
            			}
            		
        			sb.append("|");
        		
        				if(Trader_ID3 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Trader_ID3);
            			}
            		
        			sb.append("|");
        		
        				if(Broker_ID1 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Broker_ID1);
            			}
            		
        			sb.append("|");
        		
        				if(Broker_ID2 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Broker_ID2);
            			}
            		
        			sb.append("|");
        		
        				if(Broker_ID3 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Broker_ID3);
            			}
            		
        			sb.append("|");
        		
        				if(Company_ID1 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Company_ID1);
            			}
            		
        			sb.append("|");
        		
        				if(Company_ID2 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Company_ID2);
            			}
            		
        			sb.append("|");
        		
        				if(Company_ID3 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Company_ID3);
            			}
            		
        			sb.append("|");
        		
        				if(Quantity1 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Quantity1);
            			}
            		
        			sb.append("|");
        		
        				if(Quantity2 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Quantity2);
            			}
            		
        			sb.append("|");
        		
        				if(Quantity3 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Quantity3);
            			}
            		
        			sb.append("|");
        		
        				if(Price1 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Price1);
            			}
            		
        			sb.append("|");
        		
        				if(Price2 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Price2);
            			}
            		
        			sb.append("|");
        		
        				if(Price3 == null){
        					sb.append("<null>");
        				}else{
            				sb.append(Price3);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tOracleInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row1Struct row1 = new row1Struct();




	
	/**
	 * [tFlowToIterate_1 begin ] start
	 */

				
			int NB_ITERATE_tSetGlobalVar_1 = 0; //for statistics
			

	
		
		ok_Hash.put("tFlowToIterate_1", false);
		start_Hash.put("tFlowToIterate_1", System.currentTimeMillis());
		
	
	currentComponent="tFlowToIterate_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row1" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tFlowToIterate_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tFlowToIterate_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tFlowToIterate_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tFlowToIterate_1 = new StringBuilder();
            log4jParamters_tFlowToIterate_1.append("Parameters:");
                    log4jParamters_tFlowToIterate_1.append("DEFAULT_MAP" + " = " + "true");
                log4jParamters_tFlowToIterate_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tFlowToIterate_1 - "  + (log4jParamters_tFlowToIterate_1) );
    		}
    	}
    	
        new BytesLimit65535_tFlowToIterate_1().limitLog4jByte();

int nb_line_tFlowToIterate_1 = 0;
int counter_tFlowToIterate_1 = 0;

 



/**
 * [tFlowToIterate_1 begin ] stop
 */



	
	/**
	 * [tOracleInput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_1", false);
		start_Hash.put("tOracleInput_1", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_1";

	
		int tos_count_tOracleInput_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_1 = new StringBuilder();
            log4jParamters_tOracleInput_1.append("Parameters:");
                    log4jParamters_tOracleInput_1.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("TABLE" + " = " + "\"FCPRandomizer\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERY" + " = " + "\"SELECT    FCPRandomizer.Trader_ID1,    FCPRandomizer.Trader_ID2,    FCPRandomizer.Trader_ID3,    FCPRandomizer.Broker_ID1,    FCPRandomizer.Broker_ID2,    FCPRandomizer.Broker_ID3,    FCPRandomizer.Company_ID1,    FCPRandomizer.Company_ID2,    FCPRandomizer.Company_ID3,    FCPRandomizer.Quantity1,    FCPRandomizer.Quantity2,    FCPRandomizer.Quantity3,    FCPRandomizer.Price1,    FCPRandomizer.Price2,    FCPRandomizer.Price3 FROM FCPRandomizer\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("Trader_ID1")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Trader_ID2")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Trader_ID3")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Broker_ID1")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Broker_ID2")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Broker_ID3")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Company_ID1")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Company_ID2")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Company_ID3")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Quantity1")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Quantity2")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Quantity3")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Price1")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Price2")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("Price3")+"}]");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_1 - "  + (log4jParamters_tOracleInput_1) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_1().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_1 = 0;
		    java.sql.Connection conn_tOracleInput_1 = null;
				String driverClass_tOracleInput_1 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_1);
				
			String url_tOracleInput_1 = null;
				url_tOracleInput_1 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_1 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_1 = decryptedPassword_tOracleInput_1;

				
	    		log.debug("tOracleInput_1 - Driver ClassName: "+driverClass_tOracleInput_1+".");
			
	    		log.debug("tOracleInput_1 - Connection attempt to '" + url_tOracleInput_1 + "' with the username '" + dbUser_tOracleInput_1 + "'.");
			
					conn_tOracleInput_1 = java.sql.DriverManager.getConnection(url_tOracleInput_1,dbUser_tOracleInput_1,dbPwd_tOracleInput_1);
	    		log.debug("tOracleInput_1 - Connection to '" + url_tOracleInput_1 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_1 = conn_tOracleInput_1.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_1 = stmtGetTZ_tOracleInput_1.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_1 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_1.next()) {
					sessionTimezone_tOracleInput_1 = rsGetTZ_tOracleInput_1.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_1).setSessionTimeZone(sessionTimezone_tOracleInput_1);
		    
			java.sql.Statement stmt_tOracleInput_1 = conn_tOracleInput_1.createStatement();

		    String dbquery_tOracleInput_1 = "SELECT \n  FCPRandomizer.Trader_ID1, \n  FCPRandomizer.Trader_ID2, \n  FCPRandomizer.Trader_ID3, \n  FCPRandomizer.Broker_ID1, \n  FCPRandomizer.Broker_ID2, \n  FCPRandomizer.Broker_ID3, \n  FCPRandomizer.Company_ID1, \n  FCPRandomizer.Company_ID2, \n  FCPRandomizer.Company_ID3, \n  FCPRandomizer.Quantity1, \n  FCPRandomizer.Quantity2, \n  FCPRandomizer.Quantity3, \n  FCPRandomizer.Price1, \n  FCPRandomizer.Price2, \n  FCPRandomizer.Price3\nFROM FCPRandomizer";
			
                log.debug("tOracleInput_1 - Executing the query: '"+dbquery_tOracleInput_1+"'.");
			

            	globalMap.put("tOracleInput_1_QUERY",dbquery_tOracleInput_1);
		    java.sql.ResultSet rs_tOracleInput_1 = null;

		    try {
		    	rs_tOracleInput_1 = stmt_tOracleInput_1.executeQuery(dbquery_tOracleInput_1);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_1 = rs_tOracleInput_1.getMetaData();
		    	int colQtyInRs_tOracleInput_1 = rsmd_tOracleInput_1.getColumnCount();

		    String tmpContent_tOracleInput_1 = null;
		    
		    
		    	log.debug("tOracleInput_1 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_1.next()) {
		        nb_line_tOracleInput_1++;
		        
							if(colQtyInRs_tOracleInput_1 < 1) {
								row1.Trader_ID1 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(1) != null) {
						row1.Trader_ID1 = rs_tOracleInput_1.getInt(1);
					} else {
				
						row1.Trader_ID1 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 2) {
								row1.Trader_ID2 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(2) != null) {
						row1.Trader_ID2 = rs_tOracleInput_1.getInt(2);
					} else {
				
						row1.Trader_ID2 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 3) {
								row1.Trader_ID3 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(3) != null) {
						row1.Trader_ID3 = rs_tOracleInput_1.getInt(3);
					} else {
				
						row1.Trader_ID3 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 4) {
								row1.Broker_ID1 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(4) != null) {
						row1.Broker_ID1 = rs_tOracleInput_1.getInt(4);
					} else {
				
						row1.Broker_ID1 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 5) {
								row1.Broker_ID2 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(5) != null) {
						row1.Broker_ID2 = rs_tOracleInput_1.getInt(5);
					} else {
				
						row1.Broker_ID2 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 6) {
								row1.Broker_ID3 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(6) != null) {
						row1.Broker_ID3 = rs_tOracleInput_1.getInt(6);
					} else {
				
						row1.Broker_ID3 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 7) {
								row1.Company_ID1 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(7) != null) {
						row1.Company_ID1 = rs_tOracleInput_1.getInt(7);
					} else {
				
						row1.Company_ID1 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 8) {
								row1.Company_ID2 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(8) != null) {
						row1.Company_ID2 = rs_tOracleInput_1.getInt(8);
					} else {
				
						row1.Company_ID2 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 9) {
								row1.Company_ID3 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(9) != null) {
						row1.Company_ID3 = rs_tOracleInput_1.getInt(9);
					} else {
				
						row1.Company_ID3 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 10) {
								row1.Quantity1 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(10) != null) {
						row1.Quantity1 = rs_tOracleInput_1.getInt(10);
					} else {
				
						row1.Quantity1 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 11) {
								row1.Quantity2 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(11) != null) {
						row1.Quantity2 = rs_tOracleInput_1.getInt(11);
					} else {
				
						row1.Quantity2 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 12) {
								row1.Quantity3 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(12) != null) {
						row1.Quantity3 = rs_tOracleInput_1.getInt(12);
					} else {
				
						row1.Quantity3 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 13) {
								row1.Price1 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(13) != null) {
						row1.Price1 = rs_tOracleInput_1.getInt(13);
					} else {
				
						row1.Price1 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 14) {
								row1.Price2 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(14) != null) {
						row1.Price2 = rs_tOracleInput_1.getInt(14);
					} else {
				
						row1.Price2 = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 15) {
								row1.Price3 = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(15) != null) {
						row1.Price3 = rs_tOracleInput_1.getInt(15);
					} else {
				
						row1.Price3 = null;
					}
		                    }
					
						log.debug("tOracleInput_1 - Retrieving the record " + nb_line_tOracleInput_1 + ".");
					




 



/**
 * [tOracleInput_1 begin ] stop
 */
	
	/**
	 * [tOracleInput_1 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_1";

	

 


	tos_count_tOracleInput_1++;

/**
 * [tOracleInput_1 main ] stop
 */

	
	/**
	 * [tFlowToIterate_1 main ] start
	 */

	

	
	
	currentComponent="tFlowToIterate_1";

	

			//row1
			//row1


			
				if(execStat){
					runStat.updateStatOnConnection("row1"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row1 - " + (row1==null? "": row1.toLogString()));
    			}
    		


    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Trader_ID1, value=")  + (row1.Trader_ID1)  + (".") );            
            globalMap.put("row1.Trader_ID1", row1.Trader_ID1);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Trader_ID2, value=")  + (row1.Trader_ID2)  + (".") );            
            globalMap.put("row1.Trader_ID2", row1.Trader_ID2);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Trader_ID3, value=")  + (row1.Trader_ID3)  + (".") );            
            globalMap.put("row1.Trader_ID3", row1.Trader_ID3);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Broker_ID1, value=")  + (row1.Broker_ID1)  + (".") );            
            globalMap.put("row1.Broker_ID1", row1.Broker_ID1);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Broker_ID2, value=")  + (row1.Broker_ID2)  + (".") );            
            globalMap.put("row1.Broker_ID2", row1.Broker_ID2);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Broker_ID3, value=")  + (row1.Broker_ID3)  + (".") );            
            globalMap.put("row1.Broker_ID3", row1.Broker_ID3);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Company_ID1, value=")  + (row1.Company_ID1)  + (".") );            
            globalMap.put("row1.Company_ID1", row1.Company_ID1);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Company_ID2, value=")  + (row1.Company_ID2)  + (".") );            
            globalMap.put("row1.Company_ID2", row1.Company_ID2);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Company_ID3, value=")  + (row1.Company_ID3)  + (".") );            
            globalMap.put("row1.Company_ID3", row1.Company_ID3);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Quantity1, value=")  + (row1.Quantity1)  + (".") );            
            globalMap.put("row1.Quantity1", row1.Quantity1);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Quantity2, value=")  + (row1.Quantity2)  + (".") );            
            globalMap.put("row1.Quantity2", row1.Quantity2);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Quantity3, value=")  + (row1.Quantity3)  + (".") );            
            globalMap.put("row1.Quantity3", row1.Quantity3);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Price1, value=")  + (row1.Price1)  + (".") );            
            globalMap.put("row1.Price1", row1.Price1);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Price2, value=")  + (row1.Price2)  + (".") );            
            globalMap.put("row1.Price2", row1.Price2);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row1.Price3, value=")  + (row1.Price3)  + (".") );            
            globalMap.put("row1.Price3", row1.Price3);
    	
 
	   nb_line_tFlowToIterate_1++;  
       counter_tFlowToIterate_1++;
                if(log.isDebugEnabled())
            log.debug("tFlowToIterate_1 - "  + ("Current iteration is: ")  + (counter_tFlowToIterate_1)  + (".") );
       globalMap.put("tFlowToIterate_1_CURRENT_ITERATION", counter_tFlowToIterate_1);
 


	tos_count_tFlowToIterate_1++;

/**
 * [tFlowToIterate_1 main ] stop
 */
	NB_ITERATE_tSetGlobalVar_1++;
	
	
					if(execStat){				
	       				runStat.updateStatOnConnection("OnComponentOk1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row3", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row2", 3, 0);
					}           			
				
				if(execStat){
					runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tSetGlobalVar_1);
					//Thread.sleep(1000);
				}				
			

	
	/**
	 * [tSetGlobalVar_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tSetGlobalVar_1", false);
		start_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());
		
	
	currentComponent="tSetGlobalVar_1";

	
		int tos_count_tSetGlobalVar_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tSetGlobalVar_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tSetGlobalVar_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tSetGlobalVar_1 = new StringBuilder();
            log4jParamters_tSetGlobalVar_1.append("Parameters:");
                    log4jParamters_tSetGlobalVar_1.append("VARIABLES" + " = " + "[{VALUE="+("((Integer)globalMap.get(\"row1.Trader_ID1\"))")+", KEY="+("\"Trade_ID1\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Trader_ID2\"))")+", KEY="+("\"Trade_ID2\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Trader_ID3\"))")+", KEY="+("\"Trade_ID3\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Broker_ID1\"))")+", KEY="+("\"Broker_ID1\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Broker_ID2\"))")+", KEY="+("\"Broker_ID2\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Broker_ID3\"))")+", KEY="+("\"Broker_ID3\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Company_ID1\"))")+", KEY="+("\"Company_ID1\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Company_ID2\"))")+", KEY="+("\"Company_ID2\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Company_ID3\"))")+", KEY="+("\"Company_ID3\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Quantity1\"))")+", KEY="+("\"Quantity1\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Quantity2\"))")+", KEY="+("\"Quantity2\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Quantity3\"))")+", KEY="+("\"Quantity3\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Price1\"))")+", KEY="+("\"Price1\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Price2\"))")+", KEY="+("\"Price2\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row1.Price3\"))")+", KEY="+("\"Price3\"")+"}]");
                log4jParamters_tSetGlobalVar_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tSetGlobalVar_1 - "  + (log4jParamters_tSetGlobalVar_1) );
    		}
    	}
    	
        new BytesLimit65535_tSetGlobalVar_1().limitLog4jByte();

 



/**
 * [tSetGlobalVar_1 begin ] stop
 */
	
	/**
	 * [tSetGlobalVar_1 main ] start
	 */

	

	
	
	currentComponent="tSetGlobalVar_1";

	

globalMap.put("Trade_ID1", ((Integer)globalMap.get("row1.Trader_ID1")));
globalMap.put("Trade_ID2", ((Integer)globalMap.get("row1.Trader_ID2")));
globalMap.put("Trade_ID3", ((Integer)globalMap.get("row1.Trader_ID3")));
globalMap.put("Broker_ID1", ((Integer)globalMap.get("row1.Broker_ID1")));
globalMap.put("Broker_ID2", ((Integer)globalMap.get("row1.Broker_ID2")));
globalMap.put("Broker_ID3", ((Integer)globalMap.get("row1.Broker_ID3")));
globalMap.put("Company_ID1", ((Integer)globalMap.get("row1.Company_ID1")));
globalMap.put("Company_ID2", ((Integer)globalMap.get("row1.Company_ID2")));
globalMap.put("Company_ID3", ((Integer)globalMap.get("row1.Company_ID3")));
globalMap.put("Quantity1", ((Integer)globalMap.get("row1.Quantity1")));
globalMap.put("Quantity2", ((Integer)globalMap.get("row1.Quantity2")));
globalMap.put("Quantity3", ((Integer)globalMap.get("row1.Quantity3")));
globalMap.put("Price1", ((Integer)globalMap.get("row1.Price1")));
globalMap.put("Price2", ((Integer)globalMap.get("row1.Price2")));
globalMap.put("Price3", ((Integer)globalMap.get("row1.Price3")));

 


	tos_count_tSetGlobalVar_1++;

/**
 * [tSetGlobalVar_1 main ] stop
 */
	
	/**
	 * [tSetGlobalVar_1 end ] start
	 */

	

	
	
	currentComponent="tSetGlobalVar_1";

	

 
                if(log.isDebugEnabled())
            log.debug("tSetGlobalVar_1 - "  + ("Done.") );

ok_Hash.put("tSetGlobalVar_1", true);
end_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				if(execStat){   
   	 				runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tRowGenerator_1Process(globalMap);



/**
 * [tSetGlobalVar_1 end ] stop
 */
						if(execStat){
							runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tSetGlobalVar_1);
						}				
					







	
	/**
	 * [tOracleInput_1 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_1";

	

}
}finally{
stmt_tOracleInput_1.close();

	if(conn_tOracleInput_1 != null && !conn_tOracleInput_1.isClosed()) {
	
	    		log.debug("tOracleInput_1 - Closing the connection to the database.");
			
			conn_tOracleInput_1.close();
			
	    		log.debug("tOracleInput_1 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_1_NB_LINE",nb_line_tOracleInput_1);
	    		log.debug("tOracleInput_1 - Retrieved records count: "+nb_line_tOracleInput_1 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_1 - "  + ("Done.") );

ok_Hash.put("tOracleInput_1", true);
end_Hash.put("tOracleInput_1", System.currentTimeMillis());




/**
 * [tOracleInput_1 end ] stop
 */

	
	/**
	 * [tFlowToIterate_1 end ] start
	 */

	

	
	
	currentComponent="tFlowToIterate_1";

	

globalMap.put("tFlowToIterate_1_NB_LINE",nb_line_tFlowToIterate_1);
			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row1"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tFlowToIterate_1 - "  + ("Done.") );

ok_Hash.put("tFlowToIterate_1", true);
end_Hash.put("tFlowToIterate_1", System.currentTimeMillis());




/**
 * [tFlowToIterate_1 end ] stop
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
	 * [tOracleInput_1 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_1";

	

 



/**
 * [tOracleInput_1 finally ] stop
 */

	
	/**
	 * [tFlowToIterate_1 finally ] start
	 */

	

	
	
	currentComponent="tFlowToIterate_1";

	

 



/**
 * [tFlowToIterate_1 finally ] stop
 */

	
	/**
	 * [tSetGlobalVar_1 finally ] start
	 */

	

	
	
	currentComponent="tSetGlobalVar_1";

	

 



/**
 * [tSetGlobalVar_1 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_MADHAV_CombFCP = new byte[0];
    static byte[] commonByteArray_MADHAV_CombFCP = new byte[0];

	
			    public Integer TRADER_ID;

				public Integer getTRADER_ID () {
					return this.TRADER_ID;
				}
				
			    public Integer BROKER_ID;

				public Integer getBROKER_ID () {
					return this.BROKER_ID;
				}
				
			    public java.util.Date TIMESTAMP;

				public java.util.Date getTIMESTAMP () {
					return this.TIMESTAMP;
				}
				
			    public Integer COMPANY_ID;

				public Integer getCOMPANY_ID () {
					return this.COMPANY_ID;
				}
				
			    public String SECURITY_TP;

				public String getSECURITY_TP () {
					return this.SECURITY_TP;
				}
				
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float PRICE;

				public Float getPRICE () {
					return this.PRICE;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_CombFCP.length) {
				if(length < 1024 && commonByteArray_MADHAV_CombFCP.length == 0) {
   					commonByteArray_MADHAV_CombFCP = new byte[1024];
				} else {
   					commonByteArray_MADHAV_CombFCP = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_CombFCP, 0, length);
			strReturn = new String(commonByteArray_MADHAV_CombFCP, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_MADHAV_CombFCP) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.PRICE = null;
           				} else {
           			    	this.PRICE = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.TRADER_ID,dos);
					
					// Integer
				
						writeInteger(this.BROKER_ID,dos);
					
					// java.util.Date
				
						writeDate(this.TIMESTAMP,dos);
					
					// Integer
				
						writeInteger(this.COMPANY_ID,dos);
					
					// String
				
						writeString(this.SECURITY_TP,dos);
					
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.PRICE == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.PRICE);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADER_ID="+String.valueOf(TRADER_ID));
		sb.append(",BROKER_ID="+String.valueOf(BROKER_ID));
		sb.append(",TIMESTAMP="+String.valueOf(TIMESTAMP));
		sb.append(",COMPANY_ID="+String.valueOf(COMPANY_ID));
		sb.append(",SECURITY_TP="+SECURITY_TP);
		sb.append(",TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(TRADER_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADER_ID);
            			}
            		
        			sb.append("|");
        		
        				if(BROKER_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(BROKER_ID);
            			}
            		
        			sb.append("|");
        		
        				if(TIMESTAMP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TIMESTAMP);
            			}
            		
        			sb.append("|");
        		
        				if(COMPANY_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(COMPANY_ID);
            			}
            		
        			sb.append("|");
        		
        				if(SECURITY_TP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(SECURITY_TP);
            			}
            		
        			sb.append("|");
        		
        				if(TRADE_TP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADE_TP);
            			}
            		
        			sb.append("|");
        		
        				if(QUANTITY == null){
        					sb.append("<null>");
        				}else{
            				sb.append(QUANTITY);
            			}
            		
        			sb.append("|");
        		
        				if(PRICE == null){
        					sb.append("<null>");
        				}else{
            				sb.append(PRICE);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tRowGenerator_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRowGenerator_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row2Struct row2 = new row2Struct();




	
	/**
	 * [tOracleOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_1", false);
		start_Hash.put("tOracleOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row2" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_1 = new StringBuilder();
            log4jParamters_tOracleOutput_1.append("Parameters:");
                    log4jParamters_tOracleOutput_1.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("TABLE" + " = " + "\"STAGE3\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + (log4jParamters_tOracleOutput_1) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_1().limitLog4jByte();






    int nb_line_tOracleOutput_1 = 0;
    int nb_line_update_tOracleOutput_1 = 0;
    int nb_line_inserted_tOracleOutput_1 = 0;
    int nb_line_deleted_tOracleOutput_1 = 0;
    int nb_line_rejected_tOracleOutput_1 = 0;

    int tmp_batchUpdateCount_tOracleOutput_1 = 0;

    int deletedCount_tOracleOutput_1=0;
    int updatedCount_tOracleOutput_1=0;
    int insertedCount_tOracleOutput_1=0;
    int rejectedCount_tOracleOutput_1=0;

    boolean whetherReject_tOracleOutput_1 = false;

    java.sql.Connection conn_tOracleOutput_1 = null;

    //optional table
    String dbschema_tOracleOutput_1 = null;
    String tableName_tOracleOutput_1 = null;
                    String driverClass_tOracleOutput_1 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_1)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_1);
                String url_tOracleOutput_1 = null;
                    url_tOracleOutput_1 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_1 = "hr";
 
	final String decryptedPassword_tOracleOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_1 = decryptedPassword_tOracleOutput_1;
                dbschema_tOracleOutput_1 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection attempts to '")  + (url_tOracleOutput_1)  + ("' with the username '")  + (dbUser_tOracleOutput_1)  + ("'.") );

                    conn_tOracleOutput_1 = java.sql.DriverManager.getConnection(url_tOracleOutput_1, dbUser_tOracleOutput_1, dbPwd_tOracleOutput_1);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection to '")  + (url_tOracleOutput_1)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_1", conn_tOracleOutput_1);
            conn_tOracleOutput_1.setAutoCommit(false);
            int commitEvery_tOracleOutput_1 = 10000;
            int commitCounter_tOracleOutput_1 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_1.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_1 = 10000;
        int batchSizeCounter_tOracleOutput_1=0;
        int count_tOracleOutput_1=0;

        if(dbschema_tOracleOutput_1 == null || dbschema_tOracleOutput_1.trim().length() == 0) {
            tableName_tOracleOutput_1 = ("STAGE3");
        } else {
            tableName_tOracleOutput_1 = dbschema_tOracleOutput_1 + "." + ("STAGE3");
        }
                                String tableNameForSearch_tOracleOutput_1= "" + ((String)"STAGE3") + "";
String dbschemaForSearch_tOracleOutput_1= null;
if(dbschema_tOracleOutput_1== null || dbschema_tOracleOutput_1.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_1= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_1= dbschema_tOracleOutput_1.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_1 = conn_tOracleOutput_1.getMetaData();
                                if(tableNameForSearch_tOracleOutput_1.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_1 = tableNameForSearch_tOracleOutput_1.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_1 = tableNameForSearch_tOracleOutput_1.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_1 = dbMetaData_tOracleOutput_1.getTables(null, dbschemaForSearch_tOracleOutput_1, tableNameForSearch_tOracleOutput_1, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_1 = false;
                                if(rsTable_tOracleOutput_1.next()) {
                                    whetherExist_tOracleOutput_1 = true;
                                }
                                rsTable_tOracleOutput_1.close();

                                if(!whetherExist_tOracleOutput_1) {
                                    java.sql.Statement stmtCreate_tOracleOutput_1 = conn_tOracleOutput_1.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_1)  + ("'.") );
                                        stmtCreate_tOracleOutput_1.execute("CREATE TABLE " + tableName_tOracleOutput_1 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_1)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_1.close();
                                }
                String insert_tOracleOutput_1 = "INSERT INTO " + tableName_tOracleOutput_1 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_1 = conn_tOracleOutput_1.prepareStatement(insert_tOracleOutput_1);





 



/**
 * [tOracleOutput_1 begin ] stop
 */



	
	/**
	 * [tRowGenerator_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tRowGenerator_1", false);
		start_Hash.put("tRowGenerator_1", System.currentTimeMillis());
		
	
	currentComponent="tRowGenerator_1";

	
		int tos_count_tRowGenerator_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tRowGenerator_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRowGenerator_1 = new StringBuilder();
            log4jParamters_tRowGenerator_1.append("Parameters:");
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_1 - "  + (log4jParamters_tRowGenerator_1) );
    		}
    	}
    	
        new BytesLimit65535_tRowGenerator_1().limitLog4jByte();


int nb_line_tRowGenerator_1 = 0;
int nb_max_row_tRowGenerator_1 = 30;


class tRowGenerator_1Randomizer {
	public Integer getRandomTRADER_ID() {
		 
		return ((Integer)globalMap.get("row1.Trader_ID1")) ;
		
	}
	public Integer getRandomBROKER_ID() {
		 
		return ((Integer)globalMap.get("row1.Broker_ID1")) ;
		
	}
	public java.util.Date getRandomTIMESTAMP() {
		
		return TalendDate.getCurrentDate() ;
		
	}
	public Integer getRandomCOMPANY_ID() {
		 
		return ((Integer)globalMap.get("row1.Company_ID1")) ;
		
	}
	public String getRandomSECURITY_TP() {
		
		String[] SECURITY_TPTable = new String[] { "future","call","put","equity"  };
		java.util.Random randomtRowGenerator_1 = new java.util.Random();
		return SECURITY_TPTable[randomtRowGenerator_1.nextInt(SECURITY_TPTable.length)];
		
	}
	public String getRandomTRADE_TP() {
		
		String[] TRADE_TPTable = new String[] { "buy","sell"  };
		java.util.Random randomtRowGenerator_1 = new java.util.Random();
		return TRADE_TPTable[randomtRowGenerator_1.nextInt(TRADE_TPTable.length)];
		
	}
	public Integer getRandomQUANTITY() {
		 
		return ((Integer)globalMap.get("row1.Quantity1"))*50 ;
		
	}
	public Float getRandomPRICE() {
		 
		return (float)(((float)(Integer)globalMap.get("row1.Price1"))*25/100) ;
		
	}
}
	tRowGenerator_1Randomizer randtRowGenerator_1 = new tRowGenerator_1Randomizer();
	
    	log.info("tRowGenerator_1 - Generating records.");
	for (int itRowGenerator_1=0; itRowGenerator_1<nb_max_row_tRowGenerator_1 ;itRowGenerator_1++) {
		row2.TRADER_ID = randtRowGenerator_1.getRandomTRADER_ID();
		row2.BROKER_ID = randtRowGenerator_1.getRandomBROKER_ID();
		row2.TIMESTAMP = randtRowGenerator_1.getRandomTIMESTAMP();
		row2.COMPANY_ID = randtRowGenerator_1.getRandomCOMPANY_ID();
		row2.SECURITY_TP = randtRowGenerator_1.getRandomSECURITY_TP();
		row2.TRADE_TP = randtRowGenerator_1.getRandomTRADE_TP();
		row2.QUANTITY = randtRowGenerator_1.getRandomQUANTITY();
		row2.PRICE = randtRowGenerator_1.getRandomPRICE();
		nb_line_tRowGenerator_1++;
		
			log.debug("tRowGenerator_1 - Retrieving the record " + nb_line_tRowGenerator_1 + ".");
		

 



/**
 * [tRowGenerator_1 begin ] stop
 */
	
	/**
	 * [tRowGenerator_1 main ] start
	 */

	

	
	
	currentComponent="tRowGenerator_1";

	

 


	tos_count_tRowGenerator_1++;

/**
 * [tRowGenerator_1 main ] stop
 */

	
	/**
	 * [tOracleOutput_1 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_1";

	

			//row2
			//row2


			
				if(execStat){
					runStat.updateStatOnConnection("row2"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row2 - " + (row2==null? "": row2.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_1 = false;
                        if(row2.TRADER_ID == null) {
pstmt_tOracleOutput_1.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(1, row2.TRADER_ID);
}

                        if(row2.BROKER_ID == null) {
pstmt_tOracleOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(2, row2.BROKER_ID);
}

                        if(row2.TIMESTAMP != null) {
pstmt_tOracleOutput_1.setObject(3, new java.sql.Timestamp(row2.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_1.setNull(3, java.sql.Types.DATE);
}

                        if(row2.COMPANY_ID == null) {
pstmt_tOracleOutput_1.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(4, row2.COMPANY_ID);
}

                        if(row2.SECURITY_TP == null) {
pstmt_tOracleOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_1.setString(5, row2.SECURITY_TP);
}

                        if(row2.TRADE_TP == null) {
pstmt_tOracleOutput_1.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_1.setString(6, row2.TRADE_TP);
}

                        if(row2.QUANTITY == null) {
pstmt_tOracleOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(7, row2.QUANTITY);
}

                        if(row2.PRICE == null) {
pstmt_tOracleOutput_1.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_1.setFloat(8, row2.PRICE);
}

                pstmt_tOracleOutput_1.addBatch();
                nb_line_tOracleOutput_1++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_1)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_1++;
            if (batchSize_tOracleOutput_1 > 0 &&  batchSize_tOracleOutput_1 <= batchSizeCounter_tOracleOutput_1) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_1.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_1){
			        java.sql.SQLException ne_tOracleOutput_1 = e_tOracleOutput_1.getNextException(),sqle_tOracleOutput_1=null;
			    	String errormessage_tOracleOutput_1;
					if (ne_tOracleOutput_1 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_1 = new java.sql.SQLException(e_tOracleOutput_1.getMessage() + "\ncaused by: " + ne_tOracleOutput_1.getMessage(), ne_tOracleOutput_1.getSQLState(), ne_tOracleOutput_1.getErrorCode(), ne_tOracleOutput_1);
						errormessage_tOracleOutput_1 = sqle_tOracleOutput_1.getMessage();
					}else{
						errormessage_tOracleOutput_1 = e_tOracleOutput_1.getMessage();
					}
	            	
            log.error("tOracleOutput_1 - "  + (errormessage_tOracleOutput_1) );
	                	System.err.println(errormessage_tOracleOutput_1);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_1 = pstmt_tOracleOutput_1.getUpdateCount();
                    insertedCount_tOracleOutput_1
                += (tmp_batchUpdateCount_tOracleOutput_1!=-1?tmp_batchUpdateCount_tOracleOutput_1:0);
                batchSizeCounter_tOracleOutput_1 = 0;
            }
                commitCounter_tOracleOutput_1++;
                if(commitEvery_tOracleOutput_1 <= commitCounter_tOracleOutput_1) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_1.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_1){
					        java.sql.SQLException ne_tOracleOutput_1 = e_tOracleOutput_1.getNextException(),sqle_tOracleOutput_1=null;
					    	String errormessage_tOracleOutput_1;
							if (ne_tOracleOutput_1 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_1 = new java.sql.SQLException(e_tOracleOutput_1.getMessage() + "\ncaused by: " + ne_tOracleOutput_1.getMessage(), ne_tOracleOutput_1.getSQLState(), ne_tOracleOutput_1.getErrorCode(), ne_tOracleOutput_1);
								errormessage_tOracleOutput_1 = sqle_tOracleOutput_1.getMessage();
							}else{
								errormessage_tOracleOutput_1 = e_tOracleOutput_1.getMessage();
							}
			            	
            log.error("tOracleOutput_1 - "  + (errormessage_tOracleOutput_1) );
			                	System.err.println(errormessage_tOracleOutput_1);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_1 = pstmt_tOracleOutput_1.getUpdateCount();
                            insertedCount_tOracleOutput_1
                        += (tmp_batchUpdateCount_tOracleOutput_1!=-1?tmp_batchUpdateCount_tOracleOutput_1:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_1)  + (" record(s).") );
                    conn_tOracleOutput_1.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_1=0;
                }

 


	tos_count_tOracleOutput_1++;

/**
 * [tOracleOutput_1 main ] stop
 */



	
	/**
	 * [tRowGenerator_1 end ] start
	 */

	

	
	
	currentComponent="tRowGenerator_1";

	

}
globalMap.put("tRowGenerator_1_NB_LINE",nb_line_tRowGenerator_1);
	log.info("tRowGenerator_1 - Generated records count:" + nb_line_tRowGenerator_1 + " .");

 
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_1 - "  + ("Done.") );

ok_Hash.put("tRowGenerator_1", true);
end_Hash.put("tRowGenerator_1", System.currentTimeMillis());




/**
 * [tRowGenerator_1 end ] stop
 */

	
	/**
	 * [tOracleOutput_1 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_1";

	
	



	
            try {
            	if (pstmt_tOracleOutput_1 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_1.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_1){
		        java.sql.SQLException ne_tOracleOutput_1 = e_tOracleOutput_1.getNextException(),sqle_tOracleOutput_1=null;
		    	String errormessage_tOracleOutput_1;
				if (ne_tOracleOutput_1 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_1 = new java.sql.SQLException(e_tOracleOutput_1.getMessage() + "\ncaused by: " + ne_tOracleOutput_1.getMessage(), ne_tOracleOutput_1.getSQLState(), ne_tOracleOutput_1.getErrorCode(), ne_tOracleOutput_1);
					errormessage_tOracleOutput_1 = sqle_tOracleOutput_1.getMessage();
				}else{
					errormessage_tOracleOutput_1 = e_tOracleOutput_1.getMessage();
				}
            	
            log.error("tOracleOutput_1 - "  + (errormessage_tOracleOutput_1) );
                	System.err.println(errormessage_tOracleOutput_1);
            	
        	}
        	if (pstmt_tOracleOutput_1 != null) {
            	tmp_batchUpdateCount_tOracleOutput_1 = pstmt_tOracleOutput_1.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_1
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_1!=-1?tmp_batchUpdateCount_tOracleOutput_1:0);
            }
        if(pstmt_tOracleOutput_1 != null) {
			
				pstmt_tOracleOutput_1.close();
			
        }
		if(commitCounter_tOracleOutput_1 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_1)  + (" record(s).") );
		    conn_tOracleOutput_1.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_1 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_1", true);
   	


	
	nb_line_deleted_tOracleOutput_1=nb_line_deleted_tOracleOutput_1+ deletedCount_tOracleOutput_1;
	nb_line_update_tOracleOutput_1=nb_line_update_tOracleOutput_1 + updatedCount_tOracleOutput_1;
	nb_line_inserted_tOracleOutput_1=nb_line_inserted_tOracleOutput_1 + insertedCount_tOracleOutput_1;
	nb_line_rejected_tOracleOutput_1=nb_line_rejected_tOracleOutput_1 + rejectedCount_tOracleOutput_1;
	
        globalMap.put("tOracleOutput_1_NB_LINE",nb_line_tOracleOutput_1);
        globalMap.put("tOracleOutput_1_NB_LINE_UPDATED",nb_line_update_tOracleOutput_1);
        globalMap.put("tOracleOutput_1_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_1);
        globalMap.put("tOracleOutput_1_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_1);
        globalMap.put("tOracleOutput_1_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_1);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_1)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row2"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_1", true);
end_Hash.put("tOracleOutput_1", System.currentTimeMillis());




/**
 * [tOracleOutput_1 end ] stop
 */



				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRowGenerator_1:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
								} 
							
							tRowGenerator_2Process(globalMap); 
						



	
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
	 * [tRowGenerator_1 finally ] start
	 */

	

	
	
	currentComponent="tRowGenerator_1";

	

 



/**
 * [tRowGenerator_1 finally ] stop
 */

	
	/**
	 * [tOracleOutput_1 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_1";

	



	
		if(resourceMap.get("finish_tOracleOutput_1")==null){
			if(resourceMap.get("conn_tOracleOutput_1")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_1 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_1");
					
					
            		
					ctn_tOracleOutput_1.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_1) {
					String errorMessage_tOracleOutput_1 = "failed to close the connection in tOracleOutput_1 :" + sqlEx_tOracleOutput_1.getMessage();
					
            log.error("tOracleOutput_1 - "  + (errorMessage_tOracleOutput_1) );
					System.err.println(errorMessage_tOracleOutput_1);
				}
			}
		}
	

 



/**
 * [tOracleOutput_1 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRowGenerator_1_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_MADHAV_CombFCP = new byte[0];
    static byte[] commonByteArray_MADHAV_CombFCP = new byte[0];

	
			    public Integer TRADER_ID;

				public Integer getTRADER_ID () {
					return this.TRADER_ID;
				}
				
			    public Integer BROKER_ID;

				public Integer getBROKER_ID () {
					return this.BROKER_ID;
				}
				
			    public java.util.Date TIMESTAMP;

				public java.util.Date getTIMESTAMP () {
					return this.TIMESTAMP;
				}
				
			    public Integer COMPANY_ID;

				public Integer getCOMPANY_ID () {
					return this.COMPANY_ID;
				}
				
			    public String SECURITY_TP;

				public String getSECURITY_TP () {
					return this.SECURITY_TP;
				}
				
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float PRICE;

				public Float getPRICE () {
					return this.PRICE;
				}
				


	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_CombFCP.length) {
				if(length < 1024 && commonByteArray_MADHAV_CombFCP.length == 0) {
   					commonByteArray_MADHAV_CombFCP = new byte[1024];
				} else {
   					commonByteArray_MADHAV_CombFCP = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_CombFCP, 0, length);
			strReturn = new String(commonByteArray_MADHAV_CombFCP, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_MADHAV_CombFCP) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.PRICE = null;
           				} else {
           			    	this.PRICE = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.TRADER_ID,dos);
					
					// Integer
				
						writeInteger(this.BROKER_ID,dos);
					
					// java.util.Date
				
						writeDate(this.TIMESTAMP,dos);
					
					// Integer
				
						writeInteger(this.COMPANY_ID,dos);
					
					// String
				
						writeString(this.SECURITY_TP,dos);
					
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.PRICE == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.PRICE);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADER_ID="+String.valueOf(TRADER_ID));
		sb.append(",BROKER_ID="+String.valueOf(BROKER_ID));
		sb.append(",TIMESTAMP="+String.valueOf(TIMESTAMP));
		sb.append(",COMPANY_ID="+String.valueOf(COMPANY_ID));
		sb.append(",SECURITY_TP="+SECURITY_TP);
		sb.append(",TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(TRADER_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADER_ID);
            			}
            		
        			sb.append("|");
        		
        				if(BROKER_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(BROKER_ID);
            			}
            		
        			sb.append("|");
        		
        				if(TIMESTAMP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TIMESTAMP);
            			}
            		
        			sb.append("|");
        		
        				if(COMPANY_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(COMPANY_ID);
            			}
            		
        			sb.append("|");
        		
        				if(SECURITY_TP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(SECURITY_TP);
            			}
            		
        			sb.append("|");
        		
        				if(TRADE_TP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADE_TP);
            			}
            		
        			sb.append("|");
        		
        				if(QUANTITY == null){
        					sb.append("<null>");
        				}else{
            				sb.append(QUANTITY);
            			}
            		
        			sb.append("|");
        		
        				if(PRICE == null){
        					sb.append("<null>");
        				}else{
            				sb.append(PRICE);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tRowGenerator_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRowGenerator_2_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row3Struct row3 = new row3Struct();




	
	/**
	 * [tOracleOutput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_2", false);
		start_Hash.put("tOracleOutput_2", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_2";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row3" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_2{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_2 = new StringBuilder();
            log4jParamters_tOracleOutput_2.append("Parameters:");
                    log4jParamters_tOracleOutput_2.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("TABLE" + " = " + "\"STAGE3\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + (log4jParamters_tOracleOutput_2) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_2().limitLog4jByte();






    int nb_line_tOracleOutput_2 = 0;
    int nb_line_update_tOracleOutput_2 = 0;
    int nb_line_inserted_tOracleOutput_2 = 0;
    int nb_line_deleted_tOracleOutput_2 = 0;
    int nb_line_rejected_tOracleOutput_2 = 0;

    int tmp_batchUpdateCount_tOracleOutput_2 = 0;

    int deletedCount_tOracleOutput_2=0;
    int updatedCount_tOracleOutput_2=0;
    int insertedCount_tOracleOutput_2=0;
    int rejectedCount_tOracleOutput_2=0;

    boolean whetherReject_tOracleOutput_2 = false;

    java.sql.Connection conn_tOracleOutput_2 = null;

    //optional table
    String dbschema_tOracleOutput_2 = null;
    String tableName_tOracleOutput_2 = null;
                    String driverClass_tOracleOutput_2 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_2)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_2);
                String url_tOracleOutput_2 = null;
                    url_tOracleOutput_2 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_2 = "hr";
 
	final String decryptedPassword_tOracleOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_2 = decryptedPassword_tOracleOutput_2;
                dbschema_tOracleOutput_2 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection attempts to '")  + (url_tOracleOutput_2)  + ("' with the username '")  + (dbUser_tOracleOutput_2)  + ("'.") );

                    conn_tOracleOutput_2 = java.sql.DriverManager.getConnection(url_tOracleOutput_2, dbUser_tOracleOutput_2, dbPwd_tOracleOutput_2);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection to '")  + (url_tOracleOutput_2)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_2", conn_tOracleOutput_2);
            conn_tOracleOutput_2.setAutoCommit(false);
            int commitEvery_tOracleOutput_2 = 10000;
            int commitCounter_tOracleOutput_2 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_2.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_2 = 10000;
        int batchSizeCounter_tOracleOutput_2=0;
        int count_tOracleOutput_2=0;

        if(dbschema_tOracleOutput_2 == null || dbschema_tOracleOutput_2.trim().length() == 0) {
            tableName_tOracleOutput_2 = ("STAGE3");
        } else {
            tableName_tOracleOutput_2 = dbschema_tOracleOutput_2 + "." + ("STAGE3");
        }
                                String tableNameForSearch_tOracleOutput_2= "" + ((String)"STAGE3") + "";
String dbschemaForSearch_tOracleOutput_2= null;
if(dbschema_tOracleOutput_2== null || dbschema_tOracleOutput_2.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_2= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_2= dbschema_tOracleOutput_2.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_2 = conn_tOracleOutput_2.getMetaData();
                                if(tableNameForSearch_tOracleOutput_2.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_2 = tableNameForSearch_tOracleOutput_2.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_2 = tableNameForSearch_tOracleOutput_2.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_2 = dbMetaData_tOracleOutput_2.getTables(null, dbschemaForSearch_tOracleOutput_2, tableNameForSearch_tOracleOutput_2, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_2 = false;
                                if(rsTable_tOracleOutput_2.next()) {
                                    whetherExist_tOracleOutput_2 = true;
                                }
                                rsTable_tOracleOutput_2.close();

                                if(!whetherExist_tOracleOutput_2) {
                                    java.sql.Statement stmtCreate_tOracleOutput_2 = conn_tOracleOutput_2.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_2)  + ("'.") );
                                        stmtCreate_tOracleOutput_2.execute("CREATE TABLE " + tableName_tOracleOutput_2 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(0)  ,TRADE_TP VARCHAR2(0)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_2)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_2.close();
                                }
                String insert_tOracleOutput_2 = "INSERT INTO " + tableName_tOracleOutput_2 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_2 = conn_tOracleOutput_2.prepareStatement(insert_tOracleOutput_2);





 



/**
 * [tOracleOutput_2 begin ] stop
 */



	
	/**
	 * [tRowGenerator_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tRowGenerator_2", false);
		start_Hash.put("tRowGenerator_2", System.currentTimeMillis());
		
	
	currentComponent="tRowGenerator_2";

	
		int tos_count_tRowGenerator_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_2 - "  + ("Start to work.") );
    	class BytesLimit65535_tRowGenerator_2{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRowGenerator_2 = new StringBuilder();
            log4jParamters_tRowGenerator_2.append("Parameters:");
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_2 - "  + (log4jParamters_tRowGenerator_2) );
    		}
    	}
    	
        new BytesLimit65535_tRowGenerator_2().limitLog4jByte();


int nb_line_tRowGenerator_2 = 0;
int nb_max_row_tRowGenerator_2 = 30;


class tRowGenerator_2Randomizer {
	public Integer getRandomTRADER_ID() {
		 
		return ((Integer)globalMap.get("row1.Trader_ID2")) ;
		
	}
	public Integer getRandomBROKER_ID() {
		 
		return ((Integer)globalMap.get("row1.Broker_ID2")) ;
		
	}
	public java.util.Date getRandomTIMESTAMP() {
		
		return TalendDate.getCurrentDate() ;
		
	}
	public Integer getRandomCOMPANY_ID() {
		 
		return ((Integer)globalMap.get("row1.Company_ID2")) ;
		
	}
	public String getRandomSECURITY_TP() {
		
		String[] SECURITY_TPTable = new String[] { "future","call","put","equity"  };
		java.util.Random randomtRowGenerator_2 = new java.util.Random();
		return SECURITY_TPTable[randomtRowGenerator_2.nextInt(SECURITY_TPTable.length)];
		
	}
	public String getRandomTRADE_TP() {
		
		String[] TRADE_TPTable = new String[] { "buy","sell"  };
		java.util.Random randomtRowGenerator_2 = new java.util.Random();
		return TRADE_TPTable[randomtRowGenerator_2.nextInt(TRADE_TPTable.length)];
		
	}
	public Integer getRandomQUANTITY() {
		 
		return ((Integer)globalMap.get("row1.Quantity2"))*50 ;
		
	}
	public Float getRandomPRICE() {
		 
		return (float)(((float)(Integer)globalMap.get("row1.Price2"))*25/100) ;
		
	}
}
	tRowGenerator_2Randomizer randtRowGenerator_2 = new tRowGenerator_2Randomizer();
	
    	log.info("tRowGenerator_2 - Generating records.");
	for (int itRowGenerator_2=0; itRowGenerator_2<nb_max_row_tRowGenerator_2 ;itRowGenerator_2++) {
		row3.TRADER_ID = randtRowGenerator_2.getRandomTRADER_ID();
		row3.BROKER_ID = randtRowGenerator_2.getRandomBROKER_ID();
		row3.TIMESTAMP = randtRowGenerator_2.getRandomTIMESTAMP();
		row3.COMPANY_ID = randtRowGenerator_2.getRandomCOMPANY_ID();
		row3.SECURITY_TP = randtRowGenerator_2.getRandomSECURITY_TP();
		row3.TRADE_TP = randtRowGenerator_2.getRandomTRADE_TP();
		row3.QUANTITY = randtRowGenerator_2.getRandomQUANTITY();
		row3.PRICE = randtRowGenerator_2.getRandomPRICE();
		nb_line_tRowGenerator_2++;
		
			log.debug("tRowGenerator_2 - Retrieving the record " + nb_line_tRowGenerator_2 + ".");
		

 



/**
 * [tRowGenerator_2 begin ] stop
 */
	
	/**
	 * [tRowGenerator_2 main ] start
	 */

	

	
	
	currentComponent="tRowGenerator_2";

	

 


	tos_count_tRowGenerator_2++;

/**
 * [tRowGenerator_2 main ] stop
 */

	
	/**
	 * [tOracleOutput_2 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_2";

	

			//row3
			//row3


			
				if(execStat){
					runStat.updateStatOnConnection("row3"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row3 - " + (row3==null? "": row3.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_2 = false;
                        if(row3.TRADER_ID == null) {
pstmt_tOracleOutput_2.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(1, row3.TRADER_ID);
}

                        if(row3.BROKER_ID == null) {
pstmt_tOracleOutput_2.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(2, row3.BROKER_ID);
}

                        if(row3.TIMESTAMP != null) {
pstmt_tOracleOutput_2.setObject(3, new java.sql.Timestamp(row3.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_2.setNull(3, java.sql.Types.DATE);
}

                        if(row3.COMPANY_ID == null) {
pstmt_tOracleOutput_2.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(4, row3.COMPANY_ID);
}

                        if(row3.SECURITY_TP == null) {
pstmt_tOracleOutput_2.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(5, row3.SECURITY_TP);
}

                        if(row3.TRADE_TP == null) {
pstmt_tOracleOutput_2.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(6, row3.TRADE_TP);
}

                        if(row3.QUANTITY == null) {
pstmt_tOracleOutput_2.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(7, row3.QUANTITY);
}

                        if(row3.PRICE == null) {
pstmt_tOracleOutput_2.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_2.setFloat(8, row3.PRICE);
}

                pstmt_tOracleOutput_2.addBatch();
                nb_line_tOracleOutput_2++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_2)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_2++;
            if (batchSize_tOracleOutput_2 > 0 &&  batchSize_tOracleOutput_2 <= batchSizeCounter_tOracleOutput_2) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_2.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_2){
			        java.sql.SQLException ne_tOracleOutput_2 = e_tOracleOutput_2.getNextException(),sqle_tOracleOutput_2=null;
			    	String errormessage_tOracleOutput_2;
					if (ne_tOracleOutput_2 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_2 = new java.sql.SQLException(e_tOracleOutput_2.getMessage() + "\ncaused by: " + ne_tOracleOutput_2.getMessage(), ne_tOracleOutput_2.getSQLState(), ne_tOracleOutput_2.getErrorCode(), ne_tOracleOutput_2);
						errormessage_tOracleOutput_2 = sqle_tOracleOutput_2.getMessage();
					}else{
						errormessage_tOracleOutput_2 = e_tOracleOutput_2.getMessage();
					}
	            	
            log.error("tOracleOutput_2 - "  + (errormessage_tOracleOutput_2) );
	                	System.err.println(errormessage_tOracleOutput_2);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_2 = pstmt_tOracleOutput_2.getUpdateCount();
                    insertedCount_tOracleOutput_2
                += (tmp_batchUpdateCount_tOracleOutput_2!=-1?tmp_batchUpdateCount_tOracleOutput_2:0);
                batchSizeCounter_tOracleOutput_2 = 0;
            }
                commitCounter_tOracleOutput_2++;
                if(commitEvery_tOracleOutput_2 <= commitCounter_tOracleOutput_2) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_2.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_2){
					        java.sql.SQLException ne_tOracleOutput_2 = e_tOracleOutput_2.getNextException(),sqle_tOracleOutput_2=null;
					    	String errormessage_tOracleOutput_2;
							if (ne_tOracleOutput_2 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_2 = new java.sql.SQLException(e_tOracleOutput_2.getMessage() + "\ncaused by: " + ne_tOracleOutput_2.getMessage(), ne_tOracleOutput_2.getSQLState(), ne_tOracleOutput_2.getErrorCode(), ne_tOracleOutput_2);
								errormessage_tOracleOutput_2 = sqle_tOracleOutput_2.getMessage();
							}else{
								errormessage_tOracleOutput_2 = e_tOracleOutput_2.getMessage();
							}
			            	
            log.error("tOracleOutput_2 - "  + (errormessage_tOracleOutput_2) );
			                	System.err.println(errormessage_tOracleOutput_2);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_2 = pstmt_tOracleOutput_2.getUpdateCount();
                            insertedCount_tOracleOutput_2
                        += (tmp_batchUpdateCount_tOracleOutput_2!=-1?tmp_batchUpdateCount_tOracleOutput_2:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_2)  + (" record(s).") );
                    conn_tOracleOutput_2.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_2=0;
                }

 


	tos_count_tOracleOutput_2++;

/**
 * [tOracleOutput_2 main ] stop
 */



	
	/**
	 * [tRowGenerator_2 end ] start
	 */

	

	
	
	currentComponent="tRowGenerator_2";

	

}
globalMap.put("tRowGenerator_2_NB_LINE",nb_line_tRowGenerator_2);
	log.info("tRowGenerator_2 - Generated records count:" + nb_line_tRowGenerator_2 + " .");

 
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_2 - "  + ("Done.") );

ok_Hash.put("tRowGenerator_2", true);
end_Hash.put("tRowGenerator_2", System.currentTimeMillis());




/**
 * [tRowGenerator_2 end ] stop
 */

	
	/**
	 * [tOracleOutput_2 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_2";

	
	



	
            try {
            	if (pstmt_tOracleOutput_2 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_2.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_2){
		        java.sql.SQLException ne_tOracleOutput_2 = e_tOracleOutput_2.getNextException(),sqle_tOracleOutput_2=null;
		    	String errormessage_tOracleOutput_2;
				if (ne_tOracleOutput_2 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_2 = new java.sql.SQLException(e_tOracleOutput_2.getMessage() + "\ncaused by: " + ne_tOracleOutput_2.getMessage(), ne_tOracleOutput_2.getSQLState(), ne_tOracleOutput_2.getErrorCode(), ne_tOracleOutput_2);
					errormessage_tOracleOutput_2 = sqle_tOracleOutput_2.getMessage();
				}else{
					errormessage_tOracleOutput_2 = e_tOracleOutput_2.getMessage();
				}
            	
            log.error("tOracleOutput_2 - "  + (errormessage_tOracleOutput_2) );
                	System.err.println(errormessage_tOracleOutput_2);
            	
        	}
        	if (pstmt_tOracleOutput_2 != null) {
            	tmp_batchUpdateCount_tOracleOutput_2 = pstmt_tOracleOutput_2.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_2
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_2!=-1?tmp_batchUpdateCount_tOracleOutput_2:0);
            }
        if(pstmt_tOracleOutput_2 != null) {
			
				pstmt_tOracleOutput_2.close();
			
        }
		if(commitCounter_tOracleOutput_2 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_2)  + (" record(s).") );
		    conn_tOracleOutput_2.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_2 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_2", true);
   	


	
	nb_line_deleted_tOracleOutput_2=nb_line_deleted_tOracleOutput_2+ deletedCount_tOracleOutput_2;
	nb_line_update_tOracleOutput_2=nb_line_update_tOracleOutput_2 + updatedCount_tOracleOutput_2;
	nb_line_inserted_tOracleOutput_2=nb_line_inserted_tOracleOutput_2 + insertedCount_tOracleOutput_2;
	nb_line_rejected_tOracleOutput_2=nb_line_rejected_tOracleOutput_2 + rejectedCount_tOracleOutput_2;
	
        globalMap.put("tOracleOutput_2_NB_LINE",nb_line_tOracleOutput_2);
        globalMap.put("tOracleOutput_2_NB_LINE_UPDATED",nb_line_update_tOracleOutput_2);
        globalMap.put("tOracleOutput_2_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_2);
        globalMap.put("tOracleOutput_2_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_2);
        globalMap.put("tOracleOutput_2_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_2);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_2)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row3"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_2", true);
end_Hash.put("tOracleOutput_2", System.currentTimeMillis());




/**
 * [tOracleOutput_2 end ] stop
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
	 * [tRowGenerator_2 finally ] start
	 */

	

	
	
	currentComponent="tRowGenerator_2";

	

 



/**
 * [tRowGenerator_2 finally ] stop
 */

	
	/**
	 * [tOracleOutput_2 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_2";

	



	
		if(resourceMap.get("finish_tOracleOutput_2")==null){
			if(resourceMap.get("conn_tOracleOutput_2")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_2 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_2");
					
					
            		
					ctn_tOracleOutput_2.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_2) {
					String errorMessage_tOracleOutput_2 = "failed to close the connection in tOracleOutput_2 :" + sqlEx_tOracleOutput_2.getMessage();
					
            log.error("tOracleOutput_2 - "  + (errorMessage_tOracleOutput_2) );
					System.err.println(errorMessage_tOracleOutput_2);
				}
			}
		}
	

 



/**
 * [tOracleOutput_2 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRowGenerator_2_SUBPROCESS_STATE", 1);
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
        final CombFCP CombFCPClass = new CombFCP();

        int exitCode = CombFCPClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'CombFCP' - Done.");
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
        	log.info("TalendJob: 'CombFCP' - Start.");
    	

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
            java.io.InputStream inContext = CombFCP.class.getClassLoader().getResourceAsStream("madhav/combfcp_0_1/contexts/"+contextStr+".properties");
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
errorCode = null;tOracleInput_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tOracleInput_1) {
globalMap.put("tOracleInput_1_SUBPROCESS_STATE", -1);

e_tOracleInput_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : CombFCP");
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
 *     133625 characters generated by Talend Data Integration 
 *     on the September 27, 2019 2:53:49 PM IST
 ************************************************************************************************/