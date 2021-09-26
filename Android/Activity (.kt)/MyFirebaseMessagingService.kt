package com.example.catcha

import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService

/*
다음과 같은 경우에 등록 토큰이 변경될 수 있습니다.
- 새 기기에서 앱 복원
- 사용자가 앱 삭제/재설치
- 사용자가 앱 데이터 소거
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        // 토큰이 갱신될 때마다 처리 해주는 작업 여기에 필요 (실무에서)
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // FCM 수신 마다 실행
        super.onMessageReceived(message)
    }

}