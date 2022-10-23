import java.util.Arrays;
import java.util.Scanner;

class Training
{

    int week_plan[] = new int[7];
    int Dumbbells_weight;
    int Bench_weight;
    int Squat_weight;
    int Deadlift_weight;
    int weight_sum;
    String week_number;
    private int Time_for_rest_min = 10;
    public Training(int Dumbbells_weight,int Bench_weight,int Squat_weight,int Deadlift_weight)
    {
        this.Bench_weight = Bench_weight;
        this.Squat_weight = Squat_weight;
        this.Deadlift_weight = Deadlift_weight;
        this.Dumbbells_weight = Dumbbells_weight;
        this.weight_sum =  this.Bench_weight+this.Dumbbells_weight+this.Squat_weight+this.Deadlift_weight;
    }
    public void setTime_for_rest_min(int time)
    {
        this.Time_for_rest_min = time;
    }
    public int getTime_for_rest_min()
    {
        return Time_for_rest_min;
    }
    public String get_Description()
    {
        return  "1. Dumbbells weight, these are dumbbells with which the exercise is performed\n" +
                "2. Bench weight, this is the barbell weight for the bench press\n" +
                "3. Suat weight, it's a barbell squat weight\n" +
                "4. Deadlift weight, this is the deadlift weight\n" +
                "5. Time for rest, this is the rest time between approaches and between sets";

    }
    public String get_Title(int key)
    {
        switch (key) {
            case 1:
                return "1. Dumbbells weight, these are dumbbells with which the exercise is performed\n";
            case 2:
                return "2. Bench weight, this is the barbell weight for the bench press\n";
            case 3:
                return "3. Squat weight, it's a barbell squat weight\n";
            case 4:
                return "4. Deadlift weight, this is the deadlift weight\n";
            case 5:
                return "5. Time for rest, this is the rest time between approaches and between sets";

        }
        return "Error";
    }
    public void get_training_plan()
    {
        this.week_plan[0]=smallest(smallest(this.Bench_weight,this.Dumbbells_weight),smallest(this.Squat_weight,this.Deadlift_weight));
        this.week_plan[2] = biggest(smallest(this.Bench_weight,this.Dumbbells_weight),smallest(this.Squat_weight,this.Deadlift_weight));
        this.week_plan[4] = smallest(biggest(this.Bench_weight,this.Dumbbells_weight),biggest(this.Squat_weight,this.Deadlift_weight));
        this.week_plan[6] = biggest(biggest(this.Bench_weight,this.Dumbbells_weight),biggest(this.Squat_weight,this.Deadlift_weight));
    }
    private int smallest(int a,int b)
    {
        if (a < b)
        {
            return a;
        }
        return b;
    }
    private int biggest(int a,int b)
    {
        if (a > b)
        {
            return a;
        }
        return b;
    }
    public void print_tr_plan()
    {
        for (int i = 0; i < 7; i++)
        {
            if (i % 2 == 1)
            {
                System.out.println("Rest today");
            }
            else
            {
                System.out.println("Training weight = "+this.week_plan[i]);
            }

        }
    }
    public void print_all()
    {
        System.out.println("Bench = "+this.Bench_weight+"\n"+
                            "Suat = "+this.Squat_weight+"\n"+
                            "Deadlift = "+this.Deadlift_weight+"\n"+
                            "Dumbells = "+this.Dumbbells_weight+"\n"+
                            "Rest time = "+this.Time_for_rest_min+"\n");
        this.print_tr_plan();
    }
}

