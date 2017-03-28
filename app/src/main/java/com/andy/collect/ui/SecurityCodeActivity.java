package com.andy.collect.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andy.collect.R;
import com.andy.collect.widget.Code;

/**
 * see blog：http://blog.csdn.net/wk843620202/article/details/50960904
 *
 * @author andy he
 * @date 2017/3/28 15:20
 */
public class SecurityCodeActivity extends BaseAct {
    public static final String TAG = SecurityCodeActivity.class.getName();
    private ImageView iv_showCode;
    private EditText et_phoneCode;
    private EditText et_phoneNum;
    private Button btn_toSetCode;
    //产生的验证码
    private String realCode;

    @Override
    protected int loadLayoutId() {
        return R.layout.act_security_code;
    }

    @Override
    protected void initViews() {
        iv_showCode = bindView(R.id.iv_showCode);
        et_phoneCode = bindView(R.id.et_phoneCodes);
        et_phoneNum = bindView(R.id.et_forgetPass_PhoneNum);
        btn_toSetCode = bindView(R.id.btn_toSetCode);
    }

    @Override
    protected void bindListener() {
        iv_showCode.setOnClickListener(this);
        btn_toSetCode.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //将验证码用图片的形式显示出来
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_showCode:
                iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                Log.v(TAG, "realCode" + realCode);
                break;
            case R.id.btn_toSetCode:
                String phoneCode = et_phoneCode.getText().toString().toLowerCase();
                String msg = "生成的验证码：" + realCode + "输入的验证码:" + phoneCode;
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                if (phoneCode.equals(realCode)) {
                    Toast.makeText(this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, phoneCode + "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
