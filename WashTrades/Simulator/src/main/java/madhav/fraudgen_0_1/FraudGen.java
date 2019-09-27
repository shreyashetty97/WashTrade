
package madhav.fraudgen_0_1;

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
 * Job: FraudGen Purpose: <br>
 * Description:  <br>
 * @author madhav2800@gmail.com
 * @version 6.5.1.20180116_1512
 * @status 
 */
public class FraudGen implements TalendJob {
	static {System.setProperty("TalendJob.log", "FraudGen.log");}
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FraudGen.class);



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
			
			if(TRADE_ID != null){
				
					this.setProperty("TRADE_ID", TRADE_ID.toString());
				
			}
			
			if(BROKER_ID != null){
				
					this.setProperty("BROKER_ID", BROKER_ID.toString());
				
			}
			
			if(SECURITY_TP != null){
				
					this.setProperty("SECURITY_TP", SECURITY_TP.toString());
				
			}
			
			if(COMPANY_ID != null){
				
					this.setProperty("COMPANY_ID", COMPANY_ID.toString());
				
			}
			
		}

public Integer TRADE_ID;
public Integer getTRADE_ID(){
	return this.TRADE_ID;
}
public Integer BROKER_ID;
public Integer getBROKER_ID(){
	return this.BROKER_ID;
}
public String SECURITY_TP;
public String getSECURITY_TP(){
	return this.SECURITY_TP;
}
public Integer COMPANY_ID;
public Integer getCOMPANY_ID(){
	return this.COMPANY_ID;
}
	}
	private ContextProperties context = new ContextProperties();
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "FraudGen";
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
				FraudGen.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(FraudGen.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRowGenerator_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_11_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_12_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_6_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_9_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_10_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_9_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_4_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRowGenerator_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_8_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFlowToIterate_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_8_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tSetGlobalVar_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_8_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_4_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tRowGenerator_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tRowGenerator_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRowGenerator_3_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_3_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_5_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_6_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_9_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_4_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tRowGenerator_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_8_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
		




	

public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

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
    public int compareTo(out1Struct other) {

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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				
			    public Integer PRICE;

				public Integer getPRICE () {
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
						this.PRICE = readInteger(dis);
					
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
					
					// Integer
				
						writeInteger(this.PRICE,dos);
					
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

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				
			    public Integer PRICE;

				public Integer getPRICE () {
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
						this.PRICE = readInteger(dis);
					
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
					
					// Integer
				
						writeInteger(this.PRICE,dos);
					
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



		row1Struct row1 = new row1Struct();
row2Struct row2 = new row2Struct();
out1Struct out1 = new out1Struct();






	
	/**
	 * [tOracleOutput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_2", false);
		start_Hash.put("tOracleOutput_2", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_2";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out1" + iterateId, 0, 0);
					
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
                    log4jParamters_tOracleOutput_2.append("TABLE" + " = " + "\"STAGE2\"");
                log4jParamters_tOracleOutput_2.append(" | ");
                    log4jParamters_tOracleOutput_2.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
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
            tableName_tOracleOutput_2 = ("STAGE2");
        } else {
            tableName_tOracleOutput_2 = dbschema_tOracleOutput_2 + "." + ("STAGE2");
        }
                                String tableNameForSearch_tOracleOutput_2= "" + ((String)"STAGE2") + "";
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

                                if(whetherExist_tOracleOutput_2) {
                                    java.sql.Statement stmtDrop_tOracleOutput_2 = conn_tOracleOutput_2.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_2)  + ("'.") );
                                    stmtDrop_tOracleOutput_2.execute("DROP TABLE " + tableName_tOracleOutput_2 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_2)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_2.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_2 = conn_tOracleOutput_2.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_2)  + ("'.") );
                                    stmtCreate_tOracleOutput_2.execute("CREATE TABLE " + tableName_tOracleOutput_2 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_2)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_2.close();
                String insert_tOracleOutput_2 = "INSERT INTO " + tableName_tOracleOutput_2 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_2 = conn_tOracleOutput_2.prepareStatement(insert_tOracleOutput_2);





 



/**
 * [tOracleOutput_2 begin ] stop
 */



	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_1", false);
		start_Hash.put("tMap_1", System.currentTimeMillis());
		
	
	currentComponent="tMap_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row2" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_1 = new StringBuilder();
            log4jParamters_tMap_1.append("Parameters:");
                    log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_1.append(" | ");
                    log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_1.append(" | ");
                    log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_1.append(" | ");
                    log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + (log4jParamters_tMap_1) );
    		}
    	}
    	
        new BytesLimit65535_tMap_1().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row2_tMap_1 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_1 = 0;
				
out1Struct out1_tmp = new out1Struct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tOracleOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_1", false);
		start_Hash.put("tOracleOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row1" + iterateId, 0, 0);
					
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
                    log4jParamters_tOracleOutput_1.append("TABLE" + " = " + "\"STAGE1\"");
                log4jParamters_tOracleOutput_1.append(" | ");
                    log4jParamters_tOracleOutput_1.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
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
            tableName_tOracleOutput_1 = ("STAGE1");
        } else {
            tableName_tOracleOutput_1 = dbschema_tOracleOutput_1 + "." + ("STAGE1");
        }
                                String tableNameForSearch_tOracleOutput_1= "" + ((String)"STAGE1") + "";
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

                                if(whetherExist_tOracleOutput_1) {
                                    java.sql.Statement stmtDrop_tOracleOutput_1 = conn_tOracleOutput_1.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_1)  + ("'.") );
                                    stmtDrop_tOracleOutput_1.execute("DROP TABLE " + tableName_tOracleOutput_1 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_1)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_1.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_1 = conn_tOracleOutput_1.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_1)  + ("'.") );
                                    stmtCreate_tOracleOutput_1.execute("CREATE TABLE " + tableName_tOracleOutput_1 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE INT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_1)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_1.close();
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
int nb_max_row_tRowGenerator_1 = Numeric.random(5,8);


