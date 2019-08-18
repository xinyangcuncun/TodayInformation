

## 播放器配置信息

> 如何配置

- 在清单文件中配置 meta-data信息
- name 值必须为 playertype
- value 值 可取范围 1~4

> 代码示例

     <meta-data
                 android:name="playertype"
                 android:value="1" />
     // value = 1 :MediaPlayer
     // value = 2 :ExoPlayer
     ...

