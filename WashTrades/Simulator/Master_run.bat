%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/advancedPersistentLookupLib-1.0.jar;../lib/commons-collections-3.2.2.jar;../lib/dom4j-1.6.1.jar;../lib/jboss-serialization.jar;../lib/log4j-1.2.15.jar;../lib/log4j-1.2.16.jar;../lib/ojdbc7.jar;../lib/talend_file_enhanced_20070724.jar;../lib/trove.jar;master_0_1.jar;masterdatagen_0_1.jar;combfcp_0_1.jar;fraudgen_0_1.jar;merger_0_1.jar; madhav.master_0_1.Master --context=Default %* 