/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_crossmobile_backend_avian_AvianGraphicsBridge */

#ifndef _Included_org_crossmobile_backend_avian_AvianGraphicsBridge
#define _Included_org_crossmobile_backend_avian_AvianGraphicsBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_crossmobile_backend_avian_AvianGraphicsBridge
 * Method:    initSDL
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_crossmobile_backend_avian_AvianGraphicsBridge_initSDL
  (JNIEnv *, jclass);

/*
 * Class:     org_crossmobile_backend_avian_AvianGraphicsBridge
 * Method:    quitSDL
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_crossmobile_backend_avian_AvianGraphicsBridge_quitSDL
  (JNIEnv *, jclass);

/*
 * Class:     org_crossmobile_backend_avian_AvianGraphicsBridge
 * Method:    pollSDLEvents
 * Signature: ()Lorg/crossmobile/backend/avian/event/AvianEvent;
 */
JNIEXPORT jobject JNICALL Java_org_crossmobile_backend_avian_AvianGraphicsBridge_pollSDLEvents
  (JNIEnv *, jclass);

/*
 * Class:     org_crossmobile_backend_avian_AvianGraphicsBridge
 * Method:    setDebugLevel
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_crossmobile_backend_avian_AvianGraphicsBridge_setDebugLevel
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif
