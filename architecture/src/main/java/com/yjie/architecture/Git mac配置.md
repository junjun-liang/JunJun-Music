# Git mac配置

### 配置git

- 创建ssh key
    1. 设置username和email

        ```bash
        git config --global user.name "yjie"
        git config --global user.email "lyj1989428@163.com"
        ```

    2. 通过命令创建ssh key

        ```bash
        Last login: Sat Feb  6 13:30:48 on ttys000
        ➜  ~ ssh-keygen -t rsa -C "[lyj1989428@163.com](mailto:lyj1989428@163.com)"
        Generating public/private rsa key pair.
        Enter file in which to save the key (/Users/yjie/.ssh/id_rsa):
        Created directory '/Users/yjie/.ssh'.
        Enter passphrase (empty for no passphrase):
        Enter same passphrase again:
        Your identification has been saved in /Users/yjie/.ssh/id_rsa.
        Your public key has been saved in /Users/yjie/.ssh/id_rsa.pub.
        The key fingerprint is:
        SHA256:dcZgdqTUXyOxGXrJBy8++/zmK9ddsQBOV/RX6s3anLs [lyj1989428@163.com](mailto:lyj1989428@163.com)The key's randomart image is:
        +---[RSA 2048]----+
        |          =oo++o.|
        |         + B+oBo+|
        |          =.*Oo++|
        |         . +oo++.|
        |        S    oo =|
        |              o=o|
        |             ...*|
        |             .o.=|
        |              oE*|
        +----[SHA256]-----+

        ```

    3. 查看key

        ```bash
        ➜  ~ cat .ssh/id_rsa.pub
        ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDJmaiSMrFkNRj7qeWte6l8AMaiE0DkCYfuYfM0ZcKFyCMXmKa2CkQjHogVwgCWBA5M/UMzpjRieYGN9kMQBJ3v+AZ4O9XNXr3cAKhVEDzG5rTsAbUSPj4UYJC+2ql9pXlEZMUYDc6OHZesagzZG1ynZ99lsbGyz3tTdjr6uLXuw4imzI+QqOaUGRz3cjKKxpGomFvE49vm8SC+c8OOnT7m01d6miy9hZQ5l20PdFfVv3FgW+pWrdAIXC+pOnIgGyvRKJLoqiZq5vx0C6EjxsppPlnLNxypMJslMthPQs6C0JHZ1mnIofh4n9Nl+yp8iKHdKzvIFadxPqQvPhHHGZnb lyj1989428@163.com

        ```

    4. 登录GitHub（默认你已经注册了GitHub账号），添加ssh key，点击Settings
    5. 链接验证

        ```bash
        ➜  ~ ssh -T git@github.com
        Enter passphrase for key '/Users/yjie/.ssh/id_rsa':
        Hi YingjieLiang! You've successfully authenticated, but GitHub does not provide shell access.  

        ```

    6. 在GitHub上新创建一个 repository或者Start a Project
    7. 填写项目信息
    8. Clone工程到本地(先进入本地创建的项目对应的路径下/Users/yjie/Documents/Android/Learn)，首先复制ssh 地址

        ```bash
        ➜  ~ cd /Users/yjie/Documents/
        ➜  Documents cd /Android/
        cd: no such file or directory: /Android/
        ➜  Documents cd Android/
        ➜  Android cd Learn
        ➜  Learn git clone git@github.com:YingjieLiang/Learn.git
        Cloning into 'Learn'...
        Enter passphrase for key '/Users/yjie/.ssh/id_rsa':
        remote: Enumerating objects: 5, done.
        remote: Counting objects: 100% (5/5), done.
        remote: Compressing objects: 100% (4/4), done.
        remote: Total 5 (delta 0), reused 0 (delta 0), pack-reused 0
        Receiving objects: 100% (5/5), 4.73 KiB | 4.73 MiB/s, done.
        ```

    9. 把GitHub上下载的.gitignore，LICENSE和README.md三个文件复制到本地项目的根目录
    10. 把本地代码同步push到github上 (需要重新初始化git，命令是git init)

        ```bash
        ➜  Learn ls -al
        total 64
        drwxr-xr-x  14 yjie  staff   448  2  7 21:59 .
        drwxr-xr-x  19 yjie  staff   608  2  7 21:57 ..
        -rw-r--r--   1 yjie  staff   225  2  7 21:57 .gitignore
        drwxr-xr-x   6 yjie  staff   192  2  7 21:57 .gradle
        drwxr-xr-x   8 yjie  staff   256  2  7 21:57 .idea
        drwxr-xr-x   6 yjie  staff   192  2  7 21:59 Learn
        drwxr-xr-x   7 yjie  staff   224  2  7 21:57 app
        -rw-r--r--   1 yjie  staff   642  2  7 21:57 build.gradle
        drwxr-xr-x   3 yjie  staff    96  2  7 21:57 gradle
        -rw-r--r--   1 yjie  staff  1184  2  7 21:57 gradle.properties
        -rwxr--r--   1 yjie  staff  5296  2  7 21:57 gradlew
        -rw-r--r--   1 yjie  staff  2260  2  7 21:57 gradlew.bat
        -rw-r--r--   1 yjie  staff   433  2  7 21:57 local.properties
        -rw-r--r--   1 yjie  staff    41  2  7 21:57 settings.gradle
        ➜  Learn git add .
        ➜  Learn git:(main) ✗ git commit -m "First commit"
        [main 043277e] First commit
         60 files changed, 1104 insertions(+)
         create mode 100644 .DS_Store
         create mode 100644 .gradle/6.5/fileHashes/fileHashes.lock
         create mode 100644 .gradle/buildOutputCleanup/buildOutputCleanup.lock
         create mode 100644 .gradle/buildOutputCleanup/cache.properties
         create mode 100644 .gradle/checksums/checksums.lock
         create mode 100644 .gradle/checksums/md5-checksums.bin
         create mode 100644 .gradle/checksums/sha1-checksums.bin
         create mode 100644 .idea/.gitignore
         create mode 100644 .idea/gradle.xml
         create mode 100644 .idea/misc.xml
         create mode 100644 .idea/modules.xml
         create mode 100644 .idea/modules/Learn.iml
         create mode 100644 app/.gitignore
         create mode 100644 app/build.gradle
         create mode 100644 app/proguard-rules.pro
         create mode 100644 app/src/androidTest/java/com/example/learn/ExampleInstrumentedTest.kt
         create mode 100644 app/src/main/AndroidManifest.xml
         create mode 100644 app/src/main/java/com/example/learn/MainActivity.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/dashboard/DashboardFragment.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/dashboard/DashboardViewModel.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/home/HomeFragment.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/home/HomeViewModel.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/notifications/NotificationsFragment.kt
         create mode 100644 app/src/main/java/com/example/learn/ui/notifications/NotificationsViewModel.kt
         create mode 100644 app/src/main/res/drawable-v24/ic_launcher_foreground.xml
         create mode 100644 app/src/main/res/drawable/ic_dashboard_black_24dp.xml
         create mode 100644 app/src/main/res/drawable/ic_home_black_24dp.xml
         create mode 100644 app/src/main/res/drawable/ic_launcher_background.xml
         create mode 100644 app/src/main/res/drawable/ic_notifications_black_24dp.xml
         create mode 100644 app/src/main/res/layout/activity_main.xml
         create mode 100644 app/src/main/res/layout/fragment_dashboard.xml
         create mode 100644 app/src/main/res/layout/fragment_home.xml
         create mode 100644 app/src/main/res/layout/fragment_notifications.xml
         create mode 100644 app/src/main/res/menu/bottom_nav_menu.xml
         create mode 100644 app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml
         create mode 100644 app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml
         create mode 100644 app/src/main/res/mipmap-hdpi/ic_launcher.png
         create mode 100644 app/src/main/res/mipmap-hdpi/ic_launcher_round.png
         create mode 100644 app/src/main/res/mipmap-mdpi/ic_launcher.png
         create mode 100644 app/src/main/res/mipmap-mdpi/ic_launcher_round.png
         create mode 100644 app/src/main/res/mipmap-xhdpi/ic_launcher.png
         create mode 100644 app/src/main/res/mipmap-xhdpi/ic_launcher_round.png
         create mode 100644 app/src/main/res/mipmap-xxhdpi/ic_launcher.png
         create mode 100644 app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png
         create mode 100644 app/src/main/res/mipmap-xxxhdpi/ic_launcher.png
         create mode 100644 app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png
         create mode 100644 app/src/main/res/navigation/mobile_navigation.xml
         create mode 100644 app/src/main/res/values-night/themes.xml
         create mode 100644 app/src/main/res/values/colors.xml
         create mode 100644 app/src/main/res/values/dimens.xml
         create mode 100644 app/src/main/res/values/strings.xml
         create mode 100644 app/src/main/res/values/themes.xml
         create mode 100644 app/src/test/java/com/example/learn/ExampleUnitTest.kt
         create mode 100644 build.gradle
         create mode 100644 gradle.properties
         create mode 100644 gradle/wrapper/gradle-wrapper.properties
         create mode 100755 gradlew
         create mode 100644 gradlew.bat
         create mode 100644 local.properties
         create mode 100644 settings.gradle
        ➜  Learn git:(main) git push origin main
        Enter passphrase for key '/Users/yjie/.ssh/id_rsa':
        Enumerating objects: 106, done.
        Counting objects: 100% (106/106), done.
        Delta compression using up to 6 threads
        Compressing objects: 100% (79/79), done.
        Writing objects: 100% (105/105), 93.62 KiB | 10.40 MiB/s, done.
        Total 105 (delta 10), reused 0 (delta 0)
        remote: Resolving deltas: 100% (10/10), done.
        To github.com:YingjieLiang/Learn.git
           bdf6101..043277e  main -> main
        ➜  Learn git:(main)
        ```

    Mac上github密码是JIE597368

    网页gitHub  @jie1989

    ```bash
    常见错误：

    fatal: not a git repository (or any of the parent directories): .git

    重现初始化 git init

    fatal: 'origin' does not appear to be a git repository
    fatal: Could not read from remote repository.

    ```