class Collection
{
    private int count = 1;
    Training training_data[] = new Training[count];
    private int counter = 0;
    public void add_el(Training week)
    {
        this.training_data[counter] = week;
        this.training_data[counter].get_training_plan();
        this.training_data[counter].week_number ="week " + (counter + 1);//
        //System.out.println(this.training_data[counter].week_number);
        this.training_data = Arrays.copyOf(training_data,this.count+1);
        count++;
        counter++;
    }
    public void remove_el(int el)
    {
        System.out.println("lenght = "+training_data.length);
        if (training_data.length == 1)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        if (el == training_data.length)
        {
            System.out.println("Error! This item can not be deleted!!");
            return;
        }
        Training copy[] = new Training[count];
        int n=0;
        for (int i = 0; i <training_data.length - 1 ; i++)
        {
            if (i != el-1)
            {
                copy[n] = training_data[i];
                n++;
            }
        }
        this.training_data = Arrays.copyOf(copy,n+1);
    }
    public void print_one(int week)
    {
        this.training_data[week].print_all();
    }
    public void print_All()
    {
        if (training_data.length == 1)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        for (int i = 0; i < this.training_data.length-1; i++)
        {
            this.training_data[i].print_all();
        }
    }
    public void print_list()
    {
        if (training_data.length == 1)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        for (int i = 0; i < this.training_data.length-1; i++)
        {
            System.out.println(this.training_data[i].week_number+"\n Total week load = " + this.training_data[i].weight_sum);
        }
    }
    public void sort()
    {
        int n = this.training_data.length;
        Training temp;
        if (training_data.length <= 2)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        for(int i=0; i < n-1; i++){
            for(int j=1; j < (n-i-1); j++)
            {
                if(this.training_data[j-1].weight_sum > this.training_data[j].weight_sum)
                {
                    temp = this.training_data[j-1];
                    this.training_data[j-1] = this.training_data[j];
                    this.training_data[j] = temp;
                }

            }
        }
    }
    public void find_by_phrase(String phrase)
    {
        if (training_data.length == 1)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        for (int i = 0; i < this.training_data.length-1; i++)
        {
            if (this.training_data[i].week_number.equals(phrase))
            {
                this.training_data[i].print_all();
                return;
            }
        }
        System.out.println("Error!!");
    }
    public void search_by_bench(int bench)
    {
        if (training_data.length == 1)
        {
            System.out.println("Error! Not enough items!");
            return;
        }
        for (int i = 0; i < this.training_data.length-1; i++)
        {
            if (this.training_data[i].Bench_weight == bench)
            {
                this.training_data[i].print_all();
                return;
            }
        }
        System.out.println("Error!!");

    }
}

public class Main
{
    public static void print_menu()
    {
        System.out.println("Welcome to the training database!\n" +
                "Choose an option to proceed:\n" +
                "1 - print weeks list\n" +
                "2 - add new week\n" +
                "3 - remove week\n" +
                "4 - sort weeks by load\n" +
                "5 - search weeks by date\n" +
                "6 - search weeks by bench load\n" +
                "7 - print detailed training list for every weeks\n" +
                "0 - exit the program");
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean menu = true;
        int dumb,dead,bench,squat;
        int elem_to_remove;
        int weight;
        String phrase;
        Collection data = new Collection();
        while (menu)
        {
            print_menu();
            int question = scan.nextInt();
            switch (question)
            {
                case 1:
                    data.print_list();
                    break;
                case 2:
                    System.out.println("print Dumbbells weight");
                    dumb = scan.nextInt();
                    System.out.println("print Bench weight");
                    bench = scan.nextInt();
                    System.out.println("print Squat weight");
                    squat = scan.nextInt();
                    System.out.println("print Deadlift weight");
                    dead = scan.nextInt();
                    Training tr = new Training(dumb,bench,squat,dead);
                    data.add_el(tr);
                    break;
                case 3:
                    System.out.println("print week to remove");
                    elem_to_remove = scan.nextInt();
                    data.remove_el(elem_to_remove);
                    break;
                case 4:
                    data.sort();
                    break;
                case 5:
                    System.out.println("print phrase");
                    phrase = scan.nextLine();
                    phrase = scan.nextLine();
                    data.find_by_phrase(phrase);
                    break;
                case 6:
                    System.out.println("print bench weight");
                    weight= scan.nextInt();
                    data.search_by_bench(weight);
                    break;
                case 7:
                    data.print_All();
                    break;
                case 0:
                    menu = false;

            }

        }


    }
}