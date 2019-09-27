
package madhav.merger_0_1;

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
 * Job: Merger Purpose: <br>
 * Description:  <br>
 * @author madhav2800@gmail.com
 * @version 6.5.1.20180116_1512
 * @status 
 */
public class Merger implements TalendJob {
	static {System.setProperty("TalendJob.log", "Merger.log");}
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Merger.class);



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
	private final String jobName = "Merger";
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
				Merger.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(Merger.this, new Object[] { e , currentComponent, globalMap});
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
			
			public void tOracleOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_2_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleOutput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tOracleInput_3_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tCreateTable_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tCreateTable_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tOracleInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_2_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tOracleInput_3_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
			public void tCreateTable_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
		




	

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_Merger) {

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
                    log4jParamters_tOracleOutput_1.append("TABLE" + " = " + "\"MASTERSTAGE2\"");
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
            tableName_tOracleOutput_1 = ("MASTERSTAGE2");
        } else {
            tableName_tOracleOutput_1 = dbschema_tOracleOutput_1 + "." + ("MASTERSTAGE2");
        }
                                String tableNameForSearch_tOracleOutput_1= "" + ((String)"MASTERSTAGE2") + "";
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
                    log4jParamters_tOracleInput_1.append("TABLE" + " = " + "\"STAGE3\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_1.append(" | ");
                    log4jParamters_tOracleInput_1.append("QUERY" + " = " + "\"SELECT    STAGE3.TRADER_ID,    STAGE3.BROKER_ID,    STAGE3.TIMESTAMP,    STAGE3.COMPANY_ID,    STAGE3.SECURITY_TP,    STAGE3.TRADE_TP,    STAGE3.QUANTITY,    STAGE3.PRICE FROM STAGE3\"");
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
                    log4jParamters_tOracleInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("BROKER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TIMESTAMP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("COMPANY_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("SECURITY_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
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

		    String dbquery_tOracleInput_1 = "SELECT \n  STAGE3.TRADER_ID, \n  STAGE3.BROKER_ID, \n  STAGE3.TIMESTAMP, \n  STAGE3.COMPANY_ID, \n  STAGE3.SECURITY_TP, \n  STAGE3.TRADE_TP, \n  STAGE3.QUANTITY, \n  STAGE3.PRICE\nFROM STAGE3";
			
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
								row1.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(1) != null) {
						row1.TRADER_ID = rs_tOracleInput_1.getInt(1);
					} else {
				
						row1.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 2) {
								row1.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(2) != null) {
						row1.BROKER_ID = rs_tOracleInput_1.getInt(2);
					} else {
				
						row1.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 3) {
								row1.TIMESTAMP = null;
							} else {
										
			row1.TIMESTAMP = routines.system.JDBCUtil.getDate(rs_tOracleInput_1, 3);
		                    }
							if(colQtyInRs_tOracleInput_1 < 4) {
								row1.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(4) != null) {
						row1.COMPANY_ID = rs_tOracleInput_1.getInt(4);
					} else {
				
						row1.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 5) {
								row1.SECURITY_TP = null;
							} else {
	                         		
        	row1.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_1, 5, false);
		                    }
							if(colQtyInRs_tOracleInput_1 < 6) {
								row1.TRADE_TP = null;
							} else {
	                         		
        	row1.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_1, 6, false);
		                    }
							if(colQtyInRs_tOracleInput_1 < 7) {
								row1.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(7) != null) {
						row1.QUANTITY = rs_tOracleInput_1.getInt(7);
					} else {
				
						row1.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_1 < 8) {
								row1.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_1.getObject(8) != null) {
						row1.PRICE = rs_tOracleInput_1.getFloat(8);
					} else {
				
						row1.PRICE = null;
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
pstmt_tOracleOutput_1.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_1.setFloat(8, row1.PRICE);
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



				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_1:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
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
	 * [tOracleInput_1 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_1";

	

 



/**
 * [tOracleInput_1 finally ] stop
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
		

		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
			    public Integer TRADE_ID;

				public Integer getTRADE_ID () {
					return this.TRADE_ID;
				}
				
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_Merger) {

        	try {

        		int length = 0;
		
						this.TRADE_ID = readInteger(dis);
					
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
				
						writeInteger(this.TRADE_ID,dos);
					
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
		sb.append("TRADE_ID="+String.valueOf(TRADE_ID));
		sb.append(",TRADER_ID="+String.valueOf(TRADER_ID));
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
        	
        				if(TRADE_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADE_ID);
            			}
            		
        			sb.append("|");
        		
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

public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_Merger) {

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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_Merger) {

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



		row2Struct row2 = new row2Struct();
row3Struct row3 = new row3Struct();
out1Struct out1 = new out1Struct();






	
	/**
	 * [tOracleOutput_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_3", false);
		start_Hash.put("tOracleOutput_3", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_3";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out1" + iterateId, 0, 0);
					
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
                    log4jParamters_tOracleOutput_3.append("TABLE" + " = " + "\"MASTER\"");
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
            tableName_tOracleOutput_3 = ("MASTER");
        } else {
            tableName_tOracleOutput_3 = dbschema_tOracleOutput_3 + "." + ("MASTER");
        }
                                String tableNameForSearch_tOracleOutput_3= "" + ((String)"MASTER") + "";
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
                                    stmtCreate_tOracleOutput_3.execute("CREATE TABLE " + tableName_tOracleOutput_3 + "(TRADE_ID INT ,TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_3)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_3.close();
                String insert_tOracleOutput_3 = "INSERT INTO " + tableName_tOracleOutput_3 + " (TRADE_ID,TRADER_ID,BROKER_ID,TIMESTAMP,COMPANY_ID,SECURITY_TP,TRADE_TP,QUANTITY,PRICE) VALUES (?,?,?,?,?,?,?,?,?)";    

                        java.sql.PreparedStatement pstmt_tOracleOutput_3 = conn_tOracleOutput_3.prepareStatement(insert_tOracleOutput_3);





 



/**
 * [tOracleOutput_3 begin ] stop
 */



	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_1", false);
		start_Hash.put("tMap_1", System.currentTimeMillis());
		
	
	currentComponent="tMap_1";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row3" + iterateId, 0, 0);
					
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
		int count_row3_tMap_1 = 0;
		
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
	 * [tOracleOutput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_2", false);
		start_Hash.put("tOracleOutput_2", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_2";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("row2" + iterateId, 0, 0);
					
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
                    log4jParamters_tOracleOutput_2.append("TABLE" + " = " + "\"MASTERSTAGE3\"");
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
            tableName_tOracleOutput_2 = ("MASTERSTAGE3");
        } else {
            tableName_tOracleOutput_2 = dbschema_tOracleOutput_2 + "." + ("MASTERSTAGE3");
        }
                                String tableNameForSearch_tOracleOutput_2= "" + ((String)"MASTERSTAGE3") + "";
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
                    log4jParamters_tOracleInput_2.append("TABLE" + " = " + "\"MASTERSTAGE2\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_2.append(" | ");
                    log4jParamters_tOracleInput_2.append("QUERY" + " = " + "\"select * from MASTERSTAGE2 order by dbms_random.value\"");
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

		    String dbquery_tOracleInput_2 = "select * from MASTERSTAGE2 order by dbms_random.value";
			
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
								row2.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(1) != null) {
						row2.TRADER_ID = rs_tOracleInput_2.getInt(1);
					} else {
				
						row2.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 2) {
								row2.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(2) != null) {
						row2.BROKER_ID = rs_tOracleInput_2.getInt(2);
					} else {
				
						row2.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 3) {
								row2.TIMESTAMP = null;
							} else {
										
			row2.TIMESTAMP = routines.system.JDBCUtil.getDate(rs_tOracleInput_2, 3);
		                    }
							if(colQtyInRs_tOracleInput_2 < 4) {
								row2.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(4) != null) {
						row2.COMPANY_ID = rs_tOracleInput_2.getInt(4);
					} else {
				
						row2.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 5) {
								row2.SECURITY_TP = null;
							} else {
	                         		
        	row2.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_2, 5, false);
		                    }
							if(colQtyInRs_tOracleInput_2 < 6) {
								row2.TRADE_TP = null;
							} else {
	                         		
        	row2.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_2, 6, false);
		                    }
							if(colQtyInRs_tOracleInput_2 < 7) {
								row2.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(7) != null) {
						row2.QUANTITY = rs_tOracleInput_2.getInt(7);
					} else {
				
						row2.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_2 < 8) {
								row2.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_2.getObject(8) != null) {
						row2.PRICE = rs_tOracleInput_2.getFloat(8);
					} else {
				
						row2.PRICE = null;
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
	 * [tOracleOutput_2 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_2";

	

			//row2
			//row2


			
				if(execStat){
					runStat.updateStatOnConnection("row2"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row2 - " + (row2==null? "": row2.toLogString()));
    			}
    		



            row3 = null;
        whetherReject_tOracleOutput_2 = false;
                        if(row2.TRADER_ID == null) {
pstmt_tOracleOutput_2.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(1, row2.TRADER_ID);
}

                        if(row2.BROKER_ID == null) {
pstmt_tOracleOutput_2.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(2, row2.BROKER_ID);
}

                        if(row2.TIMESTAMP != null) {
pstmt_tOracleOutput_2.setObject(3, new java.sql.Timestamp(row2.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_2.setNull(3, java.sql.Types.DATE);
}

                        if(row2.COMPANY_ID == null) {
pstmt_tOracleOutput_2.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(4, row2.COMPANY_ID);
}

                        if(row2.SECURITY_TP == null) {
pstmt_tOracleOutput_2.setNull(5, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(5, row2.SECURITY_TP);
}

                        if(row2.TRADE_TP == null) {
pstmt_tOracleOutput_2.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_2.setString(6, row2.TRADE_TP);
}

                        if(row2.QUANTITY == null) {
pstmt_tOracleOutput_2.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_2.setInt(7, row2.QUANTITY);
}

                        if(row2.PRICE == null) {
pstmt_tOracleOutput_2.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_2.setFloat(8, row2.PRICE);
}

                pstmt_tOracleOutput_2.addBatch();
                nb_line_tOracleOutput_2++;
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Adding the record ")  + (nb_line_tOracleOutput_2)  + (" to the ")  + ("INSERT")  + (" batch.") );
                batchSizeCounter_tOracleOutput_2++;
            if(!whetherReject_tOracleOutput_2) {
                            row3 = new row3Struct();
                                row3.TRADER_ID = row2.TRADER_ID;
                                row3.BROKER_ID = row2.BROKER_ID;
                                row3.TIMESTAMP = row2.TIMESTAMP;
                                row3.COMPANY_ID = row2.COMPANY_ID;
                                row3.SECURITY_TP = row2.SECURITY_TP;
                                row3.TRADE_TP = row2.TRADE_TP;
                                row3.QUANTITY = row2.QUANTITY;
                                row3.PRICE = row2.PRICE;
            }
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
// Start of branch "row3"
if(row3 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

			//row3
			//row3


			
				if(execStat){
					runStat.updateStatOnConnection("row3"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row3 - " + (row3==null? "": row3.toLogString()));
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

out1_tmp.TRADE_ID = Numeric.sequence("s1",1,1);
out1_tmp.TRADER_ID = row3.TRADER_ID ;
out1_tmp.BROKER_ID = row3.BROKER_ID ;
out1_tmp.TIMESTAMP = row3.TIMESTAMP ;
out1_tmp.COMPANY_ID = row3.COMPANY_ID ;
out1_tmp.SECURITY_TP = row3.SECURITY_TP ;
out1_tmp.TRADE_TP = row3.TRADE_TP ;
out1_tmp.QUANTITY = row3.QUANTITY ;
out1_tmp.PRICE = row3.PRICE ;
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
	 * [tOracleOutput_3 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_3";

	

			//out1
			//out1


			
				if(execStat){
					runStat.updateStatOnConnection("out1"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out1 - " + (out1==null? "": out1.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_3 = false;
                        if(out1.TRADE_ID == null) {
pstmt_tOracleOutput_3.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(1, out1.TRADE_ID);
}

                        if(out1.TRADER_ID == null) {
pstmt_tOracleOutput_3.setNull(2, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(2, out1.TRADER_ID);
}

                        if(out1.BROKER_ID == null) {
pstmt_tOracleOutput_3.setNull(3, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(3, out1.BROKER_ID);
}

                        if(out1.TIMESTAMP != null) {
pstmt_tOracleOutput_3.setObject(4, new java.sql.Timestamp(out1.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_3.setNull(4, java.sql.Types.DATE);
}

                        if(out1.COMPANY_ID == null) {
pstmt_tOracleOutput_3.setNull(5, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(5, out1.COMPANY_ID);
}

                        if(out1.SECURITY_TP == null) {
pstmt_tOracleOutput_3.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_3.setString(6, out1.SECURITY_TP);
}

                        if(out1.TRADE_TP == null) {
pstmt_tOracleOutput_3.setNull(7, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_3.setString(7, out1.TRADE_TP);
}

                        if(out1.QUANTITY == null) {
pstmt_tOracleOutput_3.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_3.setInt(8, out1.QUANTITY);
}

                        if(out1.PRICE == null) {
pstmt_tOracleOutput_3.setNull(9, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_3.setFloat(9, out1.PRICE);
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

} // End of branch "out1"





} // End of branch "row3"







	
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
			 		runStat.updateStatOnConnection("row2"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_2 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_2", true);
end_Hash.put("tOracleOutput_2", System.currentTimeMillis());




/**
 * [tOracleOutput_2 end ] stop
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
			 		runStat.updateStatOnConnection("row3"+iterateId,2, 0); 
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
			 		runStat.updateStatOnConnection("out1"+iterateId,2, 0); 
			 	}
			}
		
 
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_3 - "  + ("Done.") );

ok_Hash.put("tOracleOutput_3", true);
end_Hash.put("tOracleOutput_3", System.currentTimeMillis());




/**
 * [tOracleOutput_3 end ] stop
 */









				}//end the resume

				
				    			if(resumeEntryMethodName == null || globalResumeTicket){
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_2:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
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
	 * [tOracleInput_2 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_2";

	

 



/**
 * [tOracleInput_2 finally ] stop
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

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
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









				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tOracleInput_2_SUBPROCESS_STATE", 1);
	}
	


public static class out2Struct implements routines.system.IPersistableRow<out2Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
			    public Integer TRADEID;

				public Integer getTRADEID () {
					return this.TRADEID;
				}
				
			    public String TRADETYPE;

				public String getTRADETYPE () {
					return this.TRADETYPE;
				}
				
			    public java.util.Date TIMESTAMP;

				public java.util.Date getTIMESTAMP () {
					return this.TIMESTAMP;
				}
				
			    public Integer VOLUME;

				public Integer getVOLUME () {
					return this.VOLUME;
				}
				
			    public Integer TRADERID;

				public Integer getTRADERID () {
					return this.TRADERID;
				}
				
			    public Integer BROKERID;

				public Integer getBROKERID () {
					return this.BROKERID;
				}
				
			    public String SECURITYTYPE;

				public String getSECURITYTYPE () {
					return this.SECURITYTYPE;
				}
				
			    public Float PRICE;

				public Float getPRICE () {
					return this.PRICE;
				}
				
			    public Integer SYMBOLID;

				public Integer getSYMBOLID () {
					return this.SYMBOLID;
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_MADHAV_Merger) {

        	try {

        		int length = 0;
		
						this.TRADEID = readInteger(dis);
					
					this.TRADETYPE = readString(dis);
					
					this.TIMESTAMP = readDate(dis);
					
						this.VOLUME = readInteger(dis);
					
						this.TRADERID = readInteger(dis);
					
						this.BROKERID = readInteger(dis);
					
					this.SECURITYTYPE = readString(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.PRICE = null;
           				} else {
           			    	this.PRICE = dis.readFloat();
           				}
					
						this.SYMBOLID = readInteger(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// Integer
				
						writeInteger(this.TRADEID,dos);
					
					// String
				
						writeString(this.TRADETYPE,dos);
					
					// java.util.Date
				
						writeDate(this.TIMESTAMP,dos);
					
					// Integer
				
						writeInteger(this.VOLUME,dos);
					
					// Integer
				
						writeInteger(this.TRADERID,dos);
					
					// Integer
				
						writeInteger(this.BROKERID,dos);
					
					// String
				
						writeString(this.SECURITYTYPE,dos);
					
					// Float
				
						if(this.PRICE == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.PRICE);
		            	}
					
					// Integer
				
						writeInteger(this.SYMBOLID,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("TRADEID="+String.valueOf(TRADEID));
		sb.append(",TRADETYPE="+TRADETYPE);
		sb.append(",TIMESTAMP="+String.valueOf(TIMESTAMP));
		sb.append(",VOLUME="+String.valueOf(VOLUME));
		sb.append(",TRADERID="+String.valueOf(TRADERID));
		sb.append(",BROKERID="+String.valueOf(BROKERID));
		sb.append(",SECURITYTYPE="+SECURITYTYPE);
		sb.append(",PRICE="+String.valueOf(PRICE));
		sb.append(",SYMBOLID="+String.valueOf(SYMBOLID));
	    sb.append("]");

	    return sb.toString();
    }
        public String toLogString(){
        	StringBuilder sb = new StringBuilder();
        	
        				if(TRADEID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADEID);
            			}
            		
        			sb.append("|");
        		
        				if(TRADETYPE == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADETYPE);
            			}
            		
        			sb.append("|");
        		
        				if(TIMESTAMP == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TIMESTAMP);
            			}
            		
        			sb.append("|");
        		
        				if(VOLUME == null){
        					sb.append("<null>");
        				}else{
            				sb.append(VOLUME);
            			}
            		
        			sb.append("|");
        		
        				if(TRADERID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADERID);
            			}
            		
        			sb.append("|");
        		
        				if(BROKERID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(BROKERID);
            			}
            		
        			sb.append("|");
        		
        				if(SECURITYTYPE == null){
        					sb.append("<null>");
        				}else{
            				sb.append(SECURITYTYPE);
            			}
            		
        			sb.append("|");
        		
        				if(PRICE == null){
        					sb.append("<null>");
        				}else{
            				sb.append(PRICE);
            			}
            		
        			sb.append("|");
        		
        				if(SYMBOLID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(SYMBOLID);
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

public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
    final static byte[] commonByteArrayLock_MADHAV_Merger = new byte[0];
    static byte[] commonByteArray_MADHAV_Merger = new byte[0];

	
			    public Integer TRADE_ID;

				public Integer getTRADE_ID () {
					return this.TRADE_ID;
				}
				
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
			if(length > commonByteArray_MADHAV_Merger.length) {
				if(length < 1024 && commonByteArray_MADHAV_Merger.length == 0) {
   					commonByteArray_MADHAV_Merger = new byte[1024];
				} else {
   					commonByteArray_MADHAV_Merger = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_MADHAV_Merger, 0, length);
			strReturn = new String(commonByteArray_MADHAV_Merger, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_MADHAV_Merger) {

        	try {

        		int length = 0;
		
						this.TRADE_ID = readInteger(dis);
					
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
				
						writeInteger(this.TRADE_ID,dos);
					
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
		sb.append("TRADE_ID="+String.valueOf(TRADE_ID));
		sb.append(",TRADER_ID="+String.valueOf(TRADER_ID));
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
        	
        				if(TRADE_ID == null){
        					sb.append("<null>");
        				}else{
            				sb.append(TRADE_ID);
            			}
            		
        			sb.append("|");
        		
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



		row4Struct row4 = new row4Struct();
out2Struct out2 = new out2Struct();





	
	/**
	 * [tOracleOutput_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tOracleOutput_4", false);
		start_Hash.put("tOracleOutput_4", System.currentTimeMillis());
		
	
	currentComponent="tOracleOutput_4";

	
			if (execStat) {
				if(resourceMap.get("inIterateVComp") == null){
					
						runStat.updateStatOnConnection("out2" + iterateId, 0, 0);
					
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
                    log4jParamters_tOracleOutput_4.append("TABLE" + " = " + "\"TRADEBOOK\"");
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
            tableName_tOracleOutput_4 = ("TRADEBOOK");
        } else {
            tableName_tOracleOutput_4 = dbschema_tOracleOutput_4 + "." + ("TRADEBOOK");
        }
                                String tableNameForSearch_tOracleOutput_4= "" + ((String)"TRADEBOOK") + "";
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
                                    stmtCreate_tOracleOutput_4.execute("CREATE TABLE " + tableName_tOracleOutput_4 + "(TRADEID INT ,TRADETYPE VARCHAR2(100)  ,TIMESTAMP DATE ,VOLUME INT ,TRADERID INT ,BROKERID INT ,SECURITYTYPE VARCHAR2(100)  ,PRICE FLOAT ,SYMBOLID INT )");
                if(log.isDebugEnabled())
            log.debug("tOracleOutput_4 - "  + ("Create")  + (" table '")  + (tableName_tOracleOutput_4)  + ("' has succeeded.") );
                                stmtCreate_tOracleOutput_4.close();
                String insert_tOracleOutput_4 = "INSERT INTO " + tableName_tOracleOutput_4 + " (TRADEID,TRADETYPE,TIMESTAMP,VOLUME,TRADERID,BROKERID,SECURITYTYPE,PRICE,SYMBOLID) VALUES (?,?,?,?,?,?,?,?,?)";    

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
					
						runStat.updateStatOnConnection("row4" + iterateId, 0, 0);
					
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
		int count_row4_tMap_2 = 0;
		
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
// ###############################

        
        



        









 



/**
 * [tMap_2 begin ] stop
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
                    log4jParamters_tOracleInput_3.append("TABLE" + " = " + "\"MASTER\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("QUERYSTORE" + " = " + "\"\"");
                log4jParamters_tOracleInput_3.append(" | ");
                    log4jParamters_tOracleInput_3.append("QUERY" + " = " + "\"SELECT    MASTER.TRADE_ID,    MASTER.TRADER_ID,    MASTER.BROKER_ID,    MASTER.TIMESTAMP,    MASTER.COMPANY_ID,    MASTER.SECURITY_TP,    MASTER.TRADE_TP,    MASTER.QUANTITY,    MASTER.PRICE FROM MASTER\"");
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
                    log4jParamters_tOracleInput_3.append("TRIM_COLUMN" + " = " + "[{TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TRADER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("BROKER_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TIMESTAMP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("COMPANY_ID")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("SECURITY_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("TRADE_TP")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("QUANTITY")+"}, {TRIM="+("false")+", SCHEMA_COLUMN="+("PRICE")+"}]");
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

		    String dbquery_tOracleInput_3 = "SELECT \n  MASTER.TRADE_ID, \n  MASTER.TRADER_ID, \n  MASTER.BROKER_ID, \n  MASTER.TIMESTAMP, \n  MASTER.COMPANY_ID, \n  MASTER.SECURITY_TP, \n  MASTER.TRADE_TP, \n  MASTER.QUANTITY, \n  MASTER.PRICE\nFROM MASTER";
			
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
								row4.TRADE_ID = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(1) != null) {
						row4.TRADE_ID = rs_tOracleInput_3.getInt(1);
					} else {
				
						row4.TRADE_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 2) {
								row4.TRADER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(2) != null) {
						row4.TRADER_ID = rs_tOracleInput_3.getInt(2);
					} else {
				
						row4.TRADER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 3) {
								row4.BROKER_ID = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(3) != null) {
						row4.BROKER_ID = rs_tOracleInput_3.getInt(3);
					} else {
				
						row4.BROKER_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 4) {
								row4.TIMESTAMP = null;
							} else {
										
			row4.TIMESTAMP = routines.system.JDBCUtil.getDate(rs_tOracleInput_3, 4);
		                    }
							if(colQtyInRs_tOracleInput_3 < 5) {
								row4.COMPANY_ID = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(5) != null) {
						row4.COMPANY_ID = rs_tOracleInput_3.getInt(5);
					} else {
				
						row4.COMPANY_ID = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 6) {
								row4.SECURITY_TP = null;
							} else {
	                         		
        	row4.SECURITY_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_3, 6, false);
		                    }
							if(colQtyInRs_tOracleInput_3 < 7) {
								row4.TRADE_TP = null;
							} else {
	                         		
        	row4.TRADE_TP = routines.system.JDBCUtil.getString(rs_tOracleInput_3, 7, false);
		                    }
							if(colQtyInRs_tOracleInput_3 < 8) {
								row4.QUANTITY = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(8) != null) {
						row4.QUANTITY = rs_tOracleInput_3.getInt(8);
					} else {
				
						row4.QUANTITY = null;
					}
		                    }
							if(colQtyInRs_tOracleInput_3 < 9) {
								row4.PRICE = null;
							} else {
		                          
					if(rs_tOracleInput_3.getObject(9) != null) {
						row4.PRICE = rs_tOracleInput_3.getFloat(9);
					} else {
				
						row4.PRICE = null;
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
	 * [tMap_2 main ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

			//row4
			//row4


			
				if(execStat){
					runStat.updateStatOnConnection("row4"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("row4 - " + (row4==null? "": row4.toLogString()));
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


// # Output table : 'out2'
count_out2_tMap_2++;

out2_tmp.TRADEID = row4.TRADE_ID ;
out2_tmp.TRADETYPE = row4.TRADE_TP ;
out2_tmp.TIMESTAMP = row4.TIMESTAMP ;
out2_tmp.VOLUME = row4.QUANTITY ;
out2_tmp.TRADERID = row4.TRADER_ID ;
out2_tmp.BROKERID = row4.BROKER_ID ;
out2_tmp.SECURITYTYPE = row4.SECURITY_TP ;
out2_tmp.PRICE = row4.PRICE ;
out2_tmp.SYMBOLID = row4.COMPANY_ID ;
out2 = out2_tmp;
log.debug("tMap_2 - Outputting the record " + count_out2_tMap_2 + " of the output table 'out2'.");

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
	 * [tOracleOutput_4 main ] start
	 */

	

	
	
	currentComponent="tOracleOutput_4";

	

			//out2
			//out2


			
				if(execStat){
					runStat.updateStatOnConnection("out2"+iterateId,1, 1);
				} 
			

		
    			if(log.isTraceEnabled()){
    				log.trace("out2 - " + (out2==null? "": out2.toLogString()));
    			}
    		



        whetherReject_tOracleOutput_4 = false;
                        if(out2.TRADEID == null) {
pstmt_tOracleOutput_4.setNull(1, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(1, out2.TRADEID);
}

                        if(out2.TRADETYPE == null) {
pstmt_tOracleOutput_4.setNull(2, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_4.setString(2, out2.TRADETYPE);
}

                        if(out2.TIMESTAMP != null) {
pstmt_tOracleOutput_4.setObject(3, new java.sql.Timestamp(out2.TIMESTAMP.getTime()),java.sql.Types.DATE);
} else {
pstmt_tOracleOutput_4.setNull(3, java.sql.Types.DATE);
}

                        if(out2.VOLUME == null) {
pstmt_tOracleOutput_4.setNull(4, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(4, out2.VOLUME);
}

                        if(out2.TRADERID == null) {
pstmt_tOracleOutput_4.setNull(5, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(5, out2.TRADERID);
}

                        if(out2.BROKERID == null) {
pstmt_tOracleOutput_4.setNull(6, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(6, out2.BROKERID);
}

                        if(out2.SECURITYTYPE == null) {
pstmt_tOracleOutput_4.setNull(7, java.sql.Types.VARCHAR);
} else {pstmt_tOracleOutput_4.setString(7, out2.SECURITYTYPE);
}

                        if(out2.PRICE == null) {
pstmt_tOracleOutput_4.setNull(8, java.sql.Types.FLOAT);
} else {pstmt_tOracleOutput_4.setFloat(8, out2.PRICE);
}

                        if(out2.SYMBOLID == null) {
pstmt_tOracleOutput_4.setNull(9, java.sql.Types.INTEGER);
} else {pstmt_tOracleOutput_4.setInt(9, out2.SYMBOLID);
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

} // End of branch "out2"







	
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
	 * [tMap_2 end ] start
	 */

	

	
	
	currentComponent="tMap_2";

	


// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_2 - Written records count in the table 'out2': " + count_out2_tMap_2 + ".");





			if(execStat){
				if(resourceMap.get("inIterateVComp") == null || !((Boolean)resourceMap.get("inIterateVComp"))){
			 		runStat.updateStatOnConnection("row4"+iterateId,2, 0); 
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
			 		runStat.updateStatOnConnection("out2"+iterateId,2, 0); 
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
				    				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tOracleInput_3:OnSubjobOk", "", Thread.currentThread().getId() + "", "", "", "", "", "");
								}	    				    			
					    	
								if(execStat){    	
									runStat.updateStatOnConnection("OnSubjobOk3", 0, "ok");
								} 
							
							tCreateTable_1Process(globalMap); 
						



	
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
	 * [tOracleInput_3 finally ] start
	 */

	

	
	
	currentComponent="tOracleInput_3";

	

 



/**
 * [tOracleInput_3 finally ] stop
 */

	
	/**
	 * [tMap_2 finally ] start
	 */

	

	
	
	currentComponent="tMap_2";

	

 



/**
 * [tMap_2 finally ] stop
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
		

		globalMap.put("tOracleInput_3_SUBPROCESS_STATE", 1);
	}
	

public void tCreateTable_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tCreateTable_1_SUBPROCESS_STATE", 0);

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
	 * [tCreateTable_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tCreateTable_1", false);
		start_Hash.put("tCreateTable_1", System.currentTimeMillis());
		
	
	currentComponent="tCreateTable_1";

	
		int tos_count_tCreateTable_1 = 0;
		
                if(log.isDebugEnabled())
            log.debug("tCreateTable_1 - "  + ("Start to work.") );
    	class BytesLimit65535_tCreateTable_1{
    		public void limitLog4jByte() throws Exception{
    			
            StringBuilder log4jParamters_tCreateTable_1 = new StringBuilder();
            log4jParamters_tCreateTable_1.append("Parameters:");
                    log4jParamters_tCreateTable_1.append("DBTYPE" + " = " + "DBORACLE");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("TABLEACTION" + " = " + "DROP_IF_EXIST_CREATE");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("USE_EXISTING_CONNECTION" + " = " + "false");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("HOST" + " = " + "\"localhost\"");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("DBNAME" + " = " + "\"xe\"");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("USER" + " = " + "\"hr\"");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("PASS" + " = " + String.valueOf("f57ef8d9ade3a048").substring(0, 4) + "...");     
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("PORT" + " = " + "\"1521\"");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("SCHEMA_DB" + " = " + "\"\"");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("CONNECTION_TYPE" + " = " + "ORACLE_SID");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("DB_VERSION" + " = " + "ORACLE_12");
                log4jParamters_tCreateTable_1.append(" | ");
                    log4jParamters_tCreateTable_1.append("TABLE" + " = " + "\"Stage3\"");
                log4jParamters_tCreateTable_1.append(" | ");
                if(log.isDebugEnabled())
            log.debug("tCreateTable_1 - "  + (log4jParamters_tCreateTable_1) );
    		}
    	}
    	
        new BytesLimit65535_tCreateTable_1().limitLog4jByte();

 



/**
 * [tCreateTable_1 begin ] stop
 */
	
	/**
	 * [tCreateTable_1 main ] start
	 */

	

	
	
	currentComponent="tCreateTable_1";

	

    try{
            String driverClass_tCreateTable_1="oracle.jdbc.driver.OracleDriver";
String url_tCreateTable_1="jdbc:oracle:thin:@" + "localhost" + ":" + "1521" + ":" + "xe";
String dbUser_tCreateTable_1="hr";

	    		log.debug("tCreateTable_1 - Driver ClassName: "+driverClass_tCreateTable_1+".");
			
        
	    		log.debug("tCreateTable_1 - Connection attempt to '" + url_tCreateTable_1 + "' with the username '" + dbUser_tCreateTable_1 + "'.");
			String tableName_tCreateTable_1 = "Stage3";
String dbSchema_tCreateTable_1 = "";
if(dbSchema_tCreateTable_1 != null && dbSchema_tCreateTable_1.trim().length() != 0) {tableName_tCreateTable_1 = dbSchema_tCreateTable_1 + "." + tableName_tCreateTable_1;
}java.lang.Class.forName("oracle.jdbc.driver.OracleDriver");
final String decryptedPassword_tCreateTable_1= routines.system.PasswordEncryptUtil.decryptPassword("f57ef8d9ade3a048");
java.sql.Connection conn_tCreateTable_1= java.sql.DriverManager.getConnection(url_tCreateTable_1,dbUser_tCreateTable_1, decryptedPassword_tCreateTable_1);

	    		log.debug("tCreateTable_1 - Connection to '" + url_tCreateTable_1 + "' has succeeded.");
			
            boolean whetherExist_tCreateTable_1 = false;
				String tableNameForSearch_tCreateTable_1= "" + ((String)"Stage3") + "";
String dbschemaForSearch_tCreateTable_1= null;
if(dbSchema_tCreateTable_1== null || dbSchema_tCreateTable_1.trim().length() == 0) {
dbschemaForSearch_tCreateTable_1= null;
} else {
dbschemaForSearch_tCreateTable_1= dbSchema_tCreateTable_1.toUpperCase();
}

	            java.sql.DatabaseMetaData dbMetaData_tCreateTable_1 = conn_tCreateTable_1.getMetaData();
	            if(tableNameForSearch_tCreateTable_1.indexOf("\"")==-1){
            		tableNameForSearch_tCreateTable_1 = tableNameForSearch_tCreateTable_1.toUpperCase();
            	}else{
            		tableNameForSearch_tCreateTable_1 = tableNameForSearch_tCreateTable_1.replaceAll("\"","");
            	}
	            java.sql.ResultSet rsTable_tCreateTable_1 = dbMetaData_tCreateTable_1.getTables(null, dbschemaForSearch_tCreateTable_1, tableNameForSearch_tCreateTable_1, new String[]{"TABLE"});
	            if(rsTable_tCreateTable_1.next()) {
	            	whetherExist_tCreateTable_1 = true;
	            }     
            rsTable_tCreateTable_1.close();
                if(whetherExist_tCreateTable_1) {
                    java.sql.Statement stmtDrop_tCreateTable_1 = conn_tCreateTable_1.createStatement();
                        log.info("tCreateTable_1 - Droping table '" + tableName_tCreateTable_1 + "'.");
                    stmtDrop_tCreateTable_1.execute("DROP TABLE " + tableName_tCreateTable_1 + "");
                        log.info("tCreateTable_1 - Drop table '" + tableName_tCreateTable_1 + "' has succeeded.");
                }
                java.sql.Statement stmt_tCreateTable_1 = conn_tCreateTable_1.createStatement();
                    log.info("tCreateTable_1 - Creating table '" + tableName_tCreateTable_1 + "'.");
                stmt_tCreateTable_1.execute("CREATE TABLE " + tableName_tCreateTable_1 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )"); 
                    log.info("tCreateTable_1 - Create table '" + tableName_tCreateTable_1 + "' has succeeded.");
	            
	    		log.debug("tCreateTable_1 - Closing the connection to the database.");
			
        if(conn_tCreateTable_1 != null && !conn_tCreateTable_1.isClosed()) {
conn_tCreateTable_1.close();
}

	    		log.debug("tCreateTable_1 - Connection to the database closed.");
			
        globalMap.put("tCreateTable_1_QUERY", "CREATE TABLE " + tableName_tCreateTable_1 + "(TRADER_ID INT ,BROKER_ID INT ,TIMESTAMP DATE ,COMPANY_ID INT ,SECURITY_TP VARCHAR2(100)  ,TRADE_TP VARCHAR2(100)  ,QUANTITY INT ,PRICE FLOAT )");        
    } catch(java.lang.Exception e) {
        globalMap.put("tCreateTable_1_ERROR_MESSAGE",e.getMessage());
        throw new RuntimeException("Creating table failed", e);
    }


 


	tos_count_tCreateTable_1++;

/**
 * [tCreateTable_1 main ] stop
 */
	
	/**
	 * [tCreateTable_1 end ] start
	 */

	

	
	
	currentComponent="tCreateTable_1";

	

 
                if(log.isDebugEnabled())
            log.debug("tCreateTable_1 - "  + ("Done.") );

ok_Hash.put("tCreateTable_1", true);
end_Hash.put("tCreateTable_1", System.currentTimeMillis());




/**
 * [tCreateTable_1 end ] stop
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
	 * [tCreateTable_1 finally ] start
	 */

	

	
	
	currentComponent="tCreateTable_1";

	

 



/**
 * [tCreateTable_1 finally ] stop
 */
				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tCreateTable_1_SUBPROCESS_STATE", 1);
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
        final Merger MergerClass = new Merger();

        int exitCode = MergerClass.runJobInTOS(args);
	        if(exitCode==0){
		        log.info("TalendJob: 'Merger' - Done.");
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
        	log.info("TalendJob: 'Merger' - Start.");
    	

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
            java.io.InputStream inContext = Merger.class.getClassLoader().getResourceAsStream("madhav/merger_0_1/contexts/"+contextStr+".properties");
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
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Merger");
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
 *     224977 characters generated by Talend Data Integration 
 *     on the September 27, 2019 2:53:50 PM IST
 ************************************************************************************************/