import java.util.*;
import java.time.*;

class Teacher{
    private String name;
    private ArrayList<Team> mentees;
    public Panel p;
    public ArrayList<LocalDate> availableDays = new ArrayList<>();
    // public ArrayList<String> availableDays = new ArrayList<>(); 
    public String GetName(){
        return name;
    }
    public Teacher(String name){
        this.name = name;
        mentees = new ArrayList<>();
    }
    public void addMentee(Team s){
        mentees.add(s);
    }
    public void getAvailableDays(){
        System.out.println(availableDays);
    }
    public void addAvailableDays(LocalDate d1){
        availableDays.add(d1);
    }
    public void getmentees(){
        for(Team t: mentees){
            System.out.print(t.team_name+" ");
        }
    }
}

class Student{
    public String name;
    Student(String name){
        name = this.name;
    }
}

class Panel{
    int mon_tue_slots = 5;
    int other_day_slots = 7;
    public int panel_no;
    ArrayList<Teacher> panel = new ArrayList<>();
    public Panel(int number){
        panel_no = number;
    }
    public void addPanel(Teacher t){
        if(panel.size()<3){
            panel.add(t);
            t.p = this;
        }
        else{
            System.out.println("panel already has 3 teachers!!");
        }
    }
    public void getPanelDays(){
        for(Teacher i:panel){
            i.getAvailableDays();
        }
    }
    public void getPanelMembers(){
        // System.out.print("panel-"+panel_no+" : ");
        for(Teacher i: panel){
            System.out.print(i.GetName()+ " ");
        }
        System.out.println();
    }
}

class Team{
    ArrayList<Student> members = new ArrayList<>();
    public static Map<Team,Teacher> team_to_mentor = new HashMap<>();
    public String team_name;
    Teacher mentor;
    public Team(String name){
        this.team_name = name;
    }
    public void Addstudent(Student s){
        if(members.size()<4){
            members.add(s);
        }
        else{
            System.out.println("team already has 4 members!");
        }
    }
    public void createTeam(Team team, Teacher t){
        team_to_mentor.put(team, t);
        t.addMentee(team);
        team.mentor = t;
    }
    
}

class Helper{
    public static Map<Team,Panel> team_to_panel = new HashMap<>();
    static void assign_team_to_panel(){
        for(Map.Entry<Team,Teacher> entry: Team.team_to_mentor.entrySet()){
            Team team = entry.getKey();
            Teacher t = entry.getValue();
            team_to_panel.put(team, t.p);
        }
    }
    static void get_panel_teams(){
        System.out.println("team   " + "  panel");
        for(Map.Entry<Team,Panel> entry: team_to_panel.entrySet()){
            Team t1 = entry.getKey();
            Panel p1 = entry.getValue();
            System.out.println(t1.team_name+"   "+p1.panel_no);
        }
    }
}

class Schedule{

}

public class TimeTableScheduler1 {
public static void main(String[] args) {
    Student s1 = new Student("s1");
    Student s2 = new Student("s2");
    Student s3 = new Student("s3");
    Student s4 = new Student("s4");
    Student s5 = new Student("s5");
    Student s6 = new Student("s6");
    Student s7 = new Student("s7");
    Student s8 = new Student("s8");

    Teacher t1 = new Teacher("Preet");
    Teacher t2 = new Teacher("Shruthi");
    Teacher t3 = new Teacher("Adithya");
    Teacher t4 = new Teacher("Roopa");
    Teacher t5 = new Teacher("Chitra");
    Teacher t6 = new Teacher("Nagegowda");

    Team team1 = new Team("pk_01");
    team1.createTeam(team1, t1);
    Team team2 = new Team("sj_01");
    team2.createTeam(team2, t2);
    Team team3 = new Team("ab_01");
    team3.createTeam(team3, t3);
    Team team4 = new Team("rr_01");
    team4.createTeam(team4, t4);
    Team team5 = new Team("cgm_01");
    team5.createTeam(team5, t5);
    Team team6 = new Team("nks_01");
    team6.createTeam(team6, t6);
    Team team7 = new Team("pk_02");
    team7.createTeam(team7, t1);
    Team team8 = new Team("sj_02");
    team8.createTeam(team8, t2);
    Team team9 = new Team("ab_02");
    team9.createTeam(team9, t3);
    Team team10 = new Team("rr_02");
    team10.createTeam(team10, t4);
    Team team11 = new Team("cgm_02");
    team11.createTeam(team11, t5);
    Team team12 = new Team("nks_02");
    team12.createTeam(team12, t6);
    Team team13 = new Team("pk_03");
    team13.createTeam(team13, t1);
    Team team14 = new Team("sj_03");
    team14.createTeam(team14, t2);
    Team team15 = new Team("ab_03");
    team15.createTeam(team15, t3);
    
    System.out.println("Team    mentor");
    for(Map.Entry<Team,Teacher> entry: Team.team_to_mentor.entrySet()){
        Team s = entry.getKey();
        Teacher t = entry.getValue();
        String t_name = t.GetName();
        System.out.println(s.team_name+" : "+t_name);
    }
    Panel p1 = new Panel(1);
    p1.addPanel(t1);
    p1.addPanel(t2);
    p1.addPanel(t3);
    Panel p2 = new Panel(2);
    p2.addPanel(t4);
    p2.addPanel(t5);
    p2.addPanel(t6);

    
    t1.addAvailableDays(LocalDate.of(2023, 4, 15));
    t1.addAvailableDays(LocalDate.of(2023, 4, 16));
    t2.addAvailableDays(LocalDate.of(2023, 4, 16));
    t2.addAvailableDays(LocalDate.of(2023, 4, 17));
    t3.addAvailableDays(LocalDate.of(2023, 4, 17));
    t3.addAvailableDays(LocalDate.of(2023, 4, 15));
    p1.getPanelDays();
    
}

}
// System.out.println("panel-1:");
// p1.getPanelMembers();
// System.out.println("panel-2:");
// p2.getPanelMembers();
// System.out.println(t1.p.panel_no);
// t1.getmentees();

// System.out.println(team1.mentor.p.panel_no);

// Helper.assign_team_to_panel();
// Helper.get_panel_teams();
// t1.getmentees();