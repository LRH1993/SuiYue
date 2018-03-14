# 碎阅

## 一、动机

利用这段时间学习一些新的内容，在大作业的基础上准备做一个资讯类的APP。

数据来源于第三方平台，不做于任何商业用途，如有侵权，立刻删除。

## 二、项目框架

### 图书

利用豆瓣图书API

| url                                      | 描述                                |
| ---------------------------------------- | --------------------------------- |
| https://api.douban.com/v2/book/series/:id/books | 获取一系列📚 其中:id可以替换成任意数字            |
| https://api.douban.com/v2/book/isbn/:name | 根据isbn获取📚详细信息，其中:name可以替换成isbn号码 |
| https://api.douban.com/v2/book/search    | 搜索📚，可以设置参数搜索相关📚                 |

其中，关于搜索的具体参数设置如下：

| *参数*  | *意义*       | *备注*         |
| ----- | ---------- | ------------ |
| q     | 查询关键字      | q和tag必传其一    |
| tag   | 查询的tag     | q和tag必传其一    |
| start | 取结果的offset | 默认为0         |
| count | 取结果的条数     | 默认为20，最大为100 |

### 故事

利用ONE API

| url                                      | 描述                                      |
| ---------------------------------------- | --------------------------------------- |
| http://v3.wufazhuce.com:8000/api/onelist/idlist | 获取最新 idlist, 以获取今日或往日的 onelist 信息       |
| http://v3.wufazhuce.com:8000/api/onelist/:data/0 | 获取某一天的onelist,其中:data替换成上面的idlist中的数据   |
| http://v3.wufazhuce.com:8000/api/essay/:item_id | 获取故事详细信息，其中:item_id替换成onelist中的item_id值 |

### 电影

利用豆瓣电影API

| url                                      | 描述                    |
| ---------------------------------------- | --------------------- |
| https://api.douban.com/v2/movie/in_theaters | 上映的🎬                 |
| http://api.douban.com/v2/movie/subject/:id | 🎬详情信息，其中:id可以替换成🎬id |
| https://api.douban.com/v2/movie/coming_soon | 即将上映的🎬               |
| http://api.douban.com/v2/movie/top250    | Top250🎬              |

## 三、技术点

- 语言Kotlin
- 图片框架Fresco
- 网络框架Okhttp
- 列表RecycleView
- 二维码ZXing
- 通信框架EventBus
- 安全加固
- 代码混淆
- MVP模式

## 四、Screenshot

| ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E6%96%87%E7%AB%A0%E9%A1%B5%E9%9D%A2.jpeg) | ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E5%9B%BE%E4%B9%A6%E9%A1%B5%E9%9D%A2.jpeg) | ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E7%94%B5%E5%BD%B1%E9%A1%B5%E9%9D%A2.jpeg) |
| ---------------------------------------- | ---------------------------------------- | ---------------------------------------- |
| ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E6%96%87%E7%AB%A0%E8%AF%A6%E6%83%85%E9%A1%B5%E9%9D%A2.jpeg) | ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E5%9B%BE%E4%B9%A6%E8%AF%A6%E6%83%85%E9%A1%B5%E9%9D%A2.jpeg) | ![](https://github.com/LRH1993/SuiYue/blob/master/screenshot/%E7%94%B5%E5%BD%B1%E8%AF%A6%E6%83%85%E9%A1%B5%E9%9D%A2.jpeg) |





## 五、学习输出

[从google todo-mvp示例再次学习MVP](https://juejin.im/post/5a9654f86fb9a0634a39450e)

[ScrollView嵌套RecyclerView滑动冲突相关问题](https://juejin.im/post/5aa8c2f1f265da237c689946)









