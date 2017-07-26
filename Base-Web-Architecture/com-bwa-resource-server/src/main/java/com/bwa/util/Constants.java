package com.bwa.util;

public interface Constants {

	String DEFAULT_ORGUNIT_ID ="01";

	String APPLICATION_JSON = "application/json";
	String APPLICATION_PLAIN_TXT = "text/plain";

	int FIRST_LAST_NAME_LENGTH_MIN = 1;
	int FIRST_LAST_NAME_LENGTH_MAX = 40;
	int EMAIL_ID_LENGTH_MIN = 1;
	int EMAIL_ID_LENGTH_MAX = 80;
	int USER_NAME_LENGTH_MIN = 3;
	int USER_NAME_LENGTH_MAX = 15;
	int PASSWORD_LENGTH_MIN = 8;
	int PASSWORD_LENGTH_MAX = 15;

	//Session related Constants
	String SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID = "orgUnitId";
	String SESSION_ATTRIBUTE_KEY_CUSTOMER_ID = "customerId";
	String SESSION_ATTRIBUTE_KEY_ROLE_ID = "idRole";
	String SESSION_ATTRIBUTE_KEY_DAT_CREATED = "datCreated";
	String SESSION_ATTRIBUTE_KEY_DAT_LAST_ACTIVITY = "lastActivityDate";

	//Date Related constants
	String DAT_PATTERN = "EEE, d MMM yyyy HH:mm:ss Z"; //Tue, 20 Jun 2017 16:30:41 +0000

	//Entering exiting msg for AOP
	String AOP_MSG_METHOD_BEFORE_ENTERING ="Entering Method ";
	String AOP_MSG_METHOD_AFTER_EXITING="Exiting Method ";
	String AOP_MSG_METHOD_AFTER_RETURNING="After Returning Method ";
}
