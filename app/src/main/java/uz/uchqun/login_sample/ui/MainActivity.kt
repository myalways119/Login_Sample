package uz.uchqun.login_sample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.R
import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.repository.CommonRepository
import uz.uchqun.login_sample.core.util.Utils.CheckNetworkState
import uz.uchqun.login_sample.core.util.Utils.CheckPermissions
import uz.uchqun.login_sample.core.util.Utils.ExitApp
import uz.uchqun.login_sample.core.util.Utils.GetAndroidId
import uz.uchqun.login_sample.core.util.Utils.GetDevicePhoneNo
import uz.uchqun.login_sample.core.util.Utils.startNewActivity
import uz.uchqun.login_sample.ui.auth.AuthActivity
import uz.uchqun.login_sample.ui.home.HomeActivity

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StartNextActivity()
    }

    fun StartNextActivity()
    {
        val userPreferences = UserPreferences(this)        
        var devicePhoneNo:String = GetDevicePhoneNo(this)
        var deviceAndroidId:String = GetAndroidId(this)

        if(CheckPermissions(this) == false)
        {//권한이 없는 경우
            startNewActivity(AuthActivity::class.java)//권한 설정 화면
        }
        else if(!CheckNetworkState(this))
        {//인터넷 연결이 안된 경우
            Toast.makeText(this, "인터넷 연결 상태를 확인 후, 다시 시도하시기 바랍니다.", Toast.LENGTH_LONG).show();
            ExitApp(this)
        }
        else if( devicePhoneNo.isNullOrEmpty() || deviceAndroidId.isNullOrEmpty())
        {//device의 폰번호 가져오지 못한 경우,
            Toast.makeText(this, "앱을 실행 할 수 없습니다. 관리자에게 문의 하시기 바랍니다.", Toast.LENGTH_LONG).show();
            ExitApp(this)
        }
        else if(userPreferences.phoneNo.equals(devicePhoneNo) && userPreferences.androidId.equals(deviceAndroidId))
        {//자동 로그인 (앱재설치 X, 전화번호 변경 X, 폰 초기화 X)
            startNewActivity(HomeActivity::class.java)
        }else
        {
            startNewActivity(AuthActivity::class.java) //login Activity
        }
    }

    fun GetUserInfo(phoneNo:String)
    {
        var commonRpty: CommonRepository = CommonRepository()

        CoroutineScope(Main).launch {
            commonRpty.SearchUserInfo(phoneNo)
        }

    }
}