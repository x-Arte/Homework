#include<stdio.h>
#include<math.h>
#include<stdlib.h>
#include<string.h>

#pragma warning(disable:4996)//'prevent er:scanf is unsafe'

#define MAX_STUNAME_LENGTH 10
#define MAX_SUBNAME_LENGTH 10
#define MAX_SUBJECT 10
#define TRUE 1
#define FALSE 0
#define STOP_AVERAGE -1212

typedef struct student
{
    char name[MAX_STUNAME_LENGTH + 1];
    double  grade[MAX_SUBJECT];
    int  stu_name_length;
    struct student* next_stu;
} student_data;

void welcome();
void setting(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
void add_sub(int* sub_n, char* sub_name[]);
student_data* add_stu(int flag, int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
student_data* search_stu(int flag, student_data* p0);//number
student_data* search_stu_name(int* stu_n,char this_name[], student_data* p0);//name
int search_sub_name(char this_name[], char* sub_name[], int* sub_n);
void new_student(int* sub_n, char* sub_name[], student_data* p);
void enter_stu_name(char this_name[]);
void enter_sub_name(char sun_name[]);
void print_main_menu();
void input_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
void insert_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
void delete_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
void deleting(student_data* p,int* stu_n);//delete next
void more_subject(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
void calculate(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);
double calculate_average_stu(int* sub_n, student_data* p);
double calculate_average_sub(int* stu_n, int n, student_data* p0);
void print_stu_data(int* sub_n, char* sub_name[], student_data* p);
void print_all_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0);


int main()
{
    int choice = 1, choice2 = 1, flag;
    int sub_n[1] = { 0 }, stu_n[1] = { 0 };
    char* sub_name[MAX_SUBJECT];
    char this_name[MAX_STUNAME_LENGTH + 1];
    student_data* p0 = (student_data*)malloc(sizeof(student_data));//no data
	p0->next_stu=0;
    student_data* p;
    student_data* p1;
    welcome();
    setting(stu_n,sub_n,sub_name,p0);
    do
    {
        system("cls");
        print_main_menu();
        scanf("%d", &choice);
        scanf("%*[^\n]%*c");
        switch (choice)
        {
        case 0:
            printf("Bye!\n");
            break;

        case 1:
            input_data(stu_n, sub_n, sub_name, p0);
            break;

        case 2:
            calculate(stu_n, sub_n, sub_name, p0);
            break;

        case 3:
			system("cls");
            while (choice2)
            {
                printf("============================================\n");
                printf("|Please enter your choice:                 |\n");
                printf("|1.Search a student whose number is No.n   |\n");
                printf("|2.Search a sutdent and enter his/her name.|\n");
                printf("|0.return to previous menu.                |\n");
                printf("============================================\n");
                scanf("%d", &choice2);
                scanf("%*[^\n]%*c");
                switch (choice2)
                {
                case 1:
                    printf("Please enter the number of the student (from 1\n");
                    scanf("%d", &flag);
                    scanf("%*[^\n]%*c");
                    if (flag <= 0 || flag > * stu_n) printf("Wrong number!\n");
                    else
                    {
                        p = search_stu(flag, p0);
                        print_stu_data(sub_n, sub_name, p);
                        choice2 = 0;
                    }
                    break;

                case 2:
                    enter_stu_name(this_name);
					p = p0;
                    p1 = NULL;
                    while ((p = search_stu_name(stu_n, this_name, p)) != NULL)
                    {
                        print_stu_data(sub_n, sub_name, p);
                        p1 = p;
                    }
                    if(p1 == NULL) printf("Error: Not found!\n");
					choice2 = 0;
                    break;

                case 0:
                    choice2 = 0;
                    break;

                default:
                    printf("Wrong choice!\n");
                    break;
                }
            }
            system("pause");
			choice2 = 1;
            break;

        case 4:
            print_all_data(stu_n, sub_n, sub_name, p0);
            break;

        default:
            printf("Wrong choice!\n");
            system("pause");
            break;
        }
    } while (choice);
    return 0;
}

void welcome()
{
    printf("Welcome to use the Program For the Statistics and Analysis of Students' Achievement(PFSAS)\n");
    system("pause");
    system("cls");
}

void setting(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    //student_data* p = (student_data*)malloc(sizeof(student_data));
    //p0->next_stu = p;
	strcpy(p0->name,"000");
    //p->next_stu = (student_data*)malloc(sizeof(student_data));//no data : p0->node0->node1->node2
    //p = p->next_stu;
    printf("We need to create a link.\n");
    system("pause");
    system("cls");
    add_sub(sub_n, sub_name);
    add_stu(0, stu_n, sub_n, sub_name, p0);
}

void add_sub(int* sub_n, char* sub_name[])
{
    int this_step, add = 0, i;
    do
    {
        this_step = 1;
        printf("Please enter the number of subjects you want to add(press 'enter' to end the input):\n");
        scanf("%d", &add);
        scanf("%*[^\n]%*c");
        if (add <= 0) printf("Error:Number of subjects cannot be negative!\n");
        else if ((add + *sub_n) > MAX_SUBJECT) printf("Error:Exceeded maximum subjects , increase MAX_SUBJECT and recompile or re-enter the number of subject.\n");
        else this_step = 0;
    } while (this_step);
    
    for (i = *sub_n; i < *sub_n + add; i++)
    {
        sub_name[i] = (char*)malloc(sizeof(char)*MAX_SUBNAME_LENGTH);
        printf("Please enter the name of subject %d:\n", i + 1);
        enter_sub_name(sub_name[i]);
    }
    *sub_n += add;
	
}

student_data* add_stu(int flag, int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int this_step, add = 0, i; 
    student_data* p1, *p2;
    student_data* p = (student_data*)malloc(sizeof(student_data));
    do
    {
        this_step = 1;
        printf("Please enter the number of student you want to input this time:\n");
        scanf("%d", &add);
        scanf("%*[^\n]%*c");
        if (add <= 0)  printf("Error:Number of students cannot be negative!\n");
        else this_step = 0;
    } while (this_step);
	
    p1 = search_stu(flag, p0);
	p2 = p1->next_stu;
    p1->next_stu = p;

    for (i = flag; i < flag + add; i++)
    {
        printf("student %d:\n", i + 1);
        new_student(sub_n, sub_name, p);
        printf("------------------------------------------------------------------------------------------------------------------------\n");
        if (i != flag + add - 1)
        {
            p->next_stu = (student_data*)malloc(sizeof(student_data));
            p = p->next_stu;
        }
    }
    *stu_n += add;
	p->next_stu = p2;
    return p;
}

student_data* search_stu(int flag, student_data* p0)//No.n 
{
    student_data* p = p0;
    int i;
    for (i = 0; i < flag; i++) p = p->next_stu;
    return p;
}

student_data* search_stu_name(int* stu_n, char this_name[], student_data* p0)
{
    student_data* p;
    for (p = p0->next_stu; p!=NULL; p = p->next_stu) if (strcmp(p->name, this_name)==0) return p;
    return NULL;
}

int search_sub_name(char this_name[], char* sub_name[], int* sub_n)
{
    int i;
    for (i = 0; i < *sub_n; i++)
    {
        if (strcmp(this_name, sub_name[i]) == 0) return i;
    }
    return -1;
}

void new_student(int* sub_n, char* sub_name[], student_data* p)
{
    int j, k;
    enter_stu_name(p->name);
    p->stu_name_length = strlen(p->name);
    for (j = 0; j < *sub_n; j++)//grade
    {
        printf("Please enter student ");
        for (k = 0; k <= p->stu_name_length; k++) printf("%c", p->name[k]);
        printf("'s grade of subject ");
        for (k = 0; k <= MAX_SUBNAME_LENGTH && *(*(sub_name + j) + k) != '\0'; k++) printf("%c", *(*(sub_name + j) + k));
        printf(":\n(enter a negative number to represent that the student didn't take the exam for this course)\n");
        scanf("%lf", &p->grade[j]);
    }
    scanf("%*[^\n]%*c");
}

void enter_stu_name(char this_name[])
{
    int j, this_step;
    do//name
    {
        printf("Please enter the name of this student (press 'enter' to end the input):\n");
        this_step = 1;
        scanf("%s", this_name);
        scanf("%*[^\n]%*c");
        for (j = 0; this_name[j] != '\0'; j++);
        if (j >= MAX_STUNAME_LENGTH) printf("Error:Exceeded maximum char of this student's name , increase MAX_STUNAME and recompile or re-enter the name of the student.\n");
        else this_step = 0;
    } while (this_step);

}

void enter_sub_name(char sub_name[])
{
    int this_step, j;
    do
    {
        this_step = 1;
        scanf("%s", sub_name);
        scanf("%*[^\n]%*c");
        for (j = 0; *(sub_name + j) != '\0'; j++); //printf("%c j=%d\n", *(*(sub_name + i) + j), j);
        if (j >= MAX_SUBNAME_LENGTH) printf("Error:Exceeded maximum char of this subject's name , increase MAX_SUBNAME and recompile or re-enter the name of the subject.\n");
        else this_step = 0;
    } while (this_step);
}


void print_main_menu()
{
    printf("===================================================================\n");
    printf("|Please enter your choice:                                        |\n");
    printf("|1.Input data                                                     |\n");
    printf("|2.Calculate                                                      |\n");
    printf("|3.Search for the selected data                                   |\n");
    printf("|4.Output all data                                                |\n");
    printf("|0.Exit(this operation will delete all of the data you've entered)|\n");
    printf("===================================================================\n");
}

void input_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int choice;
    system("cls");
    do
    {
        
        printf("===================================================================\n");
        printf("|Please enter your choice:                                        |\n");
        printf("|1.Insert students                                                |\n");
        printf("|2.Delete a student                                               |\n");
        printf("|3.Add subjects                                                   |\n");
        printf("|0.return to previous menu                                        |\n");
        printf("===================================================================\n");
        scanf("%d", &choice);
        scanf("%*[^\n]%*c");
        switch (choice)
        {
        case 0:
            break;
        case 1:
            insert_stu(stu_n, sub_n, sub_name, p0);
            break;
        case 2:
            delete_stu(stu_n, sub_n, sub_name, p0);
            break;
        case 3:
            more_subject(stu_n, sub_n, sub_name, p0);
            break;
        default:
            printf("Wrong choice!\n");
            system("pause");
            break;
        }
    } while (choice);
}

void insert_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int flag,this_step = 1;
    system("cls");
    while (this_step)
    {
        system("cls");
        printf("Please enter the location of the new student you want to put:\n");
        printf("(tips: the student's number will be the number you've entered plus 1, if you want him to be the first one, please enter '0')\n");
        printf("(for example: stu1 stu2 stu3\n");
        printf("               1    2    3  \n");
        printf("                  ^         \n");
        printf("                  |         \n");
        printf("enter '1' (suppose this student's name is stu4)\n");
        printf("           -> stu1 stu4 stu2 sut4)\n");
        scanf("%d", &flag);
        scanf("%*[^\n]%*c");
        if (flag > * stu_n || flag < 0) printf("Wrong number!\n");
        else
        {
            this_step = 0; 
			add_stu(flag, stu_n, sub_n, sub_name, p0);//the end of insert
        }
    }
    printf("Done.\n");
    system("pause");
}