class tRowGenerator_1Randomizer {
	public Integer getRandomTRADER_ID() {
		 
		return ((Integer)globalMap.get("row12.TRADER_ID")) ;
		
	}
	public Integer getRandomBROKER_ID() {
		 
		return ((Integer)globalMap.get("row12.BROKER_ID")) ;
		
	}
	public java.util.Date getRandomTIMESTAMP() {
		
		return TalendDate.getCurrentDate();
		
	}
	public Integer getRandomCOMPANY_ID() {
		 
		return ((Integer)globalMap.get("row12.COMPANY_ID")) ;
		
	}
	public String getRandomSECURITY_TP() {
		 
		return ((String)globalMap.get("row12.SECURITY_TP")) ;
		
	}
	public String getRandomTRADE_TP() {
		 
		return "sell" ;
		
	}
	public Integer getRandomQUANTITY() {
		
		return Numeric.random(1 ,2000 );
		
	}
	public Integer getRandomPRICE() {
		
		return Numeric.random(400 ,440 );
		
	}
}
	tRowGenerator_1Randomizer randtRowGenerator_1 = new tRowGenerator_1Randomizer();
	
    	log.info("tRowGenerator_1 - Generating records.");
	for (int itRowGenerator_1=0; itRowGenerator_1<nb_max_row_tRowGenerator_1 ;itRowGenerator_1++) {
		row1.TRADER_ID = randtRowGenerator_1.getRandomTRADER_ID();
		row1.BROKER_ID = randtRowGenerator_1.getRandomBROKER_ID();
		row1.TIMESTAMP = randtRowGenerator_1.getRandomTIMESTAMP();
		row1.COMPANY_ID = randtRowGenerator_1.getRandomCOMPANY_ID();
		row1.SECURITY_TP = randtRowGenerator_1.getRandomSECURITY_TP();
		row1.TRADE_TP = randtRowGenerator_1.getRandomTRADE_TP();
		row1.QUANTITY = randtRowGenerator_1.getRandomQUANTITY();
		row1.PRICE = randtRowGenerator_1.getRandomPRICE();
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

	

			//row1
			//row1


			
				if(execStat){
					runStat.updateStatOnConnection("row1"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row1 - " + (row1==null? "": row1.toLogString()));
    			}
    		



            row2 = null;
        whetherReject_tOracleOutput_1 = false;
                        if(row1.TRADER_ID == null) {
pstmt_tOracleOutput_1.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(1, row1.TRADER_ID);
}

                        if(row1.BROKER_ID == null) {
pstmt_tOracleOutput_1.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(2, row1.BROKER_ID);
}

                        if(row1.TIMESTAMP != null) {
pstmt_tOracleOutput_1.setObject(3, new java.sql.Timestamp(row1.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_1.setNull(3, java.sql.Types.DATE);
}

                        if(row1.COMPANY_ID == null) {
pstmt_tOracleOutput_1.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(4, row1.COMPANY_ID);
}

                        if(row1.SECURITY_TP == null) {
pstmt_tOracleOutput_1.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_1.setString(5, row1.SECURITY_TP);
}

                        if(row1.TRADE_TP == null) {
pstmt_tOracleOutput_1.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_1.setString(6, row1.TRADE_TP);
}

                        if(row1.QUANTITY == null) {
pstmt_tOracleOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(7, row1.QUANTITY);
}

                        if(row1.PRICE == null) {
pstmt_tOracleOutput_1.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_1.setInt(8, row1.PRICE);
}

                pstmt_tOracleOutput_1.addBatch();
                nb_line_tOracleOutput_1++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_1)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_1++;
            if(!whetherReject_tOracleOutput_1) {
                            row2 = new row2Struct();
                                row2.TRADER_ID = row1.TRADER_ID;
                                row2.BROKER_ID = row1.BROKER_ID;
                                row2.TIMESTAMP = row1.TIMESTAMP;
                                row2.COMPANY_ID = row1.COMPANY_ID;
                                row2.SECURITY_TP = row1.SECURITY_TP;
                                row2.TRADE_TP = row1.TRADE_TP;
                                row2.QUANTITY = row1.QUANTITY;
                                row2.PRICE = row1.PRICE;
            }
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
// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

			//row2
			//row2


			
				if(execStat){
					runStat.updateStatOnConnection("row2"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row2 - " + (row2==null? "": row2.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_1 = false;
		  boolean mainRowRejected_tMap_1 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

out1 = null;


// # Output table : 'out1'
count_out1_tMap_1++;

out1_tmp.TRADER_ID = row2.TRADER_ID;
out1_tmp.BROKER_ID = row2.BROKER_ID;
out1_tmp.TIMESTAMP = row2.TIMESTAMP;
out1_tmp.COMPANY_ID = row2.COMPANY_ID;
out1_tmp.SECURITY_TP = row2.SECURITY_TP;
out1_tmp.TRADE_TP = row2.TRADE_TP;
out1_tmp.QUANTITY = row2.QUANTITY*50;
out1_tmp.PRICE = (float) (row2.PRICE*25)/100;
out1 = out1_tmp;
log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1 + " of the output table 'out1'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_1 = false;










 


	tos_count_tMap_1++;

/**
 * [tMap_1 main ] stop
 */
// Start of branch "out1"
if(out1 != null) { 



	
	/**
	 * [tOracleOutput_2 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_2";

	

			//out1
			//out1


			
				if(execStat){
					runStat.updateStatOnConnection("out1"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out1 - " + (out1==null? "": out1.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_2 = false;
                        if(out1.TRADER_ID == null) {
pstmt_tOracleOutput_2.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(1, out1.TRADER_ID);
}

                        if(out1.BROKER_ID == null) {
pstmt_tOracleOutput_2.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(2, out1.BROKER_ID);
}

                        if(out1.TIMESTAMP != null) {
pstmt_tOracleOutput_2.setObject(3, new java.sql.Timestamp(out1.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_2.setNull(3, java.sql.Types.DATE);
}

                        if(out1.COMPANY_ID == null) {
pstmt_tOracleOutput_2.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(4, out1.COMPANY_ID);
}

                        if(out1.SECURITY_TP == null) {
pstmt_tOracleOutput_2.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(5, out1.SECURITY_TP);
}

                        if(out1.TRADE_TP == null) {
pstmt_tOracleOutput_2.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(6, out1.TRADE_TP);
}

                        if(out1.QUANTITY == null) {
pstmt_tOracleOutput_2.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(7, out1.QUANTITY);
}

                        if(out1.PRICE == null) {
pstmt_tOracleOutput_2.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_2.setFloat(8, out1.PRICE);
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

} // End of branch "out1"





} // End of branch "row2"







	
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
			 		runStat.updateStatOnConnection("row1"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_1 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_1", true);
end_Hash.put("tOracleOutput_1", System.currentTimeMillis());




/**
 * [tOracleOutput_1 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row2"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_1 - "  + ("Done.") );

ok_Hash.put("tMap_1", true);
end_Hash.put("tMap_1", System.currentTimeMillis());




/**
 * [tMap_1 end ] stop
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
			 		runStat.updateStatOnConnection("out1"+iterateId,2, 0); 
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

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRowGenerator_1:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
								} 
							
							tRowGenerator_3Process(globalMap); 
						



	
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

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
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
		

		globalMap.put("tRowGenerator_1_SUBPROCESS_STATE", 1);
	}
	


public static class copyOfout1Struct implements routines.system.IPersistableRow<copyOfout1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

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
    public int compareTo(copyOfout1Struct other) {

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

public static class row14Struct implements routines.system.IPersistableRow<row14Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				
			    public Integer PRICE;

				public Integer getPRICE () {
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
						this.PRICE = readInteger(dis);
					
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
					
					// Integer
				
						writeInteger(this.PRICE,dos);
					
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
    public int compareTo(row14Struct other) {

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

public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				
			    public Integer PRICE;

				public Integer getPRICE () {
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
						this.PRICE = readInteger(dis);
					
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
					
					// Integer
				
						writeInteger(this.PRICE,dos);
					
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
    public int compareTo(row13Struct other) {

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
public void tRowGenerator_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tRowGenerator_3_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row13Struct row13 = new row13Struct();
row14Struct row14 = new row14Struct();
copyOfout1Struct copyOfout1 = new copyOfout1Struct();






	
	/**
	 * [tOracleOutput_12 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_12", false);
		start_Hash.put("tOracleOutput_12", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_12";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("copyOfout1" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_12 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_12{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_12 = new StringBuilder();
            log4jParamters_tOracleOutput_12.append("Parameters:");
                    log4jParamters_tOracleOutput_12.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("TABLE" + " = " + "\"STAGE2\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_12.append(" | ");
                    log4jParamters_tOracleOutput_12.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_12.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + (log4jParamters_tOracleOutput_12) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_12().limitLog4jByte();






    int nb_line_tOracleOutput_12 = 0;
    int nb_line_update_tOracleOutput_12 = 0;
    int nb_line_inserted_tOracleOutput_12 = 0;
    int nb_line_deleted_tOracleOutput_12 = 0;
    int nb_line_rejected_tOracleOutput_12 = 0;

    int tmp_batchUpdateCount_tOracleOutput_12 = 0;

    int deletedCount_tOracleOutput_12=0;
    int updatedCount_tOracleOutput_12=0;
    int insertedCount_tOracleOutput_12=0;
    int rejectedCount_tOracleOutput_12=0;

    boolean whetherReject_tOracleOutput_12 = false;

    java.sql.Connection conn_tOracleOutput_12 = null;

    //optional table
    String dbschema_tOracleOutput_12 = null;
    String tableName_tOracleOutput_12 = null;
                    String driverClass_tOracleOutput_12 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_12)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_12);
                String url_tOracleOutput_12 = null;
                    url_tOracleOutput_12 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_12 = "hr";
 
	final String decryptedPassword_tOracleOutput_12 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_12 = decryptedPassword_tOracleOutput_12;
                dbschema_tOracleOutput_12 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection attempts to '")  + (url_tOracleOutput_12)  + ("' with the username '")  + (dbUser_tOracleOutput_12)  + ("'.") );

                    conn_tOracleOutput_12 = java.sql.DriverManager.getConnection(url_tOracleOutput_12, dbUser_tOracleOutput_12, dbPwd_tOracleOutput_12);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection to '")  + (url_tOracleOutput_12)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_12", conn_tOracleOutput_12);
            conn_tOracleOutput_12.setAutoCommit(false);
            int commitEvery_tOracleOutput_12 = 10000;
            int commitCounter_tOracleOutput_12 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_12.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_12 = 10000;
        int batchSizeCounter_tOracleOutput_12=0;
        int count_tOracleOutput_12=0;

        if(dbschema_tOracleOutput_12 == null || dbschema_tOracleOutput_12.trim().length() == 0) {
            tableName_tOracleOutput_12 = ("STAGE2");
        } else {
            tableName_tOracleOutput_12 = dbschema_tOracleOutput_12 + "." + ("STAGE2");
        }
                                String tableNameForSearch_tOracleOutput_12= "" + ((String)"STAGE2") + "";
String dbschemaForSearch_tOracleOutput_12= null;
if(dbschema_tOracleOutput_12== null || dbschema_tOracleOutput_12.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_12= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_12= dbschema_tOracleOutput_12.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_12 = conn_tOracleOutput_12.getMetaData();
                                if(tableNameForSearch_tOracleOutput_12.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_12 = tableNameForSearch_tOracleOutput_12.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_12 = tableNameForSearch_tOracleOutput_12.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_12 = dbMetaData_tOracleOutput_12.getTables(null, dbschemaForSearch_tOracleOutput_12, tableNameForSearch_tOracleOutput_12, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_12 = false;
                                if(rsTable_tOracleOutput_12.next()) {
                                    whetherExist_tOracleOutput_12 = true;
                                }
                                rsTable_tOracleOutput_12.close();

                                if(!whetherExist_tOracleOutput_12) {
                                    java.sql.Statement stmtCreate_tOracleOutput_12 = conn_tOracleOutput_12.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_12)  + ("'.") );
                                        stmtCreate_tOracleOutput_12.execute("CREATE TABLE " + tableName_tOracleOutput_12 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_12)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_12.close();
                                }
                String insert_tOracleOutput_12 = "INSERT INTO " + tableName_tOracleOutput_12 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_12 = conn_tOracleOutput_12.prepareStatement(insert_tOracleOutput_12);





 



/**
 * [tOracleOutput_12 begin ] stop
 */



	
	/**
	 * [tMap_6 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_6", false);
		start_Hash.put("tMap_6", System.currentTimeMillis());
		
	
	currentComponent="tMap_6";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row14" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_6 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_6 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_6{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_6 = new StringBuilder();
            log4jParamters_tMap_6.append("Parameters:");
                    log4jParamters_tMap_6.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_6.append(" | ");
                    log4jParamters_tMap_6.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_6.append(" | ");
                    log4jParamters_tMap_6.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_6.append(" | ");
                    log4jParamters_tMap_6.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_6.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_6 - "  + (log4jParamters_tMap_6) );
    		}
    	}
    	
        new BytesLimit65535_tMap_6().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row14_tMap_6 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_6__Struct  {
}
Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_copyOfout1_tMap_6 = 0;
				
copyOfout1Struct copyOfout1_tmp = new copyOfout1Struct();
// ###############################

        
        



        









 



/**
 * [tMap_6 begin ] stop
 */



	
	/**
	 * [tOracleOutput_11 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_11", false);
		start_Hash.put("tOracleOutput_11", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_11";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row13" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_11 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_11{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_11 = new StringBuilder();
            log4jParamters_tOracleOutput_11.append("Parameters:");
                    log4jParamters_tOracleOutput_11.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("TABLE" + " = " + "\"STAGE1\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_11.append(" | ");
                    log4jParamters_tOracleOutput_11.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_11.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + (log4jParamters_tOracleOutput_11) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_11().limitLog4jByte();






    int nb_line_tOracleOutput_11 = 0;
    int nb_line_update_tOracleOutput_11 = 0;
    int nb_line_inserted_tOracleOutput_11 = 0;
    int nb_line_deleted_tOracleOutput_11 = 0;
    int nb_line_rejected_tOracleOutput_11 = 0;

    int tmp_batchUpdateCount_tOracleOutput_11 = 0;

    int deletedCount_tOracleOutput_11=0;
    int updatedCount_tOracleOutput_11=0;
    int insertedCount_tOracleOutput_11=0;
    int rejectedCount_tOracleOutput_11=0;

    boolean whetherReject_tOracleOutput_11 = false;

    java.sql.Connection conn_tOracleOutput_11 = null;

    //optional table
    String dbschema_tOracleOutput_11 = null;
    String tableName_tOracleOutput_11 = null;
                    String driverClass_tOracleOutput_11 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_11)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_11);
                String url_tOracleOutput_11 = null;
                    url_tOracleOutput_11 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_11 = "hr";
 
	final String decryptedPassword_tOracleOutput_11 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_11 = decryptedPassword_tOracleOutput_11;
                dbschema_tOracleOutput_11 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection attempts to '")  + (url_tOracleOutput_11)  + ("' with the username '")  + (dbUser_tOracleOutput_11)  + ("'.") );

                    conn_tOracleOutput_11 = java.sql.DriverManager.getConnection(url_tOracleOutput_11, dbUser_tOracleOutput_11, dbPwd_tOracleOutput_11);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection to '")  + (url_tOracleOutput_11)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_11", conn_tOracleOutput_11);
            conn_tOracleOutput_11.setAutoCommit(false);
            int commitEvery_tOracleOutput_11 = 10000;
            int commitCounter_tOracleOutput_11 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_11.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_11 = 10000;
        int batchSizeCounter_tOracleOutput_11=0;
        int count_tOracleOutput_11=0;

        if(dbschema_tOracleOutput_11 == null || dbschema_tOracleOutput_11.trim().length() == 0) {
            tableName_tOracleOutput_11 = ("STAGE1");
        } else {
            tableName_tOracleOutput_11 = dbschema_tOracleOutput_11 + "." + ("STAGE1");
        }
                                String tableNameForSearch_tOracleOutput_11= "" + ((String)"STAGE1") + "";
String dbschemaForSearch_tOracleOutput_11= null;
if(dbschema_tOracleOutput_11== null || dbschema_tOracleOutput_11.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_11= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_11= dbschema_tOracleOutput_11.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_11 = conn_tOracleOutput_11.getMetaData();
                                if(tableNameForSearch_tOracleOutput_11.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_11 = tableNameForSearch_tOracleOutput_11.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_11 = tableNameForSearch_tOracleOutput_11.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_11 = dbMetaData_tOracleOutput_11.getTables(null, dbschemaForSearch_tOracleOutput_11, tableNameForSearch_tOracleOutput_11, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_11 = false;
                                if(rsTable_tOracleOutput_11.next()) {
                                    whetherExist_tOracleOutput_11 = true;
                                }
                                rsTable_tOracleOutput_11.close();

                                if(!whetherExist_tOracleOutput_11) {
                                    java.sql.Statement stmtCreate_tOracleOutput_11 = conn_tOracleOutput_11.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_11)  + ("'.") );
                                        stmtCreate_tOracleOutput_11.execute("CREATE TABLE " + tableName_tOracleOutput_11 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE INT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_11)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_11.close();
                                }
                String insert_tOracleOutput_11 = "INSERT INTO " + tableName_tOracleOutput_11 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_11 = conn_tOracleOutput_11.prepareStatement(insert_tOracleOutput_11);





 



/**
 * [tOracleOutput_11 begin ] stop
 */



	
	/**
	 * [tRowGenerator_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tRowGenerator_3", false);
		start_Hash.put("tRowGenerator_3", System.currentTimeMillis());
		
	
	currentComponent="tRowGenerator_3";

	
		int tos_count_tRowGenerator_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_3 - "  + ("Start to work.") );
    	class BytesLimit65535_tRowGenerator_3{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tRowGenerator_3 = new StringBuilder();
            log4jParamters_tRowGenerator_3.append("Parameters:");
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_3 - "  + (log4jParamters_tRowGenerator_3) );
    		}
    	}
    	
        new BytesLimit65535_tRowGenerator_3().limitLog4jByte();


int nb_line_tRowGenerator_3 = 0;
int nb_max_row_tRowGenerator_3 = Numeric.random(5,8);


class tRowGenerator_3Randomizer {
	public Integer getRandomTRADER_ID() {
		 
		return ((Integer)globalMap.get("row12.TRADER_ID")) ;
		
	}
	public Integer getRandomBROKER_ID() {
		 
		return ((Integer)globalMap.get("row12.BROKER_ID")) ;
		
	}
	public java.util.Date getRandomTIMESTAMP() {
		
		return TalendDate.getCurrentDate();
		
	}
	public Integer getRandomCOMPANY_ID() {
		 
		return ((Integer)globalMap.get("row12.COMPANY_ID")) ;
		
	}
	public String getRandomSECURITY_TP() {
		 
		return ((String)globalMap.get("row12.SECURITY_TP")) ;
		
	}
	public String getRandomTRADE_TP() {
		 
		return "buy" ;
		
	}
	public Integer getRandomQUANTITY() {
		
		return Numeric.random(1 ,2000 );
		
	}
	public Integer getRandomPRICE() {
		
		return Numeric.random(400 ,440 );
		
	}
}
	tRowGenerator_3Randomizer randtRowGenerator_3 = new tRowGenerator_3Randomizer();
	
    	log.info("tRowGenerator_3 - Generating records.");
	for (int itRowGenerator_3=0; itRowGenerator_3<nb_max_row_tRowGenerator_3 ;itRowGenerator_3++) {
		row13.TRADER_ID = randtRowGenerator_3.getRandomTRADER_ID();
		row13.BROKER_ID = randtRowGenerator_3.getRandomBROKER_ID();
		row13.TIMESTAMP = randtRowGenerator_3.getRandomTIMESTAMP();
		row13.COMPANY_ID = randtRowGenerator_3.getRandomCOMPANY_ID();
		row13.SECURITY_TP = randtRowGenerator_3.getRandomSECURITY_TP();
		row13.TRADE_TP = randtRowGenerator_3.getRandomTRADE_TP();
		row13.QUANTITY = randtRowGenerator_3.getRandomQUANTITY();
		row13.PRICE = randtRowGenerator_3.getRandomPRICE();
		nb_line_tRowGenerator_3++;
		
			log.debug("tRowGenerator_3 - Retrieving the record " + nb_line_tRowGenerator_3 + ".");
		

 



/**
 * [tRowGenerator_3 begin ] stop
 */
	
	/**
	 * [tRowGenerator_3 main ] start
	 */

	

	
	
	currentComponent="tRowGenerator_3";

	

 


	tos_count_tRowGenerator_3++;

/**
 * [tRowGenerator_3 main ] stop
 */

	
	/**
	 * [tOracleOutput_11 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_11";

	

			//row13
			//row13


			
				if(execStat){
					runStat.updateStatOnConnection("row13"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row13 - " + (row13==null? "": row13.toLogString()));
    			}
    		



            row14 = null;
        whetherReject_tOracleOutput_11 = false;
                        if(row13.TRADER_ID == null) {
pstmt_tOracleOutput_11.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_11.setInt(1, row13.TRADER_ID);
}

                        if(row13.BROKER_ID == null) {
pstmt_tOracleOutput_11.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_11.setInt(2, row13.BROKER_ID);
}

                        if(row13.TIMESTAMP != null) {
pstmt_tOracleOutput_11.setObject(3, new java.sql.Timestamp(row13.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_11.setNull(3, java.sql.Types.DATE);
}

                        if(row13.COMPANY_ID == null) {
pstmt_tOracleOutput_11.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_11.setInt(4, row13.COMPANY_ID);
}

                        if(row13.SECURITY_TP == null) {
pstmt_tOracleOutput_11.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_11.setString(5, row13.SECURITY_TP);
}

                        if(row13.TRADE_TP == null) {
pstmt_tOracleOutput_11.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_11.setString(6, row13.TRADE_TP);
}

                        if(row13.QUANTITY == null) {
pstmt_tOracleOutput_11.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_11.setInt(7, row13.QUANTITY);
}

                        if(row13.PRICE == null) {
pstmt_tOracleOutput_11.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_11.setInt(8, row13.PRICE);
}

                pstmt_tOracleOutput_11.addBatch();
                nb_line_tOracleOutput_11++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_11)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_11++;
            if(!whetherReject_tOracleOutput_11) {
                            row14 = new row14Struct();
                                row14.TRADER_ID = row13.TRADER_ID;
                                row14.BROKER_ID = row13.BROKER_ID;
                                row14.TIMESTAMP = row13.TIMESTAMP;
                                row14.COMPANY_ID = row13.COMPANY_ID;
                                row14.SECURITY_TP = row13.SECURITY_TP;
                                row14.TRADE_TP = row13.TRADE_TP;
                                row14.QUANTITY = row13.QUANTITY;
                                row14.PRICE = row13.PRICE;
            }
            if (batchSize_tOracleOutput_11 > 0 &&  batchSize_tOracleOutput_11 <= batchSizeCounter_tOracleOutput_11) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_11.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_11){
			        java.sql.SQLException ne_tOracleOutput_11 = e_tOracleOutput_11.getNextException(),sqle_tOracleOutput_11=null;
			    	String errormessage_tOracleOutput_11;
					if (ne_tOracleOutput_11 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_11 = new java.sql.SQLException(e_tOracleOutput_11.getMessage() + "\ncaused by: " + ne_tOracleOutput_11.getMessage(), ne_tOracleOutput_11.getSQLState(), ne_tOracleOutput_11.getErrorCode(), ne_tOracleOutput_11);
						errormessage_tOracleOutput_11 = sqle_tOracleOutput_11.getMessage();
					}else{
						errormessage_tOracleOutput_11 = e_tOracleOutput_11.getMessage();
					}
	            	
            log.error("tOracleOutput_11 - "  + (errormessage_tOracleOutput_11) );
	                	System.err.println(errormessage_tOracleOutput_11);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_11 = pstmt_tOracleOutput_11.getUpdateCount();
                    insertedCount_tOracleOutput_11
                += (tmp_batchUpdateCount_tOracleOutput_11!=-1?tmp_batchUpdateCount_tOracleOutput_11:0);
                batchSizeCounter_tOracleOutput_11 = 0;
            }
                commitCounter_tOracleOutput_11++;
                if(commitEvery_tOracleOutput_11 <= commitCounter_tOracleOutput_11) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_11.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_11){
					        java.sql.SQLException ne_tOracleOutput_11 = e_tOracleOutput_11.getNextException(),sqle_tOracleOutput_11=null;
					    	String errormessage_tOracleOutput_11;
							if (ne_tOracleOutput_11 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_11 = new java.sql.SQLException(e_tOracleOutput_11.getMessage() + "\ncaused by: " + ne_tOracleOutput_11.getMessage(), ne_tOracleOutput_11.getSQLState(), ne_tOracleOutput_11.getErrorCode(), ne_tOracleOutput_11);
								errormessage_tOracleOutput_11 = sqle_tOracleOutput_11.getMessage();
							}else{
								errormessage_tOracleOutput_11 = e_tOracleOutput_11.getMessage();
							}
			            	
            log.error("tOracleOutput_11 - "  + (errormessage_tOracleOutput_11) );
			                	System.err.println(errormessage_tOracleOutput_11);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_11 = pstmt_tOracleOutput_11.getUpdateCount();
                            insertedCount_tOracleOutput_11
                        += (tmp_batchUpdateCount_tOracleOutput_11!=-1?tmp_batchUpdateCount_tOracleOutput_11:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_11)  + (" record(s).") );
                    conn_tOracleOutput_11.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_11=0;
                }

 


	tos_count_tOracleOutput_11++;

/**
 * [tOracleOutput_11 main ] stop
 */
// Start of branch "row14"
if(row14 != null) { 



	
	/**
	 * [tMap_6 main ] start
	 */

	

	
	
	currentComponent="tMap_6";

	

			//row14
			//row14


			
				if(execStat){
					runStat.updateStatOnConnection("row14"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row14 - " + (row14==null? "": row14.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_6 = false;
		  boolean mainRowRejected_tMap_6 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
        // ###############################
        // # Output tables

copyOfout1 = null;


// # Output table : 'copyOfout1'
count_copyOfout1_tMap_6++;

copyOfout1_tmp.TRADER_ID = row14.TRADER_ID;
copyOfout1_tmp.BROKER_ID = row14.BROKER_ID;
copyOfout1_tmp.TIMESTAMP = row14.TIMESTAMP;
copyOfout1_tmp.COMPANY_ID = row14.COMPANY_ID;
copyOfout1_tmp.SECURITY_TP = row14.SECURITY_TP;
copyOfout1_tmp.TRADE_TP = row14.TRADE_TP;
copyOfout1_tmp.QUANTITY = row14.QUANTITY*50;
copyOfout1_tmp.PRICE = (float) (row14.PRICE*25)/100;
copyOfout1 = copyOfout1_tmp;
log.debug("tMap_6 - Outputting the record " + count_copyOfout1_tMap_6 + " of the output table 'copyOfout1'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_6 = false;










 


	tos_count_tMap_6++;

/**
 * [tMap_6 main ] stop
 */
// Start of branch "copyOfout1"
if(copyOfout1 != null) { 



	
	/**
	 * [tOracleOutput_12 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_12";

	

			//copyOfout1
			//copyOfout1


			
				if(execStat){
					runStat.updateStatOnConnection("copyOfout1"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("copyOfout1 - " + (copyOfout1==null? "": copyOfout1.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_12 = false;
                        if(copyOfout1.TRADER_ID == null) {
pstmt_tOracleOutput_12.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_12.setInt(1, copyOfout1.TRADER_ID);
}

                        if(copyOfout1.BROKER_ID == null) {
pstmt_tOracleOutput_12.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_12.setInt(2, copyOfout1.BROKER_ID);
}

                        if(copyOfout1.TIMESTAMP != null) {
pstmt_tOracleOutput_12.setObject(3, new java.sql.Timestamp(copyOfout1.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_12.setNull(3, java.sql.Types.DATE);
}

                        if(copyOfout1.COMPANY_ID == null) {
pstmt_tOracleOutput_12.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_12.setInt(4, copyOfout1.COMPANY_ID);
}

                        if(copyOfout1.SECURITY_TP == null) {
pstmt_tOracleOutput_12.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_12.setString(5, copyOfout1.SECURITY_TP);
}

                        if(copyOfout1.TRADE_TP == null) {
pstmt_tOracleOutput_12.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_12.setString(6, copyOfout1.TRADE_TP);
}

                        if(copyOfout1.QUANTITY == null) {
pstmt_tOracleOutput_12.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_12.setInt(7, copyOfout1.QUANTITY);
}

                        if(copyOfout1.PRICE == null) {
pstmt_tOracleOutput_12.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_12.setFloat(8, copyOfout1.PRICE);
}

                pstmt_tOracleOutput_12.addBatch();
                nb_line_tOracleOutput_12++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_12)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_12++;
            if (batchSize_tOracleOutput_12 > 0 &&  batchSize_tOracleOutput_12 <= batchSizeCounter_tOracleOutput_12) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_12.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_12){
			        java.sql.SQLException ne_tOracleOutput_12 = e_tOracleOutput_12.getNextException(),sqle_tOracleOutput_12=null;
			    	String errormessage_tOracleOutput_12;
					if (ne_tOracleOutput_12 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_12 = new java.sql.SQLException(e_tOracleOutput_12.getMessage() + "\ncaused by: " + ne_tOracleOutput_12.getMessage(), ne_tOracleOutput_12.getSQLState(), ne_tOracleOutput_12.getErrorCode(), ne_tOracleOutput_12);
						errormessage_tOracleOutput_12 = sqle_tOracleOutput_12.getMessage();
					}else{
						errormessage_tOracleOutput_12 = e_tOracleOutput_12.getMessage();
					}
	            	
            log.error("tOracleOutput_12 - "  + (errormessage_tOracleOutput_12) );
	                	System.err.println(errormessage_tOracleOutput_12);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_12 = pstmt_tOracleOutput_12.getUpdateCount();
                    insertedCount_tOracleOutput_12
                += (tmp_batchUpdateCount_tOracleOutput_12!=-1?tmp_batchUpdateCount_tOracleOutput_12:0);
                batchSizeCounter_tOracleOutput_12 = 0;
            }
                commitCounter_tOracleOutput_12++;
                if(commitEvery_tOracleOutput_12 <= commitCounter_tOracleOutput_12) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_12.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_12){
					        java.sql.SQLException ne_tOracleOutput_12 = e_tOracleOutput_12.getNextException(),sqle_tOracleOutput_12=null;
					    	String errormessage_tOracleOutput_12;
							if (ne_tOracleOutput_12 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_12 = new java.sql.SQLException(e_tOracleOutput_12.getMessage() + "\ncaused by: " + ne_tOracleOutput_12.getMessage(), ne_tOracleOutput_12.getSQLState(), ne_tOracleOutput_12.getErrorCode(), ne_tOracleOutput_12);
								errormessage_tOracleOutput_12 = sqle_tOracleOutput_12.getMessage();
							}else{
								errormessage_tOracleOutput_12 = e_tOracleOutput_12.getMessage();
							}
			            	
            log.error("tOracleOutput_12 - "  + (errormessage_tOracleOutput_12) );
			                	System.err.println(errormessage_tOracleOutput_12);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_12 = pstmt_tOracleOutput_12.getUpdateCount();
                            insertedCount_tOracleOutput_12
                        += (tmp_batchUpdateCount_tOracleOutput_12!=-1?tmp_batchUpdateCount_tOracleOutput_12:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_12)  + (" record(s).") );
                    conn_tOracleOutput_12.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_12=0;
                }

 


	tos_count_tOracleOutput_12++;

/**
 * [tOracleOutput_12 main ] stop
 */

} // End of branch "copyOfout1"





} // End of branch "row14"







	
	/**
	 * [tRowGenerator_3 end ] start
	 */

	

	
	
	currentComponent="tRowGenerator_3";

	

}
globalMap.put("tRowGenerator_3_NB_LINE",nb_line_tRowGenerator_3);
	log.info("tRowGenerator_3 - Generated records count:" + nb_line_tRowGenerator_3 + " .");

 
                if(log.isDebugEnabled())
            log.debug("tRowGenerator_3 - "  + ("Done.") );

ok_Hash.put("tRowGenerator_3", true);
end_Hash.put("tRowGenerator_3", System.currentTimeMillis());




/**
 * [tRowGenerator_3 end ] stop
 */

	
	/**
	 * [tOracleOutput_11 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_11";

	
	



	
            try {
            	if (pstmt_tOracleOutput_11 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_11.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_11){
		        java.sql.SQLException ne_tOracleOutput_11 = e_tOracleOutput_11.getNextException(),sqle_tOracleOutput_11=null;
		    	String errormessage_tOracleOutput_11;
				if (ne_tOracleOutput_11 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_11 = new java.sql.SQLException(e_tOracleOutput_11.getMessage() + "\ncaused by: " + ne_tOracleOutput_11.getMessage(), ne_tOracleOutput_11.getSQLState(), ne_tOracleOutput_11.getErrorCode(), ne_tOracleOutput_11);
					errormessage_tOracleOutput_11 = sqle_tOracleOutput_11.getMessage();
				}else{
					errormessage_tOracleOutput_11 = e_tOracleOutput_11.getMessage();
				}
            	
            log.error("tOracleOutput_11 - "  + (errormessage_tOracleOutput_11) );
                	System.err.println(errormessage_tOracleOutput_11);
            	
        	}
        	if (pstmt_tOracleOutput_11 != null) {
            	tmp_batchUpdateCount_tOracleOutput_11 = pstmt_tOracleOutput_11.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_11
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_11!=-1?tmp_batchUpdateCount_tOracleOutput_11:0);
            }
        if(pstmt_tOracleOutput_11 != null) {
			
				pstmt_tOracleOutput_11.close();
			
        }
		if(commitCounter_tOracleOutput_11 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_11)  + (" record(s).") );
		    conn_tOracleOutput_11.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_11 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_11", true);
   	


	
	nb_line_deleted_tOracleOutput_11=nb_line_deleted_tOracleOutput_11+ deletedCount_tOracleOutput_11;
	nb_line_update_tOracleOutput_11=nb_line_update_tOracleOutput_11 + updatedCount_tOracleOutput_11;
	nb_line_inserted_tOracleOutput_11=nb_line_inserted_tOracleOutput_11 + insertedCount_tOracleOutput_11;
	nb_line_rejected_tOracleOutput_11=nb_line_rejected_tOracleOutput_11 + rejectedCount_tOracleOutput_11;
	
        globalMap.put("tOracleOutput_11_NB_LINE",nb_line_tOracleOutput_11);
        globalMap.put("tOracleOutput_11_NB_LINE_UPDATED",nb_line_update_tOracleOutput_11);
        globalMap.put("tOracleOutput_11_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_11);
        globalMap.put("tOracleOutput_11_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_11);
        globalMap.put("tOracleOutput_11_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_11);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_11)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row13"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_11", true);
end_Hash.put("tOracleOutput_11", System.currentTimeMillis());




/**
 * [tOracleOutput_11 end ] stop
 */

	
	/**
	 * [tMap_6 end ] start
	 */

	

	
	
	currentComponent="tMap_6";

	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_6 - Written records count in the table 'copyOfout1': " + count_copyOfout1_tMap_6 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row14"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_6 - "  + ("Done.") );

ok_Hash.put("tMap_6", true);
end_Hash.put("tMap_6", System.currentTimeMillis());




/**
 * [tMap_6 end ] stop
 */

	
	/**
	 * [tOracleOutput_12 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_12";

	
	



	
            try {
            	if (pstmt_tOracleOutput_12 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_12.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_12){
		        java.sql.SQLException ne_tOracleOutput_12 = e_tOracleOutput_12.getNextException(),sqle_tOracleOutput_12=null;
		    	String errormessage_tOracleOutput_12;
				if (ne_tOracleOutput_12 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_12 = new java.sql.SQLException(e_tOracleOutput_12.getMessage() + "\ncaused by: " + ne_tOracleOutput_12.getMessage(), ne_tOracleOutput_12.getSQLState(), ne_tOracleOutput_12.getErrorCode(), ne_tOracleOutput_12);
					errormessage_tOracleOutput_12 = sqle_tOracleOutput_12.getMessage();
				}else{
					errormessage_tOracleOutput_12 = e_tOracleOutput_12.getMessage();
				}
            	
            log.error("tOracleOutput_12 - "  + (errormessage_tOracleOutput_12) );
                	System.err.println(errormessage_tOracleOutput_12);
            	
        	}
        	if (pstmt_tOracleOutput_12 != null) {
            	tmp_batchUpdateCount_tOracleOutput_12 = pstmt_tOracleOutput_12.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_12
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_12!=-1?tmp_batchUpdateCount_tOracleOutput_12:0);
            }
        if(pstmt_tOracleOutput_12 != null) {
			
				pstmt_tOracleOutput_12.close();
			
        }
		if(commitCounter_tOracleOutput_12 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_12)  + (" record(s).") );
		    conn_tOracleOutput_12.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_12 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_12", true);
   	


	
	nb_line_deleted_tOracleOutput_12=nb_line_deleted_tOracleOutput_12+ deletedCount_tOracleOutput_12;
	nb_line_update_tOracleOutput_12=nb_line_update_tOracleOutput_12 + updatedCount_tOracleOutput_12;
	nb_line_inserted_tOracleOutput_12=nb_line_inserted_tOracleOutput_12 + insertedCount_tOracleOutput_12;
	nb_line_rejected_tOracleOutput_12=nb_line_rejected_tOracleOutput_12 + rejectedCount_tOracleOutput_12;
	
        globalMap.put("tOracleOutput_12_NB_LINE",nb_line_tOracleOutput_12);
        globalMap.put("tOracleOutput_12_NB_LINE_UPDATED",nb_line_update_tOracleOutput_12);
        globalMap.put("tOracleOutput_12_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_12);
        globalMap.put("tOracleOutput_12_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_12);
        globalMap.put("tOracleOutput_12_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_12);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_12)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("copyOfout1"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_12", true);
end_Hash.put("tOracleOutput_12", System.currentTimeMillis());




/**
 * [tOracleOutput_12 end ] stop
 */









				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tRowGenerator_3:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk8", 0, "ok");
								} 
							
							tOracleInput_1Process(globalMap); 
						



	
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
	 * [tRowGenerator_3 finally ] start
	 */

	

	
	
	currentComponent="tRowGenerator_3";

	

 



/**
 * [tRowGenerator_3 finally ] stop
 */

	
	/**
	 * [tOracleOutput_11 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_11";

	



	
		if(resourceMap.get("finish_tOracleOutput_11")==null){
			if(resourceMap.get("conn_tOracleOutput_11")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_11 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_11");
					
					
            		
					ctn_tOracleOutput_11.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_11 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_11) {
					String errorMessage_tOracleOutput_11 = "failed to close the connection in tOracleOutput_11 :" + sqlEx_tOracleOutput_11.getMessage();
					
            log.error("tOracleOutput_11 - "  + (errorMessage_tOracleOutput_11) );
					System.err.println(errorMessage_tOracleOutput_11);
				}
			}
		}
	

 



/**
 * [tOracleOutput_11 finally ] stop
 */

	
	/**
	 * [tMap_6 finally ] start
	 */

	

	
	
	currentComponent="tMap_6";

	

 



/**
 * [tMap_6 finally ] stop
 */

	
	/**
	 * [tOracleOutput_12 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_12";

	



	
		if(resourceMap.get("finish_tOracleOutput_12")==null){
			if(resourceMap.get("conn_tOracleOutput_12")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_12 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_12");
					
					
            		
					ctn_tOracleOutput_12.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_12 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_12) {
					String errorMessage_tOracleOutput_12 = "failed to close the connection in tOracleOutput_12 :" + sqlEx_tOracleOutput_12.getMessage();
					
            log.error("tOracleOutput_12 - "  + (errorMessage_tOracleOutput_12) );
					System.err.println(errorMessage_tOracleOutput_12);
				}
			}
		}
	

 



/**
 * [tOracleOutput_12 finally ] stop
 */









				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tRowGenerator_3_SUBPROCESS_STATE", 1);
	}
	


public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(out2Struct other) {

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

public static class out3Struct implements routines.system.IPersistableRow<out3Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(out3Struct other) {

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

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
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



		row3Struct row3 = new row3Struct();
out2Struct out2 = new out2Struct();
out3Struct out3 = new out3Struct();





	
	/**
	 * [tOracleOutput_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_3", false);
		start_Hash.put("tOracleOutput_3", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_3";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out2" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_3{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_3 = new StringBuilder();
            log4jParamters_tOracleOutput_3.append("Parameters:");
                    log4jParamters_tOracleOutput_3.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("TABLE" + " = " + "\"BUY\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_3.append(" | ");
                    log4jParamters_tOracleOutput_3.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_3.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + (log4jParamters_tOracleOutput_3) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_3().limitLog4jByte();






    int nb_line_tOracleOutput_3 = 0;
    int nb_line_update_tOracleOutput_3 = 0;
    int nb_line_inserted_tOracleOutput_3 = 0;
    int nb_line_deleted_tOracleOutput_3 = 0;
    int nb_line_rejected_tOracleOutput_3 = 0;

    int tmp_batchUpdateCount_tOracleOutput_3 = 0;

    int deletedCount_tOracleOutput_3=0;
    int updatedCount_tOracleOutput_3=0;
    int insertedCount_tOracleOutput_3=0;
    int rejectedCount_tOracleOutput_3=0;

    boolean whetherReject_tOracleOutput_3 = false;

    java.sql.Connection conn_tOracleOutput_3 = null;

    //optional table
    String dbschema_tOracleOutput_3 = null;
    String tableName_tOracleOutput_3 = null;
                    String driverClass_tOracleOutput_3 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_3)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_3);
                String url_tOracleOutput_3 = null;
                    url_tOracleOutput_3 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_3 = "hr";
 
	final String decryptedPassword_tOracleOutput_3 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_3 = decryptedPassword_tOracleOutput_3;
                dbschema_tOracleOutput_3 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection attempts to '")  + (url_tOracleOutput_3)  + ("' with the username '")  + (dbUser_tOracleOutput_3)  + ("'.") );

                    conn_tOracleOutput_3 = java.sql.DriverManager.getConnection(url_tOracleOutput_3, dbUser_tOracleOutput_3, dbPwd_tOracleOutput_3);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection to '")  + (url_tOracleOutput_3)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_3", conn_tOracleOutput_3);
            conn_tOracleOutput_3.setAutoCommit(false);
            int commitEvery_tOracleOutput_3 = 10000;
            int commitCounter_tOracleOutput_3 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_3.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_3 = 10000;
        int batchSizeCounter_tOracleOutput_3=0;
        int count_tOracleOutput_3=0;

        if(dbschema_tOracleOutput_3 == null || dbschema_tOracleOutput_3.trim().length() == 0) {
            tableName_tOracleOutput_3 = ("BUY");
        } else {
            tableName_tOracleOutput_3 = dbschema_tOracleOutput_3 + "." + ("BUY");
        }
                                String tableNameForSearch_tOracleOutput_3= "" + ((String)"BUY") + "";
String dbschemaForSearch_tOracleOutput_3= null;
if(dbschema_tOracleOutput_3== null || dbschema_tOracleOutput_3.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_3= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_3= dbschema_tOracleOutput_3.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_3 = conn_tOracleOutput_3.getMetaData();
                                if(tableNameForSearch_tOracleOutput_3.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_3 = tableNameForSearch_tOracleOutput_3.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_3 = tableNameForSearch_tOracleOutput_3.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_3 = dbMetaData_tOracleOutput_3.getTables(null, dbschemaForSearch_tOracleOutput_3, tableNameForSearch_tOracleOutput_3, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_3 = false;
                                if(rsTable_tOracleOutput_3.next()) {
                                    whetherExist_tOracleOutput_3 = true;
                                }
                                rsTable_tOracleOutput_3.close();

                                if(whetherExist_tOracleOutput_3) {
                                    java.sql.Statement stmtDrop_tOracleOutput_3 = conn_tOracleOutput_3.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_3)  + ("'.") );
                                    stmtDrop_tOracleOutput_3.execute("DROP TABLE " + tableName_tOracleOutput_3 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_3)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_3.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_3 = conn_tOracleOutput_3.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_3)  + ("'.") );
                                    stmtCreate_tOracleOutput_3.execute("CREATE TABLE " + tableName_tOracleOutput_3 + "(TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,AMOUNT FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_3)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_3.close();
                String insert_tOracleOutput_3 = "INSERT INTO " + tableName_tOracleOutput_3 + " (TRADE_TP,QUANTITY,AMOUNT) VALUES (?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_3 = conn_tOracleOutput_3.prepareStatement(insert_tOracleOutput_3);





 



/**
 * [tOracleOutput_3 begin ] stop
 */




	
	/**
	 * [tOracleOutput_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_4", false);
		start_Hash.put("tOracleOutput_4", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_4";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out3" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_4 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_4{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_4 = new StringBuilder();
            log4jParamters_tOracleOutput_4.append("Parameters:");
                    log4jParamters_tOracleOutput_4.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("TABLE" + " = " + "\"SELL\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_4.append(" | ");
                    log4jParamters_tOracleOutput_4.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_4.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + (log4jParamters_tOracleOutput_4) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_4().limitLog4jByte();






    int nb_line_tOracleOutput_4 = 0;
    int nb_line_update_tOracleOutput_4 = 0;
    int nb_line_inserted_tOracleOutput_4 = 0;
    int nb_line_deleted_tOracleOutput_4 = 0;
    int nb_line_rejected_tOracleOutput_4 = 0;

    int tmp_batchUpdateCount_tOracleOutput_4 = 0;

    int deletedCount_tOracleOutput_4=0;
    int updatedCount_tOracleOutput_4=0;
    int insertedCount_tOracleOutput_4=0;
    int rejectedCount_tOracleOutput_4=0;

    boolean whetherReject_tOracleOutput_4 = false;

    java.sql.Connection conn_tOracleOutput_4 = null;

    //optional table
    String dbschema_tOracleOutput_4 = null;
    String tableName_tOracleOutput_4 = null;
                    String driverClass_tOracleOutput_4 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_4)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_4);
                String url_tOracleOutput_4 = null;
                    url_tOracleOutput_4 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_4 = "hr";
 
	final String decryptedPassword_tOracleOutput_4 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_4 = decryptedPassword_tOracleOutput_4;
                dbschema_tOracleOutput_4 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection attempts to '")  + (url_tOracleOutput_4)  + ("' with the username '")  + (dbUser_tOracleOutput_4)  + ("'.") );

                    conn_tOracleOutput_4 = java.sql.DriverManager.getConnection(url_tOracleOutput_4, dbUser_tOracleOutput_4, dbPwd_tOracleOutput_4);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection to '")  + (url_tOracleOutput_4)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_4", conn_tOracleOutput_4);
            conn_tOracleOutput_4.setAutoCommit(false);
            int commitEvery_tOracleOutput_4 = 10000;
            int commitCounter_tOracleOutput_4 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_4.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_4 = 10000;
        int batchSizeCounter_tOracleOutput_4=0;
        int count_tOracleOutput_4=0;

        if(dbschema_tOracleOutput_4 == null || dbschema_tOracleOutput_4.trim().length() == 0) {
            tableName_tOracleOutput_4 = ("SELL");
        } else {
            tableName_tOracleOutput_4 = dbschema_tOracleOutput_4 + "." + ("SELL");
        }
                                String tableNameForSearch_tOracleOutput_4= "" + ((String)"SELL") + "";
String dbschemaForSearch_tOracleOutput_4= null;
if(dbschema_tOracleOutput_4== null || dbschema_tOracleOutput_4.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_4= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_4= dbschema_tOracleOutput_4.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_4 = conn_tOracleOutput_4.getMetaData();
                                if(tableNameForSearch_tOracleOutput_4.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_4 = tableNameForSearch_tOracleOutput_4.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_4 = tableNameForSearch_tOracleOutput_4.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_4 = dbMetaData_tOracleOutput_4.getTables(null, dbschemaForSearch_tOracleOutput_4, tableNameForSearch_tOracleOutput_4, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_4 = false;
                                if(rsTable_tOracleOutput_4.next()) {
                                    whetherExist_tOracleOutput_4 = true;
                                }
                                rsTable_tOracleOutput_4.close();

                                if(whetherExist_tOracleOutput_4) {
                                    java.sql.Statement stmtDrop_tOracleOutput_4 = conn_tOracleOutput_4.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_4)  + ("'.") );
                                    stmtDrop_tOracleOutput_4.execute("DROP TABLE " + tableName_tOracleOutput_4 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_4)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_4.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_4 = conn_tOracleOutput_4.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_4)  + ("'.") );
                                    stmtCreate_tOracleOutput_4.execute("CREATE TABLE " + tableName_tOracleOutput_4 + "(TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,AMOUNT FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_4)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_4.close();
                String insert_tOracleOutput_4 = "INSERT INTO " + tableName_tOracleOutput_4 + " (TRADE_TP,QUANTITY,AMOUNT) VALUES (?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_4 = conn_tOracleOutput_4.prepareStatement(insert_tOracleOutput_4);





 



/**
 * [tOracleOutput_4 begin ] stop
 */



	
	/**
	 * [tMap_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_2", false);
		start_Hash.put("tMap_2", System.currentTimeMillis());
		
	
	currentComponent="tMap_2";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row3" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_2 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_2{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_2 = new StringBuilder();
            log4jParamters_tMap_2.append("Parameters:");
                    log4jParamters_tMap_2.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_2.append(" | ");
                    log4jParamters_tMap_2.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_2.append(" | ");
                    log4jParamters_tMap_2.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_2.append(" | ");
                    log4jParamters_tMap_2.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_2 - "  + (log4jParamters_tMap_2) );
    		}
    	}
    	
        new BytesLimit65535_tMap_2().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row3_tMap_2 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_2__Struct  {
}
Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out2_tMap_2 = 0;
				
out2Struct out2_tmp = new out2Struct();
				int count_out3_tMap_2 = 0;
				
out3Struct out3_tmp = new out3Struct();
// ###############################

        
        



        









 



/**
 * [tMap_2 begin ] stop
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
                    log4jParamters_tOracleInput_1.append("TABLE" + " = " + "\"STAGE2\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERY" + " = " + "\"select TRADE_TP, case when TRADE_TP='sell' then sum(QUANTITY) else sum(QUANTITY) end as QUANTITY, case when TRADE_TP='sell' then sum(QUANTITY*PRICE) else sum(QUANTITY*(-1*PRICE)) end as AMOUNT from STAGE2 group by TRADE_TP\"");
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
                    log4jParamters_tOracleInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("AMOUNT")+"}]");
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

		    String dbquery_tOracleInput_1 = "select TRADE_TP, case when TRADE_TP='sell' then sum(QUANTITY) else sum(QUANTITY) end as QUANTITY, case when TRADE_TP='sell' then sum(QUANTITY*PRICE) else sum(QUANTITY*(-1*PRICE)) end as AMOUNT from STAGE2 group by TRADE_TP";
			
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
								row3.TRADE_TP = null;
							} else {
	                         		
        	row3.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_1, 1, false);
		                    }
							if(colQtyInRs_tOracleInput_1 < 2) {
								row3.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(2) != null) {
						row3.QUANTITY = rs_tOracleInput_1.getInt(2);
					} else {
				
						row3.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 3) {
								row3.AMOUNT = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(3) != null) {
						row3.AMOUNT = rs_tOracleInput_1.getFloat(3);
					} else {
				
						row3.AMOUNT = null;
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
	 * [tMap_2 main ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

			//row3
			//row3


			
				if(execStat){
					runStat.updateStatOnConnection("row3"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row3 - " + (row3==null? "": row3.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_2 = false;
		  boolean mainRowRejected_tMap_2 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
        // ###############################
        // # Output tables

out2 = null;
out3 = null;


// # Output table : 'out2'
// # Filter conditions 
if( 

row3.TRADE_TP.equals("buy")

 ) {
count_out2_tMap_2++;

out2_tmp.TRADE_TP = row3.TRADE_TP ;
out2_tmp.QUANTITY = row3.QUANTITY ;
out2_tmp.AMOUNT = row3.AMOUNT ;
out2 = out2_tmp;
log.debug("tMap_2 - Outputting the record " + count_out2_tMap_2 + " of the output table 'out2'.");

} // closing filter/reject

// # Output table : 'out3'
// # Filter conditions 
if( 

row3.TRADE_TP.equals("sell")

 ) {
count_out3_tMap_2++;

out3_tmp.TRADE_TP = row3.TRADE_TP ;
out3_tmp.QUANTITY = row3.QUANTITY ;
out3_tmp.AMOUNT = row3.AMOUNT ;
out3 = out3_tmp;
log.debug("tMap_2 - Outputting the record " + count_out3_tMap_2 + " of the output table 'out3'.");

} // closing filter/reject
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_2 = false;










 


	tos_count_tMap_2++;

/**
 * [tMap_2 main ] stop
 */
// Start of branch "out2"
if(out2 != null) { 



	
	/**
	 * [tOracleOutput_3 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_3";

	

			//out2
			//out2


			
				if(execStat){
					runStat.updateStatOnConnection("out2"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out2 - " + (out2==null? "": out2.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_3 = false;
                        if(out2.TRADE_TP == null) {
pstmt_tOracleOutput_3.setNull(1, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_3.setString(1, out2.TRADE_TP);
}

                        if(out2.QUANTITY == null) {
pstmt_tOracleOutput_3.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(2, out2.QUANTITY);
}

                        if(out2.AMOUNT == null) {
pstmt_tOracleOutput_3.setNull(3, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_3.setFloat(3, out2.AMOUNT);
}

                pstmt_tOracleOutput_3.addBatch();
                nb_line_tOracleOutput_3++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_3)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_3++;
            if (batchSize_tOracleOutput_3 > 0 &&  batchSize_tOracleOutput_3 <= batchSizeCounter_tOracleOutput_3) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_3.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_3){
			        java.sql.SQLException ne_tOracleOutput_3 = e_tOracleOutput_3.getNextException(),sqle_tOracleOutput_3=null;
			    	String errormessage_tOracleOutput_3;
					if (ne_tOracleOutput_3 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_3 = new java.sql.SQLException(e_tOracleOutput_3.getMessage() + "\ncaused by: " + ne_tOracleOutput_3.getMessage(), ne_tOracleOutput_3.getSQLState(), ne_tOracleOutput_3.getErrorCode(), ne_tOracleOutput_3);
						errormessage_tOracleOutput_3 = sqle_tOracleOutput_3.getMessage();
					}else{
						errormessage_tOracleOutput_3 = e_tOracleOutput_3.getMessage();
					}
	            	
            log.error("tOracleOutput_3 - "  + (errormessage_tOracleOutput_3) );
	                	System.err.println(errormessage_tOracleOutput_3);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_3 = pstmt_tOracleOutput_3.getUpdateCount();
                    insertedCount_tOracleOutput_3
                += (tmp_batchUpdateCount_tOracleOutput_3!=-1?tmp_batchUpdateCount_tOracleOutput_3:0);
                batchSizeCounter_tOracleOutput_3 = 0;
            }
                commitCounter_tOracleOutput_3++;
                if(commitEvery_tOracleOutput_3 <= commitCounter_tOracleOutput_3) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_3.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_3){
					        java.sql.SQLException ne_tOracleOutput_3 = e_tOracleOutput_3.getNextException(),sqle_tOracleOutput_3=null;
					    	String errormessage_tOracleOutput_3;
							if (ne_tOracleOutput_3 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_3 = new java.sql.SQLException(e_tOracleOutput_3.getMessage() + "\ncaused by: " + ne_tOracleOutput_3.getMessage(), ne_tOracleOutput_3.getSQLState(), ne_tOracleOutput_3.getErrorCode(), ne_tOracleOutput_3);
								errormessage_tOracleOutput_3 = sqle_tOracleOutput_3.getMessage();
							}else{
								errormessage_tOracleOutput_3 = e_tOracleOutput_3.getMessage();
							}
			            	
            log.error("tOracleOutput_3 - "  + (errormessage_tOracleOutput_3) );
			                	System.err.println(errormessage_tOracleOutput_3);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_3 = pstmt_tOracleOutput_3.getUpdateCount();
                            insertedCount_tOracleOutput_3
                        += (tmp_batchUpdateCount_tOracleOutput_3!=-1?tmp_batchUpdateCount_tOracleOutput_3:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_3)  + (" record(s).") );
                    conn_tOracleOutput_3.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_3=0;
                }

 


	tos_count_tOracleOutput_3++;

/**
 * [tOracleOutput_3 main ] stop
 */

} // End of branch "out2"




// Start of branch "out3"
if(out3 != null) { 



	
	/**
	 * [tOracleOutput_4 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_4";

	

			//out3
			//out3


			
				if(execStat){
					runStat.updateStatOnConnection("out3"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out3 - " + (out3==null? "": out3.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_4 = false;
                        if(out3.TRADE_TP == null) {
pstmt_tOracleOutput_4.setNull(1, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_4.setString(1, out3.TRADE_TP);
}

                        if(out3.QUANTITY == null) {
pstmt_tOracleOutput_4.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(2, out3.QUANTITY);
}

                        if(out3.AMOUNT == null) {
pstmt_tOracleOutput_4.setNull(3, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_4.setFloat(3, out3.AMOUNT);
}

                pstmt_tOracleOutput_4.addBatch();
                nb_line_tOracleOutput_4++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_4)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_4++;
            if (batchSize_tOracleOutput_4 > 0 &&  batchSize_tOracleOutput_4 <= batchSizeCounter_tOracleOutput_4) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_4.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_4){
			        java.sql.SQLException ne_tOracleOutput_4 = e_tOracleOutput_4.getNextException(),sqle_tOracleOutput_4=null;
			    	String errormessage_tOracleOutput_4;
					if (ne_tOracleOutput_4 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_4 = new java.sql.SQLException(e_tOracleOutput_4.getMessage() + "\ncaused by: " + ne_tOracleOutput_4.getMessage(), ne_tOracleOutput_4.getSQLState(), ne_tOracleOutput_4.getErrorCode(), ne_tOracleOutput_4);
						errormessage_tOracleOutput_4 = sqle_tOracleOutput_4.getMessage();
					}else{
						errormessage_tOracleOutput_4 = e_tOracleOutput_4.getMessage();
					}
	            	
            log.error("tOracleOutput_4 - "  + (errormessage_tOracleOutput_4) );
	                	System.err.println(errormessage_tOracleOutput_4);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_4 = pstmt_tOracleOutput_4.getUpdateCount();
                    insertedCount_tOracleOutput_4
                += (tmp_batchUpdateCount_tOracleOutput_4!=-1?tmp_batchUpdateCount_tOracleOutput_4:0);
                batchSizeCounter_tOracleOutput_4 = 0;
            }
                commitCounter_tOracleOutput_4++;
                if(commitEvery_tOracleOutput_4 <= commitCounter_tOracleOutput_4) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_4.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_4){
					        java.sql.SQLException ne_tOracleOutput_4 = e_tOracleOutput_4.getNextException(),sqle_tOracleOutput_4=null;
					    	String errormessage_tOracleOutput_4;
							if (ne_tOracleOutput_4 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_4 = new java.sql.SQLException(e_tOracleOutput_4.getMessage() + "\ncaused by: " + ne_tOracleOutput_4.getMessage(), ne_tOracleOutput_4.getSQLState(), ne_tOracleOutput_4.getErrorCode(), ne_tOracleOutput_4);
								errormessage_tOracleOutput_4 = sqle_tOracleOutput_4.getMessage();
							}else{
								errormessage_tOracleOutput_4 = e_tOracleOutput_4.getMessage();
							}
			            	
            log.error("tOracleOutput_4 - "  + (errormessage_tOracleOutput_4) );
			                	System.err.println(errormessage_tOracleOutput_4);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_4 = pstmt_tOracleOutput_4.getUpdateCount();
                            insertedCount_tOracleOutput_4
                        += (tmp_batchUpdateCount_tOracleOutput_4!=-1?tmp_batchUpdateCount_tOracleOutput_4:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_4)  + (" record(s).") );
                    conn_tOracleOutput_4.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_4=0;
                }

 


	tos_count_tOracleOutput_4++;

/**
 * [tOracleOutput_4 main ] stop
 */

} // End of branch "out3"







	
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
	 * [tMap_2 end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_2 - Written records count in the table 'out2': " + count_out2_tMap_2 + ".");
				log.debug("tMap_2 - Written records count in the table 'out3': " + count_out3_tMap_2 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row3"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_2 - "  + ("Done.") );

ok_Hash.put("tMap_2", true);
end_Hash.put("tMap_2", System.currentTimeMillis());




/**
 * [tMap_2 end ] stop
 */

	
	/**
	 * [tOracleOutput_3 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_3";

	
	



	
            try {
            	if (pstmt_tOracleOutput_3 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_3.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_3){
		        java.sql.SQLException ne_tOracleOutput_3 = e_tOracleOutput_3.getNextException(),sqle_tOracleOutput_3=null;
		    	String errormessage_tOracleOutput_3;
				if (ne_tOracleOutput_3 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_3 = new java.sql.SQLException(e_tOracleOutput_3.getMessage() + "\ncaused by: " + ne_tOracleOutput_3.getMessage(), ne_tOracleOutput_3.getSQLState(), ne_tOracleOutput_3.getErrorCode(), ne_tOracleOutput_3);
					errormessage_tOracleOutput_3 = sqle_tOracleOutput_3.getMessage();
				}else{
					errormessage_tOracleOutput_3 = e_tOracleOutput_3.getMessage();
				}
            	
            log.error("tOracleOutput_3 - "  + (errormessage_tOracleOutput_3) );
                	System.err.println(errormessage_tOracleOutput_3);
            	
        	}
        	if (pstmt_tOracleOutput_3 != null) {
            	tmp_batchUpdateCount_tOracleOutput_3 = pstmt_tOracleOutput_3.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_3
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_3!=-1?tmp_batchUpdateCount_tOracleOutput_3:0);
            }
        if(pstmt_tOracleOutput_3 != null) {
			
				pstmt_tOracleOutput_3.close();
			
        }
		if(commitCounter_tOracleOutput_3 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_3)  + (" record(s).") );
		    conn_tOracleOutput_3.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_3 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_3", true);
   	


	
	nb_line_deleted_tOracleOutput_3=nb_line_deleted_tOracleOutput_3+ deletedCount_tOracleOutput_3;
	nb_line_update_tOracleOutput_3=nb_line_update_tOracleOutput_3 + updatedCount_tOracleOutput_3;
	nb_line_inserted_tOracleOutput_3=nb_line_inserted_tOracleOutput_3 + insertedCount_tOracleOutput_3;
	nb_line_rejected_tOracleOutput_3=nb_line_rejected_tOracleOutput_3 + rejectedCount_tOracleOutput_3;
	
        globalMap.put("tOracleOutput_3_NB_LINE",nb_line_tOracleOutput_3);
        globalMap.put("tOracleOutput_3_NB_LINE_UPDATED",nb_line_update_tOracleOutput_3);
        globalMap.put("tOracleOutput_3_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_3);
        globalMap.put("tOracleOutput_3_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_3);
        globalMap.put("tOracleOutput_3_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_3);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_3)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("out2"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_3", true);
end_Hash.put("tOracleOutput_3", System.currentTimeMillis());




/**
 * [tOracleOutput_3 end ] stop
 */




	
	/**
	 * [tOracleOutput_4 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_4";

	
	



	
            try {
            	if (pstmt_tOracleOutput_4 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_4.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_4){
		        java.sql.SQLException ne_tOracleOutput_4 = e_tOracleOutput_4.getNextException(),sqle_tOracleOutput_4=null;
		    	String errormessage_tOracleOutput_4;
				if (ne_tOracleOutput_4 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_4 = new java.sql.SQLException(e_tOracleOutput_4.getMessage() + "\ncaused by: " + ne_tOracleOutput_4.getMessage(), ne_tOracleOutput_4.getSQLState(), ne_tOracleOutput_4.getErrorCode(), ne_tOracleOutput_4);
					errormessage_tOracleOutput_4 = sqle_tOracleOutput_4.getMessage();
				}else{
					errormessage_tOracleOutput_4 = e_tOracleOutput_4.getMessage();
				}
            	
            log.error("tOracleOutput_4 - "  + (errormessage_tOracleOutput_4) );
                	System.err.println(errormessage_tOracleOutput_4);
            	
        	}
        	if (pstmt_tOracleOutput_4 != null) {
            	tmp_batchUpdateCount_tOracleOutput_4 = pstmt_tOracleOutput_4.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_4
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_4!=-1?tmp_batchUpdateCount_tOracleOutput_4:0);
            }
        if(pstmt_tOracleOutput_4 != null) {
			
				pstmt_tOracleOutput_4.close();
			
        }
		if(commitCounter_tOracleOutput_4 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_4)  + (" record(s).") );
		    conn_tOracleOutput_4.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_4 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_4", true);
   	


	
	nb_line_deleted_tOracleOutput_4=nb_line_deleted_tOracleOutput_4+ deletedCount_tOracleOutput_4;
	nb_line_update_tOracleOutput_4=nb_line_update_tOracleOutput_4 + updatedCount_tOracleOutput_4;
	nb_line_inserted_tOracleOutput_4=nb_line_inserted_tOracleOutput_4 + insertedCount_tOracleOutput_4;
	nb_line_rejected_tOracleOutput_4=nb_line_rejected_tOracleOutput_4 + rejectedCount_tOracleOutput_4;
	
        globalMap.put("tOracleOutput_4_NB_LINE",nb_line_tOracleOutput_4);
        globalMap.put("tOracleOutput_4_NB_LINE_UPDATED",nb_line_update_tOracleOutput_4);
        globalMap.put("tOracleOutput_4_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_4);
        globalMap.put("tOracleOutput_4_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_4);
        globalMap.put("tOracleOutput_4_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_4);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_4)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("out3"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_4", true);
end_Hash.put("tOracleOutput_4", System.currentTimeMillis());




/**
 * [tOracleOutput_4 end ] stop
 */






				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_1:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
								} 
							
							tOracleInput_3Process(globalMap); 
						



	
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
	 * [tMap_2 finally ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 finally ] stop
 */

	
	/**
	 * [tOracleOutput_3 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_3";

	



	
		if(resourceMap.get("finish_tOracleOutput_3")==null){
			if(resourceMap.get("conn_tOracleOutput_3")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_3 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_3");
					
					
            		
					ctn_tOracleOutput_3.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_3) {
					String errorMessage_tOracleOutput_3 = "failed to close the connection in tOracleOutput_3 :" + sqlEx_tOracleOutput_3.getMessage();
					
            log.error("tOracleOutput_3 - "  + (errorMessage_tOracleOutput_3) );
					System.err.println(errorMessage_tOracleOutput_3);
				}
			}
		}
	

 



/**
 * [tOracleOutput_3 finally ] stop
 */




	
	/**
	 * [tOracleOutput_4 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_4";

	



	
		if(resourceMap.get("finish_tOracleOutput_4")==null){
			if(resourceMap.get("conn_tOracleOutput_4")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_4 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_4");
					
					
            		
					ctn_tOracleOutput_4.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_4) {
					String errorMessage_tOracleOutput_4 = "failed to close the connection in tOracleOutput_4 :" + sqlEx_tOracleOutput_4.getMessage();
					
            log.error("tOracleOutput_4 - "  + (errorMessage_tOracleOutput_4) );
					System.err.println(errorMessage_tOracleOutput_4);
				}
			}
		}
	

 



/**
 * [tOracleOutput_4 finally ] stop
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
	


public static class out4Struct implements routines.system.IPersistableRow<out4Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
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
		sb.append("QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
    public int compareTo(out4Struct other) {

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

public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row4Struct other) {

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

public static class after_tOracleInput_3Struct implements routines.system.IPersistableRow<after_tOracleInput_3Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(after_tOracleInput_3Struct other) {

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
public void tOracleInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_3_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;


		tOracleInput_4Process(globalMap);

		row4Struct row4 = new row4Struct();
out4Struct out4 = new out4Struct();





	
	/**
	 * [tOracleOutput_5 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_5", false);
		start_Hash.put("tOracleOutput_5", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_5";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out4" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_5 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_5{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_5 = new StringBuilder();
            log4jParamters_tOracleOutput_5.append("Parameters:");
                    log4jParamters_tOracleOutput_5.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("TABLE" + " = " + "\"TRADEBAL\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_5.append(" | ");
                    log4jParamters_tOracleOutput_5.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_5.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + (log4jParamters_tOracleOutput_5) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_5().limitLog4jByte();






    int nb_line_tOracleOutput_5 = 0;
    int nb_line_update_tOracleOutput_5 = 0;
    int nb_line_inserted_tOracleOutput_5 = 0;
    int nb_line_deleted_tOracleOutput_5 = 0;
    int nb_line_rejected_tOracleOutput_5 = 0;

    int tmp_batchUpdateCount_tOracleOutput_5 = 0;

    int deletedCount_tOracleOutput_5=0;
    int updatedCount_tOracleOutput_5=0;
    int insertedCount_tOracleOutput_5=0;
    int rejectedCount_tOracleOutput_5=0;

    boolean whetherReject_tOracleOutput_5 = false;

    java.sql.Connection conn_tOracleOutput_5 = null;

    //optional table
    String dbschema_tOracleOutput_5 = null;
    String tableName_tOracleOutput_5 = null;
                    String driverClass_tOracleOutput_5 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_5)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_5);
                String url_tOracleOutput_5 = null;
                    url_tOracleOutput_5 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_5 = "hr";
 
	final String decryptedPassword_tOracleOutput_5 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_5 = decryptedPassword_tOracleOutput_5;
                dbschema_tOracleOutput_5 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection attempts to '")  + (url_tOracleOutput_5)  + ("' with the username '")  + (dbUser_tOracleOutput_5)  + ("'.") );

                    conn_tOracleOutput_5 = java.sql.DriverManager.getConnection(url_tOracleOutput_5, dbUser_tOracleOutput_5, dbPwd_tOracleOutput_5);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection to '")  + (url_tOracleOutput_5)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_5", conn_tOracleOutput_5);
            conn_tOracleOutput_5.setAutoCommit(false);
            int commitEvery_tOracleOutput_5 = 10000;
            int commitCounter_tOracleOutput_5 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_5.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_5 = 10000;
        int batchSizeCounter_tOracleOutput_5=0;
        int count_tOracleOutput_5=0;

        if(dbschema_tOracleOutput_5 == null || dbschema_tOracleOutput_5.trim().length() == 0) {
            tableName_tOracleOutput_5 = ("TRADEBAL");
        } else {
            tableName_tOracleOutput_5 = dbschema_tOracleOutput_5 + "." + ("TRADEBAL");
        }
                                String tableNameForSearch_tOracleOutput_5= "" + ((String)"TRADEBAL") + "";
String dbschemaForSearch_tOracleOutput_5= null;
if(dbschema_tOracleOutput_5== null || dbschema_tOracleOutput_5.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_5= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_5= dbschema_tOracleOutput_5.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_5 = conn_tOracleOutput_5.getMetaData();
                                if(tableNameForSearch_tOracleOutput_5.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_5 = tableNameForSearch_tOracleOutput_5.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_5 = tableNameForSearch_tOracleOutput_5.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_5 = dbMetaData_tOracleOutput_5.getTables(null, dbschemaForSearch_tOracleOutput_5, tableNameForSearch_tOracleOutput_5, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_5 = false;
                                if(rsTable_tOracleOutput_5.next()) {
                                    whetherExist_tOracleOutput_5 = true;
                                }
                                rsTable_tOracleOutput_5.close();

                                if(whetherExist_tOracleOutput_5) {
                                    java.sql.Statement stmtDrop_tOracleOutput_5 = conn_tOracleOutput_5.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_5)  + ("'.") );
                                    stmtDrop_tOracleOutput_5.execute("DROP TABLE " + tableName_tOracleOutput_5 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_5)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_5.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_5 = conn_tOracleOutput_5.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_5)  + ("'.") );
                                    stmtCreate_tOracleOutput_5.execute("CREATE TABLE " + tableName_tOracleOutput_5 + "(QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_5)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_5.close();
                String insert_tOracleOutput_5 = "INSERT INTO " + tableName_tOracleOutput_5 + " (QUANTITY,PRICE) VALUES (?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_5 = conn_tOracleOutput_5.prepareStatement(insert_tOracleOutput_5);





 



/**
 * [tOracleOutput_5 begin ] stop
 */



	
	/**
	 * [tMap_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_3", false);
		start_Hash.put("tMap_3", System.currentTimeMillis());
		
	
	currentComponent="tMap_3";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row4" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_3 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_3{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_3 = new StringBuilder();
            log4jParamters_tMap_3.append("Parameters:");
                    log4jParamters_tMap_3.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_3.append(" | ");
                    log4jParamters_tMap_3.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_3.append(" | ");
                    log4jParamters_tMap_3.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_3.append(" | ");
                    log4jParamters_tMap_3.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_3.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_3 - "  + (log4jParamters_tMap_3) );
    		}
    	}
    	
        new BytesLimit65535_tMap_3().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row4_tMap_3 = 0;
		
		int count_row5_tMap_3 = 0;
		
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
					globalMap.get( "tHash_Lookup_row5" ))
					;					
					
	
		tHash_Lookup_row5.initGet();
	

row5Struct row5HashKey = new row5Struct();
row5Struct row5Default = new row5Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_3__Struct  {
}
Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out4_tMap_3 = 0;
				
out4Struct out4_tmp = new out4Struct();
// ###############################

        
        



        









 



/**
 * [tMap_3 begin ] stop
 */



	
	/**
	 * [tOracleInput_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_3", false);
		start_Hash.put("tOracleInput_3", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_3";

	
		int tos_count_tOracleInput_3 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_3 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_3{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_3 = new StringBuilder();
            log4jParamters_tOracleInput_3.append("Parameters:");
                    log4jParamters_tOracleInput_3.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("TABLE" + " = " + "\"BUY\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("QUERY" + " = " + "\"SELECT    BUY.TRADE_TP,    BUY.QUANTITY,    BUY.AMOUNT FROM BUY\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("AMOUNT")+"}]");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_3.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_3 - "  + (log4jParamters_tOracleInput_3) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_3().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_3 = 0;
		    java.sql.Connection conn_tOracleInput_3 = null;
				String driverClass_tOracleInput_3 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_3);
				
			String url_tOracleInput_3 = null;
				url_tOracleInput_3 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_3 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_3 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_3 = decryptedPassword_tOracleInput_3;

				
	    		log.debug("tOracleInput_3 - Driver ClassName: "+driverClass_tOracleInput_3+".");
			
	    		log.debug("tOracleInput_3 - Connection attempt to '" + url_tOracleInput_3 + "' with the username '" + dbUser_tOracleInput_3 + "'.");
			
					conn_tOracleInput_3 = java.sql.DriverManager.getConnection(url_tOracleInput_3,dbUser_tOracleInput_3,dbPwd_tOracleInput_3);
	    		log.debug("tOracleInput_3 - Connection to '" + url_tOracleInput_3 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_3 = conn_tOracleInput_3.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_3 = stmtGetTZ_tOracleInput_3.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_3 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_3.next()) {
					sessionTimezone_tOracleInput_3 = rsGetTZ_tOracleInput_3.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_3).setSessionTimeZone(sessionTimezone_tOracleInput_3);
		    
			java.sql.Statement stmt_tOracleInput_3 = conn_tOracleInput_3.createStatement();

		    String dbquery_tOracleInput_3 = "SELECT \n  BUY.TRADE_TP, \n  BUY.QUANTITY, \n  BUY.AMOUNT\nFROM BUY";
			
                log.debug("tOracleInput_3 - Executing the query: '"+dbquery_tOracleInput_3+"'.");
			

            	globalMap.put("tOracleInput_3_QUERY",dbquery_tOracleInput_3);
		    java.sql.ResultSet rs_tOracleInput_3 = null;

		    try {
		    	rs_tOracleInput_3 = stmt_tOracleInput_3.executeQuery(dbquery_tOracleInput_3);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_3 = rs_tOracleInput_3.getMetaData();
		    	int colQtyInRs_tOracleInput_3 = rsmd_tOracleInput_3.getColumnCount();

		    String tmpContent_tOracleInput_3 = null;
		    
		    
		    	log.debug("tOracleInput_3 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_3.next()) {
		        nb_line_tOracleInput_3++;
		        
							if(colQtyInRs_tOracleInput_3 < 1) {
								row4.TRADE_TP = null;
							} else {
	                         		
        	row4.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_3, 1, false);
		                    }
							if(colQtyInRs_tOracleInput_3 < 2) {
								row4.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(2) != null) {
						row4.QUANTITY = rs_tOracleInput_3.getInt(2);
					} else {
				
						row4.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 3) {
								row4.AMOUNT = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(3) != null) {
						row4.AMOUNT = rs_tOracleInput_3.getFloat(3);
					} else {
				
						row4.AMOUNT = null;
					}
		                    }
					
						log.debug("tOracleInput_3 - Retrieving the record " + nb_line_tOracleInput_3 + ".");
					




 



/**
 * [tOracleInput_3 begin ] stop
 */
	
	/**
	 * [tOracleInput_3 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_3";

	

 


	tos_count_tOracleInput_3++;

/**
 * [tOracleInput_3 main ] stop
 */

	
	/**
	 * [tMap_3 main ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

			//row4
			//row4


			
				if(execStat){
					runStat.updateStatOnConnection("row4"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row4 - " + (row4==null? "": row4.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_3 = false;
		  boolean mainRowRejected_tMap_3 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row5" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow5 = false;
       		  	    	
       		  	    	
 							row5Struct row5ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								

								
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row5.lookup( row5HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

  								
		  				
	  								
						
									
	
		  								forceLooprow5 = true;
	  					
  									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
								
								else { // G 20 - G 21
   									forceLooprow5 = true;
			           		  	} // G 21
                    		  	
                    		

							row5Struct row5 = null;
                    		  	 
							

								while ((tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) || forceLooprow5) { // G_TM_M_043

								
									 // CALL close loop of lookup 'row5'
									
                    		  	 
							   
                    		  	 
	       		  	    	row5Struct fromLookup_row5 = null;
							row5 = row5Default;
										 
							
								
								if(!forceLooprow5) { // G 46
								
							
								 
							
								
								fromLookup_row5 = tHash_Lookup_row5.next();

							

							if(fromLookup_row5 != null) {
								row5 = fromLookup_row5;
							}
							
							
							
			  							
								
	                    		  	
		                    
	                    	
	                    		} // G 46
	                    		  	
								forceLooprow5 = false;
									 	
							
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
        // ###############################
        // # Output tables

out4 = null;


// # Output table : 'out4'
count_out4_tMap_3++;

out4_tmp.QUANTITY = Math.abs(row4.QUANTITY-row5.QUANTITY);
out4_tmp.PRICE = (float)0.25*(int)((row5.AMOUNT+row4.AMOUNT)/Math.abs(row5.QUANTITY-row4.QUANTITY)/0.25) ;
out4 = out4_tmp;
log.debug("tMap_3 - Outputting the record " + count_out4_tMap_3 + " of the output table 'out4'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_3 = false;










 


	tos_count_tMap_3++;

/**
 * [tMap_3 main ] stop
 */
// Start of branch "out4"
if(out4 != null) { 



	
	/**
	 * [tOracleOutput_5 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_5";

	

			//out4
			//out4


			
				if(execStat){
					runStat.updateStatOnConnection("out4"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out4 - " + (out4==null? "": out4.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_5 = false;
                        if(out4.QUANTITY == null) {
pstmt_tOracleOutput_5.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_5.setInt(1, out4.QUANTITY);
}

                        if(out4.PRICE == null) {
pstmt_tOracleOutput_5.setNull(2, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_5.setFloat(2, out4.PRICE);
}

                pstmt_tOracleOutput_5.addBatch();
                nb_line_tOracleOutput_5++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_5)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_5++;
            if (batchSize_tOracleOutput_5 > 0 &&  batchSize_tOracleOutput_5 <= batchSizeCounter_tOracleOutput_5) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_5.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_5){
			        java.sql.SQLException ne_tOracleOutput_5 = e_tOracleOutput_5.getNextException(),sqle_tOracleOutput_5=null;
			    	String errormessage_tOracleOutput_5;
					if (ne_tOracleOutput_5 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_5 = new java.sql.SQLException(e_tOracleOutput_5.getMessage() + "\ncaused by: " + ne_tOracleOutput_5.getMessage(), ne_tOracleOutput_5.getSQLState(), ne_tOracleOutput_5.getErrorCode(), ne_tOracleOutput_5);
						errormessage_tOracleOutput_5 = sqle_tOracleOutput_5.getMessage();
					}else{
						errormessage_tOracleOutput_5 = e_tOracleOutput_5.getMessage();
					}
	            	
            log.error("tOracleOutput_5 - "  + (errormessage_tOracleOutput_5) );
	                	System.err.println(errormessage_tOracleOutput_5);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_5 = pstmt_tOracleOutput_5.getUpdateCount();
                    insertedCount_tOracleOutput_5
                += (tmp_batchUpdateCount_tOracleOutput_5!=-1?tmp_batchUpdateCount_tOracleOutput_5:0);
                batchSizeCounter_tOracleOutput_5 = 0;
            }
                commitCounter_tOracleOutput_5++;
                if(commitEvery_tOracleOutput_5 <= commitCounter_tOracleOutput_5) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_5.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_5){
					        java.sql.SQLException ne_tOracleOutput_5 = e_tOracleOutput_5.getNextException(),sqle_tOracleOutput_5=null;
					    	String errormessage_tOracleOutput_5;
							if (ne_tOracleOutput_5 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_5 = new java.sql.SQLException(e_tOracleOutput_5.getMessage() + "\ncaused by: " + ne_tOracleOutput_5.getMessage(), ne_tOracleOutput_5.getSQLState(), ne_tOracleOutput_5.getErrorCode(), ne_tOracleOutput_5);
								errormessage_tOracleOutput_5 = sqle_tOracleOutput_5.getMessage();
							}else{
								errormessage_tOracleOutput_5 = e_tOracleOutput_5.getMessage();
							}
			            	
            log.error("tOracleOutput_5 - "  + (errormessage_tOracleOutput_5) );
			                	System.err.println(errormessage_tOracleOutput_5);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_5 = pstmt_tOracleOutput_5.getUpdateCount();
                            insertedCount_tOracleOutput_5
                        += (tmp_batchUpdateCount_tOracleOutput_5!=-1?tmp_batchUpdateCount_tOracleOutput_5:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_5)  + (" record(s).") );
                    conn_tOracleOutput_5.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_5=0;
                }

 


	tos_count_tOracleOutput_5++;

/**
 * [tOracleOutput_5 main ] stop
 */

} // End of branch "out4"



	
		} // close loop of lookup 'row5' // G_TM_M_043
	



	
	/**
	 * [tOracleInput_3 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_3";

	

}
}finally{
stmt_tOracleInput_3.close();

	if(conn_tOracleInput_3 != null && !conn_tOracleInput_3.isClosed()) {
	
	    		log.debug("tOracleInput_3 - Closing the connection to the database.");
			
			conn_tOracleInput_3.close();
			
	    		log.debug("tOracleInput_3 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_3_NB_LINE",nb_line_tOracleInput_3);
	    		log.debug("tOracleInput_3 - Retrieved records count: "+nb_line_tOracleInput_3 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_3 - "  + ("Done.") );

ok_Hash.put("tOracleInput_3", true);
end_Hash.put("tOracleInput_3", System.currentTimeMillis());




/**
 * [tOracleInput_3 end ] stop
 */

	
	/**
	 * [tMap_3 end ] start
	 */

	

	
	
	currentComponent="tMap_3";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row5 != null) {
						tHash_Lookup_row5.endGet();
					}
					globalMap.remove( "tHash_Lookup_row5" );

					
					
				
// ###############################      
				log.debug("tMap_3 - Written records count in the table 'out4': " + count_out4_tMap_3 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row4"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_3 - "  + ("Done.") );

ok_Hash.put("tMap_3", true);
end_Hash.put("tMap_3", System.currentTimeMillis());




/**
 * [tMap_3 end ] stop
 */

	
	/**
	 * [tOracleOutput_5 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_5";

	
	



	
            try {
            	if (pstmt_tOracleOutput_5 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_5.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_5){
		        java.sql.SQLException ne_tOracleOutput_5 = e_tOracleOutput_5.getNextException(),sqle_tOracleOutput_5=null;
		    	String errormessage_tOracleOutput_5;
				if (ne_tOracleOutput_5 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_5 = new java.sql.SQLException(e_tOracleOutput_5.getMessage() + "\ncaused by: " + ne_tOracleOutput_5.getMessage(), ne_tOracleOutput_5.getSQLState(), ne_tOracleOutput_5.getErrorCode(), ne_tOracleOutput_5);
					errormessage_tOracleOutput_5 = sqle_tOracleOutput_5.getMessage();
				}else{
					errormessage_tOracleOutput_5 = e_tOracleOutput_5.getMessage();
				}
            	
            log.error("tOracleOutput_5 - "  + (errormessage_tOracleOutput_5) );
                	System.err.println(errormessage_tOracleOutput_5);
            	
        	}
        	if (pstmt_tOracleOutput_5 != null) {
            	tmp_batchUpdateCount_tOracleOutput_5 = pstmt_tOracleOutput_5.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_5
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_5!=-1?tmp_batchUpdateCount_tOracleOutput_5:0);
            }
        if(pstmt_tOracleOutput_5 != null) {
			
				pstmt_tOracleOutput_5.close();
			
        }
		if(commitCounter_tOracleOutput_5 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_5)  + (" record(s).") );
		    conn_tOracleOutput_5.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_5 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_5", true);
   	


	
	nb_line_deleted_tOracleOutput_5=nb_line_deleted_tOracleOutput_5+ deletedCount_tOracleOutput_5;
	nb_line_update_tOracleOutput_5=nb_line_update_tOracleOutput_5 + updatedCount_tOracleOutput_5;
	nb_line_inserted_tOracleOutput_5=nb_line_inserted_tOracleOutput_5 + insertedCount_tOracleOutput_5;
	nb_line_rejected_tOracleOutput_5=nb_line_rejected_tOracleOutput_5 + rejectedCount_tOracleOutput_5;
	
        globalMap.put("tOracleOutput_5_NB_LINE",nb_line_tOracleOutput_5);
        globalMap.put("tOracleOutput_5_NB_LINE_UPDATED",nb_line_update_tOracleOutput_5);
        globalMap.put("tOracleOutput_5_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_5);
        globalMap.put("tOracleOutput_5_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_5);
        globalMap.put("tOracleOutput_5_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_5);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_5)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("out4"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_5", true);
end_Hash.put("tOracleOutput_5", System.currentTimeMillis());




/**
 * [tOracleOutput_5 end ] stop
 */






				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_3:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk3", 0, "ok");
								} 
							
							tOracleInput_5Process(globalMap); 
						



	
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
				
					     			//free memory for "tMap_3"
					     			globalMap.remove("tHash_Lookup_row5"); 
				     			
				try{
					
	
	/**
	 * [tOracleInput_3 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_3";

	

 



/**
 * [tOracleInput_3 finally ] stop
 */

	
	/**
	 * [tMap_3 finally ] start
	 */

	

	
	
	currentComponent="tMap_3";

	

 



/**
 * [tMap_3 finally ] stop
 */

	
	/**
	 * [tOracleOutput_5 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_5";

	



	
		if(resourceMap.get("finish_tOracleOutput_5")==null){
			if(resourceMap.get("conn_tOracleOutput_5")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_5 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_5");
					
					
            		
					ctn_tOracleOutput_5.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_5 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_5) {
					String errorMessage_tOracleOutput_5 = "failed to close the connection in tOracleOutput_5 :" + sqlEx_tOracleOutput_5.getMessage();
					
            log.error("tOracleOutput_5 - "  + (errorMessage_tOracleOutput_5) );
					System.err.println(errorMessage_tOracleOutput_5);
				}
			}
		}
	

 



/**
 * [tOracleOutput_5 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_3_SUBPROCESS_STATE", 1);
	}
	


public static class out5Struct implements routines.system.IPersistableRow<out5Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
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
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
    public int compareTo(out5Struct other) {

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

public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
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
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
    public int compareTo(row6Struct other) {

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
public void tOracleInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_5_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row6Struct row6 = new row6Struct();
out5Struct out5 = new out5Struct();





	
	/**
	 * [tOracleOutput_6 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_6", false);
		start_Hash.put("tOracleOutput_6", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_6";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out5" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_6 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_6{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_6 = new StringBuilder();
            log4jParamters_tOracleOutput_6.append("Parameters:");
                    log4jParamters_tOracleOutput_6.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("TABLE" + " = " + "\"TRADE_TP\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_6.append(" | ");
                    log4jParamters_tOracleOutput_6.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_6.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + (log4jParamters_tOracleOutput_6) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_6().limitLog4jByte();






    int nb_line_tOracleOutput_6 = 0;
    int nb_line_update_tOracleOutput_6 = 0;
    int nb_line_inserted_tOracleOutput_6 = 0;
    int nb_line_deleted_tOracleOutput_6 = 0;
    int nb_line_rejected_tOracleOutput_6 = 0;

    int tmp_batchUpdateCount_tOracleOutput_6 = 0;

    int deletedCount_tOracleOutput_6=0;
    int updatedCount_tOracleOutput_6=0;
    int insertedCount_tOracleOutput_6=0;
    int rejectedCount_tOracleOutput_6=0;

    boolean whetherReject_tOracleOutput_6 = false;

    java.sql.Connection conn_tOracleOutput_6 = null;

    //optional table
    String dbschema_tOracleOutput_6 = null;
    String tableName_tOracleOutput_6 = null;
                    String driverClass_tOracleOutput_6 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_6)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_6);
                String url_tOracleOutput_6 = null;
                    url_tOracleOutput_6 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_6 = "hr";
 
	final String decryptedPassword_tOracleOutput_6 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_6 = decryptedPassword_tOracleOutput_6;
                dbschema_tOracleOutput_6 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection attempts to '")  + (url_tOracleOutput_6)  + ("' with the username '")  + (dbUser_tOracleOutput_6)  + ("'.") );

                    conn_tOracleOutput_6 = java.sql.DriverManager.getConnection(url_tOracleOutput_6, dbUser_tOracleOutput_6, dbPwd_tOracleOutput_6);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection to '")  + (url_tOracleOutput_6)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_6", conn_tOracleOutput_6);
            conn_tOracleOutput_6.setAutoCommit(false);
            int commitEvery_tOracleOutput_6 = 10000;
            int commitCounter_tOracleOutput_6 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_6.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_6 = 10000;
        int batchSizeCounter_tOracleOutput_6=0;
        int count_tOracleOutput_6=0;

        if(dbschema_tOracleOutput_6 == null || dbschema_tOracleOutput_6.trim().length() == 0) {
            tableName_tOracleOutput_6 = ("TRADE_TP");
        } else {
            tableName_tOracleOutput_6 = dbschema_tOracleOutput_6 + "." + ("TRADE_TP");
        }
                                String tableNameForSearch_tOracleOutput_6= "" + ((String)"TRADE_TP") + "";
String dbschemaForSearch_tOracleOutput_6= null;
if(dbschema_tOracleOutput_6== null || dbschema_tOracleOutput_6.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_6= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_6= dbschema_tOracleOutput_6.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_6 = conn_tOracleOutput_6.getMetaData();
                                if(tableNameForSearch_tOracleOutput_6.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_6 = tableNameForSearch_tOracleOutput_6.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_6 = tableNameForSearch_tOracleOutput_6.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_6 = dbMetaData_tOracleOutput_6.getTables(null, dbschemaForSearch_tOracleOutput_6, tableNameForSearch_tOracleOutput_6, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_6 = false;
                                if(rsTable_tOracleOutput_6.next()) {
                                    whetherExist_tOracleOutput_6 = true;
                                }
                                rsTable_tOracleOutput_6.close();

                                if(whetherExist_tOracleOutput_6) {
                                    java.sql.Statement stmtDrop_tOracleOutput_6 = conn_tOracleOutput_6.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_6)  + ("'.") );
                                    stmtDrop_tOracleOutput_6.execute("DROP TABLE " + tableName_tOracleOutput_6 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_6)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_6.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_6 = conn_tOracleOutput_6.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_6)  + ("'.") );
                                    stmtCreate_tOracleOutput_6.execute("CREATE TABLE " + tableName_tOracleOutput_6 + "(TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_6)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_6.close();
                String insert_tOracleOutput_6 = "INSERT INTO " + tableName_tOracleOutput_6 + " (TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_6 = conn_tOracleOutput_6.prepareStatement(insert_tOracleOutput_6);





 



/**
 * [tOracleOutput_6 begin ] stop
 */



	
	/**
	 * [tMap_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_4", false);
		start_Hash.put("tMap_4", System.currentTimeMillis());
		
	
	currentComponent="tMap_4";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row6" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_4 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_4 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_4{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_4 = new StringBuilder();
            log4jParamters_tMap_4.append("Parameters:");
                    log4jParamters_tMap_4.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_4.append(" | ");
                    log4jParamters_tMap_4.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_4.append(" | ");
                    log4jParamters_tMap_4.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_4.append(" | ");
                    log4jParamters_tMap_4.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_4.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_4 - "  + (log4jParamters_tMap_4) );
    		}
    	}
    	
        new BytesLimit65535_tMap_4().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row6_tMap_4 = 0;
		
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_4__Struct  {
}
Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out5_tMap_4 = 0;
				
out5Struct out5_tmp = new out5Struct();
// ###############################

        
        



        









 



/**
 * [tMap_4 begin ] stop
 */



	
	/**
	 * [tOracleInput_5 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_5", false);
		start_Hash.put("tOracleInput_5", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_5";

	
		int tos_count_tOracleInput_5 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_5 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_5{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_5 = new StringBuilder();
            log4jParamters_tOracleInput_5.append("Parameters:");
                    log4jParamters_tOracleInput_5.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("TABLE" + " = " + "\"TRADEBAL\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("QUERY" + " = " + "\"select case when PRICE > 0 then 'buy' else 'sell' end as TRADE_TP, QUANTITY, PRICE from TRADEBAL\"  ");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
                log4jParamters_tOracleInput_5.append(" | ");
                    log4jParamters_tOracleInput_5.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_5.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_5 - "  + (log4jParamters_tOracleInput_5) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_5().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_5 = 0;
		    java.sql.Connection conn_tOracleInput_5 = null;
				String driverClass_tOracleInput_5 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_5);
				
			String url_tOracleInput_5 = null;
				url_tOracleInput_5 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_5 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_5 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_5 = decryptedPassword_tOracleInput_5;

				
	    		log.debug("tOracleInput_5 - Driver ClassName: "+driverClass_tOracleInput_5+".");
			
	    		log.debug("tOracleInput_5 - Connection attempt to '" + url_tOracleInput_5 + "' with the username '" + dbUser_tOracleInput_5 + "'.");
			
					conn_tOracleInput_5 = java.sql.DriverManager.getConnection(url_tOracleInput_5,dbUser_tOracleInput_5,dbPwd_tOracleInput_5);
	    		log.debug("tOracleInput_5 - Connection to '" + url_tOracleInput_5 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_5 = conn_tOracleInput_5.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_5 = stmtGetTZ_tOracleInput_5.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_5 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_5.next()) {
					sessionTimezone_tOracleInput_5 = rsGetTZ_tOracleInput_5.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_5).setSessionTimeZone(sessionTimezone_tOracleInput_5);
		    
			java.sql.Statement stmt_tOracleInput_5 = conn_tOracleInput_5.createStatement();

		    String dbquery_tOracleInput_5 = "select case when PRICE > 0 then 'buy' else 'sell' end as TRADE_TP, QUANTITY, PRICE from TRADEBAL"
;
			
                log.debug("tOracleInput_5 - Executing the query: '"+dbquery_tOracleInput_5+"'.");
			

            	globalMap.put("tOracleInput_5_QUERY",dbquery_tOracleInput_5);
		    java.sql.ResultSet rs_tOracleInput_5 = null;

		    try {
		    	rs_tOracleInput_5 = stmt_tOracleInput_5.executeQuery(dbquery_tOracleInput_5);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_5 = rs_tOracleInput_5.getMetaData();
		    	int colQtyInRs_tOracleInput_5 = rsmd_tOracleInput_5.getColumnCount();

		    String tmpContent_tOracleInput_5 = null;
		    
		    
		    	log.debug("tOracleInput_5 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_5.next()) {
		        nb_line_tOracleInput_5++;
		        
							if(colQtyInRs_tOracleInput_5 < 1) {
								row6.TRADE_TP = null;
							} else {
	                         		
        	row6.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_5, 1, false);
		                    }
							if(colQtyInRs_tOracleInput_5 < 2) {
								row6.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_5.getObject(2) != null) {
						row6.QUANTITY = rs_tOracleInput_5.getInt(2);
					} else {
				
						row6.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_5 < 3) {
								row6.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_5.getObject(3) != null) {
						row6.PRICE = rs_tOracleInput_5.getFloat(3);
					} else {
				
						row6.PRICE = null;
					}
		                    }
					
						log.debug("tOracleInput_5 - Retrieving the record " + nb_line_tOracleInput_5 + ".");
					




 



/**
 * [tOracleInput_5 begin ] stop
 */
	
	/**
	 * [tOracleInput_5 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_5";

	

 


	tos_count_tOracleInput_5++;

/**
 * [tOracleInput_5 main ] stop
 */

	
	/**
	 * [tMap_4 main ] start
	 */

	

	
	
	currentComponent="tMap_4";

	

			//row6
			//row6


			
				if(execStat){
					runStat.updateStatOnConnection("row6"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row6 - " + (row6==null? "": row6.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_4 = false;
		  boolean mainRowRejected_tMap_4 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
        // ###############################
        // # Output tables

out5 = null;


// # Output table : 'out5'
count_out5_tMap_4++;

out5_tmp.TRADE_TP = row6.TRADE_TP ;
out5_tmp.QUANTITY = row6.QUANTITY ;
out5_tmp.PRICE = row6.PRICE ;
out5 = out5_tmp;
log.debug("tMap_4 - Outputting the record " + count_out5_tMap_4 + " of the output table 'out5'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_4 = false;










 


	tos_count_tMap_4++;

/**
 * [tMap_4 main ] stop
 */
// Start of branch "out5"
if(out5 != null) { 



	
	/**
	 * [tOracleOutput_6 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_6";

	

			//out5
			//out5


			
				if(execStat){
					runStat.updateStatOnConnection("out5"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out5 - " + (out5==null? "": out5.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_6 = false;
                        if(out5.TRADE_TP == null) {
pstmt_tOracleOutput_6.setNull(1, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_6.setString(1, out5.TRADE_TP);
}

                        if(out5.QUANTITY == null) {
pstmt_tOracleOutput_6.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_6.setInt(2, out5.QUANTITY);
}

                        if(out5.PRICE == null) {
pstmt_tOracleOutput_6.setNull(3, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_6.setFloat(3, out5.PRICE);
}

                pstmt_tOracleOutput_6.addBatch();
                nb_line_tOracleOutput_6++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_6)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_6++;
            if (batchSize_tOracleOutput_6 > 0 &&  batchSize_tOracleOutput_6 <= batchSizeCounter_tOracleOutput_6) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_6.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_6){
			        java.sql.SQLException ne_tOracleOutput_6 = e_tOracleOutput_6.getNextException(),sqle_tOracleOutput_6=null;
			    	String errormessage_tOracleOutput_6;
					if (ne_tOracleOutput_6 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_6 = new java.sql.SQLException(e_tOracleOutput_6.getMessage() + "\ncaused by: " + ne_tOracleOutput_6.getMessage(), ne_tOracleOutput_6.getSQLState(), ne_tOracleOutput_6.getErrorCode(), ne_tOracleOutput_6);
						errormessage_tOracleOutput_6 = sqle_tOracleOutput_6.getMessage();
					}else{
						errormessage_tOracleOutput_6 = e_tOracleOutput_6.getMessage();
					}
	            	
            log.error("tOracleOutput_6 - "  + (errormessage_tOracleOutput_6) );
	                	System.err.println(errormessage_tOracleOutput_6);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_6 = pstmt_tOracleOutput_6.getUpdateCount();
                    insertedCount_tOracleOutput_6
                += (tmp_batchUpdateCount_tOracleOutput_6!=-1?tmp_batchUpdateCount_tOracleOutput_6:0);
                batchSizeCounter_tOracleOutput_6 = 0;
            }
                commitCounter_tOracleOutput_6++;
                if(commitEvery_tOracleOutput_6 <= commitCounter_tOracleOutput_6) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_6.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_6){
					        java.sql.SQLException ne_tOracleOutput_6 = e_tOracleOutput_6.getNextException(),sqle_tOracleOutput_6=null;
					    	String errormessage_tOracleOutput_6;
							if (ne_tOracleOutput_6 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_6 = new java.sql.SQLException(e_tOracleOutput_6.getMessage() + "\ncaused by: " + ne_tOracleOutput_6.getMessage(), ne_tOracleOutput_6.getSQLState(), ne_tOracleOutput_6.getErrorCode(), ne_tOracleOutput_6);
								errormessage_tOracleOutput_6 = sqle_tOracleOutput_6.getMessage();
							}else{
								errormessage_tOracleOutput_6 = e_tOracleOutput_6.getMessage();
							}
			            	
            log.error("tOracleOutput_6 - "  + (errormessage_tOracleOutput_6) );
			                	System.err.println(errormessage_tOracleOutput_6);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_6 = pstmt_tOracleOutput_6.getUpdateCount();
                            insertedCount_tOracleOutput_6
                        += (tmp_batchUpdateCount_tOracleOutput_6!=-1?tmp_batchUpdateCount_tOracleOutput_6:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_6)  + (" record(s).") );
                    conn_tOracleOutput_6.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_6=0;
                }

 


	tos_count_tOracleOutput_6++;

/**
 * [tOracleOutput_6 main ] stop
 */

} // End of branch "out5"







	
	/**
	 * [tOracleInput_5 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_5";

	

}
}finally{
stmt_tOracleInput_5.close();

	if(conn_tOracleInput_5 != null && !conn_tOracleInput_5.isClosed()) {
	
	    		log.debug("tOracleInput_5 - Closing the connection to the database.");
			
			conn_tOracleInput_5.close();
			
	    		log.debug("tOracleInput_5 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_5_NB_LINE",nb_line_tOracleInput_5);
	    		log.debug("tOracleInput_5 - Retrieved records count: "+nb_line_tOracleInput_5 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_5 - "  + ("Done.") );

ok_Hash.put("tOracleInput_5", true);
end_Hash.put("tOracleInput_5", System.currentTimeMillis());




/**
 * [tOracleInput_5 end ] stop
 */

	
	/**
	 * [tMap_4 end ] start
	 */

	

	
	
	currentComponent="tMap_4";

	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_4 - Written records count in the table 'out5': " + count_out5_tMap_4 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row6"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_4 - "  + ("Done.") );

ok_Hash.put("tMap_4", true);
end_Hash.put("tMap_4", System.currentTimeMillis());




/**
 * [tMap_4 end ] stop
 */

	
	/**
	 * [tOracleOutput_6 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_6";

	
	



	
            try {
            	if (pstmt_tOracleOutput_6 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_6.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_6){
		        java.sql.SQLException ne_tOracleOutput_6 = e_tOracleOutput_6.getNextException(),sqle_tOracleOutput_6=null;
		    	String errormessage_tOracleOutput_6;
				if (ne_tOracleOutput_6 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_6 = new java.sql.SQLException(e_tOracleOutput_6.getMessage() + "\ncaused by: " + ne_tOracleOutput_6.getMessage(), ne_tOracleOutput_6.getSQLState(), ne_tOracleOutput_6.getErrorCode(), ne_tOracleOutput_6);
					errormessage_tOracleOutput_6 = sqle_tOracleOutput_6.getMessage();
				}else{
					errormessage_tOracleOutput_6 = e_tOracleOutput_6.getMessage();
				}
            	
            log.error("tOracleOutput_6 - "  + (errormessage_tOracleOutput_6) );
                	System.err.println(errormessage_tOracleOutput_6);
            	
        	}
        	if (pstmt_tOracleOutput_6 != null) {
            	tmp_batchUpdateCount_tOracleOutput_6 = pstmt_tOracleOutput_6.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_6
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_6!=-1?tmp_batchUpdateCount_tOracleOutput_6:0);
            }
        if(pstmt_tOracleOutput_6 != null) {
			
				pstmt_tOracleOutput_6.close();
			
        }
		if(commitCounter_tOracleOutput_6 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_6)  + (" record(s).") );
		    conn_tOracleOutput_6.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_6 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_6", true);
   	


	
	nb_line_deleted_tOracleOutput_6=nb_line_deleted_tOracleOutput_6+ deletedCount_tOracleOutput_6;
	nb_line_update_tOracleOutput_6=nb_line_update_tOracleOutput_6 + updatedCount_tOracleOutput_6;
	nb_line_inserted_tOracleOutput_6=nb_line_inserted_tOracleOutput_6 + insertedCount_tOracleOutput_6;
	nb_line_rejected_tOracleOutput_6=nb_line_rejected_tOracleOutput_6 + rejectedCount_tOracleOutput_6;
	
        globalMap.put("tOracleOutput_6_NB_LINE",nb_line_tOracleOutput_6);
        globalMap.put("tOracleOutput_6_NB_LINE_UPDATED",nb_line_update_tOracleOutput_6);
        globalMap.put("tOracleOutput_6_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_6);
        globalMap.put("tOracleOutput_6_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_6);
        globalMap.put("tOracleOutput_6_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_6);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_6)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("out5"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_6", true);
end_Hash.put("tOracleOutput_6", System.currentTimeMillis());




/**
 * [tOracleOutput_6 end ] stop
 */






				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_5:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk4", 0, "ok");
								} 
							
							tOracleInput_6Process(globalMap); 
						



	
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
	 * [tOracleInput_5 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_5";

	

 



/**
 * [tOracleInput_5 finally ] stop
 */

	
	/**
	 * [tMap_4 finally ] start
	 */

	

	
	
	currentComponent="tMap_4";

	

 



/**
 * [tMap_4 finally ] stop
 */

	
	/**
	 * [tOracleOutput_6 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_6";

	



	
		if(resourceMap.get("finish_tOracleOutput_6")==null){
			if(resourceMap.get("conn_tOracleOutput_6")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_6 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_6");
					
					
            		
					ctn_tOracleOutput_6.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_6 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_6) {
					String errorMessage_tOracleOutput_6 = "failed to close the connection in tOracleOutput_6 :" + sqlEx_tOracleOutput_6.getMessage();
					
            log.error("tOracleOutput_6 - "  + (errorMessage_tOracleOutput_6) );
					System.err.println(errorMessage_tOracleOutput_6);
				}
			}
		}
	

 



/**
 * [tOracleOutput_6 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_5_SUBPROCESS_STATE", 1);
	}
	


public static class out6Struct implements routines.system.IPersistableRow<out6Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

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
    public int compareTo(out6Struct other) {

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

public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
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
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
    public int compareTo(row8Struct other) {

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

public static class after_tOracleInput_6Struct implements routines.system.IPersistableRow<after_tOracleInput_6Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
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
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",PRICE="+String.valueOf(PRICE));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
    public int compareTo(after_tOracleInput_6Struct other) {

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
public void tOracleInput_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_6_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;


		tRowGenerator_2Process(globalMap);

		row8Struct row8 = new row8Struct();
out6Struct out6 = new out6Struct();





	
	/**
	 * [tOracleOutput_8 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_8", false);
		start_Hash.put("tOracleOutput_8", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_8";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out6" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_8 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_8{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_8 = new StringBuilder();
            log4jParamters_tOracleOutput_8.append("Parameters:");
                    log4jParamters_tOracleOutput_8.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("TABLE" + " = " + "\"TRADE_FINAL\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_8.append(" | ");
                    log4jParamters_tOracleOutput_8.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_8.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + (log4jParamters_tOracleOutput_8) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_8().limitLog4jByte();






    int nb_line_tOracleOutput_8 = 0;
    int nb_line_update_tOracleOutput_8 = 0;
    int nb_line_inserted_tOracleOutput_8 = 0;
    int nb_line_deleted_tOracleOutput_8 = 0;
    int nb_line_rejected_tOracleOutput_8 = 0;

    int tmp_batchUpdateCount_tOracleOutput_8 = 0;

    int deletedCount_tOracleOutput_8=0;
    int updatedCount_tOracleOutput_8=0;
    int insertedCount_tOracleOutput_8=0;
    int rejectedCount_tOracleOutput_8=0;

    boolean whetherReject_tOracleOutput_8 = false;

    java.sql.Connection conn_tOracleOutput_8 = null;

    //optional table
    String dbschema_tOracleOutput_8 = null;
    String tableName_tOracleOutput_8 = null;
                    String driverClass_tOracleOutput_8 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_8)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_8);
                String url_tOracleOutput_8 = null;
                    url_tOracleOutput_8 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_8 = "hr";
 
	final String decryptedPassword_tOracleOutput_8 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_8 = decryptedPassword_tOracleOutput_8;
                dbschema_tOracleOutput_8 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection attempts to '")  + (url_tOracleOutput_8)  + ("' with the username '")  + (dbUser_tOracleOutput_8)  + ("'.") );

                    conn_tOracleOutput_8 = java.sql.DriverManager.getConnection(url_tOracleOutput_8, dbUser_tOracleOutput_8, dbPwd_tOracleOutput_8);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection to '")  + (url_tOracleOutput_8)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_8", conn_tOracleOutput_8);
            conn_tOracleOutput_8.setAutoCommit(false);
            int commitEvery_tOracleOutput_8 = 10000;
            int commitCounter_tOracleOutput_8 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_8.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_8 = 10000;
        int batchSizeCounter_tOracleOutput_8=0;
        int count_tOracleOutput_8=0;

        if(dbschema_tOracleOutput_8 == null || dbschema_tOracleOutput_8.trim().length() == 0) {
            tableName_tOracleOutput_8 = ("TRADE_FINAL");
        } else {
            tableName_tOracleOutput_8 = dbschema_tOracleOutput_8 + "." + ("TRADE_FINAL");
        }
                                String tableNameForSearch_tOracleOutput_8= "" + ((String)"TRADE_FINAL") + "";
String dbschemaForSearch_tOracleOutput_8= null;
if(dbschema_tOracleOutput_8== null || dbschema_tOracleOutput_8.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_8= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_8= dbschema_tOracleOutput_8.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_8 = conn_tOracleOutput_8.getMetaData();
                                if(tableNameForSearch_tOracleOutput_8.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_8 = tableNameForSearch_tOracleOutput_8.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_8 = tableNameForSearch_tOracleOutput_8.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_8 = dbMetaData_tOracleOutput_8.getTables(null, dbschemaForSearch_tOracleOutput_8, tableNameForSearch_tOracleOutput_8, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_8 = false;
                                if(rsTable_tOracleOutput_8.next()) {
                                    whetherExist_tOracleOutput_8 = true;
                                }
                                rsTable_tOracleOutput_8.close();

                                if(whetherExist_tOracleOutput_8) {
                                    java.sql.Statement stmtDrop_tOracleOutput_8 = conn_tOracleOutput_8.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_8)  + ("'.") );
                                    stmtDrop_tOracleOutput_8.execute("DROP TABLE " + tableName_tOracleOutput_8 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_8)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_8.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_8 = conn_tOracleOutput_8.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_8)  + ("'.") );
                                    stmtCreate_tOracleOutput_8.execute("CREATE TABLE " + tableName_tOracleOutput_8 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_8)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_8.close();
                String insert_tOracleOutput_8 = "INSERT INTO " + tableName_tOracleOutput_8 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_8 = conn_tOracleOutput_8.prepareStatement(insert_tOracleOutput_8);





 



/**
 * [tOracleOutput_8 begin ] stop
 */



	
	/**
	 * [tMap_5 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_5", false);
		start_Hash.put("tMap_5", System.currentTimeMillis());
		
	
	currentComponent="tMap_5";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row8" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tMap_5 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tMap_5 - "  + ("Start to work.") );
    	class BytesLimit65535_tMap_5{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tMap_5 = new StringBuilder();
            log4jParamters_tMap_5.append("Parameters:");
                    log4jParamters_tMap_5.append("LINK_STYLE" + " = " + "AUTO");
                log4jParamters_tMap_5.append(" | ");
                    log4jParamters_tMap_5.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
                log4jParamters_tMap_5.append(" | ");
                    log4jParamters_tMap_5.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
                log4jParamters_tMap_5.append(" | ");
                    log4jParamters_tMap_5.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
                log4jParamters_tMap_5.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tMap_5 - "  + (log4jParamters_tMap_5) );
    		}
    	}
    	
        new BytesLimit65535_tMap_5().limitLog4jByte();




// ###############################
// # Lookup's keys initialization
		int count_row8_tMap_5 = 0;
		
		int count_row9_tMap_5 = 0;
		
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) 
					globalMap.get( "tHash_Lookup_row9" ))
					;					
					
	
		tHash_Lookup_row9.initGet();
	

row9Struct row9HashKey = new row9Struct();
row9Struct row9Default = new row9Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_5__Struct  {
}
Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out6_tMap_5 = 0;
				
out6Struct out6_tmp = new out6Struct();
// ###############################

        
        



        









 



/**
 * [tMap_5 begin ] stop
 */



	
	/**
	 * [tOracleInput_6 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_6", false);
		start_Hash.put("tOracleInput_6", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_6";

	
		int tos_count_tOracleInput_6 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_6 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_6{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_6 = new StringBuilder();
            log4jParamters_tOracleInput_6.append("Parameters:");
                    log4jParamters_tOracleInput_6.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("TABLE" + " = " + "\"TRADE_TP\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("QUERY" + " = " + "\"SELECT    TRADE_TP.TRADE_TP,    TRADE_TP.QUANTITY,    TRADE_TP.PRICE FROM TRADE_TP\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
                log4jParamters_tOracleInput_6.append(" | ");
                    log4jParamters_tOracleInput_6.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_6.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_6 - "  + (log4jParamters_tOracleInput_6) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_6().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_6 = 0;
		    java.sql.Connection conn_tOracleInput_6 = null;
				String driverClass_tOracleInput_6 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_6);
				
			String url_tOracleInput_6 = null;
				url_tOracleInput_6 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_6 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_6 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_6 = decryptedPassword_tOracleInput_6;

				
	    		log.debug("tOracleInput_6 - Driver ClassName: "+driverClass_tOracleInput_6+".");
			
	    		log.debug("tOracleInput_6 - Connection attempt to '" + url_tOracleInput_6 + "' with the username '" + dbUser_tOracleInput_6 + "'.");
			
					conn_tOracleInput_6 = java.sql.DriverManager.getConnection(url_tOracleInput_6,dbUser_tOracleInput_6,dbPwd_tOracleInput_6);
	    		log.debug("tOracleInput_6 - Connection to '" + url_tOracleInput_6 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_6 = conn_tOracleInput_6.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_6 = stmtGetTZ_tOracleInput_6.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_6 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_6.next()) {
					sessionTimezone_tOracleInput_6 = rsGetTZ_tOracleInput_6.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_6).setSessionTimeZone(sessionTimezone_tOracleInput_6);
		    
			java.sql.Statement stmt_tOracleInput_6 = conn_tOracleInput_6.createStatement();

		    String dbquery_tOracleInput_6 = "SELECT \n  TRADE_TP.TRADE_TP, \n  TRADE_TP.QUANTITY, \n  TRADE_TP.PRICE\nFROM TRADE_TP";
			
                log.debug("tOracleInput_6 - Executing the query: '"+dbquery_tOracleInput_6+"'.");
			

            	globalMap.put("tOracleInput_6_QUERY",dbquery_tOracleInput_6);
		    java.sql.ResultSet rs_tOracleInput_6 = null;

		    try {
		    	rs_tOracleInput_6 = stmt_tOracleInput_6.executeQuery(dbquery_tOracleInput_6);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_6 = rs_tOracleInput_6.getMetaData();
		    	int colQtyInRs_tOracleInput_6 = rsmd_tOracleInput_6.getColumnCount();

		    String tmpContent_tOracleInput_6 = null;
		    
		    
		    	log.debug("tOracleInput_6 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_6.next()) {
		        nb_line_tOracleInput_6++;
		        
							if(colQtyInRs_tOracleInput_6 < 1) {
								row8.TRADE_TP = null;
							} else {
	                         		
        	row8.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_6, 1, false);
		                    }
							if(colQtyInRs_tOracleInput_6 < 2) {
								row8.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_6.getObject(2) != null) {
						row8.QUANTITY = rs_tOracleInput_6.getInt(2);
					} else {
				
						row8.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_6 < 3) {
								row8.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_6.getObject(3) != null) {
						row8.PRICE = rs_tOracleInput_6.getFloat(3);
					} else {
				
						row8.PRICE = null;
					}
		                    }
					
						log.debug("tOracleInput_6 - Retrieving the record " + nb_line_tOracleInput_6 + ".");
					




 



/**
 * [tOracleInput_6 begin ] stop
 */
	
	/**
	 * [tOracleInput_6 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_6";

	

 


	tos_count_tOracleInput_6++;

/**
 * [tOracleInput_6 main ] stop
 */

	
	/**
	 * [tMap_5 main ] start
	 */

	

	
	
	currentComponent="tMap_5";

	

			//row8
			//row8


			
				if(execStat){
					runStat.updateStatOnConnection("row8"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row8 - " + (row8==null? "": row8.toLogString()));
    			}
    		

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;
		
        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_5 = false;
		  boolean mainRowRejected_tMap_5 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row9" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow9 = false;
       		  	    	
       		  	    	
 							row9Struct row9ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_5) { // G_TM_M_020

								

								
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row9.lookup( row9HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row9.hasNext()) { // G_TM_M_090

  								
		  				
	  								
						
									
	
		  								forceLooprow9 = true;
	  					
  									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
								
								else { // G 20 - G 21
   									forceLooprow9 = true;
			           		  	} // G 21
                    		  	
                    		

							row9Struct row9 = null;
                    		  	 
							

								while ((tHash_Lookup_row9 != null && tHash_Lookup_row9.hasNext()) || forceLooprow9) { // G_TM_M_043

								
									 // CALL close loop of lookup 'row9'
									
                    		  	 
							   
                    		  	 
	       		  	    	row9Struct fromLookup_row9 = null;
							row9 = row9Default;
										 
							
								
								if(!forceLooprow9) { // G 46
								
							
								 
							
								
								fromLookup_row9 = tHash_Lookup_row9.next();

							

							if(fromLookup_row9 != null) {
								row9 = fromLookup_row9;
							}
							
							
							
			  							
								
	                    		  	
		                    
	                    	
	                    		} // G 46
	                    		  	
								forceLooprow9 = false;
									 	
							
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
        // ###############################
        // # Output tables

out6 = null;


// # Output table : 'out6'
count_out6_tMap_5++;

out6_tmp.TRADER_ID = row9.TRADER_ID ;
out6_tmp.BROKER_ID = row9.BROKER_ID ;
out6_tmp.TIMESTAMP = row9.TIMESTAMP ;
out6_tmp.COMPANY_ID = row9.COMPANY_ID ;
out6_tmp.SECURITY_TP = row9.SECURITY_TP ;
out6_tmp.TRADE_TP = row8.TRADE_TP ;
out6_tmp.QUANTITY = row8.QUANTITY ;
out6_tmp.PRICE = Math.abs(row8.PRICE) ;
out6 = out6_tmp;
log.debug("tMap_5 - Outputting the record " + count_out6_tMap_5 + " of the output table 'out6'.");

// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_5 = false;










 


	tos_count_tMap_5++;

/**
 * [tMap_5 main ] stop
 */
// Start of branch "out6"
if(out6 != null) { 



	
	/**
	 * [tOracleOutput_8 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_8";

	

			//out6
			//out6


			
				if(execStat){
					runStat.updateStatOnConnection("out6"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out6 - " + (out6==null? "": out6.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_8 = false;
                        if(out6.TRADER_ID == null) {
pstmt_tOracleOutput_8.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_8.setInt(1, out6.TRADER_ID);
}

                        if(out6.BROKER_ID == null) {
pstmt_tOracleOutput_8.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_8.setInt(2, out6.BROKER_ID);
}

                        if(out6.TIMESTAMP != null) {
pstmt_tOracleOutput_8.setObject(3, new java.sql.Timestamp(out6.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_8.setNull(3, java.sql.Types.DATE);
}

                        if(out6.COMPANY_ID == null) {
pstmt_tOracleOutput_8.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_8.setInt(4, out6.COMPANY_ID);
}

                        if(out6.SECURITY_TP == null) {
pstmt_tOracleOutput_8.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_8.setString(5, out6.SECURITY_TP);
}

                        if(out6.TRADE_TP == null) {
pstmt_tOracleOutput_8.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_8.setString(6, out6.TRADE_TP);
}

                        if(out6.QUANTITY == null) {
pstmt_tOracleOutput_8.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_8.setInt(7, out6.QUANTITY);
}

                        if(out6.PRICE == null) {
pstmt_tOracleOutput_8.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_8.setFloat(8, out6.PRICE);
}

                pstmt_tOracleOutput_8.addBatch();
                nb_line_tOracleOutput_8++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_8)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_8++;
            if (batchSize_tOracleOutput_8 > 0 &&  batchSize_tOracleOutput_8 <= batchSizeCounter_tOracleOutput_8) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_8.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_8){
			        java.sql.SQLException ne_tOracleOutput_8 = e_tOracleOutput_8.getNextException(),sqle_tOracleOutput_8=null;
			    	String errormessage_tOracleOutput_8;
					if (ne_tOracleOutput_8 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_8 = new java.sql.SQLException(e_tOracleOutput_8.getMessage() + "\ncaused by: " + ne_tOracleOutput_8.getMessage(), ne_tOracleOutput_8.getSQLState(), ne_tOracleOutput_8.getErrorCode(), ne_tOracleOutput_8);
						errormessage_tOracleOutput_8 = sqle_tOracleOutput_8.getMessage();
					}else{
						errormessage_tOracleOutput_8 = e_tOracleOutput_8.getMessage();
					}
	            	
            log.error("tOracleOutput_8 - "  + (errormessage_tOracleOutput_8) );
	                	System.err.println(errormessage_tOracleOutput_8);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_8 = pstmt_tOracleOutput_8.getUpdateCount();
                    insertedCount_tOracleOutput_8
                += (tmp_batchUpdateCount_tOracleOutput_8!=-1?tmp_batchUpdateCount_tOracleOutput_8:0);
                batchSizeCounter_tOracleOutput_8 = 0;
            }
                commitCounter_tOracleOutput_8++;
                if(commitEvery_tOracleOutput_8 <= commitCounter_tOracleOutput_8) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_8.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_8){
					        java.sql.SQLException ne_tOracleOutput_8 = e_tOracleOutput_8.getNextException(),sqle_tOracleOutput_8=null;
					    	String errormessage_tOracleOutput_8;
							if (ne_tOracleOutput_8 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_8 = new java.sql.SQLException(e_tOracleOutput_8.getMessage() + "\ncaused by: " + ne_tOracleOutput_8.getMessage(), ne_tOracleOutput_8.getSQLState(), ne_tOracleOutput_8.getErrorCode(), ne_tOracleOutput_8);
								errormessage_tOracleOutput_8 = sqle_tOracleOutput_8.getMessage();
							}else{
								errormessage_tOracleOutput_8 = e_tOracleOutput_8.getMessage();
							}
			            	
            log.error("tOracleOutput_8 - "  + (errormessage_tOracleOutput_8) );
			                	System.err.println(errormessage_tOracleOutput_8);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_8 = pstmt_tOracleOutput_8.getUpdateCount();
                            insertedCount_tOracleOutput_8
                        += (tmp_batchUpdateCount_tOracleOutput_8!=-1?tmp_batchUpdateCount_tOracleOutput_8:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_8)  + (" record(s).") );
                    conn_tOracleOutput_8.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_8=0;
                }

 


	tos_count_tOracleOutput_8++;

/**
 * [tOracleOutput_8 main ] stop
 */

} // End of branch "out6"



	
		} // close loop of lookup 'row9' // G_TM_M_043
	



	
	/**
	 * [tOracleInput_6 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_6";

	

}
}finally{
stmt_tOracleInput_6.close();

	if(conn_tOracleInput_6 != null && !conn_tOracleInput_6.isClosed()) {
	
	    		log.debug("tOracleInput_6 - Closing the connection to the database.");
			
			conn_tOracleInput_6.close();
			
	    		log.debug("tOracleInput_6 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_6_NB_LINE",nb_line_tOracleInput_6);
	    		log.debug("tOracleInput_6 - Retrieved records count: "+nb_line_tOracleInput_6 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_6 - "  + ("Done.") );

ok_Hash.put("tOracleInput_6", true);
end_Hash.put("tOracleInput_6", System.currentTimeMillis());




/**
 * [tOracleInput_6 end ] stop
 */

	
	/**
	 * [tMap_5 end ] start
	 */

	

	
	
	currentComponent="tMap_5";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row9 != null) {
						tHash_Lookup_row9.endGet();
					}
					globalMap.remove( "tHash_Lookup_row9" );

					
					
				
// ###############################      
				log.debug("tMap_5 - Written records count in the table 'out6': " + count_out6_tMap_5 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row8"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tMap_5 - "  + ("Done.") );

ok_Hash.put("tMap_5", true);
end_Hash.put("tMap_5", System.currentTimeMillis());




/**
 * [tMap_5 end ] stop
 */

	
	/**
	 * [tOracleOutput_8 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_8";

	
	



	
            try {
            	if (pstmt_tOracleOutput_8 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_8.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_8){
		        java.sql.SQLException ne_tOracleOutput_8 = e_tOracleOutput_8.getNextException(),sqle_tOracleOutput_8=null;
		    	String errormessage_tOracleOutput_8;
				if (ne_tOracleOutput_8 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_8 = new java.sql.SQLException(e_tOracleOutput_8.getMessage() + "\ncaused by: " + ne_tOracleOutput_8.getMessage(), ne_tOracleOutput_8.getSQLState(), ne_tOracleOutput_8.getErrorCode(), ne_tOracleOutput_8);
					errormessage_tOracleOutput_8 = sqle_tOracleOutput_8.getMessage();
				}else{
					errormessage_tOracleOutput_8 = e_tOracleOutput_8.getMessage();
				}
            	
            log.error("tOracleOutput_8 - "  + (errormessage_tOracleOutput_8) );
                	System.err.println(errormessage_tOracleOutput_8);
            	
        	}
        	if (pstmt_tOracleOutput_8 != null) {
            	tmp_batchUpdateCount_tOracleOutput_8 = pstmt_tOracleOutput_8.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_8
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_8!=-1?tmp_batchUpdateCount_tOracleOutput_8:0);
            }
        if(pstmt_tOracleOutput_8 != null) {
			
				pstmt_tOracleOutput_8.close();
			
        }
		if(commitCounter_tOracleOutput_8 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_8)  + (" record(s).") );
		    conn_tOracleOutput_8.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_8 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_8", true);
   	


	
	nb_line_deleted_tOracleOutput_8=nb_line_deleted_tOracleOutput_8+ deletedCount_tOracleOutput_8;
	nb_line_update_tOracleOutput_8=nb_line_update_tOracleOutput_8 + updatedCount_tOracleOutput_8;
	nb_line_inserted_tOracleOutput_8=nb_line_inserted_tOracleOutput_8 + insertedCount_tOracleOutput_8;
	nb_line_rejected_tOracleOutput_8=nb_line_rejected_tOracleOutput_8 + rejectedCount_tOracleOutput_8;
	
        globalMap.put("tOracleOutput_8_NB_LINE",nb_line_tOracleOutput_8);
        globalMap.put("tOracleOutput_8_NB_LINE_UPDATED",nb_line_update_tOracleOutput_8);
        globalMap.put("tOracleOutput_8_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_8);
        globalMap.put("tOracleOutput_8_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_8);
        globalMap.put("tOracleOutput_8_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_8);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_8)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("out6"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_8", true);
end_Hash.put("tOracleOutput_8", System.currentTimeMillis());




/**
 * [tOracleOutput_8 end ] stop
 */






				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_6:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk5", 0, "ok");
								} 
							
							tOracleInput_9Process(globalMap); 
						



	
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
				
					     			//free memory for "tMap_5"
					     			globalMap.remove("tHash_Lookup_row9"); 
				     			
				try{
					
	
	/**
	 * [tOracleInput_6 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_6";

	

 



/**
 * [tOracleInput_6 finally ] stop
 */

	
	/**
	 * [tMap_5 finally ] start
	 */

	

	
	
	currentComponent="tMap_5";

	

 



/**
 * [tMap_5 finally ] stop
 */

	
	/**
	 * [tOracleOutput_8 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_8";

	



	
		if(resourceMap.get("finish_tOracleOutput_8")==null){
			if(resourceMap.get("conn_tOracleOutput_8")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_8 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_8");
					
					
            		
					ctn_tOracleOutput_8.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_8 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_8) {
					String errorMessage_tOracleOutput_8 = "failed to close the connection in tOracleOutput_8 :" + sqlEx_tOracleOutput_8.getMessage();
					
            log.error("tOracleOutput_8 - "  + (errorMessage_tOracleOutput_8) );
					System.err.println(errorMessage_tOracleOutput_8);
				}
			}
		}
	

 



/**
 * [tOracleOutput_8 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_6_SUBPROCESS_STATE", 1);
	}
	


public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

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
    public int compareTo(row11Struct other) {

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
public void tOracleInput_9Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_9_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row11Struct row11 = new row11Struct();




	
	/**
	 * [tOracleOutput_10 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_10", false);
		start_Hash.put("tOracleOutput_10", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_10";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row11" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_10 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_10{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_10 = new StringBuilder();
            log4jParamters_tOracleOutput_10.append("Parameters:");
                    log4jParamters_tOracleOutput_10.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("TABLE" + " = " + "\"STAGE3\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_10.append(" | ");
                    log4jParamters_tOracleOutput_10.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_10.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + (log4jParamters_tOracleOutput_10) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_10().limitLog4jByte();






    int nb_line_tOracleOutput_10 = 0;
    int nb_line_update_tOracleOutput_10 = 0;
    int nb_line_inserted_tOracleOutput_10 = 0;
    int nb_line_deleted_tOracleOutput_10 = 0;
    int nb_line_rejected_tOracleOutput_10 = 0;

    int tmp_batchUpdateCount_tOracleOutput_10 = 0;

    int deletedCount_tOracleOutput_10=0;
    int updatedCount_tOracleOutput_10=0;
    int insertedCount_tOracleOutput_10=0;
    int rejectedCount_tOracleOutput_10=0;

    boolean whetherReject_tOracleOutput_10 = false;

    java.sql.Connection conn_tOracleOutput_10 = null;

    //optional table
    String dbschema_tOracleOutput_10 = null;
    String tableName_tOracleOutput_10 = null;
                    String driverClass_tOracleOutput_10 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_10)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_10);
                String url_tOracleOutput_10 = null;
                    url_tOracleOutput_10 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_10 = "hr";
 
	final String decryptedPassword_tOracleOutput_10 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_10 = decryptedPassword_tOracleOutput_10;
                dbschema_tOracleOutput_10 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection attempts to '")  + (url_tOracleOutput_10)  + ("' with the username '")  + (dbUser_tOracleOutput_10)  + ("'.") );

                    conn_tOracleOutput_10 = java.sql.DriverManager.getConnection(url_tOracleOutput_10, dbUser_tOracleOutput_10, dbPwd_tOracleOutput_10);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection to '")  + (url_tOracleOutput_10)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_10", conn_tOracleOutput_10);
            conn_tOracleOutput_10.setAutoCommit(false);
            int commitEvery_tOracleOutput_10 = 10000;
            int commitCounter_tOracleOutput_10 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_10.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_10 = 10000;
        int batchSizeCounter_tOracleOutput_10=0;
        int count_tOracleOutput_10=0;

        if(dbschema_tOracleOutput_10 == null || dbschema_tOracleOutput_10.trim().length() == 0) {
            tableName_tOracleOutput_10 = ("STAGE3");
        } else {
            tableName_tOracleOutput_10 = dbschema_tOracleOutput_10 + "." + ("STAGE3");
        }
                                String tableNameForSearch_tOracleOutput_10= "" + ((String)"STAGE3") + "";
String dbschemaForSearch_tOracleOutput_10= null;
if(dbschema_tOracleOutput_10== null || dbschema_tOracleOutput_10.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_10= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_10= dbschema_tOracleOutput_10.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_10 = conn_tOracleOutput_10.getMetaData();
                                if(tableNameForSearch_tOracleOutput_10.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_10 = tableNameForSearch_tOracleOutput_10.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_10 = tableNameForSearch_tOracleOutput_10.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_10 = dbMetaData_tOracleOutput_10.getTables(null, dbschemaForSearch_tOracleOutput_10, tableNameForSearch_tOracleOutput_10, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_10 = false;
                                if(rsTable_tOracleOutput_10.next()) {
                                    whetherExist_tOracleOutput_10 = true;
                                }
                                rsTable_tOracleOutput_10.close();

                                if(!whetherExist_tOracleOutput_10) {
                                    java.sql.Statement stmtCreate_tOracleOutput_10 = conn_tOracleOutput_10.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_10)  + ("'.") );
                                        stmtCreate_tOracleOutput_10.execute("CREATE TABLE " + tableName_tOracleOutput_10 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_10)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_10.close();
                                }
                String insert_tOracleOutput_10 = "INSERT INTO " + tableName_tOracleOutput_10 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_10 = conn_tOracleOutput_10.prepareStatement(insert_tOracleOutput_10);





 



/**
 * [tOracleOutput_10 begin ] stop
 */



	
	/**
	 * [tOracleInput_9 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_9", false);
		start_Hash.put("tOracleInput_9", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_9";

	
		int tos_count_tOracleInput_9 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_9 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_9{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_9 = new StringBuilder();
            log4jParamters_tOracleInput_9.append("Parameters:");
                    log4jParamters_tOracleInput_9.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("TABLE" + " = " + "\"TRADE_FINAL\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("QUERY" + " = " + "\"SELECT    TRADE_FINAL.TRADER_ID,    TRADE_FINAL.BROKER_ID,    TRADE_FINAL.TIMESTAMP,    TRADE_FINAL.COMPANY_ID,    TRADE_FINAL.SECURITY_TP,    TRADE_FINAL.TRADE_TP,    TRADE_FINAL.QUANTITY,    TRADE_FINAL.PRICE FROM TRADE_FINAL\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("BROKER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TIMESTAMP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("COMPANY_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("SECURITY_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
                log4jParamters_tOracleInput_9.append(" | ");
                    log4jParamters_tOracleInput_9.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_9.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_9 - "  + (log4jParamters_tOracleInput_9) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_9().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_9 = 0;
		    java.sql.Connection conn_tOracleInput_9 = null;
				String driverClass_tOracleInput_9 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_9);
				
			String url_tOracleInput_9 = null;
				url_tOracleInput_9 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_9 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_9 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_9 = decryptedPassword_tOracleInput_9;

				
	    		log.debug("tOracleInput_9 - Driver ClassName: "+driverClass_tOracleInput_9+".");
			
	    		log.debug("tOracleInput_9 - Connection attempt to '" + url_tOracleInput_9 + "' with the username '" + dbUser_tOracleInput_9 + "'.");
			
					conn_tOracleInput_9 = java.sql.DriverManager.getConnection(url_tOracleInput_9,dbUser_tOracleInput_9,dbPwd_tOracleInput_9);
	    		log.debug("tOracleInput_9 - Connection to '" + url_tOracleInput_9 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_9 = conn_tOracleInput_9.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_9 = stmtGetTZ_tOracleInput_9.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_9 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_9.next()) {
					sessionTimezone_tOracleInput_9 = rsGetTZ_tOracleInput_9.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_9).setSessionTimeZone(sessionTimezone_tOracleInput_9);
		    
			java.sql.Statement stmt_tOracleInput_9 = conn_tOracleInput_9.createStatement();

		    String dbquery_tOracleInput_9 = "SELECT \n  TRADE_FINAL.TRADER_ID, \n  TRADE_FINAL.BROKER_ID, \n  TRADE_FINAL.TIMESTAMP, \n  TRADE_FINAL.COMPANY_ID, \n  TRADE_FINAL.SECURITY_TP, \n  TRADE_FINAL.TRADE_TP, \n  TRADE_FINAL.QUANTITY, \n  TRADE_FINAL.PRICE\nFROM TRADE_FINAL";
			
                log.debug("tOracleInput_9 - Executing the query: '"+dbquery_tOracleInput_9+"'.");
			

            	globalMap.put("tOracleInput_9_QUERY",dbquery_tOracleInput_9);
		    java.sql.ResultSet rs_tOracleInput_9 = null;

		    try {
		    	rs_tOracleInput_9 = stmt_tOracleInput_9.executeQuery(dbquery_tOracleInput_9);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_9 = rs_tOracleInput_9.getMetaData();
		    	int colQtyInRs_tOracleInput_9 = rsmd_tOracleInput_9.getColumnCount();

		    String tmpContent_tOracleInput_9 = null;
		    
		    
		    	log.debug("tOracleInput_9 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_9.next()) {
		        nb_line_tOracleInput_9++;
		        
							if(colQtyInRs_tOracleInput_9 < 1) {
								row11.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_9.getObject(1) != null) {
						row11.TRADER_ID = rs_tOracleInput_9.getInt(1);
					} else {
				
						row11.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_9 < 2) {
								row11.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_9.getObject(2) != null) {
						row11.BROKER_ID = rs_tOracleInput_9.getInt(2);
					} else {
				
						row11.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_9 < 3) {
								row11.TIMESTAMP = null;
							} else {
										
			row11.TIMESTAMP = routines.system.JDBCUtil.getDate(rs_tOracleInput_9, 3);
		                    }
							if(colQtyInRs_tOracleInput_9 < 4) {
								row11.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_9.getObject(4) != null) {
						row11.COMPANY_ID = rs_tOracleInput_9.getInt(4);
					} else {
				
						row11.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_9 < 5) {
								row11.SECURITY_TP = null;
							} else {
	                         		
        	row11.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_9, 5, false);
		                    }
							if(colQtyInRs_tOracleInput_9 < 6) {
								row11.TRADE_TP = null;
							} else {
	                         		
        	row11.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_9, 6, false);
		                    }
							if(colQtyInRs_tOracleInput_9 < 7) {
								row11.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_9.getObject(7) != null) {
						row11.QUANTITY = rs_tOracleInput_9.getInt(7);
					} else {
				
						row11.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_9 < 8) {
								row11.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_9.getObject(8) != null) {
						row11.PRICE = rs_tOracleInput_9.getFloat(8);
					} else {
				
						row11.PRICE = null;
					}
		                    }
					
						log.debug("tOracleInput_9 - Retrieving the record " + nb_line_tOracleInput_9 + ".");
					




 



/**
 * [tOracleInput_9 begin ] stop
 */
	
	/**
	 * [tOracleInput_9 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_9";

	

 


	tos_count_tOracleInput_9++;

/**
 * [tOracleInput_9 main ] stop
 */

	
	/**
	 * [tOracleOutput_10 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_10";

	

			//row11
			//row11


			
				if(execStat){
					runStat.updateStatOnConnection("row11"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row11 - " + (row11==null? "": row11.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_10 = false;
                        if(row11.TRADER_ID == null) {
pstmt_tOracleOutput_10.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_10.setInt(1, row11.TRADER_ID);
}

                        if(row11.BROKER_ID == null) {
pstmt_tOracleOutput_10.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_10.setInt(2, row11.BROKER_ID);
}

                        if(row11.TIMESTAMP != null) {
pstmt_tOracleOutput_10.setObject(3, new java.sql.Timestamp(row11.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_10.setNull(3, java.sql.Types.DATE);
}

                        if(row11.COMPANY_ID == null) {
pstmt_tOracleOutput_10.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_10.setInt(4, row11.COMPANY_ID);
}

                        if(row11.SECURITY_TP == null) {
pstmt_tOracleOutput_10.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_10.setString(5, row11.SECURITY_TP);
}

                        if(row11.TRADE_TP == null) {
pstmt_tOracleOutput_10.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_10.setString(6, row11.TRADE_TP);
}

                        if(row11.QUANTITY == null) {
pstmt_tOracleOutput_10.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_10.setInt(7, row11.QUANTITY);
}

                        if(row11.PRICE == null) {
pstmt_tOracleOutput_10.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_10.setFloat(8, row11.PRICE);
}

                pstmt_tOracleOutput_10.addBatch();
                nb_line_tOracleOutput_10++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_10)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_10++;
            if (batchSize_tOracleOutput_10 > 0 &&  batchSize_tOracleOutput_10 <= batchSizeCounter_tOracleOutput_10) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_10.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_10){
			        java.sql.SQLException ne_tOracleOutput_10 = e_tOracleOutput_10.getNextException(),sqle_tOracleOutput_10=null;
			    	String errormessage_tOracleOutput_10;
					if (ne_tOracleOutput_10 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_10 = new java.sql.SQLException(e_tOracleOutput_10.getMessage() + "\ncaused by: " + ne_tOracleOutput_10.getMessage(), ne_tOracleOutput_10.getSQLState(), ne_tOracleOutput_10.getErrorCode(), ne_tOracleOutput_10);
						errormessage_tOracleOutput_10 = sqle_tOracleOutput_10.getMessage();
					}else{
						errormessage_tOracleOutput_10 = e_tOracleOutput_10.getMessage();
					}
	            	
            log.error("tOracleOutput_10 - "  + (errormessage_tOracleOutput_10) );
	                	System.err.println(errormessage_tOracleOutput_10);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_10 = pstmt_tOracleOutput_10.getUpdateCount();
                    insertedCount_tOracleOutput_10
                += (tmp_batchUpdateCount_tOracleOutput_10!=-1?tmp_batchUpdateCount_tOracleOutput_10:0);
                batchSizeCounter_tOracleOutput_10 = 0;
            }
                commitCounter_tOracleOutput_10++;
                if(commitEvery_tOracleOutput_10 <= commitCounter_tOracleOutput_10) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_10.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_10){
					        java.sql.SQLException ne_tOracleOutput_10 = e_tOracleOutput_10.getNextException(),sqle_tOracleOutput_10=null;
					    	String errormessage_tOracleOutput_10;
							if (ne_tOracleOutput_10 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_10 = new java.sql.SQLException(e_tOracleOutput_10.getMessage() + "\ncaused by: " + ne_tOracleOutput_10.getMessage(), ne_tOracleOutput_10.getSQLState(), ne_tOracleOutput_10.getErrorCode(), ne_tOracleOutput_10);
								errormessage_tOracleOutput_10 = sqle_tOracleOutput_10.getMessage();
							}else{
								errormessage_tOracleOutput_10 = e_tOracleOutput_10.getMessage();
							}
			            	
            log.error("tOracleOutput_10 - "  + (errormessage_tOracleOutput_10) );
			                	System.err.println(errormessage_tOracleOutput_10);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_10 = pstmt_tOracleOutput_10.getUpdateCount();
                            insertedCount_tOracleOutput_10
                        += (tmp_batchUpdateCount_tOracleOutput_10!=-1?tmp_batchUpdateCount_tOracleOutput_10:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_10)  + (" record(s).") );
                    conn_tOracleOutput_10.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_10=0;
                }

 


	tos_count_tOracleOutput_10++;

/**
 * [tOracleOutput_10 main ] stop
 */



	
	/**
	 * [tOracleInput_9 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_9";

	

}
}finally{
stmt_tOracleInput_9.close();

	if(conn_tOracleInput_9 != null && !conn_tOracleInput_9.isClosed()) {
	
	    		log.debug("tOracleInput_9 - Closing the connection to the database.");
			
			conn_tOracleInput_9.close();
			
	    		log.debug("tOracleInput_9 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_9_NB_LINE",nb_line_tOracleInput_9);
	    		log.debug("tOracleInput_9 - Retrieved records count: "+nb_line_tOracleInput_9 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_9 - "  + ("Done.") );

ok_Hash.put("tOracleInput_9", true);
end_Hash.put("tOracleInput_9", System.currentTimeMillis());




/**
 * [tOracleInput_9 end ] stop
 */

	
	/**
	 * [tOracleOutput_10 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_10";

	
	



	
            try {
            	if (pstmt_tOracleOutput_10 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_10.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_10){
		        java.sql.SQLException ne_tOracleOutput_10 = e_tOracleOutput_10.getNextException(),sqle_tOracleOutput_10=null;
		    	String errormessage_tOracleOutput_10;
				if (ne_tOracleOutput_10 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_10 = new java.sql.SQLException(e_tOracleOutput_10.getMessage() + "\ncaused by: " + ne_tOracleOutput_10.getMessage(), ne_tOracleOutput_10.getSQLState(), ne_tOracleOutput_10.getErrorCode(), ne_tOracleOutput_10);
					errormessage_tOracleOutput_10 = sqle_tOracleOutput_10.getMessage();
				}else{
					errormessage_tOracleOutput_10 = e_tOracleOutput_10.getMessage();
				}
            	
            log.error("tOracleOutput_10 - "  + (errormessage_tOracleOutput_10) );
                	System.err.println(errormessage_tOracleOutput_10);
            	
        	}
        	if (pstmt_tOracleOutput_10 != null) {
            	tmp_batchUpdateCount_tOracleOutput_10 = pstmt_tOracleOutput_10.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_10
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_10!=-1?tmp_batchUpdateCount_tOracleOutput_10:0);
            }
        if(pstmt_tOracleOutput_10 != null) {
			
				pstmt_tOracleOutput_10.close();
			
        }
		if(commitCounter_tOracleOutput_10 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_10)  + (" record(s).") );
		    conn_tOracleOutput_10.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_10 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_10", true);
   	


	
	nb_line_deleted_tOracleOutput_10=nb_line_deleted_tOracleOutput_10+ deletedCount_tOracleOutput_10;
	nb_line_update_tOracleOutput_10=nb_line_update_tOracleOutput_10 + updatedCount_tOracleOutput_10;
	nb_line_inserted_tOracleOutput_10=nb_line_inserted_tOracleOutput_10 + insertedCount_tOracleOutput_10;
	nb_line_rejected_tOracleOutput_10=nb_line_rejected_tOracleOutput_10 + rejectedCount_tOracleOutput_10;
	
        globalMap.put("tOracleOutput_10_NB_LINE",nb_line_tOracleOutput_10);
        globalMap.put("tOracleOutput_10_NB_LINE_UPDATED",nb_line_update_tOracleOutput_10);
        globalMap.put("tOracleOutput_10_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_10);
        globalMap.put("tOracleOutput_10_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_10);
        globalMap.put("tOracleOutput_10_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_10);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_10)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row11"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_10", true);
end_Hash.put("tOracleOutput_10", System.currentTimeMillis());




/**
 * [tOracleOutput_10 end ] stop
 */



				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_9:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk6", 0, "ok");
								} 
							
							tOracleInput_2Process(globalMap); 
						



	
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
	 * [tOracleInput_9 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_9";

	

 



/**
 * [tOracleInput_9 finally ] stop
 */

	
	/**
	 * [tOracleOutput_10 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_10";

	



	
		if(resourceMap.get("finish_tOracleOutput_10")==null){
			if(resourceMap.get("conn_tOracleOutput_10")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_10 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_10");
					
					
            		
					ctn_tOracleOutput_10.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_10 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_10) {
					String errorMessage_tOracleOutput_10 = "failed to close the connection in tOracleOutput_10 :" + sqlEx_tOracleOutput_10.getMessage();
					
            log.error("tOracleOutput_10 - "  + (errorMessage_tOracleOutput_10) );
					System.err.println(errorMessage_tOracleOutput_10);
				}
			}
		}
	

 



/**
 * [tOracleOutput_10 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_9_SUBPROCESS_STATE", 1);
	}
	


public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

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
    public int compareTo(row10Struct other) {

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
public void tOracleInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_2_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row10Struct row10 = new row10Struct();




	
	/**
	 * [tOracleOutput_9 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_9", false);
		start_Hash.put("tOracleOutput_9", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_9";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row10" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_9 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_9{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_9 = new StringBuilder();
            log4jParamters_tOracleOutput_9.append("Parameters:");
                    log4jParamters_tOracleOutput_9.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("TABLE" + " = " + "\"STAGE3\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("TABLE_ACTION" + " = " + "CREATE_IF_NOT_EXISTS");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_9.append(" | ");
                    log4jParamters_tOracleOutput_9.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_9.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + (log4jParamters_tOracleOutput_9) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_9().limitLog4jByte();






    int nb_line_tOracleOutput_9 = 0;
    int nb_line_update_tOracleOutput_9 = 0;
    int nb_line_inserted_tOracleOutput_9 = 0;
    int nb_line_deleted_tOracleOutput_9 = 0;
    int nb_line_rejected_tOracleOutput_9 = 0;

    int tmp_batchUpdateCount_tOracleOutput_9 = 0;

    int deletedCount_tOracleOutput_9=0;
    int updatedCount_tOracleOutput_9=0;
    int insertedCount_tOracleOutput_9=0;
    int rejectedCount_tOracleOutput_9=0;

    boolean whetherReject_tOracleOutput_9 = false;

    java.sql.Connection conn_tOracleOutput_9 = null;

    //optional table
    String dbschema_tOracleOutput_9 = null;
    String tableName_tOracleOutput_9 = null;
                    String driverClass_tOracleOutput_9 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_9)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_9);
                String url_tOracleOutput_9 = null;
                    url_tOracleOutput_9 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_9 = "hr";
 
	final String decryptedPassword_tOracleOutput_9 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_9 = decryptedPassword_tOracleOutput_9;
                dbschema_tOracleOutput_9 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection attempts to '")  + (url_tOracleOutput_9)  + ("' with the username '")  + (dbUser_tOracleOutput_9)  + ("'.") );

                    conn_tOracleOutput_9 = java.sql.DriverManager.getConnection(url_tOracleOutput_9, dbUser_tOracleOutput_9, dbPwd_tOracleOutput_9);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection to '")  + (url_tOracleOutput_9)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_9", conn_tOracleOutput_9);
            conn_tOracleOutput_9.setAutoCommit(false);
            int commitEvery_tOracleOutput_9 = 10000;
            int commitCounter_tOracleOutput_9 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_9.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_9 = 10000;
        int batchSizeCounter_tOracleOutput_9=0;
        int count_tOracleOutput_9=0;

        if(dbschema_tOracleOutput_9 == null || dbschema_tOracleOutput_9.trim().length() == 0) {
            tableName_tOracleOutput_9 = ("STAGE3");
        } else {
            tableName_tOracleOutput_9 = dbschema_tOracleOutput_9 + "." + ("STAGE3");
        }
                                String tableNameForSearch_tOracleOutput_9= "" + ((String)"STAGE3") + "";
String dbschemaForSearch_tOracleOutput_9= null;
if(dbschema_tOracleOutput_9== null || dbschema_tOracleOutput_9.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_9= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_9= dbschema_tOracleOutput_9.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_9 = conn_tOracleOutput_9.getMetaData();
                                if(tableNameForSearch_tOracleOutput_9.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_9 = tableNameForSearch_tOracleOutput_9.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_9 = tableNameForSearch_tOracleOutput_9.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_9 = dbMetaData_tOracleOutput_9.getTables(null, dbschemaForSearch_tOracleOutput_9, tableNameForSearch_tOracleOutput_9, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_9 = false;
                                if(rsTable_tOracleOutput_9.next()) {
                                    whetherExist_tOracleOutput_9 = true;
                                }
                                rsTable_tOracleOutput_9.close();

                                if(!whetherExist_tOracleOutput_9) {
                                    java.sql.Statement stmtCreate_tOracleOutput_9 = conn_tOracleOutput_9.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_9)  + ("'.") );
                                        stmtCreate_tOracleOutput_9.execute("CREATE TABLE " + tableName_tOracleOutput_9 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_9)  + ("' has succeeded.") );
                                    stmtCreate_tOracleOutput_9.close();
                                }
                String insert_tOracleOutput_9 = "INSERT INTO " + tableName_tOracleOutput_9 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_9 = conn_tOracleOutput_9.prepareStatement(insert_tOracleOutput_9);





 



/**
 * [tOracleOutput_9 begin ] stop
 */



	
	/**
	 * [tOracleInput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_2", false);
		start_Hash.put("tOracleInput_2", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_2";

	
		int tos_count_tOracleInput_2 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_2 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_2{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_2 = new StringBuilder();
            log4jParamters_tOracleInput_2.append("Parameters:");
                    log4jParamters_tOracleInput_2.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("TABLE" + " = " + "\"STAGE2\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("QUERY" + " = " + "\"SELECT    STAGE2.TRADER_ID,    STAGE2.BROKER_ID,    STAGE2.TIMESTAMP,    STAGE2.COMPANY_ID,    STAGE2.SECURITY_TP,    STAGE2.TRADE_TP,    STAGE2.QUANTITY,    STAGE2.PRICE FROM STAGE2\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("BROKER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TIMESTAMP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("COMPANY_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("SECURITY_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_2.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_2 - "  + (log4jParamters_tOracleInput_2) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_2().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_2 = 0;
		    java.sql.Connection conn_tOracleInput_2 = null;
				String driverClass_tOracleInput_2 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_2);
				
			String url_tOracleInput_2 = null;
				url_tOracleInput_2 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_2 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_2 = decryptedPassword_tOracleInput_2;

				
	    		log.debug("tOracleInput_2 - Driver ClassName: "+driverClass_tOracleInput_2+".");
			
	    		log.debug("tOracleInput_2 - Connection attempt to '" + url_tOracleInput_2 + "' with the username '" + dbUser_tOracleInput_2 + "'.");
			
					conn_tOracleInput_2 = java.sql.DriverManager.getConnection(url_tOracleInput_2,dbUser_tOracleInput_2,dbPwd_tOracleInput_2);
	    		log.debug("tOracleInput_2 - Connection to '" + url_tOracleInput_2 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_2 = conn_tOracleInput_2.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_2 = stmtGetTZ_tOracleInput_2.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_2 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_2.next()) {
					sessionTimezone_tOracleInput_2 = rsGetTZ_tOracleInput_2.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_2).setSessionTimeZone(sessionTimezone_tOracleInput_2);
		    
			java.sql.Statement stmt_tOracleInput_2 = conn_tOracleInput_2.createStatement();

		    String dbquery_tOracleInput_2 = "SELECT \n  STAGE2.TRADER_ID, \n  STAGE2.BROKER_ID, \n  STAGE2.TIMESTAMP, \n  STAGE2.COMPANY_ID, \n  STAGE2.SECURITY_TP, \n  STAGE2.TRADE_TP, \n  STAGE2.QUANTITY, \n  STAGE2.PRICE\nFROM STAGE2";
			
                log.debug("tOracleInput_2 - Executing the query: '"+dbquery_tOracleInput_2+"'.");
			

            	globalMap.put("tOracleInput_2_QUERY",dbquery_tOracleInput_2);
		    java.sql.ResultSet rs_tOracleInput_2 = null;

		    try {
		    	rs_tOracleInput_2 = stmt_tOracleInput_2.executeQuery(dbquery_tOracleInput_2);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_2 = rs_tOracleInput_2.getMetaData();
		    	int colQtyInRs_tOracleInput_2 = rsmd_tOracleInput_2.getColumnCount();

		    String tmpContent_tOracleInput_2 = null;
		    
		    
		    	log.debug("tOracleInput_2 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_2.next()) {
		        nb_line_tOracleInput_2++;
		        
							if(colQtyInRs_tOracleInput_2 < 1) {
								row10.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(1) != null) {
						row10.TRADER_ID = rs_tOracleInput_2.getInt(1);
					} else {
				
						row10.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 2) {
								row10.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(2) != null) {
						row10.BROKER_ID = rs_tOracleInput_2.getInt(2);
					} else {
				
						row10.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 3) {
								row10.TIMESTAMP = null;
							} else {
										
			row10.TIMESTAMP = routines.system.JDBCUtil.getDate(rs_tOracleInput_2, 3);
		                    }
							if(colQtyInRs_tOracleInput_2 < 4) {
								row10.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(4) != null) {
						row10.COMPANY_ID = rs_tOracleInput_2.getInt(4);
					} else {
				
						row10.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 5) {
								row10.SECURITY_TP = null;
							} else {
	                         		
        	row10.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_2, 5, false);
		                    }
							if(colQtyInRs_tOracleInput_2 < 6) {
								row10.TRADE_TP = null;
							} else {
	                         		
        	row10.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_2, 6, false);
		                    }
							if(colQtyInRs_tOracleInput_2 < 7) {
								row10.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(7) != null) {
						row10.QUANTITY = rs_tOracleInput_2.getInt(7);
					} else {
				
						row10.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 8) {
								row10.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(8) != null) {
						row10.PRICE = rs_tOracleInput_2.getFloat(8);
					} else {
				
						row10.PRICE = null;
					}
		                    }
					
						log.debug("tOracleInput_2 - Retrieving the record " + nb_line_tOracleInput_2 + ".");
					




 



/**
 * [tOracleInput_2 begin ] stop
 */
	
	/**
	 * [tOracleInput_2 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_2";

	

 


	tos_count_tOracleInput_2++;

/**
 * [tOracleInput_2 main ] stop
 */

	
	/**
	 * [tOracleOutput_9 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_9";

	

			//row10
			//row10


			
				if(execStat){
					runStat.updateStatOnConnection("row10"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row10 - " + (row10==null? "": row10.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_9 = false;
                        if(row10.TRADER_ID == null) {
pstmt_tOracleOutput_9.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_9.setInt(1, row10.TRADER_ID);
}

                        if(row10.BROKER_ID == null) {
pstmt_tOracleOutput_9.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_9.setInt(2, row10.BROKER_ID);
}

                        if(row10.TIMESTAMP != null) {
pstmt_tOracleOutput_9.setObject(3, new java.sql.Timestamp(row10.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_9.setNull(3, java.sql.Types.DATE);
}

                        if(row10.COMPANY_ID == null) {
pstmt_tOracleOutput_9.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_9.setInt(4, row10.COMPANY_ID);
}

                        if(row10.SECURITY_TP == null) {
pstmt_tOracleOutput_9.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_9.setString(5, row10.SECURITY_TP);
}

                        if(row10.TRADE_TP == null) {
pstmt_tOracleOutput_9.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_9.setString(6, row10.TRADE_TP);
}

                        if(row10.QUANTITY == null) {
pstmt_tOracleOutput_9.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_9.setInt(7, row10.QUANTITY);
}

                        if(row10.PRICE == null) {
pstmt_tOracleOutput_9.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_9.setFloat(8, row10.PRICE);
}

                pstmt_tOracleOutput_9.addBatch();
                nb_line_tOracleOutput_9++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_9)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_9++;
            if (batchSize_tOracleOutput_9 > 0 &&  batchSize_tOracleOutput_9 <= batchSizeCounter_tOracleOutput_9) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_9.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_9){
			        java.sql.SQLException ne_tOracleOutput_9 = e_tOracleOutput_9.getNextException(),sqle_tOracleOutput_9=null;
			    	String errormessage_tOracleOutput_9;
					if (ne_tOracleOutput_9 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_9 = new java.sql.SQLException(e_tOracleOutput_9.getMessage() + "\ncaused by: " + ne_tOracleOutput_9.getMessage(), ne_tOracleOutput_9.getSQLState(), ne_tOracleOutput_9.getErrorCode(), ne_tOracleOutput_9);
						errormessage_tOracleOutput_9 = sqle_tOracleOutput_9.getMessage();
					}else{
						errormessage_tOracleOutput_9 = e_tOracleOutput_9.getMessage();
					}
	            	
            log.error("tOracleOutput_9 - "  + (errormessage_tOracleOutput_9) );
	                	System.err.println(errormessage_tOracleOutput_9);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_9 = pstmt_tOracleOutput_9.getUpdateCount();
                    insertedCount_tOracleOutput_9
                += (tmp_batchUpdateCount_tOracleOutput_9!=-1?tmp_batchUpdateCount_tOracleOutput_9:0);
                batchSizeCounter_tOracleOutput_9 = 0;
            }
                commitCounter_tOracleOutput_9++;
                if(commitEvery_tOracleOutput_9 <= commitCounter_tOracleOutput_9) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_9.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_9){
					        java.sql.SQLException ne_tOracleOutput_9 = e_tOracleOutput_9.getNextException(),sqle_tOracleOutput_9=null;
					    	String errormessage_tOracleOutput_9;
							if (ne_tOracleOutput_9 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_9 = new java.sql.SQLException(e_tOracleOutput_9.getMessage() + "\ncaused by: " + ne_tOracleOutput_9.getMessage(), ne_tOracleOutput_9.getSQLState(), ne_tOracleOutput_9.getErrorCode(), ne_tOracleOutput_9);
								errormessage_tOracleOutput_9 = sqle_tOracleOutput_9.getMessage();
							}else{
								errormessage_tOracleOutput_9 = e_tOracleOutput_9.getMessage();
							}
			            	
            log.error("tOracleOutput_9 - "  + (errormessage_tOracleOutput_9) );
			                	System.err.println(errormessage_tOracleOutput_9);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_9 = pstmt_tOracleOutput_9.getUpdateCount();
                            insertedCount_tOracleOutput_9
                        += (tmp_batchUpdateCount_tOracleOutput_9!=-1?tmp_batchUpdateCount_tOracleOutput_9:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_9)  + (" record(s).") );
                    conn_tOracleOutput_9.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_9=0;
                }

 


	tos_count_tOracleOutput_9++;

/**
 * [tOracleOutput_9 main ] stop
 */



	
	/**
	 * [tOracleInput_2 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_2";

	

}
}finally{
stmt_tOracleInput_2.close();

	if(conn_tOracleInput_2 != null && !conn_tOracleInput_2.isClosed()) {
	
	    		log.debug("tOracleInput_2 - Closing the connection to the database.");
			
			conn_tOracleInput_2.close();
			
	    		log.debug("tOracleInput_2 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_2_NB_LINE",nb_line_tOracleInput_2);
	    		log.debug("tOracleInput_2 - Retrieved records count: "+nb_line_tOracleInput_2 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_2 - "  + ("Done.") );

ok_Hash.put("tOracleInput_2", true);
end_Hash.put("tOracleInput_2", System.currentTimeMillis());




/**
 * [tOracleInput_2 end ] stop
 */

	
	/**
	 * [tOracleOutput_9 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_9";

	
	



	
            try {
            	if (pstmt_tOracleOutput_9 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_9.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_9){
		        java.sql.SQLException ne_tOracleOutput_9 = e_tOracleOutput_9.getNextException(),sqle_tOracleOutput_9=null;
		    	String errormessage_tOracleOutput_9;
				if (ne_tOracleOutput_9 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_9 = new java.sql.SQLException(e_tOracleOutput_9.getMessage() + "\ncaused by: " + ne_tOracleOutput_9.getMessage(), ne_tOracleOutput_9.getSQLState(), ne_tOracleOutput_9.getErrorCode(), ne_tOracleOutput_9);
					errormessage_tOracleOutput_9 = sqle_tOracleOutput_9.getMessage();
				}else{
					errormessage_tOracleOutput_9 = e_tOracleOutput_9.getMessage();
				}
            	
            log.error("tOracleOutput_9 - "  + (errormessage_tOracleOutput_9) );
                	System.err.println(errormessage_tOracleOutput_9);
            	
        	}
        	if (pstmt_tOracleOutput_9 != null) {
            	tmp_batchUpdateCount_tOracleOutput_9 = pstmt_tOracleOutput_9.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_9
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_9!=-1?tmp_batchUpdateCount_tOracleOutput_9:0);
            }
        if(pstmt_tOracleOutput_9 != null) {
			
				pstmt_tOracleOutput_9.close();
			
        }
		if(commitCounter_tOracleOutput_9 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_9)  + (" record(s).") );
		    conn_tOracleOutput_9.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_9 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_9", true);
   	


	
	nb_line_deleted_tOracleOutput_9=nb_line_deleted_tOracleOutput_9+ deletedCount_tOracleOutput_9;
	nb_line_update_tOracleOutput_9=nb_line_update_tOracleOutput_9 + updatedCount_tOracleOutput_9;
	nb_line_inserted_tOracleOutput_9=nb_line_inserted_tOracleOutput_9 + insertedCount_tOracleOutput_9;
	nb_line_rejected_tOracleOutput_9=nb_line_rejected_tOracleOutput_9 + rejectedCount_tOracleOutput_9;
	
        globalMap.put("tOracleOutput_9_NB_LINE",nb_line_tOracleOutput_9);
        globalMap.put("tOracleOutput_9_NB_LINE_UPDATED",nb_line_update_tOracleOutput_9);
        globalMap.put("tOracleOutput_9_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_9);
        globalMap.put("tOracleOutput_9_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_9);
        globalMap.put("tOracleOutput_9_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_9);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_9)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row10"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_9", true);
end_Hash.put("tOracleOutput_9", System.currentTimeMillis());




/**
 * [tOracleOutput_9 end ] stop
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
	 * [tOracleInput_2 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_2";

	

 



/**
 * [tOracleInput_2 finally ] stop
 */

	
	/**
	 * [tOracleOutput_9 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_9";

	



	
		if(resourceMap.get("finish_tOracleOutput_9")==null){
			if(resourceMap.get("conn_tOracleOutput_9")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_9 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_9");
					
					
            		
					ctn_tOracleOutput_9.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_9 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_9) {
					String errorMessage_tOracleOutput_9 = "failed to close the connection in tOracleOutput_9 :" + sqlEx_tOracleOutput_9.getMessage();
					
            log.error("tOracleOutput_9 - "  + (errorMessage_tOracleOutput_9) );
					System.err.println(errorMessage_tOracleOutput_9);
				}
			}
		}
	

 



/**
 * [tOracleOutput_9 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_2_SUBPROCESS_STATE", 1);
	}
	


public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public String TRADE_TP;

				public String getTRADE_TP () {
					return this.TRADE_TP;
				}
				
			    public Integer QUANTITY;

				public Integer getQUANTITY () {
					return this.QUANTITY;
				}
				
			    public Float AMOUNT;

				public Float getAMOUNT () {
					return this.AMOUNT;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
					this.TRADE_TP = readString(dis);
					
						this.QUANTITY = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.AMOUNT = null;
           				} else {
           			    	this.AMOUNT = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.TRADE_TP,dos);
					
					// Integer
				
						writeInteger(this.QUANTITY,dos);
					
					// Float
				
						if(this.AMOUNT == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.AMOUNT);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADE_TP="+TRADE_TP);
		sb.append(",QUANTITY="+String.valueOf(QUANTITY));
		sb.append(",AMOUNT="+String.valueOf(AMOUNT));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
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
        		
        				if(AMOUNT == null){
        					sb.append("<null>");
        				}else{
            				sb.append(AMOUNT);
            			}
            		
        			sb.append("|");
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row5Struct other) {

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
public void tOracleInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_4_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row5Struct row5 = new row5Struct();




	
	/**
	 * [tAdvancedHash_row5 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row5", false);
		start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row5";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row5" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tAdvancedHash_row5 = 0;
		
    	class BytesLimit65535_tAdvancedHash_row5{
    		public void limitLog4jByte() throws Exception{
    			
    		}
    	}
    	
        new BytesLimit65535_tAdvancedHash_row5().limitLog4jByte();

			   		// connection name:row5
			   		// source node:tOracleInput_4 - inputs:(after_tOracleInput_3) outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5) outputs:()
			   		// linked node: tMap_3 - inputs:(row4,row5) outputs:(out4)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row5Struct>getLookup(matchingModeEnum_row5);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row5 begin ] stop
 */



	
	/**
	 * [tOracleInput_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_4", false);
		start_Hash.put("tOracleInput_4", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_4";

	
		int tos_count_tOracleInput_4 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_4 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_4{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_4 = new StringBuilder();
            log4jParamters_tOracleInput_4.append("Parameters:");
                    log4jParamters_tOracleInput_4.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("TABLE" + " = " + "\"SELL\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("QUERY" + " = " + "\"SELECT    SELL.TRADE_TP,    SELL.QUANTITY,    SELL.AMOUNT FROM SELL\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("AMOUNT")+"}]");
                log4jParamters_tOracleInput_4.append(" | ");
                    log4jParamters_tOracleInput_4.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_4.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_4 - "  + (log4jParamters_tOracleInput_4) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_4().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_4 = 0;
		    java.sql.Connection conn_tOracleInput_4 = null;
				String driverClass_tOracleInput_4 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_4);
				
			String url_tOracleInput_4 = null;
				url_tOracleInput_4 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_4 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_4 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_4 = decryptedPassword_tOracleInput_4;

				
	    		log.debug("tOracleInput_4 - Driver ClassName: "+driverClass_tOracleInput_4+".");
			
	    		log.debug("tOracleInput_4 - Connection attempt to '" + url_tOracleInput_4 + "' with the username '" + dbUser_tOracleInput_4 + "'.");
			
					conn_tOracleInput_4 = java.sql.DriverManager.getConnection(url_tOracleInput_4,dbUser_tOracleInput_4,dbPwd_tOracleInput_4);
	    		log.debug("tOracleInput_4 - Connection to '" + url_tOracleInput_4 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_4 = conn_tOracleInput_4.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_4 = stmtGetTZ_tOracleInput_4.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_4 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_4.next()) {
					sessionTimezone_tOracleInput_4 = rsGetTZ_tOracleInput_4.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_4).setSessionTimeZone(sessionTimezone_tOracleInput_4);
		    
			java.sql.Statement stmt_tOracleInput_4 = conn_tOracleInput_4.createStatement();

		    String dbquery_tOracleInput_4 = "SELECT \n  SELL.TRADE_TP, \n  SELL.QUANTITY, \n  SELL.AMOUNT\nFROM SELL";
			
                log.debug("tOracleInput_4 - Executing the query: '"+dbquery_tOracleInput_4+"'.");
			

            	globalMap.put("tOracleInput_4_QUERY",dbquery_tOracleInput_4);
		    java.sql.ResultSet rs_tOracleInput_4 = null;

		    try {
		    	rs_tOracleInput_4 = stmt_tOracleInput_4.executeQuery(dbquery_tOracleInput_4);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_4 = rs_tOracleInput_4.getMetaData();
		    	int colQtyInRs_tOracleInput_4 = rsmd_tOracleInput_4.getColumnCount();

		    String tmpContent_tOracleInput_4 = null;
		    
		    
		    	log.debug("tOracleInput_4 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_4.next()) {
		        nb_line_tOracleInput_4++;
		        
							if(colQtyInRs_tOracleInput_4 < 1) {
								row5.TRADE_TP = null;
							} else {
	                         		
        	row5.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_4, 1, false);
		                    }
							if(colQtyInRs_tOracleInput_4 < 2) {
								row5.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_4.getObject(2) != null) {
						row5.QUANTITY = rs_tOracleInput_4.getInt(2);
					} else {
				
						row5.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_4 < 3) {
								row5.AMOUNT = null;
							} else {
		                          
					if(rs_tOracleInput_4.getObject(3) != null) {
						row5.AMOUNT = rs_tOracleInput_4.getFloat(3);
					} else {
				
						row5.AMOUNT = null;
					}
		                    }
					
						log.debug("tOracleInput_4 - Retrieving the record " + nb_line_tOracleInput_4 + ".");
					




 



/**
 * [tOracleInput_4 begin ] stop
 */
	
	/**
	 * [tOracleInput_4 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_4";

	

 


	tos_count_tOracleInput_4++;

/**
 * [tOracleInput_4 main ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

			//row5
			//row5


			
				if(execStat){
					runStat.updateStatOnConnection("row5"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row5 - " + (row5==null? "": row5.toLogString()));
    			}
    		


			   
			   

					row5Struct row5_HashRow = new row5Struct();
		   	   	   
				
				row5_HashRow.TRADE_TP = row5.TRADE_TP;
				
				row5_HashRow.QUANTITY = row5.QUANTITY;
				
				row5_HashRow.AMOUNT = row5.AMOUNT;
				
			tHash_Lookup_row5.put(row5_HashRow);
			
            




 


	tos_count_tAdvancedHash_row5++;

/**
 * [tAdvancedHash_row5 main ] stop
 */



	
	/**
	 * [tOracleInput_4 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_4";

	

}
}finally{
stmt_tOracleInput_4.close();

	if(conn_tOracleInput_4 != null && !conn_tOracleInput_4.isClosed()) {
	
	    		log.debug("tOracleInput_4 - Closing the connection to the database.");
			
			conn_tOracleInput_4.close();
			
	    		log.debug("tOracleInput_4 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_4_NB_LINE",nb_line_tOracleInput_4);
	    		log.debug("tOracleInput_4 - Retrieved records count: "+nb_line_tOracleInput_4 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_4 - "  + ("Done.") );

ok_Hash.put("tOracleInput_4", true);
end_Hash.put("tOracleInput_4", System.currentTimeMillis());




/**
 * [tOracleInput_4 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

tHash_Lookup_row5.endPut();

			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row5"+iterateId,2, 0); 
			 	}
			}
		
 

ok_Hash.put("tAdvancedHash_row5", true);
end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());




/**
 * [tAdvancedHash_row5 end ] stop
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
	 * [tOracleInput_4 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_4";

	

 



/**
 * [tOracleInput_4 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_4_SUBPROCESS_STATE", 1);
	}
	


public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
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
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row9Struct other) {

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

public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
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
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row7Struct other) {

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



		row7Struct row7 = new row7Struct();
row9Struct row9 = new row9Struct();





	
	/**
	 * [tAdvancedHash_row9 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row9", false);
		start_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row9";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row9" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tAdvancedHash_row9 = 0;
		
    	class BytesLimit65535_tAdvancedHash_row9{
    		public void limitLog4jByte() throws Exception{
    			
    		}
    	}
    	
        new BytesLimit65535_tAdvancedHash_row9().limitLog4jByte();

			   		// connection name:row9
			   		// source node:tOracleOutput_7 - inputs:(row7) outputs:(row9,row9) | target node:tAdvancedHash_row9 - inputs:(row9) outputs:()
			   		// linked node: tMap_5 - inputs:(row8,row9) outputs:(out6)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row9Struct>getLookup(matchingModeEnum_row9);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row9 begin ] stop
 */



	
	/**
	 * [tOracleOutput_7 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_7", false);
		start_Hash.put("tOracleOutput_7", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_7";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row7" + iterateId, 0, 0);
					
				}
			} 

		
		int tos_count_tOracleOutput_7 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleOutput_7{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleOutput_7 = new StringBuilder();
            log4jParamters_tOracleOutput_7.append("Parameters:");
                    log4jParamters_tOracleOutput_7.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("TABLESCHEMA" + " = " + "\"\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("TABLE" + " = " + "\"HELPER\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("DATA_ACTION" + " = " + "INSERT");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("DIE_ON_ERROR" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("COMMIT_EVERY" + " = " + "10000");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("ADD_COLS" + " = " + "[]");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("USE_FIELD_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("USE_HINT_OPTIONS" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("CONVERT_COLUMN_TABLE_TO_UPPERCASE" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("ENABLE_DEBUG_MODE" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("USE_BATCH_SIZE" + " = " + "true");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("BATCH_SIZE" + " = " + "10000");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("SUPPORT_NULL_WHERE" + " = " + "false");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("USE_TIMESTAMP_FOR_DATE_TYPE" + " = " + "true");
                log4jParamters_tOracleOutput_7.append(" | ");
                    log4jParamters_tOracleOutput_7.append("TRIM_CHAR" + " = " + "true");
                log4jParamters_tOracleOutput_7.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + (log4jParamters_tOracleOutput_7) );
    		}
    	}
    	
        new BytesLimit65535_tOracleOutput_7().limitLog4jByte();






    int nb_line_tOracleOutput_7 = 0;
    int nb_line_update_tOracleOutput_7 = 0;
    int nb_line_inserted_tOracleOutput_7 = 0;
    int nb_line_deleted_tOracleOutput_7 = 0;
    int nb_line_rejected_tOracleOutput_7 = 0;

    int tmp_batchUpdateCount_tOracleOutput_7 = 0;

    int deletedCount_tOracleOutput_7=0;
    int updatedCount_tOracleOutput_7=0;
    int insertedCount_tOracleOutput_7=0;
    int rejectedCount_tOracleOutput_7=0;

    boolean whetherReject_tOracleOutput_7 = false;

    java.sql.Connection conn_tOracleOutput_7 = null;

    //optional table
    String dbschema_tOracleOutput_7 = null;
    String tableName_tOracleOutput_7 = null;
                    String driverClass_tOracleOutput_7 = "oracle.jdbc.OracleDriver";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Driver ClassName: ")  + (driverClass_tOracleOutput_7)  + (".") );

                java.lang.Class.forName(driverClass_tOracleOutput_7);
                String url_tOracleOutput_7 = null;
                    url_tOracleOutput_7 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
                String dbUser_tOracleOutput_7 = "hr";
 
	final String decryptedPassword_tOracleOutput_7 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

                String dbPwd_tOracleOutput_7 = decryptedPassword_tOracleOutput_7;
                dbschema_tOracleOutput_7 = "";

                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection attempts to '")  + (url_tOracleOutput_7)  + ("' with the username '")  + (dbUser_tOracleOutput_7)  + ("'.") );

                    conn_tOracleOutput_7 = java.sql.DriverManager.getConnection(url_tOracleOutput_7, dbUser_tOracleOutput_7, dbPwd_tOracleOutput_7);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection to '")  + (url_tOracleOutput_7)  + ("' has succeeded.") );
        resourceMap.put("conn_tOracleOutput_7", conn_tOracleOutput_7);
            conn_tOracleOutput_7.setAutoCommit(false);
            int commitEvery_tOracleOutput_7 = 10000;
            int commitCounter_tOracleOutput_7 = 0;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection is set auto commit to '")  + (conn_tOracleOutput_7.getAutoCommit())  + ("'.") );
        int batchSize_tOracleOutput_7 = 10000;
        int batchSizeCounter_tOracleOutput_7=0;
        int count_tOracleOutput_7=0;

        if(dbschema_tOracleOutput_7 == null || dbschema_tOracleOutput_7.trim().length() == 0) {
            tableName_tOracleOutput_7 = ("HELPER");
        } else {
            tableName_tOracleOutput_7 = dbschema_tOracleOutput_7 + "." + ("HELPER");
        }
                                String tableNameForSearch_tOracleOutput_7= "" + ((String)"HELPER") + "";
String dbschemaForSearch_tOracleOutput_7= null;
if(dbschema_tOracleOutput_7== null || dbschema_tOracleOutput_7.trim().length() == 0) {
dbschemaForSearch_tOracleOutput_7= ((String)"hr").toUpperCase();
} else {
dbschemaForSearch_tOracleOutput_7= dbschema_tOracleOutput_7.toUpperCase();
}

                                java.sql.DatabaseMetaData dbMetaData_tOracleOutput_7 = conn_tOracleOutput_7.getMetaData();
                                if(tableNameForSearch_tOracleOutput_7.indexOf("\"")==-1){
                                    tableNameForSearch_tOracleOutput_7 = tableNameForSearch_tOracleOutput_7.toUpperCase();
                                }else{
                                    tableNameForSearch_tOracleOutput_7 = tableNameForSearch_tOracleOutput_7.replaceAll("\"","");
                                }
                                java.sql.ResultSet rsTable_tOracleOutput_7 = dbMetaData_tOracleOutput_7.getTables(null, dbschemaForSearch_tOracleOutput_7, tableNameForSearch_tOracleOutput_7, new String[]{"TABLE"});
                                boolean whetherExist_tOracleOutput_7 = false;
                                if(rsTable_tOracleOutput_7.next()) {
                                    whetherExist_tOracleOutput_7 = true;
                                }
                                rsTable_tOracleOutput_7.close();

                                if(whetherExist_tOracleOutput_7) {
                                    java.sql.Statement stmtDrop_tOracleOutput_7 = conn_tOracleOutput_7.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Dropping")  + (" table '")  + (tableName_tOracleOutput_7)  + ("'.") );
                                    stmtDrop_tOracleOutput_7.execute("DROP TABLE " + tableName_tOracleOutput_7 + "" );
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Drop")  + (" table '")  + (tableName_tOracleOutput_7)  + ("' has succeeded.") );
                                    stmtDrop_tOracleOutput_7.close();
                                }
                                java.sql.Statement stmtCreate_tOracleOutput_7 = conn_tOracleOutput_7.createStatement();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Creating")  + (" table '")  + (tableName_tOracleOutput_7)  + ("'.") );
                                    stmtCreate_tOracleOutput_7.execute("CREATE TABLE " + tableName_tOracleOutput_7 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_7)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_7.close();
                String insert_tOracleOutput_7 = "INSERT INTO " + tableName_tOracleOutput_7 + " (TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP) VALUES (?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_7 = conn_tOracleOutput_7.prepareStatement(insert_tOracleOutput_7);





 



/**
 * [tOracleOutput_7 begin ] stop
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
int nb_max_row_tRowGenerator_2 = 1;


class tRowGenerator_2Randomizer {
	public Integer getRandomTRADER_ID() {
		 
		return ((Integer)globalMap.get("row12.TRADER_ID")) ;
		
	}
	public Integer getRandomBROKER_ID() {
		 
		return ((Integer)globalMap.get("row12.BROKER_ID")) ;
		
	}
	public java.util.Date getRandomTIMESTAMP() {
		
		return TalendDate.getCurrentDate();
		
	}
	public Integer getRandomCOMPANY_ID() {
		 
		return ((Integer)globalMap.get("row12.COMPANY_ID")) ;
		
	}
	public String getRandomSECURITY_TP() {
		 
		return ((String)globalMap.get("row12.SECURITY_TP")) ;
		
	}
}
	tRowGenerator_2Randomizer randtRowGenerator_2 = new tRowGenerator_2Randomizer();
	
    	log.info("tRowGenerator_2 - Generating records.");
	for (int itRowGenerator_2=0; itRowGenerator_2<nb_max_row_tRowGenerator_2 ;itRowGenerator_2++) {
		row7.TRADER_ID = randtRowGenerator_2.getRandomTRADER_ID();
		row7.BROKER_ID = randtRowGenerator_2.getRandomBROKER_ID();
		row7.TIMESTAMP = randtRowGenerator_2.getRandomTIMESTAMP();
		row7.COMPANY_ID = randtRowGenerator_2.getRandomCOMPANY_ID();
		row7.SECURITY_TP = randtRowGenerator_2.getRandomSECURITY_TP();
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
	 * [tOracleOutput_7 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_7";

	

			//row7
			//row7


			
				if(execStat){
					runStat.updateStatOnConnection("row7"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row7 - " + (row7==null? "": row7.toLogString()));
    			}
    		



            row9 = null;
            row9 = null;
        whetherReject_tOracleOutput_7 = false;
                        if(row7.TRADER_ID == null) {
pstmt_tOracleOutput_7.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_7.setInt(1, row7.TRADER_ID);
}

                        if(row7.BROKER_ID == null) {
pstmt_tOracleOutput_7.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_7.setInt(2, row7.BROKER_ID);
}

                        if(row7.TIMESTAMP != null) {
pstmt_tOracleOutput_7.setObject(3, new java.sql.Timestamp(row7.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_7.setNull(3, java.sql.Types.DATE);
}

                        if(row7.COMPANY_ID == null) {
pstmt_tOracleOutput_7.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_7.setInt(4, row7.COMPANY_ID);
}

                        if(row7.SECURITY_TP == null) {
pstmt_tOracleOutput_7.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_7.setString(5, row7.SECURITY_TP);
}

                pstmt_tOracleOutput_7.addBatch();
                nb_line_tOracleOutput_7++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_7)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_7++;
            if(!whetherReject_tOracleOutput_7) {
                            row9 = new row9Struct();
                                row9.TRADER_ID = row7.TRADER_ID;
                                row9.BROKER_ID = row7.BROKER_ID;
                                row9.TIMESTAMP = row7.TIMESTAMP;
                                row9.COMPANY_ID = row7.COMPANY_ID;
                                row9.SECURITY_TP = row7.SECURITY_TP;
                            row9 = new row9Struct();
                                row9.TRADER_ID = row7.TRADER_ID;
                                row9.BROKER_ID = row7.BROKER_ID;
                                row9.TIMESTAMP = row7.TIMESTAMP;
                                row9.COMPANY_ID = row7.COMPANY_ID;
                                row9.SECURITY_TP = row7.SECURITY_TP;
            }
            if (batchSize_tOracleOutput_7 > 0 &&  batchSize_tOracleOutput_7 <= batchSizeCounter_tOracleOutput_7) {
                try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                    pstmt_tOracleOutput_7.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                }catch (java.sql.BatchUpdateException e_tOracleOutput_7){
			        java.sql.SQLException ne_tOracleOutput_7 = e_tOracleOutput_7.getNextException(),sqle_tOracleOutput_7=null;
			    	String errormessage_tOracleOutput_7;
					if (ne_tOracleOutput_7 != null) {
						// build new exception to provide the original cause
						sqle_tOracleOutput_7 = new java.sql.SQLException(e_tOracleOutput_7.getMessage() + "\ncaused by: " + ne_tOracleOutput_7.getMessage(), ne_tOracleOutput_7.getSQLState(), ne_tOracleOutput_7.getErrorCode(), ne_tOracleOutput_7);
						errormessage_tOracleOutput_7 = sqle_tOracleOutput_7.getMessage();
					}else{
						errormessage_tOracleOutput_7 = e_tOracleOutput_7.getMessage();
					}
	            	
            log.error("tOracleOutput_7 - "  + (errormessage_tOracleOutput_7) );
	                	System.err.println(errormessage_tOracleOutput_7);
	            	
	        	}
                tmp_batchUpdateCount_tOracleOutput_7 = pstmt_tOracleOutput_7.getUpdateCount();
                    insertedCount_tOracleOutput_7
                += (tmp_batchUpdateCount_tOracleOutput_7!=-1?tmp_batchUpdateCount_tOracleOutput_7:0);
                batchSizeCounter_tOracleOutput_7 = 0;
            }
                commitCounter_tOracleOutput_7++;
                if(commitEvery_tOracleOutput_7 <= commitCounter_tOracleOutput_7) {

                        try {
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
                            pstmt_tOracleOutput_7.executeBatch();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
                        }catch (java.sql.BatchUpdateException e_tOracleOutput_7){
					        java.sql.SQLException ne_tOracleOutput_7 = e_tOracleOutput_7.getNextException(),sqle_tOracleOutput_7=null;
					    	String errormessage_tOracleOutput_7;
							if (ne_tOracleOutput_7 != null) {
								// build new exception to provide the original cause
								sqle_tOracleOutput_7 = new java.sql.SQLException(e_tOracleOutput_7.getMessage() + "\ncaused by: " + ne_tOracleOutput_7.getMessage(), ne_tOracleOutput_7.getSQLState(), ne_tOracleOutput_7.getErrorCode(), ne_tOracleOutput_7);
								errormessage_tOracleOutput_7 = sqle_tOracleOutput_7.getMessage();
							}else{
								errormessage_tOracleOutput_7 = e_tOracleOutput_7.getMessage();
							}
			            	
            log.error("tOracleOutput_7 - "  + (errormessage_tOracleOutput_7) );
			                	System.err.println(errormessage_tOracleOutput_7);
			            	
			        	}
                        tmp_batchUpdateCount_tOracleOutput_7 = pstmt_tOracleOutput_7.getUpdateCount();
                            insertedCount_tOracleOutput_7
                        += (tmp_batchUpdateCount_tOracleOutput_7!=-1?tmp_batchUpdateCount_tOracleOutput_7:0);
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_7)  + (" record(s).") );
                    conn_tOracleOutput_7.commit();
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection commit has succeeded.") );
                    commitCounter_tOracleOutput_7=0;
                }

 


	tos_count_tOracleOutput_7++;

/**
 * [tOracleOutput_7 main ] stop
 */
// Start of branch "row9"
if(row9 != null) { 



	
	/**
	 * [tAdvancedHash_row9 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

			//row9
			//row9


			
				if(execStat){
					runStat.updateStatOnConnection("row9"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row9 - " + (row9==null? "": row9.toLogString()));
    			}
    		


			   
			   

					row9Struct row9_HashRow = new row9Struct();
		   	   	   
				
				row9_HashRow.TRADER_ID = row9.TRADER_ID;
				
				row9_HashRow.BROKER_ID = row9.BROKER_ID;
				
				row9_HashRow.TIMESTAMP = row9.TIMESTAMP;
				
				row9_HashRow.COMPANY_ID = row9.COMPANY_ID;
				
				row9_HashRow.SECURITY_TP = row9.SECURITY_TP;
				
			tHash_Lookup_row9.put(row9_HashRow);
			
            




 


	tos_count_tAdvancedHash_row9++;

/**
 * [tAdvancedHash_row9 main ] stop
 */

} // End of branch "row9"







	
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
	 * [tOracleOutput_7 end ] start
	 */

	

	
	
	currentComponent="tOracleOutput_7";

	
	



	
            try {
            	if (pstmt_tOracleOutput_7 != null) {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Executing the ")  + ("INSERT")  + (" batch.") );
					pstmt_tOracleOutput_7.executeBatch();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("The ")  + ("INSERT")  + (" batch execution has succeeded.") );
        	    }
            }catch (java.sql.BatchUpdateException e_tOracleOutput_7){
		        java.sql.SQLException ne_tOracleOutput_7 = e_tOracleOutput_7.getNextException(),sqle_tOracleOutput_7=null;
		    	String errormessage_tOracleOutput_7;
				if (ne_tOracleOutput_7 != null) {
					// build new exception to provide the original cause
					sqle_tOracleOutput_7 = new java.sql.SQLException(e_tOracleOutput_7.getMessage() + "\ncaused by: " + ne_tOracleOutput_7.getMessage(), ne_tOracleOutput_7.getSQLState(), ne_tOracleOutput_7.getErrorCode(), ne_tOracleOutput_7);
					errormessage_tOracleOutput_7 = sqle_tOracleOutput_7.getMessage();
				}else{
					errormessage_tOracleOutput_7 = e_tOracleOutput_7.getMessage();
				}
            	
            log.error("tOracleOutput_7 - "  + (errormessage_tOracleOutput_7) );
                	System.err.println(errormessage_tOracleOutput_7);
            	
        	}
        	if (pstmt_tOracleOutput_7 != null) {
            	tmp_batchUpdateCount_tOracleOutput_7 = pstmt_tOracleOutput_7.getUpdateCount();
    	    	
    	    		insertedCount_tOracleOutput_7
    	    	
    	    	+= (tmp_batchUpdateCount_tOracleOutput_7!=-1?tmp_batchUpdateCount_tOracleOutput_7:0);
            }
        if(pstmt_tOracleOutput_7 != null) {
			
				pstmt_tOracleOutput_7.close();
			
        }
		if(commitCounter_tOracleOutput_7 > 0) {
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection starting to commit ")  + (commitCounter_tOracleOutput_7)  + (" record(s).") );
		    conn_tOracleOutput_7.commit();
			
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection commit has succeeded.") );
		}
		
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Closing the connection to the database.") );
		conn_tOracleOutput_7 .close();
		
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection to the database has closed.") );
		resourceMap.put("finish_tOracleOutput_7", true);
   	


	
	nb_line_deleted_tOracleOutput_7=nb_line_deleted_tOracleOutput_7+ deletedCount_tOracleOutput_7;
	nb_line_update_tOracleOutput_7=nb_line_update_tOracleOutput_7 + updatedCount_tOracleOutput_7;
	nb_line_inserted_tOracleOutput_7=nb_line_inserted_tOracleOutput_7 + insertedCount_tOracleOutput_7;
	nb_line_rejected_tOracleOutput_7=nb_line_rejected_tOracleOutput_7 + rejectedCount_tOracleOutput_7;
	
        globalMap.put("tOracleOutput_7_NB_LINE",nb_line_tOracleOutput_7);
        globalMap.put("tOracleOutput_7_NB_LINE_UPDATED",nb_line_update_tOracleOutput_7);
        globalMap.put("tOracleOutput_7_NB_LINE_INSERTED",nb_line_inserted_tOracleOutput_7);
        globalMap.put("tOracleOutput_7_NB_LINE_DELETED",nb_line_deleted_tOracleOutput_7);
        globalMap.put("tOracleOutput_7_NB_LINE_REJECTED", nb_line_rejected_tOracleOutput_7);
    
	
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Has ")  + ("inserted")  + (" ")  + (nb_line_inserted_tOracleOutput_7)  + (" record(s).") );



			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row7"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_7", true);
end_Hash.put("tOracleOutput_7", System.currentTimeMillis());




/**
 * [tOracleOutput_7 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row9 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

tHash_Lookup_row9.endPut();

			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row9"+iterateId,2, 0); 
			 	}
			}
		
 

ok_Hash.put("tAdvancedHash_row9", true);
end_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());




/**
 * [tAdvancedHash_row9 end ] stop
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
	 * [tOracleOutput_7 finally ] start
	 */

	

	
	
	currentComponent="tOracleOutput_7";

	



	
		if(resourceMap.get("finish_tOracleOutput_7")==null){
			if(resourceMap.get("conn_tOracleOutput_7")!=null){
				try {
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Closing the connection to the database.") );
					
					java.sql.Connection ctn_tOracleOutput_7 = (java.sql.Connection)resourceMap.get("conn_tOracleOutput_7");
					
					
            		
					ctn_tOracleOutput_7.close();
					
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_7 - "  + ("Connection to the database has closed.") );
				} catch (java.sql.SQLException sqlEx_tOracleOutput_7) {
					String errorMessage_tOracleOutput_7 = "failed to close the connection in tOracleOutput_7 :" + sqlEx_tOracleOutput_7.getMessage();
					
            log.error("tOracleOutput_7 - "  + (errorMessage_tOracleOutput_7) );
					System.err.println(errorMessage_tOracleOutput_7);
				}
			}
		}
	

 



/**
 * [tOracleOutput_7 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row9 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row9";

	

 



/**
 * [tAdvancedHash_row9 finally ] stop
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
	


public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
    final static byte[] commonByteArrayLock_MADHAV_FraudGen = new byte[0];
    static byte[] commonByteArray_MADHAV_FraudGen = new byte[0];

	
			    public Integer TRADER_ID;

				public Integer getTRADER_ID () {
					return this.TRADER_ID;
				}
				
			    public Integer BROKER_ID;

				public Integer getBROKER_ID () {
					return this.BROKER_ID;
				}
				
			    public Integer COMPANY_ID;

				public Integer getCOMPANY_ID () {
					return this.COMPANY_ID;
				}
				
			    public String SECURITY_TP;

				public String getSECURITY_TP () {
					return this.SECURITY_TP;
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

	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_MADHAV_FraudGen.length) {
				if(length < 1024 && commonByteArray_MADHAV_FraudGen.length == 0) {
   					commonByteArray_MADHAV_FraudGen = new byte[1024];
				} else {
   					commonByteArray_MADHAV_FraudGen = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_FraudGen, 0, length);
			strReturn = new String(commonByteArray_MADHAV_FraudGen, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_FraudGen) {

        	try {

        		int length = 0;
		
						this.TRADER_ID = readInteger(dis);
					
						this.BROKER_ID = readInteger(dis);
					
						this.COMPANY_ID = readInteger(dis);
					
					this.SECURITY_TP = readString(dis);
					
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
					
					// Integer
				
						writeInteger(this.COMPANY_ID,dos);
					
					// String
				
						writeString(this.SECURITY_TP,dos);
					
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
		sb.append(",COMPANY_ID="+String.valueOf(COMPANY_ID));
		sb.append(",SECURITY_TP="+SECURITY_TP);
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
        		
        	return sb.toString();
        }

    /**
     * Compare keys
     */
    public int compareTo(row12Struct other) {

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
public void tOracleInput_8Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tOracleInput_8_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {

			String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if( resumeEntryMethodName == null || resumeIt || globalResumeTicket){//start the resume
				globalResumeTicket = true;



		row12Struct row12 = new row12Struct();




	
	/**
	 * [tFlowToIterate_1 begin ] start
	 */

				
			int NB_ITERATE_tSetGlobalVar_1 = 0; //for statistics
			

	
		
		ok_Hash.put("tFlowToIterate_1", false);
		start_Hash.put("tFlowToIterate_1", System.currentTimeMillis());
		
	
	currentComponent="tFlowToIterate_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row12" + iterateId, 0, 0);
					
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
	 * [tOracleInput_8 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleInput_8", false);
		start_Hash.put("tOracleInput_8", System.currentTimeMillis());
		
	
	currentComponent="tOracleInput_8";

	
		int tos_count_tOracleInput_8 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tOracleInput_8 - "  + ("Start to work.") );
    	class BytesLimit65535_tOracleInput_8{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tOracleInput_8 = new StringBuilder();
            log4jParamters_tOracleInput_8.append("Parameters:");
                    log4jParamters_tOracleInput_8.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("USER" + " = " + "\"hr\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("TABLE" + " = " + "\"RANDOMIZER\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("QUERY" + " = " + "\"SELECT    RANDOMIZER.TRADER_ID,    RANDOMIZER.BROKER_ID,    RANDOMIZER.COMPANY_ID,    RANDOMIZER.SECURITY_TP FROM RANDOMIZER\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("PROPERTIES" + " = " + "\"\"");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("IS_CONVERT_XMLTYPE" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("USE_CURSOR" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("TRIM_ALL_COLUMN" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("BROKER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("COMPANY_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("SECURITY_TP")+"}]");
                log4jParamters_tOracleInput_8.append(" | ");
                    log4jParamters_tOracleInput_8.append("NO_NULL_VALUES" + " = " + "false");
                log4jParamters_tOracleInput_8.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tOracleInput_8 - "  + (log4jParamters_tOracleInput_8) );
    		}
    	}
    	
        new BytesLimit65535_tOracleInput_8().limitLog4jByte();
	


	
		    int nb_line_tOracleInput_8 = 0;
		    java.sql.Connection conn_tOracleInput_8 = null;
				String driverClass_tOracleInput_8 = "oracle.jdbc.OracleDriver";
				java.lang.Class.forName(driverClass_tOracleInput_8);
				
			String url_tOracleInput_8 = null;
				url_tOracleInput_8 = "jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";

				String dbUser_tOracleInput_8 = "hr";

				

				 
	final String decryptedPassword_tOracleInput_8 = routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");

				String dbPwd_tOracleInput_8 = decryptedPassword_tOracleInput_8;

				
	    		log.debug("tOracleInput_8 - Driver ClassName: "+driverClass_tOracleInput_8+".");
			
	    		log.debug("tOracleInput_8 - Connection attempt to '" + url_tOracleInput_8 + "' with the username '" + dbUser_tOracleInput_8 + "'.");
			
					conn_tOracleInput_8 = java.sql.DriverManager.getConnection(url_tOracleInput_8,dbUser_tOracleInput_8,dbPwd_tOracleInput_8);
	    		log.debug("tOracleInput_8 - Connection to '" + url_tOracleInput_8 + "' has succeeded.");
			
				java.sql.Statement stmtGetTZ_tOracleInput_8 = conn_tOracleInput_8.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_8 = stmtGetTZ_tOracleInput_8.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_8 = java.util.TimeZone.getDefault().getID();
				while (rsGetTZ_tOracleInput_8.next()) {
					sessionTimezone_tOracleInput_8 = rsGetTZ_tOracleInput_8.getString(1);
				}
				((oracle.jdbc.OracleConnection)conn_tOracleInput_8).setSessionTimeZone(sessionTimezone_tOracleInput_8);
		    
			java.sql.Statement stmt_tOracleInput_8 = conn_tOracleInput_8.createStatement();

		    String dbquery_tOracleInput_8 = "SELECT \n  RANDOMIZER.TRADER_ID, \n  RANDOMIZER.BROKER_ID, \n  RANDOMIZER.COMPANY_ID, \n  RANDOMIZER.SECURITY_TP\nFROM RANDOMIZER";
			
                log.debug("tOracleInput_8 - Executing the query: '"+dbquery_tOracleInput_8+"'.");
			

            	globalMap.put("tOracleInput_8_QUERY",dbquery_tOracleInput_8);
		    java.sql.ResultSet rs_tOracleInput_8 = null;

		    try {
		    	rs_tOracleInput_8 = stmt_tOracleInput_8.executeQuery(dbquery_tOracleInput_8);
		    	java.sql.ResultSetMetaData rsmd_tOracleInput_8 = rs_tOracleInput_8.getMetaData();
		    	int colQtyInRs_tOracleInput_8 = rsmd_tOracleInput_8.getColumnCount();

		    String tmpContent_tOracleInput_8 = null;
		    
		    
		    	log.debug("tOracleInput_8 - Retrieving records from the database.");
		    
		    while (rs_tOracleInput_8.next()) {
		        nb_line_tOracleInput_8++;
		        
							if(colQtyInRs_tOracleInput_8 < 1) {
								row12.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_8.getObject(1) != null) {
						row12.TRADER_ID = rs_tOracleInput_8.getInt(1);
					} else {
				
						row12.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_8 < 2) {
								row12.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_8.getObject(2) != null) {
						row12.BROKER_ID = rs_tOracleInput_8.getInt(2);
					} else {
				
						row12.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_8 < 3) {
								row12.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_8.getObject(3) != null) {
						row12.COMPANY_ID = rs_tOracleInput_8.getInt(3);
					} else {
				
						row12.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_8 < 4) {
								row12.SECURITY_TP = null;
							} else {
	                         		
        	row12.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_8, 4, false);
		                    }
					
						log.debug("tOracleInput_8 - Retrieving the record " + nb_line_tOracleInput_8 + ".");
					




 



/**
 * [tOracleInput_8 begin ] stop
 */
	
	/**
	 * [tOracleInput_8 main ] start
	 */

	

	
	
	currentComponent="tOracleInput_8";

	

 


	tos_count_tOracleInput_8++;

/**
 * [tOracleInput_8 main ] stop
 */

	
	/**
	 * [tFlowToIterate_1 main ] start
	 */

	

	
	
	currentComponent="tFlowToIterate_1";

	

			//row12
			//row12


			
				if(execStat){
					runStat.updateStatOnConnection("row12"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row12 - " + (row12==null? "": row12.toLogString()));
    			}
    		


    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row12.TRADER_ID, value=")  + (row12.TRADER_ID)  + (".") );            
            globalMap.put("row12.TRADER_ID", row12.TRADER_ID);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row12.BROKER_ID, value=")  + (row12.BROKER_ID)  + (".") );            
            globalMap.put("row12.BROKER_ID", row12.BROKER_ID);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row12.COMPANY_ID, value=")  + (row12.COMPANY_ID)  + (".") );            
            globalMap.put("row12.COMPANY_ID", row12.COMPANY_ID);
    	
                if(log.isTraceEnabled())
            log.trace("tFlowToIterate_1 - "  + ("Set global var, key=row12.SECURITY_TP, value=")  + (row12.SECURITY_TP)  + (".") );            
            globalMap.put("row12.SECURITY_TP", row12.SECURITY_TP);
    	
 
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
	       				runStat.updateStatOnConnection("out4", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("copyOfout1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row2", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("out5", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("out2", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("after_tOracleInput_6", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row3", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row5", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk2", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row5", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk8", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row4", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row8", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk5", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk6", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row13", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnComponentOk1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("out6", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk3", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row14", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk4", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("OnSubjobOk1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row6", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row10", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("after_tOracleInput_3", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("out3", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row9", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("out1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row11", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row7", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row1", 3, 0);
					}           			
				
					if(execStat){				
	       				runStat.updateStatOnConnection("row9", 3, 0);
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
                    log4jParamters_tSetGlobalVar_1.append("VARIABLES" + " = " + "[{VALUE="+("((Integer)globalMap.get(\"row12.TRADER_ID\"))")+", KEY="+("\"TRADER_ID\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row12.BROKER_ID\"))")+", KEY="+("\"BROKER_ID\"")+"}, {VALUE="+("((Integer)globalMap.get(\"row12.COMPANY_ID\"))")+", KEY="+("\"COMPANY_ID\"")+"}, {VALUE="+("((String)globalMap.get(\"row12.SECURITY_TP\"))")+", KEY="+("\"SECURITY_TP\"")+"}]");
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

	

globalMap.put("TRADER_ID", ((Integer)globalMap.get("row12.TRADER_ID")));
globalMap.put("BROKER_ID", ((Integer)globalMap.get("row12.BROKER_ID")));
globalMap.put("COMPANY_ID", ((Integer)globalMap.get("row12.COMPANY_ID")));
globalMap.put("SECURITY_TP", ((String)globalMap.get("row12.SECURITY_TP")));

 


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
	 * [tOracleInput_8 end ] start
	 */

	

	
	
	currentComponent="tOracleInput_8";

	

}
}finally{
stmt_tOracleInput_8.close();

	if(conn_tOracleInput_8 != null && !conn_tOracleInput_8.isClosed()) {
	
	    		log.debug("tOracleInput_8 - Closing the connection to the database.");
			
			conn_tOracleInput_8.close();
			
	    		log.debug("tOracleInput_8 - Connection to the database closed.");
			
	}
	
}

globalMap.put("tOracleInput_8_NB_LINE",nb_line_tOracleInput_8);
	    		log.debug("tOracleInput_8 - Retrieved records count: "+nb_line_tOracleInput_8 + " .");
			
 
                if(log.isDebugEnabled())
            log.debug("tOracleInput_8 - "  + ("Done.") );

ok_Hash.put("tOracleInput_8", true);
end_Hash.put("tOracleInput_8", System.currentTimeMillis());




/**
 * [tOracleInput_8 end ] stop
 */

	
	/**
	 * [tFlowToIterate_1 end ] start
	 */

	

	
	
	currentComponent="tFlowToIterate_1";

	

globalMap.put("tFlowToIterate_1_NB_LINE",nb_line_tFlowToIterate_1);
			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row12"+iterateId,2, 0); 
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
	 * [tOracleInput_8 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_8";

	

 



/**
 * [tOracleInput_8 finally ] stop
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
		

		globalMap.put("tOracleInput_8_SUBPROCESS_STATE", 1);
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
        final FraudGen FraudGenClass = new FraudGen();

        int exitCode = FraudGenClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'FraudGen' - Done.");
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
        	log.info("TalendJob: 'FraudGen' - Start.");
    	

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
            java.io.InputStream inContext = FraudGen.class.getClassLoader().getResourceAsStream("madhav/fraudgen_0_1/contexts/"+contextStr+".properties");
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
				    context.setContextType("TRADE_ID", "id_Integer");
				
             try{
                 context.TRADE_ID=routines.system.ParserUtils.parseTo_Integer (context.getProperty("TRADE_ID"));
             }catch(NumberFormatException e){
                 context.TRADE_ID=null;
              }
				    context.setContextType("BROKER_ID", "id_Integer");
				
             try{
                 context.BROKER_ID=routines.system.ParserUtils.parseTo_Integer (context.getProperty("BROKER_ID"));
             }catch(NumberFormatException e){
                 context.BROKER_ID=null;
              }
				    context.setContextType("SECURITY_TP", "id_String");
				
                context.SECURITY_TP=(String) context.getProperty("SECURITY_TP");
				    context.setContextType("COMPANY_ID", "id_Integer");
				
             try{
                 context.COMPANY_ID=routines.system.ParserUtils.parseTo_Integer (context.getProperty("COMPANY_ID"));
             }catch(NumberFormatException e){
                 context.COMPANY_ID=null;
              }
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }


        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {if (parentContextMap.containsKey("TRADE_ID")) {
                context.TRADE_ID = (Integer) parentContextMap.get("TRADE_ID");
            }if (parentContextMap.containsKey("BROKER_ID")) {
                context.BROKER_ID = (Integer) parentContextMap.get("BROKER_ID");
            }if (parentContextMap.containsKey("SECURITY_TP")) {
                context.SECURITY_TP = (String) parentContextMap.get("SECURITY_TP");
            }if (parentContextMap.containsKey("COMPANY_ID")) {
                context.COMPANY_ID = (Integer) parentContextMap.get("COMPANY_ID");
            }
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
errorCode = null;tOracleInput_8Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tOracleInput_8) {
globalMap.put("tOracleInput_8_SUBPROCESS_STATE", -1);

e_tOracleInput_8.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : FraudGen");
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
 *     612981 characters generated by Talend Data Integration 
 *     on the September 27, 2019 2:53:49 PM IST
 ************************************************************************************************/