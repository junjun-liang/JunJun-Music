# JunJun-Music

整个界面可以看做是一个window，fitsSystemWindows 生效的前提是状态栏(StatusBar)或导航栏(NavigationBar)透明并且不能有标题栏，默认fitsSystemWindows = true,
表示页面布局(内容区)不会扩展到状态栏，会针对透明的状态栏会自动添加一个值等于状态栏高度的paddingTop；针对透明的系统导航栏会自动添加一个值等于导航栏高度的paddingBottom，
当fitsSystemWindows = false时，表示页面布局(内容区)扩展到状态栏，设置代码如下：

<style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
        <!--透明状态栏-->
        <item name="android:windowTranslucentStatus">true</item>
    </style>
为布局view设置：

android:fitsSystemWindows="false"

app:labelVisibilityMode="labeled"  
解决安卓底部导航大于3个时标题隐藏的问题
Android Design 28 版本 BottomNavigationView 使用