void delete_stu(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int  choice = 1, flag, i;
    char this_name[MAX_SUBNAME_LENGTH + 1];
    student_data* p;
    student_data* p1 = p0;
    while (choice)
    {
        system("cls");
        printf("Please enter your choice:                 \n");
        printf("1.Delete a student whose number is No.n   \n");
        printf("2.Delete a sutdent and enter his/her name.\n");
        scanf("%d", &choice);
        scanf("%*[^\n]%*c");
        switch (choice)
        {
        case 1:
            printf("Please enter the number of the student you want to delete(from 1\n");
            scanf("%d", &flag);
            scanf("%*[^\n]%*c");
            if (flag <= 0 || flag > *stu_n) printf("Wrong number!\n");
            else
            {
                p = search_stu(flag - 1, p0);
                deleting(p, stu_n);
                choice = 0;
            }
            break;

        case 2:
            enter_stu_name(this_name);
            if ((p = search_stu_name(stu_n, this_name, p0)) != NULL)
            {
                for (i = 1; p1->next_stu != p; i++) p1 = p1->next_stu;
                deleting(p1, stu_n);
                choice = 0;
            }
            else printf("Error: Not found!\n");
            break;

        default:
            printf("Wrong choice!\n");
            choice = 1;
            break;
        }
    }
   
}

