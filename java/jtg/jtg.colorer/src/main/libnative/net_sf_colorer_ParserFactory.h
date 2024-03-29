/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class net_sf_colorer_ParserFactory */

#ifndef _Included_net_sf_colorer_ParserFactory
#define _Included_net_sf_colorer_ParserFactory
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    init
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_net_sf_colorer_ParserFactory_init
  (JNIEnv *, jobject, jstring);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    finalize
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_net_sf_colorer_ParserFactory_finalize
  (JNIEnv *, jobject, jlong);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    getVersion
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_net_sf_colorer_ParserFactory_getVersion
  (JNIEnv *, jobject, jlong);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    enumerateHRDClasses
 * Signature: (JI)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_net_sf_colorer_ParserFactory_enumerateHRDClasses
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    enumerateHRDInstances
 * Signature: (JLjava/lang/String;I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_net_sf_colorer_ParserFactory_enumerateHRDInstances
  (JNIEnv *, jobject, jlong, jstring, jint);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    getHRDescription
 * Signature: (JLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_net_sf_colorer_ParserFactory_getHRDescription
  (JNIEnv *, jobject, jlong, jstring, jstring);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    getHRCParser
 * Signature: (J)Lnet/sf/colorer/HRCParser;
 */
JNIEXPORT jobject JNICALL Java_net_sf_colorer_ParserFactory_getHRCParser
  (JNIEnv *, jobject, jlong);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    createTextParser
 * Signature: (J)Lnet/sf/colorer/TextParser;
 */
JNIEXPORT jobject JNICALL Java_net_sf_colorer_ParserFactory_createTextParser
  (JNIEnv *, jobject, jlong);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    createStyledMapper
 * Signature: (JLjava/lang/String;Ljava/lang/String;)Lnet/sf/colorer/handlers/RegionMapper;
 */
JNIEXPORT jobject JNICALL Java_net_sf_colorer_ParserFactory_createStyledMapper
  (JNIEnv *, jobject, jlong, jstring, jstring);

/*
 * Class:     net_sf_colorer_ParserFactory
 * Method:    createTextMapper
 * Signature: (JLjava/lang/String;)Lnet/sf/colorer/handlers/RegionMapper;
 */
JNIEXPORT jobject JNICALL Java_net_sf_colorer_ParserFactory_createTextMapper
  (JNIEnv *, jobject, jlong, jstring);

#ifdef __cplusplus
}
#endif
#endif
