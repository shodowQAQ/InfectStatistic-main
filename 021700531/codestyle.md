# 缩进
* 缩进采用4个空格，禁止使用tab字符。
# 变量命名
* 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
* 代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。
# 每行最多字符数
* 单行字符数限制不超过 120个，超出需要换行.
# 函数最大行数
* 单个函数最大不超过80行
# 函数、类命名
* 类名使用UpperCamelCase风格，必须遵从驼峰形式
* 方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格，必须遵从驼峰形式。
* 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。
# 常量
* 不允许出现任何魔法值（即未经定义的常量）直接出现在代码中。
* long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解。
* 不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护。如：缓存相关的常量放在类：CacheConsts下；系统配置相关的常量放在类：ConfigConsts下。
* 常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、包内共享常量、类内共享常量。
* 如果变量值仅在一个范围内变化用Enum类。如果还带有名称之外的延伸属性，必须使用Enum类，下面正例中的数字就是延伸信息，表示星期几。
# 空行规则
* 方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。
# 注释规则
* 类、类属性、类方法的注释必须使用Javadoc规范，使用/*内容/格式，不得使用//xxx方式。
* 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/ */注释，注意与代码对齐。
# 操作符前后空格
	任何运算符左右必须加一个空格。
# 其他规则
* 在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。
* 任何数据结构的构造或初始化，都应指定大小，避免数据结构无限增长吃光内存。
* 对于“明确停止使用的代码和配置”，如方法、变量、类、配置文件、动态配置属性等要坚决从程序中清理出去，避免造成过多垃圾。