void deleting(student_data* p,int *stu_n)
{
    student_data* p1;
    p1 = p->next_stu;
    p->next_stu = p1->next_stu;
    free(p1);
    (*stu_n)--;
}

void more_subject(int *stu_n, int *sub_n, char *sub_name[], student_data* p0)
{
    int add = *sub_n, i, j;
    student_data* p;
    system("cls");
    add_sub(sub_n, sub_name);
    add = *sub_n - add;
    p = p0->next_stu;
    for (i = 0; i < *stu_n; i++)
    {
        for (j = *sub_n - add ; j < *sub_n; j++)
        {
            printf("Please enter student %s", p->name);
            printf("'s grade of subject %s", sub_name[j]);
            printf(":\n(enter a negative number to represent that the student didn't take the exam for this course)\n");
            scanf("%lf", &p->grade[j]);
        }
        if (i != *stu_n - 1) p = p->next_stu;
    }
}

void calculate(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int flag, choice = 1, choice2 = 1, n = -1;
    double average = 0;
    char this_name[MAX_SUBNAME_LENGTH + 1];
    student_data* p = (student_data*)malloc(sizeof(student_data));
    student_data* p1;
    system("cls");
    do
    {
        printf("===================================================================\n");
        printf("|Please enter your choice:                                        |\n");
        printf("|1.Calculate specific student's average grade                     |\n");
        printf("|2.Calculate specific subject's average grade                     |\n");
        printf("|0.return to previous menu                                        |\n");
        printf("===================================================================\n");
        scanf("%d", &choice);
        scanf("%*[^\n]%*c");
        switch (choice)
        {
        case 1:
            printf("--------------------------------------------------------------------\n");
            printf("Please enter your choice:                 \n");
            printf("1.Search a student whose number is No.n   \n");
            printf("2.Search a sutdent and enter his/her name.\n");
            scanf("%d", &choice2);
            scanf("%*[^\n]%*c");
            while (choice2)
            {
                switch (choice2)
                {
                case 1:
                    printf("Please enter the number of the student (from 1\n");
                    scanf("%d", &flag);
                    scanf("%*[^\n]%*c");
                    if (flag <= 0 || flag > * stu_n) printf("Wrong number!\n");
                    else
                    {
                        p = search_stu(flag, p0);
                        average = calculate_average_stu(sub_n, p);
                        if (average == -1) printf("This student did not attend any exam.!\n");
                        else printf("Student %s's average grade is %lf\n", p->name, average);
                        choice2 = 0;
                    }
                    break;

                case 2:
                    enter_stu_name(this_name);
                    p = p0;
                    p1 = NULL;
                    while ((p = search_stu_name(stu_n, this_name, p)) != NULL)
                    {
                        average = calculate_average_stu(sub_n, p);
                        if (average == -1) printf("This student did not attend any exam.!\n");
                        else printf("Student %s's average grade is %lf\n", p->name, average);
                        p1 = p;
                    }
                    if (p1 == NULL) printf("Error:Not found!\n");
                    choice2 = 0;
                    break;

                default:
                    printf("Wrong choice!\n");
                    break;

                }
            }
            system("pause");
            break;

        case 2:
            printf("Please enter the name of the subject:\n");
            enter_sub_name(this_name);
            n = search_sub_name(this_name, sub_name, sub_n);
            if (n == -1) printf("Not found!\n");
            else
            {
                average = calculate_average_sub(stu_n, n, p0);
                if (average == -1) printf(" No students took the exam.\n");
                else printf("Subject %s's average grade is %lf\n", sub_name[n], average);
            }
            break;

        case 0:
            choice = 0;
            break;

        default:
            printf("Wrong number!\n");
            break;
        }
    } while (choice);
}

