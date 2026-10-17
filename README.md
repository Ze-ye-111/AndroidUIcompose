# Android 界面组件实验

一个基于 **Java + Android View** 的界面组件练习项目，包含实验三的 5 个可运行示例。进入应用首页后，可分别打开各实验页面进行体验。

## 功能

- **ListView**：使用 `SimpleAdapter` 展示动物图片和名称；点击列表项显示 Toast，并发送带应用图标的系统通知。
- **自定义 AlertDialog**：通过 XML 布局和 `AlertDialog.Builder.setView()` 创建登录对话框，支持用户名、密码校验。
- **XML 菜单**：使用 XML 定义菜单，支持切换 10/16/20 号字体、修改红/黑字体颜色，以及普通菜单项 Toast。
- **ActionMode 上下文菜单**：长按 ListView 列表项进入上下文操作模式，显示选中状态，并支持删除列表项。
- **RecyclerView**：使用 Adapter 和 ViewHolder 展示动物列表；点击名称后立即在列表末尾新增一条记录。

## 技术栈

- Java
- AndroidX AppCompat
- Material Components
- RecyclerView
- Gradle

## 运行方式

1. 使用 Android Studio 打开项目。
2. 等待 Gradle 同步完成。
3. 运行 `app` 模块，在首页选择需要测试的实验。
4. Android 13 及以上系统首次进入 ListView 实验时，请允许通知权限。

## 项目结构

```text
app/src/main/
├── java/com/example/androiduicompose/   # Activity、Adapter、数据模型
├── res/layout/                          # 页面及列表项布局
├── res/menu/                            # XML 菜单资源
├── res/drawable-nodpi/                  # 动物图片资源
└── AndroidManifest.xml
```
Android 界面组件实验效果图展示
<img width="375" height="771" alt="image" src="https://github.com/user-attachments/assets/28720bf8-d8c7-42bb-8ae2-8c34ad661d40" />
<img width="364" height="762" alt="image" src="https://github.com/user-attachments/assets/f10501e1-2614-4eaf-9eb0-55b23c3ee4ca" />
<img width="378" height="766" alt="image" src="https://github.com/user-attachments/assets/e00cc882-a9ac-467f-a468-3c7e57342df4" />
<img width="357" height="759" alt="image" src="https://github.com/user-attachments/assets/14eb130b-858c-47f3-976d-9f36929bc744" />
<img width="353" height="769" alt="image" src="https://github.com/user-attachments/assets/bd775b16-3608-4ff7-9f84-905e01046390" />
<img width="365" height="756" alt="image" src="https://github.com/user-attachments/assets/4f0928b3-388a-453d-841b-595385dd65d1" />
