package com.sunshine.first.wxapi;

import android.app.Activity;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * @author wavewave
 * @CreateDate: 2019-11-01 21:01
 * @Description:
 * @Version: 1.0
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("WXPayEntryActivity", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //这里肯定不能是像上面的DEMO一样弹出对话框了，而是通知我们发起支付调用的页面
            //然后及时finish掉这个页面，贴个伪代码：
//            sendPayNotice();
            finish();
        }
    }
}
