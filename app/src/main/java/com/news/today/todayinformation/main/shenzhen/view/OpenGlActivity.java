package com.news.today.todayinformation.main.shenzhen.view;

import android.opengl.GLSurfaceView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.web.god.opengl.OpenGlManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;

/**
 * Created by anson on 2019-11-17.
 */
@ViewInject(mainlayoutid = R.layout.activity_opengl)
public class OpenGlActivity extends BaseActivity {

    @BindView(R.id.gl_surface_view)
    GLSurfaceView glSurfaceView;
    @Override
    public void afterBindView() {

        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                // 为缓冲区 设置清除颜色的值 相当于初始化
//                gl.glClearColor(0.0f,0.0f,1.0f,1.0f);
                OpenGlManager.onSurfaceCreated();
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                // 设置 视图 大小
//                gl.glViewport(0,0,width,height);
                OpenGlManager.onSurfaceChanged(width,height);
            }


            //绘制的时候 每一帧 都会被系统调用 在Android中 默认最高绘制效率 为 1秒 60帧
            @Override
            public void onDrawFrame(GL10 gl) {
                // 设置色彩
//                gl.glClear(gl.GL_COLOR_BUFFER_BIT);
                OpenGlManager.onDrawFrame();
            }
        });

    }
}