double calculate_average_stu(int* sub_n, student_data* p)
{
    double average, sum = 0;
    int i, n = 0;
    for (i = 0; i < *sub_n; i++)
    {
        if (p->grade[i] >= 0)
        {
            sum += p->grade[i];
            n++;
        }
    }
    if (n == 0) average = -1;
    else average = sum / n;
    return average;
}

double calculate_average_sub(int* stu_n, int n, student_data* p0)
{
    double average, sum = 0;
    int i, n1 = 0;
    student_data* p;
    p = p0->next_stu;
    for (i = 0; i < *stu_n; i++)
    {
        if (p->grade[n] >= 0)
        {
            sum += p->grade[n];
            n1++;
        }
        if (i != *stu_n - 1) p = p->next_stu;
    }
    if (n1 == 0) average = -1;
    else average = sum / n1;
    return average;
}

void print_stu_data(int* sub_n, char* sub_name[], student_data* p)
{
    int i;
    printf("-------------------------------------------------------------\n");
    printf("|Student %s's data:                                          \n",p->name);
    for (i = 0; i < *sub_n; i++)
        printf("|Subject %d %s's grade:%lf                                          \n", i + 1, sub_name[i], p->grade[i]);
    printf("-------------------------------------------------------------\n");
}

void print_all_data(int* stu_n, int* sub_n, char* sub_name[], student_data* p0)
{
    int i, j, k, q;
    student_data* p;
    double average;
    p = p0->next_stu;
    system("cls");
    for (i = 0; i <= *sub_n + 1; i++)
    {
        for (q = 0; q <= MAX_SUBNAME_LENGTH; q++) printf("-");
    }
    printf("\n");
    for (q = 0; q <= MAX_STUNAME_LENGTH + 1; q++) printf(" ");
    for (i = 0; i < *sub_n; i++)
    {
        for (j = 0; *(sub_name[i] + j) != '\0'; j++) printf("%c", *(sub_name[i] + j));
        for (k = MAX_SUBNAME_LENGTH + 1 - j; k > 0; k--) printf(" ");
    }
    printf("|Student's average");
    printf("\n");
    for (i = 0; i <= *sub_n + 1; i++)
    {
        for (q = 0; q <= MAX_SUBNAME_LENGTH; q++) printf("-");
    }
    printf("\n");
    

    for (i = 0; i < *stu_n; i++)
    {
        for (j = 0; p->name[j] != '\0'; j++) printf("%c", p->name[j]);
        for (k = MAX_STUNAME_LENGTH + 1 - j; k > 0; k--) printf(" ");
        printf("|");
        for (j = 0; j < *sub_n; j++)
        {
            if (p->grade[j] < 0)
            {
                printf("x");
                for (q = 0; q < MAX_SUBNAME_LENGTH; q++) printf(" ");
            }
            else
            {
                if (p->grade[j] < 10)
                {
                    printf("%.2lf", p->grade[j]);
                    for (q = 0; q <= MAX_SUBNAME_LENGTH - 4; q++) printf(" ");
                }
                    
                else if (p->grade[j] < 100)
                {
                    printf("%.2lf", p->grade[j]);
                    for (q = 0; q <= MAX_SUBNAME_LENGTH - 5; q++) printf(" ");
                }
                else
                {
                    printf("%.2lf", p->grade[j]);
                    for (q = 0; q <= MAX_SUBNAME_LENGTH - 6; q++) printf(" ");
                }
            }
        }
        average = calculate_average_stu(sub_n, p);
        if (average == -1)printf("x");
        else printf("%.2lf", average);
        printf("\n");
        for (j = 0; j <= *sub_n + 1; j++)
        {
            for (q = 0; q <= MAX_SUBNAME_LENGTH; q++) printf("-");
        }
        printf("\n");
        p = p->next_stu;
    }

    printf("Subject");
    for (k = MAX_STUNAME_LENGTH + 1 - 7; k > 0; k--) printf(" ");
    printf("|");
    for (i = 0; i < *sub_n; i++)
    {
        average = calculate_average_sub(stu_n, i, p0);
        if (average < 0)
        {
            printf("x");
            for (q = 0; q < MAX_SUBNAME_LENGTH; q++) printf(" ");
        }
        else
        {
            if (average < 10)
            {
                printf("%.2lf", average);
                for (q = 0; q <= MAX_SUBNAME_LENGTH - 4; q++) printf(" ");
            }

            else if (average < 100)
            {
                printf("%.2lf", average);
                for (q = 0; q <= MAX_SUBNAME_LENGTH - 5; q++) printf(" ");
            }
            else
            {
                printf("%.2lf", average);
                for (q = 0; q <= MAX_SUBNAME_LENGTH - 6; q++) printf(" ");
            }
        }
    }
    printf("\n");
    system("pause");
}
