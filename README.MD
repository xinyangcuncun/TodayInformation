
# 今日快讯
## 分包规范

> 规划分包方案

1、业务代码按照功能分包，尽量做到模块化。
2、基础架构代码按照功能分包，尽量以组件的形式接入。

## 文件命名规范（讨论补充及添加）

这里指说明文件命名规范，常量、变量等编码规范不做深入说明。

>  基础类的命名：activity、fragment、adapter、listener等在命名中放在后面、如MineFragment、MineActivity。

>  接口的命名：所有的接口在类名前加入大写的I，如 IMineActivity。

>  抽象类的命名：所有的类名前加入大写的Abs，如 AbsMineActivity。

>  布局文件的命名：item、activity、fragment等均加在命名的前面、如activity_mine.

>  控件的命名：如item下的textview，命名为item_tv_xxx_xxx,在item的后面加入控件类型的缩写。

>  string 文件的命名 str开头。

>  assets 包名下的文件命名，xxx.文件类型，如xxx.mp4等。

>  color 文件的命名，cl 开头。

>  dimens 文件的命名,dim 开头。

> drawable ,shape selector等加在文件的开头，并用下划线隔开。

> gradle app 及 gradle project 添加maven 及 gradle 地址引用第三方包需添加注释。

> 清单文件添加activity、service等需添加注释。

> 除非必须，一般情况下context都是用application context

##代码分支规范

>开发者不可直接在  master 上 直接开发或者提交代码。

>应在master上 拉出 develop开发分支，然后每个开发者在 develop 上拉出自己的 feature_xxx_xxx分支，开发完毕后merge到develop，develop分支稳定且可靠后，在合并到master，这样做的目的是尽量避免master分支的代码回滚操作。

##整体工具类说明（要及时更新）