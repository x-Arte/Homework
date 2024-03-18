*author @x-Arte Mengyi Zhou 07/01/2020*
# 一．	功能描述
## Part1 新建
在程序开始时自动创建表格，可一次性输入多个学科及其名称，和多个学生成绩。
## Part2 输入数据
本部分包括三个功能，插入学生数据，删除学生数据，添加科目。 
###	插入学生数据
输入新增的学生数目，和想插入的学生队列的前一位学生的序号，并输入新学生的全部成绩。
###	删除一位学生数据
支持输入学生队列序号的删除和输入姓名的删除，但在有重名情况时仅会删除在队列中第一位拥有此姓名的学生。
###	添加科目
支持一次性添加多个科目，但科目数量不会超过上限。输入添加的科目基本信息后，会自动循环每位已有的学生，进行新添加的科目成绩输入。
## Part3 计算数据
本部分包括两个功能，计算学生平均分和计算科目平均分
### 计算学生平均分
根据学生参加考试的考试科目数量进行平均分计算，没有参加考试的科目不会参与计算。若学生没有参加考试，则会输出没有参加考试。
支持根据学生在学生队列中的序号或者学生姓名进行计算，在根据姓名计算时所有的重名学生按照在队列中的排序顺序进行平均分输出。
### 计算科目平均分
输入科目名称并计算参加考试的学生平均分，不会包含没有参加考试的学生的成绩。若没有学生参加考试，则会输出没有学生参加考试
## Part4 搜索数据
可以根据学生在学生队列中的序号或者学生姓名进行查找并输出该学生的成绩，在根据姓名搜索时所有的重名学生按照在队列中的排序顺序进行输出。
## Part5 输出全部数据
以表格形式输出全部数据，学生没有参与考试用“x”代替，同时会输出所有学生的平均分和所有科目的平均分，平均分计算规则与Part2相同。
## 备注：
1.	具有合理报错和循环输入的功能，但仍有部分错误不支持识别，在输入数据时尽量按照提示输入。
2.	支持任意人数储存，但限制科目的数量，限制的科目数量可以在程序预处理部分更改。
3.	学生姓名和科目名称均有长度限制，最大长度可以在程序预处理部分更改。
4.	学生成绩输入不支持输入字母，也没有分辨字母功能，尽量输入正确。（报错随缘
5.	学生成绩输入负数代表没有参加本学科考试。
6.	支持新建及后期操作时增加任意数目学生数据。
7.	插入学生数据时支持插入学生队列的任意位置。
8.	不支持更改已输入的数据。
9.	仅支持同时删除一位学生，支持按照所排位置和学生姓名进行删除。
10.	支持学生重名，在选择输出时会一次性输出所有相同姓名学生信息。
11.	输出学生一功能可能出现任意成绩为任意负数，均代表学生未参加此考试。
# 函数介绍
本程序共使用了21自定义个函数+main函数，自定义函数列表如下。
1.	void welcome();
功能：输出欢迎词。
2.	void setting(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：新建并初始化链表。
3.	void add_sub(int* sub_n, char* sub_name[]);
功能：一次性添加多个科目。
4.	student_data* add_stu(int flag, int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：一次性添加多个学生。
5.	student_data* search_stu(int flag, student_data* p0);//number
功能：根据学生在队列中的序号查找储存学生信息的节点，并返回指向这个节点的指针，没有找到则返回NULL。
6.	student_data* search_stu_name(int* stu_n,char this_name[], student_data* p0);//name
功能：根据学生姓名查找储存学生信息的节点，并返回指向这个节点的指针，没有找到则返回NULL。
7.	int search_sub_name(char this_name[], char* sub_name[], int* sub_n);
功能：根据学科姓名查找学科在学科队列中的序号，并返回序号，没有找到则      返回-1。
8.	void new_student(int* sub_n, char* sub_name[], student_data* p);
功能：在已给的指针指向的节点中填入当前一个新学生的数据。
9.	void enter_stu_name(char this_name[]);
功能：填入当前学生的姓名。
10.	void enter_sub_name(char sun_name[]);
功能：填入当前学生的姓名。
注：与上一个函数的区别为提示不同。
11.	void print_main_menu();
功能：输出主菜单 
12.	void input_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：输出“输入数据”的次菜单，并根据选择进行不同函数的调用并输出结果。
13.	void insert_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：在已有的学生队列中插入新的不限数量的学生数据。
14.	void delete_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：根据用户的选择进行输入学生队列序号的删除或输入姓名的删除。
15.	void deleting(student_data* p);//delete next
功能：删除所给指针的下一个数据。
16.	void more_subject(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：在已有的科目后添加新的不超过限制的多个学科，并输入学生在这些科目中的成绩。 
17.	void calculate(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：输出“计算数据”的次菜单，并根据选择进行不同函数的调用并输出结果。
18.	double calculate_average_stu(int* sub_n, student_data* p);
功能：计算所给指针的指向的学生的平均成绩，不包含学生未参加考试。
19.	double calculate_average_sub(int* stu_n, int n, student_data* p0);
功能：计算所给序号的科目的平均成绩，不包含学生未参加考试。
20.	void print_stu_data(int* sub_n, char* sub_name[], student_data* p);
功能：输出所给指针的指向的学生的成绩。
21.	void print_all_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
功能：以表格形式输出所有学生的成绩及各个科目的平均分。
