package com.news.today.todayinformation.main.beijing.view;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.beijing.util.ByteUtil;
import com.news.today.todayinformation.main.beijing.util.MD5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;

/**
 * Created by anson on 2019-11-17.
 */
@ViewInject(mainlayoutid = R.layout.activity_safe)
public class SafeActivity extends BaseActivity {

    @BindView(R.id.tv_md5)
    TextView mTvMd5;

    @BindView(R.id.tv_sp)
    TextView mTvSp;

    @BindView(R.id.tv_aes)
    TextView mTvAes;

    @BindView(R.id.tv_xposed)
    TextView mTvXposed;

    boolean isAdle = true;

    @Override
    public void afterBindView() {

        mTvMd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvMd5.setText("加密后 = " + MD5.md5Decode32("todayInformation"));
            }
        });

        mTvSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("password", 123456);
                edit.apply();
                mTvSp.setText("密码存储完毕");
            }
        });

        mTvAes.setOnClickListener(new View.OnClickListener() {
            //原文 or 密文
            String content = "AES对称加密";
            //秘钥
            String key = "todayinformation";
            //"算法/工作模式/填充模式"
            String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
            @Override
            public void onClick(View v) {
                if (isAdle) {
                    //去加密
                    try {
                        //生成秘钥
                        SecretKey secretKey = new SecretKeySpec(key.getBytes("utf-8"), "AES");
                        //加密解密器
                        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                        //Cipher.ENCRYPT_MODE 加密模式
                        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
                        //bytes 解密后的字节数组
                        byte[] bytes = cipher.doFinal(content.getBytes());
                        //如果不转成16进制 会出现乱码，因为我们加密的原文有中文
                        content = ByteUtil.parseByte2HexStr(bytes);
                        mTvAes.setText("加密后的结果 = " + content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //去解密
                    try {
                        SecretKey secretKey = new SecretKeySpec(key.getBytes("utf-8"), "AES");
                        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                        //仅仅只有这里不一样 解密模式
                        cipher.init(Cipher.DECRYPT_MODE,secretKey);
                        byte[] bytes = cipher.doFinal(ByteUtil.parseHexStr2Byte(content));
                        content = new String(bytes,"UTF-8");
                        mTvAes.setText("解密后的结果 = " + content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isAdle = !isAdle;
            }
        });

        mTvXposed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvXposed.setText(getInfo());
            }
        });

    }

    private String getInfo() {
        return "xposed是我弟弟";
    }
}
